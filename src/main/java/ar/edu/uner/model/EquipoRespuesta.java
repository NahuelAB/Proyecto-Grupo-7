/*
 * EquipoRespuesta.java
 *
 * Version 1.0
 *
 * 21/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */
package ar.edu.uner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un equipo de respuesta encargado de atender las alertas
 * generadas por la estación meteorológica.
 * Cada equipo tiene un identificador, un nombre, un estado de disponibilidad,
 * una lista de responsables técnicos que lo integran y una lista de alertas
 * asignadas.
 *
 * @author Maricela
 */
public class EquipoRespuesta {
    private String idEquipo;
    private String nombreEquipo;
    private boolean disponible;
    private List<ResponsableTecnico>integrantes;
    private List<Alerta>alertas;
    
    //Constructores
 
    /**
     * Crea un equipo de respuesta con los datos indicados.
     * Las listas de integrantes y de alertas se inicializan vacías.
     *
     * @param idEquipo     identificador único del equipo
     * @param nombreEquipo nombre del equipo
     * @param disponible    true si el equipo está disponible para
     *                     atender alertas,false en caso contrario
     */
    public EquipoRespuesta(String idEquipo, String nombreEquipo, boolean disponible) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.disponible = disponible;
        this.integrantes=new ArrayList<ResponsableTecnico>();
        this.alertas=new ArrayList<Alerta>();
    }
    
     /**
     * Crea un equipo de respuesta sin datos cargados.
     * Las listas de integrantes y de alertas se inicializan vacías.
     */
    public EquipoRespuesta() {
    this.integrantes = new ArrayList<>();
    this.alertas= new ArrayList<>();
    }
    
     
     /**
     * Devuelve el identificador del equipo.
     *
     * @return el identificador del equipo
     */
    public String getIdEquipo() {
        return idEquipo;
    }
    
    /**
     * Modifica el identificador del equipo.
     *
     * @param idEquipo el nuevo identificador del equipo
     */
    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }
    
    /**
     * Devuelve el nombre del equipo.
     *
     * @return el nombre del equipo
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }
    
    /**
     * Modifica el nombre del equipo.
     *
     * @param nombreEquipo el nuevo nombre del equipo
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
    
    /**
     * Indica si el equipo está disponible para atender alertas.
     *
     * @return {@code true} si está disponible, {@code false} en caso contrario
     */
    public boolean isDisponible() {
        return disponible;
    }
    
     /**
     * Modifica el estado de disponibilidad del equipo.
     *
     * @param disponible {@code true} para marcarlo como disponible,
     *                   {@code false} para marcarlo como no disponible
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<ResponsableTecnico> getIntegrantes() {
        return integrantes;
    }
    
    
    /**
     * Agrega un responsable técnico al equipo.
     * Solo se agrega si no es nulo y si no forma parte del equipo todavía.
     *
     * @param r el responsable técnico a agregar
     * @return {@code true} si se agregó correctamente, {@code false} si era
     *         nulo o ya estaba en el equipo
     */    
    public boolean agregarIntegrante(ResponsableTecnico r) {
    // Validar que no sea nulo o que no esté duplicado 
    if (r != null && !this.integrantes.contains(r)) {
        return this.integrantes.add(r); // .add() devuelve true si se agregó correctamente
    }
    return false; // No se Pudo agregar
}
    
    /**
     * Agrega una alerta  a la lista de alertas con las que trabaja el
     * equipo de respuesta
     * @param a alerta a agregar
     * @return {@code true} si se agregó correctamente,{@code false} si no se pudo agregar, 
     * ya sea porque el parametro era null o ya se encontraba en la lista
     */
    public boolean agregarAlerta(Alerta a){
        //validar que no sea null o que no esté duplicado
        if(a != null & this.alertas.contains(a)){
            return this.alertas.add(a);
        }
        return false;//no se pudo agregar
    }
  
    
    
    
}
