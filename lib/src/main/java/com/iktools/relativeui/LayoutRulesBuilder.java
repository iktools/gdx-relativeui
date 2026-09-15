package com.iktools.relativeui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.iktools.relativeui.rules.AlignWithParentH;
import com.iktools.relativeui.rules.ILayoutRule;

import java.util.ArrayList;
import java.util.List;

public class LayoutRulesBuilder {
    Actor actor;
    private List<ILayoutRule> rules = new ArrayList<>();

    public LayoutRulesBuilder(Actor actor) {
        this.actor = actor;
    }

    public LayoutRulesBuilder right() {
        rules.add(new AlignWithParentH(actor, 1f, -1f));
        return this;
    }

    public void execute(float parentWidth, float parentHeight) {
        for (ILayoutRule rule: this.rules) {
            rule.execute(parentWidth, parentHeight);
        }
    }
}
