package com.tcs.spring.boot.backend.apirest.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tipo_cambio")
public class TipoCambio implements Serializable {

    /** VARIABLES **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "divisa")
    private String divisa;

    @NotEmpty
    @Column(name = "codigo")
    private String codigo;

    @NotEmpty
    @Column(name = "compra")
    private double compra;

    @NotEmpty
    @Column(name = "venta")
    private double venta;

    /** CONSTRUCTOR **/
    public TipoCambio() {
    }

    /** METODOS **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCompra() {
        return compra;
    }

    public void setCompra(double compra) {
        this.compra = compra;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }
}
