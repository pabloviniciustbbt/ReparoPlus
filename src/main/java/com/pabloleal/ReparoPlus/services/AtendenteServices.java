package com.pabloleal.ReparoPlus.services;

import com.pabloleal.ReparoPlus.repositories.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtendenteServices {

    @Autowired
    private AtendenteRepository atendenteRepository;
}
