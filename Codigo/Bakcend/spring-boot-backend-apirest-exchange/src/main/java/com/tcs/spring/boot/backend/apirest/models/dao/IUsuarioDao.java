package com.tcs.spring.boot.backend.apirest.models.dao;


import com.tcs.spring.boot.backend.apirest.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("usuarioDaoJPA")
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

//    public Usuario findByUsername(String username);

//    @Query("select u from Usuario u where u.docente.codigo = ?1")
//    public Usuario findByCodigoDocente(String codigoDocente);

}
