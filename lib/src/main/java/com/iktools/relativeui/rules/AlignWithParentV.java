package com.iktools.relativeui.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class AlignWithParentV implements ILayoutRule {

    final float parentY;
    final float actorY;
    final Actor actor;
    final float offset;

    public AlignWithParentV(Actor actor, float parentY, float actorY, float offset) {
        this.actor = actor;
        this.actorY = actorY;
        this.parentY = parentY;
        this.offset = offset;
    }

    @Override
    public void execute(float parentWidth, float parentHeight) {
        this.actor.setY(parentHeight * this.parentY + actor.getHeight() * this.actorY + this.offset);
    }
}
