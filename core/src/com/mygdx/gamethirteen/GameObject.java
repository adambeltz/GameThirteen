package com.mygdx.gamethirteen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;


public abstract class GameObject implements Disposable {
    public static Array<GameObject> objectsArray = new Array<GameObject>();
    public Vector2 velocity;
    public Vector2 lastCoords;
    public Vector2 currentCoords;
    public Vector2 sizeV2;
    public float direction;
    public float sizeFloat;
    public Rectangle rectangle;
    public float aspectRatio;
    public Array<Rectangle> borders = new Array<Rectangle>();
    public boolean controlledObCollision;
    public int toBeRemoved;
    public float weight;
    public float id;
    public float collisionPartnerID;
    Sound beep = Gdx.audio.newSound(Gdx.files.internal("Beeps6.mp3"));
    Sound blip = Gdx.audio.newSound(Gdx.files.internal("Blips16.mp3"));
    Sound ohYeah = Gdx.audio.newSound(Gdx.files.internal("Oh Yeah.wav"));
    public static int stillObjects = 0;
    public static int animatedObjects = 0;
    public static int animatedObjectsCollected;




    public GameObject(){}


    public GameObject(TextureAtlas.AtlasRegion AR, float sizeF, Vector2 coords) {
        this.sizeFloat = sizeF;
        sizeV2 = new Vector2(1, 1);
        velocity = new Vector2(0, 0);
        currentCoords = new Vector2(coords);
        buildSprite(AR);
        createRectangle();
        createBorders();
        this.sizeV2.x = sizeFloat;
        this.sizeV2.y = sizeFloat * aspectRatio;
        setSize();
        GameObject.objectsArray.add(this);
        this.weight = sizeFloat * 1.2f;
        id = this.hashCode();
        stillObjects++;




    }


    public GameObject(Array<TextureAtlas.AtlasRegion> AR, float sizeF, Vector2 coords) {
        this.sizeFloat = sizeF;
        sizeV2 = new Vector2(1, 1);
        velocity = new Vector2(0, 0);
        currentCoords = new Vector2(coords);
        buildAnimatedSprite(AR);
        createRectangle();
        createBorders();
        this.sizeV2.x = sizeFloat;
        this.sizeV2.y = sizeFloat * aspectRatio;
        GameObject.objectsArray.add(this);
        this.weight = sizeFloat * 1.2f;
        this.id = this.hashCode();
        animatedObjects++;



    }


    public void buildSprite(TextureAtlas.AtlasRegion ar) {
    }

    public void buildAnimatedSprite(Array<TextureAtlas.AtlasRegion> AR) {
    }


