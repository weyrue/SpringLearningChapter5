package com.zy.mysql.mybatis.page;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dialect {
    private static final String REGEX_FROM = "\\s+FROM\\s+";
    private static final String REGEX_ORDER_BY = "\\s+ORDER\\sBY\\s+";
    private static final Pattern PATTERN_FROM = Pattern.compile(REGEX_FROM, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_ORDER_BY = Pattern.compile(REGEX_ORDER_BY, Pattern.CASE_INSENSITIVE);

    private static final String MYSQL_SELCT_COUNT_HEAD = "select count(1) count";

    /**
     * 获取分页查询sql
     *
     * @param sql    原始sql
     * @param offset offset
     * @param limit  limit
     * @return 分页查询sql
     * @author Yi
     * @since 2/20/2020
     */
    public String getLimitMySQL(String sql, int offset, int limit) {
        return new StringBuilder().append(sql).append(" limit ").append(offset).append(',').append(limit).toString();
    }

    /**
     * 获取查询总数sql
     *
     * @param sql 原始sql
     * @return 查询总数sql
     * @author Yi
     * @since 2/20/2020
     */
    public String getCountMySQL(String sql) {
        int indexOfFrom = getIndexOfFrom(sql);
        int indexOfOrderBy = getLastIndexOfOrderBy(sql);

        return new StringBuilder(MYSQL_SELCT_COUNT_HEAD.length() + (indexOfOrderBy - indexOfFrom)).append("select count(1) count").append(sql.substring(indexOfFrom, indexOfOrderBy)).toString();

    }

    private int getIndexOfFrom(String sql) {
        Matcher matcher = PATTERN_FROM.matcher(sql);

        if (!matcher.find()) return -1;

        return matcher.start(0);
    }

    private int getLastIndexOfOrderBy(String sql) {
        Matcher matcher = PATTERN_ORDER_BY.matcher(sql);
        if (!matcher.find()) return sql.length();
        return matcher.start(0);
    }
}
