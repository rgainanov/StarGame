package ru.gb.game.pool;

import com.badlogic.gdx.audio.Sound;

import ru.gb.game.base.SpritesPool;
import ru.gb.game.math.Rect;
import ru.gb.game.sprites.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound bulletSound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound bulletSound) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.bulletSound = bulletSound;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, worldBounds, bulletSound);
    }
}
