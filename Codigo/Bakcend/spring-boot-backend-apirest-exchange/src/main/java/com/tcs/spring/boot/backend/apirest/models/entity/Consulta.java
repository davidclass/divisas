package com.tcs.spring.boot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

    /** VARIABLES **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto")
    private double monto;

    @Column(name = "moneda_origen")
    private String moneda_origen;

    @Column(name = "moneda_destino")
    private String moneda_destino;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties({"consultas", "hibernateLazyInitializer", "handler"})
    private Usuario usuario;

        @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "consulta_id")
    private List<DetalleConsulta> detalleConsultas;

    /** CONSTRUCTOR **/
    public Consulta() {

    }

    /** METODOS **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getMoneda_origen() {
        return moneda_origen;
    }

    public void setMoneda_origen(String moneda_origen) {
        this.moneda_origen = moneda_origen;
    }

    public String getMoneda_destino() {
        return moneda_destino;
    }

    public void setMoneda_destino(String moneda_destino) {
        this.moneda_destino = moneda_destino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleConsulta> getDetalleConsultas() {
        return detalleConsultas;
    }

    public void setDetalleConsultas(List<DetalleConsulta> detalleConsultas) {
        this.detalleConsultas = detalleConsultas;
    }
}
