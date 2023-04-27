package pers.zcc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pers.zcc.controller.PersonOverviewController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("AddressApp");
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/RootLayout.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            FXMLLoader personOverviewLoader = new FXMLLoader(getClass().getResource("/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) personOverviewLoader.load();
            root.setCenter(personOverview);
            PersonOverviewController personOverviewController = personOverviewLoader.getController();
            personOverviewController.setPrimaryStage(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
