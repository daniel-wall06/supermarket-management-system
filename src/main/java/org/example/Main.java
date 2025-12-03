package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Set up the primary stage for javafx GUI
 */
public class Main extends Application {
    public static Stage primaryStage;
    @Override
    public void start(Stage ps) throws Exception {
        primaryStage = ps;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/SuperMarketSystem/supermarket.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Daniel Wall (20112377) - CA1 - Supermarket System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
