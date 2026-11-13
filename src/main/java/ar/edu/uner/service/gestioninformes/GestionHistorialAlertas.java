package ar.edu.uner.service.gestioninformes;

import ar.edu.uner.domain.enums.NivelAlerta;
import ar.edu.uner.domain.model.Alerta;
import ar.edu.uner.report.HistorialAlertas;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.LectorFechas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static ar.edu.uner.service.UtilidadesMenu.leerOpcion;

/**
 * Se encarga de imprimir por consola los informes generados por
 * {@link HistorialAlertas}. La lógica de filtrado y ordenamiento
 * vive en {@code HistorialAlertas}; esta clase solo la invoca y
 * muestra el resultado.
 *
 * @author Nahuel
 */
public class GestionHistorialAlertas {

    private HistorialAlertas historialAlertas;
    private GestionInfraestructura infraestructura;
    private Scanner scanner;
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Crea la gestión del informe de historial de alertas.
     *
     * @param infraestructura Gestión de infraestructura, usada para listar
     *        las zonas.
     * @param historialAlertas Reporte que filtra y ordena las alertas.
     * @param scanner Scanner compartido para leer los datos del usuario.
     */
    public GestionHistorialAlertas(GestionInfraestructura infraestructura,
                                   HistorialAlertas historialAlertas,
                                   Scanner scanner) {
        this.infraestructura = infraestructura;
        this.historialAlertas = historialAlertas;
        this.scanner = scanner;
    }

    /**
     * Muestra el submenú del historial de alertas y ejecuta la opción
     * elegida hasta que el usuario vuelve al menú de informes.
     */
    public void mostrar() {
        int opcion;
        do {
            System.out.println();
            System.out.println("=== HISTORIAL DE ALERTAS ===");
            System.out.println("1. Ver historial ordenado por severidad");
            System.out.println("2. Filtrar por zona");
            System.out.println("3. Filtrar desde una fecha");
            System.out.println("4. Filtrar por nivel de alerta");
            System.out.println("5. Filtrar por rango de fechas");
            System.out.println("0. Volver");
            System.out.print("Ingrese una opcion: ");
            opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1: mostrarHistorialOrdenado(); break;
                case 2: mostrarAlertasPorZona();    break;
                case 3: mostrarAlertasDesdeFecha(); break;
                case 4: mostrarAlertasPorNivel();   break;
                case 5: mostrarAlertasPorRango();   break;
                case 0: break;
                default: System.out.println("Opcion invalida");
            }
        } while (opcion != 0);
    }

    /**
     * Imprime todas las alertas emitidas, ordenadas por severidad.
     */
    public void mostrarHistorialOrdenado() {
        List<Alerta> alertas = historialAlertas.obtenerHistorialOrdenado();
        imprimirLista(alertas, "HISTORIAL DE ALERTAS (por severidad)");
    }

    /**
     * Pide una zona al usuario e imprime las alertas de esa zona.
     */
    public void mostrarAlertasPorZona() {
        if (!mostrarZonas()) {
            return;
        }

        System.out.print("Ingrese el ID de la zona: ");
        int idZona = leerOpcion(scanner);

        List<Alerta> alertas = historialAlertas.obtenerAlertasPorZona(idZona);

        if (alertas == null) {
            System.out.println("No existe una zona con ese ID.");
            return;
        }

        imprimirLista(alertas, "ALERTAS DE LA ZONA ID " + idZona);
    }

    /**
     * Pide una fecha y hora al usuario e imprime las alertas emitidas
     * después de ese momento.
     */
    public void mostrarAlertasDesdeFecha() {
        System.out.println("Ingrese la fecha y hora de inicio del filtro:");
        LocalDateTime desde = LectorFechas.leerFechaHora(scanner);

        List<Alerta> alertas = historialAlertas.filtrarPorFecha(desde);
        imprimirLista(alertas, "ALERTAS POSTERIORES A " + desde.format(FORMATO));
    }

    /**
     * Pide un nivel de alerta al usuario e imprime las alertas de ese nivel.
     */
    public void mostrarAlertasPorNivel() {
        NivelAlerta nivel = null;
        int opcion;

        do {
            System.out.println("1 - Verde  2 - Amarilla  3 - Naranja  4 - Roja");
            System.out.print("Seleccione el nivel: ");
            opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1: nivel = NivelAlerta.VERDE;    break;
                case 2: nivel = NivelAlerta.AMARILLA; break;
                case 3: nivel = NivelAlerta.NARANJA;  break;
                case 4: nivel = NivelAlerta.ROJA;     break;
                default: System.out.println("Opcion invalida");
            }
        } while (nivel == null);

        List<Alerta> alertas = historialAlertas.filtrarPorNivel(nivel);
        imprimirLista(alertas, "ALERTAS DE NIVEL " + nivel);
    }

    /**
     * Pide una fecha de inicio y una de fin e imprime las alertas
     * emitidas dentro de ese rango (ambas fechas incluidas).
     */
    public void mostrarAlertasPorRango() {
        System.out.println("Ingrese la fecha y hora de INICIO del rango:");
        LocalDateTime desde = LectorFechas.leerFechaHora(scanner);
        System.out.println("Ingrese la fecha y hora de FIN del rango:");
        LocalDateTime hasta = LectorFechas.leerFechaHora(scanner);

        if (desde.isAfter(hasta)) {
            System.out.println("La fecha de inicio no puede ser posterior "
                    + "a la fecha de fin.");
            return;
        }

        List<Alerta> alertas = historialAlertas.filtrarPorRango(desde, hasta);
        imprimirLista(alertas, "ALERTAS ENTRE " + desde.format(FORMATO)
                + " Y " + hasta.format(FORMATO));
    }

    // ------------------------------------------------------------------
    // Auxiliares de impresión
    // ------------------------------------------------------------------

    private void imprimirLista(List<Alerta> alertas, String titulo) {
        System.out.println();
        System.out.println("===== " + titulo + " =====");

        if (alertas == null || alertas.isEmpty()) {
            System.out.println("No hay alertas para mostrar.");
            return;
        }

        for (Alerta alerta : alertas) {
            System.out.println("-----------------------------------------");
            System.out.println("Fecha y hora: "
                    + alerta.getFechaHoraEmision().format(FORMATO));
            System.out.println("Nivel:        " + alerta.getNivel());
            System.out.println("Tipo evento:  " + alerta.getTipo());
            System.out.println("Descripcion:  " + alerta.getDescripcion());
        }
        System.out.println("-----------------------------------------");
        System.out.println("Total: " + alertas.size() + " alerta(s).");
    }

    private boolean mostrarZonas() {
        if (infraestructura.getZonas().isEmpty()) {
            System.out.println("No hay zonas registradas.");
            return false;
        }

        for (var zona : infraestructura.getZonas()) {
            System.out.println("ID: " + zona.getIdZona()
                    + " - " + zona.getNombre());
        }
        return true;
    }
}