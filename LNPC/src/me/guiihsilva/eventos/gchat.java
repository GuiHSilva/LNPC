package me.guiihsilva.eventos;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.guiihsilva.Main;

public class gchat implements Listener{

	@EventHandler
	private void onChat(ChatMessageEvent e) {
		if (e.getChannel().getName().equalsIgnoreCase("global")) {
			if (e.getSender().hasPermission("chat.espaco")) {
				Bukkit.broadcastMessage(" ");
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					@Override
					public void run() {
						Bukkit.broadcastMessage(" ");
					}
				}, 1);
			}
		}
	}
	
}
