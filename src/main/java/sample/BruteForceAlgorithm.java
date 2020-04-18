package sample;

import javafx.application.Platform;
import structure.Region;
import structure.RegionColor;
import structure.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class BruteForceAlgorithm implements IPuzzleAlgorithm {
    private Structure structure;
    private PuzzleManager puzzleManager;
    private boolean solved;

    public BruteForceAlgorithm(Structure structure, PuzzleManager puzzleManager) {

        this.structure = structure;
        this.puzzleManager = puzzleManager;
        structure.getRegions().parallelStream().forEach(region -> region.paint(RegionColor.WHITE));
    }

    @Override
    public void step() throws NotSolvablePuzzleException {
        if (solved) return;
        List<Region> blackregions = new ArrayList<>();
        int i = 0;
        Region current;
        boolean painted = false;
        while (!painted) {
            if (i >= structure.getRegions().size()) {
                throw new NotSolvablePuzzleException();
            }
            current = structure.getRegions().get(i);
            i++;
            switch (current.getColor()) {
                case BLACK:
                    blackregions.add(current);
                    break;
                case WHITE:
                    blackregions.parallelStream().forEach(region -> region.paint(RegionColor.WHITE));
                    current.paint(RegionColor.BLACK);
                    painted = true;
                    break;
            }
        }
        solved = PuzzleManager.isSolved(structure);


    }

    @Override
    public void solve() throws NotSolvablePuzzleException {
        Thread calc = new Thread(() -> {
            while (!solved) {
                try {
                    step();
                } catch (NotSolvablePuzzleException e) {
                    e.printStackTrace();
                    solved=true;
                }
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
}
