/*
 * InformeFallasMantenimiento.java
 *
 * Version 1.0
 *
 * 8/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.report;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;


/**
 * Informe de fallas y mantenimiento. Permite consultar los sensores que no
 * están operativos (fuera de servicio o en mantenimiento), las lecturas
 * anómalas de un sensor y los sensores que requieren calibración. Esta clase
 * solo realiza consultas y cálculos: la presentación de los resultados está a
 * cargo de la gestión del informe.
 *
 * @author Guillermina
 */
public class InformeFallasMantenimiento {
    private GestionInfraestructura infraestructura;

    /**
     * Crea el informe con la gestión de infraestructura de la que obtiene los
     * sensores.
     *
     * @param infraestructura Gestión de infraestructura con los sensores registrados.
     */
    public InformeFallasMantenimiento
            (GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
    }

    /**
     * Obtiene los sensores cuyo estado no es OPERATIVO, es decir, los que están
     * en mantenimiento o fuera de servicio.
     *
     * @return Lista de sensores no operativos; vacía si todos están operativos.
     */
    public List<Sensor> sensoresNoOperativos() {
        List<Sensor> resultado = new ArrayList<Sensor>();
        for (Sensor s : infraestructura.getSensores()) {
            if (s.getEstado() != EstadoOperativo.OPERATIVO) {
                resultado.add(s);
            }
        }
        return resultado;
    }

    /**
     * Obtiene las lecturas anómalas de un sensor: aquellas válidas que difieren
     * de la lectura anterior del mismo sensor en más de 100 mm y quedaron
     * marcadas como anómalas al registrarse.
     *
     * @param s Sensor a consultar.
     * @return Lista de mediciones anómalas del sensor; vacía si no tiene ninguna.
     */
    public List<Medicion> lecturasAnomalas(Sensor s) {
        List<Medicion> resultado = new ArrayList<Medicion>();
        for (Medicion m : s.getMediciones()) {
            if (m.isAnomala()) {
                resultado.add(m);
            }
        }
        return resultado;
    } 

    /**
     * Obtiene los sensores que requieren calibración, es decir, aquellos para
     * los que pasaron más de 12 meses desde su última calibración.
     *
     * @return Lista de sensores que requieren calibración; vacía si ninguno la necesita.
     */
    public List<Sensor> sensoresParaCalibrar() {
        List<Sensor> resultado = new ArrayList<Sensor>();
        for (Sensor s : infraestructura.getSensores()) {
            if (s.requiereCalibracion()) {
                resultado.add(s);
            }
        }
        return resultado;
    }
}
