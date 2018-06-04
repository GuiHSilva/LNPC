package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.utils.JSONMessage;

public class onlines implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("onlines")){
		int player_qnt = 0;
		int onlines = Bukkit.getOnlinePlayers().size();
			for (Player p : Bukkit.getOnlinePlayers()){
				player_qnt = player_qnt + 1;
				if (p.hasPermission("onlines.soustaff")){
					player_qnt = player_qnt - 1;
				}
			}
		int player_staff = 0;
			for (Player ps : Bukkit.getOnlinePlayers()){
				if (ps.hasPermission("onlines.soustaff")){
					player_staff = player_staff + 1;
				}
				
			}

			if (!(sender instanceof Player)){
				sender.sendMessage("§eJogadores online: §7" + player_qnt);
				sender.sendMessage("§eJogadores online contando a staff: §7" + onlines );
				sender.sendMessage("§eJogadores da staff online: §7" + player_staff);
				return true;
			}
			sender.sendMessage("§eJogadores online: §7" + player_qnt);
			if(sender.hasPermission("onlines.admin")){
				Player jogador = (Player) sender;
				JSONMessage msgjson1 = JSONMessage.create("§eJogadores online: " + onlines + " §8(Contando staff) ")
						.then("§f§o[Passe o mouse]")
						.tooltip("§cVocê consegue ver este valor\npois você é um membro da staff\nentão, favor não divulgue isto!");
				JSONMessage msgjson2 = JSONMessage.create("§eJogadores online: " + player_staff + " §8(Apenas staff) ")
						.then("§f§o[Passe o mouse]")
						.tooltip("§cVocê consegue ver este valor\npois você é um membro da staff\nentão, favor não divulgue isto!");
				msgjson1.send(jogador);
				msgjson2.send(jogador);
			}
		}
		return false;
	}

}
