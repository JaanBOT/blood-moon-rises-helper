package com.bloodmoonrises;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BloodMoonRisesPluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(BloodMoonRisesPlugin.class);
        RuneLite.main(args);
    }
}
