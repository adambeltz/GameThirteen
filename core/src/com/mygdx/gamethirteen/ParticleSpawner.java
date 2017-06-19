package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParticleSpawner {

    ParticleEffect pe;

    public ParticleSpawner(float x, float y){
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("explosion1.p"), Gdx.files.internal(""));
        pe.getEmitters().first().setPosition(x, y);
        pe.start();
        GameThirteenMain.particles.add(this);
    }


    public void render(SpriteBatch batch, float delta) {
       pe.update(delta);
       pe.draw(batch);
       if (pe.isComplete()){
           GameThirteenMain.particles.removeIndex(0);
       }
    }




    public void dispose() {

        pe.dispose();
    }
}
