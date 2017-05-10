package com.mygdx.gamethirteen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sun.org.apache.regexp.internal.RE;

public  class StillObject {




    public float velocity;
    public Vector2 lastCoords;
    public Vector2 currentCoords;
    public Vector2 nextCoords;
    public float direction;
    public Vector2 size;

    public Sprite sprite;
    public float spriteAspectRatio;

    public Rectangle rectangle;

    public static Array<StillObject> stillObjects = new Array<StillObject>();


    public StillObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        size = new Vector2();
        sprite = new Sprite(AR);
        spriteAspectRatio = (float)sprite.getHeight() / (float)sprite.getWidth();
        velocity = 0;
        lastCoords = new Vector2();
        currentCoords = coords;
        nextCoords = new Vector2();
        direction = 0;
        setSize(sizeF);
        createRectangle();
        stillObjects.add(this);


    }

    public void setSize(float x){
        this.size.x = x;
        this.size.y = x * spriteAspectRatio;
        sprite.setSize(size.x, size.y);

    }

    public void update(){

        collisionCheck();
        sprite.setPosition(currentCoords.x, currentCoords.y);
        rectangle.set(currentCoords.x, currentCoords.y, size.x, size.y);
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void setPosition(Vector2 coords){
        currentCoords.x = coords.x;
        currentCoords.y = coords.y;
    }

    public void createRectangle(){
        rectangle = new Rectangle(currentCoords.x, currentCoords.y, size.x, size.y);
    }

    public void move(float x, float y){
        this.currentCoords.x += x * GameThirteenMain.delta;
        this.currentCoords.y += y * GameThirteenMain.delta;
    }



    public void collisionCheck(){
        

    }


}







