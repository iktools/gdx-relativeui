package com.iktools.relativeui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LayoutRulesBuilderTest {

	private static final float PARENT_WIDTH = 800f;
	private static final float PARENT_HEIGHT = 600f;
	private static final float ACTOR_WIDTH = 120f;
	private static final float ACTOR_HEIGHT = 60f;

	private static Actor newActor() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(ACTOR_WIDTH);
		when(actor.getHeight()).thenReturn(ACTOR_HEIGHT);
		return actor;
	}

	@Test
	public void left_returnsPaddingBuilderAndAlignsLeft() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		RulesPaddingBuilder result = builder.left();

        assertInstanceOf(RulesPaddingBuilder.class, result);
		builder.execute(PARENT_WIDTH, PARENT_HEIGHT);
		verify(actor).setX(0f);
	}

	@Test
	public void right_returnsPaddingBuilderAndAlignsRight() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		RulesPaddingBuilder result = builder.right();

        assertInstanceOf(RulesPaddingBuilder.class, result);
		builder.execute(PARENT_WIDTH, PARENT_HEIGHT);
		verify(actor).setX(PARENT_WIDTH - ACTOR_WIDTH);
	}

	@Test
	public void top_returnsPaddingBuilderAndAlignsTop() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		RulesPaddingBuilder result = builder.top();

        assertInstanceOf(RulesPaddingBuilder.class, result);
		builder.execute(PARENT_WIDTH, PARENT_HEIGHT);
		verify(actor).setY(PARENT_HEIGHT - ACTOR_HEIGHT);
	}

	@Test
	public void bottom_returnsPaddingBuilderAndAlignsBottom() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		RulesPaddingBuilder result = builder.bottom();

        assertInstanceOf(RulesPaddingBuilder.class, result);
		builder.execute(PARENT_WIDTH, PARENT_HEIGHT);
		verify(actor).setY(0f);
	}

	@Test
	public void centerH_returnsParentBuilderAndCentersHorizontally() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		LayoutRulesBuilder result = builder.centerH();

		assertSame(builder, result);
		builder.execute(PARENT_WIDTH, PARENT_HEIGHT);
		verify(actor).setX((PARENT_WIDTH - ACTOR_WIDTH) / 2f);
	}

	@Test
	public void centerV_returnsParentBuilderAndCentersVertically() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		LayoutRulesBuilder result = builder.centerV();

		assertSame(builder, result);
		builder.execute(PARENT_WIDTH, PARENT_HEIGHT);
		verify(actor).setY((PARENT_HEIGHT - ACTOR_HEIGHT) / 2f);
	}

	@Test
	public void chainedRulesAcrossBuilders_applyAll() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		LayoutRulesBuilder result = builder.left().withPadding(8f).centerV();

		assertSame(builder, result);
		builder.execute(PARENT_WIDTH, PARENT_HEIGHT);
		verify(actor).setX(8f);
		verify(actor).setY((PARENT_HEIGHT - ACTOR_HEIGHT) / 2f);
	}

	@Test
	public void overlappingHorizontalRules_lastRuleWins() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		builder.left().withPadding(4f).right().withPadding(6f);

		builder.execute(PARENT_WIDTH, PARENT_HEIGHT);
		verify(actor).setX(PARENT_WIDTH - ACTOR_WIDTH - 6f);
	}

	@Test
	public void constructorWithParent_returnsParentForChaining() {
		Actor actor = newActor();
		LayoutRulesBuilder parentBuilder = new LayoutRulesBuilder(actor);
		LayoutRulesBuilder childBuilder = new LayoutRulesBuilder(mock(Actor.class), parentBuilder);

		LayoutRulesBuilder result = childBuilder.centerH();

		assertSame(parentBuilder, result);
	}

	@Test
	public void execute_usesProvidedParentDimensions() {
		Actor actor = newActor();
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		builder.centerH().centerV();
		builder.execute(1000f, 500f);

		verify(actor).setX((1000f - ACTOR_WIDTH) / 2f);
		verify(actor).setY((500f - ACTOR_HEIGHT) / 2f);
	}
}
