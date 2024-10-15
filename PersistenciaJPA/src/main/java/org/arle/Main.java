package org.arle;

import org.arle.entity.Concierto; // Asegúrate de que la entidad Concierto esté en el paquete correcto
import org.arle.service.ConciertoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ConciertoService conciertoService = new ConciertoService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- CRUD de Conciertos ---");
            System.out.println("1. Crear concierto");
            System.out.println("2. Leer concierto");
            System.out.println("3. Actualizar concierto");
            System.out.println("4. Eliminar concierto");
            System.out.println("5. Listar todos los conciertos");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> crearConcierto();
                case 2 -> leerConcierto();
                case 3 -> actualizarConcierto();
                case 4 -> eliminarConcierto();
                case 5 -> listarConciertos();
                case 6 -> salir = true;
                default -> System.out.println("Opción no válida");
            }
        }
        conciertoService.cerrar();
        scanner.close();
    }

    private static void crearConcierto() {
        System.out.print("Nombre del concierto: ");
        String nombre = scanner.nextLine();
        System.out.print("Fecha del concierto (formato YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        Concierto concierto = new Concierto();
        concierto.setNombre(nombre);
        concierto.setFecha(fecha);

        conciertoService.crearConcierto(concierto);
        System.out.println("Concierto creado con éxito");
    }

    private static void leerConcierto() {
        System.out.print("ID del concierto: ");
        Long id = scanner.nextLong();
        Concierto concierto = conciertoService.obtenerConcierto(id);
        if (concierto != null) {
            System.out.println(concierto);
        } else {
            System.out.println("Concierto no encontrado");
        }
    }

    private static void actualizarConcierto() {
        System.out.print("ID del concierto a actualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir el salto de línea

        Concierto concierto = conciertoService.obtenerConcierto(id);
        if (concierto != null) {
            System.out.print("Nuevo nombre (deje en blanco para mantener el actual): ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) {
                concierto.setNombre(nombre);
            }

            System.out.print("Nueva fecha (deje en blanco para mantener el actual): ");
            String fecha = scanner.nextLine();
            if (!fecha.isEmpty()) {
                concierto.setFecha(fecha);
            }

            conciertoService.actualizarConcierto(concierto);
            System.out.println("Concierto actualizado con éxito");
        } else {
            System.out.println("Concierto no encontrado");
        }
    }

    private static void eliminarConcierto() {
        System.out.print("ID del concierto a eliminar: ");
        Long id = scanner.nextLong();
        conciertoService.eliminarConcierto(id);
        System.out.println("Concierto eliminado con éxito"); // Corrección en el mensaje
    }

    private static void listarConciertos() {
        List<Concierto> conciertos = conciertoService.obtenerTodosLosConciertos();
        if (conciertos.isEmpty()) {
            System.out.println("No hay conciertos registrados");
        } else {
            for (Concierto concierto : conciertos) {
                System.out.println(concierto);
            }
        }
    }
}
