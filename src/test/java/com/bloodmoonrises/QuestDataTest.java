package com.bloodmoonrises;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuestDataTest
{
    @Test
    public void stepsAreNonEmptyAndWellFormed()
    {
        assertFalse(QuestData.STEPS.isEmpty());
        for (QuestStep step : QuestData.STEPS)
        {
            assertFalse(step.getPhase().trim().isEmpty());
            assertFalse(step.getText().trim().isEmpty());
        }
    }

    @Test
    public void coversAllTwelvePhasesAndCompletion()
    {
        for (int phase = 1; phase <= 12; phase++)
        {
            final String prefix = phase + ".";
            assertTrue("Missing phase " + phase,
                QuestData.STEPS.stream().anyMatch(s -> s.getPhase().startsWith(prefix)));
        }
        assertTrue(QuestData.STEPS.get(QuestData.STEPS.size() - 1).getPhase().startsWith("Complete"));
    }
}
