package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public void showNotes(){
        try {
            Statement st = Main.connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM note");
            while(rs.next()){
                list.getItems().add(new Note(rs.getInt("id"),rs.getString("title"),rs.getString("note"), rs.getDate("deadline").toString()));
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //select all
        //list.getItems().addAll
        //        (Main.notes.stream()
        //                .filter(n -> !list.getItems().contains(n))
        //                .collect(Collectors.toList()));
    }

    public void showQuantity(){
        //Количество строк в таблице
        Statement st = null;
        ResultSet rs = null;
        try {
            st = Main.connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) FROM note");
            if(rs.next()) {
                quantity.setText("Количество заметок: " + rs.getInt(1));
            }
            rs.close();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
