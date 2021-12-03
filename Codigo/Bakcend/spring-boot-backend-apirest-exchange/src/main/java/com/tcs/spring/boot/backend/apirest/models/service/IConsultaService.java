package com.tcs.spring.boot.backend.apirest.models.service;

import com.tcs.spring.boot.backend.apirest.models.entity.Consulta;
import com.tcs.spring.boot.backend.apirest.models.entity.DetalleConsulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IConsultaService {

    public List<Consulta> findAll();


    public Consulta findById(Long id);

    public Consulta save(Consulta consulta);

    public DetalleConsulta saveConsulta(Consulta consulta);

    public void delete(Long id);
}
