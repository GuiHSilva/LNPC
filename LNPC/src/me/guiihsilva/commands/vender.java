package me.guiihsilva.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.guiihsilva.Main;
import net.milkbowl.vault.economy.EconomyResponse;


public class vender implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("vender")){
			if (!(sender instanceof Player)){
				sender.sendMessage("§cComando apenas para jogadores in-game, não console");
			}
			Player p = (Player) sender;
			mainMenu(p);
		}
		return false;
	}

	public static void mainMenu(Player p){
		Inventory menu = Bukkit.createInventory(p, 9 * 5, "Vender: " + p.getName());
		/*
		 * FUNGO
		 * 
		 * */
		ItemStack fungo = new ItemStack(Material.NETHER_STALK);
		ItemMeta fungoim = fungo.getItemMeta();
		fungoim.setDisplayName("§cFungos do nether");
		ArrayList<String> fungol = new ArrayList<>();
		fungol.add(" ");
		fungol.add("§ePreço: §f170,000");
		fungol.add(" ");
		fungol.add("§7* Você deve estar com o seu");
		fungol.add("§7inventário §ncompletamente§r§7 lotado");
		fungol.add("§7deste item para poder vender!");
		fungoim.setLore(fungol);
		fungo.setItemMeta(fungoim);
		/*
		 * Cactus
		 * 
		 * */
		ItemStack cacto = new ItemStack(Material.CACTUS);
		ItemMeta cactoim = cacto.getItemMeta();
		cactoim.setDisplayName("§2Cactos");
		ArrayList<String> cactol = new ArrayList<>();
		cactol.add(" ");
		cactol.add("§ePreço: §f175,000");
		cactol.add(" ");
		cactol.add("§7* Você deve estar com o seu");
		cactol.add("§7inventário §ncompletamente§r§7 lotado");
		cactol.add("§7deste item para poder vender!");
		cactoim.setLore(cactol);
		cacto.setItemMeta(cactoim);
		/* 
		 * Melancia
		 * 
		 * */
		ItemStack melancia = new ItemStack(Material.MELON);
		ItemMeta melanciaim = melancia.getItemMeta();
		melanciaim.setDisplayName("§aMelancia");
		ArrayList<String> melancial = new ArrayList<>();
		melancial.add(" ");
		melancial.add("§ePreço: §f150,000");
		melancial.add(" ");
		melancial.add("§7* Você deve estar com o seu");
		melancial.add("§7inventário §ncompletamente§r§7 lotado");
		melancial.add("§7deste item para poder vender!");
		melanciaim.setLore(melancial);
		melancia.setItemMeta(melanciaim);
		
		
		/*
		 * Enfeite
		 * 
		 *  */
		

		ItemStack enfeite = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta enfeiteim = enfeite.getItemMeta();
		enfeiteim.setDisplayName("§c" + p.getName());
		enfeite.setItemMeta(enfeiteim);
		
		menu.setItem(20, fungo);
		menu.setItem(22, cacto);
		menu.setItem(24, melancia);
		menu.setItem(0, enfeite);
		menu.setItem(1, enfeite);
		menu.setItem(9, enfeite);
		menu.setItem(7, enfeite);
		menu.setItem(8, enfeite);
		menu.setItem(17, enfeite);
		menu.setItem(27, enfeite);
		menu.setItem(36, enfeite);
		menu.setItem(37, enfeite);
		menu.setItem(43, enfeite);
		menu.setItem(44, enfeite);
		menu.setItem(35, enfeite);
		p.openInventory(menu);
	}
	
	@EventHandler
	public static void onInvClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("Vender: " + p.getName())){
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
				return;
			}
			p.closeInventory();
			if (e.getCurrentItem().getType() == Material.NETHER_STALK){
				if (e.getCurrentItem().hasItemMeta()){
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cFungos do nether")){
						checkToSellNetherWarts(p);
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.CACTUS){
				if (e.getCurrentItem().hasItemMeta()){
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Cactos")){
						checkToSellNetherCacto(p);
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.MELON){
				if (e.getCurrentItem().hasItemMeta()){
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aMelancia")){
						checkToSellNetherMelancia(p);
					}
				}
			}
		}
	}
	
	public static void checkToSellNetherWarts(Player p){
		Inventory inv = p.getInventory();
		if (inv.contains(Material.NETHER_STALK, 2304)){
			EconomyResponse r = Main.econ.depositPlayer(p, 170000);
			if (r.transactionSuccess()){
				p.sendMessage("§aVocê vendeu um inventário inteiro de: §fFungos");
				p.getInventory().clear();
			}else{
				p.sendMessage("§cHouve um erro durante a venda!");
			}
		}else{
			p.sendMessage("§cSeu inventário não possui itens suficientes!");
		}
	}
	
	public static void checkToSellNetherCacto(Player p){
		Inventory inv = p.getInventory();
		if (inv.contains(Material.CACTUS, 2304)){
			EconomyResponse r = Main.econ.depositPlayer(p, 170000);
			if (r.transactionSuccess()){
				p.sendMessage("§aVocê vendeu um inventário inteiro de: §fCacto");
				p.getInventory().clear();
			}else{
				p.sendMessage("§cHouve um erro durante a venda!");
			}
		}else{
			p.sendMessage("§cSeu inventário não possui itens suficientes!");
		}
	}
	public static void checkToSellNetherMelancia(Player p){
		Inventory inv = p.getInventory();
		if (inv.contains(Material.MELON, 2304)){
			EconomyResponse r = Main.econ.depositPlayer(p, 170000);
			if (r.transactionSuccess()){
				p.sendMessage("§aVocê vendeu um inventário inteiro de: §fMelancia");
				p.getInventory().clear();
			}else{
				p.sendMessage("§cHouve um erro durante a venda!");
			}
		}else{
			p.sendMessage("§cSeu inventário não possui itens suficientes!");
		}
	}
}
