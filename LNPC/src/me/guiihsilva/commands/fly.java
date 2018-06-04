package me.guiihsilva.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.mewin.WGRegionEvents.events.RegionLeaveEvent;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.guiihsilva.Main;
import me.guiihsilva.utils.Title;

public class fly implements CommandExecutor, Listener {

	private static ArrayList<Player> delay = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (!(sender instanceof Player)){
					sender.sendMessage("§cUso para jogadores in-game");
					return true;
			}
			Player p = (Player) sender;
			if (args.length == 1){
				if (!p.hasPermission("fly.others")){
					sender.sendMessage("§cVocê não possui permissão para isto!");
					return true;
				}
				p = Bukkit.getPlayer(args[0]);
				if (checkPlayers(sender, p)){
					return true;
				}
			}
			if (p.hasPermission("legend.fly")) {
				if (p.hasPermission("fly.admin")){
					flyChangeMethod(p, false);
					return true;
				}
				if (p.getLocation().getWorld() == Bukkit.getWorld("plots")) {
					flyChangeMethod(p, false);
				} else {
					if (p.getLocation().getWorld() == Bukkit.getWorld("world")) {
						WorldGuardPlugin worldGuard = Main.getWorldGuard();
						RegionManager regionManager = worldGuard.getRegionManager(p.getLocation().getWorld());
						ApplicableRegionSet regions = regionManager.getApplicableRegions(p.getLocation());
						if (regions.size() == 0) {
							p.sendMessage("§cNão é possível alterar o fly neste local!");
							return true;
						}
						regions.toString().toLowerCase();
						String area = ((ProtectedRegion) regions.iterator().next()).getId();
						if (area.contains("loja")) {
							flyChangeMethod(p, true);
						} else {
							p.sendMessage("§cNão é possível alterar o fly neste local!");
							return true;
						}
					}
				}
			} else {
				
				p.sendMessage("§cO fly é um privilégio §6VIP§c faça sua doação e adquira o seu!");
				return true;
			}
			
		}
			return true;
	}

	private static void flyChangeMethod(Player p, boolean msg_de_risco) {
		if (p.getAllowFlight() == false) {
			Title t = new Title(" ", "§aFly ativado", 0, 1, 2);
			p.setAllowFlight(true);
			t.send(p);
			if (msg_de_risco == true) {
				p.sendMessage(
						" §cAtenção: §7Você esta ativando o modo de voo em uma área perigosa, se sair pra fora do mapa, seu fly será desativado, e não será de respostabilidade da staff se você morrer, perder itens, etc. Não abuso deste privilégio!");
				p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 2);
			}
		} else {
			Title t = new Title(" ", "§cFly destivado", 0, 1, 2);
			p.setAllowFlight(false);
			t.send(p);
		}
	}

	@EventHandler
	public static void aoSair(RegionLeaveEvent e) {
		if (e.getRegion().getId().equalsIgnoreCase("loja")) {
			if (e.getPlayer().getAllowFlight() == true) {
				if (e.getPlayer().hasPermission("legegend.soustaff")) {
					return;
				}
				// e.getPlayer().setAllowFlight(false);
				e.setCancelled(true);
				if (delay.contains(e.getPlayer())){
					return;
				}else{
					delay.add(e.getPlayer());
					e.getPlayer().sendMessage("§cNão pode sair desta região com o fly ativo, desative-o para poder sair!");
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
						@Override
						public void run() {
							if (delay.contains(e.getPlayer())){
								delay.remove(e.getPlayer());
							}
						}
					}, 20*2);
				}
			}
		}
	}
	
	@EventHandler
	public static void aoQuitar(PlayerQuitEvent e){
		if (e.getPlayer().getAllowFlight() == true){
			if (e.getPlayer().hasPermission("legend.soustaff")){
				return;
			}
			e.getPlayer().setAllowFlight(false);
		}
	}
	
	
	public boolean checkPlayers(CommandSender sender, Player target)
	  {
	    if (target == null)
	    {
	      sender.sendMessage("§cJogador não encontrado! ");
	      return true;
	    }
	    if (sender.getName().equalsIgnoreCase(target.getName()))
	    {
	      sender.sendMessage("§cSe for pra ser você mesmo, use apenas /fly");
	      return true;
	    }
	    return false;
	  }
}
