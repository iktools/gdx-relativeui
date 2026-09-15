package com.iktools.relativeui;

import com.iktools.relativeui.rules.ApplyPadding;

public class RulesPaddingBuilder extends LayoutRulesBuilder {
	private final ApplyPadding applyFunc;
	
	public RulesPaddingBuilder(LayoutRulesBuilder parent, ApplyPadding applyFunc) {
		super(parent.actor, parent);
		this.applyFunc = applyFunc;
	}
	
	public LayoutRulesBuilder withPadding(float padding) {
		this.parent.replaceLast(applyFunc.apply(padding));
		return this.parent;
	}
}
