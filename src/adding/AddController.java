package adding;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import database.Backup;
import database.BookDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class AddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane contentArea;

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
    private Button goaddbtn;

    @FXML
    private Button returnbtn;

    @FXML
    private Button bupbtn;

    @FXML
    private Button rollbtn;

    @FXML
    private Button closebtn;

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

    public void sort(String fl) throws IOException {
        String flfl = "C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + fl;
        String aflafl = "C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + "double" + fl;
        BufferedReader reader = null;
        PrintWriter outputStream = null;
        ArrayList<String> rows = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(flfl));
            outputStream = new PrintWriter(new FileWriter(aflafl));

            String file;
            while ((file = reader.readLine()) != null) {
                rows.add(file);
            }
            Collections.sort(rows);
            String[] strArr = rows.toArray(new String[0]);
            for (String cur : strArr)
                outputStream.println(cur);
            File ff = new File(flfl);
            ff.delete();
            File fie = new File(aflafl);
            fie.renameTo(new File(flfl));

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }

    public void deleteRedundant(String fl) {
        String laaa = "C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + "double" + fl;
        File fr = new File(laaa);
        fr.delete();
    }

    public boolean parseFile(String file, String str) {
        String q = str + " ";
        boolean ff = false;
        File fl = new File("C:/Users/" + System.getProperty("user.name") +
                "/Downloads/databases/file_databases/" + file);
        try {
            FileReader fileIn = new FileReader(fl);
            BufferedReader reader = new BufferedReader(fileIn);
            String line;
            while((line = reader.readLine()) != null) {
                if((line.contains(q))) {
                    ff = true;
                    break;
                } else
                    ff = false;
            }
        }catch (IOException e){
            System.out.println(e);
        }
        return ff;
    }

    @FXML
    void initialize() {
        choosedb.setItems(dbses);
        goaddbtn.setOnAction(event -> {
            if (bookid.getText().isEmpty() || bookappl.getText().isEmpty() ||
                    bookappl.getText().isEmpty() || bookauthor.getText().isEmpty() || bookyop.getText().isEmpty() ||
                    bookprice.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("warning");
                alert.setHeaderText("please, fill in the from");
                alert.showAndWait();
            } else {
                if(!parseFile(choosedb.getSelectionModel().getSelectedItem(), bookid.getText().trim())) {
                    String numberMatcher = "^-?\\d+$";
                    String doubleMatcher = "[+-]?([0-9]*[.])?[0-9]+";
                    if ((bookyop.getText().trim().matches(numberMatcher)) &&
                            (Integer.valueOf(bookyop.getText().trim()) >= 1500) &&
                            (Integer.valueOf(bookyop.getText().trim()) <= 2019) &&
                            (bookprice.getText().trim().matches(doubleMatcher)) &&
                            (bookid.getText().trim().matches(numberMatcher))) {
                        Integer id_db = Integer.valueOf(bookid.getText().trim());
                        String apl_db = bookappl.getText().trim();
                        String auth_db = bookauthor.getText().trim();
                        Integer yop_db = Integer.valueOf(bookyop.getText().trim());
                        Double price_db = Double.valueOf(bookprice.getText().trim());
                        BookDB bdb = new BookDB(id_db, apl_db, auth_db, yop_db, price_db);
                        try {
                            bdb.addAnotherNote(choosedb.getSelectionModel().getSelectedItem());
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("congrats!");
                            alert.setHeaderText("new notes have been added :)");
                            alert.showAndWait();
                            sort(choosedb.getSelectionModel().getSelectedItem());
                            deleteRedundant(choosedb.getSelectionModel().getSelectedItem());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("error");
                        alert.setHeaderText("something is incorrect (check year of publication, price or id)");
                        alert.setContentText("note: id must contain only figures");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setHeaderText("the given id is already added to this database");
                    alert.showAndWait();
                }

            }
        });
        bupbtn.setOnAction(event -> {
            try {
                Backup backup = new Backup();
                backup.createAnotherBackup(choosedb.getSelectionModel().getSelectedItem());
            } catch (IOException e){
                System.out.println(e);
            }
        });
    }
}
