package sample.logicalAlgorithm;

import structure.RegionColor;

public class RegionIdColor implements Cloneable{

    private final int id;
    private final int countInLine;

    private RegionColor regionColor=RegionColor.WHITE;

    public RegionIdColor(int id, int nr) {
        this.id = id;
        this.countInLine = nr;
    }

    public RegionColor getRegionColor() {
        return regionColor;
    }

    public void setRegionColor(RegionColor regionColor) {
        this.regionColor = regionColor;
    }

    public int getCountInLine() {
        return countInLine;
    }

    public int getId() {
        return id;
    }

}
