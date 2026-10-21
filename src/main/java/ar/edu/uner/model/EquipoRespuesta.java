/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.edu.uner.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class EquipoRespuesta {
    private String idEquipo;
    private String nombreEquipo;
    private boolean disponible;
    private List<ResponsableTecnico>integrantes;
    private List<Alerta>alertas;
    
    //Constructores
    
    public EquipoRespuesta(String idEquipo, String nombreEquipo, boolean disponible) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.disponible = disponible;
        this.integrantes=new ArrayList<ResponsableTecnico>();
        this.alertas=new ArrayList<Alerta>();
    }
    public EquipoRespuesta() {
    this.integrantes = new ArrayList<>();
    this.alertas= new ArrayList<>();
    }


    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<ResponsableTecnico> getIntegrantes() {
        return integrantes;
    }
    

    @Override
    public String toString() {
        return "EquipoRespuesta{" + "idEquipo=" + idEquipo + ", nombreEquipo=" + nombreEquipo + ", disponible=" + disponible + '}';
    }
    
    public boolean agregarIntegrante(ResponsableTecnico r) {
    // Validar que no sea nulo o que no esté duplicado 
    if (r != null && !this.integrantes.contains(r)) {
        return this.integrantes.add(r); // .add() devuelve true si se agregó correctamente
    }
    return false; // No se Pudo agregar
}
    
    
    
}
