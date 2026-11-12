/*
 * ZonaGeografica.java
 *
 * Version 1.0
 *
 * 17/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */


package ar.edu.uner.domain.model;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.NivelVulnerabilidad;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase que representa a una zona geografica en el sistema.
 * Define sus tres umbrales de lluvia acumulada en 24 horas y guarda su
 * nivel de alerta vigente.
 *
 * @author Nahuel
 */
public class ZonaGeografica {
    private static int contId = 1;
    private int idZona;
    private String nombre;
    private NivelVulnerabilidad nivelVulnerabilidad;
    private NivelAlerta nivelAlertaActual;
    private List<ValorHistorico> valoresHistoricos;
    private List<EstacionMeteorologica> estacionesMeteorologicas;
    private List<Alerta> historialAlertas;

    /**
     * Crea una zona geográfica con valores predeterminados
     * e inicializa su lista de valores históricos vacía.
     */
    public ZonaGeografica() {
        this("NOMBRE PENDIENTE",
                null,
                NivelAlerta.VERDE);
    }

    /**
     * Crea una zona geográfica con valores específicos
     * e inicializa su lista de valores históricos vacía.
     *
     * @param nombre Nombre de zona.
     * @param nivelVulnerabilidad Nivel de vulnerabilidad climática.
     * @param nivelAlertaActual Nivel de alerta vigente de la zona.
     */
    public ZonaGeografica(String nombre,
                          NivelVulnerabilidad nivelVulnerabilidad,
                          NivelAlerta nivelAlertaActual) {
        this.idZona = contId;
        this.nombre = nombre;
        this.nivelVulnerabilidad = nivelVulnerabilidad;
        this.nivelAlertaActual = nivelAlertaActual;
        this.estacionesMeteorologicas = new ArrayList<>();
        this.valoresHistoricos = new ArrayList<>();
        this.historialAlertas = new ArrayList<>();
        contId++;
    }

    /**
     * Obtiene el identificador de la zona.
     *
     * @return Identificador de la zona.
     */
    public int getIdZona() {
        return idZona;
    }

    /**
     * Obtiene el nombre de la zona.
     *
     * @return Nombre de la zona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la zona.
     *
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nivel de vulnerabilidad climática de la zona.
     *
     * @return Nivel de vulnerabilidad de la zona.
     */
    public NivelVulnerabilidad getNivelVulnerabilidad() {
        return nivelVulnerabilidad;
    }

    /**
     * Establece el nivel de vulnerabilidad climática de la zona.
     *
     * @param nivelVulnerabilidad Nivel de vulnerabilidad a asignar.
     */
    public void setNivelVulnerabilidad(
            NivelVulnerabilidad nivelVulnerabilidad) {
        this.nivelVulnerabilidad = nivelVulnerabilidad;
    }

    /**
     * Obtiene el nivel de alerta vigente de la zona.
     *
     * @return Nivel de alerta vigente.
     */
    public NivelAlerta getNivelAlertaActual() {
        return nivelAlertaActual;
    }

    /**
     * Establece el nivel de alerta vigente de la zona.
     *
     * @param nivelAlertaActual Nivel de alerta a asignar.
     */
    public void setNivelAlertaActual(NivelAlerta nivelAlertaActual) {
        this.nivelAlertaActual = nivelAlertaActual;
    }

    /**
     * Calcula el total acumulado de lluvia en milímetros registrados en la zona
     * durante las últimas 24 horas a partir del momento actual.
     *
     * Recorre las estaciones meteorológicas asociadas a la zona y sus sensores,
     * contando unicamente las mediciones de aquellos sensores que se encuentren
     * operativos.
     *
     * @return Suma total de milímetros de precipitación acumulados en 24 horas.
     */
    public double lluviaAcumulada24hs() {
        double acumuladoTotal = 0.0;
        LocalDateTime limiteTiempo = LocalDateTime.now().minusHours(24);

        for (EstacionMeteorologica estacion : estacionesMeteorologicas) {
            for (Sensor sensor : estacion.getSensores()) {
                if (sensor.getEstado() == EstadoOperativo.OPERATIVO) {
                    for (Medicion medicion : sensor.getMediciones()){
                        if (medicion.getFechaHora().isAfter(limiteTiempo)) {
                            acumuladoTotal += medicion.getMilimetros();
                        }
                    }
                }
            }
        }
        return acumuladoTotal;
    }

    /**
     * Evalúa la lluvia acumulada en las últimas 24 en la zona
     * y actualiza el nivel de alerta correspondiente según los umbrales.
     *
     * @return El nivel de alerta actual asignado a la zona geografica.
     */
    public NivelAlerta evaluarRiesgo() {
        double acumulado24hs = lluviaAcumulada24hs();

        if (acumulado24hs < 50) {
            nivelAlertaActual = NivelAlerta.VERDE;
        } else if (acumulado24hs < 100) {
            nivelAlertaActual = NivelAlerta.AMARILLA;
        } else if (acumulado24hs < 150) {
            nivelAlertaActual = NivelAlerta.NARANJA;
        } else {
            nivelAlertaActual = NivelAlerta.ROJA;
        }

        return nivelAlertaActual;
    }

    /**
     * Obtiene la lista de valores históricos de la zona geográfica.
     *
     * @return Lista de valores históricos.
     */
    public List<ValorHistorico> getValoresHistoricos() {
        return valoresHistoricos;
    }

    /**
     * Agrega un valor histórico mensual a la zona geográfica.
     *
     * @param valorHistorico Valor histórico a agregar.
     */
    public boolean agregarValorHistorico(ValorHistorico valorHistorico) {
        if (valorHistorico != null &&
                !this.valoresHistoricos.contains(valorHistorico)) {
            return this.valoresHistoricos.add(valorHistorico);
        }
        return false;
    }

    /**
     * Agrega una estación meteorológica a la zona.
     *
     * @param estacion Estación a agregar.
     */
    public boolean agregarEstacion(EstacionMeteorologica estacion) {
        if (estacion != null && !estacionesMeteorologicas.contains(estacion)) {
            return this.estacionesMeteorologicas.add(estacion);
        }
        return false;
    }

    /**
     * Obtiene las estaciones meteorológicas de la zona.
     *
     * @return Lista de estaciones de la zona.
     */
    public List<EstacionMeteorologica> getEstacionesMeteorologicas() {
        return estacionesMeteorologicas;
    }

    public List<Alerta> getHistorialAlertas() {
        return historialAlertas;
    }

    public boolean agregarAlerta(Alerta alerta) {
        if (alerta != null && !historialAlertas.contains(alerta)) {
            return this.historialAlertas.add(alerta);
        }
        return false;
    }

    @Override 
    public String toString() {
        return "ID: " + idZona + " | Nombre: " + nombre;
    }
}

