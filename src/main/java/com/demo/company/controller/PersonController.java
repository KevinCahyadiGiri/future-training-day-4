package com.demo.company.controller;

import com.demo.base.BaseResponse;
import com.demo.base.ListBaseResponse;
import com.demo.base.SingleBaseResponse;
import com.demo.company.entity.Address;
import com.demo.company.entity.Department;
import com.demo.company.entity.Employee;
import com.demo.company.entity.Person;
import com.demo.company.service.PersonService;
import com.demo.dto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = PersonControllerPath.BASE_PATH)
public class PersonController implements PersonControllerPath {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse create(@RequestParam String requestId, @RequestBody PersonCreateRequest request) throws Exception {
        this.personService.create(toPerson(request));
        return new BaseResponse(null, null, true, requestId);
    }

    @RequestMapping(value = PersonControllerPath.UPDATE_BY_CODE, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse updateByPersonCode(@RequestParam String requestId, @PathVariable String personCode, @RequestBody PersonUpdateRequest request) throws Exception {
        this.personService.update(personCode, toPerson(request));
        return new BaseResponse(null, null, true, requestId);
    }

    @RequestMapping(value = PersonControllerPath.FIND_BY_CODE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SingleBaseResponse<PersonResponse> findByPersonCode(@RequestParam String requestId, @PathVariable String personCode) throws Exception {
        Person person = this.personService.findByPersonCode(personCode);
        PersonResponse personResponse = Optional.ofNullable(person).map(this::toPersonResponse).orElse(null);
        return new SingleBaseResponse<>(null, null, true, requestId, personResponse);
    }

    @RequestMapping(value = PersonControllerPath.DELETE_BY_CODE, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteByPersonCode(@RequestParam String requestId, @RequestParam String personCode) throws Exception {
        this.personService.deleteByPersonCode(personCode);
        return new BaseResponse(null, null, true, requestId);
    }

    private PersonResponse toPersonResponse(Person request) {
        return Optional.ofNullable(request).map(e -> {
            PersonResponse personResponse = PersonResponse.builder().build();
            BeanUtils.copyProperties(e, personResponse);
            personResponse.setAdrresses(toAddressesResponse(request.getAddresses()));
            return personResponse;
        }).orElse(null);
    }

    private List<AddressResponse> toAddressesResponse(List<Address> request) {
        return Optional.ofNullable(request).map(d -> {
            List<AddressResponse> addresses = new ArrayList<AddressResponse>();
            for(Address addr : d) {
                AddressResponse address = AddressResponse.builder()
                        .addressName(addr.getAddressName())
                        .address(addr.getAddress())
                        .city(addr.getCity())
                        .build();
                addresses.add(address);
            }
            return addresses;
        }).orElse(null);
    }

    private Person toPerson(PersonUpdateRequest request) {
        return Optional.ofNullable(request).map(e -> {
            Person person = Person.builder().build();
            BeanUtils.copyProperties(e, person);
            person.setAddresses(toAddresses(request.getAddresses()));
            return person;
        }).orElse(null);
    }

    private Person toPerson(PersonCreateRequest request) {
        return Optional.ofNullable(request).map(e -> {
            Person person = Person.builder().build();
            BeanUtils.copyProperties(e, person);
            person.setAddresses(toAddresses(request.getAddresses()));
            return person;
        }).orElse(null);
    }

    private List<Address> toAddresses(List<AddressRequest> request) {
        return Optional.ofNullable(request).map(d -> {
            List<Address> addresses = new ArrayList<Address>();
            for(AddressRequest addr : d) {
                Address address = Address.builder()
                        .addressName(addr.getAddressName())
                        .address(addr.getAddress())
                        .city(addr.getCity())
                        .build();
                addresses.add(address);
            }
            return addresses;
        }).orElse(null);
    }
}
