package com.example.TechFellowQueryBuilder.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/get")
public interface QueryAPI {

    @GetMapping
    String getQuery() throws InterruptedException;


}
