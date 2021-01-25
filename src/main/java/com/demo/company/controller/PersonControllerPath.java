package com.demo.company.controller;

public interface PersonControllerPath {

    String BASE_PATH = "/api/persons";
    String FIND_BY_CODE = "/{personCode}";
    String UPDATE_BY_CODE = "/{personCode}";
    String DELETE_BY_CODE = "/{personCode}";
    String UPDATE_NAME_BY_CODE = "/{personCode}/name";
}
