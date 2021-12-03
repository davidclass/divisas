package com.tcs.spring.boot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalle_consulta")
public class DetalleConsulta implements Serializable {

    /**
     * VARIABLES
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto")
    private double monto;

    @Column(name = "monto_devuelto")
    private double monto_devuelto;

    @Column(name = "moneda_origen")
    private String moneda_origen;

    @Column(name = "moneda_destino")
    private String moneda_destino;

    @Column(name = "exchange_rate")
    private double exchange_rate;


    @JsonBackReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    /**
     * CONSTRUCTOR
     **/
    public DetalleConsulta() {
    }

    /**
     * METODOS
     **/
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

    public double getMonto_devuelto() {
        return monto_devuelto;
    }

    public void setMonto_devuelto(double monto_devuelto) {
        this.monto_devuelto = monto_devuelto;
    }

    public void setMonto_devuelto(Consulta consulta, double destino) {

        double exchange_rate = 0.0;

        exchange_rate = (destino * 1000.0 / 1000.0);

        this.setExchange_rate(exchange_rate);
        this.monto_devuelto = consulta.getMonto() * exchange_rate;
    }

    public void setMonto_devuelto(Consulta consulta, double origen, double destino) {

        double exchange_rate = (double) (origen / destino);

        this.setExchange_rate(exchange_rate);
        this.monto_devuelto = (double) consulta.getMonto() * exchange_rate * 1000.0 / 1000.0;
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

    public double getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(double exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public void setExchange_rate(Consulta consulta, double origen, double destino) {

        double exchange_rate = 0;
        exchange_rate = (double) (origen) / (destino);
        this.exchange_rate = exchange_rate;
    }

    public void setExchange_rate(Consulta consulta, double destino) {

        double exchange_rate = 0;
        exchange_rate = destino;
        this.exchange_rate = exchange_rate;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
