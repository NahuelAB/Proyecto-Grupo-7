/*
* Alerta.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.model;

//import java.time.LocalDateTime;


/**
 * Alerta emitida por una zona. Si su nivel es NARANJA o ROJA
 * requiere la asignacion obligatoria de un equipo respuesta.
 * 
 * @author Guillermina
 */
public class Alerta {
    private String idAlerta;
    private String descripcion;
    private boolean atendida;
    //private LocalDateTime fechaHoraEmision;
    //private NivelAlerta nivel;
    //private TipoEventoClimatico evento;

    public Alerta() {
        this("ALERTA PENDIENTE",
            "DESCRIPCION PENDIENTE");
    }

    public Alerta(String idAlerta, String descripcion) {
        this.idAlerta = idAlerta;
        this.descripcion = descripcion;
        atendida = false;
    }
    
}
