package ru.gb.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.Sprite;
import ru.gb.game.math.Rect;

public class Bullet extends Sprite {

    private final Vector2 v = new Vector2();

    private Rect worldBounds;
    private int damage;
    private Sprite owner;

    public Bullet() {
        regions = new TextureRegion[1];
    }

    public void set(
            Sprite owner,
            TextureRegion region,
            Vector2 pos,
            Vector2 v,
            Rect worldBounds,
            float height,
            int damage
    ) {
        this.owner = owner;
        this.regions[0] = region;
//        не знаю на сколько такое решение верно, проблема в том что в данный момент пули летят от центра коробля
//        и если перемещять корабль из стороны в сторону то это очнь сильно заметно, добавив .add(0, owner.getHalfHeight())
//        я попытался сместить начало пули к верху нашего спрайта
        this.pos.set(pos).add(0, owner.getHalfHeight());
        this.v.set(v);
        this.worldBounds = worldBounds;
        setHeightProportions(height);
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }
}
