package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static AssetManager assetManager;
    TextureAtlas textureAtlas;

    public TextureAtlas.AtlasRegion backgroundAtlasRegion;
    public TextureAtlas.AtlasRegion brownCatAtlasRegion;
    public TextureAtlas.AtlasRegion grayCatAtlasRegion;
    public TextureAtlas.AtlasRegion lipsAtlasRegion;
    public TextureAtlas.AtlasRegion berriesAtlasRegion;
    public TextureAtlas.AtlasRegion fSquareAtlasRegion;
    public TextureAtlas.AtlasRegion cactusAtlasRegion;
    public Array<TextureAtlas.AtlasRegion> redSquareKeyFrames;
    public Array<TextureAtlas.AtlasRegion> yellowSquareKeyFrames;
    public Array<TextureAtlas.AtlasRegion> blueSquareKeyFrames;
    public Array<TextureAtlas.AtlasRegion> sunKeyFrames;
    public Array<Array<TextureAtlas.AtlasRegion>> arrayKeyFrames;
    public Array<TextureAtlas.AtlasRegion> arrayAtlasRegions;
    public BitmapFont font;

    public Assets(){
        init();
    }


    private void init(){
        assetManager = new AssetManager();
        assetManager.load("atlas.atlas", TextureAtlas.class);
        assetManager.finishLoading();
        arrayKeyFrames = new Array<Array<TextureAtlas.AtlasRegion>>();
        arrayAtlasRegions = new Array<TextureAtlas.AtlasRegion>();
        textureAtlas = assetManager.get("atlas.atlas");
        backgroundAtlasRegion = textureAtlas.findRegion("bluebackground1366x768");
        brownCatAtlasRegion = textureAtlas.findRegion("cat_resize");
        grayCatAtlasRegion = textureAtlas.findRegion("gray_cat_front200");
        lipsAtlasRegion = textureAtlas.findRegion("lips_resize");
        berriesAtlasRegion = textureAtlas.findRegion("berries_resize");
        fSquareAtlasRegion = textureAtlas.findRegion("box_front_175x158");

        cactusAtlasRegion = textureAtlas.findRegion("cactus_resize");
        redSquareKeyFrames = textureAtlas.findRegions("square1");
        blueSquareKeyFrames = textureAtlas.findRegions("square2");
        yellowSquareKeyFrames = textureAtlas.findRegions("square3");
        sunKeyFrames = textureAtlas.findRegions("sun");
        arrayKeyFrames.add(textureAtlas.findRegions("square1"));
        arrayKeyFrames.add(blueSquareKeyFrames);
        arrayKeyFrames.add(yellowSquareKeyFrames);
        arrayKeyFrames.add(sunKeyFrames);
        arrayAtlasRegions.add(brownCatAtlasRegion);
        arrayAtlasRegions.add(grayCatAtlasRegion);
        arrayAtlasRegions.add(lipsAtlasRegion);
        arrayAtlasRegions.add(fSquareAtlasRegion);
        arrayAtlasRegions.add(cactusAtlasRegion);

        font = new BitmapFont();


    }






    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset : " + asset.fileName, (Exception)throwable);

    }

    //@Override
    public void error (String filename, Class type, Throwable throwable){
        Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", (Exception)throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        textureAtlas.dispose();
        font.dispose();
    }
}
