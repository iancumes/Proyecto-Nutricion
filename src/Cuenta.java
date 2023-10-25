import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private List<Usuario> usuarios;
    private String archivoUsuarios;
    private Usuario usuarioSesion;

    public Cuenta(String archivoUsuarios) {
        this.archivoUsuarios = archivoUsuarios;
        usuarios = new ArrayList<>();
        cargarUsuariosDesdeArchivo();
    }

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
    
    
    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }
}
