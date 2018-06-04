package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.utils.fireworkutil;

public class firework implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fogos")){
			if(!(sender instanceof Player)){
				if (args.length < 1){
					sender.sendMessage("Voc� � o console, e tem que dizer um player!");
				}else if(args.length > 0){
				    Player target = Bukkit.getPlayer(args[0]);
					if(checkPlayers(sender, target)){
						return true;
					}else{
						Location location = target.getLocation();
						fireworkutil.spawnRandomFirework(location);
						//sender.sendMessage("Lancando fogos em " + target.getDisplayName());
					}
					
				}
				return true;
			}
			if(sender.hasPermission("fogos.usar")){
				if (args.length > 0){
					if(sender.hasPermission("fogos.other")){
					    Player target = Bukkit.getPlayer(args[0]);
						Location location = target.getLocation();
						fireworkutil.spawnRandomFirework(location);
					//	sender.sendMessage("�aLan�ando fogos em " + target.getDisplayName());
						return true;
					}else{
						sender.sendMessage("�cVoc� n�o pode lan�ar fogos para outros jogadores!");
						return true;
					}
					
				}
				Player p = (Player) sender;
				Location loc = p.getLocation();
				p.sendMessage("�aLan�ando!");
				fireworkutil.spawnRandomFirework(loc);
			}else{
				sender.sendMessage("�c� necess�rio ser de �6VIP�c pra cima para ter acesso � este comando!!");
			}
		}
		return false;
	}
	
	
	
	
	public boolean checkPlayers(CommandSender sender, Player target)
	  {
	    if (target == null)
	    {
	      sender.sendMessage("�cJogador n�o encontrado! ");
	      return true;
	    }
	    if (sender.getName().equalsIgnoreCase(target.getName()))
	    {
	      sender.sendMessage("�cN�o pode ser voc� mesmo!");
	      return true;
	    }
	    return false;
	  }

}
