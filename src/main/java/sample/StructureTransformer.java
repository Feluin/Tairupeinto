package sample;

import structure.Region;
import structure.Structure;
import structure.Tile;
import structure.loadStructure.LoadStucture;

import java.util.HashMap;

public class StructureTransformer {


    public static Structure transform(LoadStucture loadStucture) throws InvalidStructureExeption {
        Structure structure = new Structure();
        Integer[][] tilesWithRegionNr = loadStucture.getTilesWithRegionNr();
        HashMap<Integer, Region> regionHashMap = new HashMap<>();
        Tile[][] tiles = new Tile[tilesWithRegionNr.length][];
        for (int i = 0; i < tilesWithRegionNr.length; i++) {
            tiles[i]=new Tile[tilesWithRegionNr[i].length];
            for (int j = 0; j < tilesWithRegionNr[i].length; j++) {
                Region region;
                if(regionHashMap.containsKey(tilesWithRegionNr[i][j])){
                    region=regionHashMap.get(tilesWithRegionNr[i][j]);
                }else {
                    region=new Region(tilesWithRegionNr[i][j]);
                    regionHashMap.put(tilesWithRegionNr[i][j],region);
                }
                tiles[i][j]=new Tile(i,j,region);
                region.addTile(tiles[i][j]);
            }
        }
        structure.setRegions(regionHashMap);
        structure.setTiles(tiles);
        structure.setHorizontalNrs(loadStucture.getHorizontalNrs());
        structure.setVerticalNrs(loadStucture.getVertialNrs());
        return structure;
    }
}