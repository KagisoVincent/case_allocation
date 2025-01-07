/*
 * @Author Vincent Serothwane 220104716
 */
package acsse.csc03a3.gui;
import java.util.ArrayList;
import java.util.List;

import acsse.csc03a3.obj.FileHandler;
import acsse.csc03a3.obj.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/*
 * Register page for users with no accounts , works with the Login
 */
public class Register {
   
    	 FileHandler fileHandler = new FileHandler();

    public GridPane createGui() {
        Label nameLabel = new Label("Name");
        Label surnameLabel = new Label("Surname");
        Label idLabel = new Label("User ID");
        TextField surnameField = new TextField();
        TextField idField = new TextField();
        TextField nameField = new TextField();
        Button btnRegister;

        Label nationalityLabel = new Label("Nationality:");
        ComboBox<String> nationalityBox = new ComboBox<>();

        List<String> nationalityOptions = new ArrayList<>();
        nationalityOptions.add("South African");
        nationalityOptions.add("Non South African");
        nationalityBox.getItems().addAll(nationalityOptions);

        Label userRoleLabel = new Label("User Role:");
        ComboBox<String> roleBox;
        roleBox = new ComboBox<>();

        List<String> storeRoles = new ArrayList<>();
        storeRoles.add("Police Station");
        storeRoles.add("Victims");
        roleBox.getItems().addAll(storeRoles);

        GridPane grid = new GridPane();

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
        Background background = new Background(backgroundFill);
        grid.setBackground(background);

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(surnameLabel, 0, 1);
        grid.add(surnameField, 1, 1);

        grid.add(nationalityLabel, 0, 2);
        grid.add(nationalityBox, 1, 2);

        grid.add(idLabel, 0, 3);
        grid.add(idField, 1, 3);

        btnRegister = new Button("REGISTER");
        btnRegister.setStyle("-fx-background-color: #ffcc00; -fx-text-fill: blue;");
        grid.add(btnRegister, 1, 5);

        grid.add(userRoleLabel, 0, 4);
        grid.add(roleBox, 1, 4);

        btnRegister.setOnAction(e -> {
            String username = nameField.getText();
            String surname = surnameField.getText();
            String id = idField.getText();
            String nationality = nationalityBox.getValue();
            String userRole = roleBox.getValue();

            if (!username.isEmpty() && !surname.isEmpty() && !id.isEmpty() && nationality != null && userRole != null) {
                Person person = new Person(username, surname, id, nationality, userRole);
                fileHandler.registerUser(person);
                successfulAlert(username + " registered successfully");
            } else {
                showAlert("ERROR", "Please fill in all fields.");
            }
        });

        return grid;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void successfulAlert(String message) {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText(message);
        successAlert.showAndWait();
    }
}
