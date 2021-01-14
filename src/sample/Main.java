package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    //TODO: Git
    //TODO: Показівать количество заметок при входе
    //TODO: Подключить базу данных
    //TODO: Обработка Exception'ов
    //TODO: Редактирование заметок
    //TODO: Попробовать не с listView, а с VBox


    public static void main(String[] args) {
        launch(args);
    }

    static Stage stg;
    static Scene scene;
    static ArrayList<Note> notes = new ArrayList<>();

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
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        Font.loadFont(getClass().getResourceAsStream("style/Lobster-Regular.ttf"), 27);
        scene = new Scene(rootNode, 400, 600);
        stg.setScene(scene);
        stg.getScene().getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
        stg.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

}
