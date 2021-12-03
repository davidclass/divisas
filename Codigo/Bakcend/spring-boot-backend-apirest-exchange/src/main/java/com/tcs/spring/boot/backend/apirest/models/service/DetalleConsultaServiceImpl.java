package com.tcs.spring.boot.backend.apirest.models.service;

import com.tcs.spring.boot.backend.apirest.models.dao.IDetalleConsultaDao;
import com.tcs.spring.boot.backend.apirest.models.dao.ITipoCambioDao;
import com.tcs.spring.boot.backend.apirest.models.entity.DetalleConsulta;
import com.tcs.spring.boot.backend.apirest.models.entity.TipoCambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository("detalleConsultaServiceJPA")
public class DetalleConsultaServiceImpl  implements IDetalleConsultaService{

    /** VARIABLES PARA INYECION DE DEPENDENCIA**/
    @Autowired
    @Qualifier("detalleConsultaDaoJPA")
    private IDetalleConsultaDao detalleConsultaDao;

    /** METODOS IMPLEMENTADOS **/

    @Override
    @Transactional(readOnly = true)
    public List<DetalleConsulta> findAll() {
        return (List<DetalleConsulta>) detalleConsultaDao.findAll();
    }

    @Override
    @Transactional
    public DetalleConsulta save(DetalleConsulta detalleConsulta) {
        return detalleConsultaDao.save(detalleConsulta);
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleConsulta findById(Long id) {
        return detalleConsultaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        detalleConsultaDao.deleteById(id);
    }
}
