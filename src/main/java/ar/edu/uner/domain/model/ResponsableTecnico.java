/*
 * ResponsableTecnico.java
 *
 * Version 1.0
 *
 * 19/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */


package ar.edu.uner.domain.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Representa a un responsable técnico del sistema.
 * Extiende de Persona y contiene su especialidad, número de licencia,
 * las estaciones a su cargo y los mantenimientos que realiza.
 *
 * @author Jmanuel (Grupo-7)
 */
public class ResponsableTecnico extends Persona {
    private String especialidad;
    private String numeroLicencia;
    private List<EstacionMeteorologica> estaciones;
    private List<Mantenimiento> mantenimientos;

    /**
     * Crea un responsable técnico con valores predeterminados.
     */
    public ResponsableTecnico() {
        this("DNI PENDIENTE",
                "NOMBRE PENDIENTE",
                "APELLIDO PENDIENTE",
                "CORREO PENDIENTE",
                "CELULAR PENDIENTE",
                "ESPECIALIDAD PENDIENTE",
                "LICENCIA PENDIENTE",
                new ArrayList<>(),
                new ArrayList<>());
    }

    /**
     * Crea un responsable técnico con valores específicos.
     *
     * @param dni DNI del responsable técnico.
     * @param nombre Nombre del responsable técnico.
     * @param apellido Apellido del responsable técnico.
     * @param correoElectronico Correo electrónico del responsable técnico.
     * @param celular Celular del responsable técnico.
     * @param especialidad Especialidad del responsable técnico.
     * @param numeroLicencia Número de licencia del responsable técnico.
     * @param estaciones Estaciones meteorológicas a cargo del responsable técnico.
     * @param mantenimientos Mantenimientos que realiza el responsable técnico.
     */
    public ResponsableTecnico(String dni, String nombre, String apellido,
                              String correoElectronico, String celular,
                              String especialidad, String numeroLicencia,
                              List<EstacionMeteorologica> estaciones,
                              List<Mantenimiento> mantenimientos) {
        super(dni, nombre, apellido, correoElectronico, celular);
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
        this.estaciones = estaciones;
        this.mantenimientos = mantenimientos;
    }

    /**
     * Obtiene las estaciones meteorológicas a cargo del responsable técnico.
     *
     * @return Lista de estaciones a cargo del responsable técnico.
     */
    public List<EstacionMeteorologica> getEstaciones() {
        return estaciones;
    }

    /**
     * Establece las estaciones meteorológicas a cargo del responsable técnico.
     *
     * @param estaciones Lista de estaciones a asignar.
     */
    public void setEstaciones(List<EstacionMeteorologica> estaciones) {
        this.estaciones = estaciones;
    }

    /**
     * Agrega una estación meteorológica a cargo del responsable técnico.
     *
     * @param estacion Estación a agregar.
     * @return true si la estacion fue agregada.
     */
    public boolean agregarEstacion(EstacionMeteorologica estacion) {
        return this.estaciones.add(estacion);
    }

    /**
     * Quita una estación meteorológica a cargo del responsable técnico.
     *
     * @param estacion Estación a quitar.
     * @return true si la estacion fue quitada.
     */
    public boolean quitarEstacion(EstacionMeteorologica estacion) {
        return this.estaciones.remove(estacion);
    }

    /**
     * Obtiene los mantenimientos que realiza el responsable técnico.
     *
     * @return Lista de mantenimientos del responsable técnico.
     */
    public List<Mantenimiento> getMantenimientos() {
        return mantenimientos;
    }

    /**
     * Establece los mantenimientos que realiza el responsable técnico.
     *
     * @param mantenimientos Lista de mantenimientos a asignar.
     */
    public void setMantenimientos(List<Mantenimiento> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }

    /**
     * Agrega un mantenimiento realizado por el responsable técnico.
     *
     * @param mantenimiento Mantenimiento a agregar.
     * @return true si el mantenimiento fue agregado.
     */
    public boolean agregarMantenimiento(Mantenimiento mantenimiento) {
        return this.mantenimientos.add(mantenimiento);
    }

    /**
     * Quita un mantenimiento realizado por el responsable técnico.
     *
     * @param mantenimiento Mantenimiento a quitar.
     * @return true si el mantenimiento fue quitado.
     */
    public boolean quitarMantenimiento(Mantenimiento mantenimiento) {
        return this.mantenimientos.remove(mantenimiento);
    }

    /**
     * Obtiene la especialidad del responsable técnico.
     *
     * @return Especialidad del responsable técnico.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad del responsable técnico.
     *
     * @param especialidad Especialidad a asignar.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Obtiene el número de licencia del responsable técnico.
     *
     * @return Número de licencia del responsable técnico.
     */
    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    /**
     * Establece el número de licencia del responsable técnico.
     *
     * @param numeroLicencia Número de licencia a asignar.
     */
    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }
}
