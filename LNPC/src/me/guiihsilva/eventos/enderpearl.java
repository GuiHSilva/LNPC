package me.guiihsilva.eventos;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class enderpearl implements Listener{
	
	@EventHandler
	public void aoInteragir(PlayerInteractEvent e){
		if (e.getMaterial() == Material.ENDER_PEARL){
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cEnderPearl bloqueada!");
		}
		if (e.getMaterial() == Material.TNT){
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cTNT bloqueada!");
		}
	}

}
