package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AnimatedObject extends GameObject {

    public Animation<TextureRegion> animation;

    // For animated objects
    public TextureRegion currentFrame;
    float stateTime;


    public AnimatedObject(Array<TextureAtlas.AtlasRegion> AR, float sizeF, Vector2 coords) {
        super(AR, sizeF, coords);
    }

    @Override
    public void buildAnimatedSprite(Array<TextureAtlas.AtlasRegion> AR) {
        animation = new Animation<TextureRegion>(.10f, AR, Animation.PlayMode.LOOP);
        aspectRatio = (float) AR.get(0).getRegionHeight() / (float) AR.get(0).getRegionWidth();
    }


    public void render(SpriteBatch batch, float delta) {
        super.render(batch, delta);
        stateTime += delta;
        currentFrame = animation.getKeyFrame(stateTime);
        batch.draw(currentFrame, currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
    }

    @Override
    public void collisionCheck(float delta) {
        super.collisionCheck(delta);

    }

    @Override
    public void newObject() {
        Gdx.app.log("new","AnicatmedObject");

        AnimatedObject newAO = new AnimatedObject(GameThirteenMain.assets.arrayKeyFrames.get(MathUtils.random(0,3)), MathUtils.random(0.5f,4f), new Vector2(MathUtils.random(0f, 100f), MathUtils.random(0f, Constants.VIEWPORT_HEIGHT - 5)));
        for (int i = 0; i < objectsArray.size; i++) {

            if (newAO.rectangle.overlaps(objectsArray.get(i).rectangle) && !(newAO.rectangle.equals(objectsArray.get(i).rectangle))) {
                objectsArray.pop();
                break;
            }
        }


    }
}








