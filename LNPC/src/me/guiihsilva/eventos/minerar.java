package me.guiihsilva.eventos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import me.guiihsilva.utils.CheckWorldGuard;
import me.guiihsilva.utils.Title;

public class minerar implements Listener {

	@EventHandler
	@SuppressWarnings("deprecation")
	public void onMine(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block block = e.getBlock();
		// e.getPlayer().sendMessage("Quebrou " + block.getType() + ";" +
		// e.getBlock().getTypeId());
		ItemStack minerador_sortudo = new ItemStack(Material.NETHER_STAR);
		ItemMeta minerador_sortudo_meta = minerador_sortudo.getItemMeta();
		if (block.getTypeId() == 21 || block.getTypeId() == 1) {
			if(block.getLocation().getWorld() != Bukkit.getWorld("world")){
				return;
			}
			if (!CheckWorldGuard.checkWorldGuard(e.getBlock().getLocation(), p, "BLOCK-BREAK", false)){
				return ;
			}
			minerador_sortudo_meta.setDisplayName("§6Minerador Sortudo");
			minerador_sortudo.setItemMeta(minerador_sortudo_meta);
			if (Math.random() * 100 < 0.5){
				Title t = new Title("&6&lHASTE!", "§eVocê encontrou um bônus de pressa II", 0, 2, 2);
				t.send(p);
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 2);
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20*35, 1));
			}
			if (Math.random() * 100 < 3) {
				e.getBlock().getDrops().clear();
				int ex = e.getBlock().getX();
				int ey = e.getBlock().getY();
				int ez = e.getBlock().getZ();
				World ew = e.getBlock().getWorld();
				Location loc = new Location(ew, ex, ey, ez);
				ew.dropItem(loc, minerador_sortudo);
				p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 10, 2);
				Title t = new Title("&6Minerador Sortudo", "§eVenda-o na loja!", 0, 1, 1);
				t.send(p);
				return;
			} else if (Math.random() * 100 < 0.4) {
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10, 2);
				ItemStack chave = new ItemStack(Material.TRIPWIRE_HOOK);
				ItemMeta chavemeta = chave.getItemMeta();
				chavemeta.setDisplayName("§7Chave comum");
				ArrayList<String> chavelore = new ArrayList<>();
				chavelore.add(" ");
				chavelore.add("§7* Abra uma caixa usando");
				chavelore.add("§7esta key, no /warp ficha");
				chavemeta.setLore(chavelore);
				chave.setItemMeta(chavemeta);
				int ex = e.getBlock().getX();
				int ey = e.getBlock().getY();
				int ez = e.getBlock().getZ();
				World ew = e.getBlock().getWorld();
				Location loc = new Location(ew, ex, ey, ez);
				ew.dropItem(loc, chave);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fogos " + e.getPlayer().getName());
				Title t = new Title("§6Encontrou uma chave!", "§eVocê encontrou minerando uma chave!", 0, 2, 1);
				t.send(p);
				return;
			} else if (Math.random() * 100 < 0.1) {
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10, 2);
				ItemStack chave = new ItemStack(Material.TRIPWIRE_HOOK);
				ItemMeta chavemeta = chave.getItemMeta();
				chavemeta.setDisplayName("§5Chave épica");
				ArrayList<String> chavelore = new ArrayList<>();
				chavelore.add(" ");
				chavelore.add("§7* Abra uma caixa usando");
				chavelore.add("§7esta key, no /warp ficha");
				chavemeta.setLore(chavelore);
				chave.setItemMeta(chavemeta);
				int ex = e.getBlock().getX();
				int ey = e.getBlock().getY();
				int ez = e.getBlock().getZ();
				World ew = e.getBlock().getWorld();
				Location loc = new Location(ew, ex, ey, ez);
				ew.dropItem(loc, chave);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fogos " + e.getPlayer().getName());
				Title t = new Title("§6Encontrou uma chave!", "§eVocê encontrou minerando uma chave!", 0, 2, 1);
				t.send(p);
				return;
			} else if (Math.random() * 100 < 0.042) {
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10, 2);
				ItemStack chave = new ItemStack(Material.TRIPWIRE_HOOK);
				ItemMeta chavemeta = chave.getItemMeta();
				chavemeta.setDisplayName("§6Chave lendária");
				ArrayList<String> chavelore = new ArrayList<>();
				chavelore.add(" ");
				chavelore.add("§7* Abra uma caixa usando");
				chavelore.add("§7esta key, no /warp ficha");
				chavemeta.setLore(chavelore);
				chave.setItemMeta(chavemeta);
				int ex = e.getBlock().getX();
				int ey = e.getBlock().getY();
				int ez = e.getBlock().getZ();
				World ew = e.getBlock().getWorld();
				Location loc = new Location(ew, ex, ey, ez);
				ew.dropItem(loc, chave);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fogos " + e.getPlayer().getName());
				Title t = new Title("§6Encontrou uma chave!", "§eVocê encontrou minerando uma chave!", 0, 2, 1);
				t.send(p);
				return;
			} else if (Math.random() * 100 < 0.0050) {
				p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10, 2);
				ItemStack chave = new ItemStack(Material.TRIPWIRE_HOOK);
				ItemMeta chavemeta = chave.getItemMeta();
				chavemeta.setDisplayName("§4Chave 4x");
				ArrayList<String> chavelore = new ArrayList<>();
				chavelore.add(" ");
				chavelore.add("§7* Abra uma caixa usando");
				chavelore.add("§7esta key, no /warp ficha");
				chavemeta.setLore(chavelore);
				chave.setItemMeta(chavemeta);
				int ex = e.getBlock().getX();
				int ey = e.getBlock().getY();
				int ez = e.getBlock().getZ();
				World ew = e.getBlock().getWorld();
				Location loc = new Location(ew, ex, ey, ez);
				ew.dropItem(loc, chave);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
						"bc O jogador(a) " + p.getName() + " mitou e encontrou uma chave &44x&f minerando!");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fogos " + e.getPlayer().getName());
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fogos " + e.getPlayer().getName());
				Title t = new Title("§6Encontrou uma chave!", "§eVocê encontrou minerando uma chave!", 0, 2, 1);
				t.send(p);
				return;
			}
		}
	}

	public static ArrayList<String> delay = new ArrayList<>();

	void sendActionbarMessage(Player player, String message) {
		new ActionbarTitleObject(message).send(player);
	}
	

