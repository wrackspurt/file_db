package start;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import static java.lang.System.exit;

public class StartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane contentArea;

    @FXML
    private Button createbtn;

    @FXML
    private Button editbtn;

    @FXML
    private Button closebtn;

    @FXML
    private Button rollbtn;

    @FXML
    private Button findbtn;

    @FXML
    private Button showbtn;

    @FXML
    private Button deletebtn;

    @FXML
    private Button addbtn;


    @FXML
    void close(MouseEvent event) {
        exit(0);

    }

    double x = 0, y = 0;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
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
        createbtn.setOnAction(event -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/creation/createdb.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        editbtn.setOnAction(event -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/editing/edit.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        showbtn.setOnAction(event -> {
            Parent fxml = null;
            try {
                fxml = FXMLLoader.load(getClass().getResource("/show/show.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        findbtn.setOnAction(event -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/search/search.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addbtn.setOnAction(event -> {
            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("/adding/add.fxml"));
                contentArea.getChildren().removeAll();
                contentArea.getChildren().setAll(fxml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}




