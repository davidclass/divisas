package com.tcs.spring.boot.backend.apirest.models.service;


import com.tcs.spring.boot.backend.apirest.models.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuarioService {

    public List<Usuario> findAll();

    public Usuario save(Usuario usuario);

    public void delete(Long id);

    public Usuario findById(Long id);

//    public Usuario findByUsername(String username);

}
