package sample;

import structure.Region;
import structure.RegionColor;
import structure.Structure;

import java.util.ArrayList;
import java.util.List;

public class BruteForceAlgorithm implements IPuzzleAlgorithm {
    private Structure structure;
    private boolean solved;

    public BruteForceAlgorithm(Structure structure) {

        this.structure = structure;
        structure.getRegions().parallelStream().forEach(region -> region.paint(RegionColor.WHITE));
    }

    @Override
    public void step() throws NotSolvablePuzzleException {
        if(solved)return;
        List<Region> blackregions=new ArrayList<>();
        int i=0;
        Region current;
        boolean painted=false;
        while (!painted){
            if(i>=structure.getRegions().size()){
                throw new NotSolvablePuzzleException();
            }
           current=structure.getRegions().get(i);
            i++;
            switch (current.getColor()) {
                case BLACK:
                    blackregions.add(current);
                    break;
                case WHITE:
                    blackregions.parallelStream().forEach(region -> region.paint(RegionColor.WHITE));
                    current.paint(RegionColor.BLACK);
                    painted=true;
                    break;
            }
        }
        solved=PuzzleManager.isSolved(structure);
    }

    @Override
    public void solve() throws NotSolvablePuzzleException {
        while(!solved){
            step();
        }
    }
}
