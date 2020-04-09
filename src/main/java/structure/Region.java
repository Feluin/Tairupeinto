package structure;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

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
       if (this.color != color){
            for (Tile tile : tiles) {
                tile.paint(color);
            }
        }
        this.color=color;

    }

    public Integer getRegionID() {
        return regionID;
    }
}
