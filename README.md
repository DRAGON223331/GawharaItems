# GawharaItems 1.1.2 - GawharaSMP

Custom survival tools, eight elemental swords, six armor sets and redesigned item/worn textures for **Paper 26.3 / Java 25**.

## Visual redesign in 1.1.2

- Replaced the original minimal 32x32 item icons with detailed 64x64 pixel-art icons for all eight swords and 24 armor pieces.
- Rebuilt the six Java and Bedrock worn-armor texture atlases using the standard 64x32 humanoid UV layout (helmet, torso, arms, boots and leggings).
- Preserved the same item IDs, model IDs, equipment asset IDs, Geyser mappings, abilities and menu behavior.
- Armor on the player is rendered through Minecraft's standard equipment texture layers; this is a 2D armor-surface system, not an arbitrary 3D player-mesh replacement.

## Added swords

| ID | Name | Right-click ability |
|---|---|---|
| `flame_blade` | Flame Blade | Burns and damages nearby monsters |
| `frost_blade` | Frost Blade | Damages and slows nearby monsters |
| `lightning_blade` | Stormbreaker | Calls a lightning burst at the targeted block |
| `shadow_blade` | Umbral Edge | Blinds and weakens nearby monsters |
| `venom_blade` | Venom Fang | Poisons and damages nearby monsters |
| `vital_blade` | Lifebinder | Damages nearby monsters and heals the wielder |
| `dragon_blade` | Wyrmfang | Fire shockwave and knockback |
| `spirit_blade` | Soulreaper | Marks nearby monsters and grants absorption/speed |

## Added armor sets

- **Shadow**: Speed II, invisibility while sneaking, 10% damage reduction.
- **Royal**: Resistance, Regeneration, 12% damage reduction.
- **Dragon**: Fire Resistance, Strength, immunity to fire/lava damage, 10% damage reduction.
- **Demon**: Strength II, Speed, 8% damage reduction.
- **Ice**: Resistance, Speed, slows melee attackers, 10% damage reduction.
- **Angel**: Slow Falling, Jump Boost II, 10% damage reduction.

Set passives require all four pieces from the same set. Armor passives refresh every 2 seconds while worn. All sword abilities currently target monsters only; they do not damage other players.

## Existing content kept

Miner's Pickaxe, Lumberjack's Axe, Lightning Wand, Healing Wand, and the original Guardian armor remain available. The showcase menu now has pages so all items can be browsed.

## Commands

| Command | Description | Permission |
|---|---|---|
| `/gitems` or `/gitems menu` | Open the item catalogue | `gawharaitems.use` |
| `/gitems pack` | Send the Java resource-pack prompt again | `gawharaitems.use` |
| `/gitems list` | List all item IDs | `gawharaitems.admin` |
| `/gitems give <player> <item_id> [amount]` | Give an online player an item | `gawharaitems.admin` |
| `/gitems reload` | Reload config.yml | `gawharaitems.admin` |

Example:

```text
/gitems give Ent2am lightning_blade
/gitems give Ent2am shadow_helmet
/gitems give Ent2am shadow_chestplate
/gitems give Ent2am shadow_leggings
/gitems give Ent2am shadow_boots
```

## Hosting limitation: Aternos

Aternos does not allow uploading arbitrary custom Java plugin JAR files and does not provide full plugin-file/FTP access. Therefore, this custom `GawharaItems-1.1.2.jar` cannot be installed on an Aternos server through a normal upload. Use a host that allows custom Paper plugins (Aternos recommends exaroton for this use case), or have the plugin accepted into Aternos's supported plugin library. Aternos does allow Java server resource packs through its Resource Packs page, but that alone does not install the plugin.

## Install the plugin

1. Upload the contents of this repository to the GitHub repository root (the folder containing `build.gradle`).
2. Open **Actions** and wait for **Build GawharaItems** to succeed.
3. Download the `GawharaItems-JAR` artifact and extract `GawharaItems-1.1.2.jar`.
4. Put the JAR in the server's `plugins` folder and restart the server fully. Do not use `/reload`.

The project is compiled with Java 25 and Paper API 26.3. The Paper API is `compileOnly` and is not bundled into the plugin.

