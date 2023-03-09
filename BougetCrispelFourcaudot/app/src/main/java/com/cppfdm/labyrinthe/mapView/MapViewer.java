package com.cppfdm.labyrinthe.mapView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Enemy;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

public class MapViewer extends AbstractDrawable {
    Viewer root;
    Player player;
    int scale;

    public MapViewer(Player player) {
        this.player = player;
    }

    @Override
    public void setDrawableParent(Drawable drawable) {
        super.setDrawableParent(drawable);
        root = (Viewer) getDrawableRoot();
    }

    @Override
    public void paint(Canvas canvas, Paint paint) {
        if (scale == 0) {
            Labyrinth laby = player.getLaby();
            scale = Math.min(root.getHeight(), root.getWidth()) / Math.max(laby.getCOL(), laby.getROW());
            System.out.println("ROOT: " + Math.min(root.getHeight(), root.getWidth()));
            System.out.println("LABY: " + Math.max(laby.getCOL(), laby.getROW()));
            System.out.println("MAP SCALE: " + scale);
        }
        Paint atCase = new Paint();
        atCase.setColor(Color.rgb(255, 255, 255));
        for (int xSize = 0; xSize < player.getLaby().getCOL(); xSize++) {
            for (int ySize = 0; ySize < player.getLaby().getROW(); ySize++) {
                if (player.getLaby().getCase(new Coord(xSize, ySize)) == null) {
                    continue;
                }
                drawMap(new Coord(xSize, ySize), canvas, atCase);
            }
        }
        Coord playerCoordinates = player.getCurrentCase().getCoord();
        Paint playerPaint = new Paint();
        playerPaint.setColor(Color.rgb(0,0,255));
        drawMap(playerCoordinates, canvas, playerPaint);

        Paint enemyPaint = new Paint();
        enemyPaint.setColor(Color.rgb(255,0,0));
        for (Enemy enemy: player.getLaby().getEnemies()) {
            drawMap(enemy.getPos(), canvas, enemyPaint);
        }

        Paint exitPaint = new Paint();
        exitPaint.setColor(Color.rgb(0,255,0));
        drawMap(player.getLaby().getEndCoord(), canvas, exitPaint);
    }

    public void drawMap(Coord coord, Canvas canvas, Paint paint) {
        canvas.drawRect(
                new Rect(
                        scale * coord.getX(),
                        scale * coord.getY(),
                        scale * (coord.getX()+1),
                        scale * (coord.getY()+1)
                ),
                paint
        );

    }
}
