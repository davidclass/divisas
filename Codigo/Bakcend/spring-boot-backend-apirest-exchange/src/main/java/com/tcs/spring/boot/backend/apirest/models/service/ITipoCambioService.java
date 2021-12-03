package com.tcs.spring.boot.backend.apirest.models.service;

import com.tcs.spring.boot.backend.apirest.models.entity.TipoCambio;

import java.util.List;

public interface ITipoCambioService {

    public List<TipoCambio> findAll();

    public TipoCambio save(TipoCambio tipoCambio);

    public TipoCambio findById(Long id);

    public TipoCambio findByCodigo(String codigo);

    public void delete(Long id);
}
