package com.bloodmoonrises;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.runelite.api.TileObject;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.Perspective;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

/**
 * Highlights the current step's NPCs/objects and marks the travel target tile
 * plus a minimap guide line toward it.
 */
public class SceneHighlightOverlay extends Overlay
{
    private static final Color NPC_COLOR = new Color(0, 220, 255);
    private static final Color OBJECT_COLOR = new Color(80, 255, 100);
    private static final Color TARGET_COLOR = new Color(255, 140, 0);

    private final BloodMoonRisesPlugin plugin;
    private final BloodMoonRisesConfig config;

    @Inject
    public SceneHighlightOverlay(BloodMoonRisesPlugin plugin, BloodMoonRisesConfig config)
    {
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (!config.highlightTargets())
        {
            return null;
        }

        Client client = plugin.getClient();

        for (NPC npc : plugin.getHighlightedNpcs())
        {
            Shape hull = npc.getConvexHull();
            if (hull != null)
            {
                OverlayUtil.renderPolygon(graphics, toPolygon(hull), NPC_COLOR);
            }
        }

        for (TileObject obj : plugin.getHighlightedObjects())
        {
            Shape clickbox = obj.getClickbox();
            if (clickbox != null)
            {
                graphics.setColor(OBJECT_COLOR);
                graphics.setStroke(new BasicStroke(2));
                graphics.draw(clickbox);
                graphics.setColor(new Color(OBJECT_COLOR.getRed(), OBJECT_COLOR.getGreen(), OBJECT_COLOR.getBlue(), 40));
                graphics.fill(clickbox);
            }
        }

        WorldPoint target = plugin.getStep().getTarget();
        if (target != null)
        {
            renderTargetGuide(graphics, client, target);
        }

        return null;
    }

    private void renderTargetGuide(Graphics2D graphics, Client client, WorldPoint target)
    {
        Player player = client.getLocalPlayer();
        if (player == null)
        {
            return;
        }

        // Target tile in the loaded scene: draw a tile marker.
        if (target.getPlane() == client.getPlane())
        {
            LocalPoint lp = LocalPoint.fromWorld(client, target);
            if (lp != null)
            {
                Polygon tile = Perspective.getCanvasTilePoly(client, lp);
                if (tile != null)
                {
                    OverlayUtil.renderPolygon(graphics, tile, TARGET_COLOR);
                    net.runelite.api.Point text = Perspective.getCanvasTextLocation(client, graphics, lp, "Go here", 40);
                    if (text != null)
                    {
                        OverlayUtil.renderTextLocation(graphics, text, "Go here", TARGET_COLOR);
                    }
                }

                // Minimap guide toward the target while it's loaded.
                net.runelite.api.Point mini = Perspective.localToMinimap(client, lp, 2500);
                net.runelite.api.Point playerMini = Perspective.localToMinimap(client, player.getLocalLocation());
                if (mini != null && playerMini != null)
                {
                    graphics.setColor(TARGET_COLOR);
                    graphics.setStroke(new BasicStroke(2));
                    graphics.drawLine(playerMini.getX(), playerMini.getY(), mini.getX(), mini.getY());
                    graphics.fillOval(mini.getX() - 3, mini.getY() - 3, 6, 6);
                }
            }
        }
    }

    private static Polygon toPolygon(Shape shape)
    {
        if (shape instanceof Polygon)
        {
            return (Polygon) shape;
        }
        Polygon poly = new Polygon();
        java.awt.geom.PathIterator it = shape.getPathIterator(null, 1.0);
        double[] coords = new double[6];
        while (!it.isDone())
        {
            int type = it.currentSegment(coords);
            if (type != java.awt.geom.PathIterator.SEG_CLOSE)
            {
                poly.addPoint((int) coords[0], (int) coords[1]);
            }
            it.next();
        }
        return poly;
    }
}
