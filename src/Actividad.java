// Interfaz para actividades
public interface Actividad {
    void realizar();
    void marcarComoCompletada();
    String getNombre();
    int getDuracion();
    boolean isCompletada();
    String getTipo();
}
