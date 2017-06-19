package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Trail {

    ParticleEffect pe;

    public Trail(float x, float y){
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("trail.p"), Gdx.files.internal(""));
        pe.getEmitters().first().setPosition(x, y);
        pe.start();
        GameThirteenMain.trailParticles.add(this);

    }


    public void render(SpriteBatch batch, float delta) {
        pe.update(delta);
        pe.draw(batch);


    }

    public void explosion(float x, float y){
        pe.reset();
        pe.setPosition(x,y);
        pe.start();
    }




    public void dispose() {

        pe.dispose();
    }
}


