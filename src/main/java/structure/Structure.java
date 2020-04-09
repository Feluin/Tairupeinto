package structure;

import java.util.List;

public class Structure {


    private List<Integer> horizontalNrs;
    private List<Integer> vertialNrs;
    private List<Region> regions;
    private Tile[][] tiles;

    public void setHorizontalNrs(List<Integer> horizontalNrs) {

        this.horizontalNrs = horizontalNrs;
    }

    public void setVerticalNrs(List<Integer> vertialNrs) {
        this.vertialNrs = vertialNrs;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public List<Integer> getVertialNrs() {
        return vertialNrs;
    }

    public List<Integer> getHorizontalNrs() {
        return horizontalNrs;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
