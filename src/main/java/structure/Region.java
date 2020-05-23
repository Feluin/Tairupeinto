package structure;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Region {
    private List<Tile> tiles = new ArrayList<>();

    public RegionColor getColor() {
        return color;
    }

    private RegionColor color = RegionColor.NONE;
    private Integer regionID;

    public Region(Integer regionID) {
        this.regionID = regionID;
    }

    public void addTile(Tile tile) {
        tiles.add(tile);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void paint(RegionColor color) {
        this.color = color;
        tiles.forEach(tile -> tile.paint(color));

    }

    public Integer getRegionID() {
        return regionID;
    }

    public Set<Integer> activeCombinations = new HashSet<>();

    public void removeCombination(int id) {
        activeCombinations.remove(id);
        if(activeCombinations.isEmpty()){
            color=RegionColor.NONE;
        }
    }

    public void addCombination(int id) {
        activeCombinations.add(id);
    }

    public boolean isMarked() {
        return activeCombinations.size() != 0;
    }
}
