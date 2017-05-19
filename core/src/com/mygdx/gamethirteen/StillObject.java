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

public  class StillObject extends GameObject{


    public Sprite sprite;
    public float spriteAspectRatio;

    public float delta;





    public StillObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        super(sizeF, coords);

        sprite = new Sprite(AR);
        spriteAspectRatio = (float)sprite.getHeight() / (float)sprite.getWidth();

        setSize();
        createRectangle();
        GameObject.stillObjects.add(this);

        //velocity = new Vector2(0,0);

        //currentCoords = new Vector2(coords);


        //setSize(sizeF);
        //createRectangle();
        //stillObjects.add(this);


    }



    @Override
    public void setSize(){
        this.sizeV2.x = sizeFloat * spriteAspectRatio;
        this.sizeV2.y = sizeFloat * spriteAspectRatio;
        sprite.setSize(sizeV2.x, sizeV2.y);
    }

    public void update(){

        move();


        sprite.setPosition(currentCoords.x, currentCoords.y);
        rectangle.set(currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);


    }


    public void render(SpriteBatch batch, float delta){
        this.delta = delta;
        sprite.draw(batch);
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
            if (this.rectangle.overlaps(GameObject.stillObjects.get(i).rectangle) && !(this.rectangle.equals(GameObject.stillObjects.get(i).rectangle))) {


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
                    this.velocity.y = MathUtils.random(0f, 10f);
                } else if (this.rectangle.overlaps(GameThirteenMain.borders[1])){
                    this.velocity.x = MathUtils.random(0f, 10f);
                } else if (this.rectangle.overlaps(GameThirteenMain.borders[2])){
                    this.velocity.y = MathUtils.random(-10f, 0f);
                } else if (this.rectangle.overlaps(GameThirteenMain.borders[3])){
                    this.velocity.x = MathUtils.random(-10f, 0f);
                }


        }


}



















