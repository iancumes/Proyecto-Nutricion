public abstract class ActividadBase implements Actividad {
    private String nombre;
    private int duracion;
    private boolean completada;
    private String tipo;

    public ActividadBase(String tipo, String nombre, int duracion, boolean completada) {
        this.tipo= tipo;
        this.nombre = nombre;
        this.duracion = duracion;
        this.completada = false;
    }

    // Implementación de métodos de la interfaz Actividad
    @Override
    public void realizar() {
        System.out.println("Realizando actividad: " + nombre);
        
    }

    @Override
    public void marcarComoCompletada() {
        this.completada = true;
        System.out.println("Actividad completada: " + nombre);
    }

    // Otros métodos y propiedades comunes a todas las actividades

    /**
     * Obtiene el nombre de la actividad.
     *
     * @return El nombre de la actividad.
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getDuracion() {
        return duracion;
    }

    @Override
    public boolean isCompletada() {
        return completada;
    }
    @Override
    public String getTipo() {
        return tipo;
    }

   
}
    