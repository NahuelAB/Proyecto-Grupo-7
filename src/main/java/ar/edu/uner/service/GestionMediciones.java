package ar.edu.uner.service;

import java.time.LocalDateTime;

import ar.edu.uner.domain.enums.EstadoOperativo;
import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;

public class GestionMediciones {
    private GestionInfraestructura infraestructura;

    public GestionMediciones(GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
                            }
    

    public Medicion registrarLectura(int idSensor, double mm, LocalDateTime fh) {
        Sensor s = infraestructura.buscarSensor(idSensor);
        EstacionMeteorologica e = infraestructura.buscarEstacionDeSensor(s);
        Medicion m = null;
        
        if (e!=null
            && e.estaOperativa()
            && (s.getEstado() == EstadoOperativo.OPERATIVO)
            && s.validar(mm)) {
                m = s.registrar(mm, fh);
        }
        
        return m;
    }

    
}
