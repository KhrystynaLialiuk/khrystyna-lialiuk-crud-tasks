package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.InfoConfig;
import com.crud.tasks.trello.config.TrelloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private InfoConfig infoConfig;

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public static final String PREVIEW_MESSAGE_FOR_CARD_CREATION_EMAIL = "New notification from Trello!\n";
    public static final String PREVIEW_MESSAGE_FOR_DAILY_EMAIL = "Your daily notification from Trello!\n";
    public static final String GOODBYE_MESSAGE = "Kind regards, \n Company support";

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("preview_message", PREVIEW_MESSAGE_FOR_CARD_CREATION_EMAIL);
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", GOODBYE_MESSAGE);
        context.setVariable("company_details", infoConfig.getCompanyName() + "\n" + infoConfig.getCompanyMail() +
                " " + infoConfig.getCompanyPhone() + "\n" + infoConfig.getCompanyGoal());
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildOnceADayEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks in Trello Account");
        functionality.add("You can manage your tasks through CRUD application");


        Context context = new Context();
        context.setVariable("preview_message", PREVIEW_MESSAGE_FOR_DAILY_EMAIL);
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://trello.com/" + trelloConfig.getTrelloUsername() + "/boards");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", GOODBYE_MESSAGE);
        context.setVariable("company_details", infoConfig.getCompanyName() + "\n" + infoConfig.getCompanyMail() +
                " " + infoConfig.getCompanyPhone());
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
