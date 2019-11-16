package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.InfoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private InfoConfig infoConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public static final String PREVIEW_MESSAGE = "New notification from Trello!\n";
    public static final String GOODBYE_MESSAGE = "Kind regards, \n Company support";

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("preview_message", PREVIEW_MESSAGE);
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", GOODBYE_MESSAGE);
        context.setVariable("company_details", infoConfig.getCompanyName() + "\n" + infoConfig.getCompanyMail() +
                " " + infoConfig.getCompanyPhone() + "\n" + infoConfig.getCompanyGoal());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}
