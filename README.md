# GawharaItems 1.2.0 — Medieval War Armory

Custom survival tools, elemental weapons, and **nine medieval armor bundles** for GawharaSMP. The plugin targets Paper 26.3 / Java 25.

## What's new in 1.2.0

- Redrawn armor item icons: helmets, chestplates, leggings, and boots now read as actual Minecraft armor pieces rather than small abstract symbols.
- Nine armor sets: Shadow, Royal, Dragon, Demon, Ice, Angel, Warrior, Viking, and Gladiator.
- Three new full armor sets (Warrior, Viking, Gladiator), with medieval steel, leather, bronze, and arena-inspired details instead of futuristic/sci-fi styling.
- A dedicated signature weapon and matching mining pickaxe for every armor bundle, plus a War Hammer, Hunter Crossbow, and Battle Spear.
- New silhouettes for the Miner War Pickaxe and Lumber War Axe.
- Java item icons, worn armor, and Geyser/Bedrock textures and mappings are updated together.
- `/gitems bundle <player> <set>` gives the four armor pieces, signature weapon, and matching mining pickaxe.

## Java resource pack

The plugin requests `resourcepack-java-26.3.zip` using the SHA-1 in `src/main/resources/config.yml`. Upload the exact ZIP to the HTTPS URL configured there. If you modify or re-compress the ZIP, calculate its SHA-1 again and replace the config value.

## Geyser / Bedrock installation

1. Stop the server.
2. In `plugins/Geyser-Spigot/packs/`, remove old GawharaItems packs and place `GawharaItems-Geyser-Bedrock-1.2.0.mcpack`.
3. In `plugins/Geyser-Spigot/custom_mappings/`, replace the old mappings with `gawharaitems_mappings-1.2.0.json`.
4. Confirm `enable-custom-content: true` in the Geyser configuration.
5. Fully restart the server and Geyser. Bedrock players may need to clear the old server resource-pack cache and reconnect.

## Commands

- `/gitems` or `/gitems menu` — open the item catalogue.
- `/gitems list` — list every custom item ID.
- `/gitems give <player> <item_id> [amount]` — give one item by ID.
- `/gitems bundle <player> <set>` — give a complete armor bundle, signature weapon, and matching mining pickaxe. Sets: `shadow`, `royal`, `dragon`, `demon`, `ice`, `angel`, `warrior`, `viking`, `gladiator`.
- `/gitems pack` or `/gpack` — resend the Java resource-pack prompt.
- `/gitems reload` — reload plugin configuration.

## Build

Requires Gradle and JDK 25. The GitHub Actions workflow builds the plugin JAR with JDK 25. The local runtime used to prepare this source archive had JDK 21, so the plugin JAR was not compiled locally; build it through GitHub Actions or another JDK 25 environment.
