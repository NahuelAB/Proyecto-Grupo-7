package ar.edu.uner.service.gestioninformes;

import ar.edu.uner.domain.model.Alerta;
import ar.edu.uner.report.HistorialAlertas;
import ar.edu.uner.service.GestionInfraestructura;
import ar.edu.uner.service.LectorFechas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

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

    public GestionHistorialAlertas(GestionInfraestructura infraestructura,
                                   HistorialAlertas historialAlertas,
                                   Scanner scanner) {
        this.infraestructura = infraestructura;
        this.historialAlertas = historialAlertas;
        this.scanner = scanner;
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
        int idZona = scanner.nextInt();
        scanner.nextLine();

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
        imprimirLista(alertas, "ALERTAS POSTERIORES A " + desde);
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
            System.out.println("Fecha y hora: " + alerta.getFechaHoraEmision());
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