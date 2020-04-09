package sample;

import graphical.Numberlabel;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.*;
import structure.Structure;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    public HBox horizontalBox;
    public VBox verticalBox;
    public GridPane playGrid;
    public PuzzleManager manager;
    public ToggleGroup toggle;
    public Menu algorithmMenu;
    private boolean isLocked;

    public void load(Structure structure) {
        horizontalBox.getChildren().setAll(structure.getHorizontalNrs().stream().map(Numberlabel::new).collect(Collectors.toList()));
        horizontalBox.getChildren().forEach(node -> HBox.setHgrow(node, Priority.ALWAYS));
        verticalBox.getChildren().setAll(structure.getVertialNrs().stream().map(Numberlabel::new).collect(Collectors.toList()));
        verticalBox.getChildren().forEach(node -> VBox.setVgrow(node, Priority.ALWAYS));
        playGrid.getChildren().setAll();
        playGrid.getRowConstraints().setAll(structure.getVertialNrs().stream().map(integer -> new RowConstraints()).collect(Collectors.toList()));
        playGrid.getColumnConstraints().setAll(structure.getVertialNrs().stream().map(integer -> new ColumnConstraints()).collect(Collectors.toList()));
        manager = new PuzzleManager(playGrid, structure);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void step() {
        lockAlgorithm();
        manager.step();
    }

    public void solve() {
        lockAlgorithm();
        manager.solve();
    }

    private void lockAlgorithm() {
        if(isLocked){
            return;
        }
        isLocked=true;
        String selectedAlgorithm = ((RadioMenuItem) toggle.getSelectedToggle()).getText();
        algorithmMenu.setText(selectedAlgorithm);
        algorithmMenu.setDisable(true);
        manager.setAlgoritm(Algorithm.valueOf(selectedAlgorithm));
    }

    public void close(Event actionEvent) {
        Platform.exit();

    }

    public void importJson(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
    }
}
