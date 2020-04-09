package structure.loadStructure;

import structure.Region;

import java.util.List;

public class LoadStucture {

    private Integer[][] tilesWithRegionNr;


    private List<Integer> vertialNrs;
    private List<Integer> horizontalNrs;

    public List<Integer> getHorizontalNrs() {
        return horizontalNrs;
    }

    public void setHorizontalNrs(List<Integer> horizontalNrs) {
        this.horizontalNrs = horizontalNrs;
    }

    public List<Integer> getVertialNrs() {
        return vertialNrs;
    }

    public void setVertialNrs(List<Integer> vertialNrs) {
        this.vertialNrs = vertialNrs;
    }

    public int getColumnSize(){
        return vertialNrs.size();
    }
    public  int getRowSize(){
        return horizontalNrs.size();
    }

    public Integer[][] getTilesWithRegionNr() {
        return tilesWithRegionNr;
    }

    public void setTilesWithRegionNr(Integer[][] tilesWithRegionNr) {
        this.tilesWithRegionNr = tilesWithRegionNr;
    }
}
