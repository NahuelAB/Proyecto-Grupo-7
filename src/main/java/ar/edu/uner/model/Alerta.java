/*
* Alerta.java
*
* 17/10/2026
*
* Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.model;

import java.time.LocalDateTime;
import ar.edu.uner.model.enums.*;


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
    private NivelAlerta nivel;
    private TipoEventoClimatico eventoClim;
    private LocalDateTime fechaHoraEmision;
    private EquipoRespuesta equipo;
    private ZonaGeografica zona;

    /**
     * Crea una alerta con valores predeterminados.
     */
    public Alerta() {
        this("ALERTA PENDIENTE",
            "DESCRIPCION PENDIENTE",
            null,
            null);
    }

    /**
     * Crea una alerta con valores especificos.
     * @param idAlerta identificador unico de la alerta.
     * @param descripcion descripcion de la alerta.
     * @param nivel nivel de la alerta.
     * @param eventoClim tipo de evento climatico.
     */
    public Alerta(String idAlerta, String descripcion,
                NivelAlerta nivel, TipoEventoClimatico eventoClim) {
        this.idAlerta = idAlerta;
        this.descripcion = descripcion;
        atendida = false;
        this.nivel = nivel;
        this.eventoClim = eventoClim;
        fechaHoraEmision = LocalDateTime.now(); 
    }
    
    /**
     * Devuelve el ID de la alerta.
     * 
     * @return ID de la alerta.
     */
    public String getIdAlerta() {
        return idAlerta;
    }

    /**
     * Establece el ID de la alerta.
     * 
     * @param idAlerta nuevo ID de la alerta.
     */
    public void setIdAlerta(String idAlerta) {
        this.idAlerta = idAlerta;
    }

    /**
     * Devuelve la descripcion de la alerta.
     * 
     * @return descripcion de la alerta.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion de la alerta.
     * 
     * @param descripcion nueva descripcion de la alerta.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el estado de atencion de la alerta.
     * 
     * @return estado de atencion de la alerta.
     */
    public boolean getAtendida() {
        return atendida;
    }

    /**
     * Establece el estado de atencion de la alerta.
     * 
     * @param atendida nuevo estado de atencion de la alerta.
     */
    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
        /*if (equipo != null) {
        *    equipo.setDisponible(true);
        }*/
    }

    /**
     * Devuelve el nivel de la alerta.
     * 
     * @return nivel de la alerta.
     */
    public NivelAlerta getNivel() {
        return nivel;
    }
    /**
     * Establece el nivel de la alerta.
     * 
     * @param nivel nuevo nivel de la alerta.
     */
    public void setNivel(NivelAlerta nivel) {
        this.nivel = nivel;
    }

    /**
     * Devuelve el tipo de evento climatico de la alerta.
     * 
     * @return tipo de evento climatico.
     */
    public TipoEventoClimatico getEventoClim() {
        return eventoClim;
    }

    /**
     * Devuelve la zona geografica de la alerta.
     * 
     * @return zona geografica de la alerta.
     */
    public ZonaGeografica getZona() {
        return zona;
    }

    /**
     * Establece la zona geografica de la alerta.
     * 
     * @param zona nueva zona geografica de la alerta.
     */
    public void setZona(ZonaGeografica zona) {
        this.zona = zona;
    }

    /*public void setEventoClim(TipoEventoClimatico eventoClim) {
    *    this.eventoClim = eventoClim;
    }*/

    public EquipoRespuesta getEquipo() {
        return equipo;
    }

    /**
     * Metodo para agregar equipo de respuesta a la alerta.
     * 
     * @param equipo nuevo equipo de respuesta.
     */
    public void agregarEquipo(EquipoRespuesta equipo) {
        this.equipo = equipo;
        equipo.setDisponible(false); //EQUIPO PASA A NO ESTAR DISPO
    }

    /**
     * Verifica que la alerta necesite un equipo de respuesta.
     * 
     * @return true si la alerta necesita un equipo,
     *         false en caso contrario.
     */
    public boolean requiereEquipo() {
        if (nivel == NivelAlerta.NARANJA 
            || nivel == NivelAlerta.ROJA) {
                return true;
        }
        return false;
    }
}
