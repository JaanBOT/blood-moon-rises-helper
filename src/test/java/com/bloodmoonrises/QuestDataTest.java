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

    @Test
    public void trackingMetadataIsWellFormed()
    {
        boolean anyTracked = false;
        for (QuestStep step : QuestData.STEPS)
        {
            for (String item : step.getTrackedItems())
            {
                assertFalse("blank tracked item", item.trim().isEmpty());
            }
            anyTracked |= step.hasTrackedItems();
        }
        assertTrue(anyTracked);
    }

    @Test
    public void chatMatchingIsCaseInsensitive()
    {
        QuestStep mineStep = QuestData.STEPS.stream()
            .filter(s -> !s.getChatTriggers().isEmpty())
            .findFirst()
            .orElseThrow(IllegalStateException::new);
        String trigger = mineStep.getChatTriggers().get(0);
        assertTrue(mineStep.matchesChat("Prefix " + trigger.toUpperCase() + " suffix"));
        assertFalse(mineStep.matchesChat("Unrelated message"));
    }
}
