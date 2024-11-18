package com.example.repairorder;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("MYDATENTECHNIK Login");

        // Create logo
        Image logo = new Image("file:C:/Users/GSbur/OneDrive/Desktop/logo_v4-247b584d.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(300);
        logoView.setPreserveRatio(true);

        // Create form elements
        Label titleLabel = new Label("Einloggen");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Benutzernamen eingeben");
        usernameField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Passwort eingeben");
        passwordField.setMaxWidth(300);

        Button loginButton = new Button("Einloggen");
        loginButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 16px;");
        loginButton.setPrefWidth(300);
        loginButton.setPrefHeight(40);

        // Add action to login button
        loginButton.setOnAction(e -> {
            // Here you would typically validate the login credentials
            // For this example, we'll just switch to the HomePage
            HomePage homePage = new HomePage();
            try {
                homePage.start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create login form layout
        VBox loginForm = new VBox(20);
        loginForm.setAlignment(Pos.CENTER);
        loginForm.setPadding(new Insets(40));
        loginForm.getChildren().addAll(logoView, titleLabel, usernameField, passwordField, loginButton);
        loginForm.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        loginForm.setMaxWidth(400);
        loginForm.setMaxHeight(500);

        // Create root layout
        StackPane root = new StackPane(loginForm);
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Create scene with full HD resolution
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }}