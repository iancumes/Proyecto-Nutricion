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
    private boolean esActividadEjercicio(String tipo) {
        // Implementa la lógica para determinar si el nombre indica una actividad de ejercicio
        return tipo.contains("Ejercicio");
    }
    private boolean esActividadEstudio(String tipo) {
        // Implementa la lógica para determinar si el nombre indica una actividad de ejercicio
        return tipo.contains("Estudio");
    }

    private boolean esActividadOcio(String tipo) {
        // Implementa la lógica para determinar si el nombre indica una actividad de ocio
        return tipo.contains("Ocio");
    }

    public void agregarActividad(Actividad actividad) {
        // Crear la actividad y agregarla a la lista
        actividades.add(actividad);

        // Guardar actividades en el archivo CSV del usuario
        guardarActividadesEnArchivo();
        System.out.println("Actividad agregada: " + "Tipo"+actividad.getTipo()+ "Nombre:" + actividad.getNombre() + " (Duración: " + actividad.getDuracion() + " horas)");
    }

    public void mostrarHorario() {
        System.out.println("Tareas del día:");
        for (Actividad actividad : actividades) {
            String estado = actividad.isCompletada() ? "Completada" : "Pendiente";
            System.out.println("Tipo: "+actividad.getTipo()+"Nombre: "+actividad.getNombre() + " (Duración: " + actividad.getDuracion() + " horas, Estado: " + estado + ")");
        }
    }

    public void verTareasDiarias() {
        System.out.println("Tareas Diarias:");
        for (Actividad actividad : actividades) {
            String estado = actividad.isCompletada() ? "Completada" : "Pendiente";
              System.out.println("Tipo: "+actividad.getTipo()+". -Nombre: "+actividad.getNombre() + " (Duración: " + actividad.getDuracion() + " horas, Estado: " + estado + ")");
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
                if (datos.length == 4) {
                    ActividadBase actividad;
                    String tipo = datos[0];
                    String nombre = datos[1];
                    int duracion = Integer.parseInt(datos[2]);
                    boolean completada = Boolean.parseBoolean(datos[3]);
                    if (esActividadEjercicio(tipo)) {
                        actividad = new ActividadEjercicio(tipo, nombre, duracion, completada);
                    } else if (esActividadOcio(tipo)) {
                        actividad = new ActividadOcio(tipo, nombre, duracion, completada);
                        
                    } else if (esActividadEstudio(tipo)) {
                        actividad = new ActividadEstudio(tipo, nombre, duracion, completada);
                        
                    } else {
                        throw new IllegalArgumentException("Tipo de actividad desconocido: " + tipo);
                    }
    
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
                String linea = actividad.getTipo()+ ","+ actividad.getNombre() + "," + actividad.getDuracion() + "," + actividad.isCompletada();
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
