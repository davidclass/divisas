package com.tcs.spring.boot.backend.apirest.models.service;

import com.tcs.spring.boot.backend.apirest.models.dao.IAuthorityDao;
import com.tcs.spring.boot.backend.apirest.models.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository("authorityServiceJPA")
public class AuthorityServiceImpl implements IAuthorityService{

    /** VARIABLES PARA INYECION DE DEPENDENCIA**/
    @Autowired
    @Qualifier("authorityDaoJPA")
    private IAuthorityDao authorityDao;

    /** METODOS IMPLEMENTADOS **/
    @Override
    public List<Authority> findAll() {
        return (List<Authority>)authorityDao.findAll();
    }
}
