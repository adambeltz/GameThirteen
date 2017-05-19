package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class AnimatedObject extends GameObject{

    public Animation<TextureRegion> animation;







    public float delta;



    //public Rectangle rectangle;


    // For animated objects
    public static Array<AnimatedObject> animatedObjects = new Array<AnimatedObject>();
    public TextureRegion currentFrame;
    float stateTime;


    public AnimatedObject(Array<TextureAtlas.AtlasRegion> AR, float sizeF, Vector2 coords) {
        super(sizeF, coords);
        animation = new Animation<TextureRegion>(.10f, AR, Animation.PlayMode.LOOP);
        sizeV2 = new Vector2();


        velocity = new Vector2(0,0);

        currentCoords = coords;


        setSize();
        createRectangle();
        GameObject.stillObjects.add(this);


    }



    public void setSize(){
        sizeV2.x = sizeFloat;
        sizeV2.y = sizeFloat;



    }

    public void update(){
        move();

        rectangle.set(currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
    }

    public void render(SpriteBatch batch, float delta){
        stateTime += delta;
        currentFrame = animation.getKeyFrame(stateTime);
        batch.draw(currentFrame, currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
    }

    public void setPosition(Vector2 coords){
        currentCoords.x = coords.x;
        currentCoords.y = coords.y;
    }

    public void createRectangle(){
        rectangle = new Rectangle(currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
    }

    public void move(){
        currentCoords.x += velocity.x * delta;
        currentCoords.y += velocity.y * delta;
        collisionCheck();
    }

    public void collisionCheck() {
        for (int i = 0; i < stillObjects.size; i++) {
            // Collision
            if (this.rectangle.overlaps(stillObjects.get(i).rectangle) && !(this.rectangle.equals(stillObjects.get(i).rectangle))) {


                if (this.rectangle.x > stillObjects.get(i).rectangle.x && getDirection(stillObjects.get(i)).equals("moving west")){
                    // collision on left
                    velocity.x = MathUtils.random(stillObjects.get(i).velocity.x, 20f);
                } else if (this.rectangle.x < stillObjects.get(i).rectangle.x && getDirection(stillObjects.get(i)).equals("moving east")) {
                    velocity.x = MathUtils.random(-20f, stillObjects.get(i).velocity.x);
                } else if (this.rectangle.y > stillObjects.get(i).rectangle.y) {
                    velocity.y = MathUtils.random(stillObjects.get(i).velocity.y, 20f);
                } else if (this.rectangle.y < stillObjects.get(i).rectangle.x) {
                    velocity.y = MathUtils.random(-20f, stillObjects.get(i).velocity.y);
                }
            }
        }



        if (this.rectangle.overlaps(GameThirteenMain.borders[0])){
            Gdx.app.log("border", "bottom");
            this.velocity.y = MathUtils.random(0f, 10f);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[1])){
            Gdx.app.log("border", "left");
            this.velocity.x = MathUtils.random(0f, 10f);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[2])){
            Gdx.app.log("border", "top");
            this.velocity.y = MathUtils.random(-10f, 0f);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[3])){
            Gdx.app.log("border", "right");
            this.velocity.x = MathUtils.random(-10f, 0f);
        }


    }

    public String getDirection(StillObject stillObject){
        return super.getDirection(stillObject);
    }


}



