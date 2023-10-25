import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase gestiona cuentas de usuario, incluyendo el registro e inicio de sesión.
 * Además, permite cargar y guardar información de usuario en un archivo CSV.
 * @author Camila Richter 
 * @version 24-10-23
 */
public class Cuenta {
    private List<Usuario> usuarios;
    private String archivoUsuarios;
    private Usuario usuarioSesion;

    /**
     * Constructor de la clase Cuenta que inicializa la lista de usuarios y carga usuarios desde un archivo CSV.
     *
     * @param archivoUsuarios La ubicación del archivo CSV que contiene los datos de los usuarios.
     */
    public Cuenta(String archivoUsuarios) {
        this.archivoUsuarios = archivoUsuarios;
        usuarios = new ArrayList<>();
        cargarUsuariosDesdeArchivo();
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param nombreUsuario El nombre de usuario del nuevo usuario.
     * @param contraseña La contraseña del nuevo usuario.
     * @param nombre El nombre del nuevo usuario.
     * @param edad La edad del nuevo usuario.
     * @param peso El peso del nuevo usuario.
     * @param altura La altura del nuevo usuario.
     * @param objetivo El objetivo del nuevo usuario (bajar, mantener, subir de peso, etc.).
     * @return true si el registro es exitoso, false si el nombre de usuario ya está en uso.
     */
    public boolean registrarUsuario(String nombreUsuario, String contraseña, String nombre, int edad, double peso, double altura, String objetivo) {
        // Verificar si el nombre de usuario ya existe
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return false; // El nombre de usuario ya está en uso
            }
        }

        Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña, nombre, edad, peso, altura, objetivo);
        usuarios.add(nuevoUsuario);
        guardarUsuariosEnArchivo();
        return true;
    }

    /**
     * Inicia sesión para un usuario con un nombre de usuario y una contraseña.
     *
     * @param nombreUsuario El nombre de usuario del usuario que intenta iniciar sesión.
     * @param contraseña La contraseña del usuario que intenta iniciar sesión.
     * @return true si el inicio de sesión es exitoso, false si el usuario no existe o la contraseña es incorrecta.
     */
    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario) && usuario.getContraseña().equals(contraseña)) {
                // Usuario y contraseña coinciden, inicio de sesión exitoso
                usuarioSesion = usuario;
                return true;
            }
        }
        return false; // Inicio de sesión fallido
    }

    /**
     * Carga los usuarios desde un archivo CSV.
     */
    private void cargarUsuariosDesdeArchivo() {
        usuarios.clear(); // Limpiar la lista actual para evitar duplicados

        try (CSVReader reader = new CSVReader(new FileReader(archivoUsuarios))) {
            String[] linea;
            reader.skip(1); // Saltar la primera línea (encabezados)
            while ((linea = reader.readNext()) != null) {
                if (linea.length == 7) { // Ajustar según el número de columnas esperado
                    String nombreUsuario = linea[0];
                    String contraseña = linea[1];
                    String nombre = linea[2];
                    int edad = Integer.parseInt(linea[3]);
                    double peso = Double.parseDouble(linea[4]);
                    double altura = Double.parseDouble(linea[5]);
                    String objetivo = linea[6];

                    Usuario usuario = new Usuario(nombreUsuario, contraseña, nombre, edad, peso, altura, objetivo);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            // Manejar la excepción de lectura de archivo
            System.out.println("Error1");
        } catch (CsvValidationException e) {
            System.out.println("Error2");
        }
    }

    /**
     * Guarda los usuarios en un archivo CSV.
     */
    private void guardarUsuariosEnArchivo() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivoUsuarios, true))) {
            File archivoCSV = new File(archivoUsuarios);
            boolean archivoExiste = archivoCSV.exists();
            // Verifica si el archivo existe y está vacío para agregar encabezados
            if (!archivoExiste || archivoCSV.length() == 0) {
                String[] encabezadosEsperados = {"Usuario", "Contrasena", "Nombre", "Edad", "Peso", "Altura", "Objetivo"};
                writer.writeNext(encabezadosEsperados);
            }

            // Obtén el índice del usuario recién registrado
            int indiceNuevoUsuario = usuarios.size() - 1;

            if (indiceNuevoUsuario >= 0) {
                Usuario usuario = usuarios.get(indiceNuevoUsuario);
                String[] linea = {usuario.getNombreUsuario(), usuario.getContraseña(), usuario.getNombre(),
                        String.valueOf(usuario.getEdad()), String.valueOf(usuario.getPeso()),
                        String.valueOf(usuario.getAltura()), usuario.getObjetivo()};
                writer.writeNext(linea);
            }
        } catch (IOException e) {
            // Manejar la excepción de escritura de archivo
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el usuario en sesión.
     *
     * @return El usuario en sesión o null si no hay sesión activa.
     */
    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }
}
