package me.guiihsilva.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;

public class securitymode implements CommandExecutor {

	public static boolean manuinfo;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("manutencao.usar")) {
			sender.sendMessage("§cSem permissão");
			return true;
		}
		if (args.length == 0){
			sender.sendMessage("§cUso correto: /manu <ON/OFF>");
			return true;
			
		}
		File file = new File(Main.instance.getDataFolder() + File.separator + "config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		if (config.get("manutencao") == null){
			config.addDefault("manutencao", false);
		}
		manuinfo = config.getBoolean("manutencao");
		if (args[0].equalsIgnoreCase("on")) {
			if (manuinfo == true) {
				sender.sendMessage("§cModo manutenção já esta ativo!");
				return true;
			} else {
				sender.sendMessage("§7Modo manutenção: §2ATIVADO");
				if (config.get("manutencao") == null){
					config.addDefault("manutencao", true);
				}
				config.set("manutencao", true);
				config.options().copyDefaults(true);
				try {
					config.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				manuinfo = true;
				for (Player players : Bukkit.getOnlinePlayers()){
					if (!players.hasPermission("manutencao.bypass")){
						players.kickPlayer("§cManutenção!\nEstamos trabalhando para aprimorar o servidor, volte mais tarde!");
					}
				}
				return true;
			}
		} if (args[0].equalsIgnoreCase("off")) {
			if (manuinfo == false) {
				sender.sendMessage("§cModo manutenção não esta ativo!");
			} else {
				sender.sendMessage("§7Modo manutenção: §4DESATIVADO");
				if (config.get("manutencao") == null){
					config.addDefault("manutencao", true);
				}
				config.set("manutencao", false);
				config.options().copyDefaults(true);
				try {
					config.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				manuinfo = false;
			}
		}else{
			sender.sendMessage("§cUso correto: /manu <ON/OFF>");
		}
		return false;
	}
}
