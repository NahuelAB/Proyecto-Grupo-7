package ar.edu.uner.report;

import ar.edu.uner.domain.model.EstacionMeteorologica;
import ar.edu.uner.domain.model.Medicion;
import ar.edu.uner.domain.model.Sensor;
import ar.edu.uner.domain.model.ValorHistorico;
import ar.edu.uner.domain.model.ZonaGeografica;

public class EstadisticaAnomaliaClimatica {

    public int cantidadMediciones(ZonaGeografica z, int anio, int mes) {
        int cantidad = 0;
        for (EstacionMeteorologica e : z.getEstacionesMeteorologicas()) {
            for (Sensor s : e.getSensores()) {
                for (Medicion m : s.getMediciones()) {
                    if (m.getFechaHora().getYear() == anio &&
                            m.getFechaHora().getMonthValue() == mes) {
                        cantidad++;
                    }
                }
            }
        }
        return cantidad;
    }

    public double acumuladoDelMes(ZonaGeografica z, int anio, int mes) {
        double acumulado = 0;
        for (EstacionMeteorologica e : z.getEstacionesMeteorologicas()) {
            for (Sensor s : e.getSensores()) {
                for (Medicion m : s.getMediciones()) {
                    if (m.getFechaHora().getYear() == anio 
                            && m.getFechaHora().getMonthValue() == mes) {
                        acumulado+= m.getMilimetros();
                    }
                }
            }
        }
        return acumulado;
    }

    public ValorHistorico buscarValorHistorico(ZonaGeografica z, int mes) {
        ValorHistorico encontrado = null;
        for (ValorHistorico v : z.getValoresHistoricos()) {
            if (v.getMes() == mes) {
                encontrado = v;
                break;
            }
        }
        return encontrado;
    }

    public double calcularDesvio(double acumulado, double media) {
        return (acumulado - media) / media * 100;
    }
}
