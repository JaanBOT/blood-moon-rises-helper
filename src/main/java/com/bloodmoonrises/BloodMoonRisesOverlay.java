package com.bloodmoonrises;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class BloodMoonRisesOverlay extends OverlayPanel
{
    private static final Color BLOOD_RED = new Color(170, 20, 20);
    private static final Color ITEM_COLOR = new Color(140, 200, 255);
    private static final Color TARGET_COLOR = new Color(255, 140, 0);

    private final Client client;
    private final BloodMoonRisesPlugin plugin;
    private final BloodMoonRisesConfig config;

    @Inject
    public BloodMoonRisesOverlay(Client client, BloodMoonRisesPlugin plugin, BloodMoonRisesConfig config)
    {
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.TOP_LEFT);
        setPreferredSize(new Dimension(280, 0));
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!config.showOverlay())
        {
            return null;
        }

        QuestStep step = plugin.getStep();

        panelComponent.getChildren().add(TitleComponent.builder()
            .text("The Blood Moon Rises")
            .color(BLOOD_RED)
            .build());

        panelComponent.getChildren().add(LineComponent.builder()
            .left(step.getPhase())
            .leftColor(Color.ORANGE)
            .right((plugin.getCurrentStep() + 1) + "/" + QuestData.STEPS.size())
            .build());

        panelComponent.getChildren().add(LineComponent.builder()
            .left(step.getText())
            .build());

        if (step.hasItems())
        {
            panelComponent.getChildren().add(LineComponent.builder()
                .left("Items: " + step.getItems())
                .leftColor(ITEM_COLOR)
                .build());
        }

        if (config.showDetails() && step.hasDetail())
        {
            panelComponent.getChildren().add(LineComponent.builder()
                .left(step.getDetail())
                .leftColor(new Color(200, 200, 160))
                .build());
        }

        // Direction/distance readout toward the step's travel target.
        WorldPoint target = step.getTarget();
        Player player = client.getLocalPlayer();
        if (config.highlightTargets() && target != null && player != null)
        {
            WorldPoint pos = player.getWorldLocation();
            int dx = target.getX() - pos.getX();
            int dy = target.getY() - pos.getY();
            int dist = Math.max(Math.abs(dx), Math.abs(dy));
            if (dist > 3)
            {
                panelComponent.getChildren().add(LineComponent.builder()
                    .left("Head " + direction(dx, dy) + " (" + dist + " tiles) - marked on world map")
                    .leftColor(TARGET_COLOR)
                    .build());
            }
        }

        return super.render(graphics);
    }

    private static String direction(int dx, int dy)
    {
        String ns = dy > 3 ? "north" : (dy < -3 ? "south" : "");
        String ew = dx > 3 ? "east" : (dx < -3 ? "west" : "");
        String dir = ns + (ns.isEmpty() || ew.isEmpty() ? "" : "-") + ew;
        return dir.isEmpty() ? "here" : dir;
    }
}
