package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.cabecas.manager.Manager;

public class cabecas implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cabecas")){
			if (args.length == 0){
				if (!(sender instanceof Player)){
					sender.sendMessage("§aLista de comandos: ");
					sender.sendMessage(" §7/cabecas ver [Jogador]§f - Ve quantas cabecas alguem tem!");
					sender.sendMessage(" §7/cabecas set [Jogador] [Quantidade]§f - Define as cebacas de alguem!");
					sender.sendMessage(" §7/cabecas add [Jogador] [Quantidade]§f - Adiciona cabecas para alguem!");
					sender.sendMessage(" §7/cabecas remove [Jogador] [Quantidade] §f - Remove cabecas de alguem!");
					return true;
				}
				sender.sendMessage("§eSuas cabeças: §7" + Manager.getPlayersHeads((Player) sender));
				return true;
			}
			if (args[0].equalsIgnoreCase("ver")){
				if (!sender.hasPermission("cabecas.admin")){
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				if (args.length != 2){
					sender.sendMessage("§cUso correto: /cabecas ver [Jogador]");
					return true;
				}else{
					Player target = Bukkit.getPlayer(args[1]);
					if (checkPlayers(sender, target)){
						return true;
					}
					sender.sendMessage("§eCabeças de " + target.getName() + ": §7" + Manager.getPlayersHeads(target));
				}
			}
			if (args[0].equalsIgnoreCase("set")){
				if (!sender.hasPermission("cabecas.admin")){
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				if (args.length != 3){
					sender.sendMessage("§cUso correto: /cabecas set [Jogador] [Quantidade]");
					return true;
				}else{
					if (!(isInteger(args[2]))){
						sender.sendMessage("§cO valor precisa ser apenas números!");
						return true;
					}
					Player target = Bukkit.getPlayer(args[1]);
					if (checkPlayers(sender, target)){
						return true;
					}
					if (Integer.parseInt(args[2]) < 0){
						sender.sendMessage("§cNão é permitido definir valores menores que 0!");
						return true;
					}
					Manager.setPlayersHeads(target, Integer.parseInt(args[2]));
					sender.sendMessage("§eCabeças de " + target.getName() + " aleterado para: §7" + args[2]);
				}
			}
			if (args[0].equalsIgnoreCase("add")){
				if (!sender.hasPermission("cabecas.admin")){
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				if (args.length != 3){
					sender.sendMessage("§cUso correto: /cabecas add [Jogador] [Quantidade]");
					return true;
				}else{
					if (!(isInteger(args[2]))){
						sender.sendMessage("§cO valor precisa ser apenas números!");
						return true;
					}
					Player target = Bukkit.getPlayer(args[1]);
					if (checkPlayers(sender, target)){
						return true;
					}
					if (Integer.parseInt(args[2]) < 0){
						sender.sendMessage("§cNão é permitido definir valores menores que 0!");
						return true;
					}
					Manager.setPlayersHeads(target, Integer.parseInt(args[2]));
					sender.sendMessage("§eAdicionado "+args[2]+" cabeças para:§7 " + target.getName());
				}
			}
			if (args[0].equalsIgnoreCase("remove")){
				if (!sender.hasPermission("cabecas.admin")){
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				if (args.length != 3){
					sender.sendMessage("§cUso correto: /cabecas remove [Jogador] [Quantidade]");
					return true;
				}else{
					if (!(isInteger(args[2]))){
						sender.sendMessage("§cO valor precisa ser apenas números!");
						return true;
					}
					Player target = Bukkit.getPlayer(args[1]);
					if (checkPlayers(sender, target)){
						return true;
					}
					if (Integer.parseInt(args[2]) < 0){
						sender.sendMessage("§cNão é permitido definir valores menores que 0!");
						return true;
					}
					Manager.removePlayersHeads(target, Integer.parseInt(args[2]), sender); 
				}
			}
		}
		return false;
	}
	
	public boolean checkPlayers(CommandSender sender, Player target)
	  {
	    if (target == null)
	    {
	      sender.sendMessage("§cJogador não encontrado! O mesmo precisa estar online para isto!");
	      return true;
	    }
	    if (sender.getName().equalsIgnoreCase(target.getName()))
	    {
	      sender.sendMessage("§cNão pode ser você mesmo!");
	      return true;
	    }
	    return false;
	  }

	

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
