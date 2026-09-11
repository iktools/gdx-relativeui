package com.iktools.relativeui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;

public class RelativeLayout extends WidgetGroup {
	private boolean sizeInvalid = true;

	public LayoutRulesBuilder add(Actor actor) {
		addActor(actor);
		
		return new LayoutRulesBuilder();
	}
	
	public void layout () {
		if (sizeInvalid) computeSize();
		
		float layoutWidth = getWidth(), layoutHeight = getHeight();
		Array<Actor> childrenArray = getChildren();
		Actor[] children = childrenArray.items;
		for (int i = 0, n = childrenArray.size; i < n; i++) {
			Actor child = children[i];
			child.setHeight(layoutHeight);
			if (child instanceof Layout) ((Layout)child).validate();
		}
	}
	
	private void computeSize () {
		sizeInvalid = false;
		//TODO compute minimum children size
	}
}
