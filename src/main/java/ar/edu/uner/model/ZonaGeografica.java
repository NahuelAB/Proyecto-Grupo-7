/*
 * ZonaGeografica.java
 *
 * Version 1.0
 *
 * 17/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */


package ar.edu.uner.model;

import ar.edu.uner.model.enums.NivelAlerta;
import ar.edu.uner.model.enums.NivelVulnerabilidad;

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
    private String idZona;
    private String nombre;
    private NivelVulnerabilidad nivelVulnerabilidad;
    private NivelAlerta nivelAlertaActual;
    private List<ValorHistorico> valoresHistoricos;
    private List<EstacionMeteorologica> estacionMeteorologicas;

    /**
     * Crea una zona geográfica con valores predeterminados
     * e inicializa su lista de valores históricos vacía.
     */
    public ZonaGeografica() {
        this("ID ZONA PENDIENTE",
                "NOMBRE PENDIENTE",
                null,
                NivelAlerta.VERDE);
    }

    /**
     * Crea una zona geográfica con valores específicos
     * e inicializa su lista de valores históricos vacía.
     *
     * @param idZona Identificador de la zona.
     * @param nombre Nombre de zona.
     * @param nivelVulnerabilidad Nivel de vulnerabilidad climática.
     * @param nivelAlertaActual Nivel de alerta vigente de la zona.
     */
    public ZonaGeografica(String idZona, String nombre,
                          NivelVulnerabilidad nivelVulnerabilidad,
                          NivelAlerta nivelAlertaActual) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.nivelVulnerabilidad = nivelVulnerabilidad;
        this.nivelAlertaActual = nivelAlertaActual;
        this.valoresHistoricos = new ArrayList<>();
        this.estacionMeteorologicas = new ArrayList<>();
    }

    /**
     * Obtiene el identificador de la zona.
     *
     * @return Identificador de la zona.
     */
    public String getIdZona() {
        return idZona;
    }

    /**
     * Establece el identificador de la zona.
     *
     * @param idZona Identificador a asignar.
     */
    public void setIdZona(String idZona) {
        this.idZona = idZona;
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

    public double lluviaAcumulada24hs() {
        double acumuladoTotal = 0.0;
        LocalDateTime limiteTiempo = LocalDateTime.now().minusHours(24);

        // Imcompleto, faltan metodos de medicion
        /*for (EstacionMeteorologica estacion : this.estacionMeteorologicas) {
            for (Sensor sensor : estacion.getSensores()) {
                //for (Medicion medicion : sensor.getMedicion()){

                }
            }
        }*/
        return acumuladoTotal;
    }

    public NivelAlerta evaluarRiesgo() {
        return nivelAlertaActual;
    }

    /**
     * Agrega un valor histórico mensual a la zona geográfica..
     *
     * @param valorHistorico Valor histórico a agregar.
     */
    public void agregarValorHistorico(ValorHistorico valorHistorico) {
        valoresHistoricos.add(valorHistorico);
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
     * Agrega una estación meteorológica a la zona.
     *
     * @param estacionMeteorologica Estación a agregar.
     */
    public void agregarEstacion(EstacionMeteorologica estacionMeteorologica) {
        this.estacionMeteorologicas.add(estacionMeteorologica);
    }

    /**
     * Obtiene las estaciones meteorológicas de la zona.
     *
     * @return Lista de estaciones de la zona.
     */
    public List<EstacionMeteorologica> getEstacionMeteorologicas() {
        return estacionMeteorologicas;
    }
}

