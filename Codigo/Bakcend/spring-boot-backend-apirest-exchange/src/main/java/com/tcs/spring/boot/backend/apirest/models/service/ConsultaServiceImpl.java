package com.tcs.spring.boot.backend.apirest.models.service;

import com.tcs.spring.boot.backend.apirest.models.dao.IAuthorityDao;
import com.tcs.spring.boot.backend.apirest.models.dao.IConsultaDao;
import com.tcs.spring.boot.backend.apirest.models.dao.IDetalleConsultaDao;
import com.tcs.spring.boot.backend.apirest.models.dao.ITipoCambioDao;
import com.tcs.spring.boot.backend.apirest.models.entity.Consulta;
import com.tcs.spring.boot.backend.apirest.models.entity.DetalleConsulta;
import com.tcs.spring.boot.backend.apirest.models.entity.TipoCambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository("consultaServiceJPA")
public class ConsultaServiceImpl implements IConsultaService {

    /**
     * VARIABLES PARA INYECION DE DEPENDENCIA
     **/
    @Autowired
    @Qualifier("consultaDaoJPA")
    private IConsultaDao consultaDao;

    @Autowired
    @Qualifier("detalleConsultaDaoJPA")
    private IDetalleConsultaDao detalleConsultaDao;

    @Autowired
    @Qualifier("tipoCambioDaoJPA")
    private ITipoCambioDao tipoCambioDao;

    /**
     * METODOS IMPLEMENTADOS
     **/
    @Override
    @Transactional(readOnly = true)
    public List<Consulta> findAll() {
        return (List<Consulta>) consultaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Consulta findById(Long id) {
        return consultaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Consulta save(Consulta consulta) {
        return consultaDao.save(consulta);
    }

    @Override
    @Transactional
    public DetalleConsulta saveConsulta(Consulta consulta) {
        DetalleConsulta detalleConsulta = null;

        TipoCambio tipoCambio_pivot = null;
        TipoCambio tipoCambio_origen = null;
        TipoCambio tipoCambio_destino = null;

        if (consulta != null) {
            detalleConsulta = new DetalleConsulta();

            detalleConsulta.setMonto(consulta.getMonto());
            if (consulta.getMoneda_origen().equalsIgnoreCase("SOL")) {
                System.out.println("sol origen");
                tipoCambio_origen = tipoCambioDao.findByCodigo(consulta.getMoneda_origen());
                tipoCambio_destino = tipoCambioDao.findByCodigo(consulta.getMoneda_destino());
                detalleConsulta.setMonto_devuelto(consulta, 1 / tipoCambio_destino.getCompra());
                detalleConsulta.setExchange_rate(consulta, 1 / tipoCambio_destino.getCompra());

            }
            else if(consulta.getMoneda_destino().equalsIgnoreCase("SOL")) {
                System.out.println("sol destino ");
                tipoCambio_origen = tipoCambioDao.findByCodigo(consulta.getMoneda_origen());
                tipoCambio_destino = tipoCambioDao.findByCodigo(consulta.getMoneda_destino());
                detalleConsulta.setMonto_devuelto(consulta, tipoCambio_origen.getCompra());
                detalleConsulta.setExchange_rate(consulta, tipoCambio_origen.getCompra());

            }else{
                System.out.println("otra divisa");
                tipoCambio_origen = tipoCambioDao.findByCodigo(consulta.getMoneda_origen());
                tipoCambio_destino = tipoCambioDao.findByCodigo(consulta.getMoneda_destino());
                detalleConsulta.setMonto_devuelto(consulta, tipoCambio_origen.getCompra(), tipoCambio_destino.getCompra());
                detalleConsulta.setExchange_rate(consulta, tipoCambio_origen.getCompra(), tipoCambio_destino.getCompra());
            }

            detalleConsulta.setMoneda_origen(consulta.getMoneda_origen());
            detalleConsulta.setMoneda_destino(consulta.getMoneda_destino());

        }

        try {
            consultaDao.save(consulta);

            detalleConsulta.setConsulta(consulta);
            detalleConsultaDao.save(detalleConsulta);
        } catch (Exception e) {
            e.getMessage();
        }


        return detalleConsulta;
    }

    @Override
    public void delete(Long id) {
        consultaDao.deleteById(id);
    }
}
