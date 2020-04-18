package sample;

import graphical.PlayTilePane;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import structure.RegionColor;
import structure.Structure;
import structure.Tile;

import java.util.Arrays;


public class PuzzleManager {

    private final GridPane playGrid;
    private final Structure structure;
    private IPuzzleAlgorithm puzzleAlgorithm;
    private PlayTilePane[][] playTilePanes;

    public PuzzleManager(GridPane playGrid, Structure structure) {
        this.playGrid = playGrid;
        this.structure = structure;
        Tile[][] tiles = structure.getTiles();
        playTilePanes = new PlayTilePane[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            playTilePanes[i] = new PlayTilePane[tiles[i].length];
            for (int j = 0; j < tiles[i].length; j++) {
                PlayTilePane pane = new PlayTilePane(tiles[i][j]);
                playTilePanes[i][j] = pane;
                playGrid.add(pane, i, j);
            }
        }
    }

    public static boolean isSolved(Structure structure) {
        for (int i = 0; i < structure.getHorizontalNrs().size(); i++) {
            if (structure.getHorizontalNrs().get(i) != Arrays.stream(structure.getTiles()[i]).parallel().filter(tile -> tile.getColor().equals(RegionColor.BLACK)).count()) {
                return false;
            }
        }
        for (int i = 0; i < structure.getHorizontalNrs().size(); i++) {
            final int n = i;
            if (structure.getVertialNrs().get(i) != Arrays.stream(structure.getTiles()).parallel().map(tiles -> tiles[n]).filter(tile -> tile.getColor().equals(RegionColor.BLACK)).count()) {
                return false;
            }
        }
        return true;
    }

    public void step() {

        try {
            puzzleAlgorithm.step();
            updateGui();
        } catch (NotSolvablePuzzleException e) {
            e.printStackTrace();
        }
    }

    public void setAlgoritm(Algorithm algoritm) {
        switch (algoritm) {
            case BruteForce:
                puzzleAlgorithm = new BruteForceAlgorithm(structure,this);
                break;
            case Logical:
                puzzleAlgorithm = new LogicalAlgorithm(structure,this);
                break;

        }
    }

    public void solve() {
        try {
            puzzleAlgorithm.solve();
        } catch (NotSolvablePuzzleException e) {
            e.printStackTrace();
        }
    }

    public void updateGui() {
        Platform.runLater(() -> {
                    Tile[][] tiles = structure.getTiles();
                    for (int i = 0; i < tiles.length; i++) {
                        for (int j = 0; j < tiles[i].length; j++) {
                            playTilePanes[i][j].painted(tiles[i][j].getColor());
                        }
                    }
                }

        );
        ;
    }
}


