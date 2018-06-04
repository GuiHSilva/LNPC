package me.guiihsilva.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;

public class skype implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("skype")){
			if(!(sender instanceof Player)){
				sender.sendMessage("�cO console provavelmente n�o possui um skype '-' seu in�til");
				return true;
			}
			if(args.length == 0){
				Player p = (Player) sender;
				UUID pUUID = p.getUniqueId(); 
				if(Main.instance.getConfig().getString("users." + pUUID + ".skype") != null){
					p.sendMessage("�b* �7Seu Skype registrado, � este: �e" + Main.instance.getConfig().getString("users." + pUUID + ".skype"));
					p.sendMessage("�b*�7 Mudou de Skype? Use /skype alterar [novo skype]");
					return true;
				}
				sender.sendMessage("�b* �7Por que � importante voc� registrar o seu Skype no servidor? Bom isso � para caso seja necess�rio, a nossa equipe da staff possa entrar em contato com voc� mais facilmente");
				sender.sendMessage("�b* �7Uso correto: /skype [add/alterar] [seu skype]");
				return true;
			}if(args.length != 2){
				sender.sendMessage(" ");
				if (sender.hasPermission("skype.ver")){
					sender.sendMessage(" �cUso correto: /skype [add/ver/alterar] [seu skype]");		
					sender.sendMessage(" ");
					return true;
				}	
				sender.sendMessage(" �cUso correto: /skype [add/alterar] [seu skype]");			
				sender.sendMessage(" ");
				return true;
			}if(args[0].equalsIgnoreCase("add")){
				Player p = (Player) sender;
				UUID pUUID = p.getUniqueId(); 
			    if(Main.instance.getConfig().getString("users." + pUUID + ".skype") != null){
			    	p.sendMessage("�b*�7 Voc� j� possui o seguinte Skype registrado: �e" + Main.instance.getConfig().getString("users." + pUUID + ".skype"));
			    	p.sendMessage("�b* �7Registre um novo Skype usando /skype alterar [Skype]");
			    	return true;
			    }
				if(Main.instance.getConfig().getString("users." + pUUID + ".skype") == null){
					Main.instance.getConfig().addDefault("users." + pUUID + ".skype", args[1]);
				}
				Main.instance.getConfig().addDefault("users." + pUUID + ".nickname", p.getName());
			    p.sendMessage("�b*�7 Skype registrado com �xito: �e" + args[1]);
			    p.sendMessage("�b*�7 Mudou de Skype? Use /skype alterar [novo skype]");
			    Main.instance.getConfig().options().copyDefaults(true);
				Main.instance.saveConfig();
				return true;
			}if(args[0].equalsIgnoreCase("alterar")){
				Player p = (Player) sender;
				UUID pUUID = p.getUniqueId();
				if(Main.instance.getConfig().getString("users." + pUUID + ".skype") == null){
					p.sendMessage("�b* �7Voc� n�o tem nenhum Skype para alterar, use /skype add [Skype]");
					return true;
				}
				if(Main.instance.getConfig().getString("users." + pUUID) == null){
					p.sendMessage("�b* �7Voc� n�o tem nenhum Skype para alterar, use /skype add [Skype]");
					return true;
				}
			    p.sendMessage("�b* �7Seu Skype foi alterado com �xito para: �e" + args[1]);
			    p.sendMessage("�b* �7Caso deseje mudar de Skype, use /skype alterar [Novo Skype]");
				Main.instance.getConfig().set("users." + pUUID + ".nickname", p.getName());
				Main.instance.getConfig().set("users." + pUUID + ".skype", args[1]);
				Main.instance.getConfig().options().copyDefaults(true);
				Main.instance.saveConfig();
				return true;
			}if(args[0].equalsIgnoreCase("ver")){
				if(!sender.hasPermission("skype.ver")){
					sender.sendMessage("�b*�7 Lamentamos, mas voc� n�o tem acesso � isto!");
					return true;
				}
				@SuppressWarnings("deprecation")
				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
				UUID targetUUID = target.getUniqueId();
				if (Main.instance.getConfig().getString("users." + targetUUID + ".skype") == null){
					sender.sendMessage("�b* �7O jogador �e" + args[1] + " �7n�o possui um Skype cadastrado!");
					return true;
				}
				String skype = Main.instance.getConfig().getString("users." + targetUUID + ".skype");
				sender.sendMessage("�b* �7Skype: �e" + skype);
				sender.sendMessage("�b* �7Nickname: �e" + Main.instance.getConfig().getString("users." + targetUUID + ".nickname"));
				sender.sendMessage("�b* �7UUID: �e" + targetUUID.toString());
			}else{
				Player p = (Player) sender;
				UUID pUUID = p.getUniqueId(); 
				if(Main.instance.getConfig().getString("users." + pUUID + ".skype") != null){
					p.sendMessage("�b* �7Seu Skype registrado, � este: �e" + Main.instance.getConfig().getString("users." + pUUID + ".skype"));
					p.sendMessage("�b*�7 Mudou de Skype? Use /skype alterar [novo skype]");
					return true;
				}
				sender.sendMessage("�b* �7Por que � importante voc� registrar o seu Skype no servidor? Bom isso � para caso seja necess�rio, a nossa equipe da staff possa entrar em contato com voc� mais facilmente");
				sender.sendMessage("�b* �7Uso correto: /skype [add/alterar] [seu skype]");
				return true;
			}
		}
		return false;
	}

	

	
	public boolean checkPlayers(CommandSender sender, Player target)
	  {
	    if (target == null)
	    {
	      sender.sendMessage("�cJogador n�o encontrado!");
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
