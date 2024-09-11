package com.app.course_crypto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {
    private String symbol;
    private String category;
    private List<String[]> list;
}
