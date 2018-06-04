package me.guiihsilva.eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class clansuport implements Listener{

	@EventHandler
	public void clanSuport(PlayerCommandPreprocessEvent e){
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if(arr[0].equalsIgnoreCase("/clan")){
			if (arr.length == 1){
				e.setCancelled(true);
				e.getPlayer().chat("/clan help");
				e.getPlayer().sendMessage(" ");
				e.getPlayer().sendMessage("§7Use /clan help <página> ou solicite a staff com /ajuda");
			}
		}
	}
	
}
