package com.tcs.spring.boot.backend.apirest.models.dao;

import com.tcs.spring.boot.backend.apirest.models.entity.DetalleConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("detalleConsultaDaoJPA")
public interface IDetalleConsultaDao extends JpaRepository<DetalleConsulta, Long> {
}
