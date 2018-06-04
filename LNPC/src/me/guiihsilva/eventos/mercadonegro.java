package me.guiihsilva.eventos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import me.guiihsilva.Main;
import me.guiihsilva.cabecas.manager.Manager;
import me.guiihsilva.scoreboard.scoreboard;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.milkbowl.vault.economy.EconomyResponse;

public class mercadonegro implements Listener {


	@EventHandler
	private void npcLick2(NPCLeftClickEvent e) {
		if (e.getNPC().getId() == 10) {
			e.getClicker().playSound(e.getClicker().getLocation(), Sound.NOTE_PLING, 10, 3);
			openMainMenu(e.getClicker());
		}
	}
	@EventHandler
	private void npcLick1(NPCRightClickEvent e) {
		if (e.getNPC().getId() == 10) {
			e.getClicker().playSound(e.getClicker().getLocation(), Sound.NOTE_PLING, 10, 3);
			openMainMenu(e.getClicker());
		}
	}

	private void openMainMenu(Player p) {
		Inventory inv = Bukkit.createInventory(p, 9 * 3, "§7Mercado negro - PRINCIPAL");

		/*
		 * TROCA
		 */

		ItemStack trade = new ItemStack(Material.SOUL_SAND, 1);
		ItemMeta tradem = trade.getItemMeta();
		tradem.setDisplayName("§aVender cabeças");
		ArrayList<String> tradel = new ArrayList<>();
		tradel.add(" §7* Venda suas cabeças e receba");
		tradel.add(" §7pontos de cabeças para poder trocar");
		tradel.add(" §7por itens no mercado negro!");
		tradem.setLore(tradel);
		trade.setItemMeta(tradem);

		/*
		 * Como funciona
		 */

		ItemStack informacao = new ItemStack(Material.PAPER, 1);
		ItemMeta informacaom = informacao.getItemMeta();
		informacaom.setDisplayName("§6§lComo funciona?");
		ArrayList<String> informacaol = new ArrayList<>();
		informacaol.add(" §e* O sistema de cabeças");
		informacaol.add(" §eé uma espécie de economia");
		informacaol.add(" §esecundária, onde você pode");
		informacaol.add(" §etrocar a sua quantidade de");
		informacaol.add(" §ecabeças por itens!");
		informacaol.add(" ");
		informacaol.add("§6§lComo conseguir cabeças?");
		informacaol.add(" §e* Você deve pegar as cabeças");
		informacaol.add(" §ede jogadores matando-os! Daí");
		informacaol.add(" §ebasta coloca-la entre os vidros");
		informacaol.add(" §evermelhos para contar as cabeças");
		informacaol.add(" §ecomo pontos!");
		informacaom.setLore(informacaol);
		informacao.setItemMeta(informacaom);

		/*
		 * Loja negra
		 */

		ItemStack loja = new ItemStack(Material.EMERALD, 1);
		ItemMeta lojam = loja.getItemMeta();
		lojam.setDisplayName("§2§lLoja NEGRA");
		ArrayList<String> lojal = new ArrayList<>();
		lojal.add(" §7* Você pode trocar aqui");
		lojal.add(" §7itens pela quantidade de");
		lojal.add(" §7cabeças que você possui!");
		lojam.setLore(lojal);
		loja.setItemMeta(lojam);

		inv.setItem(4, informacao);
		inv.setItem(11, loja);
		inv.setItem(15, trade);
		p.openInventory(inv);
	}

