package sample;

import sample.logicalAlgorithm.LineCombination;
import sample.logicalAlgorithm.PuzzleLine;
import sample.logicalAlgorithm.RegionIdColor;
import structure.Region;
import structure.RegionColor;
import structure.Structure;
import structure.Tile;

import java.util.*;

public class LogicalAlgorithm implements IPuzzleAlgorithm {

    private final Structure structure;
    private final PuzzleManager puzzleManager;
    private final List<PuzzleLine> puzzleLines;
    private final Stack<PuzzleLine> activeLines=new Stack<>();
    private int currentLine=0;

    public LogicalAlgorithm(Structure structure, PuzzleManager puzzleManager) {
        // PuzzleLine
        this.structure = structure;
        this.puzzleManager = puzzleManager;
        puzzleLines = getPuzzlelineCombinations(structure);
        puzzleLines.sort(Comparator.comparingInt(value -> value.getPossibleCombinations().size()));

    }

    @Override
    public void step() {
        PuzzleLine puzzleLine=puzzleLines.get(currentLine);
        try {
            LineCombination nextCombination = puzzleLine.getNextCombination();
            testLineCombination(nextCombination);
        } catch (InvalidLineException e) {
           currentLine--;
        }
    }

    private void testLineCombination(LineCombination nextCombination) {
        try {
            acceptLineCombination(nextCombination);
            currentLine++;
        } catch (InvalidLineException ignored) {

        }
    }

    @Override
    public void solve() {

    }

    private List<PuzzleLine> getPuzzlelineCombinations(Structure structure) {
        List<PuzzleLine> combinations = new ArrayList<>();
        List<Integer> verticalNrs = structure.getVertialNrs();
        for (int i = 0; i < verticalNrs.size(); i++) {
            Integer verticalN = verticalNrs.get(i);
            PuzzleLine puzzleLine = new PuzzleLine(structure.getTiles()[i], verticalN);
            combinations.add(puzzleLine);

        }
        List<Integer> horizontalNrs = structure.getHorizontalNrs();
        for (int i = 0; i < horizontalNrs.size(); i++) {
            Integer horizontalNr = horizontalNrs.get(i);
            int finalI = i;
            PuzzleLine puzzleLine = new PuzzleLine(Arrays.stream(structure.getTiles()).map(tiles -> tiles[finalI]).toArray(Tile[]::new), horizontalNr);
            combinations.add(puzzleLine);
        }
        return combinations;
    }

    private void acceptLineCombination(LineCombination lineCombination) throws InvalidLineException {
        for (RegionIdColor regionIdColor : lineCombination.getRegionColorPairList()) {
            Region region = structure.getRegions().get(regionIdColor.getId());
            if(region.getColor()== RegionColor.NONE){
                region.paint(regionIdColor.getRegionColor());
                continue;
            }
            if(region.getColor()!=regionIdColor.getRegionColor()){
                throw new InvalidLineException();

            }
        }

    }
}
