/*
 *ResponsableTecnico.java
 * Version 1.0
 * 19/10/2026
 * Copyright (c) 2026 Grupo-7 Uner
 */
package ar.edu.uner.model;

//import java.util.ArrayList;
//import java.util.List;

/**
 * Representa a un responsable técnico del sistema.
 * Extiende de Persona y contiene su especialidad y número de licencia.
 *
 * @author Jmanuel (Grupo-7)
 * @version 1.0
 */
public class ResponsableTecnico extends Persona {
    //Especialidad del responsable tecnico
    private String especialidad;

    //Numero de licencia del responsable tecnico
    private String numeroLicencia;

    //Estaciones meteorologicas de las que esta a cargo el responsable tecnico (0..*).
    //private List<EstacionMeteorologica> estaciones = new ArrayList<>();

    //Equipo que integra el responsable tecnico (0..1).
    //private EquipoRespuesta equipoRespuesta;

    //Mantenimientos que realiza el responsable tecnico (0..*).
    //private List<Mantenimiento> mantenimientos = new ArrayList<>();

    //Funcion que retorna las estaciones de las que esta a cargo el responsable tecnico.
    public List<EstacionMeteorologica> getEstaciones() {
        return estaciones;
    }

    //Funcion que agrega una estacion a cargo del responsable tecnico.
    public void agregarEstacion(EstacionMeteorologica estacion) {
        this.estaciones.add(estacion);
    }

    //Funcion que quita una estacion a cargo del responsable tecnico.
    public void quitarEstacion(EstacionMeteorologica estacion) {
        this.estaciones.remove(estacion);
    }

    //Funcion que retorna el equipo que integra el responsable tecnico.
    public EquipoRespuesta getEquipoRespuesta() {
        return equipoRespuesta;
    }

    //Funcion que establece el equipo que integra el responsable tecnico.
    public void setEquipoRespuesta(EquipoRespuesta equipoRespuesta) {
        this.equipoRespuesta = equipoRespuesta;
    }

    //Funcion que retorna los mantenimientos que realiza el responsable tecnico.
    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    //Funcion que agrega un mantenimiento realizado por el responsable tecnico.
    public void agregarMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimientos.add(mantenimiento);
    }

    //Funcion que retorna la especialidad del responsable tecnico.
    public String getEspecialidad() {
        return especialidad;
    }

    //Funcion que establece la especialidad del responsable tecnico.
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    //Funcion que retorna el numero de licencia del responsable tecnico.
    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    //Funcion que establece el numero de licencia del responsable tecnico.
    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

}
