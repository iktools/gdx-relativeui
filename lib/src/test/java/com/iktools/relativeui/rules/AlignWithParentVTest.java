package com.iktools.relativeui.rules;

import com.badlogic.gdx.scenes.scene2d.Actor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlignWithParentVTest {

	private static final float PARENT_WIDTH = 800f;
	private static final float PARENT_HEIGHT = 600f;
	private static final float ACTOR_HEIGHT = 60f;

	@Test
	public void bottomAlignNoPadding_positionsActorAtBottomEdge() {
		Actor actor = mock(Actor.class);
		when(actor.getHeight()).thenReturn(ACTOR_HEIGHT);

		new AlignWithParentV(actor, 0f, 0f, 0f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setY(0f);
	}

	@Test
	public void topAlignNoPadding_positionsActorAtTopEdge() {
		Actor actor = mock(Actor.class);
		when(actor.getHeight()).thenReturn(ACTOR_HEIGHT);

		new AlignWithParentV(actor, 1f, -1f, 0f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setY(PARENT_HEIGHT - ACTOR_HEIGHT);
	}

	@Test
	public void centerAlignNoPadding_positionsActorAtVerticalCenter() {
		Actor actor = mock(Actor.class);
		when(actor.getHeight()).thenReturn(ACTOR_HEIGHT);

		new AlignWithParentV(actor, 0.5f, -0.5f, 0f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setY((PARENT_HEIGHT - ACTOR_HEIGHT) / 2f);
	}

	@Test
	public void bottomAlignWithOffset_positionsActorOffsetFromBottomEdge() {
		Actor actor = mock(Actor.class);
		when(actor.getHeight()).thenReturn(ACTOR_HEIGHT);

		new AlignWithParentV(actor, 0f, 0f, 10f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setY(10f);
	}

	@Test
	public void topAlignWithOffset_positionsActorOffsetFromTopEdge() {
		Actor actor = mock(Actor.class);
		when(actor.getHeight()).thenReturn(ACTOR_HEIGHT);

		new AlignWithParentV(actor, 1f, -1f, -10f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setY(PARENT_HEIGHT - ACTOR_HEIGHT - 10f);
	}

	@Test
	public void zeroHeightActor_positionsUsingParentHeightOnly() {
		Actor actor = mock(Actor.class);
		when(actor.getHeight()).thenReturn(0f);

		new AlignWithParentV(actor, 1f, -1f, 0f).execute(PARENT_WIDTH, PARENT_HEIGHT);

		verify(actor).setY(PARENT_HEIGHT);
	}
}