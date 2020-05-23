package graphical;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.ITileListener;
import structure.RegionColor;
import structure.Tile;

import java.util.ArrayList;
import java.util.List;

public class PlayTilePane extends Pane {


    public PlayTilePane(Tile tile) {
        this.setPrefHeight(100000);
        this.setPrefWidth(100000);
        drawBorder(tile);
        this.setBackground(gray);
    }

    private void drawBorder(Tile tile) {
        List<NeighborDirection> neighborDirections = new ArrayList<>();
        for (Tile nextTile : tile.getRegion().getTiles()) {
            if (tile.isNeighbor(nextTile) != NeighborDirection.NONE) {
                neighborDirections.add(tile.isNeighbor(nextTile));
            }
        }
        this.setBorder(createBorder(neighborDirections));

    }

    private Border createBorder(List<NeighborDirection> neighborDirections) {
        Color northColor = neighborDirections.contains(NeighborDirection.NORTH) ? Color.LIGHTGRAY : Color.BLACK;
        Color southColor = neighborDirections.contains(NeighborDirection.SOUTH) ? Color.LIGHTGRAY : Color.BLACK;
        Color eastColor = neighborDirections.contains(NeighborDirection.EAST) ? Color.LIGHTGRAY : Color.BLACK;
        Color westColor = neighborDirections.contains(NeighborDirection.WEST) ? Color.LIGHTGRAY : Color.BLACK;

        BorderStroke borderStroke = new BorderStroke(northColor, eastColor, southColor, westColor,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, createBordersWidth(neighborDirections), Insets.EMPTY);
        return new Border(borderStroke);
    }


    private BorderWidths createBordersWidth(List<NeighborDirection> neighborDirections) {
        return new BorderWidths(
                neighborDirections.contains(NeighborDirection.NORTH) ? 1 : 2,
                neighborDirections.contains(NeighborDirection.EAST) ? 1 : 2,
                neighborDirections.contains(NeighborDirection.SOUTH) ? 1 : 2,
                neighborDirections.contains(NeighborDirection.WEST) ? 1 : 2);
    }

    private static Background gray = new Background(new BackgroundFill(Color.gray(0.5), null, null));
    private static Background black = new Background(new BackgroundFill(Color.gray(0.1), null, null));
    private static Background white = new Background(new BackgroundFill(Color.WHITE, null, null));

    public void painted(RegionColor color) {
        if(color==null){
            return;
        }
        Platform.runLater(() -> {

                    switch (color) {
                        case BLACK:
                            this.setBackground(black);
                            break;
                        case WHITE:
                            this.setBackground(white);
                            break;
                        case NONE:

                            this.setBackground(gray);
                            break;

                    }
                }


        );

    }

}
