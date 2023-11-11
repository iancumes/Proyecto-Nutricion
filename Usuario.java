import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * Esta clase representa a un usuario del sistema, almacenando información personal y de cuenta.
 *
 * @author Camila Richter
 * @version 24-10-23 1.0.0
 */
public class Usuario {
    private String nombreUsuario;
    private String contraseña;
    private String nombre;
    private int edad;
    private double peso;
    private double altura;
    private String objetivo;
    private List<Progreso> progresos;

    /**
     * Constructor de la clase Usuario que inicializa los atributos del usuario.
     *
     * @param nombreUsuario Nombre de usuario del usuario.
     * @param contraseña Contraseña del usuario.
     * @param nombre Nombre del usuario.
     * @param edad Edad del usuario.
     * @param peso Peso del usuario.
     * @param altura Altura del usuario.
     * @param objetivo Objetivo del usuario 
     */
    public Usuario(String nombreUsuario, String contraseña, String nombre, int edad, double peso, double altura, String objetivo) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.objetivo = objetivo;
        this.progresos = new ArrayList<>();
    }

    // Getters y setters

    /**
     * Obtiene el nombre de usuario del usuario.
     *
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void verEstadoActual() {
        double imc = calcularIMC();
        String estadoIMC = obtenerEstadoIMC(imc);

        System.out.println("Estado Actual:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad + " años");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Altura: " + altura + " cm");
        System.out.println("IMC: " + imc + " (" + estadoIMC + ")");
    }

    public void cambiarEstado(double nuevaAltura, double nuevoPeso) {
        // Guardar el cambio de estado
        guardarCambioEstado(nuevaAltura, nuevoPeso);

        // Actualizar los datos del usuario
        altura = nuevoPeso;
        peso = nuevaAltura;
        actualizarUsuarioEnArchivo();
        System.out.println("Cambio de estado realizado con éxito.");
    }

    public double calcularIMC() {
        // Implementación del cálculo del IMC
        double alturaEnMetros = altura / 100.0;
        return peso / (alturaEnMetros * alturaEnMetros);
    }

    public String obtenerEstadoIMC(double imc) {
        // Implementación para obtener el estado según el IMC
        if (imc < 18.5) {
            return "Bajo Peso";
        } else if (imc < 25) {
            return "Normal";
        } else if (imc < 30) {
            return "Sobre Peso";
        } else if (imc < 35) {
            return "Obesidad 1";
        } else if (imc < 40) {
            return "Obesidad 2";
        } else {
            return "Obesidad 3";
        }
    }

    public void guardarCambioEstado(double nuevoPeso, double nuevaAltura) {
        String nombreArchivo = getNombreArchivoProgreso();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Formatear la fecha y escribir los datos en el archivo
            String linea = fechaActual.format(formatter) + "," + nuevoPeso + "," + nuevaAltura;
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNombreArchivoProgreso() {
        return nombreUsuario + "_progreso.csv";
    }

    public void cargarProgreso() {
        String nombreArchivo = getNombreArchivoProgreso();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    LocalDate fecha = LocalDate.parse(partes[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    double peso = Double.parseDouble(partes[1]);
                    double altura = Double.parseDouble(partes[2]);
                    
                    // Puedes hacer algo con los datos cargados, por ejemplo, almacenarlos en una lista
                    // o realizar algún otro tipo de procesamiento según tus necesidades.
                }
            }
        } catch (IOException e) {
            // El archivo aún no existe, lo cual es normal
        }
    }

    /**
     * Establece el nombre de usuario del usuario.
     *
     * @param nombreUsuario El nuevo nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contraseña La nueva contraseña.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la edad del usuario.
     *
     * @return La edad del usuario.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario.
     *
     * @param edad La nueva edad del usuario.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el peso del usuario.
     *
     * @return El peso del usuario.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso del usuario.
     *
     * @param peso El nuevo peso del usuario.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene la altura del usuario.
     *
     * @return La altura del usuario.
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Establece la altura del usuario.
     *
     * @param altura La nueva altura del usuario.
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * Obtiene el objetivo del usuario.
     *
     * @return El objetivo del usuario.
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * Establece el objetivo del usuario.
     *
     * @param objetivo El nuevo objetivo del usuario.
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    private void actualizarUsuarioEnArchivo() {
        String nombreArchivo = "usuarios.csv";
        String nombreArchivoTemporal = "temporal.csv";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
             BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivoTemporal))) {
    
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 7 && partes[0].equals(nombreUsuario)) {
                    // Reemplazar la línea con la información actualizada
                    String lineaActualizada = String.join(",", nombreUsuario, contraseña, nombre,
                            String.valueOf(edad), String.valueOf(peso), String.valueOf(altura), objetivo);
                    writer.write(lineaActualizada);
                } else {
                    // Mantener las líneas de otros usuarios intactas
                    writer.write(linea);
                }
                writer.newLine();
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Reemplazar el archivo original con el archivo temporal
        File archivoOriginal = new File(nombreArchivo);
        File archivoTemporal = new File(nombreArchivoTemporal);
        if (archivoOriginal.delete()) {
            // Renombrar el archivo temporal
            archivoTemporal.renameTo(archivoOriginal);
        } else {
            System.out.println("Error al eliminar el archivo original.");
        }
    }
}
