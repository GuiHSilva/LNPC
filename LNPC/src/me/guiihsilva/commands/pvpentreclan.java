package me.guiihsilva.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;

public class pvpentreclan implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)){
			sender.sendMessage("�cComando para jogadores in-gamer");
			return true;
		}else{
			Player p = (Player) sender;
			if (args.length == 0){
				p.sendMessage("�2*�a O que � o pvp entre clan? Por padr�o, voc� e os membros do seu clan, n�o pode se bater, mas � poss�vel mudar isso, com essa fun��o!");
				p.sendMessage("�2*�f /pvpentreclan permitir �7- �aVoc� e seus membros v�o poder se bater!");
				p.sendMessage("�2*�f /pvpentreclan bloquear �7- �aVoc� e seus membros n�o v�o poder se bater!");
				return true;
			}
			if (args[0].equalsIgnoreCase("permitir")){
				if (Main.core != null) {
					ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
					if (cp != null) {
						Main.core.getClanManager().getClanPlayer(p).setFriendlyFire(true);
						sender.sendMessage("�bPvP entra clan definido como: �apermitido");
					}else{
						sender.sendMessage("�cClan n�o encontrado!");
						return true;
					}
				}
			}else if (args[0].equalsIgnoreCase("bloquear")){
				if (Main.core != null) {
					ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
					if (cp != null) {
						Main.core.getClanManager().getClanPlayer(p).setFriendlyFire(false);
						sender.sendMessage("�bPvP entra clan definido como: �cbloqueado");
					}else{
						sender.sendMessage("�cClan n�o encontrado!");
						return true;
					}
				}
			}else{
				p.sendMessage("�2*�a O que � o pvp entre clan? Por padr�o, voc� e os membros do seu clan, n�o pode se bater, mas � poss�vel mudar isso, com essa fun��o!");
				p.sendMessage("�2*�f /pvpentreclan permitir �7- �aVoc� e seus membros v�o poder se bater!");
				p.sendMessage("�2*�f /pvpentreclan bloquear �7- �aVoc� e seus membros n�o v�o poder se bater!");
				return true;
			}
		}
		return false;
	}

}
