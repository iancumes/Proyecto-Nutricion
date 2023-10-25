import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class InterfazGrafica extends Application {
    private Cuenta cuenta;
    private VBox layoutRegistro;
    private VBox layoutInicioSesion;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        cuenta = new Cuenta("usuarios.csv"); // Reemplaza "usuarios.csv" con el nombre de tu archivo CSV

        primaryStage.setTitle("Sistema de Registro e Inicio de Sesión");

        // Crear elementos de la interfaz gráfica
        Label labelTitulo = new Label("Bienvenido");
        Button botonRegistrarse = new Button("Registrarse");
        Button botonIniciarSesion = new Button("Iniciar Sesión");

        // Contenedor para la vista de registro
        layoutRegistro = crearLayoutRegistro();

        // Contenedor para la vista de inicio de sesión
        layoutInicioSesion = crearLayoutInicioSesion();

        VBox layoutPrincipal = new VBox(10);
        layoutPrincipal.setAlignment(Pos.CENTER);
        layoutPrincipal.getChildren().addAll(
                labelTitulo,
                botonRegistrarse,
                botonIniciarSesion
        );

        Scene scene = new Scene(layoutPrincipal, 400, 200); // Ajusta el tamaño de la ventana según tus necesidades
        primaryStage.setScene(scene);
        primaryStage.show();

        // Manejadores de eventos
        botonRegistrarse.setOnAction(e -> {
            primaryStage.setScene(new Scene(layoutRegistro, 400, 400)); // Cambia a la vista de registro
        });

        botonIniciarSesion.setOnAction(e -> {
            primaryStage.setScene(new Scene(layoutInicioSesion, 400, 200)); // Cambia a la vista de inicio de sesión
        });
    }

    private VBox crearLayoutRegistro() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label labelNombreUsuario = new Label("Nombre de Usuario:");
        TextField campoNombreUsuario = new TextField();
        Label labelContraseña = new Label("Contraseña:");
        PasswordField campoContraseña = new PasswordField();
        Label labelNombre = new Label("Nombre:");
        TextField campoNombre = new TextField();
        Label labelEdad = new Label("Edad:");
        TextField campoEdad = new TextField();
        Label labelPeso = new Label("Peso (kg):");
        TextField campoPeso = new TextField();
        Label labelAltura = new Label("Altura (cm):");
        TextField campoAltura = new TextField();
        Label labelObjetivo = new Label("Objetivo (subir/bajar/mantener):");
        TextField campoObjetivo = new TextField();
        Button botonConfirmarRegistro = new Button("Confirmar Registro");

        // Manejador de evento para confirmar registro
        botonConfirmarRegistro.setOnAction(e -> {
            // Obtén los valores de los campos de registro
            String nombreUsuario = campoNombreUsuario.getText();
            String contraseña = campoContraseña.getText();
            String nombre = campoNombre.getText();
            int edad = Integer.parseInt(campoEdad.getText());
            double peso = Double.parseDouble(campoPeso.getText());
            double altura = Double.parseDouble(campoAltura.getText());
            String objetivo = campoObjetivo.getText();

            // Intenta registrar al usuario
            boolean registroExitoso = cuenta.registrarUsuario(nombreUsuario, contraseña, nombre, edad, peso, altura, objetivo);

            if (registroExitoso) {
                // Registro exitoso, puedes mostrar un mensaje o realizar acciones adicionales
                System.out.println("Registro exitoso");
            } else {
                // El registro falló, el nombre de usuario podría estar en uso
                System.out.println("El nombre de usuario ya está en uso");
            }
        });

        layout.getChildren().addAll(
                labelNombreUsuario,
                campoNombreUsuario,
                labelContraseña,
                campoContraseña,
                labelNombre,
                campoNombre,
                labelEdad,
                campoEdad,
                labelPeso,
                campoPeso,
                labelAltura,
                campoAltura,
                labelObjetivo,
                campoObjetivo,
                botonConfirmarRegistro
        );

        return layout;
    }

    private VBox crearLayoutInicioSesion() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label labelNombreUsuario = new Label("Nombre de Usuario:");
        TextField campoNombreUsuario = new TextField();
        Label labelContraseña = new Label("Contraseña:");
        PasswordField campoContraseña = new PasswordField();
        Button botonIniciarSesion = new Button("Iniciar Sesión");

        // Manejador de evento para iniciar sesión
        botonIniciarSesion.setOnAction(e -> {
            // Obtén los valores de los campos de inicio de sesión
            String nombreUsuario = campoNombreUsuario.getText();
            String contraseña = campoContraseña.getText();

            // Intenta iniciar sesión
            boolean inicioSesionExitoso = cuenta.iniciarSesion(nombreUsuario, contraseña);

            if (inicioSesionExitoso) {
                // Inicio de sesión exitoso, puedes agregar el código para la siguiente pantalla o acción aquí
                Usuario usuarioSesion = cuenta.getUsuarioSesion();
                System.out.println("Inicio de sesión exitoso");
            } else {
                // El inicio de sesión falló, puedes mostrar un mensaje de error
                System.out.println("Inicio de sesión fallido");
            }
        });

        layout.getChildren().addAll(
                labelNombreUsuario,
                campoNombreUsuario,
                labelContraseña,
                campoContraseña,
                botonIniciarSesion
        );

        return layout;
    }
}
