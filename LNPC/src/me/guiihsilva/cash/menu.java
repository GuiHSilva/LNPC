package me.guiihsilva.cash;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import me.guiihsilva.utils.Title;
import me.guiihsilva.utils.inventory;
import net.milkbowl.vault.economy.EconomyResponse;

public class menu implements Listener {

//	 ######   ######   ###  #     #   #####   ###  ######      #     #       
//	 #     #  #     #   #   ##    #  #     #   #   #     #    # #    #       
//	 #     #  #     #   #   # #   #  #         #   #     #   #   #   #       
//	 ######   ######    #   #  #  #  #         #   ######   #     #  #       
//	 #        #   #     #   #   # #  #         #   #        #######  #       
//	 #        #    #    #   #    ##  #     #   #   #        #     #  #       
//	 #        #     #  ###  #     #   #####   ###  #        #     #  ####### 
	                                                                         
	
	
	public static void principal(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§oLoja de cash");

		/*
		 * CARREGANDO
		 */
		ItemStack loading = new ItemStack(Material.SKULL_ITEM);
		ItemMeta loadingm = loading.getItemMeta();
		loadingm.setDisplayName("§7Carregando, aguarde...");
		loading.setItemMeta(loadingm);
		for (int i = 53; i > -1; i--) {
			inv.setItem(i, loading);
		}
		p.openInventory(inv);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				for (int i = 53; i > -1; i--) {
					inv.setItem(i, new ItemStack(Material.AIR));

					/*
					 * ENFEITE
					 */
					ItemStack enfeite = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
					ItemMeta enfeitem = enfeite.getItemMeta();
					enfeitem.setDisplayName("§6LegendMania");
					enfeite.setItemMeta(enfeitem);

					/*
					 * CASH AMOUNT
					 */
					ItemStack cashqnt = new ItemStack(Material.ENDER_CHEST);
					ItemMeta cashqntm = cashqnt.getItemMeta();
					cashqntm.setDisplayName("§6Você possui: §f" + cash.getCash(p) + " de cash");
					cashqnt.setItemMeta(cashqntm);

					/*
					 * NAO DEFINIDO
					 */
					ItemStack naodefinido = new ItemStack(Material.BARRIER);
					ItemMeta naodefinidom = naodefinido.getItemMeta();
					naodefinidom.setDisplayName("§cEm breve...");
					naodefinido.setItemMeta(naodefinidom);

					/*
					 * VIP
					 */
					ItemStack vip = new ItemStack(Material.NETHER_STAR);
					ItemMeta vipm = vip.getItemMeta();
					vipm.setDisplayName("§6VIP");
					ArrayList<String> vipl = new ArrayList<>();
					vipl.add(" ");
					vipl.add(" §7Torne-se VIP");
					vipm.setLore(vipl);
					vip.setItemMeta(vipm);

					/*
					 * money
					 */
					ItemStack money = new ItemStack(Material.GOLD_NUGGET);
					ItemMeta moneym = money.getItemMeta();
					moneym.setDisplayName("§eMoney");
					ArrayList<String> moneyl = new ArrayList<>();
					moneyl.add(" ");
					moneyl.add(" §7Fique rico usando o seu cash!");
					moneym.setLore(moneyl);
					money.setItemMeta(moneym);

					/*
					 * pocoes
					 */
					ItemStack pocoes = new ItemStack(Material.BREWING_STAND_ITEM);
					ItemMeta pocoesm = pocoes.getItemMeta();
					pocoesm.setDisplayName("§cPoções");
					ArrayList<String> pocoesl = new ArrayList<>();
					pocoesl.add(" ");
					pocoesl.add(" §7Troque seu cash por poções!");
					pocoesm.setLore(pocoesl);
					pocoes.setItemMeta(pocoesm);

					/*
					 * mcmmo
					 */
					ItemStack mcmmo = new ItemStack(Material.REDSTONE);
					ItemMeta mcmmom = mcmmo.getItemMeta();
					mcmmom.setDisplayName("§dBooster mcMMo");
					ArrayList<String> mcmmol = new ArrayList<>();
					mcmmol.add(" ");
					mcmmol.add(" §7Múltiplique o seu XP ganho nas habilidades");
					mcmmom.setLore(mcmmol);
					mcmmo.setItemMeta(mcmmom);

					/*
					 * livros
					 */
					ItemStack livros = new ItemStack(Material.ENCHANTED_BOOK);
					ItemMeta livrosm = livros.getItemMeta();
					livrosm.setDisplayName("§5Livros §d(Encantamentos especiais)");
					ArrayList<String> livrosl = new ArrayList<>();
					livrosl.add(" ");
					livrosl.add(" §7Use encantamentos especiais...");
					livrosm.setLore(livrosl);
					livros.setItemMeta(livrosm);

					/*
					 * eventos
					 */
					ItemStack eventos = new ItemStack(Material.PAPER);
					ItemMeta eventosm = eventos.getItemMeta();
					eventosm.setDisplayName("§2Eventos");
					ArrayList<String> eventosl = new ArrayList<>();
					eventosl.add(" ");
					eventosl.add(" §7Você mesmo pode começar um evento!");
					eventosm.setLore(eventosl);
					eventos.setItemMeta(eventosm);

					/*
					 * kits
					 */
					ItemStack kits = new ItemStack(Material.FLINT_AND_STEEL);
					ItemMeta kitsm = kits.getItemMeta();
					kitsm.setDisplayName("§9Kits");
					ArrayList<String> kitsl = new ArrayList<>();
					kitsl.add(" ");
					kitsl.add(" §7Troque seu cash por kits!");
					kitsm.setLore(kitsl);
					kits.setItemMeta(kitsm);

					/*
					 * Chaves
					 */
					ItemStack efeitos = new ItemStack(Material.TRIPWIRE_HOOK);
					ItemMeta efeitosm = efeitos.getItemMeta();
					efeitosm.setDisplayName("§fChaves");
					ArrayList<String> efeitosl = new ArrayList<>();
					efeitosl.add(" ");
					efeitosl.add(" §7Dê chaves para todos onlines no servidor!");
					efeitosm.setLore(efeitosl);
					efeitos.setItemMeta(efeitosm);

					inv.setItem(4, cashqnt);
					inv.setItem(20, vip);
					inv.setItem(21, money);
					inv.setItem(22, pocoes);
					inv.setItem(23, mcmmo);
					inv.setItem(24, efeitos);
				//	inv.setItem(29, eventos);
					inv.setItem(29, naodefinido);
			//		inv.setItem(30, kits);
					inv.setItem(30, naodefinido);
			//		inv.setItem(31, efeitos);
					inv.setItem(31, naodefinido);
					inv.setItem(32, naodefinido);
					inv.setItem(33, naodefinido);

					inv.setItem(0, enfeite);
					inv.setItem(1, enfeite);
					inv.setItem(9, enfeite);
					inv.setItem(7, enfeite);
					inv.setItem(8, enfeite);
					inv.setItem(17, enfeite);
					inv.setItem(36, enfeite);
					inv.setItem(45, enfeite);
					inv.setItem(46, enfeite);
					inv.setItem(44, enfeite);
					inv.setItem(53, enfeite);
					inv.setItem(52, enfeite);
					inv.setItem(48, enfeite);
					inv.setItem(49, enfeite);
					inv.setItem(50, enfeite);
					inv.setItem(3, enfeite);
					inv.setItem(5, enfeite);

				}
			}
		}, 13);
	}
	

	@EventHandler
	private void onInteract(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§oLoja de cash")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			if (e.getCurrentItem().hasItemMeta()) {
				if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6LegendMania")) {
						p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 5);
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§6Você possui:")) {
						p.playSound(p.getLocation(), Sound.CLICK, 10, 2);
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cEm breve...")) {
						p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 5);
						p.closeInventory();
						p.sendMessage("§cEsta opção esta em breve...");
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equalsIgnoreCase("§5Livros §d(Encantamentos especiais)")) {
						p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 5);
						p.closeInventory();
						p.sendMessage("§cEsta opção esta em breve...");
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6VIP")) {
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
						p.closeInventory();
						vips(p);
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eMoney")) {
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
						p.closeInventory();
						money(p);
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPoções")) {
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
						p.closeInventory();
						pocoes(p);
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§dBooster mcMMo")) {
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
						p.closeInventory();
						mcmmo(p);
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fChaves")) {
						p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
						p.closeInventory();
						chaves(p);
					}
				}
			}
		}
	}
	
    
