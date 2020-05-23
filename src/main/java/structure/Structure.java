package structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Structure {


    private List<Integer> verticalNrs;
    private List<Integer> horizontalNrs;

    public HashMap<Integer, Region> getRegions() {
        return regions;
    }

    private HashMap<Integer,Region> regions;
    private Tile[][] tiles;

    public void setVerticalNrs(List<Integer> verticalNrs) {

        this.verticalNrs = verticalNrs;
    }

    public void setRegions(HashMap<Integer,Region> regions) {
        this.regions = regions;
    }

    public List<Region> getRegionsList() {
        return new ArrayList<>(regions.values());
    }

    public List<Integer> getHorizontalNrs() {
        return horizontalNrs;
    }

    public List<Integer> getVerticalNrs() {
        return verticalNrs;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setHorizontalNrs(List<Integer> horizontalNrs) {
        this.horizontalNrs = horizontalNrs;
    }
}
