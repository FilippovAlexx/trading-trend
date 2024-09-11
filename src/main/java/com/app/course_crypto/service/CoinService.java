package com.app.course_crypto.service;

import com.app.course_crypto.repository.CoinRepository;
import com.app.course_crypto.response.CoinResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoinService {
    CoinRepository coinRepository;

    public List<CoinResponse> getAllCoins() {
        return coinRepository.getAllCoins();
    }
}
