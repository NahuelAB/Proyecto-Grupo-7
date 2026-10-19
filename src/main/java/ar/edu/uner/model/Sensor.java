/*
* Sensor.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.model;

//import java.time.LocalDate;

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
    //private EstadoOperativo estado;
    /*
    * private LocalDate ultimaCalibracion; 
    * Ver si conviene crear un constructor aparte pidiendo anio/mes/dia,
    * guardarlos en un unico dato mediante un get y llamar al get en el
    * constructor Sensor.
    */

    /**
     * Crea un sensor con valores predeterminados.
     */
    public Sensor() {
        this("ID PENDIENTE",
            "MARCA PENDIENTE",
            "MODELO PENDIENTE",
            0);
    }

    /**
     * Crea un sensor con valores especificos.
     * 
     * @param idSensor identificador unico.
     * @param marca marca del sensor.
     * @param modelo modelo del sensor.
     * @param precision precision del sensor (mm).
     */
    public Sensor(String idSensor, String marca, String modelo,
                  double precision) {
        this.idSensor = idSensor;
        this.marca = marca;
        this.modelo = modelo;
        this.precision = precision;
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
}
