import java.util.List;
import java.util.Scanner;

public class TerminalApp {
    private Cuenta cuenta;
    private Scanner scanner;

    public TerminalApp() {
        cuenta = new Cuenta("usuarios.csv");
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Bienvenido al Sistema de Registro e Inicio de Sesión");

        while (true) {
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void registrarUsuario() {
        System.out.println("Registro de Nuevo Usuario");

        System.out.print("Nombre de Usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        System.out.print("Peso (kg): ");
        double peso = scanner.nextDouble();
        System.out.print("Altura (cm): ");
        double altura = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea
        System.out.print("Objetivo: ");
        String objetivo = scanner.nextLine();

        boolean registroSesionExitoso = cuenta.registrarUsuario(nombreUsuario, contraseña, nombre, edad, peso, altura, objetivo);

        if (registroSesionExitoso) {
            System.out.println("Registro de Sesion Exitoso");
        } else {
            System.out.println("Registro de sesión fallido");
        }
    }

    private void iniciarSesion() {
        System.out.println("Inicio de Sesión");
        System.out.print("Nombre de Usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        boolean inicioSesionExitoso = cuenta.iniciarSesion(nombreUsuario, contraseña);

        if (inicioSesionExitoso) {
            mostrarMenuDespuesDeInicioSesion();
        } else {
            System.out.println("Inicio de sesión fallido");
        }
    }

    private void mostrarMenuDespuesDeInicioSesion() {
        System.out.println("Inicio de sesión exitoso");
        while (true) {
            System.out.println("Menú Principal");
            System.out.println("1. Ver Dietas");
            System.out.println("2. Ver Rutinas");
            System.out.println("3. Ver Horario y Actividades"); // Nueva opción
            System.out.println("4. Ver Progreso"); // Nueva opción
            System.out.println("5. Salir al Menú de Inicio de Sesión");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    mostrarDietas();
                    break;
                case 2:
                    mostrarRutinas();
                    break;
                case 3:
                    manejarHorarioYActividades();
                    break;
                case 4:
                    mostrarMenuProgreso();
                    break;
                case 5:
                    return; // Regresar al menú de inicio de sesión
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void mostrarDietas() {
        Usuario usuarioSesion = cuenta.getUsuarioSesion();
        DietaGenerator dietaGenerator = new DietaGenerator(usuarioSesion);
        dietaGenerator.calcularCaloriasDiarias();
        dietaGenerator.consultarAlimentosPorTipo();
    }

    private void mostrarRutinas() {
        Rutinas rutinas = new Rutinas(cuenta.getUsuarioSesion().getObjetivo());
        rutinas.ejecutar();
    }

    private void mostrarMenuProgreso() {
        Usuario usuarioSesion = cuenta.getUsuarioSesion();
        
    
        while (true) {
            System.out.println("Menú de Progreso");
            System.out.println("1. Ver Estado Actual");
            System.out.println("2. Cambiar Estado");
            System.out.println("3. Ver Progreso");
            System.out.println("4. Regresar al Menú Principal");
            System.out.print("Elija una opción: ");
    
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea
    
            switch (opcion) {
                case 1:
                    verEstadoActual();
                    break;
                case 2:
                    cambiarEstado();
                    break;
                case 3:
                    usuarioSesion.cargarProgreso();
                    break;
                case 4:
                    return; // Regresar al menú principal
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
    

    private void verEstadoActual() {
        Usuario usuarioSesion = cuenta.getUsuarioSesion();
        System.out.println("Estado Actual:");
        System.out.println("Nombre: " + usuarioSesion.getNombre());
        System.out.println("Edad: " + usuarioSesion.getEdad());
        System.out.println("Peso: " + usuarioSesion.getPeso() + " kg");
        System.out.println("Altura: " + usuarioSesion.getAltura() + " cm");
        double imc = usuarioSesion.calcularIMC();
        System.out.println("IMC: " + imc + " - " + usuarioSesion.obtenerEstadoIMC(imc));
    }

    private void cambiarEstado() {
        Usuario usuarioSesion = cuenta.getUsuarioSesion();
        System.out.println("Cambiar Estado");
        System.out.print("Nuevo Peso (kg): ");
        double nuevoPeso = scanner.nextDouble();
        System.out.print("Nueva Altura (cm): ");
        double nuevaAltura = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea

        usuarioSesion.cambiarEstado(nuevoPeso, nuevaAltura);

        System.out.println("Cambio de estado exitoso");
    }

    private void manejarHorarioYActividades() {
        Horario horario = new Horario(8, cuenta.getUsuarioSesion().getNombreUsuario());

        while (true) {
            System.out.println("Menú de Horario y Actividades");
            System.out.println("1. Agregar Actividad");
            System.out.println("2. Ver Tareas Diarias");
            System.out.println("3. Marcar Actividad como Completada");
            System.out.println("4. Regresar al Menú Principal");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    agregarActividad(horario);
                    break;
                case 2:
                    horario.verTareasDiarias();
                    break;
                case 3:
                    marcarActividadComoCompletada(horario);
                    break;
                case 4:
                    return; // Regresar al menú principal
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void agregarActividad(Horario horario) {
        System.out.print("Nombre de la actividad: ");
        String nombre = scanner.nextLine();
        System.out.print("Duración de la actividad (en horas): ");
        int duracion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        boolean completada = false;
        System.out.println("Selecciona el tipo de actividad:");
        System.out.println("1. Actividad Estudios");
        System.out.println("2. Actividad de Ejercicio");
        System.out.println("3. Actividad de Ocio");
        System.out.print("Elije una opción: ");
    
        int tipoActividad = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
    
        ActividadBase actividad;
        String tipo; 
        switch (tipoActividad) {
            case 1:
                tipo= "Estudio";
                actividad = new ActividadEstudio(tipo, nombre, duracion, false);
                break;
            case 2:
            tipo= "Ejercicio";
                actividad = new ActividadEjercicio(tipo, nombre, duracion, false);
                break;
            case 3:
            tipo= "Ocio";
                actividad = new ActividadOcio(tipo, nombre, duracion, false);
                break;
            default:
                tipo= "Cualquiera";
                System.out.println("Opción no válida. Se agregará como Actividad Base.");
                actividad = new ActividadBase(tipo, nombre, duracion,completada) {
                    @Override
                    public void realizar() {
                        System.out.println("Realizando actividad base: " + getNombre());
                    }
    
                    @Override
                    public void marcarComoCompletada() {
                        System.out.println("Actividad base completada: " + getNombre());
                    }
                };
                break;
        }
    
        horario.agregarActividad(actividad);
    }
    
    

    private void marcarActividadComoCompletada(Horario horario) {
        System.out.print("Nombre de la actividad a marcar como completada: ");
        String nombreActividad = scanner.nextLine();
        horario.marcarActividadComoCompletada(nombreActividad);
    }

    public static void main(String[] args) {
        TerminalApp app = new TerminalApp();
        app.run();
    }
}
