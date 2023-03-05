package ma.enset.JavaFxJdbc.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationJavaFx extends Application {
    public static void main (String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root= FXMLLoader.load(getClass().getResource("Views/ProductView.fxml"));
        Scene scene =new Scene(root,500,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion de produits");
        primaryStage.show();
    }
}
