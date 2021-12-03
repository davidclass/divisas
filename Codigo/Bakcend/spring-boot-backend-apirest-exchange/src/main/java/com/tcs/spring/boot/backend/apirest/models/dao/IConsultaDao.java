package com.tcs.spring.boot.backend.apirest.models.dao;


import com.tcs.spring.boot.backend.apirest.models.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("consultaDaoJPA")
public interface IConsultaDao extends JpaRepository<Consulta, Long> {

//    @Query("select d from Consulta d join fetch d.docente p join fetch d.itemsCursos l join fetch l.curso where d.id=?1")
//    public Consulta fetchByIdDocenteWithItemDisponibilidadWithCurso(Long id);

}
