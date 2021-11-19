package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class FirstLevel extends GameScreen implements Screen {
    final GameJamGame game;

    public FirstLevel(GameJamGame game) {
        super(game);
        this.game = game;
    }

    public void create(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);

        box = new Rectangle((0), 416, 32, 32);
        boxImage = new Texture("pixil-frame-0.png");

        box2 = new Rectangle((1024 - 32), 416, 32, 32);
        boxImage2 = new Texture("pixil-frame-0.png");

        goal1 = new Rectangle((128), 480, 32, 32);
        goal1Image = new Texture("pixil-frame-black.png");

        goal2 = new Rectangle((1024 - 160), 480, 32, 32);
        goal2Image = new Texture("pixil-frame-black.png");

        leftMove = new Rectangle(128, 416, 32, 32);
        leftImage = new Texture("pixil-frame-left.png");

        rightMove = new Rectangle(128, 256, 32, 32);
        rightImage = new Texture("pixil-frame-right.png");

        noMove = new Rectangle(128, 128, 32, 32);
        noMoveImage = new Texture("pixil-frame-box.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        background();
        game.batch.draw(boxImage, box.x, box.y, box.width, box.height);
        game.batch.draw(boxImage2, box2.x, box2.y, box2.width, box2.height);
        game.batch.draw(goal1Image, goal1.x, goal1.y, goal1.width, goal1.height);
        game.batch.draw(goal2Image, goal2.x, goal2.y, goal2.width, goal2.height);
        game.batch.draw(leftImage, leftMove.x, leftMove.y, leftMove.width, leftMove.height);
        game.batch.draw(rightImage, rightMove.x, rightMove.y, rightMove.width, rightMove.height);
        game.batch.draw(noMoveImage, noMove.x, noMove.y, noMove.width, noMove.height);
        game.batch.end();

        movement(box, box2);
        boundary();
        moveBoxes(box, leftMove, -32, 0);
        moveBoxes(box, rightMove, 32, 0);
        score();
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) game.setScreen(new FirstLevel(game));
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

    }
}
