package ar.edu.uner.report;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ValorHistorico;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.service.GestionInfraestructura;

public class EstadisticaAnomaliaClimatica {
    private GestionInfraestructura infraestructura;

    public EstadisticaAnomaliaClimatica
            (GestionInfraestructura infraestructura) {
        this.infraestructura = infraestructura;
    }

    public void generar(int idZona, int anio, int mes) {
        ZonaGeografica zona = infraestructura.buscarZona(idZona);
        System.out.println("ANOMALIA CLIMATICA | Zona " 
        + zona.getNombre() + " - " + mes + "/" + anio);

        double acumulado = 0;
        boolean condicion = false;
        for (EstacionMeteorologica e : zona.getEstacionesMeteorologicas()) {
            for (Sensor s : e.getSensores()) {
                for (Medicion m : s.getMediciones()) {
                    if (m.getFechaHora().getYear() == anio 
                            && m.getFechaHora().getMonthValue() == mes) {
                        acumulado = acumulado + m.getMilimetros();
                        condicion = true;
                    }
                }
            }
        }

        if (!condicion) {
            System.out.println("Sin mediciones registradas en ese mes.");
            return;
        }

        
        System.out.printf("Acumulado del mes: %.2f mm%n" + acumulado);
        ValorHistorico h = buscarValorHistorico(zona, mes);
        if (h == null) {
            System.out.println("No hay valor historico cargado para ese mes.");
        } else {
            double media = h.getAcumuladoMedio();
            double desvio = (acumulado - media) / media * 100;
            System.out.printf("Valor historico: %.2f mm%n" + media);
            System.out.printf("Desvio: %.2f %%%n" + desvio);
        }
    }

    private ValorHistorico buscarValorHistorico(ZonaGeografica z, int mes) {
        for (ValorHistorico v : z.getValoresHistoricos()) {
            if (v.getMes() == mes) {
                return v;
            }
        }

        return null;
    }
}
