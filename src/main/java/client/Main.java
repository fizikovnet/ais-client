package client;

import client.controller.MainController;
import client.views.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/main.fxml"));
        Parent root = fxmlLoader.load();
        MainView mainView = fxmlLoader.getController();

        MainController controller = new MainController();
        mainView.setController(controller);

        primaryStage.setTitle("AIS Client");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
