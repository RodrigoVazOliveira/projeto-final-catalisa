package br.com.zup.zupayments.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailService {

    @Autowired
    private SpringTemplateEngine springTemplateEngine;


}
