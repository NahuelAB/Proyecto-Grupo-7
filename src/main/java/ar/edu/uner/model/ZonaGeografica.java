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
    private double umbralAmarilla;
    private double umbralNaranja;
    private double umbralRoja;
    private NivelAlerta nivelAlerta;

    /**
     * Crea una zona geográfica con valores predeterminados.
     */
    public ZonaGeografica() {
        this("ID ZONA PENDIENTE",
                "NOMBRE PENDIENTE",
                null,
                0,
                0,
                0,
                null);
    }

    /**
     * Crea una zona geográfica con valores específicos.
     * @param idZona Identificador de la zona.
     * @param nombre Nombre de zona.
     * @param nivelVulnerabilidad Nivel de vulnerabilidad climática.
     * @param umbralAmarilla Umbral de lluvia (mm en 24 hs) de alerta amarilla.
     * @param umbralNaranja Umbral de lluvia (mm en 24 hs) de alerta naranja.
     * @param umbralRoja Umbral de lluvia (mm en 24 hs) de alerta roja.
     * @param nivelAlerta Nivel de alerta vigente de la zona.
     */
    public ZonaGeografica(String idZona, String nombre,
                          NivelVulnerabilidad nivelVulnerabilidad,
                          double umbralAmarilla, double umbralNaranja,
                          double umbralRoja, NivelAlerta nivelAlerta) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.nivelVulnerabilidad = nivelVulnerabilidad;
        this.umbralAmarilla = umbralAmarilla;
        this.umbralNaranja = umbralNaranja;
        this.umbralRoja = umbralRoja;
        this.nivelAlerta = nivelAlerta;
    }

    /**
     * Obtiene el identificador de la zona.
     * @return Identificador de la zona.
     */
    public String getIdZona() {
        return idZona;
    }

    /**
     * Establece el identificador de la zona.
     * @param idZona Identificador a asignar.
     */
    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    /**
     * Obtiene el nombre de la zona.
     * @return Nombre de la zona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la zona.
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nivel de vulnerabilidad climática de la zona.
     * @return Nivel de vulnerabilidad de la zona.
     */
    public NivelVulnerabilidad getNivelVulnerabilidad() {
        return nivelVulnerabilidad;
    }

    /**
     * Establece el nivel de vulnerabilidad climática de la zona.
     * @param nivelVulnerabilidad Nivel de vulnerabilidad a asignar.
     */
    public void setNivelVulnerabilidad(NivelVulnerabilidad nivelVulnerabilidad) {
        this.nivelVulnerabilidad = nivelVulnerabilidad;
    }

    /**
     * Obtiene el umbral de lluvia acumulada en 24 horas de alerta amarilla.
     * @return Umbral amarillo en milímetros.
     */
    public double getUmbralAmarilla() {
        return umbralAmarilla;
    }

    /**
     * Establece el umbral de lluvia acumulada en 24 horas de alerta amarilla.
     * @param umbralAmarilla Umbral amarillo a asignar, en milímetros.
     */
    public void setUmbralAmarilla(double umbralAmarilla) {
        this.umbralAmarilla = umbralAmarilla;
    }

    /**
     * Obtiene el umbral de lluvia acumulada en 24 horas de alerta naranja.
     * @return Umbral naranja en milímetros.
     */
    public double getUmbralNaranja() {
        return umbralNaranja;
    }

    /**
     * Establece el umbral de lluvia acumulada en 24 horas de alerta naranja.
     * @param umbralNaranja Umbral naranja a asignar, en milímetros.
     */
    public void setUmbralNaranja(double umbralNaranja) {
        this.umbralNaranja = umbralNaranja;
    }

    /**
     * Obtiene el umbral de lluvia acumulada en 24 horas de alerta roja.
     * @return Umbral rojo en milímetros.
     */
    public double getUmbralRoja() {
        return umbralRoja;
    }

    /**
     * Establece el umbral de lluvia acumulada en 24 horas de alerta roja.
     * @param umbralRoja Umbral rojo a asignar, en milímetros.
     */
    public void setUmbralRoja(double umbralRoja) {
        this.umbralRoja = umbralRoja;
    }

    /**
     * Obtiene el nivel de alerta vigente de la zona.
     * @return Nivel de alerta vigente.
     */
    public NivelAlerta getNivelAlerta() {
        return nivelAlerta;
    }

    /**
     * Establece el nivel de alerta vigente de la zona.
     * @param nivelAlerta Nivel de alerta a asignar.
     */
    public void setNivelAlerta(NivelAlerta nivelAlerta) {
        this.nivelAlerta = nivelAlerta;
    }

    public double lluviaAcumulada24hs() {
        return umbralAmarilla;
    }

    public NivelAlerta evaluarRiesgo() {
        return nivelAlerta;
    }
}
