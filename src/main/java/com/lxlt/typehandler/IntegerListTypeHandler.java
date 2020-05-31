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
import java.util.List;

/**
 * @PackgeName: com.lxlt.typehandler
 * @ClassName: IntegerListTypeHandler
 * @Author: Li Haiquan
 * Date: 2020/5/31 16:44
 * project name: project2
 */
@MappedTypes(List.class)
public class IntegerListTypeHandler implements TypeHandler<List<Integer>> {

    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<Integer> integers, JdbcType jdbcType) throws SQLException {
        String s = objectMapper.writeValueAsString(integers);
        preparedStatement.setString(i, s);
    }

    @Override
    public List<Integer> getResult(ResultSet resultSet, String s) throws SQLException {
        String json = resultSet.getString(s);
        return transfer(json);
    }

    @Override
    public List<Integer> getResult(ResultSet resultSet, int i) throws SQLException {
        String json = resultSet.getString(i);
        return transfer(json);
    }

    @Override
    public List<Integer> getResult(CallableStatement callableStatement, int i) throws SQLException {
        String json = callableStatement.getString(i);
        return transfer(json);
    }

    private List<Integer> transfer(String json){
        if (json == null) {
            return null;
        }
        List<Integer> list = null;
        try {
            list = objectMapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }
}
