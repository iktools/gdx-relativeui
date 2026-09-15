package com.iktools.relativeui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.iktools.relativeui.rules.AlignWithParentV;
import com.iktools.relativeui.rules.AlignWithParentH;
import com.iktools.relativeui.rules.ApplyPadding;
import com.iktools.relativeui.rules.ILayoutRule;

import java.util.ArrayList;
import java.util.List;

public class LayoutRulesBuilder {
    protected final Actor actor;
    protected final LayoutRulesBuilder parent;
    
    private final List<ILayoutRule> rules = new ArrayList<>();

    public LayoutRulesBuilder(Actor actor) {
        this.actor = actor;
        this.parent = this;
    }
    
    public LayoutRulesBuilder(Actor actor, LayoutRulesBuilder parent) {
        this.actor = actor;
        this.parent = parent;
    }
    
    /*
     * Parent relative positions
     */
    
    public RulesPaddingBuilder left() {
    	return this.addRule(pad -> new AlignWithParentH(actor, 0f, 0f, pad));
    }
    
    public RulesPaddingBuilder right() {
    	return this.addRule(pad -> new AlignWithParentH(actor, 1f, -1f, -pad));
    }
    
    public RulesPaddingBuilder top() {
    	return this.addRule(pad -> new AlignWithParentV(actor, 1f, -1f, -pad));
    }
    
    public RulesPaddingBuilder bottom() {
    	return this.addRule(pad -> new AlignWithParentV(actor, 0f, 0f, pad));
    }
    
    public LayoutRulesBuilder centerH() {
    	this.rules.add(new AlignWithParentH(actor, 0.5f, -0.5f, 0));
    	return this.parent;
    }
    
    public LayoutRulesBuilder centerV() {
    	this.rules.add(new AlignWithParentV(actor, 0.5f, -0.5f, 0));
    	return this.parent;
    }

    public void execute(float parentWidth, float parentHeight) {
        for (ILayoutRule rule: this.rules) {
            rule.execute(parentWidth, parentHeight);
        }
    }
    
    protected void replaceLast(ILayoutRule rule) {
    	this.rules.set(rules.size() - 1, rule);
    }
    
    private RulesPaddingBuilder addRule(ApplyPadding rule) {
    	this.rules.add(rule.apply(0f));
    	return new RulesPaddingBuilder(this.parent, rule);
    }
}
