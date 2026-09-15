package com.iktools.relativeui.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlignWithParentHTest {

	private static final float PARENT_WIDTH = 800f;
	private static final float PARENT_HEIGHT = 600f;
	private static final float ACTOR_WIDTH = 120f;

	@Test
	public void leftAlignNoPadding_positionsActorAtLeftEdge() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(ACTOR_WIDTH);

		new AlignWithParentH(actor, 0f, 0f, 0f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setX(0f);
	}

	@Test
	public void rightAlignNoPadding_positionsActorAtRightEdge() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(ACTOR_WIDTH);

		new AlignWithParentH(actor, 1f, -1f, 0f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setX(PARENT_WIDTH - ACTOR_WIDTH);
	}

	@Test
	public void centerAlignNoPadding_positionsActorAtHorizontalCenter() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(ACTOR_WIDTH);

		new AlignWithParentH(actor, 0.5f, -0.5f, 0f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setX((PARENT_WIDTH - ACTOR_WIDTH) / 2f);
	}

	@Test
	public void leftAlignWithOffset_positionsActorOffsetFromLeftEdge() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(ACTOR_WIDTH);

		new AlignWithParentH(actor, 0f, 0f, 10f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setX(10f);
	}

	@Test
	public void rightAlignWithOffset_positionsActorOffsetFromRightEdge() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(ACTOR_WIDTH);

		new AlignWithParentH(actor, 1f, -1f, -10f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setX(PARENT_WIDTH - ACTOR_WIDTH - 10f);
	}

	@Test
	public void zeroWidthActor_positionsUsingParentWidthOnly() {
		Actor actor = mock(Actor.class);
		when(actor.getWidth()).thenReturn(0f);

		new AlignWithParentH(actor, 1f, -1f, 0f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setX(PARENT_WIDTH);
	}
}