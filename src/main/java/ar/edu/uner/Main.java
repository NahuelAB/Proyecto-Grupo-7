package ar.edu.uner;

import java.util.Scanner;

import ar.edu.uner.report.EstadisticaAnomaliaClimatica;
import ar.edu.uner.report.FichaTecnica;
import ar.edu.uner.report.HistorialAlertas;
import ar.edu.uner.report.InformeFallasMantenimiento;
import ar.edu.uner.report.InformeHistoricoPrecipitaciones;
import ar.edu.uner.report.RankingVolumetria;
import ar.edu.uner.service.*;
import ar.edu.uner.service.gestioninformes.GestionEstadisticaAnomaliaClimatica;
import ar.edu.uner.service.gestioninformes.GestionHistorialAlertas;
import ar.edu.uner.service.gestioninformes.GestionInformeFallasMantenimiento;
import ar.edu.uner.service.gestioninformes.GestionInformeFichaTecnica;
import ar.edu.uner.service.gestioninformes.GestionInformeVolumetria;
import ar.edu.uner.ui.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. Instaciar los servicios centrales
        GestionInfraestructura gestorInfra = new GestionInfraestructura();
        GestionPersonal gestorPersonal = new GestionPersonal(gestorInfra);
        GestionSistemaAlerta gestorSistAlerta = new GestionSistemaAlerta(gestorInfra);

        //2. Informes
        EstadisticaAnomaliaClimatica estAnomClim = new EstadisticaAnomaliaClimatica();
        FichaTecnica fichaTec = new FichaTecnica(gestorInfra);
        HistorialAlertas histAlertas = new HistorialAlertas(gestorInfra);
        InformeFallasMantenimiento infFallasMant = new InformeFallasMantenimiento(gestorInfra);
        InformeHistoricoPrecipitaciones infHistPrec = new InformeHistoricoPrecipitaciones();
        RankingVolumetria rankingVol = new RankingVolumetria(gestorInfra);

        //3. Gestiones de informes
        GestionEstadisticaAnomaliaClimatica gestorInfEstAnomClim = new GestionEstadisticaAnomaliaClimatica(scanner, gestorInfra, estAnomClim);
        GestionInformeFichaTecnica gestorInfFichaTec = new GestionInformeFichaTecnica(gestorInfra);
        GestionHistorialAlertas gestorInfHistAlertas = new GestionHistorialAlertas(gestorInfra, histAlertas, scanner);
        GestionInformeFallasMantenimiento gestorInfFallasMant = new GestionInformeFallasMantenimiento(gestorInfra, infFallasMant);
        //GESTION DE HISTORICO PRECIPITACIONES
        GestionInformeVolumetria gestorInfVol = new GestionInformeVolumetria(gestorInfra);

        // 2. Cargar datos iniciales
        CargadorDatosPrueba.cargarDatosIniciales(gestorInfra, gestorPersonal);

        // 3. Crear e iniciar el menu principal
        MenuPrincipal menu = new MenuPrincipal(gestorInfra, gestorPersonal, gestorSistAlerta, gestorInfEstAnomClim, 
                                              gestorInfHistAlertas, gestorInfFallasMant, gestorInfFichaTec, gestorInfVol, scanner);
        
        menu.ejecutar();
    }
}
