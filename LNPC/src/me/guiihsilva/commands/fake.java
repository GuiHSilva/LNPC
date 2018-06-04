package me.guiihsilva.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;

public class fake implements CommandExecutor{
	
	private static ArrayList<Player> delay = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fake")){
			if (!(sender instanceof Player)){
				sender.sendMessage("§cApenas para jogadores!");
				return true;
			}else{
				if (!sender.hasPermission("fake.transformar")){
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				if (args.length != 1){
					sender.sendMessage("§cUso correto: /fake [Nick/Clear/Voltar/Limpar]");
					return true;
				}else{
					if (delay.contains((Player) sender)){
						sender.sendMessage("§cAguarde 10s para usar este comando novamente!");
						return true;
					}
					if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("voltar") || args[0].equalsIgnoreCase("limpar")){
						Player p = (Player) sender;
						if (!DisguiseAPI.isDisguised(p)){
							p.sendMessage("§cERRO: Você não esta como outro player");
							return true;
						}
						DisguiseAPI.undisguiseToAll(p);
						p.sendMessage("§bVocê voltou ao normal!");
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
					}else{
						String target = args[0];
						if (Bukkit.getPlayer(target) != null){
							sender.sendMessage("§cERRO: Você escolheu o nick de alguém que está online!");
							return true;
						}
						Player p = (Player) sender;
						PlayerDisguise pd = new PlayerDisguise("§7" + target);
						DisguiseAPI.disguiseEntity(p, pd);
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
						p.sendMessage("§bVocê agora esta como: §f" + target);
						delay.add(p);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
							@Override
							public void run() {
								delay.remove(p);
							}
						}, 20*10);
					}
				}
			}
		}
		return false;
	}

}
