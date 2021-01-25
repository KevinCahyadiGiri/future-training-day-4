package com.demo.company.repository;

import com.demo.company.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

    Person findFirstByPersonCodeAndMarkForDeleteFalse(String personCode);

    void deleteByPersonCode(String personCode);

}
