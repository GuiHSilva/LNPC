package me.guiihsilva.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.guiihsilva.Main;
import me.guiihsilva.utils.JSONMessage;
import me.guiihsilva.utils.TpaManager;

public class tpa implements CommandExecutor {

	public static HashMap<Player, Player> tparequest = new HashMap<>();
	private static ArrayList<Player> delay = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpa")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cO console não pode se teleportar ate um jogador!");
				return true;
			} else {
				if (args.length == 0) {
					sender.sendMessage("§cUso correto: /tpa [Jogador]");
					return true;
				} else if (args.length > 1) {
					sender.sendMessage("§cUso correto: /tpa [Jogador]");
					return true;
				} else {
					Player target = Bukkit.getPlayer(args[0]);
					if (checkPlayers(sender, target)) {
						return true;
					} else {
						if (delay.contains((Player) sender)) {
							sender.sendMessage("§6» §eAguarde 20 segundos para usar este comando.");
							return true;
						}
						if (TpaManager.tpastatus.get(target) == "off") {
							sender.sendMessage("§e»§6 Este jogador esta com o tpa desativado!");
							return true;
						} else {
							if (chain.participantes.contains(target.getName())){
								sender.sendMessage("§cErro: este jogador esta na arena chain!!");
								return true;
							}
							sender.sendMessage("§eVocê enviu um pedido de teleporte á §6" + target.getName());
							target.sendMessage(" ");
							target.sendMessage("§e»§f " + sender.getName() + " §6deseja teleportar-se até você.");
							target.sendMessage("§e»§6 Este pedido vai expirar em §f20 segundos§6!");
							JSONMessage msgjson = JSONMessage.create("§e»§6 Ações: ").then("§2§lACEITAR ")
									.tooltip(" \n §2§lACEITAR \n ").runCommand("/tpaceitar " + sender.getName())
									.then(" ").then("§4§lRECUSAR").tooltip(" \n §4§lRECUSAR \n ")
									.runCommand("/tpanegar " + sender.getName());
							msgjson.send(target);
							target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 10, 2);
							target.sendMessage(" ");
							delay.add((Player) sender);
							tparequest.put((Player) sender, target);
							BukkitScheduler sh = Bukkit.getScheduler();
							sh.scheduleSyncDelayedTask(Main.instance, new Runnable() {
								@Override
								public void run() {
									if (tparequest.get((Player) sender) == target) {
										tparequest.remove((Player) sender);
										sender.sendMessage(
												"§cO seu pedido de teleporte para " + target.getName() + " expirou!");
										target.sendMessage(
												"§cO pedido de teleporte de " + sender.getName() + " expirou!");
									}
									delay.remove((Player) sender);
								}
							}, 20 * 20);
						}
					}
				}
			}
		}
		return false;
	}

	public boolean checkPlayers(CommandSender sender, Player target) {
		if (target == null) {
			sender.sendMessage("§cJogador não encontrado! ");
			return true;
		}
		if (sender.getName().equalsIgnoreCase(target.getName())) {
			sender.sendMessage("§cNão pode ser você mesmo!");
			return true;
		}
		return false;
	}

}
