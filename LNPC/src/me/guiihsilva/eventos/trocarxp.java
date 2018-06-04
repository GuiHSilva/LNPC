package me.guiihsilva.eventos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.guiihsilva.Main;
import me.guiihsilva.utils.FormarNumber;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.milkbowl.vault.economy.EconomyResponse;

public class trocarxp implements Listener {

	@EventHandler
	public void npcLick(NPCRightClickEvent e) {
		if (e.getNPC().getId() == 9) {
			e.getClicker().playSound(e.getClicker().getLocation(), Sound.LEVEL_UP, 10, 2);
			abrirMenu(e.getClicker());
		}
	}

	public static Inventory menu = Bukkit.createInventory(null, 9, "Loja de XP!");

	private static void abrirMenu(Player p) {
		ItemStack en = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
		ItemMeta enmeta = en.getItemMeta();
		enmeta.setDisplayName("§a§oLoja de XP");
		en.setItemMeta(enmeta);
		for (int i = 8; i >= 0;) {
			menu.setItem(i--, en);
		}
		ItemStack comprar = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta comprarm = comprar.getItemMeta();
		comprarm.setDisplayName("§2§l COMPRAR XP");
		ArrayList<String> comprarl = new ArrayList<>();
		comprarl.add(" ");
		comprarl.add(" §7Adquira §f30 §7leveis");
		comprarl.add(" §7a mais por §2$§f10,000");
		comprarl.add(" ");
		comprarm.setLore(comprarl);
		comprar.setItemMeta(comprarm);
		menu.setItem(5, comprar);
		int xplevel = p.getLevel();
		int preco = xplevel * 2000;
		preco = preco / 21;
		ItemStack vender = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta venderm = vender.getItemMeta();
		venderm.setDisplayName("§2§l VENDER XP");
		if (xplevel == 0) {
			ArrayList<String> vendeml = new ArrayList<>();
			vendeml.add(" ");
			vendeml.add(" §cXP Insuficiente");
			vendeml.add(" §cpara vender!");
			vendeml.add(" ");
			venderm.setLore(vendeml);
		} else {
			ArrayList<String> vendeml = new ArrayList<>();
			vendeml.add(" ");
			vendeml.add(" §7Venda §2" + xplevel + "§7 de xp");
			vendeml.add(" §7por §2$§f" + FormarNumber.formatValue(preco));
			vendeml.add(" ");
			venderm.setLore(vendeml);
		}
		vender.setItemMeta(venderm);
		menu.setItem(3, vender);
		p.openInventory(menu);
	}

	public static void createDisplay(ItemStack itemStack, Inventory inv, int Slot, String name, String lore) {
		ItemStack item = new ItemStack(itemStack);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);
		item.setItemMeta(meta);

		inv.setItem(Slot, item);

	}

	@EventHandler
	public void clickComprarXp(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("Loja de XP!")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){
				return ;
			}
			if (e.getCurrentItem().getType() == Material.EXP_BOTTLE) {
				if (e.getCurrentItem().hasItemMeta()) {
					if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
						if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§2§l COMPRAR XP")) {
							@SuppressWarnings("deprecation")
							EconomyResponse r = Main.econ.withdrawPlayer(p.getName(), 10000);
							if (r.transactionSuccess()) {
								e.setCancelled(true);
								int level = p.getLevel();
								p.setLevel(level + 30);
								p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 1);
								p.sendMessage("§9Loja de xp ➡ §fVocê comprou §230 níveis§f por §2$§f20,000");
								p.updateInventory();
								p.closeInventory();
							} else {
								e.getWhoClicked().sendMessage("§9Loja de xp ➡ §cVocê não possui dinheiro suficiente");
								p.closeInventory();
								p.updateInventory();
							}
						}
						if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§2§l VENDER XP")) {
							int xplevel = p.getLevel();
							int preco = xplevel * 2000;
							preco = preco / 21;
							p.setLevel(0);
							if (preco == 0){
								p.sendMessage("§9Loja de xp ➡ §cSem xp suficiente!");
								p.playSound(p.getLocation(), Sound.VILLAGER_NO, 10, 3);
								return ;
							}
							EconomyResponse r = Main.econ.depositPlayer(p, preco);
							if (r.transactionSuccess()) {
								p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 1);
								p.sendMessage("§9Loja de xp ➡ §fTroca efetuada com êxito");
								p.closeInventory();
								p.updateInventory();
							}
						}else{
							return ;
						}
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				e.setCancelled(true);
				p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 6);
			}
		}
	}
}
