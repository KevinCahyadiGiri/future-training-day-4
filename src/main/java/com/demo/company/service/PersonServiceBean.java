package com.demo.company.service;

import com.demo.company.entity.Employee;
import com.demo.company.entity.Person;
import com.demo.company.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceBean implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceBean.class);

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void create(Person person) throws Exception {
        personRepository.save(person);
    }

    @Override
    public void update(String personCode, Person person) throws Exception {
        Person oldPerson = personRepository.findFirstByPersonCodeAndMarkForDeleteFalse(personCode);
        oldPerson.setPersonCode(person.getPersonCode());
        oldPerson.setPersonName(person.getPersonName());
        oldPerson.setAddresses(person.getAddresses());
        personRepository.save(oldPerson);
    }

//    @Override
//    public void updateName(Integer code, Person person) throws Exception {
//
//    }

//    @Override
//    public Page<Employee> find(Pageable pageable) throws Exception {
//        return null;
//    }

    @Override
    public Person findByPersonCode(String personCode) throws Exception {
        return personRepository.findFirstByPersonCodeAndMarkForDeleteFalse(personCode);
    }

    @Override
    public void deleteByPersonCode(String personCode) throws Exception {
        this.personRepository.deleteByPersonCode(personCode);
    }
}
