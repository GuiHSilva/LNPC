package me.guiihsilva.cabecas.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import me.guiihsilva.scoreboard.scoreboard;

public class Manager {
	
	@SuppressWarnings("unused")
	private static FileConfiguration getFile;
	private static int PlayersHeads;
	private static File file = new File(Main.instance.getDataFolder() + File.separator + "cabecas_data.yml");
	@SuppressWarnings("static-access")
	public static FileConfiguration config = new YamlConfiguration().loadConfiguration(file);

	public static int getPlayersHeads(Player p) {
		if (config.getString("players." + p.getUniqueId() + ".nickname") == null){
			config.addDefault("players." + p.getUniqueId() + ".nickname", p.getName());
			config.addDefault("players." + p.getUniqueId() + ".quantidade", 0);
			saveConfig(file);
		}else{
			PlayersHeads = config.getInt("players." + p.getUniqueId() + ".quantidade");
		}
		return PlayersHeads;
	}

	public static void setPlayersHeads(Player p, int qnt) {
		if (config.getString("players." + p.getUniqueId() + ".nickname") == null){
			config.addDefault("players." + p.getUniqueId() + ".nickname", p.getName());
			config.addDefault("players." + p.getUniqueId() + ".quantidade", qnt);
			saveConfig(file);
		}else{
			config.set("players." + p.getUniqueId() + ".quantidade", qnt);
			saveConfig(file);
		}
	}
	
	public static void addPlayersHeads(Player p, int qnt) {
		if (config.getString("players." + p.getUniqueId() + ".nickname") == null){
			config.addDefault("players." + p.getUniqueId() + ".nickname", p.getName());
			config.addDefault("players." + p.getUniqueId() + ".quantidade", 0 + qnt);
			saveConfig(file);
		}else{
			int anteriror = config.getInt("players." + p.getUniqueId() + ".quantidade");
			anteriror = anteriror + qnt;
			config.set("players." + p.getUniqueId() + ".quantidade", anteriror);
			saveConfig(file);
		}
	}
	
	public static void removePlayersHeads(Player p, int qnt, CommandSender send) {
		if (config.getString("players." + p.getUniqueId() + ".nickname") == null){
			config.addDefault("players." + p.getUniqueId() + ".nickname", p.getName());
			config.addDefault("players." + p.getUniqueId() + ".quantidade", 0 + qnt);
			saveConfig(file);
		}else{
			int anteriror = config.getInt("players." + p.getUniqueId() + ".quantidade");
			anteriror = anteriror - qnt;
			if (anteriror < 0){
				send.sendMessage("§cSe você retirar todo este valor, o mesmo ficará com valor negativo, é proíbido ficar com valor negativos!");
				return;
			}
			config.set("players." + p.getUniqueId() + ".quantidade", anteriror);
			send.sendMessage("§eRemovido "+qnt+" cabeças de:§7 " + p.getName());
			saveConfig(file);
		}

		scoreboard.setScore(p);
	}
	
	public static void removePlayersHeads2(Player p, int qnt) {
		if (config.getString("players." + p.getUniqueId() + ".nickname") == null){
			config.addDefault("players." + p.getUniqueId() + ".nickname", p.getName());
			config.addDefault("players." + p.getUniqueId() + ".quantidade", 0 + qnt);
			saveConfig(file);
		}else{
			int anteriror = config.getInt("players." + p.getUniqueId() + ".quantidade");
			anteriror = anteriror - qnt;
			config.set("players." + p.getUniqueId() + ".quantidade", anteriror);
			saveConfig(file);
		}
		scoreboard.setScore(p);
	}
	
	public static void saveConfig(File file){
		config.options().copyDefaults(true);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("§4* ERRO AO SALVAR ALGUMA CONFIGURAÇÃO DO ARQUIVO: cabecas_data.yml");
		}
	}
}




