package com.ExamenFinal.DoctorStrangeApi.Entidades;


import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;

    @Column(name = "hora")
    @NotNull
    private Integer hora;

    @Column(name = "fecha")
    @NotNull
    private final Date fecha = new Date();

    @Column(name = "paciente")
    @NotNull
    private String paciente;

    @Column(name = "estado")
    @NotNull
    private String estado;

    @Column(name = "observaciones")
    @NotNull
    private String observaciones;

    public Cita(Long id, Integer hora, String paciente, String estado, String observaciones)
    {
        this.id = id;
        this.hora = hora;
        this.paciente = paciente;
        this.estado = estado;
        this.observaciones = observaciones;
    }
    public Cita(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
