package me.guiihsilva.eventos;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import io.puharesource.mc.titlemanager.api.TitleObject;
import me.guiihsilva.Main;
import me.guiihsilva.utils.FormarNumber;
import me.guiihsilva.utils.Title;
import net.milkbowl.vault.economy.EconomyResponse;

public class reparar implements Listener {

	void title(Player player, String title, int fadeIn, int stay, int fadeOut) {
		new TitleObject(title, TitleObject.TitleType.TITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut)
				.send(player);
	}

	void sendActionbarMessage(Player player, String message) {
		new ActionbarTitleObject(message).send(player);
	}

	@EventHandler
	public void signCreate(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (e.getLine(0).equalsIgnoreCase("[reparar]")) {
			if (!p.hasPermission("reparar.placa")) {
				p.sendMessage("§cSem permissão!");
				e.getBlock().breakNaturally();
				return;
			} else {
				e.setLine(0, "§2«Reparar»");
				e.setLine(1, " ");
				e.setLine(2, "Clique aqui");
				e.setLine(3, "parar reparar");
			}
		}
	}

	@EventHandler
	public void signBreak(BlockBreakEvent e) {
		if (e.getBlock().getType() == Material.SIGN || e.getBlock().getType() == Material.SIGN_POST
				|| e.getBlock().getType() == Material.WALL_SIGN) {
			Sign sign = (Sign) e.getBlock().getState();
			String line0 = sign.getLine(0);
			String line1 = sign.getLine(1);
			String line2 = sign.getLine(2);
			String line3 = sign.getLine(3);
			if (line0.equalsIgnoreCase("§2«Reparar»") || line1.equalsIgnoreCase(" ")
					|| line2.equalsIgnoreCase("Clique aqui") || line3.equalsIgnoreCase("parar reparar")) {
				if (!e.getPlayer().hasPermission("reparar.placa")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage("§cSem permissão!");
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void signInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Block block = e.getClickedBlock();
		Action action = e.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK || action == Action.LEFT_CLICK_BLOCK) {
			if (block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST
					|| block.getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) block.getState();
				String line0 = sign.getLine(0);
				String line1 = sign.getLine(1);
				String line2 = sign.getLine(2);
				String line3 = sign.getLine(3);
				if (line0.equalsIgnoreCase("§2«Reparar»") || line1.equalsIgnoreCase(" ")
						|| line2.equalsIgnoreCase("Clique aqui") || line3.equalsIgnoreCase("parar reparar")) {
					if (p.getItemInHand().getType() == Material.AIR) {
						p.sendMessage("§cVocê deve segurar o item na mão!");
						return;
					} else {
						if (p.getItemInHand().getType() == Material.GOLDEN_APPLE
								|| p.getItemInHand().getType() == Material.STONE
								|| p.getItemInHand().getType() == Material.STONE
								|| p.getItemInHand().getType() == Material.WOOD
								|| p.getItemInHand().getType() == Material.BRICK
								|| p.getItemInHand().getType() == Material.GLASS
								|| p.getItemInHand().getType() == Material.POTION
								|| p.getItemInHand().getType() == Material.STAINED_CLAY
								|| p.getItemInHand().getType() == Material.STAINED_GLASS
								|| p.getItemInHand().getType() == Material.STAINED_GLASS_PANE
								|| p.getItemInHand().getType() == Material.MONSTER_EGG
								|| p.getItemInHand().getType() == Material.MONSTER_EGGS
								|| p.getItemInHand().getType() == Material.CARPET
								|| p.getItemInHand().getType() == Material.BANNER
								|| p.getItemInHand().getType() == Material.STANDING_BANNER
								|| p.getItemInHand().getType() == Material.WALL_BANNER
								|| p.getItemInHand().getType() == Material.LONG_GRASS
								|| p.getItemInHand().getType() == Material.DIRT
								|| p.getItemInHand().getType() == Material.PRISMARINE
								|| p.getItemInHand().getType() == Material.ENCHANTED_BOOK
								|| p.getItemInHand().getType() == Material.MOB_SPAWNER
								|| p.getItemInHand().getType() == Material.LOG
								|| p.getItemInHand().getType() == Material.LOG_2
								|| p.getItemInHand().getType() == Material.GLOWING_REDSTONE_ORE
								|| p.getItemInHand().getType() == Material.REDSTONE_TORCH_ON
								|| p.getItemInHand().getType() == Material.SKULL
								|| p.getItemInHand().getType() == Material.SKULL_ITEM
								|| p.getItemInHand().getType() == Material.YELLOW_FLOWER
								|| p.getItemInHand().getType() == Material.FLOWER_POT_ITEM
								|| p.getItemInHand().getType() == Material.FLOWER_POT
								|| p.getItemInHand().getType() == Material.CACTUS
								|| p.getItemInHand().getType() == Material.SAPLING
								|| p.getItemInHand().getType() == Material.LEAVES
								|| p.getItemInHand().getType() == Material.LEAVES_2
								|| p.getItemInHand().getTypeId() == 351
								|| p.getItemInHand().getTypeId() == 349
								|| p.getItemInHand().getTypeId() == 179
								|| p.getItemInHand().getTypeId() == 98
								|| p.getItemInHand().getTypeId() == 44
								|| p.getItemInHand().getTypeId() == 145
								|| p.getItemInHand().getTypeId() == 155
								|| p.getItemInHand().getTypeId() == 175
								|| p.getItemInHand().getTypeId() == 38
								|| p.getItemInHand().getTypeId() == 52
								|| p.getItemInHand().getTypeId() == 19
								|| p.getItemInHand().getTypeId() == 126
								|| p.getItemInHand().getTypeId() == 139) {
							p.sendMessage(
									"§cItem não aceito para reparar, se acredita que isto é um erro, avise a staff!");
							return;
						}
						try {
							if (p.getItemInHand().getDurability() == 0) {
								p.sendMessage("§cEste item não precisa ser reparado!");
								return;
							} else {
								int durabilidade = p.getItemInHand().getDurability();
								durabilidade = durabilidade * 20;
								int valor = durabilidade / 2;
								if (p.hasPermission("reparar.desconto")) {
									int desconto = valor * 10;
									desconto = desconto / 100;
									valor = valor - desconto;
									try {
										Title t = new Title("§c§l-§e§l10%", "§aVocê tem direito a descontos!", 0, 2, 1);
										t.send(p);
									} catch (Exception e1) {
										p.sendMessage("§aVocê teve &e10% &ade desconto! Você possui este direito!");
									}
								}
								EconomyResponse r = Main.econ.withdrawPlayer(p, valor);
								if (r.transactionSuccess()) {
									p.sendMessage("§aReparação efetuada com sucesso! Custo: §2$§f" + FormarNumber.formatValue(valor));
									p.playSound(p.getLocation(), Sound.ORB_PICKUP, 10, 1);
									p.getItemInHand().setDurability((short) 0);
									return;
								} else {
									p.sendMessage("§cDinheiro insuficiente! Este item custa: §2$§f" + FormarNumber.formatValue(valor));
									return;
								}
							}
						} catch (Exception i) {
							p.sendMessage("§cNão é possível reparar este item!");
						}
					}
				}
			}
		}
	}

}
