package me.guiihsilva.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import me.guiihsilva.Main;
import me.guiihsilva.cabecas.manager.Manager;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;;

public class PlaceholderAPIFB {
	public static final String init = null;

	public static void init() {
		PlaceholderAPI.registerPlaceholder(Main.instance, "lm_jogadores", new PlaceholderReplacer() {
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				int i = Bukkit.getOnlinePlayers().size();
				for (Player ps : Bukkit.getOnlinePlayers()) {
					if (ps.hasPermission("onlines.soustaff")) {
						i--;
					}
				}
				String i2 = i + "";
				return i2;
			}
		});

		PlaceholderAPI.registerPlaceholder(Main.instance, "lm_cabecas", new PlaceholderReplacer() {
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player player = e.getPlayer();
				if (player == null) {
					return "§cErro...";
				}
				int cabecas = Manager.config.getInt("players." + player.getUniqueId() + ".quantidade");
				String i2 = cabecas + "";
				return i2;
			}
		});
		PlaceholderAPI.registerPlaceholder(Main.instance, "lm_ping", new PlaceholderReplacer() {
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player player = e.getPlayer();
				if (player == null) {
					return "§cErro...";
				}
				int ping = ((CraftPlayer) player).getHandle().ping;
				String i2 = ping + "";
				return i2;
			}
		});
		PlaceholderAPI.registerPlaceholder(Main.instance, "lm_clantag", new PlaceholderReplacer() {
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player player = e.getPlayer();
				if (player == null) {
					return "§cErro...";
				}
				ClanPlayer cp = Main.core.getClanManager().getClanPlayer(player);
				if (cp != null) {
					return cp.getTagLabel();
				} else {
					return "Nenhum";
				}
			}
		});
		PlaceholderAPI.registerPlaceholder(Main.instance, "lm_kdr", new PlaceholderReplacer() {
			@SuppressWarnings("unused")
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player player = e.getPlayer();
				if (player == null) {
					return "§cErro...";
				}
				ClanPlayer cp = Main.core.getClanManager().getClanPlayer(player);
				if (cp != null) {
					return "§6» §eKDR: §f" + cp.getKDR();
				} else if (cp == null) {
					int ping = ((CraftPlayer) player).getHandle().ping;
					return "§6» §ePing: §f" + ping + "ms";
				}
				return null;
			}
		});
		PlaceholderAPI.registerPlaceholder(Main.instance, "lm_kill", new PlaceholderReplacer() {
			@SuppressWarnings("unused")
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player player = e.getPlayer();
				if (player == null) {
					return "§cErro...";
				}
				ClanPlayer cp = Main.core.getClanManager().getClanPlayer(player);
				if (cp != null) {
					int kills = cp.getCivilianKills() + cp.getNeutralKills() + cp.getRivalKills();
					return "§6» §eKills: §f" + kills;
				} else if (cp == null) {
					int ping = ((CraftPlayer) player).getHandle().ping;
					return "§6» §ePing: §f" + ping + "ms";
				}
				return null;
			}
		});
		PlaceholderAPI.registerPlaceholder(Main.instance, "lm_deaths", new PlaceholderReplacer() {
			@SuppressWarnings("unused")
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player player = e.getPlayer();
				if (player == null) {
					return "§cErro...";
				}
				ClanPlayer cp = Main.core.getClanManager().getClanPlayer(player);
				if (cp != null) {
					return "§6» §eMortes: §f" + cp.getDeaths();
				} else if (cp == null) {
					int ping = ((CraftPlayer) player).getHandle().ping;
					return "§6» §ePing: §f" + ping + "ms";
				}
				return null;
			}
		});
		

		PlaceholderAPI.registerPlaceholder(Main.instance, "lm_cash", new PlaceholderReplacer() {
			@Override
			public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
				Player player = e.getPlayer();
				if (player == null) {
					return "§cErro...";
				}
				String cash = me.guiihsilva.cash.cash.getCash(player);
				if (cash.equalsIgnoreCase("Jogador não encontrado no banco de dados!")){
					return "§cErro...";
				}else{
					return cash;
				}
			}
		});
	}

}
