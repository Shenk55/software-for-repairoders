package com.example.repairorder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewOrderDialog extends Stage {

    public NewOrderDialog(Stage owner) {
        // Configure the dialog window
        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.DECORATED);
        setTitle("Neuer Auftrag");
        setMinWidth(500);
        setMinHeight(600);

        // Create the form layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: white;");

        // Create form fields
        Label titleLabel = new Label("Neuer Auftrag");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setVgap(15);
        form.setHgap(10);
        form.setAlignment(Pos.CENTER);

        // Name/Firma field
        Label nameLabel = new Label("Name/Firma");
        TextField nameField = new TextField();
        nameField.setPromptText("Name eingeben");
        nameField.setPrefWidth(400);

        // Email field
        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();
        emailField.setPromptText("Email eingeben");

        // Telefonnummer field
        Label phoneLabel = new Label("Telefonnummer");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Telefonnummer eingeben");

        // Gerät field
        Label deviceLabel = new Label("Welches Gerät?");
        TextField deviceField = new TextField();
        deviceField.setPromptText("Gerät eingeben");

        // Seriennummer field
        Label serialLabel = new Label("Seriennummer");
        TextField serialField = new TextField();
        serialField.setPromptText("Seriennummer eingeben");

        // Defekt field
        Label defectLabel = new Label("Was ist Defekt?");
        TextArea defectArea = new TextArea();
        defectArea.setPromptText("Defekt beschreiben");
        defectArea.setPrefRowCount(4);
        defectArea.setWrapText(true);

        // Add fields to form
        form.addRow(0, nameLabel, nameField);
        form.addRow(1, emailLabel, emailField);
        form.addRow(2, phoneLabel, phoneField);
        form.addRow(3, deviceLabel, deviceField);
        form.addRow(4, serialLabel, serialField);
        form.addRow(5, defectLabel, defectArea);

        // Buttons
        Button cancelButton = new Button("Abbrechen");
        cancelButton.setStyle("-fx-background-color: transparent; -fx-border-color: #666; -fx-border-radius: 4;");

        Button saveButton = new Button("Speichern");
        saveButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().addAll(cancelButton, saveButton);

        // Add all elements to main layout
        mainLayout.getChildren().addAll(titleLabel, form, buttonBox);

        // Set up button actions
        cancelButton.setOnAction(e -> close());
        saveButton.setOnAction(e -> {
            // Here you would typically save the order
            // For now, we'll just close the dialog
            close();
        });

        // Create and set the scene
        Scene scene = new Scene(mainLayout);
        setScene(scene);

        // Center the dialog on the owner window
        centerOnScreen();
    }
}
