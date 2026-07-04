package com.bloodmoonrises;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(BloodMoonRisesConfig.GROUP)
public interface BloodMoonRisesConfig extends Config
{
    String GROUP = "bloodmoonrises";
    String CURRENT_STEP_KEY = "currentStep";

    @ConfigItem(
        keyName = "showOverlay",
        name = "Show step overlay",
        description = "Show the current quest step as an on-screen overlay",
        position = 1
    )
    default boolean showOverlay()
    {
        return true;
    }

    @ConfigItem(
        keyName = "showDetails",
        name = "Show puzzle/boss details",
        description = "Include puzzle solutions and boss mechanics in the overlay",
        position = 2
    )
    default boolean showDetails()
    {
        return true;
    }

    @ConfigItem(
        keyName = "highlightTargets",
        name = "Highlight NPCs/objects & path",
        description = "Highlight the current step's NPCs and objects in the scene, mark the travel target tile, "
            + "and draw a minimap/world map guide toward it",
        position = 3
    )
    default boolean highlightTargets()
    {
        return true;
    }

    @ConfigItem(
        keyName = "progressVarbit",
        name = "Progress varbit ID",
        description = "Varbit tracking quest progress, for auto-advancing steps. "
            + "Leave at 0 (manual mode) until the community documents the varbit for The Blood Moon Rises.",
        position = 3
    )
    default int progressVarbit()
    {
        return 0;
    }

    @ConfigItem(
        keyName = CURRENT_STEP_KEY,
        name = "",
        description = "",
        hidden = true
    )
    default int currentStep()
    {
        return 0;
    }
}
