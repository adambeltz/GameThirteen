package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sun.org.apache.regexp.internal.RE;

public class StillObject extends GameObject {


    public Sprite sprite;


    public StillObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        super(AR, sizeF, coords);
    }

    public void buildSprite(TextureAtlas.AtlasRegion ar) {
        sprite = new Sprite(ar);
        aspectRatio = (float) sprite.getHeight() / (float) sprite.getWidth();
    }


    @Override
    public void setSize() {
        sprite.setSize(sizeV2.x, sizeV2.y);
    }

    @Override
    public void newObject() {
        StillObject newAO = new StillObject(GameThirteenMain.assets.arrayAtlasRegions.get(MathUtils.random(0,4)), 2, new Vector2(MathUtils.random(0f, 100f), MathUtils.random(0f, 50f)));
        for (int i = 0; i < stillObjects.size; i++) {

            if (newAO.rectangle.overlaps(stillObjects.get(i).rectangle) && !(newAO.rectangle.equals(stillObjects.get(i).rectangle))) {
                stillObjects.pop();
                break;
            }
        }
        Gdx.app.log("Stillobjects size: ", String.valueOf(stillObjects.size));

    }


    public void update(float delta) {
        super.update(delta);
        sprite.setPosition(currentCoords.x, currentCoords.y);

    }


    public void render(SpriteBatch batch, float delta) {
        super.render(batch,delta);
        sprite.draw(batch);
    }







}



















