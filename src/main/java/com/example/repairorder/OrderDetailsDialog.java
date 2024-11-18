package com.example.repairorder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OrderDetailsDialog extends Stage {

    public OrderDetailsDialog(Stage owner, HomePage.Order order) {
        // Configure the dialog window
        initOwner(owner);
        initModality(Modality.APPLICATION_MODAL);
        initStyle(StageStyle.DECORATED);
        setTitle("Auftrag Details");
        setMinWidth(500);
        setMinHeight(600);

        // Create the main layout
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: white;");

        // Create header with title and close button
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("Auftrag #" + order.orderNumberProperty().get() + " Details");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button closeButton = new Button("×");
        closeButton.setStyle("-fx-font-size: 18px; -fx-background-color: transparent; -fx-padding: 0 5 0 5;");
        closeButton.setOnAction(event -> close());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        header.getChildren().addAll(titleLabel, spacer, closeButton);

        // Create details grid
        GridPane detailsGrid = new GridPane();
        detailsGrid.setVgap(15);
        detailsGrid.setHgap(10);
        detailsGrid.setPadding(new Insets(20, 0, 20, 0));

        // Add all the details fields
        addDetailRow(detailsGrid, 0, "Auftrags Datum:", order.dateProperty().get());
        addDetailRow(detailsGrid, 1, "Kunden Name:", order.nameProperty().get());
        addDetailRow(detailsGrid, 2, "Email:", "alex.johnson@email.co");
        addDetailRow(detailsGrid, 3, "Telefonnummer:", "+49 123 456789");
        addDetailRow(detailsGrid, 4, "Produkt Details:", "Wireless Bluetooth Headphones");
        addDetailRow(detailsGrid, 5, "Seriennummer:", "SN123456789");
        addDetailRow(detailsGrid, 6, "Defekt:", "Left speaker not");
        addDetailRow(detailsGrid, 7, "Status:", order.statusProperty().get());

        // Create save button
        Button saveButton = new Button("Speichern");
        saveButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        saveButton.setOnAction(event -> close());

        // Add button container
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().add(saveButton);

        // Add all elements to main layout
        mainLayout.getChildren().addAll(header, detailsGrid, buttonBox);

        // Create and set the scene
        Scene scene = new Scene(mainLayout);
        setScene(scene);

        // Center the dialog on the owner window
        centerOnScreen();
    }

    private void addDetailRow(GridPane grid, int row, String label, String value) {
        Label labelNode = new Label(label);
        labelNode.setStyle("-fx-font-weight: bold;");
        Label valueNode = new Label(value);
        grid.addRow(row, labelNode, valueNode);
    }
}