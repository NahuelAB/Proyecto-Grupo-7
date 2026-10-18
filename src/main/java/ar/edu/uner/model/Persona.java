/*
 * Persona.java
 *
 * Version 1.0
 *
 * 15/10/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */


package ar.edu.uner.model;


/**
 * Clase abstracta que representa a una persona en el sistema.
 * Contiene los datos básicos de identificación compartidos por el
 * personal técnico y demás entidades del sistema.
 *
 * @author Nahuel
 */
public abstract class Persona {
    private String dni;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String celular;

    /**
     * Crea una persona con valores predeterminados.
     */
    public Persona() {
        this("DNI PENDIENTE",
                "NOMBRE PENDIENTE",
                "APELLIDO PENDIENTE",
                "CELULAR PENDIENTE",
                "CORREO PENDIENTE");
    }

    /**
     * Crea una persona con valores especificos.
     *
     * @param dni DNI de la persona.
     * @param nombre Nombre de la persona.
     * @param apellido Apellido de la persona.
     * @param correoElectronico Correo electrónico de la persona.
     * @param celular Celular de la persona.
     */
    public Persona(String dni, String nombre, String apellido,
                   String correoElectronico, String celular) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.celular = celular;
    }

    /**
     * Obtiene el DNI de la persona.
     *
     * @return DNI de la persona.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI de la persona.
     *
     * @param dni DNI a asignar.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return Nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido de la persona.
     *
     * @return Apellido de la persona.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido de la persona.
     *
     * @param apellido Apellido a asignar.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el correo electrónico de la persona.
     *
     * @return Correo electrónico de la persona.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico de la persona.
     *
     * @param correoElectronico Correo electrónico a asignar.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene el celular de la persona.
     *
     * @return Celular de la persona.
     */
    public String getCelular() {
        return celular;
    }

    /**
     * Establece el celular de la persona.
     *
     * @param celular Celular a asignar.
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * Devuelve el nombre completo concatenando nombre y apellido.
     *
     * @return Nombre y apellido separados por un espacio.
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }
}
