package com.iktools.relativeui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class RelativeLayout extends WidgetGroup {
	private boolean sizeInvalid = true;
    private Map<Actor, LayoutRulesBuilder> layoutRules = new HashMap<>();

	public LayoutRulesBuilder add(Actor actor) {
        LayoutRulesBuilder rulesBuilder = new LayoutRulesBuilder(actor);
        this.layoutRules.put(actor, rulesBuilder);

        addActor(actor);

		return rulesBuilder;
	}

	public void layout () {
		if (sizeInvalid) computeSize();

		float layoutWidth = getWidth(), layoutHeight = getHeight();
		Array<Actor> childrenArray = getChildren();
		Actor[] children = childrenArray.items;
		for (int i = 0, n = childrenArray.size; i < n; i++) {
			Actor child = children[i];
			this.layoutRules.get(child).execute(layoutWidth, layoutHeight);

			if (child instanceof Layout) ((Layout)child).validate();
		}
	}

	private void computeSize () {
		sizeInvalid = false;
		//TODO compute minimum children size
	}
}
