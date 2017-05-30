package com.mygdx.gamethirteen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class ControlledObject extends StillObject {
    public ControlledObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        super(AR, sizeF, coords);



    }



    public void collisionCheckC() {
        for (int i = 0; i < stillObjects.size; i++) {
            // Collision
            if (this.rectangle.overlaps(stillObjects.get(i).rectangle) && !(this.rectangle.equals(stillObjects.get(i).rectangle))) {
                toBeRemoved = i;
                controlledObCollision = true;

            }
        }
    }
}
