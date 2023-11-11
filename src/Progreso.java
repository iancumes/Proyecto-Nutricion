import java.time.LocalDate;

class Progreso {
    private LocalDate fecha;
    private double altura;
    private double peso;
    private int dias;

    public Progreso(LocalDate fecha, double altura, double peso, int dias) {
        this.fecha = fecha;
        this.altura = altura;
        this.peso = peso;
        this.dias = dias;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    
}