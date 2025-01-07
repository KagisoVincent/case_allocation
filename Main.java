/*
 * @author 220104716
 */
import acsse.csc03a3.gui.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Login gui = new Login();

        Scene scene = new Scene(gui, 400, 300);
        scene.setFill(Color.WHITE);

        primaryStage.setTitle("SAPS Reporting System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
