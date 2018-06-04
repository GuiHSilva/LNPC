package me.guiihsilva.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class inventory {

	public static boolean inventoryEmpty(Player p) {
		p.updateInventory();
		for (ItemStack item : p.getInventory().getContents()) {
			if (item != null) {
				p.sendMessage("§cEsvazie seu inventário!");
				return true;
			}
		}
		for (@SuppressWarnings("unused") ItemStack armor : p.getInventory().getArmorContents()){
			if (p.getInventory().getHelmet() != null){
				p.sendMessage("§cEsvazie seu inventário! (Armaduras também contam!)");
				return true;
			}
			if (p.getInventory().getChestplate() != null){
				p.sendMessage("§cEsvazie seu inventário! (Armaduras também contam!)");
				return true;
			}
			if (p.getInventory().getLeggings() != null){
				p.sendMessage("§cEsvazie seu inventário! (Armaduras também contam!)");
				return true;
			}
			if (p.getInventory().getBoots() != null){
				p.sendMessage("§cEsvazie seu inventário! (Armaduras também contam!)");
				return true;
			}
		}
		return false;
	}
	
	public static boolean inventoryEmpty2(Player p){
		p.updateInventory();
		for (ItemStack item : p.getInventory().getContents()){
			if (item != null){
				return true;
			}
		}
		return false;
	}

	public static boolean inventoryEmpty3(Player p){
		p.updateInventory();
		int i = 0;
		for (ItemStack item : p.getInventory().getContents()){
			if (item == null){
				if (i > 1){
					return false;
				}
				i++;
			}
		}
		if (i <= 0){
			return true;
		}
		return false;
	}
	
}
