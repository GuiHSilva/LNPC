package me.guiihsilva.eventos;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.guiihsilva.Main;
import me.guiihsilva.utils.Msg2;

public class opcmd implements Listener {

	@EventHandler
	public void commandEvent(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase("/op")) {
			e.setCancelled(true);
			p.sendMessage("§cPor motivos de segurança, este comando só pode ser executado pelo console!");
		}
	}

	@EventHandler
	public void commandEvent2(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase("/fb")) {
			if (p.hasPermission("legendmania.administrador")) {
				return;
			} else {
				e.setCancelled(true);
				p.sendMessage("§4§lERRO: §cComando desconhecido!");
			}
		}
	}

	@EventHandler
	public void commandEvent3(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase("/hd") || arr[0].equalsIgnoreCase("/cc") || arr[0].equalsIgnoreCase("/crazycrate")
				|| arr[0].equalsIgnoreCase("/chestcommands") || arr[0].equalsIgnoreCase("/holographicdisplays")
				|| arr[0].equalsIgnoreCase("/npc") || arr[0].equalsIgnoreCase("/leaderheads")
				|| arr[0].equalsIgnoreCase("/authme") || arr[0].equalsIgnoreCase("/mrl")
				|| arr[0].equalsIgnoreCase("/gate") || arr[0].equalsIgnoreCase("/tm")
				|| arr[0].equalsIgnoreCase("/titlemanager") || arr[0].equalsIgnoreCase("/svs")
				|| arr[0].equalsIgnoreCase("/vipzero") || arr[0].equalsIgnoreCase("/ap")
				|| arr[0].equalsIgnoreCase("/autopickup") || arr[0].equalsIgnoreCase("/mb")
				|| arr[0].equalsIgnoreCase("/reaction")) {
			if (p.hasPermission("legendmania.administrador")) {
				return;
			} else {
				e.setCancelled(true);
				p.sendMessage("§4§lERRO: §cComando desconhecido!");
			}
		}
	}

	@EventHandler
	public void commandEvent4(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase("/bau")) {
			if (p.hasPermission("chestmaster.open")) {
				return;
			} else {
				e.setCancelled(true);
				p.sendMessage("§cÉ necessário rank WolfII ou superior para isto!");
			}
		}
	}
	@EventHandler
	public void commandEvent5(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}

		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");
		if (arr[0].equalsIgnoreCase("/p")) {
			p.sendMessage("§cEste comando é utilizado pelo ");
		}
	}
	
	
	@EventHandler (priority = EventPriority.HIGH)
	public void commandEvent6(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}
		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		String msg = e.getMessage();
		String arr[] = msg.split(" ");

		if (arr[0].equalsIgnoreCase("/pl") || arr[0].equalsIgnoreCase("/plugins") || arr[0].contains("/bukkit")
				|| arr[0].contains("/about")) {
			if (p.isOp()) {
				return;
			}
			e.setCancelled(true);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				@Override
				public void run() {

					Msg2.send(p, "§3§m-----------------------------------------");
					Msg2.send(p,
							"§eEste servidor foi desenvolvido com códigos próprios e com fontes abertas publicas por §o§eSpigotMC");
					Msg2.send(p, "");
					Msg2.send(p, "§eProgramador:§f GuiHSilva");
					Msg2.send(p, "§eContato: §fensan.gamer §b(SKYPE)");
					Msg2.send(p, "§3§m-----------------------------------------");
					p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10, 1);					
				}
			}, 20);
			return;
		}
	}
	


}
