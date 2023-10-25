<<<<<<< HEAD
import java.io.*;
import java.util.ArrayList;
import java.util.List;

=======
/**
 * Esta clase representa una actividad, con un nombre, duración y estado de completitud.
 * Permite gestionar y acceder a la información de la actividad.
 * @author Javier Valladares
 * @version 24-10-23 1.0.0
 */
>>>>>>> 5fc7291e1d3f40bd5ce7cdb48aa72467c8196d26
public class Actividad {
    private String nombre;
    private int duracion;
    private boolean completada;

<<<<<<< HEAD
=======
    /**
     * Constructor de la clase Actividad que inicializa una actividad con nombre y duración.
     *
     * @param nombre El nombre de la actividad.
     * @param duracion La duración de la actividad.
     */
>>>>>>> 5fc7291e1d3f40bd5ce7cdb48aa72467c8196d26
    public Actividad(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.completada = false; // Por defecto, la actividad no está completada
    }

<<<<<<< HEAD
=======
    /**
     * Constructor sobrecargado de la clase Actividad que permite establecer el estado de completitud.
     *
     * @param nombre El nombre de la actividad.
     * @param duracion La duración de la actividad.
     * @param completada true si la actividad está completada, false de lo contrario.
     */
>>>>>>> 5fc7291e1d3f40bd5ce7cdb48aa72467c8196d26
    public Actividad(String nombre, int duracion, boolean completada) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.completada = completada;
    }

<<<<<<< HEAD
=======
    /**
     * Obtiene el nombre de la actividad.
     *
     * @return El nombre de la actividad.
     */
>>>>>>> 5fc7291e1d3f40bd5ce7cdb48aa72467c8196d26
    public String getNombre() {
        return nombre;
    }

<<<<<<< HEAD
=======
    /**
     * Obtiene la duración de la actividad.
     *
     * @return La duración de la actividad en horas.
     */
>>>>>>> 5fc7291e1d3f40bd5ce7cdb48aa72467c8196d26
    public int getDuracion() {
        return duracion;
    }

<<<<<<< HEAD
=======
    /**
     * Verifica si la actividad está completada.
     *
     * @return true si la actividad está completada, false de lo contrario.
     */
>>>>>>> 5fc7291e1d3f40bd5ce7cdb48aa72467c8196d26
    public boolean isCompletada() {
        return completada;
    }

<<<<<<< HEAD
=======
    /**
     * Marca la actividad como completada.
     */
>>>>>>> 5fc7291e1d3f40bd5ce7cdb48aa72467c8196d26
    public void marcarComoCompletada() {
        completada = true;
    }
}
