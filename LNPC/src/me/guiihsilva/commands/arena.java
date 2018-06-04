package me.guiihsilva.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class arena implements Listener {

	@EventHandler
	public void commandEvent(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}
		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase("/arena")) {
			p.chat("/warps arena");
			e.setCancelled(true);
		}

	}

}
