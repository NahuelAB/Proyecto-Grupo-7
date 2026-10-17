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
    
}
