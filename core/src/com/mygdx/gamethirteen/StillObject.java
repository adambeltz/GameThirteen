package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

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
        Gdx.app.log("new","StillObject");
        StillObject newAO = new StillObject(GameThirteenMain.assets.arrayAtlasRegions.get(MathUtils.random(0,4)), MathUtils.random(2f, 4f), new Vector2(MathUtils.random(0f, 100f), MathUtils.random(0f, Constants.VIEWPORT_HEIGHT-5)));
        for (int i = 0; i < objectsArray.size; i++) {

            if (newAO.rectangle.overlaps(objectsArray.get(i).rectangle) && !(newAO.rectangle.equals(objectsArray.get(i).rectangle))) {
                objectsArray.pop();
                break;
            }
        }


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



















