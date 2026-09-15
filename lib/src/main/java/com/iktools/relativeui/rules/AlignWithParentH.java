package com.iktools.relativeui.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class AlignWithParentH implements ILayoutRule {

    float parentX;
    float actorX;
    Actor actor;

    public AlignWithParentH(Actor actor, float parentX, float actorX) {
        this.actor = actor;
        this.actorX = actorX;
        this.parentX = parentX;
    }

    @Override
    public void execute(float parentWidth, float parentHeight) {
        this.actor.setX(parentWidth * this.parentX + actor.getWidth() * this.actorX);
    }
}
