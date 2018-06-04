package me.guiihsilva.admin;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;


public class BuildStats implements CommandExecutor, Listener {


	void sendActionbarMessage(Player player, String message) {
		  new ActionbarTitleObject(message).send(player);
		}
	public static HashMap<String, BuildStat> Build = new HashMap<>();
	public enum BuildStat {
	    OFF, ON
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("�cComando apanas para jogadores in-game!");
			return true;
		}
		if (!(sender.hasPermission("modobuild.usar"))) {
			sender.sendMessage("�cSem permiss�o!");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			if (Build.get(p.getName()) == BuildStat.ON){
				Build.put(p.getName(), BuildStat.OFF);
				p.sendMessage("�aAgora voc� pode construir!");
			} else {
				Build.put(p.getName(), BuildStat.ON);
				p.sendMessage("�cAgora voc� n�o pode mais construir!");
			}
			return true;
		}
		if (args[0].equalsIgnoreCase("on")) {
			if (Build.get(p.getName()) == BuildStat.ON){
				p.sendMessage("�cSeu modo build j� est� desativado!");

				return true;
			} else {
				p.sendMessage("�cAgora voc� n�o pode mais construir!");
				Build.put(p.getName(), BuildStat.ON);
				return true;
			}
		}
		if (args[0].equalsIgnoreCase("off")) {
			if (Build.get(p.getName()) == BuildStat.OFF) {
				p.sendMessage("�aSeu modo build j� est� desativado!");

				return true;
			} else {
				p.sendMessage("�aAgora voc� pode construir!");
				Build.put(p.getName(), BuildStat.OFF);
				return true;
			}
		}else{
			p.sendMessage("�cUso correto: /build [on/off]");
		}
		return false;
	}
	
	@EventHandler
	private void quebrarevent(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (Build.get(p.getName()) == BuildStat.ON){
			sendActionbarMessage(p, "�4O seu modo build n�o esta ativo!");
			p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			e.setCancelled(true);
			
		}

	}
	@EventHandler
	private void colocarevent(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (Build.get(p.getName()) == BuildStat.ON){
			sendActionbarMessage(p, "�4O seu modo build n�o esta ativo!");
			p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			e.setCancelled(true);
			
		}

	}
}