package com.tcs.spring.boot.backend.apirest.models.service;

import com.tcs.spring.boot.backend.apirest.models.dao.IConsultaDao;
import com.tcs.spring.boot.backend.apirest.models.dao.ITipoCambioDao;
import com.tcs.spring.boot.backend.apirest.models.entity.Consulta;
import com.tcs.spring.boot.backend.apirest.models.entity.TipoCambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository("tipoCambioServiceJPA")
public class TipoCambioServiceImpl implements ITipoCambioService{

    /** VARIABLES PARA INYECION DE DEPENDENCIA**/
    @Autowired
    @Qualifier("tipoCambioDaoJPA")
    private ITipoCambioDao tipoCambioDao;

    /** METODOS IMPLEMENTADOS **/

    @Override
    @Transactional(readOnly = true)
    public List<TipoCambio> findAll() {
        return (List<TipoCambio>) tipoCambioDao.findAll();
    }

    @Override
    @Transactional
    public TipoCambio save(TipoCambio tipoCambio) {
        return tipoCambioDao.save(tipoCambio);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoCambio findById(Long id) {
        return tipoCambioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public TipoCambio findByCodigo(String codigo) {
        return tipoCambioDao.findByCodigo(codigo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tipoCambioDao.deleteById(id);
    }
}
