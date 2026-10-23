/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Mantenimiento.java
 *
 * Version 1.0
 *
 * 15/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.model;

import ar.edu.uner.model.enums.TipoMantenimiento;
import java.time.LocalDate;

/**
 *clase Mantenimiento que representa un mantenimiento realizado.
 * @author Maricela
 */
public class Mantenimiento {
    private String idMantenimiento;
    private LocalDate fecha;
    private TipoMantenimiento tipo;
    private String descripcion;
    private ResponsableTecnico responsableTecnico;
    private Sensor sensor;
    
    
    //Constructores
    /**
    * Crea un mantenimiento con valores predeterminados.
    */
    public Mantenimiento() {
        this(
        "ID PENDIENTE",
        LocalDate.now(),
        null,
        "DESCRIPCION PENDIENTE",
        null,
        null
               
        );
}
    /**
     * crea una instancia de Mantenimiento
     * @param idMantenimiento identificador del mantenimiento
     * @param fecha fecha en que se realizo el mantenimiento 
     * @param tipo tipo de mantenimiento realizado
     * @param descripcion descripción del mantenimiento
     * @param responsableTecnico
     * @param sensor
     */
     public Mantenimiento(String idMantenimiento, LocalDate fecha, TipoMantenimiento tipo, String descripcion,ResponsableTecnico responsableTecnico,Sensor sensor) {
        this.idMantenimiento = idMantenimiento;
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.responsableTecnico=responsableTecnico;
        this.sensor=sensor;
    }
    
    /**
     * Devuelve la identidicación del mantenimiento
     * @return idMantenimiento
     */
    public String getIdMantenimiento() {
        return idMantenimiento;
    }
    /**
     * Asigna una identificaión al mantenimiento
     * @param idMantenimiento 
     */

    public void setIdMantenimiento(String idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }
    /**
     * Devuelve la fecha en que se realizó el mantenimiento
     * @return fecha
     */

    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Asigna una fecha al mantenimiento
     * @param fecha 
     */

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    /**
     * Devuelve el tipo de mantenimiento realizado
     * @return tipo 
     */

    public TipoMantenimiento getTipo() {
        return tipo;
    }
    
    /**
     * Asigna un tipo al mantenimiento
     * @param tipo 
     */

    public void setTipo(TipoMantenimiento tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Devuelve una descripción del mantenimiento realizado
     * @return descripcion
     */

    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Asigna una descripción al mantenimiento realizado
     * @param descripcion 
     */

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ResponsableTecnico getResponsableTecnico() {
        return responsableTecnico;
    }

    public void setResponsableTecnico(ResponsableTecnico responsableTecnico) {
        this.responsableTecnico = responsableTecnico;
    }
    
    
    
    /**
     * Devuelve el sensor al que esta asociado el mantenimiento
     * @return  el sensor asociado, o null si el mantenimiento no aplica a un sensor
     */
    public Sensor getSensor() {
        return sensor;
    }
    
    
    /**
     * Asocia el sensor a un mantenimiento
     * @param sensor al que se aplica el mantenimiento
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    
    
    /**
     * Devuelve una representación en forma de cadena de texto del objeto
     * Mantenimiento.
     * @return cadena de texto con la información del mantenimiento
     */

    @Override
    public String toString() {
        return "Mantenimiento{" + "idMantenimiento=" + idMantenimiento + ", fecha=" + fecha + ", tipo=" + tipo + ", descripcion=" + descripcion + ", responsable=" + (responsableTecnico != null ? responsableTecnico.getNumeroLicencia() : "sin asignar")+ ",sensor="+(sensor != null ? sensor.getIdSensor() : "sin sensor")+'}';
    }
    
    
    
    
    
     
}

