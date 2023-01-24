package edu.wpi.pitchtracker.views;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import edu.wpi.pitchtracker.App;
import edu.wpi.pitchtracker.database.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML private AnchorPane anchorPane;
    @FXML private ImageView homeImage;
    @FXML private HBox menuBox;
    @FXML private Button exportButton;
    @FXML private Button pitchTrackerButton;

    public HomePageController() {}


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void exportToCSV() throws SQLException, IOException {
        FileChooser fChoose = new FileChooser();
        fChoose.setTitle("Choose CSV File");
        Stage stage = (Stage) exportButton.getScene().getWindow();
        File file = fChoose.showSaveDialog(stage);
        export(file);

        //    String path = file.getPath();
        //stage.close();
    }

    private void export(File file) throws SQLException, IOException {
        DatabaseManager.getInstance().getPitchesDAO().backUpToCSV(file);
    }

    @FXML
    public void openPitchTracker(){
        Parent root = null;
        try {
            root =
                    FXMLLoader.load(Objects.requireNonNull(App.class.getResource("views/pitchTracker.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window = SceneManager.getInstance().getStage();
        Scene scene1 = new Scene(root);
        window.setScene(scene1);
    }
}
