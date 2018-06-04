package me.guiihsilva.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
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

public class home implements CommandExecutor, Listener {

	private ArrayList<Player> delay = new ArrayList<>();
	
	private static File file = new File(Main.instance.getDataFolder() + File.separator + "homes.yml");
	@SuppressWarnings("static-access")
	public static FileConfiguration config = new YamlConfiguration().loadConfiguration(file);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cEsse comando é apenas para jogadores in-game!");
			return true;
		}
		openMenuHome((Player) sender); 
		return false;
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	private void openMenuHome(Player p){
		if (delay.contains(p)){
			p.sendMessage("§cVocê deve esperar 10 segundos para abrir o menu novamente!");
			return;
		}
		delay.add(p);
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				delay.remove(p);
			}
		}, 20*10);
		Inventory inv = Bukkit.createInventory(null, 9*5, "§lIr até uma home!");
		

		File file2 = new File(Main.instance.getDataFolder() + File.separator + "homes.yml");
		FileConfiguration config2 = new YamlConfiguration().loadConfiguration(file2);
		
		/*
		 * HOME 1
		 */
		ItemStack home1 = new ItemStack(Material.ENDER_PEARL);
		ItemMeta home1m = home1.getItemMeta();
		ArrayList<String> home1l = new ArrayList<>();
		if (p.hasPermission("sethome.1")) {
			home1m.setDisplayName("§6Teleportar-se até:§e 1");
			home1l.add(" ");
			home1l.add(" §7* Ao clicar você vai");
			home1l.add(" §7se teleportar até a");
			home1l.add(" §7sua home: 1");
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
		ItemStack home2 = new ItemStack(Material.ENDER_PEARL);
		ItemMeta home2m = home2.getItemMeta();
		ArrayList<String> home2l = new ArrayList<>();
		if (p.hasPermission("sethome.2")) {
			home2m.setDisplayName("§6Teleportar-se até:§e 2");
			home2l.add(" ");
			home2l.add(" §7* Ao clicar você vai");
			home2l.add(" §7se teleportar até a");
			home2l.add(" §7sua home: 2");
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
		ItemStack home3 = new ItemStack(Material.ENDER_PEARL);
		ItemMeta home3m = home3.getItemMeta();
		ArrayList<String> home3l = new ArrayList<>();
		if (p.hasPermission("sethome.3")) {
			home3m.setDisplayName("§6Teleportar-se até:§e 3");
			home3l.add(" ");
			home3l.add(" §7* Ao clicar você vai");
			home3l.add(" §7se teleportar até a");
			home3l.add(" §7sua home: 3");
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
		ItemStack home4 = new ItemStack(Material.ENDER_PEARL);
		ItemMeta home4m = home4.getItemMeta();
		ArrayList<String> home4l = new ArrayList<>();
		if (p.hasPermission("sethome.4")) {
			home4m.setDisplayName("§6Teleportar-se até:§e 4");
			home4l.add(" ");
			home4l.add(" §7* Ao clicar você vai");
			home4l.add(" §7se teleportar até a");
			home4l.add(" §7sua home: 4");
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
		ItemStack home5 = new ItemStack(Material.ENDER_PEARL);
		ItemMeta home5m = home5.getItemMeta();
		ArrayList<String> home5l = new ArrayList<>();
		if (p.hasPermission("sethome.5")) {
			home5m.setDisplayName("§6Teleportar-se até:§e 5");
			home5l.add(" ");
			home5l.add(" §7* Ao clicar você vai");
			home5l.add(" §7se teleportar até a");
			home5l.add(" §7sua home: 5");
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
	@SuppressWarnings("static-access")
	@EventHandler
	private void onInvClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§lIr até uma home!")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){
				return;
			}
			if (e.getCurrentItem().hasItemMeta()){
				if (e.getCurrentItem().getItemMeta().hasDisplayName()){
					File file2 = new File(Main.instance.getDataFolder() + File.separator + "homes.yml");
					FileConfiguration config2 = new YamlConfiguration().loadConfiguration(file2);
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSem rank suficiente! =(")){
						p.sendMessage("§cVocê precisa evoluir (/rankup) para ter acesso!");
						p.closeInventory();
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleportar-se até:§e 1")){
						if (config2.getString("homes." + p.getName() + ".um.world") == null){
							p.sendMessage("§cEsta home esta vazia! Use /sethome");
							p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 10, 1);
							p.closeInventory();
							return;
						}else{
							teleportTo(p, "um");
							p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
							p.closeInventory();
							return;
						}
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleportar-se até:§e 2")){
						if (config2.getString("homes." + p.getName() + ".dois.world") == null){
							p.sendMessage("§cEsta home esta vazia! Use /sethome");
							p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 10, 1);
							p.closeInventory();
							return;
						}else{
							teleportTo(p, "dois");
							p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
							p.closeInventory();
							return;
						}
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleportar-se até:§e 3")){
						if (config2.getString("homes." + p.getName() + ".tres.world") == null){
							p.sendMessage("§cEsta home esta vazia! Use /sethome");
							p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 10, 1);
							p.closeInventory();
							return;
						}else{
							teleportTo(p, "tres");
							p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
							p.closeInventory();
							return;
						}
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleportar-se até:§e 4")){
						if (config2.getString("homes." + p.getName() + ".quatro.world") == null){
							p.sendMessage("§cEsta home esta vazia! Use /sethome");
							p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 10, 1);
							p.closeInventory();
							return;
						}else{
							teleportTo(p, "quatro");
							p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
							p.closeInventory();
							return;
						}
					}else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Teleportar-se até:§e 5")){
						if (config2.getString("homes." + p.getName() + ".cinco.world") == null){
							p.sendMessage("§cEsta home esta vazia! Use /sethome");
							p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 10, 1);
							p.closeInventory();
							return;
						}else{
							teleportTo(p, "cinco");
							p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 2);
							p.closeInventory();
							return;
						}
					}
				}
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private static void teleportTo(Player p, String i){
		File file2 = new File(Main.instance.getDataFolder() + File.separator + "homes.yml");
		FileConfiguration config2 = new YamlConfiguration().loadConfiguration(file2);
		Double x = p.getLocation().getX();
		Double y = p.getLocation().getY();
		Double z = p.getLocation().getZ();
		p.sendMessage("§eTeleportando em §63 segundos§e!");
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if (p.getLocation().getX() == x){
					if (p.getLocation().getY() == y){
						if (p.getLocation().getZ() == z){
							Double x = config2.getDouble("homes."+p.getName()+"."+i+".x");
							Double y = config2.getDouble("homes."+p.getName()+"."+i+".y");
							Double z = config2.getDouble("homes."+p.getName()+"."+i+".z");
							Float yaw = (float) config2.getDouble("homes."+p.getName()+"."+i+".yaw");
							Float pitch = (float) config2.getDouble("homes."+p.getName()+"."+i+".pitch");
							World world = Bukkit.getWorld(config2.getString("homes." + p.getName() + "." + i + ".world"));
							Location loc = new Location(world, x, y, z);
							loc.setPitch(pitch);
							loc.setYaw(yaw);
							p.teleport(loc);
						}else{
							p.sendMessage("§cTeleporte cancelado... Você se moveu!");
						}
					}else{
						p.sendMessage("§cTeleporte cancelado... Você se moveu!");
					}
				}else{
					p.sendMessage("§cTeleporte cancelado... Você se moveu!");
				}
			}
		}, 20*3);
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
