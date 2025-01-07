/*
 * @author 220104716 
 */
package acsse.csc03a3.Clients;

import java.util.ArrayList;
import java.util.List;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/*
 * implementation for the station client
 */
public class StationReport {

	public TextArea caseTextArea;
	//main container blockchain so that clients can use one blockchain
	Blockchain<Reports> container = new Blockchain<>();
	

	public StationReport() {
		// Register stakeholders with stakes for Proof of Stake
		System.out.println("Registering Police stake...");
		container.registerStake("Police", 150);

	}

	public String name, casetype;

	public GridPane createGrid() {
		// Create GUI components
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setVgap(10);
		grid.setHgap(10);

		BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
		Background background = new Background(backgroundFill);
		grid.setBackground(background);

		Label headerLabel = new Label("Welcome to JHB POLICE STATIONS");
		headerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		grid.add(headerLabel, 0, 0);

		ImageView imageView = new ImageView();
		Image image = new Image("file:saps.png");
		imageView.setImage(image);
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		grid.add(imageView, 1, 0);

		grid.setHalignment(imageView, HPos.RIGHT);
		grid.setValignment(imageView, VPos.TOP);

		GridPane.setMargin(imageView, new Insets(10, 10, 0, 0)); // Insets: top, right, bottom, left

		Label policeName = new Label("Name of Police");
		grid.add(policeName, 0, 2);

		TextField policeField = new TextField();
		grid.add(policeField, 1, 2);

		Label stationLabel = new Label("Name of Station");
		grid.add(stationLabel, 0, 3);

		ComboBox<String> stationBox = new ComboBox<>();

		List<String> stations = new ArrayList<>();
		stations.add("STATION A");//deal will Theft
		stations.add("STATION B");//deal with Assault
		stations.add("STATION C");//deal with vandalism
		stations.add("STATION D");//dela with other cases
		stationBox.getItems().addAll(stations);

		// TextField stationField = new TextField();
		grid.add(stationBox, 1, 3);

		Label casesLabel = new Label("Feedback Available");
		grid.add(casesLabel, 0, 7);

		TextArea feedbackArea = new TextArea();
		grid.add(feedbackArea, 1, 8);

		Button btnList = new Button("List");

		btnList.setOnAction(e -> {

			// Get victim cases from blockchain and display them
			StringBuilder sb = new StringBuilder();

			String blockchainInfo = container.toString();
			sb.append(blockchainInfo);

			feedbackArea.setText(sb.toString());
		});
		grid.add(btnList, 1, 7);

		Label responseLabel = new Label("Replies");
		grid.add(responseLabel, 0, 5);

		Button btnReply = new Button("Reply");
		grid.add(btnReply, 1, 5);

		TextArea responseCases = new TextArea();
		grid.add(responseCases, 1, 6);
		Label priorityLabel = new Label("Case Priority");
		grid.add(priorityLabel, 0, 4);

		ComboBox<String> priorityBox = new ComboBox<>();
		List<String> priorities = new ArrayList<>();
		priorities.add("High");
		priorities.add("Medium");
		priorities.add("Low");
		priorityBox.getItems().addAll(priorities);
		grid.add(priorityBox, 1, 4);

		btnReply.setOnAction(e -> {
			String policeNameText = policeField.getText();
			String station = stationBox.getValue();
			String reply = responseCases.getText();

			if (policeNameText.isEmpty() || station == null || station.isEmpty() || reply.isEmpty()) {
				showAlert("Please fill in all fields.");
				return;
			}else {
				successfulAlert("Feedback sent.");
			}

			reportCase(policeNameText, station, reply);
		});

		return grid;
	}
//-------------------------------------------------------------------------------------------------------------------------------
	private void reportCase(String policeName, String station, String reply) {
		Station stationReport = new Station(policeName, station, reply);
		Transaction<Reports> report = new Transaction<>(policeName, "VICTIMS", stationReport);

		List<Transaction<Reports>> transactions = new ArrayList<>();
		transactions.add(report);

		try {
			System.out.println("Adding block with transactions...");
			container.addBlock(transactions);
		
			// Check if the chain is valid after adding the block
			if (container.isChainValid()) {
				System.out.println("Blockchain is valid.");
				successfulAlert("Blockchain is valid.");
			} else {
				System.out.println("Blockchain is not valid.");
				showAlert("Error: Blockchain is not valid.");
			}

			System.out.println(container.toString());
		} catch (RuntimeException e) {
			showAlert("Error: " + e.getMessage());
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------
	// buttons alerts
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
