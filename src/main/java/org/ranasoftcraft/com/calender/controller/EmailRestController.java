package org.ranasoftcraft.com.calender.controller;

import lombok.RequiredArgsConstructor;
import org.ranasoftcraft.com.calender.dto.EmailRequest;
import org.ranasoftcraft.com.calender.services.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/email/service")
@RequiredArgsConstructor
public class EmailRestController {


    private final EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        return emailService.sendEmail(emailRequest);
    }

}
