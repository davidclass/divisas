package com.tcs.spring.boot.backend.apirest.controller;

import com.tcs.spring.boot.backend.apirest.models.entity.Consulta;
import com.tcs.spring.boot.backend.apirest.models.entity.DetalleConsulta;
import com.tcs.spring.boot.backend.apirest.models.entity.Usuario;
import com.tcs.spring.boot.backend.apirest.models.service.IConsultaService;
import com.tcs.spring.boot.backend.apirest.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api")
public class ConsultaController {

    /** VARIABLES PARA INYECION DE DEPENDENCIA **/
    @Autowired
    @Qualifier("usuarioServiceJPA")
    private IUsuarioService usuarioService;

    @Autowired
    @Qualifier("consultaServiceJPA")
    private IConsultaService consultaService;

    /** METODOS IMPLEMENTADOS **/

    @GetMapping("/consultas")
    public List<Consulta> index(){

        return consultaService.findAll();
    }

    @PostMapping("/consultas")
    public ResponseEntity<?> create(@Valid @RequestBody Consulta consulta,
                                    BindingResult result){

        Consulta consultaNueva = null;
        DetalleConsulta detalleConsultaNueva = null;

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            /*
            List<String> errors = new ArrayList<>();
            for(FieldError error: result.getFieldErrors()){
                errors.add("El campo '" + error.getField() + "' " + error.getDefaultMessage());

            }
            */
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            usuarioService.save(consulta.getUsuario());

//            consultaNueva = consultaService.save(consulta);
            detalleConsultaNueva = consultaService.saveConsulta(consulta);

        }catch (DataAccessException e){

            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La consulta ha sido realizada con éxito!");
        response.put("consulta", detalleConsultaNueva);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


}
