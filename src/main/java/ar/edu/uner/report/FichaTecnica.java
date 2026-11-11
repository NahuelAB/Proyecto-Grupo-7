/*
 * FichaTecnica.java
 *
 * Version 1.0
 *
 * 11/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.report;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.service.GestionInfraestructura;
import java.util.ArrayList;
import java.util.List;

/**
 * Obtiene los datos de la ficha técnica operativa de una estación
 * meteorológica: la estación, con sus sensores y su responsable técnico,
 * y las últimas lecturas de cada sensor.
 *
 * @author Maricela
 */
public class FichaTecnica {
    private static final int CANTIDAD_LECTURAS = 5;
    private GestionInfraestructura infraestructura;

    /**
     * Crea la ficha técnica a partir de la gestión de infraestructura, de
     * donde obtiene las estaciones registradas.
     *
     * @param infraestructura gestión de infraestructura del sistema
     */
    public FichaTecnica(GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
    }

    /**
     * Obtiene la estación de la que se arma la ficha técnica. A partir de
     * ella se acceden sus sensores y su responsable técnico a cargo.
     *
     * @param idEstacion identificador de la estación
     * @return la estación con ese identificador, o {@code null} si no hay
     *         ninguna estación registrada con ese identificador
     */
    public EstacionMeteorologica obtenerEstacion(int idEstacion) {
        return this.infraestructura.buscarEstacion(idEstacion);
    }

    /**
     * Obtiene las últimas lecturas registradas por un sensor, de la más
     * antigua a la más reciente. Si el sensor tiene menos lecturas que la
     * cantidad buscada, devuelve todas las que tiene.
     *
     * @param s el sensor cuyas lecturas se buscan
     * @return lista con las últimas lecturas del sensor, vacía si el sensor
     *         es nulo o no tiene lecturas
     */
    public List<Medicion> ultimasLecturas(Sensor s) {
        List<Medicion> resultado = new ArrayList<Medicion>();
        if (s == null) {
            return resultado;
        }
        List<Medicion> mediciones = s.getMediciones();
        int inicio = Math.max(0, mediciones.size() - CANTIDAD_LECTURAS);
        for (int i = inicio; i < mediciones.size(); i++) {
            resultado.add(mediciones.get(i));
        }
        return resultado;
    }
}