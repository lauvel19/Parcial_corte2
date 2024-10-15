package org.example.aplication;

import org.example.aplication.service.ConciertoService;
import org.example.aplication.service.ConciertoServiceImpl;
import org.example.domain.Concierto;
import org.example.infraestructure.repository.ConciertoRepositoryImpl;
import org.example.interfaces.ConciertoRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ConciertoService conciertoService;

    static {
        ConciertoRepository conciertoRepository = new ConciertoRepositoryImpl();
        conciertoService = new ConciertoServiceImpl(conciertoRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Listar conciertos");
            System.out.println("2. Crear concierto");
            System.out.println("3. Actualizar concierto");
            System.out.println("4. Eliminar concierto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    listarConciertos();
                    break;
                case 2:
                    crearConcierto();
                    break;
                case 3:
                    actualizarConcierto();
                    break;
                case 4:
                    eliminarConcierto();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void listarConciertos() {
        List<Concierto> conciertos = conciertoService.findAll();
        if (conciertos.isEmpty()) {
            System.out.println("No hay conciertos registrados.");
        } else {
            System.out.println("Listado de conciertos:");
            for (Concierto concierto : conciertos) {
                System.out.println(concierto);
            }
        }
    }

    private static void crearConcierto() {
        System.out.print("Ingrese el código del concierto: ");
        int cod = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el nombre del concierto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la fecha del concierto (formato YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        Concierto concierto = new Concierto();
        concierto.setId(cod);
        concierto.setNombre(nombre);
        concierto.setFecha(fecha);

        try {
            conciertoService.save(concierto);
            System.out.println("Concierto creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarConcierto() {
        System.out.print("Ingrese el ID del concierto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Concierto concierto = conciertoService.findById(id);
        if (concierto == null) {
            System.out.println("No se encontró el concierto con ID " + id);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del concierto (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            concierto.setNombre(nombre);
        }

        System.out.print("Ingrese la nueva fecha del concierto (dejar en blanco para no cambiar): ");
        String fecha = scanner.nextLine();
        if (!fecha.isEmpty()) {
            concierto.setFecha(fecha);
        }

        try {
            conciertoService.update(concierto);
            System.out.println("Concierto actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarConcierto() {
        System.out.print("Ingrese el ID del concierto a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Concierto concierto = conciertoService.findById(id);
        if (concierto == null) {
            System.out.println("No se encontró el concierto con ID " + id);
            return;
        }

        conciertoService.delete(id);
        System.out.println("Concierto eliminado exitosamente.");
    }
}