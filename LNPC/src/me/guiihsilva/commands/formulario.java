package me.guiihsilva.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import me.guiihsilva.utils.JSONMessage;

public class formulario implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("formulario")){
			if (args.length > 0){
				if (sender.hasPermission("form.set")){
					String url = args[0];
					FileConfiguration filec = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder() + File.separator + "config.yml"));
					sender.sendMessage("�aFormul�rio definido para: �f" + args[0]);
					if (Main.instance.getConfig().getString("form") == null){
						filec.addDefault("form", url);
						filec.options().copyDefaults(true);
						try {
							filec.save(new File(Main.instance.getDataFolder() + File.separator + "config.yml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						return true;
					}else{
						filec.set("form", url);
						filec.options().copyDefaults(true);
						try {
							filec.save(new File(Main.instance.getDataFolder() + File.separator + "config.yml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						return true;
					}
				}
			}
			FileConfiguration filec = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder() + File.separator + "config.yml"));
			sender.sendMessage(" ");
			if (!(sender instanceof Player)){
				sender.sendMessage("�eVoc� pode fazer o nosso formul�rio: �f�o" + filec.getString("form"));
			}else{
				JSONMessage json = JSONMessage.create("�eVoc� pode fazer o nosso formul�rio: �f�l�o").then("CLIQUE AQUI!")
						.tooltip("�aClique para acessar: �f" + filec.getString("form").replaceAll("/", "").replaceAll("https:", "").replaceAll("http:", ""))
						.openURL(filec.getString("form"));
				json.send((Player) sender);
				Player p = (Player) sender;
				p.playSound(p.getLocation(), Sound.LAVA_POP, 10, 1);
			}
			sender.sendMessage("�cAten��o: �7N�o envie mais de um formul�rio, n�o fique pedindo � staff para olhar o seu formul�rio, ou voc� sera desconsiderado! Caso voc� seja interessante a nossa equipe, entraremos em contato com voc�, portanto n�o fique pedindo para ver formul�rio!!");
			sender.sendMessage(" ");
		}
		return false;
	}
	
}
