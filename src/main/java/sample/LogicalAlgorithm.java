package sample;

import sample.logicalAlgorithm.LineCombination;
import sample.logicalAlgorithm.PuzzleLine;
import sample.logicalAlgorithm.RegionIdColor;
import structure.Region;
import structure.Structure;
import structure.Tile;

import java.util.*;

public class LogicalAlgorithm implements IPuzzleAlgorithm {

    private final Structure structure;
    private final PuzzleManager puzzleManager;
    private final List<PuzzleLine> puzzleLines;
    private final Stack<LineCombination> activeLines = new Stack<>();
    private int currentLine = 0;

    public LogicalAlgorithm(Structure structure, PuzzleManager puzzleManager) {
        // PuzzleLine
        this.structure = structure;
        this.puzzleManager = puzzleManager;
        puzzleLines = getPuzzlelineCombinations(structure);
        puzzleLines.sort(Comparator.comparingInt(value -> value.getPossibleCombinations().size()));

    }

    @Override
    public void step() {
        PuzzleLine puzzleLine = puzzleLines.get(currentLine);

        try {

            LineCombination nextCombination = puzzleLine.getNextCombination();
            System.out.println("Testing "+currentLine+"/"+puzzleLines.size()+" Combination: "+puzzleLine.getNR());
            testLineCombination(nextCombination);
        } catch (InvalidLineException e) {
            currentLine--;
            LineCombination pop = activeLines.pop();
            for (RegionIdColor regionIdColor : pop.getRegionColorPairList()) {
                structure.getRegions().get(regionIdColor.getId()).removeCombination(pop.getId());
            }

        }
    }

    private void testLineCombination(LineCombination nextCombination) {
        try {
            acceptLineCombination(nextCombination);
            currentLine++;
            activeLines.push(nextCombination);
            if(currentLine==puzzleLines.size()){
                solved=true;
                return;
            }
            puzzleLines.get(currentLine).reset();
            for (RegionIdColor regionIdColor : nextCombination.getRegionColorPairList()) {
                structure.getRegions().get(regionIdColor.getId()).addCombination(nextCombination.getId());
            }
        } catch (InvalidLineException ignored) {
        }
    }

    boolean solved=false;
    @Override
    public void solve() {
        Thread calc = new Thread(() -> {
            while (!solved) {
                step();
            }
        });
        calc.start();
        Thread updategui = new Thread(() -> {
            while (!solved){
                puzzleManager.updateGui();
                try {
                    // give him time to repaint
                    //for faster bruteforcing repaint less
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        updategui.start();
    }

    private List<PuzzleLine> getPuzzlelineCombinations(Structure structure) {
        List<PuzzleLine> combinations = new ArrayList<>();
        List<Integer> horizontalNrs = structure.getHorizontalNrs();
        for (int i = 0; i < horizontalNrs.size(); i++) {
            Integer horizontalNr = horizontalNrs.get(i);
            int finalI = i;
            PuzzleLine puzzleLine = new PuzzleLine(Arrays.stream(structure.getTiles()).map(tiles -> tiles[finalI]).toArray(Tile[]::new), horizontalNr);
            combinations.add(puzzleLine);
        }

        List<Integer> verticalNrs = structure.getVerticalNrs();
        for (int i = 0; i < verticalNrs.size(); i++) {
            Integer verticalN = verticalNrs.get(i);
            PuzzleLine puzzleLine = new PuzzleLine(structure.getTiles()[i], verticalN);
            combinations.add(puzzleLine);

        }
        return combinations;
    }

    private void acceptLineCombination(LineCombination lineCombination) throws InvalidLineException {
        for (RegionIdColor regionIdColor : lineCombination.getRegionColorPairList()) {
            Region region = structure.getRegions().get(regionIdColor.getId());
            if (region.isMarked() && region.getColor() != regionIdColor.getRegionColor()) {
                throw new InvalidLineException();
            }
            region.paint(regionIdColor.getRegionColor());
        }
    }

}
