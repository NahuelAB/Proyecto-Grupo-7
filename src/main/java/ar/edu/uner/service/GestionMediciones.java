/*
* GestionMediciones.java
*
* 2/11/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.service;

import java.time.LocalDateTime;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.exception.LecturaInvalidaException;

/**
 * Modulo de gestion que captura lecturas periodicas de precipitacion
 * de los sensores y valida que cada valor este dentro del rango fisico
 * admitido.
 * 
 * @author Guillermina
 */
public class GestionMediciones {
    private GestionInfraestructura infraestructura;
    private GestionSistemaAlerta gestionAlerta;

    /**
     * Crea la gestion de mediciones.
     * @param infraestructura 
     */
    public GestionMediciones(GestionInfraestructura infraestructura,
                             GestionSistemaAlerta gestionAlerta) {
        this.infraestructura = infraestructura;
        this.gestionAlerta = gestionAlerta;
    }
    
    /**
     * Registra una lectura de precipitación en un sensor.
     * 
     * @param idSensor sensor de la lectura.
     * @param mm milimetros registrados.
     * @param fh fecha y hora de la lectura.
     * @return medicion registrada.
     */
    public Medicion registrarLectura(int idSensor, double mm, LocalDateTime fh) 
                                    throws LecturaInvalidaException {
        Sensor s = infraestructura.buscarSensor(idSensor);
        EstacionMeteorologica e = infraestructura.buscarEstacionDeSensor(s);
        Medicion m = null;
        
        if (e!=null
                && e.estaOperativa()
                && (s.getEstado() == EstadoOperativo.OPERATIVO)) {
            s.validar(mm);
            m = s.registrar(mm, fh);

            ZonaGeografica zona = infraestructura.buscarZonaDeEstacion(e);
            gestionAlerta.evaluarZona(zona);
        }
        
        return m;
    }

    
}
