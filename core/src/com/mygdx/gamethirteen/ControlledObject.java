package com.mygdx.gamethirteen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ControlledObject extends StillObject {
    public ControlledObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        super(AR, sizeF, coords);



    }



    public void collisionCheckC() {
        for (int i = 0; i < objectsArray.size; i++) {
            // Collision
            if (this.rectangle.overlaps(objectsArray.get(i).rectangle) && !(this.rectangle.equals(objectsArray.get(i).rectangle))) {
                toBeRemoved = i;
                controlledObCollision = true;
                Rectangle rect = new Rectangle();
                Intersector.intersectRectangles(this.rectangle, objectsArray.get(i).rectangle, rect);


                new ParticleSpawner((rect.x + (rect.x + rect.getWidth())) / 2, (rect.y + (rect.y + rect.getHeight())) / 2);

            }
        }
    }
}
