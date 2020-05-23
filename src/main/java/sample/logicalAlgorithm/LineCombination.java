package sample.logicalAlgorithm;

import structure.Region;

import java.util.ArrayList;
import java.util.List;

public class LineCombination {

    private List<RegionIdColor> regionColorPairList = new ArrayList<>();
    private final int id;
    private static int lineCombinatonIDcounter = 0;

    public LineCombination(List<RegionIdColor> regionIdColors) {
        id=lineCombinatonIDcounter;
        lineCombinatonIDcounter++;
        for (RegionIdColor orginal : regionIdColors) {
            RegionIdColor newRegionIdColor1 = new RegionIdColor(orginal.getId(), orginal.getCountInLine());
            newRegionIdColor1.setRegionColor(orginal.getRegionColor());
            regionColorPairList.add(newRegionIdColor1);
        }
    }

    public List<RegionIdColor> getRegionColorPairList() {
        return regionColorPairList;
    }

    public int getId() {
        return id;
    }
}
