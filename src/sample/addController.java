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

    @FXML
    void back(ActionEvent event) {
        this.goBack();
    }

    @FXML
    void save(ActionEvent event) {
            Main.notes.add(new Note(name.getText(), note.getText(), deadline.getValue().toString()));
            goBack();
    }

    private void goBack(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
        try {
            Parent root = loader.load();
            Controller controller = loader.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
            Main.stg.setScene(scene);
            controller.showNotes();
            controller.showQuantity();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
