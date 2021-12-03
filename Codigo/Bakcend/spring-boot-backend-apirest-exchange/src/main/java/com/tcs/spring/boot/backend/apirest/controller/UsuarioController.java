package com.tcs.spring.boot.backend.apirest.controller;

import com.tcs.spring.boot.backend.apirest.models.entity.Usuario;
import com.tcs.spring.boot.backend.apirest.models.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api")
public class UsuarioController {

    /** VARIABLES PARA INYECION DE DEPENDENCIA **/
    @Autowired
    @Qualifier("usuarioServiceJPA")
    private IUsuarioService usuarioService;

    /** METODOS IMPLEMENTADOS **/

    @GetMapping("/usuarios")
    public List<Usuario> index(){

        return usuarioService.findAll();
    }


}
