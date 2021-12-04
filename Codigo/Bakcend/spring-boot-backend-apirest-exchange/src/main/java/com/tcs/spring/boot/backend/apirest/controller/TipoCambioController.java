package com.tcs.spring.boot.backend.apirest.controller;

import com.tcs.spring.boot.backend.apirest.models.entity.Consulta;
import com.tcs.spring.boot.backend.apirest.models.entity.TipoCambio;
import com.tcs.spring.boot.backend.apirest.models.entity.Usuario;
import com.tcs.spring.boot.backend.apirest.models.service.ITipoCambioService;
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
public class TipoCambioController {

    /** VARIABLES PARA INYECION DE DEPENDENCIA **/
    @Autowired
    @Qualifier("tipoCambioServiceJPA")
    private ITipoCambioService tipoCambioService;

    /** METODOS IMPLEMENTADOS **/

    @GetMapping("/tipo-cambio")
    public List<TipoCambio> index(){

        return tipoCambioService.findAll();
    }

    @GetMapping("/tipo-cambio/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        TipoCambio tipoCambio = null;
        Map<String, Object> response = new HashMap<>();

        try {
            tipoCambio = tipoCambioService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        if (tipoCambio == null){
            response.put("mensaje", "El tipo de cambio ID: ".concat(id.toString().concat(" no existe en la base datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TipoCambio>(tipoCambio, HttpStatus.OK);
    }

    @PostMapping("/tipo-cambio")
    public ResponseEntity<?> create(@Valid @RequestBody TipoCambio tipoCambio,
                                    BindingResult result){

        TipoCambio TipoCambioNuevo = null;
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
            TipoCambioNuevo = tipoCambioService.save(tipoCambio);
        }catch (DataAccessException e){

            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Tipo de Cambio ha sido registrada con éxito!");
        response.put("tipoCambio", TipoCambioNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/tipo-cambio/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TipoCambio tipoCambio,
                                    BindingResult result,
                                    @PathVariable Long id){

        TipoCambio tipoCambioActual = tipoCambioService.findById(id);
        TipoCambio tipoCambioUpdated = null;

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

        if (tipoCambioActual == null){
            response.put("mensaje", "Error: No se pudo editar, el tipo cambio  ".concat(id.toString().concat(" no existe en la base datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            tipoCambioActual.setDivisa(tipoCambio.getDivisa());
            tipoCambioActual.setCodigo(tipoCambio.getCodigo());
            tipoCambioActual.setCompra(tipoCambio.getCompra());
            tipoCambioActual.setVenta(tipoCambio.getVenta());

            tipoCambioUpdated = tipoCambioService.save(tipoCambioActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar el tipo de cambio en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El tipo de cambio ha sido actualizado con éxito!");
        response.put("tipoCambio", tipoCambioUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    //Eliminar un cursos por su ID
    @DeleteMapping("/tipo-cambio/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){

        Map<String, Object> response = new HashMap<>();

        try {
            tipoCambioService.delete(id);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el ipo de cambio en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()) );
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El tipo de cambio ha sido eliminado con éxito!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);


    }
}
