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

    public void update(float delta) {
        super.update(delta);
        sprite.setPosition(currentCoords.x, currentCoords.y);

    }


    public void render(SpriteBatch batch, float delta) {
        sprite.draw(batch);
    }





}



















