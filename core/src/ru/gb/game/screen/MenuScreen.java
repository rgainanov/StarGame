package ru.gb.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.BaseScreen;
import ru.gb.game.math.Rect;
import ru.gb.game.sprites.Background;
import ru.gb.game.sprites.Logo;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bg;

    private Background background;
    private Logo logo;

    @Override
    public void show() {
        super.show();

        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        img = new Texture("badlogic.jpg");
        logo = new Logo(img);
//        logo.setHeightProportions(0.2f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        float dt = Gdx.graphics.getDeltaTime();

        batch.begin();
        background.draw(batch);

        logo.draw(batch);
        logo.update(dt);

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
        bg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.touchDown(touch, pointer,button);
        return super.touchDown(touch, pointer, button);
    }
}