//#    #   ####   #    #  ######  #   # 
//##  ##  #    #  ##   #  #        # #  
//# ## #  #    #  # #  #  #####     #   
//#    #  #    #  #  # #  #         #   
//#    #  #    #  #   ##  #         #   
//#    #   ####   #    #  ######    #   
    
	public static void money(Player p){
		Inventory inv = Bukkit.createInventory(null, 9*6, "§oMoney");
		
		/*
		 * money1
		 */
		ItemStack money1 = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta money1m = money1.getItemMeta();
		money1m.setDisplayName("§e10kk");
		ArrayList<String> money1l = new ArrayList<>();
		money1l.add(" ");
		money1l.add(" §ePreço:§f 6 de cash");
		money1l.add(" §eQuantidade:§f 1");
		money1m.setLore(money1l);
		money1.setItemMeta(money1m);
		
		
		/*
		 * money2
		 */
		ItemStack money2 = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta money2m = money2.getItemMeta();
		money2m.setDisplayName("§c100kk");
		ArrayList<String> money2l = new ArrayList<>();
		money2l.add(" ");
		money2l.add(" §ePreço:§f 41 de cash");
		money2l.add(" §eQuantidade:§f 1");
		money2m.setLore(money2l);
		money2.setItemMeta(money2m);
		

		/*
		 * back
		 */
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta backm = back.getItemMeta();
		backm.setDisplayName("§eVoltar");
		back.setItemMeta(backm);
		
		inv.setItem(11, money1);
		inv.setItem(15, money2);
		inv.setItem(45, back);
		p.openInventory(inv);
	}
	
 /*
  *                                                 
 #####    ####    ####    ####   ######   ####  
 #    #  #    #  #    #  #    #  #       #      
 #    #  #    #  #       #    #  #####    ####  
 #####   #    #  #       #    #  #            # 
 #       #    #  #    #  #    #  #       #    # 
 #        ####    ####    ####   ######   ####  
                                                
  *  
  *  */
	
	public static void pocoes(Player p){
		Inventory inv = Bukkit.createInventory(null, 9*6, "§oPoções");
		
		/*
		 * pocoes1
		 */
		ItemStack pocoes1 = new ItemStack(Material.POTION, 32);
		ItemMeta pocoes1m = pocoes1.getItemMeta();
		pocoes1m.setDisplayName("§cPoção de força II (1:30)");
		ArrayList<String> pocoes1l = new ArrayList<>();
		pocoes1l.add(" ");
		pocoes1l.add(" §ePreço:§f 4 de cash");
		pocoes1l.add(" §eQuantidade:§f 32");
		pocoes1m.setLore(pocoes1l);
		pocoes1.setItemMeta(pocoes1m);
		
		
		/*
		 * pocoes2
		 */
		ItemStack pocoes2 = new ItemStack(Material.POTION, 32);
		ItemMeta pocoes2m = pocoes2.getItemMeta();
		pocoes2m.setDisplayName("§9Poção de agilidade II (1:30)");
		ArrayList<String> pocoes2l = new ArrayList<>();
		pocoes2l.add(" ");
		pocoes2l.add(" §ePreço:§f 2 de cash");
		pocoes2l.add(" §eQuantidade:§f 32");
		pocoes2m.setLore(pocoes2l);
		pocoes2.setItemMeta(pocoes2m);
		

		/*
		 * back
		 */
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta backm = back.getItemMeta();
		backm.setDisplayName("§eVoltar");
		back.setItemMeta(backm);
		
		inv.setItem(11, pocoes1);
		inv.setItem(15, pocoes2);
		inv.setItem(45, back);
		p.openInventory(inv);
	}
	

	/*
	
  #####   #     #     #     #     #  #######   #####  
 #     #  #     #    # #    #     #  #        #     # 
 #        #     #   #   #   #     #  #        #       
 #        #######  #     #  #     #  #####     #####  
 #        #     #  #######   #   #   #              # 
 #     #  #     #  #     #    # #    #        #     # 
  #####   #     #  #     #     #     #######   #####  
	 
	 * 
	 */
	

	public static void chaves(Player p){
		Inventory inv = Bukkit.createInventory(null, 9*6, "§oChaves");
		
		/*
		 * chaves1
		 */
		ItemStack chaves1 = new ItemStack(Material.TRIPWIRE_HOOK, 3);
		ItemMeta chaves1m = chaves1.getItemMeta();
		chaves1m.setDisplayName("§6Chave lendária");
		ArrayList<String> chaves1l = new ArrayList<>();
		chaves1l.add(" ");
		chaves1l.add(" §ePreço:§f 2 de cash");
		chaves1l.add(" §eQuantidade:§f 3");
		chaves1l.add(" ");
		chaves1l.add(" §7Ao comprar todos do servidor");
		chaves1l.add(" §7vão receber este item, exceto");
		chaves1l.add(" §7os que estiverem com o inventário");
		chaves1l.add(" §7completamente lotado");
		chaves1m.setLore(chaves1l);
		chaves1.setItemMeta(chaves1m);
		
		
		/*
		 * chaves2
		 */
		ItemStack chaves2 = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta chaves2m = chaves2.getItemMeta();
		chaves2m.setDisplayName("§4Chave 4x");
		ArrayList<String> chaves2l = new ArrayList<>();
		chaves2l.add(" ");
		chaves2l.add(" §ePreço:§f 5 de cash");
		chaves2l.add(" §eQuantidade:§f 1");
		chaves2l.add(" ");
		chaves2l.add(" §7Ao comprar todos do servidor");
		chaves2l.add(" §7vão receber este item, exceto");
		chaves2l.add(" §7os que estiverem com o inventário");
		chaves2l.add(" §7completamente lotado");
		chaves2m.setLore(chaves2l);
		chaves2.setItemMeta(chaves2m);
		

		/*
		 * back
		 */
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta backm = back.getItemMeta();
		backm.setDisplayName("§eVoltar");
		back.setItemMeta(backm);
		
		inv.setItem(11, chaves1);
		inv.setItem(15, chaves2);
		inv.setItem(45, back);
		p.openInventory(inv);
	}
	
	
	
	
	/* 
	 * 
	 
 #    #   ####   #    #  #    #   ####  
 ##  ##  #    #  ##  ##  ##  ##  #    # 
 # ## #  #       # ## #  # ## #  #    # 
 #    #  #       #    #  #    #  #    # 
 #    #  #    #  #    #  #    #  #    # 
 #    #   ####   #    #  #    #   ####  
	 
	 * */
	
	
	public static void mcmmo(Player p){
		Inventory inv = Bukkit.createInventory(null, 9*6, "§oBooster mcMMo");
		
		/*
		 * mcmmo1
		 */
		ItemStack mcmmo1 = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta mcmmo1m = mcmmo1.getItemMeta();
		mcmmo1m.setDisplayName("§aBoster em todas habilidades (1 hora)");
		ArrayList<String> mcmmo1l = new ArrayList<>();
		mcmmo1l.add(" ");
		mcmmo1l.add(" §ePreço:§f 12 de cash");
		mcmmo1l.add(" §eQuantidade:§f 1");
		mcmmo1l.add(" ");
		mcmmo1l.add(" §7Isso vai deixar suas habilidades");
		mcmmo1l.add(" §7com o XP múltiplicado por 50, ou");
		mcmmo1l.add(" §7seja, você vai upar, MUUUITO rápido");
		mcmmo1m.setLore(mcmmo1l);
		mcmmo1.setItemMeta(mcmmo1m);

		/*
		 * back
		 */
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta backm = back.getItemMeta();
		backm.setDisplayName("§eVoltar");
		back.setItemMeta(backm);
		
		inv.setItem(13, mcmmo1);
		inv.setItem(45, back);
		p.openInventory(inv);
	}
	
	
	/*
	 * 
 #     #  ###  ######  
 #     #   #   #     # 
 #     #   #   #     # 
 #     #   #   ######  
  #   #    #   #       
   # #     #   #       
    #     ###  #       
                       
	 * 
	 * */
	
	
	public static void vips(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 6, "§oVIPs");

		/*
		 * VIP
		 */
		ItemStack vip = new ItemStack(Material.IRON_HELMET);
		ItemMeta vipm = vip.getItemMeta();
		vipm.setDisplayName("§6VIP (30 dias)");
		ArrayList<String> vipl = new ArrayList<>();
		vipl.add(" ");
		vipl.add(" §ePreço:§f 15 de cash");
		vipl.add(" §eQuantidade:§f 1");
		vipm.setLore(vipl);
		vip.setItemMeta(vipm);

		/*
		 * vip+
		 */
		ItemStack vipplus = new ItemStack(Material.GOLD_HELMET);
		ItemMeta vipplusm = vipplus.getItemMeta();
		vipplusm.setDisplayName("§bVIP§3+ §b(30 dias)");
		ArrayList<String> vipplusl = new ArrayList<>();
		vipplusl.add(" ");
		vipplusl.add(" §ePreço:§f 25 de cash");
		vipplusl.add(" §eQuantidade:§f 1");
		vipplusm.setLore(vipplusl);
		vipplus.setItemMeta(vipplusm);

		/*
		 * vipultimate
		 */
		ItemStack vipultimate = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta vipultimatem = vipultimate.getItemMeta();
		vipultimatem.setDisplayName("§4VIP§cUltimate (30 dias)");
		ArrayList<String> vipultimatel = new ArrayList<>();
		vipultimatel.add(" ");
		vipultimatel.add(" §ePreço:§f 35 de cash");
		vipultimatel.add(" §eQuantidade:§f 1");
		vipultimatem.setLore(vipultimatel);
		vipultimate.setItemMeta(vipultimatem);

		/*
		 * 2X
		 */

		/*
		 * vip2
		 */
		ItemStack vip2 = new ItemStack(Material.IRON_HELMET);
		ItemMeta vip2m = vip2.getItemMeta();
		vip2m.setDisplayName("§6VIP (60 dias)");
		ArrayList<String> vip2l = new ArrayList<>();
		vip2l.add(" ");
		vip2l.add(" §ePreço:§f 30 de cash");
		vip2l.add(" §eQuantidade:§f 1");
		vip2m.setLore(vip2l);
		vip2.setItemMeta(vip2m);

		/*
		 * vip2+
		 */
		ItemStack vip2plus = new ItemStack(Material.GOLD_HELMET);
		ItemMeta vip2plusm = vip2plus.getItemMeta();
		vip2plusm.setDisplayName("§bVIP§3+ §b(60 dias)");
		ArrayList<String> vip2plusl = new ArrayList<>();
		vip2plusl.add(" ");
		vip2plusl.add(" §ePreço:§f 50 de cash");
		vip2plusl.add(" §eQuantidade:§f 1");
		vip2plusm.setLore(vip2plusl);
		vip2plus.setItemMeta(vip2plusm);

		/*
		 * vip2ultimate
		 */
		ItemStack vip2ultimate = new ItemStack(Material.DIAMOND_HELMET);
		ItemMeta vip2ultimatem = vip2ultimate.getItemMeta();
		vip2ultimatem.setDisplayName("§4VIP§cUltimate (60 dias)");
		ArrayList<String> vip2ultimatel = new ArrayList<>();
		vip2ultimatel.add(" ");
		vip2ultimatel.add(" §ePreço:§f 70 de cash");
		vip2ultimatel.add(" §eQuantidade:§f 1");
		vip2ultimatem.setLore(vip2ultimatel);
		vip2ultimate.setItemMeta(vip2ultimatem);


		/*
		 * back
		 */
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta backm = back.getItemMeta();
		backm.setDisplayName("§eVoltar");
		back.setItemMeta(backm);

		
		inv.setItem(20, vip);
		inv.setItem(22, vipplus);
		inv.setItem(24, vipultimate);
		inv.setItem(29, vip2);
		inv.setItem(31, vip2plus);
		inv.setItem(33, vip2ultimate);
		inv.setItem(45, back);

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				p.openInventory(inv);
			}
		}, 3);
	}


