package com.tcs.spring.boot.backend.apirest.models.service;


import com.tcs.spring.boot.backend.apirest.models.entity.Authority;

import java.util.List;

public interface IAuthorityService {

    public List<Authority> findAll();
}
