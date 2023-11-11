/**
 * Esta clase representa una actividad, con un nombre, duración y estado de completitud.
 * Permite gestionar y acceder a la información de la actividad.
 * @author Javier Valladares
 * @version 24-10-23 1.0.0
 */
public class Actividad {
    private String nombre;
    private int duracion;
    private boolean completada;

    /**
     * Constructor de la clase Actividad que inicializa una actividad con nombre y duración.
     *
     * @param nombre El nombre de la actividad.
     * @param duracion La duración de la actividad.
     */
    public Actividad(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.completada = false; // Por defecto, la actividad no está completada
    }

    /**
     * Constructor sobrecargado de la clase Actividad que permite establecer el estado de completitud.
     *
     * @param nombre El nombre de la actividad.
     * @param duracion La duración de la actividad.
     * @param completada true si la actividad está completada, false de lo contrario.
     */
    public Actividad(String nombre, int duracion, boolean completada) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.completada = completada;
    }

    /**
     * Obtiene el nombre de la actividad.
     *
     * @return El nombre de la actividad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la duración de la actividad.
     *
     * @return La duración de la actividad en horas.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Verifica si la actividad está completada.
     *
     * @return true si la actividad está completada, false de lo contrario.
     */
    public boolean isCompletada() {
        return completada;
    }

    /**
     * Marca la actividad como completada.
     */
    public void marcarComoCompletada() {
        completada = true;
    }
}
    