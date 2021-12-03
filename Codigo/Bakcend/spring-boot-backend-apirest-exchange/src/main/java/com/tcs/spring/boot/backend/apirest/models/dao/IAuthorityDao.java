package com.tcs.spring.boot.backend.apirest.models.dao;


import com.tcs.spring.boot.backend.apirest.models.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("authorityDaoJPA")
public interface IAuthorityDao extends JpaRepository<Authority, Long> {
}
