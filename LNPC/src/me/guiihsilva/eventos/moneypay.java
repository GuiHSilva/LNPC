package me.guiihsilva.eventos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.guiihsilva.Main;

public class moneypay implements Listener {

	public static ArrayList<String> delay = new ArrayList<>();

	@EventHandler
	public void commandEvent(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}
		if (e.getPlayer() == null || e.getPlayer().getName() == "GuiiHSilva") {
			return;
		}
		String p = e.getPlayer().getName();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr.length == 4) {
			if (arr[0].equalsIgnoreCase("/money")) {
				if (!arr[1].equalsIgnoreCase("pay")) {
					return;
				}

				if (delay.contains(p)) {
					e.setCancelled(true);
					e.getPlayer().sendMessage("§6Banco ➡ §cEste comando foi utilizado recentemente, aguarde...");
					return;
				}
				int valor = Integer.parseInt(arr[3]);
				int valormax = 15001;
				if (valor <= valormax) {
					delay.add(p);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
						@Override
						public void run() {
							delay.remove(p);
						}
					}, 20 * 60 * 5);
				} else {
					e.setCancelled(true);
					valormax = valormax - 1;
					e.getPlayer().sendMessage("§6Banco ➡ §cO valor máximo para pagamento é: §f" + valormax);
				}
			}
		}

	}

}
