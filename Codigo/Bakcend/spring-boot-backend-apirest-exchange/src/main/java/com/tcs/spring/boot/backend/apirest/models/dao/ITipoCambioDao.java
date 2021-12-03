package com.tcs.spring.boot.backend.apirest.models.dao;

import com.tcs.spring.boot.backend.apirest.models.entity.TipoCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository("tipoCambioDaoJPA")
public interface ITipoCambioDao extends JpaRepository<TipoCambio, Long> {

        @Query("select t from TipoCambio t where t.codigo = ?1")
        public TipoCambio findByCodigo(String codigo);
}
