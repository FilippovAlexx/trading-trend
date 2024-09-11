package com.app.course_crypto.service;

import com.app.course_crypto.entity.Coin;
import com.app.course_crypto.repository.CoinRepository;
import com.app.course_crypto.repository.KlineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class TrendAnalisService {

    private final KlineRepository klineRepository;
    private final CoinRepository coinRepository;

    public String analyzeTrend(String coinName) {
        Coin coin = coinRepository.findByName(coinName);
        List<Double> klines = klineRepository.getAllOpenPricesForCoin(coin.getId());

        if (klines.size() <= 200) {
            return "Insufficient data to determine trend";
        }

        BigDecimal shortTermMA = calculateMovingAverage(klines.subList(0, 50), 50);
        BigDecimal longTermMA = calculateMovingAverage(klines.subList(0, 200), 200);

        if (shortTermMA.compareTo(longTermMA) > 0) {
            return "Long";
        } else {
            return "Short";
        }
    }

    private BigDecimal calculateMovingAverage(List<Double> klines, int period) {
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < period; i++) {
            sum = sum.add(BigDecimal.valueOf(klines.get(i)));
        }
        return sum.divide(BigDecimal.valueOf(period), BigDecimal.ROUND_HALF_UP);
    }
}