package me.guiihsilva.eventos;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class wecalc implements Listener{
	
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
		if (arr[0].equalsIgnoreCase("//calc") || arr[0].equalsIgnoreCase("//calculate") || arr[0].equalsIgnoreCase("//eval") || arr[0].equalsIgnoreCase("//evaluate") || arr[0].equalsIgnoreCase("//solve") || arr[0].equalsIgnoreCase("/worldedit:/calc") || arr[0].equalsIgnoreCase("/worldedit:/calculate") || arr[0].equalsIgnoreCase("/worldedit:/eval") || arr[0].equalsIgnoreCase("/worldedit:/evaluate") || arr[0].equalsIgnoreCase("/worldedit:/solve")){
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cNão não não! HAHAHAH");
			for (Player p : Bukkit.getOnlinePlayers()){
				if (!p.hasPermission("legend.soustaff")){
					return;
				}
				p.sendMessage("§4§l* §f" + e.getPlayer().getName() + " §ctentou derrubar o server com //calc!");
				return;
			}
		}
	}
}
