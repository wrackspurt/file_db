package editing;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

import creation.AddNotesController;
import database.Backup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class EditController {

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
    private Button cleanbtn;

    @FXML
    private Button deletebtn;

    @FXML
    private Button addbtn;

    @FXML
    private Button modifybtn;

    @FXML
    private Button deletedbbtn;

    @FXML
    private Button restorebtn;


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
    private ComboBox<String> choosedb;

    File fl = new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/databases/file_databases");

    ObservableList<String> dbses = FXCollections.observableArrayList(fl.list());

    @FXML
    private ComboBox<String> choosbak;

    File bf = new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/databases/backup");

    ObservableList<String> back = FXCollections.observableArrayList(bf.list());

    @FXML
    void initialize() {
        choosedb.setItems(dbses);
        choosbak.setItems(back);
        cleanbtn.setOnAction(event -> {
            if (!choosedb.getSelectionModel().isEmpty()) {
                String fnm = "C:/Users/" + System.getProperty("user.name") + "/Downloads/databases/file_databases/" +
                        choosedb.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("clean database");
                alert.setHeaderText("are you sure that you want to delete all notes from the following database?");
                alert.setContentText(choosedb.getSelectionModel().getSelectedItem());
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK) {
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(fnm);
                        writer.print("");
                        writer.close();
                        Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                        alert0.setTitle("confirmation");
                        alert0.setHeaderText("the following database is empty now");
                        alert0.setContentText(choosedb.getSelectionModel().getSelectedItem());
                        alert0.showAndWait();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Alert at = new Alert(Alert.AlertType.ERROR);
                at.setTitle("error");
                at.setHeaderText("please, choose the database to continue");
                at.showAndWait();
            }


        });

        deletedbbtn.setOnAction(event -> {
            if (!choosedb.getSelectionModel().isEmpty()) {
                String filename = "C:/Users/" + System.getProperty("user.name") + "/Downloads/databases/file_databases/" +
                        choosedb.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("delete database");
                alert.setHeaderText("are you sure that you want to delete the following database?");
                alert.setContentText(choosedb.getSelectionModel().getSelectedItem());
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    File df = new File(filename);
                    df.delete();
                    Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("confirmation");
                    alert0.setHeaderText("the following database has been deleted");
                    alert0.setContentText(choosedb.getSelectionModel().getSelectedItem());
                    alert0.showAndWait();
                }
            } else {
                Alert at = new Alert(Alert.AlertType.ERROR);
                at.setTitle("error");
                at.setHeaderText("please, choose the database to continue");
                at.showAndWait();
            }

        });

        restorebtn.setOnAction(event -> {
            if (!choosbak.getSelectionModel().isEmpty()) {
                Backup bk = new Backup();
                try {
                    bk.restore(choosbak.getSelectionModel().getSelectedItem());
                    Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
                    alert0.setTitle("confirmation");
                    alert0.setHeaderText("the database restored successfully");
                    alert0.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert art = new Alert(Alert.AlertType.ERROR);
                art.setTitle("error");
                art.setHeaderText("please, choose the backup file to continue");
                art.showAndWait();
            }


        });



    }
}
