/*
 * @author 220104716
 */
package acsse.csc03a3.gui;

import java.io.IOException;
import java.util.List;

import acsse.csc03a3.Clients.StationReport;
import acsse.csc03a3.Clients.VictimReport;
import acsse.csc03a3.obj.FileHandler;
import acsse.csc03a3.obj.Person;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/*
 * This class extends borderpane and monitors security by allowing bothe members to register then login 
 */
public class Login extends BorderPane {
	private FileHandler filehandler = new FileHandler();

	public Login() {
		setPadding(new Insets(20));
		try {
			initializeGUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initializeGUI() throws IOException {
		// Header statement ,welcome the user
		Label headerLabel = new Label("Welcome to JHB SAPS");
		headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		setTop(headerLabel);

		// Center
		GridPane centerGrid = new GridPane();
		centerGrid.setHgap(10);
		centerGrid.setVgap(10);

		Label usernameLabel = new Label("Username");
		centerGrid.add(usernameLabel, 0, 0);

		TextField loginNameInput = new TextField();
		loginNameInput.setPromptText("Enter your username");
		centerGrid.add(loginNameInput, 1, 0);

		Label passwordLabel = new Label("ID");
		centerGrid.add(passwordLabel, 0, 1);

		TextField loginIdInput = new TextField();
		loginIdInput.setPromptText("Enter your id");
		centerGrid.add(loginIdInput, 1, 1);

		Button loginButton = new Button("Login");
		loginButton.setStyle("-fx-background-color: #1DA1F2; -fx-text-fill: white;");
		centerGrid.add(loginButton, 1, 2);

		loginButton.setOnAction(e -> {
			List<Person> persons = filehandler.loadUserData();
			String username = loginNameInput.getText();
			String id = loginIdInput.getText();

			Person person = persons.stream().filter(u -> u.getUsername().equals(username) && u.getUserid().equals(id))
					.findFirst().orElse(null);

			if (person != null) {
				System.out.println("Login Successful");
				loginNameInput.clear();
				loginIdInput.clear();

				if ("Police Station".equals(person.getUserRoles())) {
					successfulAlert("Login Successful as Police");
					openPoliceStationPage();
				} else if ("Victims".equals(person.getUserRoles())) {
					successfulAlert("Login Successful as Victim");
					openVictimPage();
				}
			} else {
				System.out.println("Invalid username or id");
				showAlert("Invalid username or id, can you please register before Log in");
			}
		});

		VBox mainContainer = new VBox(20);
		mainContainer.getChildren().addAll(centerGrid);
		mainContainer.setPadding(new Insets(20));
		mainContainer.setStyle("-fx-background-color: #F5F8FA; -fx-border-color: #E1E8ED; -fx-border-width: 1px;");
		setCenter(mainContainer);

		//  clients without accounts
		Label signUpLabel = new Label("Don't have an account?");
		Button registerBtn = new Button("Register here");
		registerBtn.setOnAction(e -> openRegisterPage());
		VBox footerContainer = new VBox(5);
		footerContainer.getChildren().addAll(signUpLabel, registerBtn);
		footerContainer.setStyle("-fx-alignment: center-right;");
		setBottom(footerContainer);

		// Setting background color to light blue
		setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
	}

	private void openPoliceStationPage() {
		StationReport gui = new StationReport();
		Stage stage = new Stage();
		stage.setScene(new Scene(gui.createGrid(), 300, 200));
		stage.setTitle("Police Station Page");
		stage.show();
	}

	private void openVictimPage() {
		VictimReport gui = new VictimReport();
		Stage stage = new Stage();
		stage.setScene(new Scene(gui.createGrid(), 300, 200));
		stage.setTitle("Victim Page");
		stage.show();
	}

	private void openRegisterPage() {
		Register registerGui = new Register();
		Stage stage = new Stage();
		stage.setScene(new Scene(registerGui.createGui(), 300, 200));
		stage.setTitle("Register Page");
		stage.show();
	}

	private void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
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
