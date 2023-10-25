import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Rutinas {
    private Map<String, String> ejerciciosPorObjetivo;
    private Scanner scanner;
    private String objetivoUsuario;

    public Rutinas(String objetivoUsuario) {
        ejerciciosPorObjetivo = new HashMap<>();
        scanner = new Scanner(System.in);
        cargarEjerciciosDesdeCSV();
        this.objetivoUsuario = objetivoUsuario;
    }

    public void mostrarRutinasSegunObjetivo(String objetivoUsuario) {
        cargarEjerciciosDesdeCSV();
    
        if (ejerciciosPorObjetivo.containsKey(objetivoUsuario)) {
            System.out.println("Rutinas para el objetivo " + objetivoUsuario + ":");
    
            String rutinas = ejerciciosPorObjetivo.get(objetivoUsuario);
            String[] rutinasArray = rutinas.split(",");
    
            for (String rutina : rutinasArray) {
                System.out.println(rutina);
            }
        } else {
            System.out.println("No se encontraron rutinas recomendadas para el objetivo " + objetivoUsuario);
        }
    }

    private void cargarEjerciciosDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Rutinas.csv"))) {
            String line;
            reader.readLine(); // Leer la primera línea (encabezados) y descartarla
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String ejercicio = parts[0].trim();
                    String objetivo = parts[1].trim();
                    ejerciciosPorObjetivo.put(objetivo, ejercicio);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ejecutar() {
        System.out.println("Menú de Rutinas");
        while (true) {
            System.out.println("1. Ver Rutinas");
            System.out.println("2. Regresar al Menú Principal");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            if (opcion == 1) {
                mostrarRutinasSegunObjetivo(objetivoUsuario);
                System.out.println("Presiona Enter para regresar al Menú de Rutinas...");
                scanner.nextLine();
            } else if (opcion == 2) {
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
