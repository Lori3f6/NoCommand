# NoCommand
A Spigot plugin to create blocklist/whitelist for commands.

## Usage
All functions can be configured in `plugins/NoCommand/config.yml`. This file will be generatored at the first start up.

### config
Here is an example for config.yml.
```
whitelistmode: false 
feedback: "&7NoCommand &f> &e{player}&f, you can't use command &c{command}&f."  
commands: 
  - version
  - plugins
```
The discription of each item will be list below.


```
whitelistmode: false
```
Whitelist mode. If the value is true, the whitelist mode will be enabled. The player without permission "nocommand.bypass" can only use the commands list below. If the value is false, the blacklist mode will be enabled. The player has no permission "nocommand.bypass" will NOT be allowed to use the commands list below.

```
feedback: "&7NoCommand &f> &e{player}&f, you can't use command &c{command}&f."
```
Feedback is the notice the player will see when they are preventing to use the command. Two placeholder is privaded here. {player} will be replaced by player's name, and {command} will be replaced by the command which player want to use. Placeholders are not required, you can set the notice without placeholders as you like.(placeholderapi is not supported now)
If you don't want the player to see this notice at all, just set it to `feedback: ""`

```
commands: 
  - version
  - plugins
```
Here is the command list(See the whitelistmode setting as a white list or blacklist). The commands here needs removing the beginning `/`. All commands here must be lowercase.

### commands

#### reload
Because using `/reload` conflicts with the default command in spigot, please use `/nocommand:reload` instead.
To use this command, you need to have permission "nocommand.reload".

### permissions
+ `nocommand.bypass` : Bypass the command restriction of the plugin.
+ `nocommand.reload` : Needed when use command `/nocommand:reload`.

## Issue Tracker
If you fall into any problems during the use, or have any suggestions. Fell free to post a new issue [here](https://github.com/Lori3f6/NoCommand/issues/new). English and Chinese are both avalable.