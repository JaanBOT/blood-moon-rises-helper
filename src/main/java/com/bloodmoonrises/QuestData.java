package com.bloodmoonrises;

import com.google.common.collect.ImmutableList;
import java.util.List;
import static com.bloodmoonrises.QuestStep.step;

/**
 * Full walkthrough for The Blood Moon Rises (quest #180, Grandmaster, released 2026-06-30).
 * Source: OSRS Wiki - https://oldschool.runescape.wiki/w/The_Blood_Moon_Rises
 *
 * Travel targets use overworld coordinates and are approximate; steps inside Castle Drakan,
 * Sangvesti and Vampyrium have no target because those regions' coordinates are not yet
 * publicly mapped. Highlights match NPC/object names case-insensitively.
 */
public final class QuestData
{
    private QuestData()
    {
    }

    public static final String REQUIREMENTS =
        "Skills: 74 Slayer, 74 Woodcutting, 72 Smithing, 72 Cooking, 70 Fletching, "
        + "66 Mining, 65 Hunter, 64 Crafting, 64 Herblore, 57 Magic. "
        + "Quests: A Night at the Theatre, Sins of the Father (and their prerequisites). "
        + "Recommended: 110+ combat.";

    public static final String ITEMS =
        "Required: Blisterwood flail (or 40k coins), Vyre noble outfit (obtainable during quest), "
        + "melee gear, tinderbox, pickaxe, hammer, knife, chisel (all obtainable during quest). "
        + "Recommended: fast-hitting ranged/magic weapon, Efaritay's aid, food, prayer/stamina potions, "
        + "Drakan's medallion, Morytania legs 3+.";

    public static final String REWARDS =
        "4 Quest points, Tome of experience (6x 30,000 XP in skills 70+), Hallowed flail, "
        + "Sunspear upgrade, access to Vampyrium and Sangvesti, a fermenting vat, "
        + "and permanent vyre tolerance (no more Vyrewatch harassment).";

    // Enemies fought during the quest, with combat levels. Quest-instanced bosses
    // are scaled versions, not their regular-game counterparts.
    public static final String ENEMIES =
        "Vyrewatch (lvl 87-125, several fights)<br>"
        + "Vyrewatch Sentinel (lvl 151)<br>"
        + "Acidic bloodveld (lvl 120)<br>"
        + "Monk of Zamorak (lvl 45)<br>"
        + "Venator (lvl 198, x3)<br>"
        + "Sanguidae (lvl 164)<br>"
        + "Webbed-winged crow (lvl 89)<br>"
        + "Ancient Feral Vyre (lvl 210, two paired waves)<br>"
        + "Maxilla beast (lvl 302, avoidable via safespots)<br>"
        + "Nylocas Ischyros/Toxobolos (lvl 164, waves)<br>"
        + "Blood serpent (lvl 92, wrangled not killed)<br>"
        + "Maiden of Sugadinti (lvl 620, quest-scaled)<br>"
        + "Nylocas Vasilias (lvl 480, quest-scaled)<br>"
        + "The Wyrd (lvl 564, 1,100 HP - vampyrebane weapon required)<br>"
        + "Lowerniel Drakan (lvl 786, 3,000 HP, fought four times)";

