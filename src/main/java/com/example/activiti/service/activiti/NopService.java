package com.example.activiti.service.activiti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NopService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void someMethod() {
        logger.info("There is nothing happening here...");
    }
}
