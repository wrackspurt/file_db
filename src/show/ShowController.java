package show;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class ShowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane contentArea;

    @FXML
    private Button closebtn;

    @FXML
    private Button rollbtn;

    @FXML
    private Button returnbtn;


    @FXML
    private TextArea textArea;

    @FXML
    private Button showbtn;

    @FXML
    private ComboBox<String> choosedb;

    File fl = new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/databases/file_databases");

    ObservableList<String> dbses = FXCollections.observableArrayList(fl.list());


    @FXML
    void backToStart(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/editing/edit.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void close(MouseEvent event) {
        exit(0);

    }

    double x = 0, y = 0;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void roll_up_app(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void initialize() {
        choosedb.setItems(dbses);
        showbtn.setOnAction(event -> {

            try {
                File file = new File("C:/Users/" + System.getProperty("user.name") +
                        "/Downloads/databases/file_databases/" + choosedb.getSelectionModel().getSelectedItem());
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                while (line != null) {
                    textArea.appendText("\n" + line);
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        });

    }
}
