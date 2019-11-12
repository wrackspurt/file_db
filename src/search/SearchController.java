package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class SearchController {

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
    private ComboBox<String> choosedb;

    File fl = new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/databases/file_databases");

    ObservableList<String> dbses = FXCollections.observableArrayList(fl.list());

    @FXML
    private Button searchbtn;

    @FXML
    private TextField bookid;

    @FXML
    private TextField bookappl;

    @FXML
    private TextField bookauthor;

    @FXML
    private TextField bookyop;

    @FXML
    private TextField bookprice;

    @FXML
    private TextArea textArea;

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
        choosedb.setItems(dbses);
        searchbtn.setOnAction(event -> {
            textArea.clear();
            String fl = "C:/Users/" + System.getProperty("user.name") +
                    "/Downloads/databases/file_databases/" + choosedb.getSelectionModel().getSelectedItem();
            if (!bookid.getText().isEmpty()) {
                String str = bookid.getText().trim() + " ";
                try {
                    fileSearch(fl, str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if (!bookappl.getText().isEmpty()) {
                String str = bookappl.getText().trim() + " ";
                try {
                    fileSearch(fl, str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if (!bookauthor.getText().isEmpty()) {
                String str = bookauthor.getText().trim() + " ";
                try {
                    fileSearch(fl, str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if (!bookprice.getText().isEmpty()) {
                String str = bookprice.getText().trim() + " ";
                try {
                    fileSearch(fl, str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else if (!bookyop.getText().isEmpty()) {
                String str = bookyop.getText().trim();
                try {
                    fileSearch(fl, str);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText("please, enter some information for search");
                alert.showAndWait();
            }
        });

    }

    public void fileSearch(String flnm, String searchln) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(flnm));
        while(scan.hasNext()){
            String line = scan.nextLine().toLowerCase() + " ";
            if(line.contains(searchln)){
                textArea.appendText("\n" + line);
            }
        }
    }
}

