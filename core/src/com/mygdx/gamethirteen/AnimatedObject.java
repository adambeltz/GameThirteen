package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
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
        stateTime += delta;
        currentFrame = animation.getKeyFrame(stateTime);
        batch.draw(currentFrame, currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
    }


}








