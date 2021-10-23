package ru.gb.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.gb.game.base.Sprite;
import ru.gb.game.math.Rect;

public class Background extends Sprite {

    public Background(Texture texture) {
        super(new TextureRegion(texture));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportions(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }
}
