package com.tcs.spring.boot.backend.apirest.models.service;

import com.tcs.spring.boot.backend.apirest.models.entity.DetalleConsulta;
import com.tcs.spring.boot.backend.apirest.models.entity.Usuario;

import java.util.List;

public interface IDetalleConsultaService {

    public List<DetalleConsulta> findAll();


    public DetalleConsulta save(DetalleConsulta detalleConsulta);

    public DetalleConsulta findById(Long id);

    public void delete(Long id);
}
