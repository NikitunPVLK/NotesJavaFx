package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;

public class Controller {

    @FXML
    private Button addButton;

    @FXML
    private ListView<Note> list;

    @FXML
    private Label quantity;


    @FXML
    void addNote(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/newScene.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Main.stg.setScene(new Scene(root));
            Main.stg.getScene().getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Чекнуть время потока и без потока
    public void showNotes(){
        list.getItems().addAll
                (Main.notes.stream()
                        .filter(n -> !list.getItems().contains(n))
                        .collect(Collectors.toList()));
    }

    public void showQuantity(){
        quantity.setText(((Integer) Main.notes.size()).toString());
    }
}
