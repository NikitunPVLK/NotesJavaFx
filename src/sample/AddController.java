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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddController {


    //LocalDate localDate = LocalDate.now(); - время добавления

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

    private Integer id;

    @FXML
    void save(ActionEvent event) {
        //Insert into
        PreparedStatement ps = null;
        try {
            if (id != null) {
                ps = Main.connection.prepareStatement("UPDATE note SET title = ?, note = ?, deadline = ? WHERE id = ?");
                ps.setInt(4, this.id);
            } else {
                ps = Main.connection.prepareStatement("INSERT INTO note (title, note , deadline) VALUES(?,?,?)");
            }
            ps.setString(1, name.getText());
            ps.setString(2, note.getText());
            try {
                ps.setDate(3, Date.valueOf(deadline.getValue()));
            }catch (NullPointerException e){
                ps.setDate(3, null);
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Main.notes.add(new Note(name.getText(), note.getText(), deadline.getValue().toString()));
        goBack();
    }

    private void goBack() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/notesList.fxml"));
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

    public void change(int id, String name, String note, String deadline) {
        this.id = id;
        this.name.setText(name);
        this.note.setText(note);
        try {
            this.deadline.setValue(LocalDate.parse(deadline));
        }catch (DateTimeParseException e){
            this.deadline.setValue(null);
        }
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setDeadline(Date deadline) {
        this.deadline.setChronology(deadline.toLocalDate().getChronology());
    }

    public void setNote(String note) {
        this.note.setText(note);
    }

}
