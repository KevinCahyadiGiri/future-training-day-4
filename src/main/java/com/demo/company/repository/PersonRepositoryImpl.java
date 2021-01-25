package com.demo.company.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class PersonRepositoryImpl implements PersonCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

}
