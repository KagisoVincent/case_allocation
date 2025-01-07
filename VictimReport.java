/*
 * @author Serothwane Vincent 220104716
 * @version Mini Practical
 */
package acsse.csc03a3.Clients;

import java.util.ArrayList;
import java.util.List;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class VictimReport {

	public TextArea caseTextArea;
//	Blockchain<Victim> blockchain = Holde.container;
	Blockchain<Reports> container = new Blockchain<>();
	TextField locationField = new TextField();


	public VictimReport() {

		// Register stakeholders with stakes for Proof of Stake
		System.out.println("Registering Police stake...");
		container.registerStake("Police", 150); // Example stake registration
		// System.out.println("Registering Government stake...");
		// blockchain.registerStake("Government", 100); // Example stake registration

	}

	public GridPane createGrid() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setVgap(10);
		grid.setHgap(10);
		setBackground(grid);
		addUIComponents(grid);
		return grid;
	}

	private void addUIComponents(GridPane grid) {

		// Create UI components
		Label NameLabel = new Label("Full Names:");
		NameLabel.setStyle("-fx-font-weight: bold;");
		grid.add(NameLabel, 1, 0);

		TextField NameField = new TextField();
		grid.add(NameField, 2, 0);

		Label caseTypeLabel = new Label("Case Type:");
		caseTypeLabel.setStyle("-fx-font-weight: bold;");
		grid.add(caseTypeLabel, 1, 1);

		Label locationLabel = new Label("Location:");
		locationLabel.setStyle("-fx-font-weight: bold;");
		grid.add(locationLabel, 1, 2);

		TextField locationField = new TextField();
		grid.add(locationField, 2, 2);

		ComboBox<String> caseTypeComboBox = new ComboBox<>();
		caseTypeComboBox.getItems().addAll("Theft", "Assault", "Vandalism", "Other");

		grid.add(caseTypeComboBox, 2, 1);

		Label descriptionLabel = new Label("Description:");
		descriptionLabel.setStyle("-fx-font-weight: bold;");
		grid.add(descriptionLabel, 1, 3);
		// descriptionLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");

		TextArea descriptionArea = new TextArea();
		descriptionArea.setWrapText(true);
		grid.add(descriptionArea, 2, 3);

		Button reportButton = new Button("Report Case");
		grid.add(reportButton, 2, 4);

		Label messgLabel = new Label("Click the Track case to check your case updates");
		messgLabel.setStyle("-fx-font-weight: bold;");
		grid.add(messgLabel, 1, 5);

		Button trackCaseBtn = new Button("Track Case");
		grid.add(trackCaseBtn, 2, 6);

		caseTextArea = new TextArea();
		caseTextArea.setEditable(false);
		caseTextArea.setWrapText(true);
		grid.add(caseTextArea, 2, 7);

		// Event handler for report button
		reportButton.setOnAction(e -> {
			String caseType = caseTypeComboBox.getValue();
			String location = locationField.getText();
			String description = descriptionArea.getText();
			String strName = NameField.getText();
			if (caseType != null && !caseType.isEmpty() && location != null && !location.isEmpty()
					&& description != null && !description.isEmpty() && !strName.isEmpty()) {

				reportCase(strName, caseType, location, description);
				// updateCaseTextArea();

			} else {
				showAlert("Error", "Please fill in all fields.");
			}
		});

		trackCaseBtn.setOnAction(e -> {

			// this method should be able to update the victim
			updateCaseTextArea1();
		});

	}

	private void setBackground(GridPane grid) {
		BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTBLUE, null, null);
		Background background = new Background(backgroundFill);
		grid.setBackground(background);
	}

	private void updateCaseTextArea1() {
		StringBuilder sb = new StringBuilder();

		String blockchainInfo = container.toString();
		sb.append(blockchainInfo);

		caseTextArea.setText(sb.toString());
	}

	// Method to report a new case
	@SuppressWarnings({ "unused", "unused" })
	private void reportCase(String victimName, String caseType, String location, String description) {
		StringBuilder sb = new StringBuilder();
		Victim victim = new Victim(victimName, caseType, location, description);

		// Determine the appropriate police station based on the case type
		String receiver = "Station D"; // Default receiver for unknown case types
		switch (caseType.toLowerCase()) {
		case "assault":
			receiver = "Station A";
			break;
		case "theft":
			receiver = "Station B";
			break;
		case "vandalism":
			receiver = "Station C";
			break;
		case "other":
			receiver = "Station D";
			break;
		}

		Transaction<Reports> report = new Transaction<>(victimName, receiver, victim);

		List<Transaction<Reports>> transactions = new ArrayList<>();
		transactions.add(report);

		try {
			System.out.println("Adding block with transactions...");
			container.addBlock(transactions);

			// Check if the chain is valid after adding the block
			if (container.isChainValid()) {
				System.out.println("Blockchain is valid.");
			} else {
				System.out.println("Blockchain is not valid.");
				showAlert("Error", "Blockchain is not valid");
			}

			System.out.println(container.toString());

		} catch (RuntimeException e) {
			showAlert("Error", e.getMessage());
		}

		if (transactions != null) {
			// VictimReport report = transactions.getData();
			// Check the case type and allocate response accordingly
			switch (caseType) {
			case "Theft":
				sb.append("Response for Theft cases:\n");
				sb.append("Victim Name: ").append(victim.getReporterName()).append("\n");
				sb.append("Location: ").append(victim.getLocation()).append("\n");
				sb.append("Description: ").append(victim.getDescription()).append("\n\n");
				sb.append("Actions taken for Theft cases...\n");
				break;
			case "Assault":
				sb.append("Response for Assault cases:\n");
				sb.append("Victim Name: ").append(victim.getReporterName()).append("\n");
				sb.append("Location: ").append(victim.getLocation()).append("\n");
				sb.append("Description: ").append(victim.getDescription()).append("\n\n");
				sb.append("Actions taken for Assault cases...\n");
				break;
			case "Vandalism":
				sb.append("Response for Vandalism cases:\n");
				sb.append("Victim Name: ").append(victim.getReporterName()).append("\n");
				sb.append("Location: ").append(victim.getLocation()).append("\n");
				sb.append("Description: ").append(victim.getDescription()).append("\n\n");
				sb.append("Actions taken for Vandalism cases...\n");
				break;
			case "Other":
				sb.append("Response for Other cases:\n");
				sb.append("Victim Name: ").append(victim.getReporterName()).append("\n");
				sb.append("Location: ").append(victim.getLocation()).append("\n");
				sb.append("Description: ").append(victim.getDescription()).append("\n\n");
				sb.append("Actions taken for Other cases...\n");
				break;
			default:
				sb.append("Invalid case type selected.");
			}
		} else {
			sb.append("Transaction not found for the specified location.");
		}

		caseTextArea.setText(sb.toString());

	}

	// Method to show an alert dialog
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	
}
