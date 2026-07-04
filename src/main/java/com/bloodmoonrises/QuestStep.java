package com.bloodmoonrises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import net.runelite.api.coords.WorldPoint;

@Getter
public class QuestStep
{
    private final String phase;
    private final String text;
    // Extra detail: puzzle solutions, boss mechanics, warnings.
    private String detail;
    // Items needed for this step (shown in the panel dropdown and overlay).
    private String items;
    // Concrete item names to live-track against inventory/equipment/bank.
    // Matched case-insensitively as a substring of the item name, so "Pickaxe"
    // matches "Rune pickaxe" and "Super restore" matches "Super restore(4)".
    private final List<String> trackedItems = new ArrayList<>();
    // Chat-message phrases that mark this step complete (advance to the next step).
    private final List<String> chatTriggers = new ArrayList<>();
    // NPC/GameObject names to highlight in the scene while this step is active.
    private final List<String> highlights = new ArrayList<>();
    // Where to go for this step; drives the tile highlight, minimap guide and world map marker.
    private WorldPoint target;

    private QuestStep(String phase, String text)
    {
        this.phase = phase;
        this.text = text;
    }

    static QuestStep step(String phase, String text)
    {
        return new QuestStep(phase, text);
    }

    QuestStep detail(String detail)
    {
        this.detail = detail;
        return this;
    }

    QuestStep items(String items)
    {
        this.items = items;
        return this;
    }

    QuestStep tracked(String... names)
    {
        trackedItems.addAll(Arrays.asList(names));
        return this;
    }

    QuestStep onChat(String... phrases)
    {
        chatTriggers.addAll(Arrays.asList(phrases));
        return this;
    }

    QuestStep highlight(String... names)
    {
        highlights.addAll(Arrays.asList(names));
        return this;
    }

    QuestStep target(int x, int y, int plane)
    {
        this.target = new WorldPoint(x, y, plane);
        return this;
    }

    public boolean hasDetail()
    {
        return detail != null && !detail.isEmpty();
    }

    public boolean hasItems()
    {
        return items != null && !items.isEmpty();
    }

    public boolean hasTrackedItems()
    {
        return !trackedItems.isEmpty();
    }

    // Message with chat tags already stripped.
    public boolean matchesChat(String message)
    {
        if (message == null)
        {
            return false;
        }
        String lower = message.toLowerCase();
        for (String trigger : chatTriggers)
        {
            if (lower.contains(trigger.toLowerCase()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean matchesHighlight(String name)
    {
        if (name == null)
        {
            return false;
        }
        for (String h : highlights)
        {
            if (name.equalsIgnoreCase(h))
            {
                return true;
            }
        }
        return false;
    }
}
