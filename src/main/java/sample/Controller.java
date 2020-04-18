package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import structure.Structure;
import structure.loadStructure.LoadStucture;

import java.io.IOException;
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
        if (isLocked) {
            return;
        }
        isLocked = true;
        String selectedAlgorithm = ((RadioMenuItem) toggle.getSelectedToggle()).getText();
        algorithmMenu.setText(selectedAlgorithm);
        algorithmMenu.setDisable(true);
        manager.setAlgoritm(Algorithm.valueOf(selectedAlgorithm));
    }

    public void close(Event actionEvent) {
        Platform.exit();

    }

    private ObjectMapper mapper = new ObjectMapper();

    public void importJson(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.getFiles().size() == 1) {
            try {
                LoadStucture loadStucture = mapper.readValue(dragboard.getFiles().get(0), LoadStucture.class);
                Structure structure = StructureTransformer.transform(loadStucture);
                load(structure);
            } catch (IOException | InvalidStructureExeption e) {
                e.printStackTrace();
            }
        }
    }


    public void dragover(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    }
}
