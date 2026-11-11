# GawharaItems 1.1.7

Custom tools, elemental swords, and six custom armor sets for GawharaSMP. Targets Paper 26.3 and Java 25.

## Armor rendering fix (1.1.6)
- Restores the complete vanilla-compatible 64x32 UV atlases; no manual alpha masking of helmet/chest/boot UV faces.
- Java uses current equipment assets and separate humanoid / humanoid_leggings textures.
- Bedrock via Geyser uses independent Bedrock attachables, mappings, and layer 1 / layer 2 armor atlases.
- Keeps all 24 armor pieces across Shadow, Royal, Dragon, Demon, Ice, and Angel sets.

## Geyser installation
1. Stop the server. In `plugins/Geyser-Spigot/packs/`, remove older GawharaItems `.mcpack` / `.zip` files and leave only the new `GawharaItems-Geyser-Bedrock-1.1.7.mcpack`.
2. In `plugins/Geyser-Spigot/custom_mappings/`, remove older `gawharaitems_mappings-*.json` files and place only `gawharaitems_mappings-1.1.7.json`.
3. Set `enable-custom-content: true` in Geyser config.
4. Fully restart the server/Geyser.
5. Bedrock players should clear the old server pack cache if a previous version remains.

## Java resource pack
The plugin sends the Java pack after join. Upload the included `resourcepack-java-26.3.zip` to the direct HTTPS URL configured in `plugins/GawharaItems/config.yml`; the SHA-1 there matches this archive.

## Commands
- `/gitems` - open showcase menu
- `/gitems list` - list item IDs
- `/gitems give <player> <item_id> [amount]` - give an item
- `/gitems pack` - resend Java resource-pack prompt
- `/gitems reload` - reload config

## Build
Requires Java 25 and Gradle. GitHub Actions builds with JDK 25.
