package me.guiihsilva.commands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class loja implements Listener{
	@EventHandler
	public void minapvpCommand(PlayerCommandPreprocessEvent e){
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		if (e.getMessage().equalsIgnoreCase("/loja")){
			e.setCancelled(true);
			e.getPlayer().sendMessage("�cERRO: O servidor possui v�rias lojas, como lojas de jogadores, loja principal e mercado p�blico");
			e.getPlayer().sendMessage("�c - Para acessar o mercado: �7/mercado");
			e.getPlayer().sendMessage("�c - Para acessar a loja de vips: �7/lojas");
			e.getPlayer().sendMessage("�c - Para acessar a loja principal: �7/warp");
		}
	}
}