    public void render(SpriteBatch batch, float delta) {

        currentCoords.x += velocity.x * delta;
        currentCoords.y += velocity.y * delta;
        // Must move rectangle when gameObjects are rendered, not in an update loop before getting to render
        rectangle.set(currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
        updateBorders();

    }

    public void update(float delta) {


        move(delta);

    }

    protected void move(float delta) {

        if (controlledObCollision == true) {
            if (objectsArray.get(toBeRemoved).getClass().equals(AnimatedObject.class)){
                animatedObjectsCollected ++;
                if (animatedObjectsCollected % 10 == 0){
                    ohYeah.play(.5f);
                }
                else {
                beep.play(.5f);}

            } else {
                blip.play(.4f);
            }
            objectsArray.removeIndex(toBeRemoved);
            controlledObCollision = false;
        }
        collisionCheck(delta);

    }

    public void collisionCheck(float delta) {

        for (int i = 0; i < objectsArray.size; i++) {
            // Collision
            if (this.rectangle.overlaps(objectsArray.get(i).rectangle) && !(this.rectangle.equals(objectsArray.get(i).rectangle))) {
                if (this.collisionPartnerID != objectsArray.get(i).id){
                    objectsArray.get(i).collisionPartnerID = this.id;
                    collision(this,objectsArray.get(i), delta);

                    if (objectsArray.size < 80) {
                        objectsArray.get(MathUtils.random(0, objectsArray.size - 1)).newObject();
                        getScore();
                    }

                }
            }
        }



        if (this.rectangle.overlaps(GameThirteenMain.borders[0])) {
            this.velocity.y = MathUtils.random(10f, 20f);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[1])) {
            this.velocity.x = MathUtils.random(10f, 20f);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[2])) {
            this.velocity.y = MathUtils.random(-20f, -10);
        } else if (this.rectangle.overlaps(GameThirteenMain.borders[3])) {
            this.velocity.x = MathUtils.random(-20f, -10f);
        }


    }


    public void setSize() {

    }

    public void createRectangle() {
        rectangle = new Rectangle(currentCoords.x, currentCoords.y, sizeV2.x, sizeV2.y);
    }

    public void createBorders() {
        borders.add(new Rectangle(currentCoords.x, currentCoords.y, sizeV2.x, .1f));
        borders.add(new Rectangle(currentCoords.x, currentCoords.y, .1f, sizeV2.y));
        borders.add(new Rectangle(sizeV2.x, currentCoords.y, -.1f, sizeV2.y));
        borders.add(new Rectangle(currentCoords.x, sizeV2.y, sizeV2.x, -.1f));
    }

    public void updateBorders() {
        borders.set(0, new Rectangle(currentCoords.x, currentCoords.y, sizeV2.x, .1f));
        borders.get(1).set(currentCoords.x, currentCoords.y, .1f, sizeV2.y);
        borders.get(2).set(currentCoords.x + sizeV2.x, currentCoords.y, -.1f, sizeV2.y);
        borders.get(3).set(currentCoords.x, currentCoords.y + sizeV2.y, sizeV2.x, -.1f);
    }

    public abstract void newObject();

    private void collision(GameObject collider, GameObject collidee, float delta) {
        float leftCollisionOffset = (collidee.currentCoords.x + collidee.sizeV2.x) - collider.currentCoords.x;
        float rightCollisionOffset = (collider.currentCoords.x + collider.sizeV2.x) - collidee.currentCoords.x;
        float topCollisionOffset = (collider.currentCoords.y + collider.sizeV2.y) - collidee.currentCoords.y;
        float bottomCollisionOffset = (collidee.currentCoords.y + collidee.sizeV2.y) - collider.currentCoords.y;

        // collision on colliders left
        if (collider.rectangle.overlaps(collidee.borders.get(2))){
            if (leftCollisionOffset < topCollisionOffset && leftCollisionOffset < bottomCollisionOffset){
                collider.currentCoords.x += (leftCollisionOffset * 1.1);

                explosion(collidee.rectangle.x + collidee.rectangle.getWidth(),
                        (collidee.rectangle.y + (collidee.rectangle.y + collidee.rectangle.getHeight())) / 2);
            }

            float newCollliderVel;
            float newCollideeVel;


            newCollliderVel = (((collider.weight - collidee.weight) / (collider.weight + collidee.weight)) * collider.velocity.x) +
                    (((2 * collidee.weight) / (collider.weight + collidee.weight)) * collidee.velocity.x);
            newCollideeVel = (((2 * collider.weight) / (collider.weight + collidee.weight)) * collider.velocity.x) +
                    (((collidee.sizeFloat - collider.weight) / (collider.weight + collidee.weight)) * collidee.velocity.x);
            collider.velocity.x = newCollliderVel;
            collidee.velocity.x = newCollideeVel;



        }
        // collision on collliders right
        if (collider.rectangle.overlaps(collidee.borders.get(1))){


            if (rightCollisionOffset < topCollisionOffset && rightCollisionOffset < bottomCollisionOffset){
                collider.currentCoords.x -= (rightCollisionOffset * 1.1);

                explosion(collidee.rectangle.x,
                        (collidee.rectangle.y + (collidee.rectangle.y + collidee.rectangle.getHeight())) / 2);
            }

            float newCollliderVel;
            float newCollideeVel;


            newCollliderVel = (((collider.weight - collidee.weight) / (collider.weight + collidee.weight)) * collider.velocity.x) +
                    (((2 * collidee.weight) / (collider.weight + collidee.weight)) * collidee.velocity.x);
            newCollideeVel = (((2 * collider.weight) / (collider.weight + collidee.weight)) * collider.velocity.x) +
                    (((collidee.weight - collider.weight) / (collider.weight + collidee.weight)) * collidee.velocity.x);
            collider.velocity.x = newCollliderVel;
            collidee.velocity.x = newCollideeVel;



        }
        // collision on collliders top
        if (collider.rectangle.overlaps(collidee.borders.get(0))){

            if (topCollisionOffset < rightCollisionOffset && topCollisionOffset < leftCollisionOffset){
                collider.currentCoords.y -= (topCollisionOffset * 1.1);
                explosion((collidee.rectangle.x + (collidee.rectangle.x + collidee.rectangle.getWidth())) / 2,
                        collidee.rectangle.y);
            }


            float newCollliderVel;
            float newCollideeVel;


            newCollliderVel = (((collider.weight - collidee.weight) / (collider.weight + collidee.weight)) * collider.velocity.y) +
                    (((2 * collidee.weight) / (collider.weight + collidee.weight)) * collidee.velocity.y);
            newCollideeVel = (((2 * collider.weight) / (collider.weight + collidee.weight)) * collider.velocity.y) +
                    (((collidee.weight - collider.weight) / (collider.weight + collidee.weight)) * collidee.velocity.y);
            collider.velocity.y = newCollliderVel;
            collidee.velocity.y = newCollideeVel;


        }
        // collision on collliders bottom
        if (collider.rectangle.overlaps(collidee.borders.get(3))){

            if (bottomCollisionOffset < rightCollisionOffset && bottomCollisionOffset < leftCollisionOffset){
                collider.currentCoords.y += (bottomCollisionOffset * 1.1) ;

                explosion((collidee.rectangle.x + (collidee.rectangle.x + collidee.rectangle.getWidth())) / 2,
                        collidee.rectangle.y + collidee.rectangle.getHeight());
            }


            float newCollliderVel;
            float newCollideeVel;


            newCollliderVel = (((collider.weight - collidee.weight) / (collider.weight + collidee.weight)) * collider.velocity.y) +
                    (((2 * collidee.weight) / (collider.weight + collidee.weight)) * collidee.velocity.y);
            newCollideeVel = (((2 * collider.weight) / (collider.weight + collidee.weight)) * collider.velocity.y) +
                    (((collidee.weight - collider.weight) / (collider.weight + collidee.weight)) * collidee.velocity.y);
            collider.velocity.y = newCollliderVel;
            collidee.velocity.y = newCollideeVel;


        }




    }

    public void explosion(float x, float y){
        // Creates a pool of smallExplosions to use only creates a new instance of SmallExplosions if none are complete
        if (GameThirteenMain.smallExplosions.size == 0){
            new SmallExplosion(x,y);
        } else {
            int j = GameThirteenMain.smallExplosions.size;
            for (int i = 0; i < j; i++) {
                if (GameThirteenMain.smallExplosions.get(i).pe.isComplete()){
                    GameThirteenMain.smallExplosions.get(i).explosion(x, y);
                    break;
                } if (i + 1 == j){
                    new SmallExplosion(x, y);
                }
            }

        }
    }

    public void getScore(){
        Gdx.app.log("Still Objects: " + stillObjects, "Animated Objects: " + animatedObjects);
    }

    @Override
    public void dispose() {
        blip.dispose();
        beep.dispose();
        ohYeah.dispose();




    }
}
