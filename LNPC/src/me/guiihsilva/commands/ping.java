package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ping implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ping")){
			if (!(sender instanceof Player)){
				sender.sendMessage("§cO console não possui ping!");
				return true;
			}else{
				if (args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					int ping = ((CraftPlayer) target).getHandle().ping;
					if (ping <= 50){
						String classi = "§2(Ótimo)";
						sender.sendMessage("§7Ping de " + target.getName() + ": §b" + ping + "ms " + classi);
					}else if (ping <= 150){
						String classi = "§a(Bom)";
						sender.sendMessage("§7Ping de " + target.getName() + ": §b" + ping + "ms " + classi);
					}else if (ping <= 270){
						String classi = "§e(Médio)";
						sender.sendMessage("§7Ping de " + target.getName() + ": §b" + ping + "ms " + classi);
					}else if (ping <= 360){
						String classi = "§c(Ruim)";
						sender.sendMessage("§7Ping de " + target.getName() + ": §b" + ping + "ms " + classi);
					}else{
						String classi = "§4(Péssimo, tente reiniciar sua Internet!)";
						sender.sendMessage("§7Ping de " + target.getName() + ": §b" + ping + "ms " + classi);
					}
					return true;
				}
				Player p = (Player) sender;
				int ping = ((CraftPlayer) p).getHandle().ping;
				if (ping <= 50){
					String classi = "§2(Ótimo)";
					p.sendMessage("§7Seu ping: §b" + ping + "ms " + classi);
				}else if (ping <= 150){
					String classi = "§a(Bom)";
					p.sendMessage("§7Seu ping: §b" + ping + "ms " + classi);
				}else if (ping <= 270){
					String classi = "§e(Médio)";
					p.sendMessage("§7Seu ping: §b" + ping + "ms " + classi);
				}else if (ping <= 360){
					String classi = "§c(Ruim)";
					p.sendMessage("§7Seu ping: §b" + ping + "ms " + classi);
				}else{
					String classi = "§4(Péssimo, tente reiniciar sua Internet!)";
					p.sendMessage("§7Seu ping: §b" + ping + "ms " + classi);
				}
			}
		}
		return false;
	}

}
