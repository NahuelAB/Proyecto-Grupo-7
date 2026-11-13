/*
 * GestionPersonal.java
 *
 * Version 1.1
 *
 * 12/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.service;

import ar.edu.uner.domain.enums.TipoMantenimiento;
import ar.edu.uner.domain.model.*;

import java.time.LocalDate;
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
    private List<ResponsableTecnico> responsables;
    private List<EquipoRespuesta> equiposRespuesta;

    /**
     * Crea una gestión de personal con atributo de GestionInfraestructura
     * para utilizar su lista de ZonaGeográficas e
     * inicialización de lista para equipos registrados.
     */
    public GestionPersonal(GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
        this.responsables = new ArrayList<>();
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

    /**
     * Registra un responsable técnico si no es null y su DNI todavía
     * no está registrado.
     *
     * @param responsable Responsable técnico a registrar.
     * @return true si se registró, false si es null o el DNI ya existe.
     */
    public boolean registrarResponsable(ResponsableTecnico responsable) {
        if (responsable != null && buscarResponsable(responsable.getDni()) == null) {
            return responsables.add(responsable);
        }
        return false;
    }

    /**
     * Busca un responsable técnico registrado por su DNI.
     *
     * @param dni DNI del responsable.
     * @return El responsable encontrado, o null si no existe.
     */
    public ResponsableTecnico buscarResponsable(String dni) {
        for (ResponsableTecnico r : responsables) {
            if (r.getDni().equals(dni)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Obtiene los responsables técnicos registrados.
     *
     * @return Copia de la lista de responsables técnicos.
     */
    public List<ResponsableTecnico> getResponsables() {
        return new ArrayList<>(responsables);
    }

    /**
     * Asigna un responsable técnico a cargo de una estación.
     *
     * @param dni DNI del responsable.
     * @param idEstacion Identificador de la estación.
     * @return true si se asignó; false si el responsable o la estación
     *         no existen.
     */
    public boolean asignarResponsable(String dni, int idEstacion) {
        ResponsableTecnico responsable = buscarResponsable(dni);
        EstacionMeteorologica estacion = infraestructura.buscarEstacion(idEstacion);

        if (responsable == null || estacion == null) {
            return false;
        }
        // Si la estación tenía otro responsable, se la quita de su cargo
        ResponsableTecnico anterior = estacion.getResponsable();
        if (anterior != null && anterior != responsable) {
            anterior.quitarEstacion(estacion);
        }
        estacion.setResponsable(responsable);
        responsable.agregarEstacion(estacion);
        return true;
    }

    /**
     * Registra un mantenimiento de una estación que no se aplica a un
     * sensor puntual.
     *
     * @param idEstacion Identificador de la estación.
     * @param tipo Tipo de mantenimiento.
     * @param fecha Fecha del mantenimiento.
     * @param descripcion Descripción del mantenimiento.
     * @param dni DNI del responsable que lo realizó.
     * @return true si se registró; false si la estación o el responsable
     *         no existen.
     */
    public boolean registrarMantenimiento(int idEstacion, TipoMantenimiento tipo,
                                          LocalDate fecha, String descripcion,
                                          String dni) {
        EstacionMeteorologica estacion = infraestructura.buscarEstacion(idEstacion);
        ResponsableTecnico responsable = buscarResponsable(dni);

        if (estacion == null || responsable == null || tipo == null
                || fecha == null) {
            return false;
        }
        Mantenimiento m = new Mantenimiento(fecha, tipo, descripcion, responsable);
        estacion.getMantenimientos().add(m);
        responsable.agregarMantenimiento(m);
        return true;
    }

    /**
     * Registra un mantenimiento aplicado a un sensor de una estación.
     * Si el tipo es CALIBRACION, actualiza la fecha de última calibración
     * del sensor.
     *
     * @param idEstacion Identificador de la estación.
     * @param idSensor Identificador del sensor (debe estar instalado en
     *                 esa estación).
     * @param tipo Tipo de mantenimiento.
     * @param fecha Fecha del mantenimiento.
     * @param descripcion Descripción del mantenimiento.
     * @param dni DNI del responsable que lo realizó.
     * @return true si se registró; false si la estación, el sensor o el
     *         responsable no existen, o el sensor no pertenece a la estación.
     */
    public boolean registrarMantenimiento(int idEstacion, int idSensor,
                                          TipoMantenimiento tipo, LocalDate fecha,
                                          String descripcion, String dni) {
        EstacionMeteorologica estacion = infraestructura.buscarEstacion(idEstacion);
        Sensor sensor = infraestructura.buscarSensor(idSensor);
        ResponsableTecnico responsable = buscarResponsable(dni);

        if (estacion == null || sensor == null || responsable == null
                || tipo == null || fecha == null
                || !estacion.getSensores().contains(sensor)) {
            return false;
        }
        Mantenimiento m = new Mantenimiento(fecha, tipo, descripcion,
                responsable, sensor);
        estacion.getMantenimientos().add(m);
        responsable.agregarMantenimiento(m);
        if (tipo == TipoMantenimiento.CALIBRACION) {
            sensor.setUltimaCalibracion(fecha);
        }
        return true;
    }

    /**
     * Agrega un responsable técnico a un equipo de respuesta. Un
     * responsable integra a lo sumo un equipo.
     *
     * @param idEquipo Identificador del equipo.
     * @param dni DNI del responsable.
     * @return true si se agregó; false si el equipo o el responsable no
     *         existen, o el responsable ya integra un equipo.
     */
    public boolean agregarIntegrante(int idEquipo, String dni) {
        EquipoRespuesta equipo = null;
        for (EquipoRespuesta e : equiposRespuesta) {
            if (e.getIdEquipo() == idEquipo) {
                equipo = e;
            }
        }
        ResponsableTecnico responsable = buscarResponsable(dni);

        if (equipo == null || responsable == null) {
            return false;
        }
        for (EquipoRespuesta e : equiposRespuesta) {
            if (e.getIntegrantes().contains(responsable)) {
                return false;
            }
        }
        return equipo.agregarIntegrante(responsable);
    }

    /**
     * Obtiene los equipos de respuesta que están disponibles.
     *
     * @return Lista de equipos disponibles.
     */
    public List<EquipoRespuesta> obtenerEquiposDisponibles() {
        List<EquipoRespuesta> disponibles = new ArrayList<>();
        for (EquipoRespuesta e : equiposRespuesta) {
            if (e.isDisponible()) {
                disponibles.add(e);
            }
        }
        return disponibles;
    }
}
