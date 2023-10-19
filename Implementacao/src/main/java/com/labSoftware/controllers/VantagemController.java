package com.labSoftware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labSoftware.services.VantagemService;

@RestController
@RequestMapping("/vantagem")
public class VantagemController {

    @Autowired
    private VantagemService vantagemService;

}
