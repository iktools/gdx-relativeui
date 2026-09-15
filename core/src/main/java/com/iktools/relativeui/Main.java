package com.iktools.relativeui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private Stage stage;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());

        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        Table rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.add(new Label("Column 1", skin));

        RelativeLayout relativeLayout = new RelativeLayout();
        rootTable.add(relativeLayout).grow();

        relativeLayout.add(new Label("Move around", skin)).left().withPadding(100).bottom().withPadding(50);

        stage.addActor(rootTable);
    }

    @Override
    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize (int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
