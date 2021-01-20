package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Note extends AnchorPane {

    private int id;

    @FXML
    private TextFlow noteText;

    @FXML
    private Label noteName;

    @FXML
    private Label noteDeadline;

    @FXML
    private Button delete;

    @FXML
    private Button change;

    @FXML
    void change(ActionEvent event) {
        //UPDATE VALUE...
        //Открыть addController и заполнить его имеющемися значениями
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/newScene.fxml"));
        try {
            Parent parent = loader.load();
            addController controller = loader.getController();
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
            Main.stg.setScene(scene);

            StringBuilder sb = new StringBuilder();
            for(Node n : noteText.getChildren()) {
                if (n instanceof Text) {
                    sb.append(((Text) n).getText());
                }
            }
            controller.change(id, noteName.getText(), sb.toString(), noteDeadline.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void delete(ActionEvent event) {
        Connection connection = Main.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM note WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Note(int id, String name, String text, String deadline) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/custom.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            this.id = id;
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

    public int getNoteId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
