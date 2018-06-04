package me.guiihsilva.admin;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.earth2me.essentials.Essentials;

import io.puharesource.mc.titlemanager.api.TitleObject;
import me.guiihsilva.Main;
import me.guiihsilva.utils.ActionBarUtil;
import me.guiihsilva.utils.FormarNumber;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import org.bukkit.inventory.ItemStack;

public class admin implements Listener, CommandExecutor {
	public static ArrayList<String> admin = new ArrayList<>();
	public static HashMap<String, ItemStack[]> saveinv = new HashMap<String, ItemStack[]>();
	Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
	@SuppressWarnings("unused")
	private EntityType Player;
	public static HashMap<String, ItemStack[]> savearmor = new HashMap<String, ItemStack[]>();
	public static HashMap<Player, Integer> tasktrocarapida = new HashMap<>();

	void title(Player player, String title, int fadeIn, int stay, int fadeOut) {
		new TitleObject(title, TitleObject.TitleType.TITLE).setFadeIn(fadeIn).setStay(stay).setFadeOut(fadeOut)
				.send(player);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cApenas jogadores in-game podem usar esse comando!");
			return true;
		}
		if (!(sender.hasPermission("admin.usar"))) {
			sender.sendMessage("§4§lERRO: §cComando desconhecido!");
			return true;
		}
		Player p = (Player) sender;
		if (args.length == 0) {
			if (!admin.contains(p.getName())) {
				title(p, "§aATIVADO", 5, 10, 5);
				p.sendMessage(" ");
				p.sendMessage(" §7Modo admin: §aATIVADO!");
				p.sendMessage(" ");
				p.playSound(p.getLocation(), Sound.LAVA_POP, 10, 5);
				this.ess.getUser(p).setVanished(true);
				p.removePotionEffect(PotionEffectType.INVISIBILITY);
				for (Player s : Bukkit.getOnlinePlayers()) {
					// Aq alguma coisa para todos os jogadores!
					if (!s.hasPermission("Admin.use")) {
						s.hidePlayer(p);
					}
				}
				admin.add(p.getName());
				saveinv.put(p.getName(), p.getInventory().getContents());
				savearmor.put(p.getName(), p.getInventory().getArmorContents());
				p.setGameMode(GameMode.CREATIVE);
				p.setFlying(true);
				p.getInventory().clear();

				ItemStack info = new ItemStack(Material.PAPER);
				ItemMeta infometa = info.getItemMeta();
				infometa.setDisplayName("§aInformações de um player §7(Clique em alguém)");
				info.setItemMeta(infometa);

				ItemStack redstone = new ItemStack(Material.REDSTONE);
				ItemMeta redstonemeta = redstone.getItemMeta();
				redstonemeta.setDisplayName("§cSair do modo admin §7(Clique direito)");
				redstone.setItemMeta(redstonemeta);

				ItemStack knock = new ItemStack(Material.STICK);
				ItemMeta knockmeta = info.getItemMeta();
				knockmeta.addEnchant(Enchantment.KNOCKBACK, 3, true);
				knockmeta.setDisplayName("§aTestar knockback §7(Hit em alguém)");
				knock.setItemMeta(knockmeta);

				ItemStack cage = new ItemStack(Material.BARRIER);
				ItemMeta cagem = info.getItemMeta();
				cagem.addEnchant(Enchantment.KNOCKBACK, 3, true);
				cagem.setDisplayName("§aCage §7(Clique em alguém)");
				cage.setItemMeta(cagem);

				// ItemStack ff = new ItemStack(Material.SKULL_ITEM, 1, (short)
				// 3);
				ItemStack ff = new ItemStack(Material.ARROW);
				ItemMeta ffmeta = info.getItemMeta();
				ffmeta.setDisplayName("§aTeste FF/KillAura §7(Clique direito)");
				ff.setItemMeta(ffmeta);

				ItemStack tr = new ItemStack(Material.MAGMA_CREAM);
				ItemMeta trmeta = tr.getItemMeta();
				trmeta.setDisplayName("§aTroca Rápida §7(Clique direito)");
				tr.setItemMeta(trmeta);

				PermissionUser user = PermissionsEx.getUser(p.getName());
				String prefix = user.getPrefix();
				ItemStack adminitem = new ItemStack(Material.BLAZE_ROD, 1);
				ItemMeta adminitemmeta = adminitem.getItemMeta();
				adminitemmeta.setDisplayName("§aMenu moderação §7(Clique direito)");
				ArrayList<String> lore = new ArrayList<>();
				lore.add(" ");
				lore.add(" §7Use isso sempre que precisar");
				lore.add(" §7moderar o servidor!");
				lore.add(" ");
				lore.add(" §7Seu cargo: " + prefix.replaceAll("&", "§"));
				lore.add(" §7Seu nick: §f" + p.getDisplayName());
				adminitemmeta.setLore(lore);
				adminitem.setItemMeta(adminitemmeta);

				p.getInventory().setItem(8, redstone);
				p.getInventory().setItem(2, info);
				p.getInventory().setItem(1, knock);
				p.getInventory().setItem(3, cage);
				p.getInventory().setItem(5, tr);
				p.getInventory().setItem(4, adminitem);
				p.updateInventory();
			} else {
				admin.remove(p.getName());
				title(p, "§cDESATIVADO", 5, 10, 5);
				p.sendMessage(" ");
				p.sendMessage(" §7Modo admin: §cDESATIVADO!");
				p.sendMessage(" ");
				p.playSound(p.getLocation(), Sound.LAVA_POP, 10, 6);
				p.getInventory().clear();
				p.setGameMode(GameMode.SURVIVAL);
				p.setAllowFlight(true);
				p.setFlying(true);
				p.removePotionEffect(PotionEffectType.INVISIBILITY);
				ItemStack adminitem = new ItemStack(Material.BLAZE_ROD, 1);
				ItemMeta adminitemmeta = adminitem.getItemMeta();
				adminitemmeta.setDisplayName("§aMenu moderação §7(Clique direito)");
				ArrayList<String> lore = new ArrayList<>();
				PermissionUser user = PermissionsEx.getUser(p);
				String prefix = user.getPrefix();
				lore.add(" ");
				lore.add(" §7Use isso sempre que precisar");
				lore.add(" §7moderar o servidor!");
				lore.add(" ");
				lore.add(" §7Seu cargo: " + prefix.replaceAll("&", "§"));
				lore.add(" §7Seu nick: §f" + p.getPlayer().getDisplayName());
				adminitemmeta.setLore(lore);
				adminitem.setItemMeta(adminitemmeta);

				for (Player s : Bukkit.getOnlinePlayers()) {
					// Aq alguma coisa para todos os jogadores!
					if (!s.hasPermission("Admin.use")) {
						s.showPlayer(p);
					}
				}
				p.getInventory().setContents(saveinv.get(p.getName()));
				p.getInventory().setArmorContents(savearmor.get(p.getName()));
				p.getInventory().setItem(4, adminitem);
			}
		}
		return false;
	}

	@EventHandler
	public void iredstone(PlayerInteractEvent e) {
		if (e.getMaterial() == Material.REDSTONE && admin.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
			e.getPlayer().chat("/admin");
			e.getPlayer().updateInventory();

		}
	}

	@EventHandler
	public void trocarapida(PlayerInteractEvent e) {
		if (e.getMaterial() == Material.MAGMA_CREAM && admin.contains(e.getPlayer().getName())) {

			@SuppressWarnings("unused")
			Essentials essi = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
			Player p = e.getPlayer();
			System.out.println(p.getDisplayName() + " iniciou um traca rapida do modo admin");
			p.sendMessage("§aEfetuando: Troca Rápida");
			p.chat("/admin");

			this.ess.getUser(p).setVanished(false);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {

				@Override
				public void run() {
					p.chat("/admin");
					setvanishedtrue(p);
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 15, 10));
					p.sendMessage("§aTroca rápida concluída!");
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
				}
			}, 10);
		}
	}

	public void setvanishedtrue(Player p) {

		this.ess.getUser(p).setVanished(true);
	}

	@EventHandler
	public void vereificarff(PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player && e.getPlayer().getItemInHand().getType() == Material.ARROW
				&& admin.contains(e.getPlayer().getName())) {
			Player p = e.getPlayer();
			Player t = (Player) e.getRightClicked();
			p.chat("/ff " + t.getName());
		}
	}

	@EventHandler
	public void cage(PlayerInteractEntityEvent e) {
		if (e.getPlayer().getItemInHand().getType() == Material.BARRIER && admin.contains(e.getPlayer().getName())) {
			if (!(e.getRightClicked() instanceof Player)) {
				e.getPlayer().sendMessage("§cVocê deve clicar em um jogador!");
				return;
			}
			Player p = e.getPlayer();
			Player t = (Player) e.getRightClicked();
			t.getLocation().add(0.0D, 13.0D, 0.0D).getBlock().setType(Material.BARRIER);
			t.getLocation().add(0.0D, 11.0D, 1.0D).getBlock().setType(Material.BARRIER);
			t.getLocation().add(1.0D, 11.0D, 0.0D).getBlock().setType(Material.BARRIER);
			t.getLocation().add(0.0D, 11.0D, -1.0D).getBlock().setType(Material.BARRIER);
			t.getLocation().add(-1.0D, 11.0D, 0.0D).getBlock().setType(Material.BARRIER);
			t.getLocation().add(0.0D, 10.0D, 0.0D).getBlock().setType(Material.BEDROCK);
			t.teleport(t.getLocation().add(0.0D, 11.0D, -0.05D));
			//Location tp = new Location(loc.getWorld(), Double.parseDouble(loc.getBlockX() + 0.5D + ""),loc.getBlockX() + 11, Double.parseDouble(loc.getBlockZ()+ 0.5D + ""));
			//t.teleport(tp);
			p.sendMessage("§aJogador prendido numa cage: §f" + t.getName());
			return;
		} else {
			return;
		}
	}

	@EventHandler
	public void iinfo(PlayerInteractEntityEvent e) {
		if (e.getPlayer().getItemInHand().getType() == Material.PAPER && admin.contains(e.getPlayer().getName())) {
			if (!(e.getRightClicked() instanceof Player)) {
				e.getPlayer().sendMessage("§cVocê deve clicar em um jogador!");
				return;
			}
			Player p = e.getPlayer();
			Player t = (Player) e.getRightClicked();
			PermissionUser user = PermissionsEx.getUser(t);
			String prefix = user.getPrefix();
			p.playSound(p.getLocation(), Sound.WOOD_CLICK, 10, 5);
			p.sendMessage(" ");
			p.sendMessage(" §aInformações de: §f" + t.getDisplayName());
			p.sendMessage(" §8- §7Rank: §f" + prefix.replaceAll("&", "§"));
			p.sendMessage(" §8- §7Vida: §f" + t.getHealth() + "§8/§7" + t.getMaxHealth());
			p.sendMessage(" §8- §7Fome: §f" + t.getFoodLevel());
			p.sendMessage(" §8- §7IP: §f" + t.getAddress().getAddress() );
			p.sendMessage(" §8- §7Money: §f"
					+ FormarNumber.formatValue(Main.econ.getBalance((OfflinePlayer) e.getRightClicked())));
			return;
		} else {
			return;
		}
	}

	@EventHandler
	public void openinventory(PlayerInteractEntityEvent e) {
		if (admin.contains(e.getPlayer().getName()) && e.getPlayer().getItemInHand().getType() == Material.AIR) {
			if (!(e.getRightClicked() instanceof Player)) {
				e.getPlayer().sendMessage("§cEsta entidade não possui inventario!");
				return;
			}
			Player t = (Player) e.getRightClicked();
			e.getPlayer().openInventory(t.getInventory());

		}
	}

	@EventHandler
	public void dontdrop(PlayerPickupItemEvent e) {
		if (admin.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void dontdroptheadminstick(PlayerDropItemEvent e) {
		if (e.getItemDrop().getCustomName() == "§aMenu moderação §7(Clique direito)") {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§cVocê não pode dropar este item!");
		}

	}

	@EventHandler
	public void donatstaffhit(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		Player s = (Player) e.getEntity();
		if (s.hasPermission("staff.naohitar")) {
			if (p.hasPermission("staff.naohitar")) {
				return;
			}
			e.setCancelled(true);
			p.sendMessage("§cJogadores da staff não podem batalhar!");
			ActionBarUtil.sendActionBar("§cO jogador §7" + p.getName() + "§c tentou te hitar!", s);
			p.setVelocity(p.getLocation().getDirection().multiply(3));
			p.setVelocity(new Vector(p.getVelocity().getZ(), 0.2, p.getVelocity().getX()));
			p.playSound(p.getLocation(), Sound.NOTE_BASS, 10, 1);
		}
	}
}
