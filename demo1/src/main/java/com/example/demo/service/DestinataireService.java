package com.example.demo.service;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.entity.Destinataire;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.DestinataireMapper;
import com.example.demo.repository.DestinataireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DestinataireService {

    private static final Logger logger = LoggerFactory.getLogger(DestinataireService.class);

    @Autowired
    private DestinataireRepository repository;

    @Autowired
    private DestinataireMapper mapper;


}