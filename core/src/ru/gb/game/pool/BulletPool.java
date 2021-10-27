package ru.gb.game.pool;

import ru.gb.game.base.SpritesPool;
import ru.gb.game.sprites.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
