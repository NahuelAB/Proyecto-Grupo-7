package ar.edu.uner.report;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;

public class InformeFallasMantenimiento {
    private GestionInfraestructura infraestructura;

    public InformeFallasMantenimiento
            (GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
    }

    public void generar() {
        System.out.println("INFORME DE FALLAS Y MANTENIMIENTO");
        System.out.println("---------------------------------");

        System.out.println("Sensores en mantenimiento o fuera de servicio: ");
        boolean condicion = false;
        for (Sensor s : infraestructura.getSensores()){
            if (s.getEstado() != EstadoOperativo.OPERATIVO) {
                mostrarSensor(s);
                condicion = true;
            }
        }

        if (!condicion) {
            System.out.println("ninguno");
        }

        System.out.println("Lecturas anomalas: ");
        condicion = false;
        for (Sensor s : infraestructura.getSensores()) {
            for (Medicion m : s.getMediciones()) {
                if (m.isAnomala()) {
                    System.out.println("Sensor: " + s.getIdSensor()
                    + " | " + m.getFechaHora() + " | "
                    + m.getMilimetros() + " mm");
                    condicion = true;
                }
            }
        }

        if (!condicion) {
            System.out.println("ninguna");
        }

        System.out.println("Sensores que requieren calibracion: ");
        condicion = false;
        for (Sensor s : infraestructura.getSensores()) {
            if (s.requiereCalibracion(null)) {
                mostrarSensor(s);
                condicion = true;
            }
        }

        if (!condicion) {
            System.out.println(" ninguno.");
        }
    }

    private void mostrarSensor(Sensor s) {
        EstacionMeteorologica e = infraestructura.buscarEstacionDeSensor(s);
        String ubicacion = "sin instalar";
        if (e != null) {
            ubicacion = "Estacion: " + e.getIdEstacion();
        }
        
        System.out.println(s.toString() + " | " + ubicacion);
    }
}