    public static final List<QuestStep> STEPS = ImmutableList.of(
        // Phase 1
        step("1. Starting Out",
            "Talk to Sarius Guile in the Icyene Graveyard (boat from Burgh de Rott, Meiyerditch or Slepe).")
            .items("None to start; bank your melee gear nearby first")
            .highlight("Sarius Guile")
            .target(3684, 3172, 0),
        step("1. Starting Out",
            "Go to the Myreque Hideout under Old Man Ral's house in Meiyerditch and inspect the Makeshift shrine.")
            .highlight("Old Man Ral", "Makeshift shrine", "Trapdoor")
            .target(3605, 3215, 0),
        step("1. Starting Out",
            "Talk to Ivan Strom. Wearing the Vyre noble outfit (chest nearby if needed), select 'Ready to head to Darkmeyer'.")
            .items("Vyre noble outfit (worn) - search the nearby chest if you don't have it")
            .tracked("Vyre noble top", "Vyre noble legs", "Vyre noble shoes")
            .highlight("Ivan Strom", "Chest"),
        step("1. Starting Out",
            "Defend Ivan from Vyrewatch and Sentinels for several minutes.")
            .detail("Pray Protect from Melee. Kill Acidic bloodvelds before they reach Ivan; feed Ivan food if he gets low. "
                + "3 Vyrewatch spawn initially, more replace them; 3 Sentinels spawn throughout.")
            .items("Melee gear, food (some to feed Ivan), prayer potions")
            .tracked("Prayer potion")
            .highlight("Ivan Strom", "Vyrewatch", "Vyrewatch Sentinel", "Acidic bloodveld"),
        step("1. Starting Out",
            "Talk to Ivan again - he'll ask you to meet him at the church in Slepe.")
            .highlight("Ivan Strom"),

        // Phase 2
        step("2. The Truth Hurtz",
            "Travel to Slepe and talk to Ivan Strom west of the chapel.")
            .detail("Bring a Super restore (Monks of Zamorak drain stats later). Keep 6 free inventory slots; no pets.")
            .items("Super restore, combat gear, 6 free inventory slots")
            .tracked("Super restore")
            .highlight("Ivan Strom")
            .target(3660, 3298, 0),
        step("2. The Truth Hurtz",
            "In the Rat & Bat pub, ask bartender Roy about a friend, then try to enter Crombwick Manor north of the church.")
            .highlight("Roy")
            .target(3650, 3306, 0),
        step("2. The Truth Hurtz",
            "Go through the Sisterhood Sanctuary (south Slepe), north to Kroy's laboratory, then up the ladder into Crombwick Manor.")
            .highlight("Ladder")
            .target(3653, 3283, 0),
        step("2. The Truth Hurtz",
            "Help Veliaf Hurtz kill four Vyrewatch, then talk to him.")
            .highlight("Veliaf Hurtz", "Vyrewatch"),
        step("2. The Truth Hurtz",
            "Travel to the Paterdomus basement. Talk to Ivan, read the book, talk to Ivan again.")
            .highlight("Ivan Strom", "Book")
            .target(3405, 3506, 0),
        step("2. The Truth Hurtz",
            "In the Paterdomus chapel, kill the Monks of Zamorak and go up a level. Talk to Ivan and search the bookcases.")
            .items("Combat gear, Super restore (monks drain stats)")
            .tracked("Super restore")
            .highlight("Monk of Zamorak", "Ivan Strom", "Bookcase")
            .target(3406, 3487, 0),
        step("2. The Truth Hurtz",
            "Solve the book plinth puzzle.")
            .detail("North to south: Purple (Pious Proceedings), Green (The Life of Friar), Red (Scruffy notebook), "
                + "Grey (From Misthalin to Morytania), Light blue (Sarl's journal), Brown (Essiandar's notes).")
            .highlight("Plinth"),
        step("2. The Truth Hurtz",
            "Talk to Ivan for Ivandis' writings, read it, talk to him again. Restock supplies, then talk to Ivan at Paterdomus before leaving.")
            .items("Restock: food, prayer potions, pickaxe (Ivan can provide one)")
            .tracked("Pickaxe", "Prayer potion")
            .highlight("Ivan Strom"),
        step("2. The Truth Hurtz",
            "Mine the blockage south of Ivan and head northeast through the tunnel.")
            .items("Pickaxe")
            .tracked("Pickaxe")
            .onChat("break through the blockage", "mine your way through")
            .highlight("Blockage"),
        step("2. The Truth Hurtz",
            "Defeat the Vyrewatch and Sentinels in the tunnel.")
            .detail("Focus Sentinels first with Protect from Magic. They spawn blood orbs that deal passive damage - "
                + "lure Vyrewatch into the orbs for massive damage.")
            .highlight("Vyrewatch", "Vyrewatch Sentinel"),
        step("2. The Truth Hurtz",
            "Talk to Ivan, head north then northwest up the stairs, east to the shrine. "
            + "Pray at the shrine to unlock the Drakan's medallion teleport, then enter the portal.")
            .highlight("Ivan Strom", "Shrine", "Portal"),

        // Phase 3
        step("3. Castle Drakan",
            "Venators will chase you on arrival - split up and escape into another room.")
            .detail("Checkpoints on death, items kept (HCIM lose status). Supply items here: Mysterious jerky (22hp), "
                + "Smelly kebab (18hp fast), Foul chunky (super restore), Rancid slimy (brew), Rank frothy (super combat), "
                + "Putrid sticky (stamina), Jar of congealed blood (full hp/prayer).")
            .highlight("Venator"),
        step("3. Castle Drakan",
            "Clocks: read the poem scroll south of start, search the throne east, then solve the bust puzzle.")
            .detail("Pull the busts in order (north to south): 3rd, 4th, 1st, 2nd. Search the throne for the Half moon key.")
            .highlight("Poem scroll", "Throne", "Bust"),
        step("3. Castle Drakan",
            "Collect the Small clock hand (southwest shelves), tinderbox, explosive barrel, Drakan emblem, "
            + "and Large clock hand (crate behind the emblem receptacle door).")
            .items("Collect here: Small clock hand, Tinderbox, Explosive barrel, Drakan emblem, Large clock hand")
            .tracked("Small clock hand", "Tinderbox", "Drakan emblem", "Large clock hand")
            .highlight("Shelves", "Crate", "Explosive barrel", "Receptacle"),
        step("3. Castle Drakan",
            "Pull the lever east of the crescent moon door, take the portal, and reach the dining room fireplace. Set the clocks.")
            .detail("Western clock: big hand 11, small hand 9. Eastern clock: big hand 12, small hand 4. "
                + "Then inspect and search the fireplace for a second Drakan emblem.")
            .items("Small clock hand, Large clock hand")
            .tracked("Small clock hand", "Large clock hand")
            .highlight("Lever", "Portal", "Clock", "Fireplace"),
        step("3. Castle Drakan",
            "Crescent moon key: shuffle the Drakan emblems through the receptacle doors (blow the cracked wall with the barrel) "
            + "until you have three emblems, and grab the Ornate skull from the table.")
            .items("Explosive barrel, Drakan emblems, Tinderbox")
            .tracked("Drakan emblem", "Tinderbox", "Ornate skull")
            .highlight("Receptacle", "Cracked wall", "Ornate skull"),
        step("3. Castle Drakan",
            "Find Veliaf downstairs and open the chest on the northern wall.")
            .detail("Chest code (from the vampyre paintings): Right, Down, Right, Left, Left. Gives the Crescent moon key.")
            .highlight("Veliaf Hurtz", "Chest"),
        step("3. Castle Drakan",
            "New moon key: in the laboratory kill the Sanguidae, then build the syringe.")
            .detail("Syringe barrel (table) + Syringe plunger (crate near sink) + Syringe needle (tongs on sink; tongs from "
                + "sharp knife on venator corpse; knife from broken pipe on cabinet). Fill from Venator stomach, "
                + "use full syringe on the chest for the Left crest half.")
            .items("Collect here: Syringe barrel, Venator stomach, Sink plug, Broken pipe, Sharp knife, Syringe plunger, Tongs")
            .highlight("Sanguidae", "Sink", "Crate", "Cabinet", "Venator corpse", "Chest"),
        step("3. Castle Drakan",
            "Armoury: deal with the Venator, then solve the statue puzzle for the Right crest half.")
            .detail("Pray Melee/Missiles vs the Venator; toggle prayers OFF when it screeches. "
                + "Statues east to west should hold: Battleaxes, Spears, Maces, Swords. "
                + "Combine crest halves, use the full crest on the fireplace for the New moon key.")
            .items("Left crest half + Right crest half -> Full crest")
            .tracked("Left crest half", "Right crest half")
            .highlight("Venator", "Weapons rack", "Statue", "Chest", "Fireplace"),
        step("3. Castle Drakan",
            "Free Safalaan and Vanescula in the basement jail; pick up the Gibbous moon key.")
            .highlight("Safalaan Hallow", "Vanescula Drakan", "Gibbous moon key"),
        step("3. Castle Drakan",
            "Gilded key part 1: get the Ornate knife (crate through the SE gibbous door), then the Lockbox from the fireplace room.")
            .detail("Lockbox code, left to right: the symbol on the 4 banners, the symbol on the 2 shields, the symbol on the "
                + "6 braziers (venator head code '426'). Gives a Fancy gem.")
            .items("Tinderbox (to light the fireplace)")
            .highlight("Crate", "Lever", "Portal", "Fireplace", "Chest", "Lockbox"),
        step("3. Castle Drakan",
            "Enter the passcode door and finish the display case.")
            .detail("Door passcode: SPEAR. Get the Mysterious book (bookcase) for a 2nd Fancy gem. Put both gems in the "
                + "Mounted venator head, search it for the Ornate hourglass, then use skull + hourglass + knife on the "
                + "display case. Search it for the Gilded key.")
            .items("Ornate skull, Ornate hourglass, Ornate knife, 2x Fancy gem")
            .tracked("Ornate skull", "Ornate hourglass", "Ornate knife", "Fancy gem")
            .highlight("Bookcase", "Mounted venator head", "Display case"),
        step("3. Castle Drakan",
            "Full moon key: through the gilded door, kill the Venator, talk to Ivan, then solve the gilded bookcase.")
            .detail("Get the Gilded book (north bookcase) and Vampyre book (SE bookcase). Use the gilded book on the western "
                + "gilded bookcase and arrange the Vampyric House symbols ALPHABETICALLY. Take the Full moon key in the secret passage.")
            .items("Gilded book, Vampyre book")
            .tracked("Gilded book", "Vampyre book")
            .highlight("Venator", "Ivan Strom", "Bookcase", "Gilded bookcase"),
        step("3. Castle Drakan",
            "Solid key: reach the potion room (kill Webbed-winged crows if needed) and solve the basin puzzle.")
            .detail("Cloudy grey -> SW basin. Weightless black -> NE. Thick red -> NW. Cold bluish-white -> SE. "
                + "Search the altar for the Ancient symbol, then combine it with the Ancient shield (crate by the window), "
                + "mount the shield on the southern wall and search it for the Solid key.")
            .items("Cloudy grey/Weightless black/Thick red/Cold bluish-white potions, Ancient symbol, Ancient shield")
            .highlight("Webbed-winged crow", "Shelves", "Basin", "Altar", "Crate", "Empty mount"),
        step("3. Castle Drakan",
            "Destroy the stockpile in the solid-door laboratory.")
            .detail("Refiner puzzle - panel must read 53 HP: Blood+Essence = 17 (make TWO), Blood+Water = 13, "
                + "Essence+Water = 6. Use all four vials on the basin, then destroy the Stockpile through the north door.")
            .items("2x Vial of water, 3x Vial of blood, 3x Pure essence (all from the NW shelves)")
            .tracked("Vial of water", "Vial of blood", "Pure essence")
            .highlight("Shelves", "Basin", "Stockpile"),

        // Phase 4
        step("4. Blood Hunt",
            "In Sangvesti: get the Crank wheel (3 houses east of the altar), use it on the Crank base north of the altar. Drakan starts hunting you!")
            .detail("If you leave, re-enter via the portal in Castle Drakan near Vanescula.")
            .highlight("Crank wheel", "Crank base"),
        step("4. Blood Hunt",
            "Key chain: Dusty book -> Vitur key. Shed code TOOLS -> Bolt cutters. Chained door -> Loop half. "
            + "Vitur house -> Tooth half. Combine -> Myrmel key.")
            .detail("Then: bucket from the water-icon house, fill at the well, use on the north barrel in the Myrmel house "
                + "for the Shadum key. Shadum house (SE) has the trapdoor key.")
            .items("Dusty book, Bolt cutters, Loop half + Tooth half, Bucket, Shadum key")
            .tracked("Bolt cutters", "Myrmel key", "Shadum key")
            .highlight("Bookshelf", "Shed", "Chained door", "Well", "Barrel"),
        step("4. Blood Hunt",
            "In the bank, spam-click the crank to open the gate, climb through the trapdoor and open the chest.")
            .detail("Chest code: 35158 (prayer levels of the five lowest prayers hinted by the chairs: Mystic Will 3, "
                + "Sharp Eye 5, Burst of Strength 1, Clarity of Thought 5, Thick Skin 8).")
            .highlight("Crank", "Trapdoor", "Chest"),
        step("4. Blood Hunt",
            "Get the cog from the Jovkai house (anvil symbol) and return to Vanescula on the east side.")
            .detail("After the Jovkai house Drakan chases at RUN speed - be careful.")
            .highlight("Cog", "Crate", "Vanescula Drakan"),

        // Phase 5
        step("5. Drakan Fight I",
            "Fight Lowerniel Drakan (3,000 HP) alongside Vanescula and Veliaf until 85% HP.")
            .detail("Spear marks on HIS LEFT -> dodge right; marks on HIS RIGHT -> dodge left; step back/diagonally 1-2 tiles, "
                + "tick-perfect. Keep camera high with you and Drakan on the same vertical axis. Safe tiles sparkle at first. "
                + "Low on supplies? Right-click 'quick escape' to restock at the tent.")
            .items("Melee gear, Rancid slimy (brews), Foul chunky (restores), Rank frothy (combat boost)")
            .tracked("Rancid slimy", "Foul chunky", "Rank frothy")
            .highlight("Lowerniel Drakan"),

        // Phase 6
        step("6. Fleeing the Woods",
            "Defeat 2 waves of paired Ancient Feral Vyres.")
            .detail("Damage one at a time; swap targets when one is low.")
            .highlight("Ancient Feral Vyre"),
        step("6. Fleeing the Woods",
            "Chop both sides of the darkwood trees to fell them (bronze axe at the stump - drop a Soul reaper axe first). "
            + "Remove ticks from your inventory. Exit northeast.")
            .detail("Optional: chop the south-peninsula tree for the Rotten diary.")
            .items("Bronze axe (from the stump)")
            .highlight("Darkwood tree", "Tree stump"),
        step("6. Fleeing the Woods",
            "Walk (run OFF) past the Maxilla beasts using safespots; Redemption helps. Then survive the Nylocas waves.")
            .detail("White nylos = melee, yellow = ranged. Use the throwable spines (or harvest the dead Venator); "
                + "fast-hitting weapons recommended.")
            .items("Throwable spines (pick up here), fast-hitting weapon")
            .highlight("Maxilla beast", "Nylocas Ischyros", "Nylocas Toxobolos", "Venator corpse"),
        step("6. Fleeing the Woods",
            "Wrangle the three Blood Serpents from BEHIND (front = 5 poison damage), combine into a Serpent rope, "
            + "use it on the long branched tree southeast.")
            .items("3x Blood serpent -> Serpent rope")
            .tracked("Blood serpent", "Serpent rope")
            .highlight("Blood serpent", "Long branched tree"),

        // Phase 7
        step("7. The Hideout",
            "Help Safalaan: make Amitire stew.")
            .detail("Bowl (shelves by sink), potato + meat (cupboard behind supply table). Water + potato = incomplete stew; "
                + "cook meat, add it, cook stew, add Amitire leaves (plant east of the door). Use on Safalaan.")
            .items("Bowl, Potato, Meat, Amitire leaves (all found here)")
            .tracked("Bowl", "Potato", "Amitire")
            .highlight("Safalaan Hallow", "Efaritay Hallow", "Shelves", "Cupboard", "Range", "Sink", "Amitire plant"),
        step("7. The Hideout",
            "Talk to Efaritay, then upgrade your flail to the Hallowed flail.")
            .detail("Blessed silver sickle (east crate) + Enchanted diamond (south chest) = Diamond sickle (b). "
                + "Enchant diamond (west chest) -> Enchanted diamond sickle (b). Add blisterwood logs -> Blisterwood sickle (e). "
                + "Smith at the anvil with hallowed marks, hammer and your blisterwood flail.")
            .items("Blisterwood flail, Hammer, Hallowed marks, Blessed silver sickle, 2x Enchanted diamond, Blisterwood logs")
            .tracked("Blisterwood flail", "Hammer", "Hallowed mark", "Blisterwood logs")
            .highlight("Efaritay Hallow", "Crate", "Chest", "Anvil", "Workbench"),
        step("7. The Hideout",
            "Talk to Sarei and Ivan in the east room, then fletch Blisterwood stakes (knife on workbench, logs in the north crate).")
            .items("Knife, Blisterwood logs")
            .tracked("Knife", "Blisterwood logs", "Blisterwood stake")
            .highlight("Sarei", "Ivan Strom", "Workbench", "Crate"),
        step("7. The Hideout",
            "Talk to Vanescula downstairs, then Sugadinti Vitur. Gear up and go to the bridge.")
            .items("~15 Rancid slimy, ~5 Foul chunky, 1 Rank frothy, melee gear")
            .tracked("Rancid slimy", "Foul chunky", "Rank frothy")
            .highlight("Vanescula Drakan", "Sugadinti Vitur"),

        // Phase 8
        step("8. Drakan's Onslaught",
            "Outside: shoot the portals with the ballista (1 hit each) until the barrier falls (~60% integrity).")
            .detail("You must participate or the section fails.")
            .highlight("Ballista", "Portal"),
        step("8. Drakan's Onslaught",
            "Inside: grab a hammer and healing potions. Free stunned aranei from rubble and repair window/pillar cracks.")
            .items("Hammer, healing potions (grab inside)")
            .highlight("Rubble", "Crack"),
        step("8. Drakan's Onslaught",
            "Fight Lowerniel again to 58% HP.")
            .detail("Same spear dodges, plus a 2x3 AoE in FRONT and BEHIND him - step to his left or right side after his "
                + "charging animation begins. Efaritay heals you a little. Death = respawn in the main hall with a supply table.")
            .highlight("Lowerniel Drakan"),

        // Phase 9
        step("9. Ver Sinhaza",
            "Teleport to Ver Sinhaza (Drakan's medallion) and speak to Sugadinti Vitur outside the notice board.")
            .detail("Gear for Nylocas + Maiden: any melee weapon works; ranged/magic swaps help. Bring food and potions. "
                + "Fights run back-to-back: Nylocas wave -> Maiden of Sugadinti -> Nylocas wave -> Nylocas Vasilias.")
            .items("Melee weapon, ranged/magic swaps, food, potions")
            .highlight("Sugadinti Vitur")
            .target(3650, 3219, 0),

        // Phase 10
        step("10. Wyrd of the Hallowed",
            "Meet Ivan and Veliaf east then north of Burgh de Rott (south of the Barrows fence); enter the entry and fight the Wyrd to ~10% HP.")
            .detail("Wyrd (lvl 564, 1,100 HP) needs vampyrebane: Hallowed flail (melee) or Blisterwood stakes (ranged). "
                + "Cycle: 2 melee -> Screech -> 2 melee -> Screech -> 2 Slams. Pray Melee (each hit knocks you back 1 tile - "
                + "no tile behind = recoil damage). Screech: prayers OFF or take 15-20 + delayed attack (earmuffs/slayer helm halve it). "
                + "Slam: right arm -> move to HIS LEFT; left arm -> HIS RIGHT. Below 60%: Bloody Screech - run away, prayers off, "
                + "avoid blood pools.")
            .items("Hallowed flail or Blisterwood stakes, Stamina potion, Saradomin brews, Super restores, earmuffs/slayer helm")
            .tracked("Hallowed flail", "Blisterwood stake", "Stamina potion", "Saradomin brew", "Super restore")
            .highlight("Ivan Strom", "Veliaf Hurtz", "Wyrd", "Entry")
            .target(3522, 3230, 0),

        // Phase 11
        step("11. Night of the Blood Moon",
            "Follow the bloody trail and fight Lowerniel at the Castle Drakan gates to ~30% (900 HP).")
            .detail("Efaritay, Ivan, Sugadinti and the Nylocas Queen assist. NEW: he fires an orb after every special attack. "
                + "Died/left? Walk back through the castle gates.")
            .items("Melee gear, supplies from the chest")
            .highlight("Lowerniel Drakan", "Bloody trail"),

        // Phase 12
        step("12. The Blood Moon Falls",
            "Talk to Ivan in Drakan's Castle, then Sugadinti, then Efaritay at the Icyene Graveyard. Enter the portal and gear from the supply chest.")
            .detail("Loadout: Hallowed flail (mandatory - ranged not viable), 1 Jar of congealed blood, 2 Rank frothy, "
                + "3-4 Foul chunky, rest Rancid slimy. Pre-pot trick: drop full potions, sip inventory ones, swap. "
                + "Efaritay's aid boosts DPS. No blood fury healing, Vengeance, serp venom or thralls.")
            .items("Hallowed flail, Efaritay's aid, 1 Jar of congealed blood, 2 Rank frothy, 3-4 Foul chunky, rest Rancid slimy")
            .tracked("Hallowed flail", "Efaritay's aid", "Jar of congealed blood", "Rank frothy", "Foul chunky", "Rancid slimy")
            .highlight("Ivan Strom", "Sugadinti Vitur", "Efaritay Hallow", "Portal", "Supply chest")
            .target(3684, 3172, 0),
        step("12. The Blood Moon Falls",
            "PHASE 1 (3000-2000 HP): Protect from Melee + Piety. Dodge spear combos.")
            .detail("Blood on HIS LEFT -> dodge right; HIS RIGHT -> dodge left; BOTH sides -> step next to him. "
                + "After a slam, attacks alternate (left, slam, right, left...). Semicircle AoE has a longer charge - "
                + "click WELL beyond his model. Keep 3-4 tiles of space behind you; reposition 90 degrees if cramped.")
            .highlight("Lowerniel Drakan"),
        step("12. The Blood Moon Falls",
            "PHASE 2 (2000-1000 HP): blood wave gauntlet, then blood strikes.")
            .detail("Run STRAIGHT INTO waves to pass through (true tile helps; no diagonals, no spam clicking). "
                + "Pattern: R, L, Down, R, L, R, Down, Pincer, repeat, then R, Down, L. Start far west. "
                + "After waves: PROTECT FROM MAGIC for the 4 blood strikes (80+ damage!), then straight back to Melee "
                + "for his combo. Single red projectiles after = flick Magic until the 0 hitsplat.")
            .highlight("Lowerniel Drakan"),
        step("12. The Blood Moon Falls",
            "PHASE 3 (1000-0 HP): lunges, lightning, blood pools - then victory!")
            .detail("He appears 3 times rotating 90 degrees around you; his FIRST appearance direction is where you click to dodge "
                + "the 3x5 lunge (move 2+ tiles perpendicular; clicking past blood pools is fine). 4 lunges, then 4 blood "
                + "strikes (Protect Magic!), then a combo - x3 total. Lightning and pools disable/punish prayers. "
                + "Save the Jar of congealed blood for the final stretch. Pray Melee before every normal attack.")
            .highlight("Lowerniel Drakan"),

        step("Complete!",
            "Quest complete! 4 QP, access to Vampyrium, Tome of experience (30k XP x6 to skills 70+), "
            + "Hallowed flail, Sunspear, fermenting vat, and vyre tolerance.")
    );
}
