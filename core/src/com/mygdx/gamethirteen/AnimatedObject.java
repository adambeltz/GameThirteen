package com.mygdx.gamethirteen;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AnimatedObject {

    public Animation<TextureRegion> animation;

    public float velocity;
    public Vector2 lastCoords;
    public Vector2 currentCoords;
    public Vector2 nextCoords;
    public float direction;
    public Vector2 size;




    public Rectangle rectangle;


    // For animated objects
    public static Array<AnimatedObject> animatedObjects = new Array<AnimatedObject>();
    public TextureRegion currentFrame;
    float stateTime;


    public AnimatedObject(Array<TextureAtlas.AtlasRegion> AR, float sizeF, Vector2 coords) {
        animation = new Animation<TextureRegion>(.10f, AR, Animation.PlayMode.LOOP);
        size = new Vector2();


        velocity = 0;
        lastCoords = new Vector2();
        currentCoords = coords;
        nextCoords = new Vector2();
        direction = 0;
        setSize(sizeF);
        createRectangle();
        animatedObjects.add(this);


    }

    public void setSize(float x){
        size.x = x;
        size.y = x;



    }

    public void update(){


        rectangle.set(currentCoords.x, currentCoords.y, size.x, size.y);
    }

    public void render(SpriteBatch batch, float delta){
        stateTime += delta;
        currentFrame = animation.getKeyFrame(stateTime);
        batch.draw(currentFrame, currentCoords.x, currentCoords.y, size.x, size.y);
    }

    public void setPosition(Vector2 coords){
        currentCoords.x = coords.x;
        currentCoords.y = coords.y;
    }

    public void createRectangle(){
        rectangle = new Rectangle(currentCoords.x, currentCoords.y, size.x, size.y);
    }



}

