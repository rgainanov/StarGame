package ru.gb.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture backgroundImage;
    private Vector2 touch;
    private Vector2 v;

    @Override
    public void show() {
        super.show();
        backgroundImage = new Texture("backgroundImage.jpg");
        img = new Texture("badlogic.jpg");
        touch = new Vector2();
        v = new Vector2(1, 1);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(backgroundImage, 0, 0);
        batch.draw(img, touch.x, touch.y);
        batch.end();
        touch.add(v);
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
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
