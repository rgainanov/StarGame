package ru.gb.game;

import com.badlogic.gdx.Game;

import ru.gb.game.screen.MenuScreen;

public class StarGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
