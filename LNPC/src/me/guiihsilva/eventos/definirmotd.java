package me.guiihsilva.eventos;

import org.bukkit.event.Listener;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;
import me.guiihsilva.Main;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class definirmotd implements Listener {

	@EventHandler
	public void onServerPing(ServerListPingEvent e) { // Main.getInstance().getConfig().set("teleportes.spawn.x",
														// p.getLocation().getX());
		String secondsline = Main.instance.getConfig().getString("msgs.secondline").replaceAll("&", "§");
		File file = new File(Main.instance.getDataFolder()+File.separator + "config.yml" );
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		if (config.getBoolean("manutencao") == false) {
			for (Player p : Bukkit.getOnlinePlayers()){
				PermissionUser user = PermissionsEx.getUser(p);
				String prefix = user.getSuffix();
				if (prefix.contains("YouTuber")){
					e.setMotd("§6§lLegendMania §e➡ §7Venha se divertir §f1.8.*\n§fVenha! O YouTuber §c§l" + p.getName() + "§f esta online!");
					return;
				}
			}
			e.setMotd("§6§lLegendMania §e➡ §7Venha se divertir §f1.8.*\n" + ChatColor.YELLOW + secondsline);
		} else {
			e.setMotd("§6§lLegendMania §e➡ §7Venha se divertir §f1.8.*\n§cO servidor encontra-se em manutenção!");
		}
	}

}
