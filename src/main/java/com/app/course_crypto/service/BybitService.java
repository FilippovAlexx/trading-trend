package com.app.course_crypto.service;

import com.app.course_crypto.entity.Kline;
import com.app.course_crypto.repository.CoinRepository;
import com.app.course_crypto.repository.KlineRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class BybitService {
    private String bybitApiUrl = "https://api.bybit.com";

    private final RestTemplate restTemplate;
    private final KlineRepository klineRepository;
    private final CoinRepository coinRepository;

    public BybitService(RestTemplate restTemplate, KlineRepository klineRepository, CoinRepository coinRepository) {
        this.restTemplate = restTemplate;
        this.klineRepository = klineRepository;
        this.coinRepository = coinRepository;
    }

    public void fetchInitialCandles() {
        try {
            fetchAndSaveCoins();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//2160000
    @Scheduled(fixedRate = 1500) // 6 hours in milliseconds
    public void updateCandles() {
        fetchAndSaveCoins();
    }

    private void fetchAndSaveCoins() {
        try {
            long end = System.currentTimeMillis();
            long start = end - 17280000000L;
            List<String> name_coins = List.of("BTCUSDT", "ETHUSDT", "XRPUSDT", "ONGUSDT", "BNBUSDT", "DOGEUSDT", "OPUSDT", "SOLUSDT");
            for (String coinName : name_coins) {
                String url = String.format("%s/v5/market/mark-price-kline?category=linear&symbol=%s&interval=240&start=%d&end=%d&limit=%d", bybitApiUrl, coinName, start, end, 200);
                fetchAndParseJson(url);
                //Thread.sleep(2000);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fetchAndParseJson(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            List<Kline> klines = parseJson(responseBody);
            //klineRepository.clearAll();
            klineRepository.saveAll(klines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Kline> parseJson(String responseBody) {
        List<Kline> klines = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(responseBody);

            if (jsonObject.getInt("retCode") == 0) {
                JSONObject result = jsonObject.getJSONObject("result");
                Long coin_id = coinRepository.findIdByName(result.getString("symbol"));
                JSONArray json_klines = result.getJSONArray("list");
                System.out.println(json_klines.length());
                for (int i = 0; i < json_klines.length(); i++) {
                    JSONArray innerArray = json_klines.getJSONArray(i);
                    Kline kline = Kline.fromJsonArray(coin_id, innerArray);
                    klines.add(kline);
                }
                klineRepository.deleteByCoinId(coin_id);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return klines;
    }







   /* private void fetchAndSaveCandles(int limit) {
        List<String> name_coins = List.of("BTCUSDT", "ETHUSDT", "XRPUSDT");

        for (String coinName : name_coins) {
            String url = String.format("%s/v5/market/mark-price-kline?category=linear&symbol=%s&interval=15&start=1717310000000&end=1717326000000&limit=%d", bybitApiUrl, coinName, limit);
            //String url = String.format("%s/v5/market/mark-price-kline?symbol=%s&interval=15&limit=%d", bybitApiUrl, coinName, limit);
            BybitResponse response = restTemplate.getForObject(url, BybitResponse.class);
            System.out.println(response);

            if (response != null && response.getRetCode() == 0) {
                Coin coin = coinRepository.findByName(coinName);
                List<Kline> klines = response.getResult().getList().stream()
                        .map(data -> new Kline(
                                3L,
                                Instant.ofEpochMilli(Long.parseLong(data[0])),
                                new BigDecimal(data[1]),
                                new BigDecimal(data[2]),
                                new BigDecimal(data[3]),
                                new BigDecimal(data[4])
                        ))
                        .collect(Collectors.toList());
                klineRepository.saveAll(klines);
            }
        }
    }*/
}