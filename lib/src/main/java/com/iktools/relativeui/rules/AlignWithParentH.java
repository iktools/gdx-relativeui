package com.iktools.relativeui.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class AlignWithParentH implements ILayoutRule {

    float parentX;
    float actorX;
    Actor actor;
    float offset;

    public AlignWithParentH(Actor actor, float parentX, float actorX, float offset) {
        this.actor = actor;
        this.actorX = actorX;
        this.parentX = parentX;
        this.offset = offset;
    }

    @Override
    public void execute(float parentWidth, float parentHeight) {
        this.actor.setX(parentWidth * this.parentX + actor.getWidth() * this.actorX + offset);
    }
}
