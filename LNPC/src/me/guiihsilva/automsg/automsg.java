package me.guiihsilva.automsg;

import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;

public class automsg extends BukkitRunnable {

	private static File file = new File(Main.instance.getDataFolder() + File.separator + "mensagens.yml");
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);	
	public automsg(Main paramMain) {
	}

	public void run() {
		List<String> listaLocal = Main.instance.getConfig().getStringList("Mensagens");
		Random localRandom = new Random();
		String prefix = config.getString("Prefixo_automsg");
		localRandom.nextInt(listaLocal.size());
		String str = (String) listaLocal.get(new Random().nextInt(listaLocal.size()));
		for (Player jogadores : Bukkit.getOnlinePlayers()){
			jogadores.sendMessage(" ");
			jogadores.sendMessage(prefix.replaceAll("&", "§")+str.replaceAll("&", "§"));
			jogadores.sendMessage(" ");
			jogadores.playSound(jogadores.getLocation(), Sound.LEVEL_UP, 2, 8);
		}
	}
}