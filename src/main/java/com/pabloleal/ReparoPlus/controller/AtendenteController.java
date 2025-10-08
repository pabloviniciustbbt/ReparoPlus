package com.pabloleal.ReparoPlus.controller;

import com.pabloleal.ReparoPlus.services.AtendenteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atendente")
public class AtendenteController {

    @Autowired
    private AtendenteServices atendenteServices;
}
