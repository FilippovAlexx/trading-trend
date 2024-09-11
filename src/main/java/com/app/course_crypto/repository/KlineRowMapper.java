package com.app.course_crypto.repository;

import com.app.course_crypto.entity.Kline;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


@AllArgsConstructor
public class KlineRowMapper implements RowMapper<Kline> {
    private final CoinRepository coinRepository;


    @Override
    public Kline mapRow(ResultSet rs, int rowNum) throws SQLException {
        Kline kline = new Kline();
        kline.setId(rs.getLong("id"));
        kline.setCoin_id(rs.getLong("coin_id"));
        kline.setTimestamp(rs.getLong("timestamp"));
        kline.setOpen(rs.getBigDecimal("open"));
        kline.setHigh(rs.getBigDecimal("high"));
        kline.setLow(rs.getBigDecimal("low"));
        kline.setClose(rs.getBigDecimal("close"));
        return kline;
    }
}