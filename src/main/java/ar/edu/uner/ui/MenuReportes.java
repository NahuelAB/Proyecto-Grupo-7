package ar.edu.uner.ui;

import java.util.Scanner;
import ar.edu.uner.service.gestioninformes.*;
import static ar.edu.uner.service.UtilidadesMenu.leerOpcion;

public class MenuReportes implements Ejecutable {
    private GestionEstadisticaAnomaliaClimatica menuEstAnomCli;
    private GestionHistorialAlertas menuHistorialAlertas;
    private GestionInformeHistoricoPrecipitaciones menuInfHistPrec;
    private GestionInformeFallasMantenimiento menuInformeFallasMant;
    private GestionInformeFichaTecnica menuInformeFichaT;
    private GestionInformeVolumetria menuInformeVol;
    
    private Scanner sc;

    public MenuReportes(GestionEstadisticaAnomaliaClimatica menuEstAnomCli,
                        GestionHistorialAlertas menuHistorialAlertas,
                        GestionInformeFallasMantenimiento menuInformeFallasMant,
                        GestionInformeFichaTecnica menuInformeFichaT,
                        GestionInformeVolumetria menuInformeVol,
                        GestionInformeHistoricoPrecipitaciones menuInfHistPrec,
                        Scanner sc) {
    this.menuEstAnomCli = menuEstAnomCli;
    this.menuHistorialAlertas = menuHistorialAlertas;
    this.menuInformeFallasMant = menuInformeFallasMant;
    this.menuInformeFichaT = menuInformeFichaT;
    this.menuInformeVol = menuInformeVol;
    this.menuInfHistPrec = menuInfHistPrec;
    this.sc = sc;
    }

    @Override
    public void ejecutar() {
        int opcion;
        do {
            System.out.println("=== INFORMES ===");
            System.out.println("1. Historico de precipitaciones por estacion o zona");
            System.out.println("2. Ranking de estaciones por volumetria");
            System.out.println("3. Historial de alertas emitidas");
            System.out.println("4. Ficha tecnica operativa de una estacion");
            System.out.println("5. Fallas y mantenimiento");
            System.out.println("6. Estadisticas de anomalia climatica");
            System.out.println("0. Volver");
            System.out.print("Ingrese una opcion: ");
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1: 
                    menuInfHistPrec.mostrar();
                    break;

                case 2:
                    menuInformeVol.mostrar();
                    break;

                case 3:
                    menuHistorialAlertas.mostrar();
                    break;

                case 4:
                    menuInformeFichaT.mostrar();
                    break;

                case 5:
                    menuInformeFallasMant.mostrar();
                    break;

                case 6:
                    menuEstAnomCli.mostrar();
                    break;

                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

}
