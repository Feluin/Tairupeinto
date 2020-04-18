package sample.logicalAlgorithm;

import sample.NotSolvablePuzzleException;
import sample.logicalAlgorithm.LineCombination;
import structure.RegionColor;
import structure.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class PuzzleLine {

    private List<LineCombination> possibleCombinations = new ArrayList<>();


    public PuzzleLine(Tile[] tiles, int blackTiles) {
        HashMap<Integer, Integer> regionidNrmap = new HashMap<>();
        //try all possible combinations for this line
        for (Tile tile : tiles) {
            regionidNrmap.put(tile.getRegion().getRegionID(), regionidNrmap.getOrDefault(tile.getRegion().getRegionID(), 0) + 1);
        }
        List<RegionIdColor> regionIdColors = regionidNrmap.entrySet().stream().map(integerIntegerEntry -> new RegionIdColor(integerIntegerEntry.getKey(), integerIntegerEntry.getValue())).collect(Collectors.toList());
        RegionIdColor current;
        int i = 0;

        while (i <= regionIdColors.size()) {
            current = regionIdColors.get(i);
            i++;
            switch (current.getRegionColor()) {
                case BLACK:
                    current.setRegionColor(RegionColor.WHITE);
                    addIfPossible(regionIdColors,blackTiles);
                    break;
                case WHITE:
                    current.setRegionColor(RegionColor.BLACK);
                    regionIdColors.subList(0,i-1).forEach(region-> region.setRegionColor(RegionColor.WHITE));
                    i=0;
                    break;
            }
        }


    }

    private void addIfPossible(List<RegionIdColor> regionIdColors, int blackTiles) {
        if(regionIdColors.stream().filter(regionIdColor -> regionIdColor.getRegionColor()==RegionColor.BLACK).count()==blackTiles){
            possibleCombinations.add(new LineCombination(regionIdColors));
        }
    }

}

