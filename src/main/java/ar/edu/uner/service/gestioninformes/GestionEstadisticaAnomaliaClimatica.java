/*
 * GestionEstadisticaAnomaliaClimatica.java
 *
 * Version 1.0
 *
 * 13/11/2026
 *
 * Copyright (c) 2026 Grupo 7 - UNER
 */

package ar.edu.uner.service.gestioninformes;

import java.util.Scanner;
import ar.edu.uner.domain.model.ZonaGeografica;
import ar.edu.uner.domain.model.ValorHistorico;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.report.EstadisticaAnomaliaClimatica;


/**
 * Gestiona la interacción con el usuario para el informe de estadísticas de
 * anomalía climática: solicita la zona, el año y el mes, y muestra el
 * acumulado de precipitación del mes junto con su desvío porcentual respecto
 * de la media histórica de la zona.
 *
 * @author Guillermina
 */
public class GestionEstadisticaAnomaliaClimatica {
    private Scanner sc;
    private GestionInfraestructura infraestructura;
    private EstadisticaAnomaliaClimatica estadisticas;

    /**
     * Crea la gestión del informe con sus dependencias.
     *
     * @param sc Scanner con el que se leen los datos del usuario.
     * @param infraestructura Gestión de infraestructura donde se buscan las zonas.
     * @param estadisticas Informe que calcula acumulados y desvíos.
     */
    public GestionEstadisticaAnomaliaClimatica(Scanner sc, GestionInfraestructura infraestructura,
                                              EstadisticaAnomaliaClimatica estadisticas) {
        this.sc = sc;
        this.infraestructura = infraestructura;
        this.estadisticas = estadisticas;
    }

    /**
     * Solicita el ID de la zona, el año y el mes, y muestra por pantalla el
     * informe de anomalía climática. Informa el acumulado del mes, la media
     * histórica y el desvío porcentual. Si la zona no existe, si no hay
     * mediciones en el mes indicado o si no hay valor histórico cargado para
     * ese mes, muestra el mensaje correspondiente y no calcula el desvío.
     */
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
        } else if (historico.getAcumuladoMedio() <= 0) {
            System.out.println("El valor historico es 0: no se puede calcular el desvio.");
        } else {
            double media = historico.getAcumuladoMedio();
            double desvio = estadisticas.calcularDesvio(acumulado, media);
            System.out.printf("Media histórica: %.2f mm%n", media);
            System.out.printf("Desvío: %.2f %%%n", desvio);
        }
    }
}