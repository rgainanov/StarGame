package ru.gb.game.sprites;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.Ship;
import ru.gb.game.math.Rect;
import ru.gb.game.pool.BulletPool;

public class MainShip extends Ship {

    private static final float RELOAD_INTERVAL = 0.2f;

    private static final float HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;

    private boolean pressedLeft;
    private boolean pressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, Sound bulletSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.reloadInterval = RELOAD_INTERVAL;
        this.bulletPool = bulletPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.bulletV = new Vector2(0, 0.5f);
        this.bulletHeight = 0.01f;
        this.bulletDamage = 1;
        this.bulletPos = new Vector2();
        this.hp = 100;
        this.bulletSound = bulletSound;
        this.v = new Vector2();
        this.v0 = new Vector2(0.5f, 0);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        bulletPos.set(this.pos.x, getTop());
        if (getLeft() > worldBounds.getRight()) {
            setRight(worldBounds.getLeft());
        }
        if (getRight() < worldBounds.getLeft()) {
            setLeft(worldBounds.getRight());
        }
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        stop();
        return false;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setHeightProportions(HEIGHT);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotateDeg(180);
    }

    private void stop() {
        v.setZero();
    }


}