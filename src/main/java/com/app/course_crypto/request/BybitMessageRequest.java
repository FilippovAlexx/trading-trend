package com.app.course_crypto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
//прописать ограничения примитивные для полей
    public class BybitMessageRequest {

        private String coinName;
        private long timestamp;
        private double open;
        private double close;
        private double high;
        private double low;
        private double volume;
    }

