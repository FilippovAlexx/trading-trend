package com.app.course_crypto.repository;

import com.app.course_crypto.entity.Coin;
import com.app.course_crypto.entity.Kline;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class KlineRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CoinRepository coinRepository;

    public KlineRepository(JdbcTemplate jdbcTemplate, CoinRepository coinRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.coinRepository = coinRepository;
    }

    /*public List<Kline> findTop200ByCoinOrderByTimestampDesc(Long id) {
        String sql = "SELECT * FROM kline WHERE coin_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new KlineRowMapper());
    }*/

    public List<Double> getAllOpenPricesForCoin(Long id) {
        String sql = "SELECT open FROM kline WHERE coin_id = ?";
        return jdbcTemplate.queryForList(sql, new Object[]{id}, Double.class);
    }

    public void clearAll (){
        String sql = "DELETE FROM kline WHERE coin_id > -1";
        jdbcTemplate.update(sql);
    }

    public void deleteByCoinId(long coin_id) {
        String sql = "DELETE FROM kline WHERE id = ?";
        jdbcTemplate.update(sql, coin_id);
    }


    public void saveAll(List<Kline> klines) {
        String sql = "INSERT INTO kline (coin_id, timestamp, open, high, low, close) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, klines, klines.size(), (ps, kline) -> {
            ps.setLong(1, kline.getCoin_id());
            ps.setLong(2, kline.getTimestamp());
            ps.setBigDecimal(3, kline.getOpen());
            ps.setBigDecimal(4, kline.getHigh());
            ps.setBigDecimal(5, kline.getLow());
            ps.setBigDecimal(6, kline.getClose());
        });
    }
}