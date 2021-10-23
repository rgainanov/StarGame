package ru.gb.game.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.BaseScreen;
import ru.gb.game.math.Rect;
import ru.gb.game.sprites.Background;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 2.5f;
    private Texture img;
    private Texture bg;
    private Vector2 position;
    private Vector2 direction;
    private float length;

    private Background background;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        img = new Texture("badlogic.jpg");
        direction = new Vector2();
        position = new Vector2(0, 0);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        batch.draw(img, position.x, position.y, 0.5f, 0.5f);
        batch.end();
//        if (position.dst(touch) <= direction.len()) {
//            position.set(touch);
//        }
//        position.add(direction);
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        bg.dispose();
    }

//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
////        length = touch.len();
////        direction.set(touch.cpy().sub(position).nor());
//        direction.set(touch.cpy().sub(position).setLength(V_LEN));
//
//        return super.touchDown(screenX, screenY, pointer, button);
//    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        position.set(touch);
        return super.touchDown(touch, pointer, button);
    }
}
