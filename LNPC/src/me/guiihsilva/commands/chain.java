package me.guiihsilva.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.guiihsilva.Main;
import me.guiihsilva.utils.LocationForConfig;
import me.guiihsilva.utils.clanff;
import me.guiihsilva.utils.inventory;

public class chain implements CommandExecutor, Listener {

	public static ArrayList<String> participantes = new ArrayList<>();
	private ArrayList<Player> delay = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chain")) {
			if (args.length == 0) {
				sender.sendMessage("§cChain ➡ §fComandos de uso:");
				sender.sendMessage(" §f* §7/chain entrar §8-§f Entra na arena");
				sender.sendMessage(" §f* §7/chain sair §8-§f Sai da arena");
				if (sender.hasPermission("chain.admin")) {
					sender.sendMessage(" §c* §4/chain abrir §8-§f Abre a arena");
					sender.sendMessage(" §c* §4/chain fechar §8-§f Fecha a arena");
					sender.sendMessage(" §c* §4/chain setentrada §8-§f Define a entrada");
					sender.sendMessage(" §c* §4/chain setsaida §8-§f Define a saida");
					sender.sendMessage(" §c* §4/chain kick §8-§f Kicka da arena");
				}
			} else if (args[0].equalsIgnoreCase("abrir")) {
				if (!sender.hasPermission("chain.admin")) {
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				File file = new File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
				FileConfiguration f = YamlConfiguration.loadConfiguration(file);
				if (f.getBoolean("aberta") == true) {
					sender.sendMessage("§cA arena já esta aberta!");
					return true;
				} else {
					f.set("aberta", true);
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(" §cChain ➡§7 A arena foi aberta");
					Bukkit.broadcastMessage(" ");
					f.options().copyDefaults(true);
					try {
						f.save(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 5);
					}
				}
			} else if (args[0].equalsIgnoreCase("fechar")) {
				if (!sender.hasPermission("chain.admin")) {
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				File file = new File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
				FileConfiguration f = YamlConfiguration.loadConfiguration(file);
				if (f.getBoolean("aberta") == false) {
					sender.sendMessage("§cA arena já esta fechada!");
					return true;
				} else {
					f.set("aberta", false);
					f.options().copyDefaults(true);
					try {
						f.save(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage(" §cChain ➡§7 A arena foi fechada");
					Bukkit.broadcastMessage(" ");
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 5);
					}
					for (String ps : participantes) {
						Player p = Bukkit.getPlayer(ps);
						p.sendMessage(" ");
						p.sendMessage(" §e* Você foi teleportado á saida");
						p.sendMessage(" §e* Motivo: A arena foi fechada");
						p.sendMessage(" ");
						Location saida = LocationForConfig.getLocation((YamlConfiguration) f, "saida_loc");
						p.teleport(saida);
						p.setHealth(20);
						p.getInventory().setHelmet(new ItemStack(Material.AIR));
						p.getInventory().setChestplate(new ItemStack(Material.AIR));
						p.getInventory().setLeggings(new ItemStack(Material.AIR));
						p.getInventory().setBoots(new ItemStack(Material.AIR));
						participantes.remove(p.getName());
						p.getInventory().clear();
						clanff.desativarClanFF(p);
					}
				}
			} else if (args[0].equalsIgnoreCase("setentrada")) {
				if (!sender.hasPermission("chain.admin")) {
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cComando apenas para jogadores");
					return true;
				}
				sender.sendMessage("§aVocê definiu a entrada da arena chain");
				Player p = (Player) sender;
				Location loc = p.getLocation();
				File file = new File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
				FileConfiguration f = YamlConfiguration.loadConfiguration(file);
				if (f.get("entrada_loc") == null) {
					f.addDefault("entrada_loc", LocationForConfig.getLocationForConfig(loc));
				} else {
					f.set("entrada_loc", LocationForConfig.getLocationForConfig(loc));
				}
				f.options().copyDefaults(true);
				try {
					f.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (args[0].equalsIgnoreCase("setsaida")) {
				if (!sender.hasPermission("chain.admin")) {
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cComando apenas para jogadores");
					return true;
				}
				sender.sendMessage("§aVocê definiu a saida da arena chain");
				Player p = (Player) sender;
				Location loc = p.getLocation();
				File file = new File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
				FileConfiguration f = YamlConfiguration.loadConfiguration(file);
				if (f.get("saida_loc") == null) {
					f.addDefault("saida_loc", LocationForConfig.getLocationForConfig(loc));
				} else {
					f.set("saida_loc", LocationForConfig.getLocationForConfig(loc));
				}
				f.options().copyDefaults(true);
				try {
					f.save(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (args[0].equalsIgnoreCase("entrar")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cComando apenas para jogadores");
					return true;
				}
				File file = new File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
				FileConfiguration f = YamlConfiguration.loadConfiguration(file);
				Player p = (Player) sender;
				if (participantes.contains(p.getName())) {
					sender.sendMessage("§cVocê já esta na arena!");
					return true;
				}
				if (!f.getBoolean("aberta")) {
					sender.sendMessage("§cNão é possível entrar na arena: Arena fechada!");
					return true;
				} else if (!inventory.inventoryEmpty(p)) {
					if (delay.contains(p)){
						p.sendMessage("§cÉ necessário esperar para usar este comando novamente...");
						return true;
					}
					Location entrada = LocationForConfig.getLocation((YamlConfiguration) f, "entrada_loc");
					delay.add(p);
					p.sendMessage(" ");
					p.sendMessage(" §e* Você foi teleportado para a arena!");
					p.sendMessage(" §e* Seu fogo amigo pessoal foi ativado!");
					p.sendMessage(" §e* Se desconectar-se aqui dentro, vai §lmorrer§e!");
					p.sendMessage(" §e* Para sair use §f/chain sair");
					p.sendMessage(" §e* Divirta-se!");
					p.sendMessage(" ");
					p.teleport(entrada);
					p.setHealth(20);
					p.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
					p.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
					p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
					p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
					p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
					p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));
					p.getInventory().addItem(new ItemStack(Material.BOW));
					p.getInventory().addItem(new ItemStack(Material.ARROW, 5));
					p.getActivePotionEffects().remove(true);
					p.getActivePotionEffects().clear();
					for(PotionEffect effect : p.getActivePotionEffects())
					{
					    p.removePotionEffect(effect.getType());
					}
					clanff.ativarClanFF(p);
					participantes.add(p.getName());
					Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
						@Override
						public void run() {
							if (delay.contains(p)){
								delay.remove(p);
							}
						}
					}, 20*15);
				} else {
					return true;
				}
			} else if (args[0].equalsIgnoreCase("sair")) {
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cComando apenas para jogadores");
					return true;
				}
				File file = new File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
				FileConfiguration f = YamlConfiguration.loadConfiguration(file);
				Player p = (Player) sender;
				if (participantes.contains(p.getName())) {
					if (p.getHealth() < 10){
						p.sendMessage("§cPara pode sair sua vida deve estar mais que a metade cheia!");
						return true;
					}
					Location saida = LocationForConfig.getLocation((YamlConfiguration) f, "saida_loc");
					p.teleport(saida);
					p.getInventory().setHelmet(new ItemStack(Material.AIR));
					p.getInventory().setChestplate(new ItemStack(Material.AIR));
					p.getInventory().setLeggings(new ItemStack(Material.AIR));
					p.getInventory().setBoots(new ItemStack(Material.AIR));
					participantes.remove(p.getName());
					p.setHealth(20);
					p.getInventory().clear();
					clanff.desativarClanFF(p);
				} else {
					p.sendMessage("§cVocê não esta na arena!");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("kick")) {
				if (!sender.hasPermission("chain.admin")) {
					sender.sendMessage("§cSem permissão!");
					return true;
				}
				if (args.length != 2) {
					sender.sendMessage("§cUso correto: /chain kick [Jogador]");
					return true;
				}
				File file = new File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
				FileConfiguration f = YamlConfiguration.loadConfiguration(file);
				Player target = Bukkit.getPlayer(args[1]);
				if (participantes.contains(target.getName())) {
					Location saida = LocationForConfig.getLocation((YamlConfiguration) f, "saida_loc");
					target.sendMessage(" ");
					target.sendMessage(" §e* Você foi teleportado á saida");
					target.sendMessage(" §e* Motivo: Você foi kickado!");
					target.sendMessage(" ");
					target.teleport(saida);
					target.setHealth(20);
					target.getInventory().setHelmet(new ItemStack(Material.AIR));
					target.getInventory().setChestplate(new ItemStack(Material.AIR));
					target.getInventory().setLeggings(new ItemStack(Material.AIR));
					target.getInventory().setBoots(new ItemStack(Material.AIR));
					participantes.remove(target.getName());
					target.getInventory().clear();
					clanff.desativarClanFF(target);
				} else {
					sender.sendMessage("§cEste jogador não esta na arena!");
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (participantes.contains(e.getEntity().getName())) {
			File file = new File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
			FileConfiguration f = YamlConfiguration.loadConfiguration(file);
			Location saida = LocationForConfig.getLocation((YamlConfiguration) f, "saida_loc");
			e.getEntity().sendMessage(" ");
			e.getEntity().getInventory().clear();
			e.getEntity().sendMessage(" §e* Você foi teleportado á saida!");
			e.getEntity().sendMessage(" §e* Motivo: Você morreu!");
			e.getEntity().sendMessage(" ");
			e.getEntity().teleport(saida);
			delay.add(e.getEntity());
			clanff.ativarClanFF(e.getEntity());
			Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
				@Override
				public void run() {
					participantes.remove(e.getEntity().getName());
					delay.remove(e.getEntity());
				}
			}, 15);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (participantes.contains(e.getPlayer().getName())) {
			e.getPlayer().getInventory().clear();
			e.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
			e.getPlayer().getInventory().setChestplate(new ItemStack(Material.AIR));
			e.getPlayer().getInventory().setLeggings(new ItemStack(Material.AIR));
			e.getPlayer().getInventory().setBoots(new ItemStack(Material.AIR));
			delay.add(e.getPlayer());
			e.getPlayer().setHealth(0);
			Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
				@Override
				public void run() {
					participantes.remove(e.getPlayer().getName());
					delay.remove(e.getPlayer());
				}
			}, 15);
		}
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void commandEvent(PlayerCommandPreprocessEvent e) {
		if (e == null) {
			return;
		}
		if (e.getPlayer() == null) {
			return;
		}
		Player p = e.getPlayer();
		if (participantes.contains(p.getName())) {
			String msg = e.getMessage();
			String arr[] = msg.split(" ");
			if (arr[0].equalsIgnoreCase("/chain") || arr[0].equalsIgnoreCase("/g") || arr[0].equalsIgnoreCase("/tell")
					|| arr[0].equalsIgnoreCase("/r") || arr[0].equalsIgnoreCase("/reportar")
					|| arr[0].equalsIgnoreCase("/denunciar") || arr[0].equalsIgnoreCase("/ajuda")) {
				return;
			} else {
				e.setCancelled(true);
				p.sendMessage("§cComandos bloqueados na arena, use §f/chain sair");
			}
		}
	}

	/*
	 * public boolean onCommand(CommandSender sender, Command cmd, String label,
	 * String[] args) { if (cmd.getName().equalsIgnoreCase("chain")) { if
	 * (args.length == 0) { sender.sendMessage("§cChain ➡§7 Comandos:");
	 * sender.sendMessage(" §8- §7/chain entrar §f- Entra na chain");
	 * sender.sendMessage(" §8- §7/chain sair §f- Sai da chain"); if
	 * (sender.hasPermission("chain.admin")) {
	 * sender.sendMessage(" §4- §c/chain abrir §f- Abre a arena");
	 * sender.sendMessage(" §4- §c/chain fechar §f- Fecha a arena");
	 * sender.sendMessage(" §4- §c/chain setentrada §f- Fecha a arena");
	 * sender.sendMessage(" §4- §c/chain setsaida §f- Fecha a arena");
	 * sender.sendMessage(" §4- §c/chain kick [player] §f- Kicka da arena");
	 * return true; } return true; } else if (args.length > 0) { if
	 * (args[0].equalsIgnoreCase("abrir")) { if
	 * (!sender.hasPermission("admin.admin")) {
	 * sender.sendMessage("§cSem permissão!"); return true; } else { File
	 * arenachainfile = new File( Main.instance.getDataFolder() + File.separator
	 * + "arena_chain.yml"); FileConfiguration arena =
	 * YamlConfiguration.loadConfiguration(arenachainfile); if
	 * (arena.getBoolean("config.aberta") == true) {
	 * sender.sendMessage("§cA arena já esta aberta!"); return true; } else {
	 * Bukkit.broadcastMessage("§cChain ➡§7 A arena chain foi aberta!");
	 * arena.set("config.aberta", true); arena.options().copyDefaults(true); try
	 * { arena.save(arenachainfile); } catch (IOException e) {
	 * e.printStackTrace(); } } } } else if (args[0].equalsIgnoreCase("fechar"))
	 * { if (!sender.hasPermission("admin.admin")) {
	 * sender.sendMessage("§cSem permissão!"); return true; } else { File
	 * arenachainfile = new File( Main.instance.getDataFolder() + File.separator
	 * + "arena_chain.yml"); FileConfiguration arena =
	 * YamlConfiguration.loadConfiguration(arenachainfile); if
	 * (arena.getBoolean("config.aberta") == false) {
	 * sender.sendMessage("§cA arena já esta fechada!"); return true; } else {
	 * Bukkit.broadcastMessage("§cChain ➡§7 A arena chain foi fechada!");
	 * Location saida = LocationForConfig.getLocation((YamlConfiguration) arena,
	 * "config.saida"); for (String participantesdachain2 : participantes) {
	 * Player participantesdachain = Bukkit.getPlayer(participantesdachain2);
	 * participantesdachain.teleport(saida);
	 * clanff.desativarClanFF(participantesdachain);
	 * participantesdachain.sendMessage(" "); participantesdachain.
	 * sendMessage("§e* Você foi teleportado para a saida da chain!");
	 * participantesdachain.sendMessage("§e* Motivo: A arena foi fechada!");
	 * participantesdachain.sendMessage(" "); } participantes.clear();
	 * arena.set("config.aberta", false); arena.options().copyDefaults(true);
	 * try { arena.save(arenachainfile); } catch (IOException e) {
	 * e.printStackTrace(); } } } } else if (args[0].equalsIgnoreCase("tst")) {
	 * sender.sendMessage("§6jogadores:" + participantes); return true; } else
	 * if (args[0].equalsIgnoreCase("entrar")) { File arenachainfile = new
	 * File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
	 * FileConfiguration arena =
	 * YamlConfiguration.loadConfiguration(arenachainfile); Player p = (Player)
	 * sender; if (participantes.contains(sender)) {
	 * sender.sendMessage("§cVocê já esta na arena!"); return true; } if
	 * (arena.getBoolean("config.aberta") == false) { sender.
	 * sendMessage("§cNão foi possivel entrar na arena, ela se encontra fechada!"
	 * ); return true; } else { if (inventory.inventoryEmpty(p)) { return true;
	 * } Location entrada = LocationForConfig.getLocation((YamlConfiguration)
	 * arena, "config.entrada"); p.teleport(entrada);
	 * p.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
	 * p.getInventory().setChestplate(new
	 * ItemStack(Material.CHAINMAIL_CHESTPLATE));
	 * p.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
	 * p.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
	 * p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
	 * p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 5));
	 * p.getInventory().addItem(new ItemStack(Material.BOW));
	 * p.getInventory().addItem(new ItemStack(Material.ARROW, 5));
	 * clanff.ativarClanFF(p); p.sendMessage(" ");
	 * p.sendMessage("§e* Você agora esta na arena chain!");
	 * p.sendMessage("§e* Aqui todos possuem os mesmos itens");
	 * p.sendMessage("§e* Você foi forçado a ativar o fogo amigo dos clans");
	 * p.sendMessage(" "); participantes.add(p.getName()); } } else if
	 * (args[0].equalsIgnoreCase("setentrada")) { if
	 * (!sender.hasPermission("chain.admin")) {
	 * sender.sendMessage("§cSem permissão!"); return true; } else { File
	 * arenachainfile = new File( Main.instance.getDataFolder() + File.separator
	 * + "arena_chain.yml"); FileConfiguration arena =
	 * YamlConfiguration.loadConfiguration(arenachainfile); Player p = (Player)
	 * sender; arena.set("config.entrada",
	 * LocationForConfig.getLocationForConfig(p.getLocation()));
	 * arena.options().copyDefaults(true);
	 * p.sendMessage("§cChain ➡§7 Você definiu a entrada da arena chain!"); try
	 * { arena.save(arenachainfile); } catch (IOException e) {
	 * e.printStackTrace(); } } } else if (args[0].equalsIgnoreCase("setsaida"))
	 * { if (!sender.hasPermission("chain.admin")) {
	 * sender.sendMessage("§cSem permissão!"); return true; } else { File
	 * arenachainfile = new File( Main.instance.getDataFolder() + File.separator
	 * + "arena_chain.yml"); FileConfiguration arena =
	 * YamlConfiguration.loadConfiguration(arenachainfile); Player p = (Player)
	 * sender; arena.set("config.saida",
	 * LocationForConfig.getLocationForConfig(p.getLocation()));
	 * arena.options().copyDefaults(true);
	 * p.sendMessage("§cChain ➡§7 Você definiu a saida da arena chain!"); try {
	 * arena.save(arenachainfile); } catch (IOException e) {
	 * e.printStackTrace(); } } } else if (args[0].equalsIgnoreCase("sair")) {
	 * if (!participantes.contains(sender.getName())) {
	 * sender.sendMessage("§cVocê não esta na arena!"); return true; } else {
	 * File arechainfile = new File( Main.instance.getDataFolder() +
	 * File.separator + "arena_chain.yml"); FileConfiguration config =
	 * YamlConfiguration.loadConfiguration(arechainfile); Player p = (Player)
	 * sender; participantes.remove(p.getName()); Location saida =
	 * LocationForConfig.getLocation((YamlConfiguration) config,
	 * "config.saida"); p.teleport(saida); clanff.desativarClanFF(p);
	 * p.getInventory().clear(); p.getInventory().setHelmet(new
	 * ItemStack(Material.AIR)); p.getInventory().setChestplate(new
	 * ItemStack(Material.AIR)); p.getInventory().setLeggings(new
	 * ItemStack(Material.AIR)); p.getInventory().setBoots(new
	 * ItemStack(Material.AIR)); p.sendMessage(" ");
	 * p.sendMessage("§e* Você saiu da arena chain!"); p.sendMessage(" "); } }
	 * else if (args[0].equalsIgnoreCase("kick")) { if
	 * (!sender.hasPermission("chain.admin")) {
	 * sender.sendMessage("§cSem permissão!"); return true; } else if
	 * (args.length == 2) { Player target = Bukkit.getPlayer(args[1]); if
	 * (participantes.contains(target.getName())) {
	 * sender.sendMessage("§cKickando " + target.getName() +
	 * " da arena chain!"); File arechainfile = new File(
	 * Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
	 * FileConfiguration config =
	 * YamlConfiguration.loadConfiguration(arechainfile);
	 * participantes.remove(target.getName()); Location saida =
	 * LocationForConfig.getLocation((YamlConfiguration) config,
	 * "config.saida"); target.teleport(saida); clanff.desativarClanFF(target);
	 * target.getInventory().clear(); target.getInventory().setHelmet(new
	 * ItemStack(Material.AIR)); target.getInventory().setChestplate(new
	 * ItemStack(Material.AIR)); target.getInventory().setLeggings(new
	 * ItemStack(Material.AIR)); target.getInventory().setBoots(new
	 * ItemStack(Material.AIR)); target.sendMessage(" ");
	 * target.sendMessage("§e* Você foi teleportado para a saida da chain!");
	 * target.sendMessage("§e* Motivo: Você foi kickado!");
	 * target.sendMessage(" "); } else {
	 * sender.sendMessage("§cEste jogador não esta na arena!"); return true; }
	 * return true; } else {
	 * sender.sendMessage("§cUso correto: /chain kick [player]"); return true; }
	 * 
	 * } else { sender.sendMessage("§cChain ➡§7 Comandos:");
	 * sender.sendMessage(" §8- §7/chain entrar §f- Entra na chain");
	 * sender.sendMessage(" §8- §7/chain sair §f- Sai da chain"); if
	 * (sender.hasPermission("chain.admin")) {
	 * sender.sendMessage(" §4- §c/chain abrir §f- Abre a arena");
	 * sender.sendMessage(" §4- §c/chain fechar §f- Fecha a arena");
	 * sender.sendMessage(" §4- §c/chain setentrada §f- Fecha a arena");
	 * sender.sendMessage(" §4- §c/chain setsaida §f- Fecha a arena");
	 * sender.sendMessage(" §4- §c/chain kick [player] §f- Kicka da arena");
	 * return true; } } } } return false; }
	 * 
	 * @EventHandler public void onCommand(PlayerCommandPreprocessEvent e) { if
	 * (participantes.contains(e.getPlayer().getName())) {
	 * e.getPlayer().sendMessage("cara n usa cmd não, é feio, tu ta na chain");
	 * } }
	 * 
	 * @EventHandler public void onDeath(PlayerDeathEvent e) {
	 * Bukkit.broadcastMessage("Evento chamado"); ArrayList<String> pl =
	 * participantes; Bukkit.broadcastMessage("§6jogadores: " + pl); if
	 * (!(e.getEntity() instanceof Player)) { return; } Player p =
	 * e.getEntity(); if (participantes.contains(p.getName())) {
	 * Bukkit.broadcastMessage("Verificado, arraylist participantes contem p");
	 * participantes.remove(p); File arechainfile = new
	 * File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
	 * FileConfiguration config =
	 * YamlConfiguration.loadConfiguration(arechainfile); Player p1 = (Player)
	 * e.getEntity(); participantes.remove(p1.getName());
	 * p1.getInventory().clear(); p1.getInventory().setHelmet(new
	 * ItemStack(Material.AIR)); p1.getInventory().setChestplate(new
	 * ItemStack(Material.AIR)); p1.getInventory().setLeggings(new
	 * ItemStack(Material.AIR)); p1.getInventory().setBoots(new
	 * ItemStack(Material.AIR)); Location saida =
	 * LocationForConfig.getLocation((YamlConfiguration) config,
	 * "config.saida"); p1.teleport(saida); clanff.desativarClanFF(p1); } }
	 * 
	 * @EventHandler public void onQuit(PlayerQuitEvent e) { if (!(e.getPlayer()
	 * instanceof Player)) { return; } Player p = e.getPlayer(); if
	 * (participantes.contains(e.getPlayer().getName())) {
	 * Bukkit.broadcastMessage("nego saiu e foi tirado da chain!");
	 * participantes.remove(e.getPlayer()); File arechainfile = new
	 * File(Main.instance.getDataFolder() + File.separator + "arena_chain.yml");
	 * FileConfiguration config =
	 * YamlConfiguration.loadConfiguration(arechainfile); Player p1 = (Player)
	 * e.getPlayer(); participantes.remove(p1.getName());
	 * p1.getInventory().clear(); p1.getInventory().setHelmet(new
	 * ItemStack(Material.AIR)); p1.getInventory().setChestplate(new
	 * ItemStack(Material.AIR)); p1.getInventory().setLeggings(new
	 * ItemStack(Material.AIR)); p1.getInventory().setBoots(new
	 * ItemStack(Material.AIR)); participantes.remove(p.getName()); Location
	 * saida = LocationForConfig.getLocation((YamlConfiguration) config,
	 * "config.saida"); p1.teleport(saida); clanff.desativarClanFF(p); } }
	 * 
	 */

}
