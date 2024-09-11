package com.app.course_crypto.repository;

import com.app.course_crypto.entity.Coin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinRowMapper implements RowMapper<Coin> {

    @Override
    public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Coin coin = new Coin();
        coin.setId(rs.getLong("id"));
        coin.setName(rs.getString("name"));
        return coin;
    }
}
