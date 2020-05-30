package com.lxlt.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/29 18:38
 */
@MappedTypes(String[].class)
public class StringArrayTypeHandler implements TypeHandler<String[]> {

    ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        String s = objectMapper.writeValueAsString(strings);
        preparedStatement.setString(i, s);
    }

    @Override
    public String[] getResult(ResultSet resultSet, String columnName) throws SQLException {
        String json = resultSet.getString(columnName);
        String[] transfer = transfer(json);
        return transfer;
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        String json = resultSet.getString(i);
        return transfer(json);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String json = callableStatement.getString(i);
        return transfer(json);
    }

    private String[] transfer(String json){
        if (json == null) {
            return null;
        }
        String[] list = null;
        try {
            list = objectMapper.readValue(json, String[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }
}
