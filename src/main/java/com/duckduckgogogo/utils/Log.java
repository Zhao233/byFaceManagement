package com.duckduckgogogo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

public class Log {

    @Bean
    public Logger getLoger(){
        return LoggerFactory.getLogger(this.getClass());
    }
}

