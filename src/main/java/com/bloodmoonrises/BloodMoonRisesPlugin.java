package com.bloodmoonrises;

import com.google.inject.Provides;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Inject;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.NPC;
import net.runelite.api.ObjectComposition;
import net.runelite.api.TileObject;
import net.runelite.api.events.DecorativeObjectDespawned;
import net.runelite.api.events.DecorativeObjectSpawned;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GroundObjectDespawned;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.events.WallObjectDespawned;
import net.runelite.api.events.WallObjectSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;
import net.runelite.client.ui.overlay.worldmap.WorldMapPointManager;
import net.runelite.client.util.ImageUtil;

@PluginDescriptor(
    name = "Blood Moon Rises Helper",
    description = "Quest helper for The Blood Moon Rises: step-by-step guidance, puzzle solutions and boss mechanics",
    tags = {"quest", "helper", "myreque", "vampyre", "drakan"}
)
public class BloodMoonRisesPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ClientToolbar clientToolbar;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private BloodMoonRisesOverlay overlay;

    @Inject
    private SceneHighlightOverlay sceneOverlay;

    @Inject
    private BloodMoonRisesConfig config;

    @Inject
    private ConfigManager configManager;

    @Inject
    private WorldMapPointManager worldMapPointManager;

    private BloodMoonRisesPanel panel;
    private NavigationButton navButton;
    private WorldMapPoint mapPoint;
    private BufferedImage icon;

    @Getter
    private int currentStep;

    @Getter
    private final List<NPC> npcs = new CopyOnWriteArrayList<>();

    @Getter
    private final List<TileObject> objects = new CopyOnWriteArrayList<>();

    private final Map<Integer, String> objectNameCache = new HashMap<>();

    @Provides
    BloodMoonRisesConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(BloodMoonRisesConfig.class);
    }

    @Override
    protected void startUp()
    {
        currentStep = Math.max(0, Math.min(config.currentStep(), QuestData.STEPS.size() - 1));

        panel = new BloodMoonRisesPanel(this);
        icon = ImageUtil.loadImageResource(getClass(), "/icon.png");
        navButton = NavigationButton.builder()
            .tooltip("Blood Moon Rises Helper")
            .icon(icon)
            .priority(7)
            .panel(panel)
            .build();
        clientToolbar.addNavigation(navButton);

        overlayManager.add(overlay);
        overlayManager.add(sceneOverlay);
        updateMapPoint();
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
        overlayManager.remove(sceneOverlay);
        clientToolbar.removeNavigation(navButton);
        removeMapPoint();
        npcs.clear();
        objects.clear();
        objectNameCache.clear();
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        if (event.getGameState() == GameState.LOADING)
        {
            objects.clear();
        }
        else if (event.getGameState() == GameState.LOGIN_SCREEN || event.getGameState() == GameState.HOPPING)
        {
            npcs.clear();
            objects.clear();
        }
    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned event)
    {
        npcs.add(event.getNpc());
    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned event)
    {
        npcs.remove(event.getNpc());
    }

    @Subscribe
    public void onGameObjectSpawned(GameObjectSpawned event)
    {
        objects.add(event.getGameObject());
    }

    @Subscribe
    public void onGameObjectDespawned(GameObjectDespawned event)
    {
        objects.remove(event.getGameObject());
    }

    @Subscribe
    public void onWallObjectSpawned(WallObjectSpawned event)
    {
        objects.add(event.getWallObject());
    }

    @Subscribe
    public void onWallObjectDespawned(WallObjectDespawned event)
    {
        objects.remove(event.getWallObject());
    }

    @Subscribe
    public void onGroundObjectSpawned(GroundObjectSpawned event)
    {
        objects.add(event.getGroundObject());
    }

    @Subscribe
    public void onGroundObjectDespawned(GroundObjectDespawned event)
    {
        objects.remove(event.getGroundObject());
    }

    @Subscribe
    public void onDecorativeObjectSpawned(DecorativeObjectSpawned event)
    {
        objects.add(event.getDecorativeObject());
    }

    @Subscribe
    public void onDecorativeObjectDespawned(DecorativeObjectDespawned event)
    {
        objects.remove(event.getDecorativeObject());
    }

    @Subscribe
    public void onVarbitChanged(VarbitChanged event)
    {
        int varbitId = config.progressVarbit();
        if (varbitId > 0 && event.getVarbitId() == varbitId)
        {
            panel.refresh();
        }
    }

    public QuestStep getStep()
    {
        return QuestData.STEPS.get(currentStep);
    }

    public void setStep(int index)
    {
        currentStep = Math.max(0, Math.min(index, QuestData.STEPS.size() - 1));
        configManager.setConfiguration(BloodMoonRisesConfig.GROUP, BloodMoonRisesConfig.CURRENT_STEP_KEY, currentStep);
        updateMapPoint();
        if (panel != null)
        {
            panel.refresh();
        }
    }

    public void nextStep()
    {
        setStep(currentStep + 1);
    }

    public void previousStep()
    {
        setStep(currentStep - 1);
    }

    // Must be called on the client thread (render/event handlers are fine).
    public String getObjectName(TileObject object)
    {
        return objectNameCache.computeIfAbsent(object.getId(), id ->
        {
            ObjectComposition def = client.getObjectDefinition(id);
            if (def == null)
            {
                return "";
            }
            if (def.getImpostorIds() != null)
            {
                def = def.getImpostor();
            }
            return def == null ? "" : def.getName();
        });
    }

    public List<NPC> getHighlightedNpcs()
    {
        QuestStep step = getStep();
        List<NPC> out = new ArrayList<>();
        for (NPC npc : npcs)
        {
            if (step.matchesHighlight(npc.getName()))
            {
                out.add(npc);
            }
        }
        return out;
    }

    public List<TileObject> getHighlightedObjects()
    {
        QuestStep step = getStep();
        List<TileObject> out = new ArrayList<>();
        for (TileObject obj : objects)
        {
            if (step.matchesHighlight(getObjectName(obj)))
            {
                out.add(obj);
            }
        }
        return out;
    }

    private void updateMapPoint()
    {
        removeMapPoint();
        QuestStep step = getStep();
        if (step.getTarget() != null && icon != null)
        {
            mapPoint = new WorldMapPoint(step.getTarget(), icon);
            mapPoint.setTooltip("Blood Moon Rises: " + step.getPhase());
            mapPoint.setJumpOnClick(true);
            worldMapPointManager.add(mapPoint);
        }
    }

    private void removeMapPoint()
    {
        if (mapPoint != null)
        {
            worldMapPointManager.remove(mapPoint);
            mapPoint = null;
        }
    }

    public Client getClient()
    {
        return client;
    }

    public BloodMoonRisesConfig getConfig()
    {
        return config;
    }
}
