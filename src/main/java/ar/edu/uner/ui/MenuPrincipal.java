/*
 * MenuPrincipal.java
 *
 * Version 1.0
 *
 * 08/11/2026
 *
 * Copyright (c) UNER - Taller de Programación II
 */

package ar.edu.uner.ui;

import ar.edu.uner.service.*;
import ar.edu.uner.service.gestioninformes.*;

import java.util.Scanner;

import static ar.edu.uner.service.UtilidadesMenu.leerOpcion;

/**
 * Menú principal de la interfaz de usuario por consola del sistema
 * meteorológico. Coordina la navegación hacia los distintos módulos
 * de gestión, operación y reportes.
 *
 * @author Nahuel
 */
public class MenuPrincipal implements Ejecutable {
    private GestionInfraestructura gestorInfraestructura;
    private GestionPersonal gestorPersonal;
    private GestionSistemaAlerta gestorSistemaAlerta;
    private GestionEstadisticaAnomaliaClimatica gestorInfEstAnomClim;
    private GestionHistorialAlertas gestorInfHistAlerta;
    private GestionInformeFallasMantenimiento gestorInfFallasMant;
    private GestionInformeFichaTecnica gestorInfFichaTec;
    private GestionInformeVolumetria gestorInfVol;
    private Scanner scanner;

    /**
     * Construye el menú principal inicializando las referencias a los
     * servicios centrales del sistema y el lector de entrada estándar.
     *
     * @param gestorInfraestructura gestor central de zonas, estaciones y sensores
     * @param gestorPersonal        gestor de responsables técnicos y equipos de respuesta
     * @param gestorSistemaAlerta   gestor del sistema de alertas tempranas
     */
    public MenuPrincipal(GestionInfraestructura gestorInfraestructura,
                         GestionPersonal gestorPersonal,
                        GestionSistemaAlerta gestorSistemaAlerta,
                        GestionEstadisticaAnomaliaClimatica gestorInfEstAnomClim,
                        GestionHistorialAlertas gestorInfHistAlerta,
                        GestionInformeFallasMantenimiento gestorInfFallasMant,
                        GestionInformeFichaTecnica gestorInfFichaTec,
                        GestionInformeVolumetria gestorInfVol, Scanner scanner) {
        this.gestorInfraestructura = gestorInfraestructura;
        this.gestorPersonal = gestorPersonal;
        this.gestorSistemaAlerta = gestorSistemaAlerta;
        this.gestorInfEstAnomClim = gestorInfEstAnomClim;
        this.gestorInfFallasMant = gestorInfFallasMant;
        this.gestorInfFichaTec = gestorInfFichaTec;
        this.gestorInfVol = gestorInfVol;
        this.scanner = scanner;
    }

    /**
     * Despliega por consola las opciones principales disponibles para acceder
     * a cada módulo del sistema meteorológico.
     */
    public void mostrarOpciones() {
        System.out.println();
        System.out.println("===== SISTEMA METEOROLOGICO =====");
        System.out.println("1. Infraestructura");
        System.out.println("2. Personal técnico");
        System.out.println("3. Mediciones");
        System.out.println("4. Alertas");
        System.out.println("5. Reportes");
        System.out.println("0. Salir");
    }

    /**
     * Inicia el ciclo principal de interacción del menú por consola,
     * delegando la ejecución al submenú seleccionado hasta que el usuario
     * elige la opción de salida.
     */
    @Override
    public void ejecutar(){
        int opcion;

        do {
            mostrarOpciones();
            System.out.println("Ingrese una opción: ");
            opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    MenuInfraestructura menuInfra = new MenuInfraestructura(gestorInfraestructura, scanner);
                    menuInfra.ejecutar();
                    break;
                case 2:
                    MenuPersonalTecnico menuPersonal = new MenuPersonalTecnico(gestorPersonal);
                    menuPersonal.ejecutar();
                    break;
                case 3:
                    MenuMediciones menuMediciones = new MenuMediciones(gestorInfraestructura, scanner);
                    menuMediciones.ejecutar();
                    break;
                case 4:
                    MenuSistemaAlerta menuAlertas = new MenuSistemaAlerta(gestorSistemaAlerta, gestorPersonal, gestorInfraestructura, scanner);
                    menuAlertas.ejecutar();
                    break;
                case 5:
                    MenuReportes menuReportes = new MenuReportes(gestorInfEstAnomClim, gestorInfHistAlerta, gestorInfFallasMant,
                                                                gestorInfFichaTec, gestorInfVol, scanner);
                    menuReportes.ejecutar();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        } while (opcion != 0);
    }
}
