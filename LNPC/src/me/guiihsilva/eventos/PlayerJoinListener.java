package me.guiihsilva.eventos;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import me.guiihsilva.Main;
import me.guiihsilva.commands.score;
import me.guiihsilva.commands.score.ScoreBoardToggle;
import me.guiihsilva.utils.UtilBlockText;
import me.guiihsilva.utils.UtilBlockText.TextAlign;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerJoinListener implements Listener {
	private JavaPlugin _plugin;


	@EventHandler
	public void aoSair2(PlayerQuitEvent e){

		UtilBlockText.MakeText("Jogadores:", new Location(Bukkit.getWorld("world"), 0, 99, -52), BlockFace.EAST, 159, (byte) 1, TextAlign.CENTRO, true, false);
		UtilBlockText.MakeText(getPlayersSize() + "", new Location(Bukkit.getWorld("world"), 0, 92, -52), BlockFace.EAST, 159, (byte) 0, TextAlign.CENTRO, true, false);
		
	}
	
	@EventHandler
	public void aoEntrar(PlayerJoinEvent event) {
		PermissionUser user = PermissionsEx.getUser(event.getPlayer());
		if (event.getPlayer().getName().equalsIgnoreCase("Regina")
				|| event.getPlayer().getName().equalsIgnoreCase("FilhoDoScop")
				|| event.getPlayer().getName().equalsIgnoreCase("Abacate69")) {
			event.getPlayer().kickPlayer("disconnect");
			return;
		}
		final Player p = event.getPlayer();
		new BukkitRunnable() {
			public void run() {
				for (int i = 150; i > 0; i--) {
					p.sendMessage(" ");
				}
				int player_qnt = 0;
				for (Player ponline : Bukkit.getOnlinePlayers()) {
					player_qnt = player_qnt + 1;
					if (ponline.hasPermission("onlines.soustaff")) {
						player_qnt = player_qnt - 1;
					}
				}
				p.sendMessage("§6§m------------------§r §e§lLegendMania §6§m------------------");
				p.sendMessage(" §8- §7Seja bem vindo ao §oLegendMania§r§f " + p.getName() + "§e!");
				p.sendMessage(" §8- §7Atualmente temos §f§o" + player_qnt + "§r§7 jogadores onlines!");
				p.sendMessage(" §8- §7Se precisar de ajuda use §f/ajuda§7!");
				p.sendMessage(" §8- §7Lhe desejamos um bom jogo!");
				p.sendMessage("§6§m------------------§r §e§lLegendMania §6§m ------------------");
				UtilBlockText.MakeText("Jogadores:", new Location(Bukkit.getWorld("world"), 0, 99, -52), BlockFace.EAST, 159, (byte) 1, TextAlign.CENTRO, true, false);
				UtilBlockText.MakeText(getPlayersSize() + "", new Location(Bukkit.getWorld("world"), 0, 92, -52), BlockFace.EAST, 159, (byte) 0, TextAlign.CENTRO, true, false);
				score.score.put(p.getName(), ScoreBoardToggle.ON);
			}
		}.runTaskLater(this._plugin, 5L);

		new BukkitRunnable() {

			@SuppressWarnings("unused")
			private JavaPlugin _plugin;
			File file = new File(Main.instance.getDataFolder() + File.separator + "config.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);

			@Override
			public void run() {
				if (config.getBoolean("manutencao") == true) {
					if (p.hasPermission("manutencao.bypass")) {
						p.sendMessage("§4§m------------------§r §c§lManutenção §4§m------------------");
						p.sendMessage(" §7- §cO servidor atualmente esta com o modo manutenção ativo!");
						p.sendMessage(" §7- §cPara que os jogadores entrem, desative o modo manutenção!");
						p.sendMessage("§4§m------------------§r §c§lManutenção §4§m------------------");
						p.playSound(p.getLocation(), Sound.GHAST_MOAN, 10, 1);
						return;

					}
				}
			}
		}.runTaskLater(this._plugin, 10L);
		new BukkitRunnable() {
			public void run() {
				if ((p.hasPotionEffect(PotionEffectType.INVISIBILITY)) && (p.hasPermission("essentials.vanish"))) {
					p.removePotionEffect(PotionEffectType.INVISIBILITY);
					p.sendMessage(" ");
					p.sendMessage(" §4* §cVocê esta no modo vanish!");
					p.sendMessage(" ");
					p.playSound(p.getLocation(), Sound.SLIME_ATTACK, 1.0F, 3.0F);
				}
			}
		}.runTaskLater(this._plugin, 50L);
		new BukkitRunnable() {
			public void run() {
				UUID pUUID = p.getUniqueId();
				if (Main.instance.getConfig().getString("users." + pUUID) == null) {
					p.sendMessage(" ");
					p.sendMessage(" §b* §7Você não possui nenhum Skype cadastrado!");
					p.sendMessage(" §b* §7Use /skype add [seu skype]");
					p.sendMessage(" §b* §7Isto pode ser exetremamente útil futuramente!");
					p.sendMessage(" ");
					p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10, 1);
				}
			}
		}.runTaskLater(this._plugin, 20L * 30L);
		String prefix = user.getPrefix();
		String suffix = user.getSuffix();
		ItemMeta adminitemmeta;
		if ((user.inGroup("mod")) || (user.inGroup("admin")) || (user.inGroup("subdono")) || (user.inGroup("dono"))) {
			ItemStack adminitem = new ItemStack(Material.BLAZE_ROD, 1);
			adminitemmeta = adminitem.getItemMeta();
			adminitemmeta.setDisplayName("§aMenu moderação §7(Clique direito)");
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(" ");
			lore.add(" §7Use isso sempre que precisar");
			lore.add(" §7moderar o servidor!");
			lore.add(" ");
			lore.add(" §7Seu cargo: " + prefix.replaceAll("&", "§"));
			lore.add(" §7Seu nick: §f" + event.getPlayer().getDisplayName());
			adminitemmeta.setLore(lore);
			adminitem.setItemMeta(adminitemmeta);
			event.getPlayer().getInventory().setItem(4, adminitem);
		}
		if ((user.inGroup("vip")) || (user.inGroup("vip+")) || (user.inGroup("vipultimate"))) {
			event.setJoinMessage(null);
			Bukkit.broadcastMessage(" §eUm§6 " + suffix.replace("&", "§") + "§econectou-se: §f"
					+ prefix.replaceAll("&", "§") + p.getName());
			for (Player todosplayers : Bukkit.getOnlinePlayers()) {
				todosplayers.playSound(todosplayers.getLocation(), Sound.NOTE_PIANO, 1.0F, 5.0F);
			}
		}
		if ((user.inGroup("yt")) || (user.inGroup("ytbasico"))) {
			event.setJoinMessage(null);
			Bukkit.broadcastMessage(" §eUm§c " + suffix.replaceAll("&", "§") + "§econectou-se: §f"
					+ prefix.replaceAll("&", "§") + p.getName());
			for (Player todosplayers : Bukkit.getOnlinePlayers()) {
				todosplayers.playSound(todosplayers.getLocation(), Sound.NOTE_PIANO, 1.0F, 5.0F);
			}
		} else {
			event.setJoinMessage(null);
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 5.0F);
		}
	}

	private static int getPlayersSize(){
		int i = Bukkit.getOnlinePlayers().size();
		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (ps.hasPermission("onlines.soustaff")) {
				i--;
			}
		}
		return i;
	}
	
	@EventHandler
	public void aoSair(PlayerQuitEvent e) {
		e.setQuitMessage(null);

		PermissionUser user = PermissionsEx.getUser(e.getPlayer());
		String prefix = user.getPrefix();
		String suffix = user.getSuffix();
		Player p = e.getPlayer();
		if ((user.inGroup("vip")) || (user.inGroup("vip+")) || (user.inGroup("vipultimate"))) {
			Bukkit.broadcastMessage(" §eUm §6" + suffix.replace("&", "§") + "§edesconectou-se: §f"
					+ prefix.replaceAll("&", "§") + p.getName());
			for (Player todosplayers : Bukkit.getOnlinePlayers()) {
				todosplayers.playSound(todosplayers.getLocation(), Sound.FIRE_IGNITE, 1.0F, 7.0F);
			}
		}
		if ((user.inGroup("yt")) || (user.inGroup("ytbasico"))) {
			e.setQuitMessage(null);
			Bukkit.broadcastMessage(" §eUm §c" + suffix.replaceAll("&", "§") + "§edesconectou-se: §f"
					+ prefix.replaceAll("&", "§") + p.getName());
			for (Player todosplayers : Bukkit.getOnlinePlayers()) {
				todosplayers.playSound(todosplayers.getLocation(), Sound.FIRE_IGNITE, 1.0F, 5.0F);
			}
		}
	}

	public PlayerJoinListener(JavaPlugin plugin) {
		this._plugin = plugin;

		this._plugin.getServer().getPluginManager().registerEvents(this, this._plugin);
	}

	@EventHandler
	public void onconect(PlayerJoinEvent e) {
		File file = new File(Main.instance.getDataFolder() + File.separator + "config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		if (config.getBoolean("manutencao") == true) {
			if (!e.getPlayer().hasPermission("manutencao.entrar")){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					@Override
					public void run() {
						e.getPlayer().sendMessage("§4§m------------------- §cMANUTENCAO §4§m-------------------");
						e.getPlayer().sendMessage(" §4*§c Atenção, o servidor esta com o modo manutenção ativo, para controlar use /manu <on/off>");
						e.getPlayer().sendMessage("§4§m------------------- §cMANUTENCAO §4§m-------------------");
						return;
					}
				}, 20*5);
			}
			e.getPlayer().kickPlayer("§cManutenção!\nEstamos trabalhando para aprimorar o servidor, volte mais tarde!");

		}

	}
}
