package ar.edu.uner.report;

import java.util.ArrayList;
import java.util.List;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;


public class InformeFallasMantenimiento {
    private GestionInfraestructura infraestructura;

    public InformeFallasMantenimiento
            (GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
    }

    public List<Sensor> sensoresNoOperativos() {
        List<Sensor> resultado = new ArrayList<Sensor>();
        for (Sensor s : infraestructura.getSensores()) {
            if (s.getEstado() != EstadoOperativo.OPERATIVO) {
                resultado.add(s);
            }
        }
        return resultado;
    }

    public List<Medicion> lecturasAnomalas(Sensor s) {
        List<Medicion> resultado = new ArrayList<Medicion>();
        for (Medicion m : s.getMediciones()) {
            if (m.isAnomala()) {
                resultado.add(m);
            }
        }
        return resultado;
    } 

    public List<Sensor> sensoresParaCalibrar() {
        List<Sensor> resultado = new ArrayList<Sensor>();
        for (Sensor s : infraestructura.getSensores()) {
            if (s.requiereCalibracion(null)) {
                resultado.add(s);
            }
        }
        return resultado;
    }
}
