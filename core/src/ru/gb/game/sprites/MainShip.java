package ru.gb.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.Sprite;
import ru.gb.game.math.Rect;

public class MainShip extends Sprite {

    private static final float BOTTOM_PADDING = 0.03f;
    private static final float LR_PADDING = 0.005f;
    private static final float MOVE_STEP = 0.005f;

    private float leftBorder;
    private float rightBorder;

    private boolean pressed;
    private Vector2 touch;

    private char keyTypedChar;

    public MainShip(TextureAtlas atlas) {
        super(new TextureRegion(atlas.findRegion("main_ship"), 0, 0, 200, 300));
        touch = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportions(0.1f);
        setBottom(worldBounds.getBottom() + BOTTOM_PADDING);
        leftBorder = worldBounds.getLeft();
        rightBorder = worldBounds.getRight();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (pressed) {
            if (touch.x < pos.x) {
                moveLeft();
            }
            if (touch.x > pos.x) {
                moveRight();
            }
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (pressed) {
            return false;
        }
        this.touch = touch;
        pressed = true;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (!pressed) {
            return false;
        }
        pressed = false;
        return false;
    }

    public void moveLeft() {
        if (Math.abs(getLeft() - leftBorder) > LR_PADDING) {
            setLeft((getLeft() - MOVE_STEP));
        }
    }

    public void moveRight() {
        if (Math.abs(getRight() - rightBorder) > LR_PADDING) {
            setRight((getRight() + MOVE_STEP));
        }
    }
}