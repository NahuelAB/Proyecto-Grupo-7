package ar.edu.uner.service.gestioninformes;

import java.util.List;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.report.InformeFallasMantenimiento;
import ar.edu.uner.service.GestionInfraestructura;


public class GestionInformeFallasMantenimiento {

    private GestionInfraestructura infraestructura;
    private InformeFallasMantenimiento informe;

    public GestionInformeFallasMantenimiento(GestionInfraestructura infraestructura, 
                                            InformeFallasMantenimiento informe) {
        this.infraestructura = infraestructura;
        this.informe = informe;
    }

    public void mostrar() {
        System.out.println("INFORME DE FALLAS Y MANTENIMIENTO");
        System.out.println("---------------------------------");

        System.out.println("Sensores no operativos: ");
        List<Sensor> sensoresNoOp = informe.sensoresNoOperativos();
        if (sensoresNoOp.isEmpty()) {
            System.out.println("ninguno.");
        } else {
            for (Sensor s : sensoresNoOp) {
                mostrarSensor(s);
            }
        }

        System.out.println("Lecturas anómalas: ");
        boolean condicion = false;
        for (Sensor s : infraestructura.getSensores()) {
            List<Medicion> anomalas = informe.lecturasAnomalas(s);
            if (!anomalas.isEmpty()) {
                condicion = true;
                System.out.println("Sensor " + s.getIdSensor());
                for (Medicion m : anomalas) {
                    System.out.println(m.getFechaHora() + " | " + m.getMilimetros() + " mm");
                }
            }
        }
        if (!condicion) {
            System.out.println("ninguna.");
        }

        System.out.println("Sensores que requieren calibración: ");
        List<Sensor> paraCalibrar = informe.sensoresParaCalibrar();
        if (paraCalibrar.isEmpty()) {
            System.out.println("ninguno.");
        } else { 
            for (Sensor s : paraCalibrar) {
                mostrarSensor(s);
            }
        }
    }

    private void mostrarSensor(Sensor s) {
        EstacionMeteorologica e = infraestructura.buscarEstacionDeSensor(s);
        String ubicacion;
        if (e == null) {
            ubicacion = "sin instalar";
        } else {
            ubicacion = "estación " + e.getNombre();
        }
        System.out.println(s.toString() + " | Ubicacion: " + ubicacion);
    }
}
