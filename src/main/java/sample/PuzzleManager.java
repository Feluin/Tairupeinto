package sample;

import graphical.PlayTilePane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import structure.RegionColor;
import structure.Structure;
import structure.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleManager {

    private final GridPane playGrid;
    private final Structure structure;
    private IPuzzleAlgorithm puzzleAlgorithm;

    public PuzzleManager(GridPane playGrid, Structure structure) {
        this.playGrid = playGrid;
        this.structure = structure;
        Tile[][] tiles = structure.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                playGrid.add(new PlayTilePane(tiles[i][j]), i, j);
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
        } catch (NotSolvablePuzzleException e) {
            e.printStackTrace();
        }
    }

    public void setAlgoritm(Algorithm algoritm) {
        switch (algoritm) {
            case BruteForce:
                puzzleAlgorithm = new BruteForceAlgorithm(structure);
                break;
            case Logical:
                puzzleAlgorithm = new LogicalAlgorithm(structure);
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
}
