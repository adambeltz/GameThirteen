package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;



public abstract class GameObject {
    public static Array<GameObject> stillObjects = new Array<GameObject>();
    public Vector2 velocity;
    public Vector2 lastCoords;
    public Vector2 currentCoords;
    public Vector2 sizeV2;
    public float direction;
    public float sizeFloat;
    public Rectangle rectangle;
    public float aspectRatio;


    public GameObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        this.sizeFloat = sizeF;
        sizeV2 = new Vector2(1, 1);
        velocity = new Vector2(0, 0);
        currentCoords = new Vector2(coords);
        buildSprite(AR);
        createRectangle();
        this.sizeV2.x = sizeFloat * aspectRatio;
        this.sizeV2.y = sizeFloat * aspectRatio;
        setSize();
        GameObject.stillObjects.add(this);

    }


    public GameObject(Array<TextureAtlas.AtlasRegion> AR, float sizeF, Vector2 coords) {
        this.sizeFloat = sizeF;
        sizeV2 = new Vector2(1, 1);
        velocity = new Vector2(0, 0);
        currentCoords = new Vector2(coords);
        buildAnimatedSprite(AR);
        createRectangle();
        this.sizeV2.x = sizeFloat * aspectRatio;
        this.sizeV2.y = sizeFloat * aspectRatio;
        GameObject.stillObjects.add(this);
    }



    public void buildSprite(TextureAtlas.AtlasRegion ar) {}

    public void buildAnimatedSprite(Array<TextureAtlas.AtlasRegion> AR) {}



    public void render(SpriteBatch batch, float delta) {

    }

    public void update(float delta){
        move(delta);
        rectangle.set(currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
    }

    protected  void move(float delta){
        collisionCheck();
        currentCoords.x += velocity.x * delta;
        currentCoords.y += velocity.y * delta;
    }

    public void collisionCheck() {
        for (int i = 0; i < stillObjects.size; i++) {
            // Collision
            if (this.rectangle.overlaps(stillObjects.get(i).rectangle) && !(this.rectangle.equals(stillObjects.get(i).rectangle))) {


                if (this.rectangle.x > stillObjects.get(i).rectangle.x) {
                    // collision on left
                    velocity.x = MathUtils.random(stillObjects.get(i).velocity.x, 20f);
                    stillObjects.get(i).velocity.x = MathUtils.random(-20f, 0);
                } else if (this.rectangle.x < stillObjects.get(i).rectangle.x) {
                    velocity.x = MathUtils.random(-20f, stillObjects.get(i).velocity.x);
                    stillObjects.get(i).velocity.x = MathUtils.random(0f, 20f);
                } else if (this.rectangle.y > stillObjects.get(i).rectangle.y) {
                    velocity.y = MathUtils.random(stillObjects.get(i).velocity.y, 20f);
                    stillObjects.get(i).velocity.y = MathUtils.random(-20f, 0);
                } else if (this.rectangle.y < stillObjects.get(i).rectangle.x) {
                    velocity.y = MathUtils.random(-20f, stillObjects.get(i).velocity.y);
                    stillObjects.get(i).velocity.y = MathUtils.random(-0f, 20f);

                }
            }
        }


        if (this.rectangle.overlaps(GameThirteenMain.borders[0])) {
            this.velocity.y = MathUtils.random(0f, 10f);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[1])) {
            this.velocity.x = MathUtils.random(0f, 10f);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[2])) {
            this.velocity.y = MathUtils.random(-10f, 0f);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[3])) {
            this.velocity.x = MathUtils.random(-10f, 0f);
        }


    }


    public void setSize(){

    }

    public void createRectangle(){
        rectangle = new Rectangle(currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
    }
}
