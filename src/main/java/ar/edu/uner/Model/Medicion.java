/*
 *Medicion.java
 * Version 1.0
 * 19/10/2026
 * Copyright (c) 2026 Grupo-7 Uner
 */
package ar.edu.uner.model;

import java.time.LocalDateTime;

/**
 * Representa una medición de precipitación registrada por un sensor.
 * Contiene la fecha y hora, los milímetros medidos y si fue marcada como anómala.
 *
 * @author Jmanuel (Grupo-7)
 * @version 1.0
 */
public class Medicion {
    //Identificador unico de la medicion
    private String idMedicion;

    //Fecha y hora en que se registro la medicion
    private LocalDateTime fechaHora;

    //Cantidad de precipitacion medida, en milimetros
    private double milimetros;

    //Indica si la medicion fue marcada como anomala
    private boolean anomala;

    //Sensor que registro la medicion (1).
   // private Sensor sensor;

    //Funcion que retorna el identificador de la medicion.
    public String getIdMedicion() {
        return idMedicion;
    }

    //Funcion que establece el identificador de la medicion.
    public void setIdMedicion(String idMedicion) {
        this.idMedicion = idMedicion;
    }

    //Funcion que retorna la fecha y hora de la medicion.
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    //Funcion que establece la fecha y hora de la medicion.
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    //Funcion que retorna los milimetros medidos.
    public double getMilimetros() {
        return milimetros;
    }


    //Funcion que establece los milimetros medidos.
    /*public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }*/
}
