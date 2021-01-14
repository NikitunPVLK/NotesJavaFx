package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.Date;

public class Note extends AnchorPane {
    @FXML
    private TextFlow noteText;

    @FXML
    private Label noteName;

    @FXML
    private Label noteDeadline;


    public Note(String name, String text, String deadline) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/custom.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            noteName.setText(name);
            noteText.getChildren().add(new Text(text));
            noteDeadline.setText(deadline);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setName(String name){
        noteName.setText(name);
    }

    public void setText(String text){
        noteText.getChildren().add(new Text(text));
    }

    public void setDeadline(Date deadline){
        noteDeadline.setText(deadline.toString());
    }
}
