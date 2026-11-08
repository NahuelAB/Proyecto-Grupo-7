/*
 * GestionPersonal.java
 *
 * Version 1.0
 *
 * 03/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.service;

import ar.edu.uner.domain.enums.TipoMantenimiento;
import ar.edu.uner.domain.model.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Gestiona el personal técnico del sistema. Mantiene las zonas
 * geográficas y los equipos de respuesta registrados, y permite consultar
 * qué responsables técnicos cumplen funciones de supervisión,
 * mantenimiento y calibración.
 *
 * @author Nahuel
 */

public class GestionPersonal {
    private GestionInfraestructura infraestructura;
    private List<EquipoRespuesta> equiposRespuesta;

    /**
     * Crea una gestión de personal con atributo de GestionInfraestructura
     * para utilizar su lista de ZonaGeográficas e
     * inicialización de lista para equipos registrados.
     */
    public GestionPersonal(GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
        this.equiposRespuesta = new ArrayList<>();
    }

    /**
     * Registra un equipo de respuesta si no es null y todavía no está
     * registrado.
     *
     * @param equipo Equipo de respuesta a registrar.
     * @return true si el equipo se registró, false si es null o ya estaba.
     */
    public boolean agregarEquipo(EquipoRespuesta equipo) {
        if (equipo != null && !equiposRespuesta.contains(equipo)) {
            return equiposRespuesta.add(equipo);
        }
        return false;
    }

    /**
     * Metodo auxiliar que reúne las estaciones meteorológicas
     * de todas las zonas registradas.
     *
     * @return Lista con todas las estaciones de las zonas registradas.
     */
    public List<EstacionMeteorologica> getEstaciones() {
        List<EstacionMeteorologica> estaciones = new ArrayList<>();

        for (ZonaGeografica zona : infraestructura.getZonas()) {

            for (EstacionMeteorologica estacion :
                    zona.getEstacionesMeteorologicas()) {

                if (!estaciones.contains(estacion)) {
                    estaciones.add(estacion);
                }
            }
        }
        return estaciones;
    }

    /**
     * Obtiene los responsables técnicos que están a cargo de al menos
     * una estación, sin repetidos.
     *
     * @return Lista de responsables técnicos supervisores.
     */
    public List<ResponsableTecnico> obtenerSupervisores() {
        List<ResponsableTecnico> responsablesSupervision = new ArrayList<>();

        for (EstacionMeteorologica estacion : getEstaciones()) {
                ResponsableTecnico responsable = estacion.getResponsable();
                if (responsable != null &&
                        !responsablesSupervision.contains(responsable)) {
                    responsablesSupervision.add(responsable);
                }
            }
        return responsablesSupervision;
    }

    /**
     * Obtiene los responsables técnicos que realizaron al menos un
     * mantenimiento, de cualquier tipo, sin repetidos.
     *
     * @return Lista de responsables técnicos de mantenimiento.
     */
    public List<ResponsableTecnico> obtenerResponsablesMantenimiento() {
        List<ResponsableTecnico> responsablesMantenimiento = new ArrayList<>();

        for (EstacionMeteorologica estacion : getEstaciones()) {

            for (Mantenimiento mantenimiento : estacion.getMantenimientos()) {
                ResponsableTecnico responsable = mantenimiento.getResponsableTecnico();
                if (responsable != null &&
                        !responsablesMantenimiento.contains(responsable)) {
                    responsablesMantenimiento.add(responsable);
                }
            }
        }
        return responsablesMantenimiento;
    }

    /**
     * Obtiene los responsables técnicos que realizaron al menos un
     * mantenimiento de tipo calibración, sin repetidos.
     *
     * @return Lista de responsables técnicos de calibración.
     */
    public List<ResponsableTecnico> obtenerResponsablesCalibracion() {
        List<ResponsableTecnico> responsablesCalibracion = new ArrayList<>();

        for (EstacionMeteorologica estacion : getEstaciones()) {

            for (Mantenimiento mantenimiento : estacion.getMantenimientos()) {

                if (mantenimiento.getTipo() == TipoMantenimiento.CALIBRACION) {
                    ResponsableTecnico responsable = mantenimiento.getResponsableTecnico();

                    if (responsable != null &&
                            !responsablesCalibracion.contains(responsable)) {
                        responsablesCalibracion.add(responsable);
                    }
                }
            }
        }
        return responsablesCalibracion;
    }

    /**
     * Obtiene los equipos de respuesta registrados.
     *
     * @return Copia de la lista de equipos de respuesta registrados.
     */
    public List<EquipoRespuesta> getEquipos() {
        return new ArrayList<>(equiposRespuesta);
    }
}
