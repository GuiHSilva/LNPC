package me.guiihsilva.commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.guiihsilva.Main;

public class setloja implements CommandExecutor,Listener{

	private static File file = new File(Main.instance.getDataFolder() + File.separator + "lojas.yml");
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setloja")){
			if (!(sender instanceof Player)){
				sender.sendMessage("§cO comando não é suportado pelo console!");
				return true;
			}else{
				Player p = (Player) sender;
				Inventory inv = Bukkit.createInventory(null, 3*9, "§oConfigurações de loja");
				
				
				/* 
				 * ITEM ATIVADO
				 */
				ItemStack ativado = new ItemStack(Material.INK_SACK, 1, (short) 10);
				ItemMeta ativadom = ativado.getItemMeta();
				ativadom.setDisplayName(" §aATIVADO ");
				ativado.setItemMeta(ativadom);
			
				
				
				/* 
				 * ITEM desativado
				 */
				ItemStack desativado = new ItemStack(Material.INK_SACK, 1, (short) 8);
				ItemMeta desativadom = desativado.getItemMeta();
				desativadom.setDisplayName(" §cDESATIVADO ");
				desativado.setItemMeta(desativadom);
			

				/* 
				 * ITEM norank
				 */
				ItemStack norank = new ItemStack(Material.BARRIER);
				ItemMeta norankm = norank.getItemMeta();
				norankm.setDisplayName("§cNecessário rank §aCreeperIII §cpra cima para esta função!");
				norank.setItemMeta(norankm);
			
				/*
				 * setloja
				 */
				ItemStack setloja = new ItemStack(Material.ENDER_PEARL);
				ItemMeta setlojam = setloja.getItemMeta();
				setlojam.setDisplayName("§aDefinir sua loja neste local");
				ArrayList<String> setlojal = new ArrayList<>();
				setlojal.add("");
				setlojal.add("§7 Ao clicar, sua loja");
				setlojal.add("§7será definida na sua");
				setlojal.add("§7atual localização!");
				setlojam.setLore(setlojal);
				setloja.setItemMeta(setlojam);
				
				

				/*
				 * delloja
				 */
				ItemStack delloja = new ItemStack(Material.REDSTONE_BLOCK);
				ItemMeta dellojam = delloja.getItemMeta();
				dellojam.setDisplayName("§cDeletar loja");
				ArrayList<String> dellojal = new ArrayList<>();
				dellojal.add("");
				dellojal.add("§7 Ao clicar, sua loja");
				dellojal.add("§7será retirada do menu");
				dellojal.add("§7para acesso!");
				dellojam.setLore(dellojal);
				delloja.setItemMeta(dellojam);
			
				

				/*
				 * openorclose
				 */
				ItemStack openorclose = new ItemStack(Material.REDSTONE_TORCH_ON);
				ItemMeta openorclosem = openorclose.getItemMeta();
				openorclosem.setDisplayName("§aAbre ou fecha sua loja");
				ArrayList<String> openorclosel = new ArrayList<>();
				openorclosel.add("");
				openorclosel.add("§aATIVADO: §7Os jogadores vão");
				openorclosel.add("§7conseguir acessar sua loja");
				openorclosel.add("");
				openorclosel.add("§cDESATIVADO:§7 Ninguém vai");
				openorclosel.add("§7conseguir acessar a sua loja!");
				openorclosem.setLore(openorclosel);
				openorclose.setItemMeta(openorclosem);
				
				
				
				
				if (p.hasPermission("lojas.set")){

				}else{
					
				}
				p.openInventory(inv);
			}
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private static void hasShop(Player p){
		if (config.getString(p.getName() + "." + "world") == null){
			return;
		}
	}
}
