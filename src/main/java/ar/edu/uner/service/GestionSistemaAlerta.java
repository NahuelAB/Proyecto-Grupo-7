/*
 * GestionSistemaAlerta.java
 *
 * Version 1.0
 *
 * 03/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.service;

import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Alerta;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.ZonaGeografica;
import java.util.List;
import java.util.ArrayList;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.TipoEventoClimatico;
import ar.edu.uner.domain.model.EquipoRespuesta;

/**
 * Gestiona la infraestructura meteorológica: zonas geográficas,
 * estaciones meteorológicas y la emisión/asignación de alertas.
 *
 * @author Jmanuel (Grupo-7)
 */
public class GestionSistemaAlerta {
    private List<Alerta> alertas;
    private List<ZonaGeografica> zonasGeograficas;

    public List<Alerta> getAlertas(){
        return alertas;
    }

    public boolean agregarAlertas(Alerta alerta){
        return alertas.add(alerta);
    }

    public List<ZonaGeografica> getZonaGeograficas(){
        return zonasGeograficas;
    }

    public boolean agregarZonaGeografica(ZonaGeografica zonaGeografica){
        return zonasGeograficas.add(zonaGeografica);
    }

    public GestionSistemaAlerta(){
        this.alertas = new ArrayList<>();
        this.zonasGeograficas = new ArrayList<>();
    }

    /**
     * Evalúa el nivel de riesgo de una zona geográfica según la precipitación
     * acumulada en las últimas 24 horas y emite automáticamente una alerta de
     * tipo LLUVIA_COPIOSA si el riesgo cambia y supera el nivel VERDE.
     *
     * @param zona La zona geográfica a evaluar.
     * @return La Alerta emitida si se superó un umbral crítico (AMARILLA, NARANJA o ROJA)
     *         y cambió respecto al nivel actual; o null si el nivel no cambió o se mantiene en VERDE.
     */
    public Alerta evaluarZona(ZonaGeografica zona) {
        NivelAlerta nuevo = zona.evaluarRiesgo();

        // Si el nivel de riesgo no ha cambiado, no se genera una nueva alerta
        if (nuevo == zona.getNivelAlertaActual()) {
            return null;
        }

        // Actualiza el nivel vigente en la zona
        zona.setNivelAlertaActual(nuevo);

        // Nivel VERDE representa normalidad; no genera alerta en el sistema
        if (nuevo == NivelAlerta.VERDE) {
            return null;
        }

        // Emite y registra la alerta por Lluvia Copiosa
        Alerta alerta = new Alerta("Lluvia acumulada en 24 hs: "
                + zona.lluviaAcumulada24hs() + " mm", nuevo, TipoEventoClimatico.LLUVIA_COPIOSA);
        alerta.setZona(zona);
        agregarAlertas(alerta);
        return alerta;
    }

    /**
     * Asigna un equipo de respuesta técnica a una alerta crítica existente.
     * Verifica que la alerta requiera atención de emergencia (nivel NARANJA o ROJA)
     * y que el equipo se encuentre disponible.
     *
     * @param alerta La alerta a la cual se asignará la respuesta.
     * @param equipo El equipo de respuesta humana/técnica a asignar.
     * @return true si la asignación fue exitosa; false si la alerta no requiere equipo
     *         o si el equipo no está disponible.
     */
    public boolean asignarEquipo(Alerta alerta, EquipoRespuesta equipo) {
        if (!alerta.requiereEquipo() || !equipo.isDisponible()) {
            return false;
        }
        alerta.agregarEquipo(equipo);
        equipo.agregarAlerta(alerta);
        return true;
    }

    /**
     * Permite a un operador registrar manualmente una alerta en el sistema,
     * orientada a eventos específicos como CRECIDA_RIO o TORMENTA_SEVERA.
     *
     * @param zona Zona geográfica afectada por el evento.
     * @param nivel Nivel de severidad asignado por el operador (VERDE, AMARILLA, NARANJA, ROJA).
     * @param tipo Tipo de evento climático (ej. CRECIDA_RIO, TORMENTA_SEVERA).
     * @param descripcion Detalle o informe descriptivo redactado por el operador.
     * @return La Alerta creada y almacenada en el sistema.
     */
    public Alerta registrarAlertaManual(ZonaGeografica zona, NivelAlerta nivel, TipoEventoClimatico tipo, String descripcion) {
        Alerta alerta = new Alerta(descripcion, nivel, tipo);
        alerta.setZona(zona);
        agregarAlertas(alerta);
        return alerta;
    }
}