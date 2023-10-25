import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Actividad {
    private String nombre;
    private int duracion;
    private boolean completada;

    public Actividad(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.completada = false; // Por defecto, la actividad no está completada
    }

    public Actividad(String nombre, int duracion, boolean completada) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.completada = completada;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void marcarComoCompletada() {
        completada = true;
    }
}
