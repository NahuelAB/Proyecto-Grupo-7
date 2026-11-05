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
 * Clase para la gestion del personal.
 *
 * @author Nahuel
 */

public class GestionPersonal {
    private final List<ZonaGeografica> zonasGeograficas;
    private final List<EquipoRespuesta> equiposRespuesta;

    public GestionPersonal() {
        zonasGeograficas = new ArrayList<>();
        equiposRespuesta = new ArrayList<>();
    }

    public List<ZonaGeografica> obtenerZonasGeograficas() {
        return new ArrayList<>(zonasGeograficas);
    }


    public boolean agregarZona(ZonaGeografica z) {
        if (z != null && !zonasGeograficas.contains(z)) {
            return zonasGeograficas.add(z);
        }
        return false;
    }

    public boolean agregarEquipo(EquipoRespuesta e) {
        if (e != null && !equiposRespuesta.contains(e)) {
            return equiposRespuesta.add(e);
        }
        return false;
    }

    public List<EstacionMeteorologica> getEstaciones() {
        List<EstacionMeteorologica> estaciones = new ArrayList<>();

        for (ZonaGeografica zona : zonasGeograficas) {
            estaciones.addAll(zona.getEstacionesMeteorologicas());
        }
        return estaciones;
    }

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

    public List<EquipoRespuesta> getEquipos() {
        return new ArrayList<>(equiposRespuesta);
    }
}
