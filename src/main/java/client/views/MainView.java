package client.views;

import client.controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class MainView {


    @FXML
    public ImageView imgView;

    @FXML
    public ListView listView;

    private MainController controller;

    private ObservableList names = FXCollections.observableArrayList();

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public void getListFiles(ActionEvent actionEvent) {
        listView.getItems().clear();
        List<String> listFiles = controller.getFiles();
        names.addAll(listFiles);
        createTreeOfFiles();
    }

    private void createTreeOfFiles() {
        listView.setItems(names);

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String nameFileClicked = (String)listView.getSelectionModel().getSelectedItem();
                BufferedImage image = controller.showFile(nameFileClicked);
                ByteArrayOutputStream byteArrayOutputStreams = new ByteArrayOutputStream();
                ByteArrayInputStream byteArrayInputStream;
                try {
                    ImageIO.write(image, "jpg", byteArrayOutputStreams);
                    byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStreams.toByteArray());
                    imgView.setImage(new Image(byteArrayInputStream));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
