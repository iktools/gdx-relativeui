package com.iktools.relativeui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RulesPaddingBuilderTest {

	@Test
	public void withPadding_replacesLastRuleInsteadOfAppending() throws Exception {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(100f);
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

        RulesPaddingBuilder leftBuilder = builder.left();
		assertEquals(1, rulesOf(builder).size());

        leftBuilder.withPadding(25f);

		assertEquals(1, rulesOf(builder).size());
		builder.execute(300f, 200f);
		verify(actor).setX(25f);
	}

	@Test
	public void withPadding_returnsParentBuilder() {
		Actor actor = mock(Actor.class);
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		RulesPaddingBuilder paddingBuilder = builder.left();

		LayoutRulesBuilder result = paddingBuilder.withPadding(10f);

		assertSame(builder, result);
	}

	@Test
	public void leftWithPadding_positionsActorOffsetFromLeft() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(100f);
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		builder.left().withPadding(25f);
		builder.execute(300f, 200f);

		verify(actor).setX(25f);
	}

	@Test
	public void rightWithPadding_positionsActorOffsetFromRight() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(100f);
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		builder.right().withPadding(10f);
		builder.execute(300f, 200f);

		verify(actor).setX(190f);
	}

	@Test
	public void topWithPadding_positionsActorOffsetFromTop() {
		Actor actor = mock(Actor.class);
		when(actor.getHeight()).thenReturn(50f);
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		builder.top().withPadding(10f);
		builder.execute(300f, 200f);

		verify(actor).setY(140f);
	}

	@Test
	public void bottomWithPadding_positionsActorOffsetFromBottom() {
		Actor actor = mock(Actor.class);
		when(actor.getHeight()).thenReturn(50f);
		LayoutRulesBuilder builder = new LayoutRulesBuilder(actor);

		builder.bottom().withPadding(5f);
		builder.execute(300f, 200f);

		verify(actor).setY(5f);
	}

	private static List<?> rulesOf(LayoutRulesBuilder builder) throws Exception {
		Field rules = LayoutRulesBuilder.class.getDeclaredField("rules");
		rules.setAccessible(true);
		return (List<?>) rules.get(builder);
	}
}
