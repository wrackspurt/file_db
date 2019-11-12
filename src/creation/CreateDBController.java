package creation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class CreateDBController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane contentArea;

    @FXML
    private TextField dbname;

    @FXML
    private Button gocreatebtn;

    @FXML
    private Button returnbtn;

    @FXML
    private Button closebtn;
    
    @FXML
    void backToStart(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/start/start.fxml"));
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
        gocreatebtn.setOnAction(event -> {
            if (dbname.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("warning");
                alert.setHeaderText("please, enter the name of your database");
                alert.showAndWait();
            }
            else {
                File file = new File("C:/Users/" + System.getProperty("user.name") +
                        "/Downloads/databases/file_databases/" + dbname.getText().trim() + ".txt");
                if (!file.exists() && !file.isDirectory()) {
                    try {
                        PrintWriter pw = new PrintWriter(file);
                        pw.flush();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("congrats!");
                    alert.setHeaderText("new database has been created :)");
                    alert.showAndWait();
                    try {
                        /*Parent fxml = FXMLLoader.load(getClass().getResource("/creation/addnotes.fxml"));
                        contentArea.getChildren().removeAll();
                        contentArea.getChildren().setAll(fxml);*/
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/creation/addnotes.fxml"));
                        Parent root = (Parent) loader.load();
                        AddNotesController addNotesController = loader.getController();
                        addNotesController.getFilename(dbname.getText().trim());
                        contentArea.getChildren().removeAll();
                        contentArea.getChildren().setAll(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setHeaderText("please, choose another name of database");
                    alert.showAndWait();
                }

            }

        });
    }
}
