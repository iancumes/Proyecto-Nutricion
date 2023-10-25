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
        // (Implementación del registro de usuario)
        // ...

        System.out.println("Registro exitoso");
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
            System.out.println("4. Salir al Menú de Inicio de Sesión");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            if (opcion == 1) {
                Usuario usuarioSesion = cuenta.getUsuarioSesion();
                DietaGenerator dietaGenerator = new DietaGenerator(usuarioSesion);
                dietaGenerator.calcularCaloriasDiarias();
                dietaGenerator.consultarAlimentosPorTipo();
            } else if (opcion == 2) {
                // Implementa la lógica para ver las rutinas
                Rutinas rutinas = new Rutinas(cuenta.getUsuarioSesion().getObjetivo());
                rutinas.ejecutar();
            } else if (opcion == 3) {
                // Nueva opción para manejar el horario y actividades
                manejarHorarioYActividades();
            } else if (opcion == 4) {
                return; // Regresar al menú de inicio de sesión
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
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
                    System.out.print("Nombre de la actividad: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Duración de la actividad (en horas): ");
                    int duracion = scanner.nextInt();
                    horario.agregarActividad(nombre, duracion);
                    break;
                case 2:
                    horario.verTareasDiarias();
                    break;
                case 3:
                    System.out.print("Nombre de la actividad a marcar como completada: ");
                    String nombreActividad = scanner.nextLine();
                    horario.marcarActividadComoCompletada(nombreActividad);
                    break;
                case 4:
                    return; // Regresar al menú principal
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        TerminalApp app = new TerminalApp();
        app.run();
    }
}
