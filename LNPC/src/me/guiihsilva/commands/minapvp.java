package me.guiihsilva.commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class minapvp implements Listener{
	
	@EventHandler
	public void minapvpCommand(PlayerCommandPreprocessEvent e){
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		if (e.getMessage().equalsIgnoreCase("/minapvp")){
			e.setCancelled(true);
			e.getPlayer().chat("/warps minapvp");
		}
	}

}
