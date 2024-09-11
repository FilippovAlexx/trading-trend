package com.app.course_crypto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BybitResponse {
    private int retCode;
    private String retMsg;
    private ResultResponse result;
    private long time;
}
