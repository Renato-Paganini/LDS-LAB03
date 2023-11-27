package com.labSoftware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labSoftware.models.EmailSenderStructure;
import com.labSoftware.services.EmailSenderStructureService;

@RestController
@RequestMapping("/mail")
public class EmailSenderStructureController {

    @Autowired
    private EmailSenderStructureService emailService;

    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody EmailSenderStructure emailStructure) {

        emailService.sendMail(mail, emailStructure);

        return "E-nail enviado com sucesso!";

    }
}
