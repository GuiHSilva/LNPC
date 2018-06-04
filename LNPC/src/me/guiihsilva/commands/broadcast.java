package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;

public class broadcast implements CommandExecutor {
	void sendActionbarMessage(Player player, String message) {
		new ActionbarTitleObject(message).send(player);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage("");
				sender.sendMessage("§cUso correto: /bc <Mensagem>");
				sender.sendMessage("");
				return true;
			
			} else {
				String msg = "";
				for (int i = 0; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage(" §6*§eSERVIDOR: §f" + msg.replaceAll("<3", "❤").replaceAll("&", "§"));
				Bukkit.broadcastMessage(" ");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.playSound(pl.getLocation(), Sound.ITEM_BREAK, 10, 1);
					sendActionbarMessage(pl, "§6SERVIDOR §efez uma transmissão!");
				return true;
				}
			}
		}

		if (cmd.getName().equalsIgnoreCase("bc")) {
			Player p = (Player) sender;
			if (!p.hasPermission("broadcast.use")) {
				p.sendMessage("§cSem permissão!");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage("");
				p.sendMessage("§cUso correto: /bc <Mensagem>");
				p.sendMessage("");
				p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 10, 1);
				return true;
			
			} else {
				String msg = "";
				for (int i = 0; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				sendActionbarMessage(p, "§6" + p.getPlayer().getName() + " §efez uma transmissão!");
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage(
						" §6*§e" + sender.getName() + ": §f" + msg.replaceAll("<3", "❤").replaceAll("&", "§"));
				Bukkit.broadcastMessage(" ");
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.playSound(pl.getLocation(), Sound.ITEM_BREAK, 10, 1);
					sendActionbarMessage(pl, "§6" + sender.getName() + " §efez uma transmissão!");
				}
				return true;

			}
		}
		return false;
	}

}
