package JavaFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Insurance Claims Management System");

        // Initialize the root layout
        initRootLayout();
    }

    public void initRootLayout() {
        try {
            System.out.println("Initializing root layout...");
            // Load the root layout from the FXML file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Login.fxml"));
            AnchorPane rootLayout = loader.load();
            System.out.println("Root layout loaded.");

            // Set the scene with the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("Primary stage shown.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize root layout: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
