package me.guiihsilva.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import net.milkbowl.vault.economy.EconomyResponse;

public class anuncio implements CommandExecutor {

	private static ArrayList<Player> delay = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)){
			sender.sendMessage("§cComando apenas para jogadores in-game!");
			return true;
		}else{
			if (sender.hasPermission("vip.anunciar")){
				Player p = (Player) sender;
				if (delay.contains(p)){
					p.sendMessage("§cVocê deve esperar 3 minutos");
					return true;
				}else{
					if (args.length == 0){
						p.sendMessage("§cUso correto: /anuncio [Sua mensagem!]");
						return true;
					}
					EconomyResponse r = Main.econ.withdrawPlayer(p, 3000);
					if (r.transactionSuccess()){
						delay.add(p);
						String msg = "";
						for (int i = 0; i < args.length; i++) {
							msg = msg + args[i] + " ";
						}
						for (Player players : Bukkit.getOnlinePlayers()){
							players.sendMessage(" ");
							players.sendMessage("§3§lAnúncio §r§3(§b" + p.getName() + "§3) ➡ §a" + msg.replaceAll("&", "§"));
							players.sendMessage(" ");
							players.playSound(players.getLocation(), Sound.AMBIENCE_THUNDER, 10, 2);
						}
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
							@Override
							public void run() {
								delay.remove(p);
							}
						}, 20*60*3);
					}else{
						sender.sendMessage("§cVocê não possui dinheiro suficiente! (3,000)");
						return true;
					}
				}
			}else{
				sender.sendMessage("§cEsta é uma função para jogadores VIP!");
				return true;
			}
		}
		return false;
			
	}
}
