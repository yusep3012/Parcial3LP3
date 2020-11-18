package com.uniremington.estudiantes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="estudiante")
public class Estudiante {

    @Id
    public final Long cedula;
    public String nombre;
    public String apellido;
    public Double promedio;

    public Estudiante(Long cedula, String nombre, String apellido, Double promedio) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.promedio = promedio;
    }

    public Estudiante() {
        this.cedula = 0L;
        this.nombre = "";
        this.apellido = "";
        this.promedio = 0.0;
    }

    public Long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Double getPromedio() {
        return promedio;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "cedula=" + cedula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", promedio=" + promedio +
                '}';
    }
}
