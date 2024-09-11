package com.app.course_crypto.controller;

import com.app.course_crypto.response.CoinResponse;
import com.app.course_crypto.service.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class CoinController {
    private final CoinService coinService;

    @GetMapping("/all_coins")
    public ResponseEntity<List<CoinResponse>> getAllProfiles() {
        List<CoinResponse> coins = coinService.getAllCoins();
        return ResponseEntity.ok(coins);
    }
}
