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

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Mantenimiento;
import ar.edu.uner.domain.model.ResponsableTecnico;
import ar.edu.uner.domain.model.ZonaGeografica;

import java.util.ArrayList;
import java.util.List;

public class GestionPersonal {
    List<ZonaGeografica> zonasGeograficas;

    public GestionPersonal() {
        zonasGeograficas = new ArrayList<>();
    }

    public List<ZonaGeografica> getZonaGeografica() {
        return zonasGeograficas;
    }

    public boolean agregarZona(ZonaGeografica z) {
        if (!zonasGeograficas.contains(z)) {
            return zonasGeograficas.add(z);
        }
        return false;
    }

    public List<ResponsableTecnico> obtenerSupervisores() {
        List<ResponsableTecnico> responsablesSupervision = new ArrayList<>();

        for (ZonaGeografica zona : zonasGeograficas) {
            for (EstacionMeteorologica estacion :
                    zona.getEstacionesMeteorologicas()) {
                ResponsableTecnico responsable = estacion.getResponsable();

                if (responsable != null
                        && !responsablesSupervision.contains(responsable)) {
                    responsablesSupervision.add(responsable);
                }
            }
        }
        return responsablesSupervision;
    }

    public List<ResponsableTecnico> obtenerResponsablesMantenimiento() {
        List<ResponsableTecnico> responsablesMantenimiento = new ArrayList<>();

        for (ZonaGeografica zona : zonasGeograficas) {
            for (EstacionMeteorologica estacion :
                    zona.getEstacionesMeteorologicas()) {
                for (Mantenimiento mantenimiento : estacion.getMantenimientos()) {
                    ResponsableTecnico responsable = mantenimiento.getResponsableTecnico();

                    if (responsable != null && !responsablesMantenimiento.contains(responsable)) {
                        responsablesMantenimiento.add(responsable);
                    }
                }
            }
        }
        return responsablesMantenimiento;
    }
}
