package com.iktools.relativeui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelativeLayoutTest {

	private static final float LAYOUT_W = 400f;
	private static final float LAYOUT_H = 300f;

	@Test
	public void add_addsActorAndReturnsBuilder() {
		RelativeLayout layout = new RelativeLayout();
		Actor actor = new Actor();

		LayoutRulesBuilder result = layout.add(actor);

		assertNotNull(result);
		assertTrue(layout.getChildren().contains(actor, true));
	}

	@Test
	public void layout_appliesSingleCenterRule() {
		RelativeLayout layout = new RelativeLayout();
		layout.setSize(LAYOUT_W, LAYOUT_H);

		Actor actor = new Actor();
		actor.setSize(50, 25);
		layout.add(actor).centerH().centerV();

		layout.layout();

		assertEquals((LAYOUT_W - 50) / 2f, actor.getX(), 0.001f);
		assertEquals((LAYOUT_H - 25) / 2f, actor.getY(), 0.001f);
	}

	@Test
	public void layout_appliesMultipleChildRules() {
		RelativeLayout layout = new RelativeLayout();
		layout.setSize(LAYOUT_W, LAYOUT_H);

		Actor left = new Actor();
		left.setSize(50, 25);
		layout.add(left).left().withPadding(10f);

		Actor right = new Actor();
		right.setSize(50, 25);
		layout.add(right).right().withPadding(10f);

		Actor top = new Actor();
		top.setSize(50, 25);
		layout.add(top).top().withPadding(15f);

		Actor bottom = new Actor();
		bottom.setSize(50, 25);
		layout.add(bottom).bottom().withPadding(5f);

		layout.layout();

		assertEquals(10f, left.getX(), 0.001f);
		assertEquals(LAYOUT_W - 50 - 10f, right.getX(), 0.001f);
		assertEquals(LAYOUT_H - 25 - 15f, top.getY(), 0.001f);
		assertEquals(5f, bottom.getY(), 0.001f);
	}

	@Test
	public void layout_callsValidateOnLayoutChildren() {
		RelativeLayout layout = new RelativeLayout();
		layout.setSize(LAYOUT_W, LAYOUT_H);

		LayoutSpy spy = new LayoutSpy();
		spy.setSize(100, 50);
		layout.add(spy).centerH();

		layout.layout();

		assertEquals(1, spy.validateCount);
	}

	@Test
	public void layout_callsValidateOnEveryPass() {
		RelativeLayout layout = new RelativeLayout();
		layout.setSize(LAYOUT_W, LAYOUT_H);

		LayoutSpy spy = new LayoutSpy();
		spy.setSize(100, 50);
		layout.add(spy).centerH();

		layout.layout();
		layout.layout();
		layout.layout();

		assertEquals(3, spy.validateCount);
	}

	@Test
	public void layout_withNoChildren_doesNotThrow() {
		RelativeLayout layout = new RelativeLayout();
		layout.setSize(LAYOUT_W, LAYOUT_H);

		layout.layout();
	}

	@Test
	public void layout_repositionsAfterResize() {
		RelativeLayout layout = new RelativeLayout();
		Actor actor = new Actor();
		actor.setSize(50, 25);
		layout.add(actor).right().withPadding(0f);

		layout.setSize(400, 300);
		layout.layout();
		assertEquals(350f, actor.getX(), 0.001f);

		layout.setSize(800, 600);
		layout.layout();
		assertEquals(750f, actor.getX(), 0.001f);
	}

	@Test
	public void layout_validatesEachChildExactlyOncePerPass() {
		RelativeLayout layout = new RelativeLayout();
		layout.setSize(LAYOUT_W, LAYOUT_H);

		LayoutSpy spy1 = new LayoutSpy();
		spy1.setSize(50, 25);
		layout.add(spy1).left();

		LayoutSpy spy2 = new LayoutSpy();
		spy2.setSize(50, 25);
		layout.add(spy2).right();

		layout.layout();

		assertEquals(1, spy1.validateCount);
		assertEquals(1, spy2.validateCount);
	}

	static class LayoutSpy extends Widget {
		int validateCount = 0;

		@Override
		public void validate() {
			validateCount++;
		}

		@Override
		public float getPrefWidth() {
			return getWidth();
		}

		@Override
		public float getPrefHeight() {
			return getHeight();
		}
	}
}