//	  #####                                                             
//	  #     #   ####   #    #  ######  #  #####   #    #    ##    #####  
//	  #        #    #  ##   #  #       #  #    #  ##  ##   #  #   #    # 
//	  #        #    #  # #  #  #####   #  #    #  # ## #  #    #  #    # 
//	  #        #    #  #  # #  #       #  #####   #    #  ######  #####  
//	  #     #  #    #  #   ##  #       #  #   #   #    #  #    #  #   #  
//	   #####    ####   #    #  #       #  #    #  #    #  #    #  #    # 
	                                                                     
	
	
	private static void confirmBuy(ItemStack item, Player p) {
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "§oCerteza?");

		/*
		 * Confirmar
		 */
		ItemStack confirmar = new ItemStack(Material.WOOL, 1, (short) 5);
		ItemMeta confirmarm = confirmar.getItemMeta();
		confirmarm.setDisplayName("§aComprar");
		confirmar.setItemMeta(confirmarm);

		/*
		 * cancelar
		 */
		ItemStack cancelar = new ItemStack(Material.WOOL, 1, (short) 14);
		ItemMeta cancelarm = cancelar.getItemMeta();
		cancelarm.setDisplayName("§cCancelar");
		cancelar.setItemMeta(cancelarm);
		
		
		inv.setItem(11, cancelar);
		inv.setItem(13, item);
		inv.setItem(15, confirmar);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				p.openInventory(inv);
			}
		}, 5);

	}

	@EventHandler
	private void onInteractConfirmBuy(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§oCerteza?")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			if (e.getCurrentItem().hasItemMeta()) {
				if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
					p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cCancelar")) {
						p.closeInventory();
						p.sendMessage("§cCompra cancelada...");
						return;
					}
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aComprar")) {
						ItemStack produto = e.getInventory().getItem(13);
						if (produto.hasItemMeta()) {
							if (produto.getItemMeta().hasDisplayName()) {
								String lore = e.getInventory().getItem(13).getItemMeta().getLore().get(1);
								String[] corte = lore.split(" ");
								String cash = corte[2];
								

								String lore2 = e.getInventory().getItem(13).getItemMeta().getLore().get(2);
								String[] corte2 = lore2.split(" ");
								String quantidade = corte2[2];
								
								p.closeInventory();
								Title title = new Title("", "&aProcessando...", 1, 1, 1);
								title.send(p);
								Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
									@Override
									public void run() {
										if (hasCash(p, Integer.parseInt(cash))){
											Title title = new Title("", "&4Erro...", 1, 1, 1);
											title.send(p);
											p.sendMessage("§cQuantidade de cash insuficiente!");
											return;
										}
										Title title = new Title("", "&2Sucesso!", 1, 2, 1);
										title.send(p);
										p.sendMessage("§aCompra efetuada com sucesso!");
										p.sendMessage("§aItem: " + e.getInventory().getItem(13).getItemMeta().getDisplayName());
										p.sendMessage("§aValor:§f " + cash + " de cash");
										p.sendMessage("§aQuantidade:§f " + quantidade);
										me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "remove");
										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§6VIP (30 dias)")) {
											p.closeInventory();
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
												@Override
												public void run() {
													if (inventory.inventoryEmpty2(p)){
														p.sendMessage("§cAtivação do produto cancelada! Motivo: Seu inventário necessida estar completamente limpo para a ativação! Você acabou de ser §nreembolsado§c!");
														me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");
														return ;
													}else{
														Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "darvip " + p.getName() + " vip 30");
													}
												}
											}, 35);
										}
										
										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§6VIP (60 dias)")) {
											p.closeInventory();
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
												@Override
												public void run() {
													if (inventory.inventoryEmpty2(p)){
														p.sendMessage("§cAtivação do produto cancelada! Motivo: Seu inventário necessida estar completamente limpo para a ativação! Você acabou de ser §nreembolsado§c!");
														me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");
														return ;
													}else{
														Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "darvip " + p.getName() + " vip 60");
													}
												}
											}, 35);
										}
										
										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§bVIP§3+ §b(30 dias)")) {
											p.closeInventory();
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
												@Override
												public void run() {
													if (inventory.inventoryEmpty2(p)){
														p.sendMessage("§cAtivação do produto cancelada! Motivo: Seu inventário necessida estar completamente limpo para a ativação! Você acabou de ser §nreembolsado§c!");
														me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");
														return ;
													}else{
														Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "darvip " + p.getName() + " vip+ 30");
													}
												}
											}, 35);
										}

										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§bVIP§3+ §b(60 dias)")) {
											p.closeInventory();
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
												@Override
												public void run() {
													if (inventory.inventoryEmpty2(p)){
														p.sendMessage("§cAtivação do produto cancelada! Motivo: Seu inventário necessida estar completamente limpo para a ativação! Você acabou de ser §nreembolsado§c!");
														me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");
														return ;
													}else{
														Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "darvip " + p.getName() + " vip+ 60");
													}
												}
											}, 35);
										}
										

										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§4VIP§cUltimate (30 dias)")) {
											p.closeInventory();
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
												@Override
												public void run() {
													if (inventory.inventoryEmpty2(p)){
														p.sendMessage("§cAtivação do produto cancelada! Motivo: Seu inventário necessida estar completamente limpo para a ativação! Você acabou de ser §nreembolsado§c!");
														me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");
														return ;
													}else{
														Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "darvip " + p.getName() + " vipultimate 30");
													}
												}
											}, 35);
										}
										
										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§4VIP§cUltimate (60 dias)")) {
											p.closeInventory();
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
												@Override
												public void run() {
													if (inventory.inventoryEmpty2(p)){
														p.sendMessage("§cAtivação do produto cancelada! Motivo: Seu inventário necessida estar completamente limpo para a ativação! Você acabou de ser §nreembolsado§c!");
														me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");
														return ;
													}else{
														Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "darvip " + p.getName() + " vipultimate 60");
													}
												}
											}, 35);
										}
										

										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§e10kk")) {
											p.closeInventory();
											EconomyResponse r = Main.econ.depositPlayer(p, 10000000);
											if (r.transactionSuccess()){
												Bukkit.broadcastMessage("§bCash »§3 " + p.getName() + "§f comprou §3" + ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()) + "§f no /shop");
												p.sendMessage("§aVocê acabou de ter depositado na sua conta: §f10,000,000");
											}else{
												p.sendMessage("§cHouve um erro ao depositar o dinheiro, você esta sendo reembolsado!");
												me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");}
										}

										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§c100kk")) {
											p.closeInventory();
											EconomyResponse r = Main.econ.depositPlayer(p, 100000000);
											if (r.transactionSuccess()){
												p.sendMessage("§aVocê acabou de ter depositado na sua conta: §f100,000,000");
												Bukkit.broadcastMessage("§bCash »§3 " + p.getName() + "§f comprou §3" + ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()) + "§f no /shop");
											}else{
												p.sendMessage("§cHouve um erro ao depositar o dinheiro, você esta sendo reembolsado!");
												me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");
											}
										}
										
										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§cPoção de força II (1:30)")) {
											p.closeInventory();
											if (inventory.inventoryEmpty3(p)){
												p.sendMessage("§cNão é possível dar item, pois o seu inventário esta completamente cheio, você esta sendo reembolsado!");
												me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");
												return ;
											}
											ItemStack item = new ItemStack(Material.POTION, 32, (short) 8233);
											p.getInventory().addItem(item);
											Bukkit.broadcastMessage("§bCash »§3 " + p.getName() + "§f comprou §3" + ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()) + "§f no /shop");
										}
										

										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§9Poção de agilidade II (1:30)")) {
											p.closeInventory();
											if (inventory.inventoryEmpty3(p)){
												p.sendMessage("§cNão é possível dar item, pois o seu inventário esta completamente cheio, você esta sendo reembolsado!");
												me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");return ;
											}
											ItemStack item = new ItemStack(Material.POTION, 32, (short) 8226);
											p.getInventory().addItem(item);
											Bukkit.broadcastMessage("§bCash »§3 " + p.getName() + "§f comprou §3" + ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()) + "§f no /shop");
										}
										

										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§6Chave lendária")) {
											p.closeInventory();
											if (inventory.inventoryEmpty3(p)){
												p.sendMessage("§cNão é possível dar item, pois o seu inventário esta completamente cheio, você esta sendo reembolsado!");
												me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");return ;
											}
											Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "cc giveall p lendaria " + quantidade);
											Bukkit.broadcastMessage("§bCash »§3 " + p.getName() + "§f comprou §3" + ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()) + "§f para todos do servidor no /shop");
										}
										
										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§4Chave 4x")) {
											p.closeInventory();
											if (inventory.inventoryEmpty3(p)){
												p.sendMessage("§cNão é possível dar item, pois o seu inventário esta completamente cheio, você esta sendo reembolsado!");
												me.guiihsilva.cash.cash.getDb(p, Integer.parseInt(cash), "add");return ;
											}
											Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "cc giveall p 4x " + quantidade);
											Bukkit.broadcastMessage("§bCash »§3 " + p.getName() + "§f comprou §3" + ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()) + "§f para todos do servidor no /shop");
										}
										

										if (produto.getItemMeta().getDisplayName().equalsIgnoreCase("§aBoster em todas habilidades (1 hora)")) {
											p.closeInventory();
											Bukkit.broadcastMessage("§bCash »§3 " + p.getName() + "§f comprou §3" + ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()) + "§f no /shop");
											Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add mcmmo.perks.xp.customboost.*");
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
												@Override
												public void run() {
													Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " remove mcmmo.perks.xp.customboost.*");
												}
											}, 20*60*60);
										}
										
										
										
									}
								}, 20*3);
							}
						}
					}
				}
			}
		}
	}
	
	private boolean hasCash(Player p, int cash){
		if (me.guiihsilva.cash.cash.getCash(p).equalsIgnoreCase("Jogador não encontrado no banco de dados!")){
			return true;
		}
		int pa = Integer.parseInt(me.guiihsilva.cash.cash.getCash(p));
		if (pa >= cash){
			return false;
		}
		return true;
	}
	
	
	/*
	 * Método invclick
	 *  
	 *  */

	@EventHandler
	private void onInteractCategory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("§oChaves") ||e.getInventory().getName().equalsIgnoreCase("§oVIPs") || e.getInventory().getName().equalsIgnoreCase("§oMoney") || e.getInventory().getName().equalsIgnoreCase("§oPoções")|| e.getInventory().getName().equalsIgnoreCase("§oBooster mcMMo")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
				return;
			}
			if (e.getCurrentItem().hasItemMeta()) {
				p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
				if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eVoltar")){
						p.closeInventory();
						principal(p);
						return;
					}
					confirmBuy(e.getCurrentItem(), p);
				}
			}
		}
	}
	
	
}
