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
}
