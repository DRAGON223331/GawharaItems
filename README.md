# GawharaItems

A custom-items plugin for **GawharaSMP**, built for **Paper 26.3** and **Java 25**.

## Features

- Custom mining pickaxe and lumberjack axe.
- Flame Blade and Frost Blade area abilities.
- Lightning Wand and Healing Wand abilities.
- Guardian armor pieces with a configurable damage-reduction bonus.
- An in-game showcase menu; clicking an item explains its ability.
- Admin commands for giving items to online players.
- Configurable ability cooldowns and balance settings.
- Custom item IDs stored with `PersistentDataContainer` (an anvil rename does not turn a normal item into a custom one).
- GitHub Actions workflow that compiles the plugin and uploads the JAR as an artifact.
- No client-side mods required.

This first release uses vanilla Minecraft item models. An optional resource pack can be added later for custom textures and 3D models.

## Commands

| Command | Description | Permission |
|---|---|---|
| `/gitems` or `/gitems menu` | Open the item showcase | `gawharaitems.use` |
| `/gitems list` | List all custom item IDs | `gawharaitems.admin` |
| `/gitems give <player> <item_id> [amount]` | Give a custom item to an online player | `gawharaitems.admin` |
| `/gitems reload` | Reload `config.yml` | `gawharaitems.admin` |

Aliases: `/gitem`, `/gawharaitems`.

## Item IDs

- `miner_pickaxe`
- `lumber_axe`
- `flame_blade`
- `frost_blade`
- `lightning_wand`
- `healing_wand`
- `guardian_helmet`
- `guardian_chestplate`
- `guardian_leggings`
- `guardian_boots`

## Permissions and LuckPerms

The plugin uses Bukkit permissions, so it works with LuckPerms without requiring LuckPerms as a hard plugin dependency.

Examples:

```text
/lp group default permission set gawharaitems.use true
/lp group admin permission set gawharaitems.admin true
```

The `gawharaitems.use` permission defaults to true. The admin permission defaults to OP.

## Build with GitHub Actions

1. Upload the project files to the root of your GitHub repository, keeping the folder structure intact.
2. Open the repository's **Actions** tab.
3. Select **Build GawharaItems** and open the newest run.
4. When the run succeeds, download the **GawharaItems-JAR** artifact.
5. Extract the downloaded artifact ZIP to get `GawharaItems-1.0.0.jar`.

The workflow runs on pushes to `main`, pull requests targeting `main`, and manual dispatches. GitHub Actions uses JDK 25 and Gradle 9.2.1.

## Install on GawharaSMP

1. Download the JAR artifact from GitHub Actions.
2. Put `GawharaItems-1.0.0.jar` in the server's `plugins` folder.
3. Restart the server (a full restart is recommended; do not use `/reload`).
4. Test with `/gitems` and, as an admin, `/gitems list`.

To give an item:

```text
/gitems give PlayerName flame_blade
/gitems give PlayerName guardian_chestplate
```

## Configuration

Edit `plugins/GawharaItems/config.yml` to change:

- Ability cooldowns.
- Ability radius, damage, duration, and healing amount.
- Guardian armor damage reduction per piece and its maximum.

Then run `/gitems reload`.

## Compatibility

- Server: Paper 26.3 (and compatible Paper forks).
- Build/runtime Java requirement: Java 25.
- Client: vanilla Java or Bedrock clients connecting through your existing Geyser setup; no client mod is needed.
