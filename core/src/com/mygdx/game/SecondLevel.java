package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SecondLevel extends GameScreen implements Screen {
    final GameJamGame game;

    SecondLevel(final GameJamGame game){
        super(game);
        this.game = game;
    }

    @Override
    public void create(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);

        noMove = new Rectangle(128, 128, 32, 32);
        noMoveImage = new Texture("pixil-frame-box.png");

        box = new Rectangle((0), 416, 32, 32);
        boxImage = new Texture("pixil-frame-0.png");

        box2 = new Rectangle((1024 - 32), 416, 32, 32);
        boxImage2 = new Texture("pixil-frame-0.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        background();
        game.batch.draw(noMoveImage, noMove.x, noMove.y, noMove.width, noMove.height);
        game.batch.draw(boxImage, box.x, box.y, box.width, box.height);
        game.batch.draw(boxImage2, box2.x, box2.y, box2.width, box2.height);
        game.batch.end();

        movement(box, box2);
        boundary();
//        moveBoxes(box, leftMove, -32, 0);
//        moveBoxes(box, rightMove, 32, 0);
//        score();
        if(Gdx.input.isKeyJustPressed(Input.Keys.R)) game.setScreen(new SecondLevel(game));
    }
}
