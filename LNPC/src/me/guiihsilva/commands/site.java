package me.guiihsilva.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import me.guiihsilva.utils.JSONMessage;

public class site implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("site")){
			if (!(sender instanceof Player)){
				sender.sendMessage("§cComando apenas para in-game");
				return true;
			}
			if (args.length == 0){
				File file = new File(Main.instance.getDataFolder() + File.separator + "config.yml");
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);
				sender.sendMessage("§6Estes são todos os nossos links:");
				if (config.getString("links.site") != null){
					JSONMessage jsonmsg = JSONMessage.create(" §eSite: §7")
							.then(config.getString("links.site"))
							.tooltip("Clique aqui para poder copiar: " + config.getString("links.site"))
							.suggestCommand(config.getString("links.site"));
					jsonmsg.send((Player) sender);
				}
				if (config.getString("links.ts3") != null){
					JSONMessage jsonmsg = JSONMessage.create(" §eTeamSpeak3: §7")
							.then(config.getString("links.ts3"))
							.tooltip("Clique aqui para poder copiar: " + config.getString("links.ts3"))
							.suggestCommand(config.getString("links.ts3"));
					jsonmsg.send((Player) sender);
				}
				if (config.getString("links.facebook") != null){
					JSONMessage jsonmsg = JSONMessage.create(" §eFacebook: §7")
							.then(config.getString("links.facebook"))
							.tooltip("Clique aqui para poder copiar: " + config.getString("links.facebook"))
							.suggestCommand(config.getString("links.facebook"));
					jsonmsg.send((Player) sender);
				}
				if (config.getString("links.twitter") != null){
					JSONMessage jsonmsg = JSONMessage.create(" §eTwitter: §7")
							.then(config.getString("links.twitter"))
							.tooltip("Clique aqui para poder copiar: " + config.getString("links.twitter"))
							.suggestCommand(config.getString("links.twitter"));
					jsonmsg.send((Player) sender);
				}
				if (config.getString("links.yt") != null){
					JSONMessage jsonmsg = JSONMessage.create(" §eYouTube: §7")
							.then(config.getString("links.yt"))
							.tooltip("Clique aqui para poder copiar: " + config.getString("links.yt"))
							.suggestCommand(config.getString("links.yt"));
					jsonmsg.send((Player) sender);
				}
				if (config.getString("links.discord") != null){
					JSONMessage jsonmsg = JSONMessage.create(" §eDiscord: §7")
							.then(config.getString("links.discord"))
							.tooltip("Clique aqui para poder copiar: " + config.getString("links.discord"))
							.suggestCommand(config.getString("links.discord"));
					jsonmsg.send((Player) sender);
				}
				if (config.getString("links.forum") != null){
					JSONMessage jsonmsg = JSONMessage.create(" §eFórum: §7")
							.then(config.getString("links.forum"))
							.tooltip("Clique aqui para poder copiar: " + config.getString("links.forum"))
							.suggestCommand(config.getString("links.forum"));
					jsonmsg.send((Player) sender);
				}
				return true;
//				sender.sendMessage("§cUso correto: /site [site/ts3/facebook/twitter/yt/ip/discord/forum]");
			}
			if (args.length > 1){
				if (!sender.hasPermission("site.setar")){
					sender.sendMessage("§cSem permissão!");
					return true;
				}else if (args.length == 2){
					File file = new File(Main.instance.getDataFolder() + File.separator + "config.yml");
					FileConfiguration config = YamlConfiguration.loadConfiguration(file);
					if (args[0].equalsIgnoreCase("site")){
						if (config.getString("links." + args[0].toLowerCase()) == null){
							config.addDefault("links." + args[0].toLowerCase(), args[1]);
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}else{
							config.set("links." + args[0].toLowerCase(), args[1].toLowerCase());
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}
					} else if (args[0].equalsIgnoreCase("ts3")){
							config.addDefault("links." + args[0].toLowerCase(), args[1]);
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
					} else if (args[0].equalsIgnoreCase("facebook")){
						if (config.getString("links." + args[0].toLowerCase()) == null){
							config.addDefault("links." + args[0].toLowerCase(), args[1]);
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}else{
							config.set("links." + args[0].toLowerCase(), args[1].toLowerCase());
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}
					} else if (args[0].equalsIgnoreCase("twitter")){
						if (config.getString("links." + args[0].toLowerCase()) == null){
							config.addDefault("links." + args[0].toLowerCase(), args[1]);
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}else{
							config.set("links." + args[0].toLowerCase(), args[1].toLowerCase());
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}
					} else if (args[0].equalsIgnoreCase("yt")){
						if (config.getString("links." + args[0].toLowerCase()) == null){
							config.addDefault("links." + args[0].toLowerCase(), args[1]);
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}else{
							config.set("links." + args[0].toLowerCase(), args[1].toLowerCase());
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							return true;
						}
					} else if (args[0].equalsIgnoreCase("ip")){
						if (config.getString("links." + args[0].toLowerCase()) == null){
							config.addDefault("links." + args[0].toLowerCase(), args[1]);
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}else{
							config.set("links." + args[0].toLowerCase(), args[1].toLowerCase());
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}
					} else if (args[0].equalsIgnoreCase("discord")){
						if (config.getString("links." + args[0].toLowerCase()) == null){
							config.addDefault("links." + args[0].toLowerCase(), args[1]);
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}else{
							config.set("links." + args[0].toLowerCase(), args[1].toLowerCase());
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}
					} else if (args[0].equalsIgnoreCase("forum")){
						if (config.getString("links." + args[0].toLowerCase()) == null){
							config.addDefault("links." + args[0].toLowerCase(), args[1]);
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}else{
							config.set("links." + args[0].toLowerCase(), args[1].toLowerCase());
							sendMessageConclued((Player) sender, args[1].toLowerCase(), args[0].toLowerCase());
							config.options().copyDefaults(true);
							try {
								config.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							return true;
						}
					}else{
						sender.sendMessage("§cUso correto: /site [site/ts3/facebook/twitter/yt/ip/discord/forum]");
						return true;
					}
				}else{
					sender.sendMessage("§cUso correto: /site [site/ts3/facebook/twitter/yt/ip/discord/forum]");
					return true;
				}
			}else{
				sender.sendMessage("§cComando não encontrado!");
				return true;
			}
		}
		return false;
	}

	public static void sendMessageConclued (Player p, String msg, String link){
		p.sendMessage("§aVocê definiu " + link + " como: " + msg);
	}
}
