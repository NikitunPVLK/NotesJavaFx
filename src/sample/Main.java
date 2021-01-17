package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.postgresql.Driver;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

import java.util.ArrayList;
import java.util.Optional;

public class Main extends Application {

    //TODO: Обработка Exception'ов
    //TODO: Редактирование заметок
    //TODO: Удаление заметок
    //TODO: Попробовать не с listView, а с VBox

    public static void main(String[] args) {
        connection = getConnection();
        launch(args);
    }

    static Connection connection;
    static Stage stg;
    static Scene scene;
    static ArrayList<Note> notes = new ArrayList<>();

    private static Connection getConnection(){
        String DBUrl = "jdbc:postgresql:Notes";
        String user = "postgres";
        String password = "pa2002nik";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBUrl, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void init() throws Exception {
        super.init();
        notes = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stg = primaryStage;
        stg.setResizable(false);
        stg.setTitle("Notes");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/sample.fxml"));
        AnchorPane rootNode = loader.load();
        Font.loadFont(getClass().getResourceAsStream("style/Lobster-Regular.ttf"), 27);
        scene = new Scene(rootNode, 400, 600);
        Controller controller = loader.getController();
        controller.showQuantity();
        controller.showNotes();
        stg.setScene(scene);
        stg.getScene().getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
        stg.show();
    }

    @Override
    public void stop() throws Exception {
        connection.close();
        super.stop();
    }

}
