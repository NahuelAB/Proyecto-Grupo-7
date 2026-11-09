/*
 * HistorialAlertas.java
 *
 * Version 1.0
 *
 * 08/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */


package ar.edu.uner.report;

import ar.edu.uner.domain.model.Alerta;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.service.GestionInfraestructura;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Reporte del historial de alertas emitidas. Reúne las alertas de las
 * zonas registradas en la infraestructura y permite ordenarlas por
 * severidad y filtrarlas por zona y por fecha.
 *
 * @author Nahuel
 */
public class HistorialAlertas {
    private GestionInfraestructura infraestructura;
    private List<Alerta> alertas;

    /**
     * Crea el reporte a partir de la infraestructura y reúne las alertas
     * emitidas hasta el momento.
     *
     * @param infraestructura Gestión de infraestructura de la que se
     *        obtienen las zonas y sus alertas.
     */
    public HistorialAlertas(GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
        this.alertas = obtenerAlertas();
    }

    /**
     * Reúne las alertas de todas las zonas de la infraestructura, sin
     * repetidos, y las guarda como base de los demás métodos del reporte.
     *
     * @return Lista con las alertas de todas las zonas.
     */
    public List<Alerta> obtenerAlertas() {
        alertas = new ArrayList<>();

        for (ZonaGeografica zona : infraestructura.getZonas()) {

            for (Alerta alerta : zona.getHistorialAlertas()) {

                if (!alertas.contains(alerta)) {
                    alertas.add(alerta);
                }
            }
        }
        return alertas;
    }

    /**
     * Obtiene el historial de alertas ordenado por severidad, de mayor a
     * menor. Dentro de un mismo nivel, la alerta más reciente va primero.
     * Trabaja sobre las alertas reunidas en la última llamada a
     * {@link #obtenerAlertas()} (o al crear el reporte).
     *
     * @return Lista de alertas ordenada por severidad y fecha de emisión.
     */
    public List<Alerta> obtenerHistorialOrdenado() {
        List<Alerta> historialFiltrado = new ArrayList<>();

        for (Alerta alerta : alertas) {

            if (!historialFiltrado.contains(alerta)) {
                historialFiltrado.add(alerta);
            }
        }

        Collections.sort(historialFiltrado);

        return historialFiltrado;
    }

    /**
     * Obtiene las alertas emitidas por una zona geográfica.
     *
     * @param id Identificador de la zona.
     * @return Lista de alertas de la zona, o null si no existe una zona
     *         con ese identificador.
     */
    public List<Alerta> obtenerAlertasPorZona(int id) {

        for (ZonaGeografica zona : infraestructura.getZonas()) {

            if (zona.getIdZona() == id) {
                return zona.getHistorialAlertas();
            }
        }
        return null;
    }

    /**
     * Filtra las alertas emitidas después de una fecha y hora dada.
     * La fecha de inicio no está incluida.
     *
     * @param fechaInicio Fecha y hora a partir de la cual se filtra.
     * @return Lista de alertas emitidas posteriormente a fechaInicio.
     */
    public List<Alerta> filtrarPorFecha(LocalDateTime fechaInicio) {
        List<Alerta> filtradoFechas = new ArrayList<>();

        for (Alerta alerta : alertas) {

            if (alerta.getFechaHoraEmision().isAfter(fechaInicio)) {

                if (!filtradoFechas.contains(alerta)) {
                    filtradoFechas.add(alerta);
                }
            }
        }
        return filtradoFechas;
    }
}
