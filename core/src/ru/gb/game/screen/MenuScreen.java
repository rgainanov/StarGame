package ru.gb.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture backgroundImage;
    private Vector2 touch;
    private Vector2 position;
    private Vector2 direction;
    private float length;

    @Override
    public void show() {
        super.show();
        backgroundImage = new Texture("background.jpg");
        img = new Texture("space.png");
        touch = new Vector2();
        direction = new Vector2();
        position = new Vector2(0, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(backgroundImage, 0, 0);
        batch.draw(img, position.x, position.y, 50, 50);
        batch.end();
        if (Math.abs(position.len() - length) < 0.5f) {
            direction.setZero();
        }
        position.add(direction);
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        backgroundImage.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        length = touch.sub(0f, 0f).len();
        direction = touch.cpy().sub(position).nor();
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
