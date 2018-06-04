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
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.guiihsilva.Main;

public class sethome implements CommandExecutor, Listener {

	
	private ArrayList<Player> delay = new ArrayList<>();

	private static File file = new File(Main.instance.getDataFolder() + File.separator + "homes.yml");
	@SuppressWarnings("static-access")
	public static FileConfiguration config = new YamlConfiguration().loadConfiguration(file);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sethome")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cComando apenas para jogadores in-game!!!");
				return true;
			}
			openMenuSetHome((Player) sender);
		}
		return false;
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	private void openMenuSetHome(Player p){
		if (!p.getLocation().getWorld().getName().equalsIgnoreCase("plots")){
			p.sendMessage("§cO sethome é liberado apenas no mundo dos terrenos!");
			return ;
		}
		if (delay.contains(p)){
			p.sendMessage("§cVocê deve esperar 35 segundos para abrir o menu novamente!");
			return;
		}
		delay.add(p);
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				delay.remove(p);
			}
		}, 20*35);
		Inventory inv = Bukkit.createInventory(null, 9*5, "§lDefina o local da home");
		
		File file2 = new File(Main.instance.getDataFolder() + File.separator + "homes.yml");
		FileConfiguration config2 = new YamlConfiguration().loadConfiguration(file2);
		
		/*
		 * HOME 1
		 */
		ItemStack home1 = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta home1m = home1.getItemMeta();
		ArrayList<String> home1l = new ArrayList<>();
		if (p.hasPermission("sethome.1")) {
			home1m.setDisplayName("§6Definir home:§e 1");
			home1l.add(" ");
			home1l.add(" §7* Ao clicar você vai");
			home1l.add(" §7definir a home 1 aqui!");
			home1l.add(" ");
			if (config2.getString("homes." + p.getName() + ".um.world") == null){
				home1l.add(" §c* Esta home esta vazia!");
				home1l.add(" ");
			}
		} else {
			home1.setType(Material.BARRIER);
			home1m.setDisplayName("§cSem rank suficiente! =(");
			home1l.add(" ");
			home1l.add(" §cRank necessário: §7ChickenII");
			home1l.add(" ");
		}
		home1m.setLore(home1l);
		home1.setItemMeta(home1m);
		/*
		 * Home 2
		 *  */
		ItemStack home2 = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta home2m = home2.getItemMeta();
		ArrayList<String> home2l = new ArrayList<>();
		if (p.hasPermission("sethome.2")) {
			home2m.setDisplayName("§6Definir home:§e 2");
			home2l.add(" ");
			home2l.add(" §7* Ao clicar você vai");
			home2l.add(" §7definir a home 2 aqui!");
			home2l.add(" ");
			if (config2.getString("homes." + p.getName() + ".dois.world") == null){
				home2l.add(" §c* Esta home esta vazia!");
				home2l.add(" ");
			}
		} else {
			home2.setType(Material.BARRIER);
			home2m.setDisplayName("§cSem rank suficiente! =(");
			home2l.add(" ");
			home2l.add(" §cRank necessário: §aSlimeIII");
			home2l.add(" ");
		}
		home2m.setLore(home2l);
		home2.setItemMeta(home2m);
		/*
		 * Home 3
		 *  */
		ItemStack home3 = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta home3m = home3.getItemMeta();
		ArrayList<String> home3l = new ArrayList<>();
		if (p.hasPermission("sethome.3")) {
			home3m.setDisplayName("§6Definir home:§e 3");
			home3l.add(" ");
			home3l.add(" §7* Ao clicar você vai");
			home3l.add(" §7definir a home 3 aqui!");
			home3l.add(" ");
			if (config2.getString("homes." + p.getName() + ".tres.world") == null){
				home3l.add(" §c* Esta home esta vazia!");
				home3l.add(" ");
			}
		} else {
			home3.setType(Material.BARRIER);
			home3m.setDisplayName("§cSem rank suficiente! =(");
			home3l.add(" ");
			home3l.add(" §cRank necessário: §6VIP");
			home3l.add(" ");
		}
		home3m.setLore(home3l);
		home3.setItemMeta(home3m);
		/* 
		 * Home 4
		 *  */
		ItemStack home4 = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta home4m = home4.getItemMeta();
		ArrayList<String> home4l = new ArrayList<>();
		if (p.hasPermission("sethome.4")) {
			home4m.setDisplayName("§6Definir home:§e 4");
			home4l.add(" ");
			home4l.add(" §7* Ao clicar você vai");
			home4l.add(" §7definir a home 4 aqui!");
			home4l.add(" ");
			if (config2.getString("homes." + p.getName() + ".quatro.world") == null){
				home4l.add(" §c* Esta home esta vazia!");
				home4l.add(" ");
			}
		} else {
			home4.setType(Material.BARRIER);
			home4m.setDisplayName("§cSem rank suficiente! =(");
			home4l.add(" ");
			home4l.add(" §cRank necessário: §bVIP§3+");
			home4l.add(" ");
		}
		home4m.setLore(home4l);
		home4.setItemMeta(home4m);
		/*
		 * HOME 5
		 *  */
		ItemStack home5 = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta home5m = home5.getItemMeta();
		ArrayList<String> home5l = new ArrayList<>();
		if (p.hasPermission("sethome.5")) {
			home5m.setDisplayName("§6Definir home:§e 5");
			home5l.add(" ");
			home5l.add(" §7* Ao clicar você vai");
			home5l.add(" §7definir a home 5 aqui!");
			home5l.add(" ");
			if (config2.getString("homes." + p.getName() + ".cinco.world") == null){
				home5l.add(" §c* Esta home esta vazia!");
				home5l.add(" ");
			}
		} else {
			home5.setType(Material.BARRIER);
			home5m.setDisplayName("§cSem rank suficiente! =(");
			home5l.add(" ");
			home5l.add(" §cRank necessário: §4VIP&cUltimate");
			home5l.add(" ");
		}
		home5m.setLore(home5l);
		home5.setItemMeta(home5m);
		
		inv.setItem(10, home1);	
		inv.setItem(12, home2);
		inv.setItem(14, home3);
		inv.setItem(16, home4);
		inv.setItem(31, home5);
		p.openInventory(inv);
	}
	
	@EventHandler
	private void onInvClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§lDefina o local da home")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){
				return;
			}
			if (e.getCurrentItem().hasItemMeta()){
				if (e.getCurrentItem().getItemMeta().hasDisplayName()){
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSem rank suficiente! =(")){
						p.sendMessage("§cVocê precisa evoluir (/rankup) para ter acesso!");
						p.closeInventory();
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Definir home:§e 1")){
						saveLocationInConfig(p.getLocation(), "um", p);
						p.sendMessage("§aA home foi definida neste local!");
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Definir home:§e 2")){
						saveLocationInConfig(p.getLocation(), "dois", p);
						p.sendMessage("§aA home foi definida neste local!");
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Definir home:§e 3")){
						saveLocationInConfig(p.getLocation(), "tres", p);
						p.sendMessage("§aA home foi definida neste local!");
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Definir home:§e 4")){
						saveLocationInConfig(p.getLocation(), "quatro", p);
						p.sendMessage("§aA home foi definida neste local!");
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Definir home:§e 5")){
						saveLocationInConfig(p.getLocation(), "cinco", p);
						p.sendMessage("§aA home foi definida neste local!");
						p.closeInventory();
						p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
					}
				}
			}
		}
	}
	
	private static void saveLocationInConfig(Location loc, String numero, Player p){
		Location pl = p.getLocation();
		if (config.getString("homes." + p.getName() + "." + numero) == null){
			config.addDefault("homes." + p.getName() + "." + numero + ".x", pl.getX());
			config.addDefault("homes." + p.getName() + "." + numero + ".y", pl.getY());
			config.addDefault("homes." + p.getName() + "." + numero + ".z", pl.getZ());
			config.addDefault("homes." + p.getName() + "." + numero + ".yaw", pl.getYaw());
			config.addDefault("homes." + p.getName() + "." + numero + ".pitch", pl.getPitch());
			config.addDefault("homes." + p.getName() + "." + numero + ".world", pl.getWorld().getName());
			saveConfig(file);
		}else{
			config.set("homes." + p.getName() + "." + numero + ".x", pl.getX());
			config.set("homes." + p.getName() + "." + numero + ".y", pl.getY());
			config.set("homes." + p.getName() + "." + numero + ".z", pl.getZ());
			config.set("homes." + p.getName() + "." + numero + ".yaw", pl.getYaw());
			config.set("homes." + p.getName() + "." + numero + ".pitch", pl.getPitch());
			config.set("homes." + p.getName() + "." + numero + ".world", pl.getWorld().getName());
			saveConfig(file);
		}
	}
	
	public static void saveConfig(File file){
		config.options().copyDefaults(true);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("§4* ERRO AO SALVAR ALGUMA CONFIGURAÇÃO DO ARQUIVO: homes.yml");
		}
	}

}
