package com.zy.mysql.mybatis.page;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {
    private final static Dialect DIALECT = new Dialect();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("Start PageInterceptor ...");
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        ParameterHandler parameterHandler = statementHandler.getParameterHandler();

        /*
         * 获取MetaObject
         * 不同版本的包装方式不同，故需要通过while找到实际的MetaObject
         */
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        while (metaObject.hasGetter("h")) metaObject = SystemMetaObject.forObject(metaObject.getValue("h"));
        while (metaObject.hasGetter("target")) metaObject = SystemMetaObject.forObject(metaObject.getValue("target"));

        /*
         * DAO层如果使用了RowBounds或者继承了RowBounds的Page，MyBatis会检测到并封装到delegate.rowBounds字段中
         */
        RowBounds rowBounds = (RowBounds) metaObject.getValue("delegate.rowBounds");

        /*
         * RowBounds为null或缺失值即为不分页的情况
         */
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        /*
         * 分页情况
         */
        Page<?> page = (Page<?>) rowBounds;
        if (page.getPageNo() > 0 && page.getPageSize() > 0) {
            // 获取原始SQL
            String originSql = (String) metaObject.getValue("delegate.boundSql.sql");
            // 将原始SQL中多余的空白字符去掉，全部转换为一个英文空格
            originSql = originSql.replaceAll("[\\s]+", " ").replaceAll("\\s{2,}", " ");
            // 获取Configuration配置，Configuration可包含MyBatis配置文件中设置的属性，如dialect
            Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");
            // 获取数据库名称以及该数据库生成SQL语句的方法
            String dialectStr = configuration.getVariables().getProperty("dialect");
            Method getLimitMethod = DIALECT.getClass().getDeclaredMethod("getLimit" + dialectStr, String.class, int.class, int.class);
            Method getCountMethod = DIALECT.getClass().getDeclaredMethod("getCount" + dialectStr, String.class);
            // 生成查询总数和分页查询SQL语句
            String countSQL = (String) getCountMethod.invoke(DIALECT, originSql);
            String limitSQL = (String) getLimitMethod.invoke(DIALECT, originSql, page.getOffset(), page.getLimit());
            // 获取数据库连接
            Connection connection = (Connection) invocation.getArgs()[0];
            // 查询总数并传给Page变量
            int count = getTotalCount(connection, parameterHandler, countSQL);
            page.setTotalRecords(count);
            // 计算总页数并传给Page变量
            int total = count / page.getPageSize();
            page.setTotalPages(count == (total * page.getPageSize()) ? total : total + 1);
            // 用新的分页查询SQL语句替换原有SQL语句替换
            // 同时修改offset，limit
            metaObject.setValue("delegate.boundSql.sql", limitSQL);
            metaObject.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
            metaObject.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 查询总数
     *
     * @param   connection 连接
     * @param   parameterHandler 参数句柄
     * @param   countSQL 查询总数语句
     * @return  总数
     * @author Yi
     * @since 2/24/2020
     */
    private int getTotalCount(Connection connection, ParameterHandler parameterHandler, String countSQL) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(countSQL);
        parameterHandler.setParameters(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        preparedStatement.close();
        return count;
    }
}
