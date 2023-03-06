package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.R;
import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.ViewerCommand;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

public class GameViewer extends AbstractDrawable {
    private int scale = 64;
    Viewer root;
    Player player;
    Bitmap mur;
    Bitmap ground;
    Bitmap[] playerFrame;

    GameViewer(Player player) {
        this.player = player;
    }


    /**
     * Set the parent and get the root for catch the bitmap
     *
     * @param drawable parent
     */
    @Override
    public void setDrawableParent(Drawable drawable) {
        super.setDrawableParent(drawable);

        root = (Viewer) getDrawableRoot();
        resized(scale);
    }

    public void resized(int scale) {
        this.scale = scale;
        Bitmap murNotSized = BitmapFactory.decodeResource(root.getContext().getResources(), R.drawable.mur0);
        mur = ViewerCommand.resizeBitmap(murNotSized, scale, scale);

        Bitmap groundNotSized = BitmapFactory.decodeResource(root.getContext().getResources(), R.drawable.ground);
        ground = ViewerCommand.resizeBitmap(groundNotSized, scale, scale);
    }


    @Override
    public void paint(Canvas canvas, Paint paint) {
        Labyrinth labyrinth = player.getLaby();
        Coord playerCase = player.getCurrentCase().getCoord();
        for (int xSize = 0; xSize < labyrinth.getCOL(); xSize++) {
            for (int ySize = 0; ySize < labyrinth.getROW(); ySize++) {
                Case aCase = labyrinth.getCase(new Coord(xSize, ySize));
                if (aCase == null) {
                    canvas.drawBitmap(
                            mur,
                            (xSize - playerCase.getX()) * scale,
                            (ySize - playerCase.getY()) * scale,
                            paint
                    );
                } else {
                    canvas.drawBitmap(ground,
                            (xSize - playerCase.getX()) * scale,
                            (ySize - playerCase.getY()) * scale,
                            paint
                    );
                }
            }
        }
    }
}
