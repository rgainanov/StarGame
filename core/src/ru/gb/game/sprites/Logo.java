package ru.gb.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.Sprite;
import ru.gb.game.math.Rect;

public class Logo extends Sprite {

    private static final float V_LEN = 0.005f;
    private Vector2 touch;
    private Vector2 direction;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        touch = new Vector2();
        direction = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportions(0.3f);
    }

    @Override
    public void update(float delta) {
        if (touch.dst(pos) > V_LEN) {
            pos.add(direction);
        } else {
            pos.set(touch);
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        direction.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }
}
