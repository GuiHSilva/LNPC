package me.guiihsilva.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import me.guiihsilva.utils.Title;

public class sortear implements CommandExecutor {

	private ArrayList<Player> participantes = new ArrayList<>();
	private static int task;
	private Player lastwin;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sortear")) {
			if (!sender.hasPermission("legend.sortear")) {
				sender.sendMessage("§cSem permissão!");
				return true;
			} else {
				int i = 0;
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (!p.hasPermission("legend.soustaff")) {
						i++;
						participantes.add(p);
					}
				}
				if (participantes.isEmpty() || i <= 1) {

					Bukkit.broadcastMessage("  ");
					Bukkit.broadcastMessage("  ");
					Bukkit.broadcastMessage("  §e§lImpossível sortear: Jogadores insuficientes!");
					Bukkit.broadcastMessage("  ");
					Bukkit.broadcastMessage("  ");
					Bukkit.broadcastMessage("  ");
					return true;
				}
				Bukkit.broadcastMessage("  ");
				Bukkit.broadcastMessage("  ");
				Bukkit.broadcastMessage("  §e§lSorteando §6§l1 §e§ljogador de §f§l" + i + " §e§lonlines");
				Bukkit.broadcastMessage("  ");
				Bukkit.broadcastMessage("  ");
				Bukkit.broadcastMessage("  ");
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 10, 2);
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					@Override
					public void run() {
						Bukkit.broadcastMessage("  ");
						Bukkit.broadcastMessage("  ");
						Bukkit.broadcastMessage("  §e§lIniciando o sorteio!");
						Bukkit.broadcastMessage("  ");
						Bukkit.broadcastMessage("  ");
						Bukkit.broadcastMessage("  ");
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 10, 2);
						}
						task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
							int l = 95;

							@Override
							public void run() {
								l--;
								Random r = new Random();
								int ganhador = r.nextInt(participantes.size());
								Player vencedor = (Player) participantes.get(ganhador);
								if (vencedor == lastwin){
									vencedor = (Player) participantes.get(ganhador);
									if (vencedor == lastwin){
										vencedor = (Player) participantes.get(ganhador);
									}
								}
								lastwin = vencedor;
								Title t = new Title(" ", "§e" + vencedor.getName(), 0, 1, 2);
								for (Player pl : Bukkit.getOnlinePlayers()) {
									t.send(pl);
									pl.playSound(pl.getLocation(), Sound.CHICKEN_WALK, 10, 5);
								}
								if (l <= 0) {
									Title t2 = new Title("§6Jogador sorteado:", "§e" + vencedor.getName(), 0, 5, 2);
									for (Player pl : Bukkit.getOnlinePlayers()) {
										t2.send(pl);
										pl.playSound(pl.getLocation(), Sound.EXPLODE, 10, 2);
									}
									participantes.clear();
									Bukkit.getScheduler().cancelTask(task);
								}
							}
						}, 0, 5);
					}
				}, 20 * 4);

			}
		}
		return false;
	}
}
