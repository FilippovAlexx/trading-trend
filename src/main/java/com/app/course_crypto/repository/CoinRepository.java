package com.app.course_crypto.repository;

import com.app.course_crypto.entity.Coin;
import com.app.course_crypto.response.CoinResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CoinRepository {
    private final JdbcTemplate jdbcTemplate;

    public CoinRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Coin findById(Long id) {
        String sql = "SELECT * FROM coin WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CoinRowMapper());
    }

    public Long findIdByName(String name) {
        String sql = "SELECT * FROM coin WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new CoinRowMapper()).getId();
    }

    public Coin findByName(String name) {
        String sql = "SELECT * FROM coin WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new CoinRowMapper());
    }

    public List<Coin> findAll() {
        String sql = "SELECT * FROM coin";
        return jdbcTemplate.query(sql, new CoinRowMapper());
    }

    public void save(Coin coin) {
        String sql = "INSERT INTO coin (name) VALUES (?)";
        jdbcTemplate.update(sql, coin.getName());
    }

    public List<CoinResponse> getAllCoins() {
        String sql = "SELECT * FROM coin";
        List<Coin> coins = jdbcTemplate.query(sql, (rs, rowNum) -> mapCoin(rs));
        return coins.stream()
                .map(coin -> CoinResponse.builder()
                        .id(coin.getId())
                        .name(coin.getName())
                        // Добавьте другие поля монеты, если они есть
                        .build())
                .collect(Collectors.toList());
    }

    private Coin mapCoin(ResultSet rs) throws SQLException {
        Coin coin = new Coin();
        coin.setId(rs.getLong("id"));
        coin.setName(rs.getString("name"));
        return coin;
    }
}