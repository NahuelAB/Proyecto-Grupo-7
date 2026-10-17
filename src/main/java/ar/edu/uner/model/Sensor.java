package ar.edu.uner.model;

import java.time.LocalDate;


public class Sensor {
    private String idSensor;
    private String marca;
    private String modelo;
    private double precision;
    //private EstadoOperativo estado;
    /*
    * private LocalDate ultimaCalibracion; 
    * Ver si conviene crear un constructor aparte pidiendo anio/mes/dia,
    * guardarlos en un unico dato mediante un get y llamar al get en el
    * constructor Sensor.
    */

    public Sensor() {
        this("ID PENDIENTE",
            "MARCA PENDIENTE",
            "MODELO PENDIENTE",
            0);
    }

    public Sensor(String idSensor, String marca, String modelo,
                  double precision) {
        this.idSensor = idSensor;
        this.marca = marca;
        this.modelo = modelo;
        this.precision = precision;
    }
    
    public String getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(String idSensor) {
        this.idSensor = idSensor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }
}
