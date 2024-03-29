package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class InfoConfig {
    @Value("${info.company.name}")
    private String companyName;

    @Value("${info.company.email}")
    private String companyMail;

    @Value("${info.company.phone}")
    private String companyPhone;

    @Value("${info.company.goal}")
    private String companyGoal;
}
