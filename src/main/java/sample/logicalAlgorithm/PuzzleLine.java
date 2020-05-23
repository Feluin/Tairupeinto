package sample.logicalAlgorithm;

import sample.InvalidLineException;
import sample.NotSolvablePuzzleException;
import sample.logicalAlgorithm.LineCombination;
import structure.RegionColor;
import structure.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class PuzzleLine {

    public List<LineCombination> getPossibleCombinations() {
        return possibleCombinations;
    }

    private List<LineCombination> possibleCombinations = new ArrayList<>();
    private int selectedCombination=0;

    public PuzzleLine(Tile[] tiles, int blackTiles) {
        HashMap<Integer, Integer> regionidNrmap = new HashMap<>();
        //try all possible combinations for this line
        for (Tile tile : tiles) {
            int regionID = tile.getRegion().getRegionID();
            regionidNrmap.put(regionID, regionidNrmap.getOrDefault(regionID, 0) + 1);
        }
        List<RegionIdColor> regionIdColors = regionidNrmap.entrySet().stream().map(idnrentry -> new RegionIdColor(idnrentry.getKey(), idnrentry.getValue())).collect(Collectors.toList());
        RegionIdColor current;
        int i = 0;

        while (i < regionIdColors.size()) {
            current = regionIdColors.get(i);

            addIfPossible(regionIdColors, blackTiles);
            switch (current.getRegionColor()) {
                case BLACK:
                    i++;
                    break;
                case WHITE:
                    current.setRegionColor(RegionColor.BLACK);
                    regionIdColors.subList(0, i).forEach(region -> region.setRegionColor(RegionColor.WHITE));
                    i = 0;
                    break;
            }
        }
    }

    private void addIfPossible(List<RegionIdColor> original, int blackTiles) {
        Optional<Integer> countedBlackTiles = original.stream().filter(regionIdColor -> regionIdColor.getRegionColor() == RegionColor.BLACK).map(RegionIdColor::getCountInLine).reduce(Integer::sum);
        if (countedBlackTiles.isPresent()&&countedBlackTiles.get() == blackTiles) {
            possibleCombinations.add(new LineCombination(original));
        }
    }


    public LineCombination getNextCombination() throws InvalidLineException{
        if(selectedCombination>possibleCombinations.size()-1){
            throw new InvalidLineException();
        }
        LineCombination current=possibleCombinations.get(selectedCombination);
        selectedCombination++;
        return current;

    }


    public String getNR() {
        return ""+selectedCombination+"/"+possibleCombinations.size();
    }

    public void reset() {
        selectedCombination=0;
    }
}

