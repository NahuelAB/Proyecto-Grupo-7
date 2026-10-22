/*
* Sensor.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.model;

import ar.edu.uner.model.enums.EstadoOperativo;
import java.time.LocalDate;

/**
 * Sensor pluviómetro que mide la precipitación en milímetros. Se instala en
 * una estación meteorológica y registra lecturas que se usan para calcular
 * acumulados y emitir alertas.
 * 
 * @author Guillermina
 */
public class Sensor {
    private String idSensor;
    private String marca;
    private String modelo;
    private double precision;
    private EstadoOperativo estado;
    private LocalDate ultimaCalibracion;
    private EstacionMeteorologica estacion;

    /**
     * Crea un sensor con valores predeterminados.
     */
    public Sensor() {
        this("ID PENDIENTE",
            "MARCA PENDIENTE",
            "MODELO PENDIENTE",
            0,
            null,
            0, 0, 0);
    }

    /**
     * Crea un sensor con valores especificos.
     * 
     * @param idSensor identificador unico.
     * @param marca marca del sensor.
     * @param modelo modelo del sensor.
     * @param precision precision del sensor (mm).
     * @param estado estado operativo inicial.
     * @param anio año de la ultima calibracion.
     * @param mes mes de la ultima calibracion.
     * @param dia dia de la ultima calibracion.
     */
    public Sensor(String idSensor, String marca, String modelo,
                  double precision, EstadoOperativo estado, 
                  int anio, int mes, int dia) {
        this.idSensor = idSensor;
        this.marca = marca;
        this.modelo = modelo;
        this.precision = precision;
        ultimaCalibracion = LocalDate.of(anio, mes, dia);
        
    }
    
    /**
     * Devuelve el ID del sensor.
     * 
     * @return ID sensor.
     */
    public String getIdSensor() {
        return idSensor;
    }

    /**
     * Establece el ID del sensor.
     * 
     * @param idSensor nuevo ID.
     */
    public void setIdSensor(String idSensor) {
        this.idSensor = idSensor;
    }

    /**
     * Devuelve la marca del sensor.
     * 
     * @return marca del sensor.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del sensor.
     * 
     * @param marca nueva marca del sensor.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Devuelve el modelo del sensor.
     * 
     * @return modelo del sensor.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Establece el modelo del sensor.
     * 
     * @param modelo nuevo modelo del sensor.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Devuelve la precision del sensor en mm.
     * 
     * @return precision en mm.
     */
    public double getPrecision() {
        return precision;
    }

    /**
     * Establece la precision del sensor en mm.
     * 
     * @param precision nueva precision del sensor.
     */
    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public EstadoOperativo getEstado() {
        return estado;
    }

    public void setEstado(EstadoOperativo estado) {
        this.estado = estado;
    }

    public LocalDate getUltimaCalibracion() {
        return ultimaCalibracion;
    }

    public void setUltimaCalibracion(int anio, int mes, int dia) {
        ultimaCalibracion = LocalDate.of(anio, mes, dia);
    }

    /**
     * Devuelve la estacion a la que esta anidado el sensor.
     * 
     * @return estacion anidada al sensor.
     */
    public EstacionMeteorologica getEstacion() {
        return estacion;
    }

    /**
     * Establece la estacion a la que estara anidado el sensor.
     * 
     * @param estacion nueva estacion anidada al sensor.
     */
    public void setEstacion(EstacionMeteorologica estacion) {
        this.estacion = estacion;
    } //VER SI SENSOR CONOCE A ESTACIONMETEOROLOGICA
}
