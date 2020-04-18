package sample.logicalAlgorithm;

import structure.RegionColor;

public class RegionIdColor {

    private final int id;
    private final int nr;

    private RegionColor regionColor=RegionColor.WHITE;

    public RegionIdColor(int id, int nr) {
        this.id = id;
        this.nr = nr;
    }

    public RegionColor getRegionColor() {
        return regionColor;
    }

    public void setRegionColor(RegionColor regionColor) {
        this.regionColor = regionColor;
    }

    public int getNr() {
        return nr;
    }

    public int getId() {
        return id;
    }
}
