package me.guiihsilva.eventos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.guiihsilva.Main;

public class tranformar implements Listener{
 
	private ArrayList<Player> delay = new ArrayList<>();
	
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
		if(arr[0].equalsIgnoreCase("/transformar") || arr[0].equalsIgnoreCase("/ab") || arr[0].equalsIgnoreCase("/autoblock")){
			if (!e.getPlayer().hasPermission("AutoBlock.command")){
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cCompre o acesso ao §f/transformar §cno mercado negro! §8(/warp)");
				return;
			}

			if (delay.contains(e.getPlayer())){
				e.setCancelled(true);
				e.getPlayer().sendMessage("§cAguarde 1 minuto para usar este comando novamente...");
				return;
			}
			delay.add(e.getPlayer());
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				@Override
				public void run() {
					delay.remove(e.getPlayer());
				}
			}, 20*60);
		}
	}
	
	
}
