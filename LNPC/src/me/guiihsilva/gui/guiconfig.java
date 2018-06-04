package me.guiihsilva.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.earth2me.essentials.Essentials;

import me.guiihsilva.admin.BuildStats.BuildStat;
import me.guiihsilva.admin.BuildStats;
import me.guiihsilva.admin.admin;

public class guiconfig implements Listener {
	Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");

	@EventHandler(priority = EventPriority.HIGH)
	public void guiConfigs(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.BLAZE_ROD) {
			if (!p.getItemInHand().hasItemMeta()){
				if (!p.getItemInHand().getItemMeta().hasDisplayName()){
					if (!p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aMenu moderação §7(Clique direito)")){
						return;
					}
				}
			}
			if (!p.hasPermission("admin.config")) {
				return;
			}
			e.setCancelled(true);
			org.bukkit.inventory.Inventory inv = Bukkit.createInventory(null, 3 * 9, "§oConfigurações ADMIN");
			//ENFEITE
			
			ItemStack en = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
			ItemMeta enmeta = en.getItemMeta();
			enmeta.setDisplayName("§e§oMenu de moderação");
			en.setItemMeta(enmeta);
			for (int i = 26;i>=0;){
				inv.setItem(i--, en);
			}
			ItemStack visibilidade = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
			ItemMeta visibilidademeta = visibilidade.getItemMeta();
			visibilidademeta.setDisplayName("§aAlterar a sua visibilidade");
			ArrayList<String> lore = new ArrayList<>();
			lore.add(" ");
			lore.add(" §7Isso altera os status");
			lore.add(" §7do seu /v!");
			lore.add(" ");
			if (this.ess.getUser(p).isVanished()) {
				lore.add(" §7Status: §2Ativado");
			} else {
				lore.add(" §7Status: §cDesativado");
			}
			lore.add(" ");
			lore.add(" §cOBS:§f Se você estiver com");
			lore.add(" §fo /admin ativo, e desativar");
			lore.add(" §fessa função, você ficará");
			lore.add(" §fvisível, mesmo no /admin");
			lore.add(" ");
			visibilidademeta.setLore(lore);
			visibilidade.setItemMeta(visibilidademeta);
			inv.setItem(10, visibilidade);
			p.openInventory(inv);

			// Desativar tell

				ItemStack toggletell = new ItemStack(Material.NAME_TAG, 1);
				ItemMeta toggletellmeta = toggletell.getItemMeta();
				toggletellmeta.setDisplayName("§aMensagens privadas");
				ArrayList<String> loretell = new ArrayList<>();
				loretell.add(" ");
				loretell.add(" §7Isso altera os status");
				loretell.add(" §7so seu tell, para ninguém");
				loretell.add(" §7te mandar msg no tell.");
				loretell.add(" ");
				toggletellmeta.setLore(loretell);
				toggletell.setItemMeta(toggletellmeta);
				inv.setItem(12, toggletell);
				p.closeInventory();
			
			// Change admin mode
			if (admin.admin.contains(p.getName())) {

				@SuppressWarnings("deprecation")
				ItemStack admini = new ItemStack(351, 1, (short) 10);

				ItemMeta adminmeta = admini.getItemMeta();
				adminmeta.setDisplayName("§aAlterar o modo admin");
				ArrayList<String> adminl = new ArrayList<>();
				adminl.add(" ");
				adminl.add(" §7Ative/desative seu");
				adminl.add(" §7/admin!");
				adminl.add(" ");
				adminl.add(" §7Status: §aAtivado");
				adminmeta.setLore(adminl);
				admini.setItemMeta(adminmeta);
				inv.setItem(14, admini);
				p.openInventory(inv);
			} else {
				@SuppressWarnings("deprecation")
				ItemStack admini = new ItemStack(351, 1, (short) 8);
				ItemMeta adminmeta = admini.getItemMeta();
				adminmeta.setDisplayName("§aAlterar o modo admin");
				ArrayList<String> adminl = new ArrayList<>();
				adminl.add(" ");
				adminl.add(" §7Ative/desative seu");
				adminl.add(" §7/admin!");
				adminl.add(" ");
				adminl.add(" §7Status: §cDesativado");
				adminmeta.setLore(adminl);
				admini.setItemMeta(adminmeta);
				inv.setItem(14, admini);
				p.updateInventory();
			}

			// Change modo build
				ItemStack build = new ItemStack(Material.BRICK);
				ItemMeta buildmeta = build.getItemMeta();
				buildmeta.setDisplayName("§aModo build");
				ArrayList<String> buildlore = new ArrayList<>();
				buildlore.add(" ");
				buildlore.add(" §7Defina se poderá");
				buildlore.add(" §7ou não construir!");
				buildlore.add(" ");
				if(BuildStats.Build.get(p.getName()) == BuildStat.ON){
					buildlore.add(" §7Pode construir: §cNão");
				}else{
					buildlore.add(" §7Pode construir: §aSim");
				}
				buildmeta.setLore(buildlore);
				build.setItemMeta(buildmeta);
				inv.setItem(16, build);
				p.updateInventory();
				
				
				
			// Abrir inventario
				
				p.openInventory(inv);
			
		}
		
	}

	@EventHandler
	public void clickInventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§oConfigurações ADMIN")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){
				return ;
			}
			if (e.getCurrentItem().getType() == Material.REDSTONE_TORCH_ON) {
				if (!e.getCurrentItem().hasItemMeta()) {
					e.setCancelled(true);
					return;
				}
				if (this.ess.getUser(p).isVanished()) {
					this.ess.getUser(p).setVanished(false);
					p.sendMessage("§cVocê agora não esta mais invisivel");
					p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
					e.setCancelled(true);
					p.closeInventory();
					return;
				} else {
					this.ess.getUser(p).setVanished(true);
					p.sendMessage("§aVocê agora esta invisivel");
					p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
					e.setCancelled(true);
					p.closeInventory();
					return;
				}
			}
			if (e.getCurrentItem().hasItemMeta()
					&& e.getCurrentItem().getItemMeta().getDisplayName() == "§aAlterar o modo admin") {
				p.chat("/admin");
				p.closeInventory();

				p.updateInventory();

			}
		}

		if (e.getInventory().getName().equalsIgnoreCase("§oConfigurações ADMIN")) {
			if (e.getCurrentItem().getType() == Material.NAME_TAG) {
				p.chat("/chat tell");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
			}
		}
		if (e.getInventory().getName().equalsIgnoreCase("§oConfigurações ADMIN")) {
			if (e.getCurrentItem().getType() == Material.BRICK && e.getCurrentItem().getItemMeta().getDisplayName().contains("§aModo build")) {
				p.chat("/build");
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				p.closeInventory();
			}
		}
		if (e.getInventory().getName().equalsIgnoreCase("§oConfigurações ADMIN")) {
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE && e.getCurrentItem().getItemMeta().getDisplayName().contains("§e§oMenu de moderação")) {
				p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 5);
			}
		}
	}
	
	@EventHandler
	public void dropStickAdmin(PlayerDropItemEvent e){
		if (e.getPlayer().hasPermission("admin.dont.drop.stick")){
			ItemStack i  = e.getItemDrop().getItemStack();
			if (i.hasItemMeta()){
				if (i.getItemMeta().hasDisplayName()){
					if (i.getItemMeta().getDisplayName().equalsIgnoreCase("§aMenu moderação §7(Clique direito)")){
						e.getPlayer().sendMessage("§cVocê §lNÃO§c pode drop este item no chão!");
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
