package structure;

import graphical.NeighborDirection;
import sample.ITileListener;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private int x;
    private int y;
    private Region region;
    private RegionColor color;
    private List<ITileListener> iTileListenerList=new ArrayList<>();

    public Tile(int x, int y, Region region) {
        this.x = x;
        this.y = y;
        this.region = region;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void paint(RegionColor color) {
        iTileListenerList.forEach(listener -> listener.painted(color));
        this.color = color;
    }

    public Region getRegion() {
        return region;
    }
    public NeighborDirection isNeighbor(Tile tile){
        if(this.x==tile.x&&this.y==tile.y+1){
            return NeighborDirection.NORTH;
        }
        if(this.x==tile.x&&this.y==tile.y-1){
            return NeighborDirection.SOUTH;
        }
        if(this.x==tile.x-1&&this.y==tile.y){
            return NeighborDirection.EAST;
        }
        if(this.x==tile.x+1&&this.y==tile.y){
            return NeighborDirection.WEST;
        }
        return NeighborDirection.NONE;
    }

    public RegionColor getColor() {
        return color;
    }
    public void addListener(ITileListener listener){
        iTileListenerList.add(listener);
    }
}
