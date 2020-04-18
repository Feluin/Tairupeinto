package sample;

import sample.logicalAlgorithm.PuzzleLine;
import structure.Structure;
import structure.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LogicalAlgorithm implements IPuzzleAlgorithm {

    private final Structure structure;
    private final PuzzleManager puzzleManager;
    private final List<PuzzleLine> puzzleLines;

    public LogicalAlgorithm(Structure structure, PuzzleManager puzzleManager) {
        // PuzzleLine
        this.structure = structure;
        this.puzzleManager = puzzleManager;
        puzzleLines = getPuzzlelineCombinations(structure);

    }

    @Override
    public void step() {

    }

    @Override
    public void solve() {

    }

    private List<PuzzleLine> getPuzzlelineCombinations(Structure structure) {
        List<PuzzleLine> combinations = new ArrayList<>();
        List<Integer> verticalNrs = structure.getVertialNrs();
        for (int i = 0; i < verticalNrs.size(); i++) {
            Integer verticalN = verticalNrs.get(i);
            combinations.add(new PuzzleLine(structure.getTiles()[i], verticalN));

        }
        List<Integer> horizontalNrs = structure.getHorizontalNrs();
        for (int i = 0; i < horizontalNrs.size(); i++) {
            Integer horizontalNr = horizontalNrs.get(i);
            int finalI = i;
            combinations.add(new PuzzleLine(Arrays.stream(structure.getTiles()).map(tiles -> tiles[finalI]).toArray(Tile[]::new), horizontalNr));
        }
        return combinations;
    }
}
