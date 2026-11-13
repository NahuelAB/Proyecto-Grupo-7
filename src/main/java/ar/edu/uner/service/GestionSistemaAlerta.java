/*
 * GestionSistemaAlerta.java
 *
 * Version 1.4
 *
 * 03/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.service;

import ar.edu.uner.domain.model.Alerta;
import ar.edu.uner.domain.model.ZonaGeografica;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.enums.TipoEventoClimatico;
import ar.edu.uner.domain.model.EquipoRespuesta;

/**
 * Gestiona el sistema de alerta temprana: zonas geográficas
 * y la emisión/asignación de alertas.
 *
 * @author Jmanuel (Grupo-7)
 */
public class GestionSistemaAlerta {
    private GestionInfraestructura infraestructura;
    private List<Alerta> alertas;

    /**
     * Crea el gestor con el atributo GestionInfraestructura, para utilizar
     * su lista de zonas geográficas, y con la lista de alertas vacía.
     *
     * @param infraestructura Gestor de infraestructura con las zonas registradas.
     */
    public GestionSistemaAlerta(GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
        this.alertas = new ArrayList<>();
    }

    /**
     * Devuelve las alertas registradas en el sistema.
     *
     * @return Lista de alertas.
     */
    public List<Alerta> getAlertas() {
        return alertas;
    }

    /**
     * Agrega una alerta al sistema.
     *
     * @param alerta Alerta a agregar.
     * @return true si se agregó; false si es null o ya estaba en la lista.
     */
    public boolean agregarAlertas(Alerta alerta) {
        if (alerta != null && !alertas.contains(alerta)) {
            return alertas.add(alerta);
        }
        return false;
    }

    /**
     * Método auxiliar que reúne las zonas geográficas registradas
     * en la gestión de infraestructura.
     *
     * @return Lista con todas las zonas geográficas registradas.
     */
    public List<ZonaGeografica> getZonaGeograficas() {
        List<ZonaGeografica> zonas = new ArrayList<>();

        for (ZonaGeografica zona : infraestructura.getZonas()) {
            if (!zonas.contains(zona)) {
                zonas.add(zona);
            }
        }
        return zonas;
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
        // Se guarda el nivel anterior porque evaluarRiesgo() lo actualiza
        NivelAlerta anterior = zona.getNivelAlertaActual();
        NivelAlerta nuevo = zona.evaluarRiesgo();

        // Si el nivel de riesgo no cambió, no se genera una nueva alerta
        if (nuevo == anterior) {
            return null;
        }

        // Nivel VERDE representa normalidad; no genera alerta
        if (nuevo == NivelAlerta.VERDE) {
            return null;
        }

        // Emite y registra la alerta por lluvia copiosa
        Alerta alerta = new Alerta("Lluvia acumulada en 24 hs: "
                + zona.lluviaAcumulada24hs() + " mm", nuevo,
                TipoEventoClimatico.LLUVIA_COPIOSA, zona, LocalDateTime.now());
        agregarAlertas(alerta);
        zona.agregarAlerta(alerta);
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
    public Alerta registrarAlertaManual(ZonaGeografica zona, NivelAlerta nivel,
                                        TipoEventoClimatico tipo, String descripcion,
                                        LocalDateTime fechaHora) {
        if (zona == null || nivel == null || descripcion == null
                || tipo == TipoEventoClimatico.LLUVIA_COPIOSA) {
            return null; // solo CRECIDA_RIO y TORMENTA_SEVERA pueden ser manuales
        }
        Alerta alerta = new Alerta(descripcion, nivel, tipo, zona, fechaHora);
        agregarAlertas(alerta);
        zona.agregarAlerta(alerta);
        return alerta;
    }

    public boolean marcarAtendida(Alerta alerta) {
        if (alerta == null || alerta.getAtendida()) {
            return false;
        }

        if (alerta.requiereEquipo() && alerta.getEquipo() == null) {
            return false; 
        }

        alerta.setAtendida(true);
        return true;
    }
}
