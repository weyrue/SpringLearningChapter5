package com.zy.mysql.jdbc;

import com.zy.web.entities.SingerPO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao {

    public List<SingerPO> findAll() {
        List<SingerPO> list = new ArrayList<>();
        Connection connection = null;

        try {
            connection = MyJdbcBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from singer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SingerPO po = new SingerPO();
                po.setId(resultSet.getLong("ID"));
                po.setFirstName(resultSet.getString("FIRST_NAME"));
                po.setLastName(resultSet.getString("LAST_NAME"));
                po.setBirthDate(resultSet.getDate("BIRTH_DATE"));
                list.add(po);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyJdbcBase.closeConnection(connection);
        }
        return list;
    }
}
