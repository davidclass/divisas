package com.tcs.spring.boot.backend.apirest.controller;

import com.tcs.spring.boot.backend.apirest.models.dao.IDetalleConsultaDao;
import com.tcs.spring.boot.backend.apirest.models.entity.Consulta;
import com.tcs.spring.boot.backend.apirest.models.entity.DetalleConsulta;
import com.tcs.spring.boot.backend.apirest.models.entity.TipoCambio;
import com.tcs.spring.boot.backend.apirest.models.service.IDetalleConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api")
public class ConsultaDetalleController {

    /** VARIABLES PARA INYECION DE DEPENDENCIA **/
    @Autowired
    @Qualifier("detalleConsultaServiceJPA")
    private IDetalleConsultaService detalleConsultaService;

    @GetMapping("/detalle-consulta")
    public List<DetalleConsulta> index(){

        return detalleConsultaService.findAll();
    }

    @GetMapping("/detalle-consulta/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        DetalleConsulta detalleConsulta = null;
        Map<String, Object> response = new HashMap<>();

        try {
            detalleConsulta = detalleConsultaService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        if (detalleConsulta == null){
            response.put("mensaje", "El detalle de la consulta ID: ".concat(id.toString().concat(" no existe en la base datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<DetalleConsulta>(detalleConsulta, HttpStatus.OK);
    }
}
