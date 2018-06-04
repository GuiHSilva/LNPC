package me.guiihsilva.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class reloadcmd implements Listener{
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
		if (arr[0].equalsIgnoreCase("/rl") || arr[0].equalsIgnoreCase("/reload")) {
			if (p.getName() == "GuiiHSilva"){
				return;
			}
			e.setCancelled(true);
			p.sendMessage("§cPor motivos de segurança, este comando só pode ser executado pelo console!");
		}
	}

}
