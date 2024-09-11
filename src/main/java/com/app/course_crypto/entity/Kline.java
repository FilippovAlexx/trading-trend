package com.app.course_crypto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Kline {
    private Long id;
    private Long coin_id;
    private Long timestamp;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;

    public Kline(Long coin_id, Long timestamp, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close) {
        this.coin_id = coin_id;
        this.timestamp = timestamp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public static Kline fromJsonArray(Long id, JSONArray jsonArray) {
        Long coin_id = id;
        Long timestamp = (Long) Long.parseLong(jsonArray.getString(0));
        BigDecimal open = new BigDecimal(jsonArray.getString(1));
        BigDecimal high = new BigDecimal(jsonArray.getString(2));
        BigDecimal low = new BigDecimal(jsonArray.getString(3));
        BigDecimal close = new BigDecimal(jsonArray.getString(4));

        return new Kline(coin_id, timestamp, open, high, low, close);
    }
}
