package com.example.repairorder;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MYDATENTECHNIK - Aktuelle Aufträge");

        // Create main VBox
        VBox mainLayout = new VBox(20);
        mainLayout.setStyle("-fx-background-color: white;");
        mainLayout.setPadding(new Insets(20));

        // Header
        HBox header = createHeader();

        // Filters
        HBox filters = createFilters();

        // Table
        TableView<Order> table = createTable();

        mainLayout.getChildren().addAll(header, filters, table);

        // Create scene
        Scene scene = new Scene(mainLayout, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setSpacing(20);

        // Logo
        Image logo = new Image("file:C:/Users/GSbur/OneDrive/Desktop/logo_v4-247b584d.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(60);
        logoView.setPreserveRatio(true);

        // Title
        Label title = new Label("Aktuelle Aufträge");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // New Order Button
        Button newOrderBtn = new Button("Neuer Auftrag");
        newOrderBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");

        // Add action to show the new order dialog
        newOrderBtn.setOnAction(e -> {
            NewOrderDialog dialog = new NewOrderDialog((Stage) header.getScene().getWindow());
            dialog.showAndWait();
        });

        // Layout
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(logoView, title, spacer, newOrderBtn);
        return header;
    }

    private HBox createFilters() {
        HBox filters = new HBox(10);
        filters.setPadding(new Insets(20, 0, 20, 0));

        // Filter fields
        TextField orderNumberFilter = new TextField();
        orderNumberFilter.setPromptText("Filter nach Auftrags Nummer");

        TextField dateFilter = new TextField();
        dateFilter.setPromptText("Filtern nach Datum");

        TextField nameFilter = new TextField();
        nameFilter.setPromptText("Filtern nach Name");

        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.setPromptText("Filtern nach Status");
        statusFilter.setItems(FXCollections.observableArrayList(
                "Alle",
                "In Bearbeitung",
                "Fertig",
                "Ausstehend"
        ));

        // Style the filters
        String filterStyle = "-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-radius: 5;";
        orderNumberFilter.setStyle(filterStyle);
        dateFilter.setStyle(filterStyle);
        nameFilter.setStyle(filterStyle);
        statusFilter.setStyle(filterStyle);

        filters.getChildren().addAll(orderNumberFilter, dateFilter, nameFilter, statusFilter);
        return filters;
    }

    private TableView<Order> createTable() {
        TableView<Order> table = new TableView<>();

        // Create columns
        TableColumn<Order, String> orderNumberCol = new TableColumn<>("Auftrag");
        orderNumberCol.setCellValueFactory(cellData -> cellData.getValue().orderNumberProperty());

        TableColumn<Order, String> dateCol = new TableColumn<>("Datum");
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        TableColumn<Order, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        TableColumn<Order, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Order, Void> actionsCol = createActionsColumn();

        table.getColumns().addAll(orderNumberCol, dateCol, statusCol, nameCol, actionsCol);

        // Add sample data
        ObservableList<Order> orders = FXCollections.observableArrayList(
                new Order("12345", "15.10.2023", "in bearbeitung", "Alice Johnson"),
                new Order("12346", "25.09.2023", "Fertig", "Bob Smith"),
                new Order("12347", "01.10.2023", "ausstehend", "Charlie Brown"),
                new Order("12348", "03.10.2023", "in bearbeitung", "David Wilson"),
                new Order("12349", "04.10.2023", "Fertig", "Emma Davis")
        );

        table.setItems(orders);

        // Style the table
        table.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0;");

        return table;
    }

    private TableColumn<Order, Void> createActionsColumn() {
        TableColumn<Order, Void> actionsCol = new TableColumn<>("Aktionen");
        actionsCol.setCellFactory(this::createActionsCellFactory);
        return actionsCol;
    }

    private TableCell<Order, Void> createActionsCellFactory(TableColumn<Order, Void> col) {
        return new TableCell<>() {
            private final Button editBtn = new Button("Bearbeiten");
            private final Button completeBtn = new Button("Abgeschlossen");
            private final HBox buttons;

            {
                editBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                completeBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                buttons = new HBox(10, editBtn, completeBtn);

                // Add action to edit button
                editBtn.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    OrderDetailsDialog dialog = new OrderDetailsDialog(
                            (Stage) getScene().getWindow(),
                            order
                    );
                    dialog.showAndWait();
                });

                // Add action to complete button
                completeBtn.setOnAction(event -> {
                    Order order = getTableView().getItems().get(getIndex());
                    // Here you would typically update the order status
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : buttons);
            }
        };
    }

    // Order class
    public static class Order {
        private final javafx.beans.property.SimpleStringProperty orderNumber;
        private final javafx.beans.property.SimpleStringProperty date;
        private final javafx.beans.property.SimpleStringProperty status;
        private final javafx.beans.property.SimpleStringProperty name;

        public Order(String orderNumber, String date, String status, String name) {
            this.orderNumber = new javafx.beans.property.SimpleStringProperty("Auftrag #" + orderNumber);
            this.date = new javafx.beans.property.SimpleStringProperty("Datum: " + date);
            this.status = new javafx.beans.property.SimpleStringProperty("Status: " + status);
            this.name = new javafx.beans.property.SimpleStringProperty("Name: " + name);
        }

        public javafx.beans.property.StringProperty orderNumberProperty() { return orderNumber; }
        public javafx.beans.property.StringProperty dateProperty() { return date; }
        public javafx.beans.property.StringProperty statusProperty() { return status; }
        public javafx.beans.property.StringProperty nameProperty() { return name; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}