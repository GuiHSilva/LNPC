package me.guiihsilva.eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class comandocl implements Listener{

	@EventHandler
	public void commandEvent(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}
		if (e.getPlayer() == null) {
			return;
		}
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase("/cl")) {
			e.setCancelled(true);
			}
		}
	
}
