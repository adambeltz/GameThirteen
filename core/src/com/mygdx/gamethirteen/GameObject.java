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
    public Array<Rectangle> borders = new Array<Rectangle>();


    public GameObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        this.sizeFloat = sizeF;
        sizeV2 = new Vector2(1, 1);
        velocity = new Vector2(0, 0);
        currentCoords = new Vector2(coords);
        buildSprite(AR);
        createRectangle();
        createBorders();
        this.sizeV2.x = sizeFloat;
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
        createBorders();
        this.sizeV2.x = sizeFloat;
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
        updateBorders();
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
               Array<Rectangle> temp = stillObjects.get(i).borders;

                    // Collision on top
                    if (this.rectangle.overlaps(stillObjects.get(i).borders.get(0))){
                        this.velocity.y = MathUtils.random(-20f, stillObjects.get(i).velocity.y);
                        stillObjects.get(i).velocity.y *= -1;
                    }

                // Collision on Right
                if (this.rectangle.overlaps(stillObjects.get(i).borders.get(1))){
                    this.velocity.x = MathUtils.random(-20f, stillObjects.get(i).velocity.x);
                    stillObjects.get(i).velocity.x *= -1;

                }

                // Collision on left
                if (this.rectangle.overlaps(stillObjects.get(i).borders.get(2))){
                    this.velocity.x = MathUtils.random(stillObjects.get(i).velocity.x, 20f);
                    stillObjects.get(i).velocity.x *= -1;

                }

                // Collision on bottom
                if (this.rectangle.overlaps(stillObjects.get(i).borders.get(3))){
                    this.velocity.y = MathUtils.random(stillObjects.get(i).velocity.y, 20f);
                    stillObjects.get(i).velocity.y *= -1;
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

    public void createBorders(){
        borders.add(new Rectangle(currentCoords.x, currentCoords.y, sizeV2.x, .1f));
        borders.add(new Rectangle(currentCoords.x, currentCoords.y, .1f, sizeV2.y));
        borders.add(new Rectangle(sizeV2.x, currentCoords.y, -.1f, sizeV2.y));
        borders.add(new Rectangle(currentCoords.x, sizeV2.y, sizeV2.x, -.1f));
    }

    public void updateBorders(){
        borders.set(0, new Rectangle(currentCoords.x, currentCoords.y, sizeV2.x, .1f));
        borders.get(1).set(currentCoords.x, currentCoords.y, .1f, sizeV2.y);
        borders.get(2).set(currentCoords.x + sizeV2.x, currentCoords.y, -.1f, sizeV2.y);
        borders.get(3).set(currentCoords.x, currentCoords.y + sizeV2.y, sizeV2.x, -.1f);
    }
}
