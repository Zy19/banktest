package com.bmo.ianlipliavyi.global;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class BmoConstants {

    public static final String URL_USER = "/user/v1/";

    public static RestTemplate createRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restTemplate;
    }
}
