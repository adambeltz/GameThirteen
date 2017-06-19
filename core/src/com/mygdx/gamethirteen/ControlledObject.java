package com.mygdx.gamethirteen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ControlledObject extends StillObject {

    public ControlledObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        super(AR, sizeF, coords);



    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        super.render(batch, delta);
        createTrailPE(currentCoords.x + (sizeV2.x / (float)2), currentCoords.y + (sizeV2.y / (float)2));
    }

    public void collisionCheckC() {
        for (int i = 0; i < objectsArray.size; i++) {
            // Collision
            if (this.rectangle.overlaps(objectsArray.get(i).rectangle) && !(this.rectangle.equals(objectsArray.get(i).rectangle))) {
                toBeRemoved = i;
                controlledObCollision = true;
                Rectangle rect = new Rectangle();
                Intersector.intersectRectangles(this.rectangle, objectsArray.get(i).rectangle, rect);
                if (objectsArray.get(i).getClass().equals(AnimatedObject.class)){
                    new ParticleSpawner("explosion1.p",(rect.x + (rect.x + rect.getWidth())) / 2, (rect.y + (rect.y + rect.getHeight())) / 2);
                    GameThirteenMain.score ++;
                } else {
                    new ParticleSpawner("explosion3.p",(rect.x + (rect.x + rect.getWidth())) / 2, (rect.y + (rect.y + rect.getHeight())) / 2);
                }


            }
        }
    }





    public void createTrailPE(float x, float y){
        // Creates a pool of smallExplosions to use only creates a new instance of SmallExplosions if none are complete
        if (GameThirteenMain.trailParticles.size == 0){
            new Trail(x,y);
        } else {
            int j = GameThirteenMain.trailParticles.size;
            for (int i = 0; i < j; i++) {
                if (GameThirteenMain.trailParticles.get(i).pe.isComplete()){
                    GameThirteenMain.trailParticles.get(i).explosion(x, y);
                    break;
                } if (i + 1 == j){
                    new Trail(x, y);
                }
            }

        }
    }
}
