package ar.edu.uner.service.gestioninformes;

import java.util.Scanner;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.domain.model.ValorHistorico;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.report.EstadisticaAnomaliaClimatica;


public class GestionEstadisticaAnomaliaClimatica {
    private Scanner sc;
    private GestionInfraestructura infraestructura;
    private EstadisticaAnomaliaClimatica estadisticas;

    public GestionEstadisticaAnomaliaClimatica(Scanner sc, GestionInfraestructura infraestructura,
                                              EstadisticaAnomaliaClimatica estadisticas) {
        this.sc = sc;
        this.infraestructura = infraestructura;
        this.estadisticas = estadisticas;
    }

    public void mostrar() {
        System.out.print("ID de la zona: ");
        int idZona = sc.nextInt();
        System.out.print("Año: ");
        int anio = sc.nextInt();
        System.out.print("Mes (1-12): ");
        int mes = sc.nextInt();

        ZonaGeografica zona = infraestructura.buscarZona(idZona);
        if (zona == null) {
            System.out.println("No existe una zona con ese ID.");
            return;
        }

        System.out.println("ANOMALÍA CLIMÁTICA - Zona " + zona.getNombre() + " - " + mes + "/" + anio);
        System.out.println("-------------------------------------------------------");

        if (estadisticas.cantidadMediciones(zona, anio, mes) == 0) {
            System.out.println("Sin mediciones registradas en ese mes.");
            return;
        }

        double acumulado = estadisticas.acumuladoDelMes(zona, anio, mes);
        System.out.printf("Acumulado del mes: %.2f mm%n", acumulado);

        ValorHistorico historico = estadisticas.buscarValorHistorico(zona, mes);
        if (historico == null) {
            System.out.println("No hay valor histórico cargado para ese mes.");
        } else {
            double media = historico.getAcumuladoMedio();
            double desvio = estadisticas.calcularDesvio(acumulado, media);
            System.out.printf("Media histórica: %.2f mm%n", media);
            System.out.printf("Desvío: %.2f %%%n", desvio);
        }
    }
}