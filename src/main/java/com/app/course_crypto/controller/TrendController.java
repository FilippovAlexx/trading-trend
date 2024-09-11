package com.app.course_crypto.controller;

import com.app.course_crypto.service.TrendAnalisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


    @AllArgsConstructor
    @RestController
    public class TrendController {
        private final TrendAnalisService trendAnalysisService;

        @GetMapping("/trend")
        public String getTrend(@RequestParam String coinName) {
            return trendAnalysisService.analyzeTrend(coinName);
        }
    }

