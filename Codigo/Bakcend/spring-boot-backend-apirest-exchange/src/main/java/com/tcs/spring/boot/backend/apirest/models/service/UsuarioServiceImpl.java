package com.tcs.spring.boot.backend.apirest.models.service;

import com.tcs.spring.boot.backend.apirest.models.dao.IConsultaDao;
import com.tcs.spring.boot.backend.apirest.models.dao.IUsuarioDao;
import com.tcs.spring.boot.backend.apirest.models.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository("usuarioServiceJPA")
public class UsuarioServiceImpl implements IUsuarioService{

    /** VARIABLES PARA INYECION DE DEPENDENCIA**/
    @Autowired
    @Qualifier("usuarioDaoJPA")
    private IUsuarioDao usuarioDao;

    /** METODOS IMPLEMENTADOS **/
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

}