## Java 26.3 custom models and automatic delivery

The project contains both the editable folder `resourcepack-java-26.3/` and the ready-to-host ZIP `resourcepack-java-26.3.zip`. The ZIP root contains `pack.mcmeta` and `assets/`.

The plugin requests this Java pack 2 seconds after a player joins. Minecraft displays its normal resource-pack prompt; it does not silently install a first-time pack. To activate delivery:

1. Upload the exact `resourcepack-java-26.3.zip` from the project root to a public file host that serves a **direct HTTPS download URL**. A GitHub Release asset is suitable.
2. Paste the final direct download URL into `resource-pack.url` in `plugins/GawharaItems/config.yml`.
3. The included `resource-pack.sha1` value is precomputed for this exact ZIP. Keep it unchanged unless you edit/repack the ZIP. The matching checksum is also in `resourcepack-java-26.3.zip.sha1`.
4. Restart the server or run `/gitems reload`. Players joining afterward will receive the prompt. An online player can request it again with `/gitems pack`.

`resource-pack.required: false` allows a player to decline. Set it to `true` to require the pack; a player who declines a required pack is disconnected. The plugin checks the URL and SHA-1 before sending a request and logs failures to the console.

**The plugin cannot host a public file from inside its own JAR or the server's plugin folder.** The ZIP must be uploaded to an externally reachable HTTPS host first. Without a valid `resource-pack.url`, the plugin logs a warning and correctly sends no request. The pack is sent only to Java clients; Floodgate players are skipped because they receive the separate Bedrock pack from Geyser.

The pack uses Java 26.3 resource-pack format 97.1 (`min_format: [97,1]`, `max_format: 97`). It is not intended for older Java versions.

## Geyser / Floodgate / Bedrock

Geyser does **not** convert Java resource packs automatically. The repository therefore includes a separate `geyser-bedrock-pack` folder containing:

- `gawharaitems_mappings.json` for Geyser custom item definitions (mapping Java item models to Bedrock identifiers).
- A Bedrock resource pack with inventory icons and armor attachables.

Install both components:

1. Make sure `gameplay.enable-custom-content: true` in Geyser's `config.yml`.
2. Copy `gawharaitems_mappings.json` into Geyser's `custom_mappings` folder.
3. Zip the **contents** of `geyser-bedrock-pack` (including `manifest.json` at the ZIP root) as an `.mcpack`, then place it in Geyser's `packs` folder.
4. Restart the server/Geyser. Geyser distributes the contents of its `packs` folder to Bedrock clients when they connect; the Java pack URL setting does not distribute the Bedrock pack.

Paths vary by hosting panel and Geyser platform. For a plugin-based Geyser install, these are commonly under `plugins/Geyser-*/custom_mappings` and `plugins/Geyser-*/packs` (or the configured Geyser data directory). Aternos does not provide full plugin-file access, so it may not let you place the custom Geyser mapping in its required folder. Confirm that those Geyser custom-content folders are writable before expecting Bedrock custom textures to load.

The Paper plugin logic itself remains compatible with Java and Bedrock players through Geyser/Floodgate. Bedrock custom visuals require the separate Geyser mapping and Bedrock pack above. Geyser documentation states that it does not convert Java packs and requires a separate Bedrock resource pack and mappings: https://geysermc.org/wiki/geyser/custom-items/

## Configuration

`plugins/GawharaItems/config.yml` contains the Java resource-pack URL, SHA-1 and required/optional setting, as well as cooldowns and damage reduction values. Armor effects and sword tuning are conservative defaults in this release. The `reload` command reloads configuration only; it does not replace custom item assets already cached by clients.

## Notes

- Custom item identity is stored in the plugin's PersistentDataContainer key; renaming an item does not make it a custom item.
- Resource pack visuals are client-side. If a client declines or cannot load the pack, the items still function but use fallback vanilla visuals.
- Java pack visuals and Geyser Bedrock visuals are separate deliverables and must be installed separately. Java is sent by GawharaItems from its configured HTTPS URL; Bedrock is delivered by Geyser from its `packs` folder.
