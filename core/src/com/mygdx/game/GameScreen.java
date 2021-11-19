package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import org.w3c.dom.Text;


public abstract class GameScreen implements Screen {

    final GameJamGame game;
    OrthographicCamera camera;
    Rectangle box;
    Texture boxImage;
    Rectangle box2;
    Texture boxImage2;
    Rectangle goal1;
    Texture goal1Image;
    Rectangle goal2;
    Texture goal2Image;
    Rectangle leftMove;
    Texture leftImage;
    Rectangle rightMove;
    Texture rightImage;
    Rectangle noMove;
    Texture noMoveImage;
    Rectangle[][] backgroundTiles = new Rectangle[25][32];
    Texture backdrop = new Texture("pixil-frame-backdrop.png");
    Texture wall = new Texture("wall.png");
    Rectangle wallDraw = new Rectangle((1024/2), 768, 1, 32);
    int x = 0;
    int y = 768;

    final static float WORLD_WIDTH = 1024;
    final static float WORLD_HEIGHT = 800;

    public GameScreen(final GameJamGame game) {
        this.game = game;
        for(int a = 0;a < 25;a++){
            for(int b = 0;b < 32;b++){
                backgroundTiles[a][b] = new Rectangle(x, y, 32 , 32);
                x += 32;
            }
            x = 0;
            y -= 32;
        }
        create();
    }

    public abstract void create();


    @Override
    public void show() {

    }

    public void move(Rectangle rec, int key, int valX, int valY){
        if (Gdx.input.isKeyJustPressed(key)) rec.x += valX;
        if (Gdx.input.isKeyJustPressed(key)) rec.y += valY;
        if (rec.overlaps(noMove)) rec.x -= valX;
        if (rec.overlaps(noMove)) rec.y -= valY;
    }

    public void move(Rectangle rec, int valX, int valY){
        rec.x += valX;
        rec.y += valY;
        if (rec.overlaps(noMove)) rec.x -= valX;
        if (rec.overlaps(noMove)) rec.y -= valY;
    }

    public void boundary(){
        if(box.getY() < 0){
            box.y = 0;
        }
        if(box.getY() > 800-32){
            box.y = 800-32;
        }
        if(box2.getY() < 0){
            box2.y = 0;
        }
        if(box2.getY() > 800-32){
            box2.y = 800-32;
        }

        if(box.getX() < 0){
            box.x = 0;
        }
        if(box.getX() > 512-32){
            box.x = 512-32;
        }
        if(box2.getX() < 512){
            box2.x = 512;
        }
        if(box2.getX() > 1024-32){
            box2.x = 1024-32;
        }
    }

    public void background() {
        for (int a = 0; a < 25; a++) {
            for (int b = 0; b < 32; b++) {
                game.batch.draw(backdrop, backgroundTiles[a][b].x, backgroundTiles[a][b].y, backgroundTiles[a][b].width, backgroundTiles[a][b].height);
            }
        }
        for (int c = 0;c < 25;c++) {
            game.batch.draw(wall, wallDraw.x, (wallDraw.y - (c * 32)), wallDraw.width, wallDraw.height);
        }
    }

    public void movement(Rectangle rec1, Rectangle rec2){
        move(rec1, Input.Keys.UP, 0, 32);
        move(rec2, Input.Keys.UP, 0, 32);
        move(rec1, Input.Keys.DOWN, 0, -32);
        move(rec2, Input.Keys.DOWN, 0, -32);
        move(rec1, Input.Keys.LEFT, -32, 0);
        move(rec2, Input.Keys.LEFT, -32, 0);
        move(rec1, Input.Keys.RIGHT, 32, 0);
        move(rec2, Input.Keys.RIGHT, 32, 0);
    }

    public void score(){
        if(goal1.overlaps(box) && goal2.overlaps(box2)) {
            game.setScreen(new WinScreen(game));
        }
    }

    public void moveBoxes(Rectangle rec, Rectangle mover, int distanceX, int distanceY){
        if(rec.overlaps(mover)){
            rec.x = mover.x + distanceX;
            rec.y = mover.y + distanceY;
        }
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        boxImage.dispose();
    }
}