	@EventHandler
	private void invClick(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§7Mercado negro - PRINCIPAL")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
				return ;
			}
			if (e.getCurrentItem().hasItemMeta()) {
				if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aVender cabeças")) {
						e.getWhoClicked().closeInventory();
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
							@Override
							public void run() {
								openTradeMenu((Player) e.getWhoClicked()); 
							}
						}, 10);
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2§lLoja NEGRA")) {
						e.getWhoClicked().closeInventory();
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
							@Override
							public void run() {
								openBlackShop((Player) e.getWhoClicked());
							}
						}, 10);
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lComo funciona?")) {
						return;
					}
				}
			}
		}
	}

	private void openTradeMenu(Player p) {
		Inventory inv = Bukkit.createInventory(p, 9, "§8Mercado §lNEGRO");

		/*
		 * Enfeite
		 * 
		 */
		ItemStack enfeite1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta enfeite1m = enfeite1.getItemMeta();
		enfeite1m.setDisplayName("§8Mercado NEGRO");
		enfeite1.setItemMeta(enfeite1m);

		/*
		 * Enfeite -->
		 * 
		 */

		ItemStack enfeited = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta enfeitedm = enfeited.getItemMeta();
		enfeitedm.setDisplayName("§e§m-->");
		enfeited.setItemMeta(enfeitedm);

		/*
		 * Enfeite <--
		 * 
		 */

		ItemStack enfeitee = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta enfeiteem = enfeitee.getItemMeta();
		enfeiteem.setDisplayName("§e§m<--");
		enfeitee.setItemMeta(enfeiteem);

		/*
		 * Informação
		 * 
		 */

		ItemStack informacao = new ItemStack(Material.PAPER, 1);
		ItemMeta informacaom = informacao.getItemMeta();
		informacaom.setDisplayName("§6§lComo funciona?");
		ArrayList<String> informacaol = new ArrayList<>();
		informacaol.add(" §e* O sistema de cabeças");
		informacaol.add(" §eé uma espécie de economia");
		informacaol.add(" §esecundária, onde você pode");
		informacaol.add(" §etrocar a sua quantidade de");
		informacaol.add(" §ecabeças por itens!");
		informacaol.add(" ");
		informacaol.add("§6§lComo conseguir cabeças?");
		informacaol.add(" §e* Você deve pegar as cabeças");
		informacaol.add(" §ede jogadores matando-os! Daí");
		informacaol.add(" §ebasta ir até a função vender");
		informacaol.add(" §ee coloca-la entre os vidros");
		informacaol.add(" §evermelhos para contar as cabeças");
		informacaol.add(" §ecomo pontos!");
		informacaom.setLore(informacaol);
		informacao.setItemMeta(informacaom);

		/*
		 * Vender cabeça
		 * 
		 */

		ItemStack trocar = new ItemStack(Material.ANVIL, 1);
		ItemMeta trocarm = trocar.getItemMeta();
		trocarm.setDisplayName("§aVender esta cabeça!");
		trocar.setItemMeta(trocarm);

		/*
		 * Abrir loja
		 * 
		 */

		ItemStack loja = new ItemStack(Material.EMERALD, 1);
		ItemMeta lojam = loja.getItemMeta();
		lojam.setDisplayName("§2§lLoja NEGRA");
		loja.setItemMeta(lojam);

		inv.setItem(0, enfeite1);
		inv.setItem(1, enfeite1);
		inv.setItem(2, enfeite1);
		inv.setItem(3, enfeited);
		inv.setItem(5, enfeitee);
		inv.setItem(6, enfeite1);
		inv.setItem(7, enfeite1);
		inv.setItem(8, trocar);
		p.openInventory(inv);
	}

	@EventHandler
	private void invInteract2(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§8Mercado §lNEGRO§r§7 (Loja)")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			if (e.getCurrentItem().hasItemMeta()) {
				if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
					Player p = (Player) e.getWhoClicked();
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eCapacete P6")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 8) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 8);
							p.sendMessage("§aItem comprado com êxito!");
							ItemStack capacete = new ItemStack(Material.DIAMOND_HELMET);
							ItemMeta capacetem = capacete.getItemMeta();
							capacetem.setDisplayName("§eCapacete P6 (Λ)");
							capacetem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
							capacetem.addEnchant(Enchantment.DURABILITY, 3, true);
							capacete.setItemMeta(capacetem);
							p.getInventory().addItem(capacete);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§ePeitoral P6")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 10) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 10) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 16);
							p.sendMessage("§aItem comprado com êxito!");
							ItemStack capacete = new ItemStack(Material.DIAMOND_CHESTPLATE);
							ItemMeta capacetem = capacete.getItemMeta();
							capacetem.setDisplayName("§ePeitoral P6 (Λ)");
							capacetem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
							capacetem.addEnchant(Enchantment.DURABILITY, 3, true);
							capacete.setItemMeta(capacetem);
							p.getInventory().addItem(capacete);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eCalça P6")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 9) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 9);
							p.sendMessage("§aItem comprado com êxito!");
							ItemStack capacete = new ItemStack(Material.DIAMOND_LEGGINGS);
							ItemMeta capacetem = capacete.getItemMeta();
							capacetem.setDisplayName("§eCalça P6 (Λ)");
							capacetem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
							capacetem.addEnchant(Enchantment.DURABILITY, 3, true);
							capacete.setItemMeta(capacetem);
							p.getInventory().addItem(capacete);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eBotas P6")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 7) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 7);
							p.sendMessage("§aItem comprado com êxito!");
							ItemStack capacete = new ItemStack(Material.DIAMOND_BOOTS);
							ItemMeta capacetem = capacete.getItemMeta();
							capacetem.setDisplayName("§eBotas P6 (Λ)");
							capacetem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
							capacetem.addEnchant(Enchantment.DURABILITY, 3, true);
							capacete.setItemMeta(capacetem);
							p.getInventory().addItem(capacete);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Mobspawner DROPER")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 35) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 35);
							p.sendMessage("§aItem comprado com êxito!");
							ItemStack capacete = new ItemStack(Material.DIAMOND_PICKAXE);
							ItemMeta capacetem = capacete.getItemMeta();
							capacetem.setDisplayName("§8Mobspawner DROPER");
							capacetem.addEnchant(Enchantment.SILK_TOUCH, 10, true);
							capacete.setItemMeta(capacetem);
							p.getInventory().addItem(capacete);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§21kk§f de money")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 5) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 5);
							p.sendMessage("§aItem comprado com êxito!");
							EconomyResponse r = Main.econ.depositPlayer(p, 1000000);
							if (r.transactionSuccess()){
								p.sendMessage("§aTransação efetuada com êxito!");
							}else{
								p.sendMessage("§c§lERRO: Não foi possivel efetuar a transação, alerte a staff, código: 0000024x41");
							}
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§22kk§f de money")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 9) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 100);
							p.sendMessage("§aItem comprado com êxito!");
							EconomyResponse r = Main.econ.depositPlayer(p, 2000000);
							if (r.transactionSuccess()){
								p.sendMessage("§aTransação efetuada com êxito!");
							}else{
								p.sendMessage("§c§lERRO: Não foi possivel efetuar a transação, alerte a staff, código: 0000024x41");
							}
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Maçã Dourada")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 3) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 3);
							p.sendMessage("§aItem comprado com êxito!");
							ItemStack capacete = new ItemStack(Material.GOLDEN_APPLE, 25, (short) 1);
							p.getInventory().addItem(capacete);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eEspada")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 7) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 7);
							p.sendMessage("§aItem comprado com êxito!");
							ItemStack capacete = new ItemStack(Material.DIAMOND_SWORD);
							ItemMeta capacetem = capacete.getItemMeta();
							capacetem.setDisplayName("§eEspada (Λ)");
							capacetem.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
							capacetem.addEnchant(Enchantment.DURABILITY, 3, true);
							capacete.setItemMeta(capacetem);
							p.getInventory().addItem(capacete);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}

					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eArco")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 4) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 4);
							p.sendMessage("§aItem comprado com êxito!");
							ItemStack capacete = new ItemStack(Material.BOW);
							ItemMeta capacetem = capacete.getItemMeta();
							capacetem.setDisplayName("§eArco (Λ)");
							capacetem.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
							capacetem.addEnchant(Enchantment.DURABILITY, 3, true);
							capacetem.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
							capacete.setItemMeta(capacetem);
							p.getInventory().addItem(capacete);
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}
					
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPoção de força")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (cabecas >= 2) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							if (invsvazio >= 36) {
								p.sendMessage("§cNecessário pelo menos 1 slot vazio para comprar!");
								return;
							}
							Manager.removePlayersHeads2(p, 2);
							p.sendMessage("§aItem comprado com êxito!");
							@SuppressWarnings("deprecation")
							Potion potion = new Potion(PotionType.STRENGTH, 2, false);
							p.getInventory().addItem(potion.toItemStack(1));
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}
					
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAcesso ao §f/transformar")) {
						int cabecas = Manager.getPlayersHeads(p);
						if (p.hasPermission("AutoBlock.command")){
							p.sendMessage("§cVocê já possui acesso ao /transformar");
							return;
						}
						if (cabecas >= 15) {
							int invsvazio = 0;
							for (int i = 0; i < 36; i++) {
								if (!(p.getInventory().getItem(i) == null)) {
									invsvazio = invsvazio + 1;
								}
							}
							Manager.removePlayersHeads2(p, 15);
							p.sendMessage("§aItem comprado com êxito!");
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add AutoBlock.command");
							p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 2);

						} else {
							p.closeInventory();
							p.sendMessage("§cSem cabeças suficientes!");
						}
					}
					
				}
			}
		} else {
			return;
		}
	}
	
	


	private void openBlockShop2(Player p) {
		p.closeInventory();
		Inventory inv = Bukkit.createInventory(p, 9 * 5, "§lLoja negra");

		/*
		 * CAPACETE
		 */
		ItemStack capacete = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta capacetem = capacete.getItemMeta();
		capacetem.setDisplayName("§eCapacete P6");
		ArrayList<String> capacetel = new ArrayList<>();
		capacetel.add("§7Proteção VI");
		capacetel.add("§7Inquebrável III");
		capacetel.add(" ");
		capacetel.add("§ePreço:§a 16 cabeças");
		capacetem.setLore(capacetel);
		// Glow glow = new Glow(-1412);
		// capacetem.addEnchant(glow, 1, true);
		capacete.setItemMeta(capacetem);

		inv.setItem(15, capacete);
		p.openInventory(inv);

	}

	private void openBlackShop(Player p) {
		Inventory shop = Bukkit.createInventory(p, 9 * 6, "§8Mercado §lNEGRO§r§7 (Loja)");

		/*
		 * CAPACETE
		 */
		ItemStack capacete = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta capacetem = capacete.getItemMeta();
		capacetem.setDisplayName("§eCapacete P6");
		ArrayList<String> capacetel = new ArrayList<>();
		capacetel.add("§7Proteção VI");
		capacetel.add("§7Inquebrável III");
		capacetel.add(" ");
		capacetel.add("§ePreço:§a 16 cabeças");
		capacetem.setLore(capacetel);
		capacete.setItemMeta(capacetem);

		/*
		 * PEITORAL
		 */
		ItemStack peitoral = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta peitoralm = peitoral.getItemMeta();
		peitoralm.setDisplayName("§ePeitoral P6");
		ArrayList<String> peitorall = new ArrayList<>();
		peitorall.add("§7Proteção VI");
		peitorall.add("§7Inquebrável III");
		peitorall.add(" ");
		peitorall.add("§ePreço:§a 16 cabeças");
		peitoralm.setLore(peitorall);
		peitoral.setItemMeta(peitoralm);

		/*
		 * CALÇAS
		 */
		ItemStack calcas = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemMeta calcasm = calcas.getItemMeta();
		calcasm.setDisplayName("§eCalça P6");
		ArrayList<String> calcasl = new ArrayList<>();
		calcasl.add("§7Proteção VI");
		calcasl.add("§7Inquebrável III");
		calcasl.add(" ");
		calcasl.add("§ePreço:§a 16 cabeças");
		calcasm.setLore(calcasl);
		calcas.setItemMeta(calcasm);

		/*
		 * BOTAS
		 */
		ItemStack botas = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta botasm = botas.getItemMeta();
		botasm.setDisplayName("§eBotas P6");
		ArrayList<String> botasl = new ArrayList<>();
		botasl.add("§7Proteção VI");
		botasl.add("§7Inquebrável III");
		botasl.add(" ");
		botasl.add("§ePreço:§a 16 cabeças");
		botasm.setLore(botasl);
		botas.setItemMeta(botasm);
		
		/*
		 * potforca
		 */
		ItemStack potforca = new ItemStack(Material.POTION, 1, (short) 8233);
		ItemMeta potforcam = potforca.getItemMeta();
		potforcam.setDisplayName("§cPoção de força");
		ArrayList<String> potforcal = new ArrayList<>();
		potforcal.add(" ");
		potforcal.add("§ePreço:§a 4 cabeças");
		potforcam.setLore(potforcal);
		potforca.setItemMeta(potforcam);

		/*
		 * silkpick
		 */
		ItemStack silkpick = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta silkpickm = silkpick.getItemMeta();
		silkpickm.setDisplayName("§8Mobspawner DROPER");
		ArrayList<String> silkpickl = new ArrayList<>();
		silkpickl.add("§7Toque Suave X");
		silkpickl.add(" ");
		silkpickl.add("§ePreço:§a 35 cabeças");
		silkpickm.setLore(silkpickl);
		silkpick.setItemMeta(silkpickm);

		/*
		 * money1kk
		 */
		ItemStack money1kk = new ItemStack(Material.PAPER);
		ItemMeta money1kkm = money1kk.getItemMeta();
		money1kkm.setDisplayName("§21kk§f de money");
		ArrayList<String> money1kkl = new ArrayList<>();
		money1kkl.add(" ");
		money1kkl.add("§ePreço:§a 13 cabeças");
		money1kkm.setLore(money1kkl);
		money1kk.setItemMeta(money1kkm);

		/*
		 * espada
		 */
		ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta espadam = espada.getItemMeta();
		espadam.setDisplayName("§eEspada");
		ArrayList<String> espadal = new ArrayList<>();
		espadal.add("§7Afiação VI");
		espadal.add("§7Inquebrável III");
		espadal.add(" ");
		espadal.add("§ePreço:§a 12 cabeças");
		espadam.setLore(espadal);
		espada.setItemMeta(espadam);

		/*
		 * arco
		 */
		ItemStack arco = new ItemStack(Material.BOW);
		ItemMeta arcom = arco.getItemMeta();
		arcom.setDisplayName("§eArco");
		ArrayList<String> arcol = new ArrayList<>();
		arcol.add("§7Força V");
		arcol.add("§7Inquebrável III");
		arcol.add("§7Infinidade I");
		arcol.add(" ");
		arcol.add("§ePreço:§a 6 cabeças");
		arcom.setLore(arcol);
		arco.setItemMeta(arcom);

		/*
		 * capiroto
		 */
		ItemStack capiroto = new ItemStack(Material.GOLDEN_APPLE, 25, (short) 1);
		ItemMeta capirotom = capiroto.getItemMeta();
		capirotom.setDisplayName("§5Maçã Dourada");
		ArrayList<String> capirotol = new ArrayList<>();
		capirotol.add(" ");
		capirotol.add("§ePreço:§a 3 cabeças");
		capirotom.setLore(capirotol);
		capiroto.setItemMeta(capirotom);

		/*
		 * money2kk
		 */
		ItemStack money2kk = new ItemStack(Material.PAPER);
		ItemMeta money2kkm = money2kk.getItemMeta();
		money2kkm.setDisplayName("§22kk§f de money");
		ArrayList<String> money2kkl = new ArrayList<>();
		money2kkl.add(" ");
		money2kkl.add("§ePreço:§a 100 cabeças");
		money2kkm.setLore(money2kkl);
		money2kk.setItemMeta(money2kkm);


		/*
		 * cmdtranformar
		 */
		ItemStack cmdtranformar = new ItemStack(Material.FLINT_AND_STEEL);
		ItemMeta cmdtranformarm = cmdtranformar.getItemMeta();
		cmdtranformarm.setDisplayName("§aAcesso ao §f/transformar");
		ArrayList<String> cmdtranformarl = new ArrayList<>();
		cmdtranformarl.add(" ");
		cmdtranformarl.add("§ePreço:§a 15 cabeças");
		cmdtranformarl.add("");
		cmdtranformarl.add("§7 * Comprando acesso a este");
		cmdtranformarl.add("§7comando, você usando o");
		cmdtranformarl.add("§7/transformar, vai tranformar");
		cmdtranformarl.add("§7todos itens possíveis, em blocos");
		cmdtranformarl.add("§7por exemplo: 63 minérios de carvão");
		cmdtranformarl.add("§7vai virar 7 blocos de carvão");
		cmdtranformarm.setLore(cmdtranformarl);
		cmdtranformar.setItemMeta(cmdtranformarm);
		
		shop.setItem(10, capacete);
		shop.setItem(19, peitoral);
		shop.setItem(28, calcas);
		shop.setItem(37, botas);
		shop.setItem(34, cmdtranformar);
		shop.setItem(42, money1kk);
		shop.setItem(43, money2kk);
		shop.setItem(16, silkpick);
		shop.setItem(15, espada);
		shop.setItem(14, arco);
		shop.setItem(11, capiroto);
		shop.setItem(20, potforca);
		//shop.setItem(20, potion.toItemStack(1));
		p.updateInventory();
		p.closeInventory();
		p.updateInventory();
		p.openInventory(shop);
		p.updateInventory();
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	private void invInteract(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equalsIgnoreCase("§8Mercado §lNEGRO")) {
			if (e.getCurrentItem() == null ) {
				e.setCancelled(true);
				return ;
			}
			if (e.getCurrentItem().hasItemMeta()) {
				if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Mercado NEGRO")) {
						e.setCancelled(true);
						return;
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lComo funciona?")) {
						e.setCancelled(true);
						return;
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§m<--")) {
						e.setCancelled(true);
						e.getWhoClicked().sendMessage("§4*§c Coloque uma cabeça entre os vidros vermelhos!");
						return;
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§m-->")) {
						e.setCancelled(true);
						e.getWhoClicked().sendMessage("§4*§c Coloque uma cabeça entre os vidros vermelhos!");
						return;
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aVender esta cabeça!")) {
						e.setCancelled(true);
						ItemStack[] inv = e.getInventory().getContents();
						int cuantity = 0;
						for (int i = 0; i < inv.length; i++) {
							if (inv[i] != null) {
								if (inv[i].getType() == Material.SKULL_ITEM) {
									int cant = inv[i].getAmount();
									cuantity = cuantity + cant;
								}
							}
						}
						if (cuantity == 1) {
							Player p = (Player) e.getWhoClicked();
							p.playSound(p.getLocation(), Sound.GHAST_SCREAM2, 10, 1);
							p.sendMessage("§2§l* §aUma cabeça foi vendida!");
							e.getInventory().setItem(4, new ItemStack(Material.AIR));
							Manager.addPlayersHeads(p, 1);
							scoreboard.setScore(p);
						} else {
							e.getWhoClicked().sendMessage(
									"§4*§c Para vender coloque §numa§r§c cabeça entre os vidros vermelhos!§f ");
						}
						return;
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2§lLoja NEGRA")) {
						e.getWhoClicked().closeInventory();
						e.getWhoClicked().closeInventory();
						Player p = (Player) e.getWhoClicked();
						Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
							@Override
							public void run() {
								openBlockShop2(p);
							}
						}, 10);
					} else {
						if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
							e.setCancelled(true);
							return;
						}
					}
				}
			}
			if (e.getCurrentItem().getType() == Material.AIR){
				return;
			}
			if (!(e.getCurrentItem().getType() == Material.SKULL || e.getCurrentItem().getType() == Material.SKULL_ITEM)){
				e.getWhoClicked().sendMessage("§cNeste menu, você pode interagir apenas com as cabeças!");
				e.setCancelled(true);
				return ;
			}
		} else {
			return;
		}
	}

	@EventHandler
	private void onInvClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		p.updateInventory();
		if (e.getInventory().getTitle().equalsIgnoreCase("§8Mercado §lNEGRO")){
			if (e.getInventory().getItem(4) != null){
				ItemStack i = e.getInventory().getItem(4);
				p.getInventory().addItem(i);
			}
		}
	}
}
