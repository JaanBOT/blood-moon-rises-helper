# Blood Moon Rises Helper

A RuneLite plugin in the style of Quest Helper, built specifically for **The Blood Moon Rises**
(quest #180, Grandmaster, released 30 June 2026) — the finale of the Myreque series that unlocks
Vampyrium.

## Features

- **Step-by-step walkthrough** — all 12 phases from Sarius Guile at the Icyene Graveyard through
  the final Lowerniel Drakan fight, as an on-screen overlay plus a sidebar panel.
- **Every puzzle solved inline**: book plinths, bust order (3-4-1-2), clock hands (11/9 & 12/4),
  painting chest (R-D-R-L-L), statue racks, lockbox (426 hint), SPEAR passcode, gilded bookcase,
  potion basins, refiner (53 HP), TOOLS shed, prayer chest (35158), and more.
- **Boss mechanic cheat-sheets** for all four Lowerniel encounters, the Wyrd, the Ver Sinhaza
  gauntlet, and the forest escape — including spear-dodge directions, the phase 2 blood-wave
  pattern, phase 3 lunge reads, and prayer-switch timing.
- **Progress** is saved between sessions. Advance with the panel's *Done, next* / *Back* buttons.
- **Live item tracking** — steps with concrete required items show them color-coded in the panel:
  **green** = in your inventory or equipped, **white** = seen in your bank, **red** = not found.
  Open your bank once per session so the plugin learns its contents.
- **Chat auto-advance** — matching game messages mark a step complete and advance the guide, and
  the quest-complete screen jumps straight to the final step. The trigger phrases are best-effort
  guesses at Jagex's wording; refine them in `QuestData` (`.onChat(...)`) as real messages are
  confirmed in game.
- **Overview pane** — a collapsible section above phase 1 with the quest's requirements, items
  overview, item-color legend, rewards, and every enemy with its combat level.
- **Auto-advance hook**: once the community documents the quest's progress varbit, enter its ID in
  the plugin config to have the plugin react to progress changes (manual mode by default).

## Building / running

```
gradlew build
```

To run a development client with the plugin loaded, run
`BloodMoonRisesPluginTest.main()` from your IDE.

## Sources

- [OSRS Wiki — The Blood Moon Rises](https://oldschool.runescape.wiki/w/The_Blood_Moon_Rises)
- [Official release news](https://secure.runescape.com/m=news/the-blood-moon-rises---out-today?oldschool=1)
- Community dodge tips (r/2007scape / guide sites): blood on his left → dodge right, blood on his
  right → dodge left, both sides → step next to him; True Tile strongly recommended.
