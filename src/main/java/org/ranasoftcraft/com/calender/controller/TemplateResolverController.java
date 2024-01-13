package org.ranasoftcraft.com.calender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateResolverController {

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/admin/event/add")
    public String createEvent() {
        return "create-event.html";
    }


}
