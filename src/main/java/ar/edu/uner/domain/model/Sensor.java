/*
* Sensor.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.domain.model;

import ar.edu.uner.domain.enums.EstadoOperativo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ListIterator;
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
    private List<Medicion> mediciones;

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

    /**
     * Devuelve el estado operativo del sensor.
     * 
     * @return estado operativo del sensor.
     */
    public EstadoOperativo getEstado() {
        return estado;
    }

    /**
     * Establece el estado operativo del sensor.
     * 
     * @param estado nuevo estado operativo del sensor.
     */
    public void setEstado(EstadoOperativo estado) {
        this.estado = estado;
    }

    /**
     * Devuelve la fecha de la ultima calibracion.
     * 
     * @return fecha de la ultima calibracion.
     */
    public LocalDate getUltimaCalibracion() {
        return ultimaCalibracion;
    }

    /**
     * Establece la fecha de la ultima calibracion.
     * 
     * @param anio año de la ultima calibracion.
     * @param mes mes de la ultima calibracion.
     * @param dia dia de la ultima calibracion.
     */
    public void setUltimaCalibracion(int anio, int mes, int dia) {
        ultimaCalibracion = LocalDate.of(anio, mes, dia);
    }

    /**
     * Registra una lectura de precipitacion.
     * 
     * @param mm milimetros registrados.
     * @param fH fecha y hora de la lectura.
     * 
     * @return la medicion creada.
     */
    public Medicion registrar(double mm, LocalDateTime fH) {
        int difAnomala = 100;
        boolean anomala = false;
        String id = "M";

        ListIterator<Medicion> listIt = mediciones.listIterator();

        if (listIt.hasPrevious() && 
            (mm - listIt.previous().getMilimetros()) > difAnomala) {
                anomala = true;
        }
        
        Medicion m = new Medicion(id, fH, mm, anomala);
        mediciones.add(m);
        return m;
    }
    

    /**
     * Verifica que la medicion este entre 0 y 500 mm.
     * @param mm valor a validar.
     * @return true si es un numero entre 0 y 500 mm,
     *         false en caso contrario.
     */
    public boolean validar(double mm) {
        return ((mm >= 0) && (mm <= 500));
    }

    /**
     * Verifica si el sensor requiere calibracion.
     * 
     * @param fechaActual fecha actual de la consulta.
     * @return true si la fecha actual es posterior por 12 meses a 
     *         la fecha de la ultima calibracion,
     *         false en caso contrario.
     */
    public boolean requiereCalibracion(LocalDate fechaActual) {
        return ultimaCalibracion.plusMonths(12).isBefore(fechaActual);
    }
}
