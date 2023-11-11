import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Horario {
    private List<Actividad> actividades;
    private int horasDeSueno;
    private int horaDeLevantarse;
    private String usuarioEnSesion; // Nombre de usuario en sesión

    public Horario(int horasDeSueno, String usuarioEnSesion) {
        this.horasDeSueno = horasDeSueno;
        this.usuarioEnSesion = usuarioEnSesion;
        actividades = new ArrayList<>();
        cargarActividadesDesdeArchivo(); // Cargar actividades al inicializar
        // Verificar si el archivo de actividades existe, y si no, crearlo
        verificarYCrearArchivoDeActividades();
    }

    public void definirHoraDeLevantarse(int horaDeLevantarse) {
        this.horaDeLevantarse = horaDeLevantarse;
    }

    public void agregarActividad(String nombre, int duracion) {
        // Crear la actividad y agregarla a la lista
        Actividad actividad = new Actividad(nombre, duracion);
        actividades.add(actividad);

        // Guardar actividades en el archivo CSV del usuario
        guardarActividadesEnArchivo();
        System.out.println("Actividad agregada: " + nombre + " (Duración: " + duracion + " horas)");
    }

    public void mostrarHorario() {
        System.out.println("Tareas del día:");
        for (Actividad actividad : actividades) {
            String estado = actividad.isCompletada() ? "Completada" : "Pendiente";
            System.out.println(actividad.getNombre() + " (Duración: " + actividad.getDuracion() + " horas, Estado: " + estado + ")");
        }
    }

    public void verTareasDiarias() {
        System.out.println("Tareas Diarias:");
        for (Actividad actividad : actividades) {
            String estado = actividad.isCompletada() ? "Completada" : "Pendiente";
            System.out.println(actividad.getNombre() + " (Duración: " + actividad.getDuracion() + " horas, Estado: " + estado + ")");
        }
    }

    public void marcarActividadComoCompletada(String nombre) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombre)) {
                actividad.marcarComoCompletada();
                System.out.println("Actividad marcada como completada: " + nombre);
                eliminarActividadDelArchivo(actividad);
                return;
            }
        }
        System.out.println("No se encontró la actividad: " + nombre);
    }

    public void eliminarActividadDelArchivo(Actividad actividad) {
        actividades.remove(actividad);
        guardarActividadesEnArchivo(); // Guardar actividades actualizadas
    }

    private void cargarActividadesDesdeArchivo() {
        String archivoActividades = usuarioEnSesion + "_actividades.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoActividades))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) { // Ajustar según el número de columnas esperado
                    String nombre = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    boolean completada = Boolean.parseBoolean(datos[2]);
                    Actividad actividad = new Actividad(nombre, duracion, completada);
                    actividades.add(actividad);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarActividadesEnArchivo() {
        String archivoActividades = usuarioEnSesion + "_actividades.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoActividades))) {
            for (Actividad actividad : actividades) {
                String linea = actividad.getNombre() + "," + actividad.getDuracion() + "," + actividad.isCompletada();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void verificarYCrearArchivoDeActividades() {
        String archivoActividades = usuarioEnSesion + "_actividades.csv";

        File file = new File(archivoActividades);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No se pudo crear el archivo de actividades.");
            }
        }
    }
}
