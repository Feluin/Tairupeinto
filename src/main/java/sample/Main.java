package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import structure.Region;
import structure.Structure;
import structure.Tile;
import structure.loadStructure.LoadStucture;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample.fxml"));
            Parent root = loader.load();
            Controller c = loader.getController();
            primaryStage.setTitle("Hello World");
            primaryStage.setScene(new Scene(root, 300, 275));
            LoadStucture loadStucture = new LoadStucture();
            Integer[][] ar = new Integer[][]{
                    {1, 1, 2},
                    {1, 2, 2},
                    {3, 3, 2}};
            loadStucture.setTilesWithRegionNr(ar);
            loadStucture.setHorizontalNrs(List.of(2,1,0));
            loadStucture.setVertialNrs(List.of(2,1,0));
            Structure structure = StructureTransformer.transform(loadStucture);
            c.load(structure);
            primaryStage.show();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
