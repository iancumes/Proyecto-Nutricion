import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Esta clase representa un conjunto de rutinas de ejercicio que se pueden mostrar y gestionar según el objetivo del usuario.
 *
 * @author Juan Viau
 * @version 24-10-23 1.0.0
 */
public class Rutinas {
    private Map<String, List<String>> ejerciciosPorObjetivo;
    private Scanner scanner;
    private String objetivoUsuario;

    /**
     * Constructor de la clase Rutinas que inicializa las variables y carga las rutinas desde un archivo CSV.
     *
     * @param objetivoUsuario El objetivo del usuario para el cual se mostrarán las rutinas.
     */
    public Rutinas(String objetivoUsuario) {
        ejerciciosPorObjetivo = new HashMap<>();
        scanner = new Scanner(System.in);
        cargarEjerciciosDesdeCSV();
        this.objetivoUsuario = objetivoUsuario;
    }

    /**
     * Muestra las rutinas de ejercicio según el objetivo del usuario.
     *
     * @param objetivoUsuario El objetivo del usuario.
     */
    public void mostrarRutinasSegunObjetivo(String objetivoUsuario) {
        //cargarEjerciciosDesdeCSV();

        if (ejerciciosPorObjetivo.containsKey(objetivoUsuario)) {
            System.out.println("Rutinas para el objetivo " + objetivoUsuario + ":");
            
            List<String> rutinas = ejerciciosPorObjetivo.get(objetivoUsuario);
            for (String rutina : rutinas) {
                System.out.println(rutina);
            }
        } else {
            System.out.println("No se encontraron rutinas recomendadas para el objetivo " + objetivoUsuario);
        }
    }

    /**
     * Carga ejercicios desde un archivo CSV y almacena las rutinas en un mapa.
     */
       private void cargarEjerciciosDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Rutinas.csv"))) {
            String line;
            reader.readLine(); // Leer la primera línea (encabezados) y descartarla
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String ejercicio = parts[0].trim();
                    String objetivo = parts[1].trim();
                    
                    // Si el objetivo ya está en el mapa, agregamos el ejercicio a la lista existente
                    if (ejerciciosPorObjetivo.containsKey(objetivo)) {
                        ejerciciosPorObjetivo.get(objetivo).add(ejercicio);
                    } else {
                        // Si no, creamos una nueva lista con el ejercicio y la asociamos al objetivo
                        List<String> nuevaLista = new ArrayList<>();
                        nuevaLista.add(ejercicio);
                        ejerciciosPorObjetivo.put(objetivo, nuevaLista);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Ejecuta el menú de rutinas, permitiendo al usuario ver las rutinas disponibles según su objetivo.
     */
    public void ejecutar() {
        System.out.println("Menú de Rutinas");
        while (true) {
            System.out.println("1. Ver Rutinas");
            System.out.println("2. Regresar al Menú Principal");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

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
