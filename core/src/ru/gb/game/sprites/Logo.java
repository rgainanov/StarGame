package ru.gb.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.game.base.Sprite;
import ru.gb.game.math.Rect;

public class Logo extends Sprite {

    private static final float V_LEN = 0.005f;
    private Vector2 touch;
    private Vector2 direction = new Vector2();

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        touch = new Vector2();

    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportions(worldBounds.getHeight() * 0.1f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        System.out.println(pos.dst(touch) + " ---- " + direction.len());
        if (Math.abs(pos.dst(touch) - direction.len()) <= 0.0029) {

//            Используя сравнение ниже у меня никак не получаестя сделть
//            остановку картинки в точке touch,
//            а со сравнением в строке 36 вроде выходит
//        if (pos.dst(touch)  <= direction.len()) {
//              когда я спользую pos.set(touch); то я вижу как sprite как бы допрыгивает
//              в самом конце, так же после того, как sprite остановался в точке touch,
//              если я продолжаю кликать, то он как бы незначительно смещается влево-вправо,
//              токого не происходит если я обнуляю вектор direction.
//            pos.set(touch);
            direction.setZero();
        }
        pos.add(direction);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch = touch;
        direction.set(this.touch.cpy().sub(pos).setLength(V_LEN));

//          я, не совсем разобрался с новой координатной сеткой, так как поле у нас теперь
//          1f на 1f * aspect, получается нормализацию в прямом виде мы уже не можем использовать?
//          еденичный вектор сразу выкидывает за пределы поля.
//        direction.set(touch.cpy().sub(pos).nor());
        return super.touchDown(touch, pointer, button);
    }
}
