import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DietaGenerator {
    private Map<String, Map<String, Integer>> alimentosPorTipo;
    private Scanner scanner;
    private Usuario usuarioSesion; // Agregar una referencia al usuario que ha iniciado sesión

    public DietaGenerator(Usuario usuarioSesion) {
        alimentosPorTipo = new HashMap<>();
        scanner = new Scanner(System.in);
        this.usuarioSesion = usuarioSesion;
    }

    public void consultarAlimentosPorTipo() {
        cargarAlimentosDesdeCSV();

        while (true) {
            System.out.println("Tipos de Alimentos Disponibles:");
            for (String tipo : alimentosPorTipo.keySet()) {
                System.out.println(tipo);
            }

            System.out.print("Elija un tipo de alimento (o 'regresar' para volver al menú principal): ");
            String tipoElegido = scanner.nextLine();

            if (tipoElegido.equalsIgnoreCase("regresar")) {
                break; // Regresar al menú principal
            }

            if (alimentosPorTipo.containsKey(tipoElegido)) {
                System.out.println("Alimentos de tipo " + tipoElegido + ": Por cada 100g");
                Map<String, Integer> alimentos = alimentosPorTipo.get(tipoElegido);
                for (Map.Entry<String, Integer> entry : alimentos.entrySet()) {
                    System.out.println(entry.getKey() + " - " + entry.getValue() + " calorias");
                }
            } else {
                System.out.println("Tipo de alimento no encontrado.");
            }
        }
    }

    private void cargarAlimentosDesdeCSV() {
        alimentosPorTipo.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("alimentos.csv"))) {
            String line;
            reader.readLine(); // Leer la primera línea (encabezados) y descartarla
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nombre = parts[0].trim();
                    String tipo = parts[2].trim();
                    int calorias = Integer.parseInt(parts[1].trim());
                    alimentosPorTipo.putIfAbsent(tipo, new HashMap<>());
                    alimentosPorTipo.get(tipo).put(nombre, calorias);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calcularCaloriasDiarias() {
        String objetivo = usuarioSesion.getObjetivo();
        double peso = usuarioSesion.getPeso();
        double caloriasDiarias = 0;

        if (objetivo.equalsIgnoreCase("bajar")) {
            caloriasDiarias = peso * 25;
        } else if (objetivo.equalsIgnoreCase("mantener")) {
            caloriasDiarias = peso * 30;
        } else if (objetivo.equalsIgnoreCase("subir")) {
            caloriasDiarias = peso * 36;
        }

        System.out.println("Según tu objetivo: " + objetivo + ", las calorías que debes consumir son: " + caloriasDiarias + " calorias.");
    }

    public void ejecutar() {
        System.out.println("Bienvenido al Generador de Dietas");

        // Calcular las calorías diarias antes de mostrar las opciones de alimentos
        calcularCaloriasDiarias();

        // Consultar alimentos por tipo
        consultarAlimentosPorTipo();
    }
}
