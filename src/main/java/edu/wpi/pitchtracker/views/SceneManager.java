package edu.wpi.pitchtracker.views;

import edu.wpi.pitchtracker.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private static SceneManager m_SceneManager = null;
    private String currentScene = "";
    private Stage currentStage;

    private SceneManager() {}

    /**
     * if instance is null creates
     *
     * @return the sole instance
     */
    public static SceneManager getInstance() {
        // separation for ease of control i.e initialization method
        if (m_SceneManager == null) m_SceneManager = new SceneManager();
        return m_SceneManager;
    }

    public String getCurrentScene() {
        return currentScene;
    }

    /**
     * Creates a scene and adds to hash table if scene already exists gets it from hash instead
     *
     * @param filename Takes a string to acess fxml file/scene
     * @return
     */

    /*
     * Singleton implementation with global access
     */
    public Scene setScene(String filename) throws IOException { // highlight tab /shift+tab
        Scene scene = null;
        /*
         * Checks the hashmap to see if filename already exists.
         */

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(filename));// alt enter while hover over/cursor in red
        scene = new Scene(fxmlLoader.load(), 1220, 660);

        currentScene = filename;
        return scene;
    }

    public void loadViews(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path));
        // alt enter while hover over/cursor in red
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1220, 660);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //    System.out.println("Loading Scene: " + path);
    }

    public Stage getStage() {
        return currentStage;
    }

    public void setStage(Stage stage) {
        currentStage = stage;
    }
}
