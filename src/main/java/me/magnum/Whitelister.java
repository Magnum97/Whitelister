
/*
 * Full License Text
 * The MIT License (MIT)
 *
 * Copyright © 2020. Richard Simpson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package me.magnum;

import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Whitelister extends JavaPlugin implements Listener {

	@Getter
	Whitelister plugin;

	@Override
	public void onDisable () {
		super.onDisable();
	}

	@Override
	public void onEnable () {
		plugin = this;
		plugin.getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onJoin (PlayerJoinEvent join) {
		OfflinePlayer player = join.getPlayer();
		// Uncomment below to have player added any time they are not on the list
		if (! plugin.getServer().getWhitelistedPlayers().contains(join.getPlayer())){
			/* Uncomment below to only add to list on first join.
			If removed from list they will not be re-added */
//		if (! join.getPlayer().hasPlayedBefore()) {
			player.setWhitelisted(true);
			plugin.getServer().getConsoleSender().sendMessage("whitelist add "+join.getPlayer().getName());
			plugin.getLogger().info("Added "+join.getPlayer().getName()+" to Whitelist");
		}
	}
}
