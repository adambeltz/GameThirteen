package com.mygdx.gamethirteen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameThirteenMain extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;

	public static float delta;

	ShapeRenderer shapeRenderer;

	// Input
	MyInputProcessor inputProcessor;




	public static Assets assets;



	// Background
	public Sprite backgroundSprite;
	Rectangle[] borders;




	// Game Objects
	public StillObject brownCat;
	public StillObject grayCat;
	public StillObject cactus;
	public StillObject berries;
	public StillObject fSquare;
	public StillObject lips;
	public AnimatedObject redSquare;
	public AnimatedObject yellowSquare;
	public AnimatedObject blueSquare;
	public AnimatedObject sun;








	@Override
	public void create () {
		delta = Gdx.graphics.getDeltaTime();

		// Camera
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		// Assets
		assets = new Assets();

		// Game objects
		brownCat = new StillObject(assets.brownCatAtlasRegion, 5, new Vector2(12, 10));
		grayCat = new StillObject(assets.grayCatAtlasRegion, 4, new Vector2( 5, 5));
		cactus = new StillObject(assets.cactusAtlasRegion, 6, new Vector2( 20, 25));
		berries = new StillObject(assets.berriesAtlasRegion, 3, new Vector2(30,30));
		fSquare = new StillObject(assets.fSquareAtlasRegion, 7, new Vector2( 40, 40));
		lips = new StillObject(assets.lipsAtlasRegion, 3, new Vector2( 70, 35));
		redSquare = new AnimatedObject(assets.redSquareKeyFrames, 5, new Vector2(47, 47));
		yellowSquare = new AnimatedObject(assets.yellowSquareKeyFrames, 6, new Vector2(80, 10));
		blueSquare = new AnimatedObject(assets.blueSquareKeyFrames, 4, new Vector2(5, 40));
		sun = new AnimatedObject(assets.sunKeyFrames, 7, new Vector2(80, 40));


		shapeRenderer = new ShapeRenderer();





		// Background
		backgroundSprite = new Sprite(GameThirteenMain.assets.backgroundAtlasRegion);
		backgroundSprite.setSize(100, 100 * (float)Constants.ASPECT_RATIO);
		backgroundSprite.setCenter(Constants.VIEWPORT_WIDTH / 2f, Constants.VIEWPORT_HEIGHT / 2f);
		borders = new Rectangle[4];
		borders[0] = new Rectangle(0f,0f,100f, 0.1f);
		borders[1] = new Rectangle(0f,0f,0.1f, 100f);
		borders[2] = new Rectangle(0f, camera.viewportHeight, 100f, 0.1f);
		borders[3] = new Rectangle(100f, 0f, 0.1f, 100f);


		// Input
		inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);


		batch = new SpriteBatch();

	}

	@Override
	public void render () {

		delta = Gdx.graphics.getDeltaTime();


		// camera.update updates position,zooming, rotation of camera. Call after manipulating the camera.
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		backgroundSprite.draw(batch);


		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			berries.move(0,10);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			berries.move(0, -10);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			berries.move(-10, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			berries.move(10, 0);
		}







		// Update Objects


		for (StillObject i : StillObject.stillObjects) {
			i.update();
		}


		// Render StillObjects
		for (StillObject i : StillObject.stillObjects) {
			i.render(batch);
		}

		// Render AnimatedObjects
		for (AnimatedObject i : AnimatedObject.animatedObjects){
			i.render(batch, delta);
		}


		batch.end();

		/*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setColor(Color.BLUE);
		for (StillObject i : StillObject.stillObjects){
			shapeRenderer.rect(i.rectangle.x, i.rectangle.y, i.rectangle.getWidth(), i.rectangle.getHeight());
		}
		for (AnimatedObject i : AnimatedObject.animatedObjects) {
			shapeRenderer.rect(i.rectangle.x, i.rectangle.y, i.rectangle.getWidth(), i.rectangle.getHeight());
		}
		shapeRenderer.end();*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();


	}

	public class MyInputProcessor implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			return false;
		}
	}
}
