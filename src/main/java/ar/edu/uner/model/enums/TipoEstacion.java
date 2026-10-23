 *
 * 22/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.model.enums;

/**
 * Representa los niveles de gravedad que puede tener una alerta,
 * ordenados de menor a mayor.
 *
 * @author Jmanuel (Grupo-7)
 * @version 1.0
 */
public enum NivelAlerta {
    /** Nivel verde: sin riesgo. */
    VERDE,

    /** Nivel amarillo: situación a vigilar. */
    AMARILLA,

    /** Nivel naranja: riesgo alto. */
    NARANJA,

    /** Nivel rojo: riesgo crítico. */
    ROJA
}
