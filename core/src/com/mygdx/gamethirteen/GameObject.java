package com.mygdx.gamethirteen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class GameObject {
    public Vector2 velocity;
    public Vector2 lastCoords;
    public Vector2 currentCoords;
    public Vector2 sizeV2;
    public float direction;
    public float sizeFloat;
    public Rectangle rectangle;


    public GameObject(float sizeF, Vector2 coords) {

        this.sizeFloat = sizeF;
        sizeV2 = new Vector2(1,1);
        velocity = new Vector2(0,0);

        currentCoords = new Vector2(coords);

        // PULL methods from a submethod

    }

    public GameObject(TextureAtlas.AtlasRegion ar, float sizeF, Vector2 coords){
        ar.getRotatedPackedWidth();
        

    }


    public static Array<GameObject> stillObjects = new Array<GameObject>();

    public String getDirection(GameObject gameObject){
        float x = gameObject.velocity.x;
        float y = gameObject.velocity.y;

        if (x > y && x > 0){
            return "moving east";
        }
        if (x > y && x < 0){
            return "moving west";
        }
        if (x < y && y > 0){
            return "moving north";
        }
        return "moving south";
    }

    public  void render(SpriteBatch batch, float delta){

    }

    public abstract void update();

    public  abstract void setSize();



    public abstract void createRectangle();
}