/*
 * 
 * @EventHandler
	public void aoquebrarteste(BlockBreakEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getInventory().getItemInHand();

		if (!p.getWorld().getName().equalsIgnoreCase("world"))
			return;

		
		for (ProtectedRegion r : WGBukkit.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation())) {
			if (r.getId().contains("minas")) {
				System.out.println("esta na regiao com nome minas");
				if (item.getType() == Material.DIAMOND_PICKAXE) {
					if (item.hasItemMeta() && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
							&& item.getItemMeta().getDisplayName().equals("§4PICARETA EXPLOSIVA I")) {

						int block = e.getBlock().getTypeId();
						Location loc = e.getBlock().getLocation();
						double X = loc.getBlockX();
						double Y = loc.getBlockY();
						double Z = loc.getBlockZ();
						if (block == 21 || block == 56) {
							if (delay.contains(p.getPlayer().getName())) {
								sendActionbarMessage(p, "§cRecarregando picareta para explosão!");
								return;
							}
							p.getWorld().createExplosion(loc, 0);
							int radius = 3 - 2;
							p.getWorld().createExplosion(loc, 0.0F);
							double minX = X - radius;
							double maxX = X + radius + 1.0D;
							double minY = Y - radius;
							double maxY = Y + radius + 1.0D;
							double minZ = Z - radius;
							double maxZ = Z + radius + 1.0D;
							for (double x = minX; x < maxX; x += 1.0D) {
								for (double y = minY; y < maxY; y += 1.0D) {
									for (double z = minZ; z < maxZ; z += 1.0D) {
										Location location = new Location(loc.getWorld(), x, y, z);
										if (location.getBlock().getType() == Material.LAPIS_ORE
												|| location.getBlock().getType() == Material.DIAMOND_ORE
												|| location.getBlock().getType() == Material.COAL_ORE) {
											location.getBlock().breakNaturally();
											delay.add(p.getPlayer().getName());
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance,
													new Runnable() {

														@Override
														public void run() {
															delay.remove(p.getPlayer().getName());
															p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10, 3);
															sendActionbarMessage(p, "§aPicareta recarregada!");
														}
													}, 20 * 1);
											return;
										}
									}
								}
							}

						}

					}
					if (item.hasItemMeta() && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
							&& item.getItemMeta().getDisplayName().equals("§4PICARETA EXPLOSIVA II")) {
						int block = e.getBlock().getTypeId();
						Location loc = e.getBlock().getLocation();
						double X = loc.getBlockX();
						double Y = loc.getBlockY();
						double Z = loc.getBlockZ();
						if (block == 21 || block == 56) {
							if (delay.contains(p.getPlayer().getName())) {
								sendActionbarMessage(p, "§cRecarregando picareta para explosão!");
								return;
							}
							p.getWorld().createExplosion(loc, 0);
							int radius2 = 4 - 2;
							p.getWorld().createExplosion(loc, 0.0F);
							double minX = X - radius2;
							double maxX = X + radius2 + 1.0D;
							double minY = Y - radius2;
							double maxY = Y + radius2 + 1.0D;
							double minZ = Z - radius2;
							double maxZ = Z + radius2 + 1.0D;
							for (double x = minX; x < maxX; x += 1.0D) {
								for (double y = minY; y < maxY; y += 1.0D) {
									for (double z = minZ; z < maxZ; z += 1.0D) {
										Location location = new Location(loc.getWorld(), x, y, z);
										if (location.getBlock().getType() == Material.LAPIS_ORE
												|| location.getBlock().getType() == Material.DIAMOND_ORE
												|| location.getBlock().getType() == Material.COAL_ORE) {
											location.getBlock().breakNaturally();
											delay.add(p.getPlayer().getName());
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance,
													new Runnable() {

														@Override
														public void run() {
															delay.remove(p.getPlayer().getName());
															p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10, 3);
															sendActionbarMessage(p, "§aPicareta recarregada!");
														}
													}, 20 * 3);
											return;
										}
									}
								}
							}

						}
					}
					if (item.hasItemMeta() && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
							&& item.getItemMeta().getDisplayName().equals("§4PICARETA EXPLOSIVA III")) {
						int block = e.getBlock().getTypeId();
						Location loc = e.getBlock().getLocation();
						double X = loc.getBlockX();
						double Y = loc.getBlockY();
						double Z = loc.getBlockZ();
						if (block == 21 || block == 56) {
							if (delay.contains(p.getPlayer().getName())) {
								sendActionbarMessage(p, "§cRecarregando picareta para explosão!");
								return;
							}
							p.getWorld().createExplosion(loc, 0);
							int radius3 = 5 - 2;
							p.getWorld().createExplosion(loc, 0.0F);
							double minX = X - radius3;
							double maxX = X + radius3 + 1.0D;
							double minY = Y - radius3;
							double maxY = Y + radius3 + 1.0D;
							double minZ = Z - radius3;
							double maxZ = Z + radius3 + 1.0D;
							for (double x = minX; x < maxX; x += 1.0D) {
								for (double y = minY; y < maxY; y += 1.0D) {
									for (double z = minZ; z < maxZ; z += 1.0D) {
										Location location = new Location(loc.getWorld(), x, y, z);
										if (location.getBlock().getType() == Material.LAPIS_ORE
												|| location.getBlock().getType() == Material.DIAMOND_ORE
												|| location.getBlock().getType() == Material.COAL_ORE) {
											location.getBlock().breakNaturally();
											delay.add(p.getPlayer().getName());
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance,
													new Runnable() {

														@Override
														public void run() {
															delay.remove(p.getPlayer().getName());
															p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10, 3);
															sendActionbarMessage(p, "§aPicareta recarregada!");
														}
													}, 20 * 6);
											return;
										}
									}
								}
							}

						}
					}
				}
			}else if (r.getId().contains("minapvpm")){
				System.out.println("esta na regiao com nome minapvpm");
				if (item.getType() == Material.DIAMOND_PICKAXE) {
					if (item.hasItemMeta() && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
							&& item.getItemMeta().getDisplayName().equals("§4PICARETA EXPLOSIVA I")) {

						int block = e.getBlock().getTypeId();
						Location loc = e.getBlock().getLocation();
						double X = loc.getBlockX();
						double Y = loc.getBlockY();
						double Z = loc.getBlockZ();
						if (block == 1) {
							if (delay.contains(p.getPlayer().getName())) {
								sendActionbarMessage(p, "§cRecarregando picareta para explosão!");
								return;
							}
							p.getWorld().createExplosion(loc, 0);
							int radius = 3 - 2;
							p.getWorld().createExplosion(loc, 0.0F);
							double minX = X - radius;
							double maxX = X + radius + 1.0D;
							double minY = Y - radius;
							double maxY = Y + radius + 1.0D;
							double minZ = Z - radius;
							double maxZ = Z + radius + 1.0D;
							for (double x = minX; x < maxX; x += 1.0D) {
								for (double y = minY; y < maxY; y += 1.0D) {
									for (double z = minZ; z < maxZ; z += 1.0D) {
										Location location = new Location(loc.getWorld(), x, y, z);
										if (location.getBlock().getType() == Material.STONE) {
											location.getBlock().breakNaturally();
											delay.add(p.getPlayer().getName());
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance,
													new Runnable() {

														@Override
														public void run() {
															delay.remove(p.getPlayer().getName());
															p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10, 3);
															sendActionbarMessage(p, "§aPicareta recarregada!");
														}
													}, 20 * 1);
											return;
										}
									}
								}
							}

						}

					}
					if (item.hasItemMeta() && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
							&& item.getItemMeta().getDisplayName().equals("§4PICARETA EXPLOSIVA II")) {
						int block = e.getBlock().getTypeId();
						Location loc = e.getBlock().getLocation();
						double X = loc.getBlockX();
						double Y = loc.getBlockY();
						double Z = loc.getBlockZ();
						if (block == 1) {
							if (delay.contains(p.getPlayer().getName())) {
								sendActionbarMessage(p, "§cRecarregando picareta para explosão!");
								return;
							}
							p.getWorld().createExplosion(loc, 0);
							int radius2 = 4 - 2;
							p.getWorld().createExplosion(loc, 0.0F);
							double minX = X - radius2;
							double maxX = X + radius2 + 1.0D;
							double minY = Y - radius2;
							double maxY = Y + radius2 + 1.0D;
							double minZ = Z - radius2;
							double maxZ = Z + radius2 + 1.0D;
							for (double x = minX; x < maxX; x += 1.0D) {
								for (double y = minY; y < maxY; y += 1.0D) {
									for (double z = minZ; z < maxZ; z += 1.0D) {
										Location location = new Location(loc.getWorld(), x, y, z);
										if (location.getBlock().getType() == Material.STONE) {
											location.getBlock().breakNaturally();
											delay.add(p.getPlayer().getName());
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance,
													new Runnable() {

														@Override
														public void run() {
															delay.remove(p.getPlayer().getName());
															p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10, 3);
															sendActionbarMessage(p, "§aPicareta recarregada!");
														}
													}, 20 * 3);
											return;
										}
									}
								}
							}

						}
					}
					if (item.hasItemMeta() && item.hasItemMeta() && item.getItemMeta().hasDisplayName()
							&& item.getItemMeta().getDisplayName().equals("§4PICARETA EXPLOSIVA III")) {
						int block = e.getBlock().getTypeId();
						Location loc = e.getBlock().getLocation();
						double X = loc.getBlockX();
						double Y = loc.getBlockY();
						double Z = loc.getBlockZ();
						if (block == 1) {
							if (delay.contains(p.getPlayer().getName())) {
								sendActionbarMessage(p, "§cRecarregando picareta para explosão!");
								return;
							}
							p.getWorld().createExplosion(loc, 0);
							int radius3 = 5 - 2;
							p.getWorld().createExplosion(loc, 0.0F);
							double minX = X - radius3;
							double maxX = X + radius3 + 1.0D;
							double minY = Y - radius3;
							double maxY = Y + radius3 + 1.0D;
							double minZ = Z - radius3;
							double maxZ = Z + radius3 + 1.0D;
							for (double x = minX; x < maxX; x += 1.0D) {
								for (double y = minY; y < maxY; y += 1.0D) {
									for (double z = minZ; z < maxZ; z += 1.0D) {
										Location location = new Location(loc.getWorld(), x, y, z);
										if (location.getBlock().getType() == Material.STONE) {
											location.getBlock().breakNaturally();
											delay.add(p.getPlayer().getName());
											Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance,
													new Runnable() {

														@Override
														public void run() {
															delay.remove(p.getPlayer().getName());
															p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10, 3);
															sendActionbarMessage(p, "§aPicareta recarregada!");
														}
													}, 20 * 6);
											return;
										}
									}
								}
							}

						}
					}
				}
			}else{
				System.out.println("nenhum dos dois");
				sendActionbarMessage(p, "§4Não é possível explodir fora das minas!");
				return;
			}
		}
	}

 * 
 * 	
 */
	
	
	
	

	
	// public boolean isInAABB(Vector min, Vector max) {
	// return 5 >= min.x && x <= max.x && y >= min.y && y <= max.y && z >= min.z
	// && z <= max.z;
	// }

	@SuppressWarnings("unused")
	private boolean inArea(Player player, Location minLoc, Location maxLoc) {

		// Verifica se os mundos sao iguais
		if ((player.getWorld() != minLoc.getWorld()) || (player.getWorld() != maxLoc.getWorld()))
			return false;

		final Location playerLocation = player.getLocation();

		if (playerLocation.getBlockX() >= minLoc.getBlockX() && playerLocation.getBlockX() <= maxLoc.getBlockX()) {
			if (playerLocation.getBlockZ() >= minLoc.getBlockZ() && playerLocation.getBlockZ() <= maxLoc.getBlockZ()) {
				return true;
			}
		}

		return false;
	}

}
