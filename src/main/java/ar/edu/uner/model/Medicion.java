/*
 * Medicion.java
 *
 * Version 1.0
 *
 * 19/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.model;

import java.time.LocalDateTime;

/**
 * Representa una medición de precipitación registrada por un sensor.
 * Contiene la fecha y hora, los milímetros medidos y si fue marcada como
 * anómala.
 *
 * @author Jmanuel (Grupo-7)
 * @version 1.0
 */
public class Medicion {
    private String idMedicion;
    private LocalDateTime fechaHora;
    private double milimetros;
    private boolean anomala;
    private Sensor sensor;

    //Constructores
    /**
     * Crea una medición con valores predeterminados.
     */
    public Medicion() {
        this(
                "ID PENDIENTE",
                LocalDateTime.now(),
                0.0,
                false);
    }

    /**
     * Crea una instancia de Medicion.
     * @param idMedicion identificador de la medición
     * @param fechaHora fecha y hora en que se registró la medición
     * @param milimetros cantidad de precipitación medida, en milímetros
     * @param anomala indica si la medición fue marcada como anómala
     */
    public Medicion(String idMedicion, LocalDateTime fechaHora, double milimetros, boolean anomala) {
        this.idMedicion = idMedicion;
        this.fechaHora = fechaHora;
        this.milimetros = milimetros;
        this.anomala = anomala;
    }

    /**
     * Devuelve el identificador de la medición.
     * @return idMedicion
     */
    public String getIdMedicion() {
        return idMedicion;
    }

    /**
     * Asigna un identificador a la medición.
     * @param idMedicion nuevo identificador de la medición
     */
    public void setIdMedicion(String idMedicion) {
        this.idMedicion = idMedicion;
    }

    /**
     * Devuelve la fecha y hora en que se registró la medición.
     * @return fechaHora
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Asigna la fecha y hora a la medición.
     * @param fechaHora nueva fecha y hora de la medición
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Devuelve los milímetros de precipitación medidos.
     * @return milimetros
     */
    public double getMilimetros() {
        return milimetros;
    }

    /**
     * Asigna los milímetros de precipitación medidos.
     * @param milimetros nueva cantidad de milímetros
     */
    public void setMilimetros(double milimetros) {
        this.milimetros = milimetros;
    }

    /**
     * Indica si la medición fue marcada como anómala.
     * @return true si es anómala, false en caso contrario
     */
    public boolean isAnomala() {
        return anomala;
    }

    /**
     * Asigna si la medición es anómala.
     * @param anomala true si la medición es anómala
     */
    public void setAnomala(boolean anomala) {
        this.anomala = anomala;
    }

    /**
     * Devuelve el sensor que registró la medición.
     * @return sensor
     */
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * Asigna el sensor que registró la medición.
     * @param sensor sensor que registró la medición
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
