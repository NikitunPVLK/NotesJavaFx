package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class addController {

    @FXML
    private Button backButton;

    @FXML
    private TextField name;

    @FXML
    private DatePicker deadline;

    @FXML
    private TextArea note;

    @FXML
    private Button saveButton;

    private Scene scene;


    @FXML
    void back(ActionEvent event) {
        //Main.notes.forEach(System.out::println);
        //scene.getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
        //Main.stg.setScene(scene);
    }

    @FXML
    void save(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
        try {
            Parent root = loader.load();
            Controller controller = loader.getController();
            this.scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
            Main.stg.setScene(scene);
            Main.notes.add(new Note(name.getText(), note.getText(), deadline.getValue().toString()));
            controller.showNotes();
            controller.showQuantity();
            //controller.show(new Note(name.getText(), note.getText()));
            //this.back(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
