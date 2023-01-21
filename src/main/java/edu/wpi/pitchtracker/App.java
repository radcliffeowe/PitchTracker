package edu.wpi.pitchtracker;

import edu.wpi.pitchtracker.database.DatabaseManager;
import edu.wpi.pitchtracker.views.SceneManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;

@Slf4j
public class App extends Application {

  @Override
  public void init() {
    log.info("Starting Up");
  }

  @Override
  public void start(Stage primaryStage) {

    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/homePage.fxml"));

    try {
      DatabaseManager.getInstance().initializeDatabaseManager();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Scene scene = null;
    try {
      scene = new Scene(fxmlLoader.load());
    } catch (IOException e) {
      e.printStackTrace();
    }
    //SceneManager.getInstance().setStage(primaryStage);
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  @Override
  public void stop() {
    log.info("Shutting Down");
  }
}
