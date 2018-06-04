package me.guiihsilva.scoreboard;

//import org.bukkit.Bukkit;
//import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
//import me.guiihsilva.Main;
//import me.guiihsilva.cabecas.manager.Manager;
//import me.guiihsilva.utils.Lag;
//import me.guiihsilva.utils.scoreboardutil;
//import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
//import ru.tehkode.permissions.PermissionUser;
//import ru.tehkode.permissions.bukkit.PermissionsEx;

public class scoreboard extends PlaceholderAPI {

	private static int pagina;

	public static void updatepage() {
		pagina = pagina + 1;
		if (pagina == 4) {
			pagina = 0;
		}

//		double tps = Lag.getTPS();
//		double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
//		Bukkit.broadcastMessage("O servidor esta com " + lag + "% de lag!");
	}

	public static void setScore(Player p) {
		return ;
		/*
		 * if (Lag.getTPS() < 14.0D) {
			return;
		}
		scoreboardutil scoreboard = new scoreboardutil("§6§lLegend§e§lMania §7(BETA)");

		String replacer = "%ezrankspro_progressbar%";
		String progressbar = PlaceholderAPI.setPlaceholders(p, replacer);

		String replacer2 = "%ezrankspro_balance_formatted%";
		String saldo = PlaceholderAPI.setPlaceholders(p, replacer2);

		PermissionUser user = PermissionsEx.getUser(p);
		String prefix = user.getPrefix().replaceAll("§", "§");

		int cabecas = Manager.config.getInt("players." + p.getUniqueId() + ".quantidade");

		int i = Bukkit.getOnlinePlayers().size();
		for (Player ps : Bukkit.getOnlinePlayers()) {
			if (ps.hasPermission("onlines.soustaff")) {
				i--;
			}
		}

		scoreboard.add("§c ");
		scoreboard.add("§6» §eSaldo: §f" + saldo);
		scoreboard.add("§6» §eCabeças: §f" + cabecas);
		scoreboard.add("§3 ");
		scoreboard.add("§6» §eRank: §f" + prefix.replaceAll("&", "§"));
		scoreboard.add("§6» §eProgresso: §f" + progressbar);
		scoreboard.add("§8 ");
		scoreboard.add("§6» §eJogadores: §f" + i);
		if (pagina == 1) {
			if (Lag.getTPS() < 15.0D) {
				return;
			}
			if (Main.core != null) {
				ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
				if (cp != null) {
					int kills = 0;
					kills = kills + cp.getCivilianKills() + cp.getNeutralKills() + cp.getRivalKills();
					scoreboard.add("§6» §eKills: §f" + kills);
				} else {
					int ping = ((CraftPlayer) p).getHandle().ping;
					scoreboard.add("§6» §ePing: §f" + ping + "ms");
				}
			} else {
				scoreboard.add("§6» §eKills: &cErro interno!");
			}
		}
		if (pagina == 0) {
			if (Lag.getTPS() < 15.0D) {
				return;
			}
			ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
			if (cp != null) {
				scoreboard.add("§6» §eClan: §8" + cp.getTagLabel());
			} else {
				scoreboard.add("§6» §eClan: §7Nenhum");
			}
		}
		if (pagina == 2) {
			if (Lag.getTPS() < 15.0D) {
				return;
			}
			if (Main.core != null) {
				ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
				if (cp != null) {
					scoreboard.add("§6» §eMortes: §f" + cp.getDeaths());
				} else {
					int ping = ((CraftPlayer) p).getHandle().ping;
					scoreboard.add("§6» §ePing: §f" + ping + "ms");
				}
			} else {
				scoreboard.add("§6» §eSimpleClans não encontrado");
			}
		}

		if (pagina == 3) {
			if (Lag.getTPS() < 15.0D) {
				return;
			}
			if (Main.core != null) {
				ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
				if (cp != null) {
					scoreboard.add("§6» §eKDR: §f" + cp.getKDR());
				} else {
					int ping = ((CraftPlayer) p).getHandle().ping;
					scoreboard.add("§6» §ePing: §f" + ping + "ms");
				}
			} else {
				scoreboard.add("§6» §eSimpleClans não encontrado");
			}
		}
		scoreboard.add("§8 ");
		scoreboard.add("§flegendmania.tk");
		scoreboard.build();
		scoreboard.send(p);
		 *  
		 *  
		 *  */
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * public static void setScore(Player player) {
	 * 
	 * // create a new scoreboard with title scoreboardutil scoreboard = new
	 * scoreboardutil("§6§lLegend§e§lMania"); // text with custom score int
	 * players = Bukkit.getOnlinePlayers().size(); int playersstaff = 0; int
	 * staff = 0; PermissionUser user = PermissionsEx.getUser(player); String
	 * prefix = user.getPrefix().replaceAll("§", "§");
	 * 
	 * @SuppressWarnings("deprecation") String[] group = user.getGroupNames();
	 * for (Player playerstaff : Bukkit.getOnlinePlayers()) { if
	 * (playerstaff.hasPermission("onlines.soustaff")) { staff = staff + 1;
	 * playersstaff = playersstaff + 1; } } String progressbar =
	 * "%ezrankspro_progressbar%"; String progressbar_r =
	 * PlaceholderAPI.setPlaceholders(player, progressbar);
	 * 
	 * String nextrank = "%ezrankspro_rankup%"; String nextrank_r =
	 * PlaceholderAPI.setPlaceholders(player, nextrank);
	 * 
	 * String saldo = "%ezrankspro_balance_formatted%"; String saldo_r =
	 * PlaceholderAPI.setPlaceholders(player, saldo);
	 * 
	 * players = players - playersstaff; scoreboard.add("§c ");
	 * scoreboard.add("§e Saldo: §f" + saldo_r); scoreboard.add("§e Rank: §f" +
	 * prefix.replaceAll("&", "§")); if (group[0].equalsIgnoreCase("dono") ||
	 * group[0].equalsIgnoreCase("legend") || group[0].equalsIgnoreCase("admin")
	 * || group[0].equalsIgnoreCase("mod") ||
	 * group[0].equalsIgnoreCase("subdono")) {
	 * scoreboard.add("§e Próximo: §c----"); } else {
	 * scoreboard.add("§e Próximo: §f" + nextrank_r);
	 * scoreboard.add("§e Progresso: §f" + progressbar_r); }
	 * scoreboard.add("§f §6  "); if
	 * (Main.instance.getConfig().getInt("scoreboard.pagina.second") == 1) { if
	 * (player.hasPermission("onlines.soustaff")) {
	 * scoreboard.add("§e Jogadores: §f" + players + " §8(§7" + staff + "§8)");
	 * }else{ scoreboard.add("§e Jogadores: §f" + players); } } if
	 * (Main.instance.getConfig().getInt("scoreboard.pagina.second") == 2) {
	 * ClanPlayer clank =
	 * SimpleClans.getInstance().getClanManager().getAnyClanPlayer(player.
	 * getName()); int clankills = 0; clankills = clankills +
	 * clank.getCivilianKills() + clank.getNeutralKills() +
	 * clank.getRivalKills(); scoreboard.add("§e Kills: §f" + clankills); } if
	 * (Main.instance.getConfig().getInt("scoreboard.pagina.second") == 3) {
	 * 
	 * ClanPlayer cland =
	 * SimpleClans.getInstance().getClanManager().getAnyClanPlayer(player.
	 * getName()); int clandeaths = 0; clandeaths = clandeaths +
	 * cland.getDeaths(); scoreboard.add("§e Mortes: §f" + clandeaths); } if
	 * (Main.instance.getConfig().getInt("scoreboard.pagina.second") == 4) {
	 * ClanPlayer clantag =
	 * SimpleClans.getInstance().getClanManager().getAnyClanPlayer(player.
	 * getName()); if (clantag != null) { String tag =
	 * clantag.getTagLabel().replaceAll("&", "§"); scoreboard.add("§e Clan: §f"
	 * + tag); } else { scoreboard.add("§e Clan: §7Nenhum"); } } if
	 * (Main.instance.getConfig().getInt("scoreboard.pagina.second") == 5) {
	 * 
	 * ClanPlayer kdrrr =
	 * SimpleClans.getInstance().getClanManager().getAnyClanPlayer(player.
	 * getName()); float kdrr = kdrrr.getKDR(); scoreboard.add("§e KDR: §f" +
	 * kdrr); } scoreboard.add("§4 "); if
	 * (Main.instance.getConfig().getInt("scoreboard.pagina.second") == 1 ||
	 * Main.instance.getConfig().getInt("scoreboard.pagina.second") == 2 ||
	 * Main.instance.getConfig().getInt("scoreboard.pagina.second") == 3){
	 * scoreboard.add("§f legendmania.esy.es"); }else{
	 * scoreboard.add("§f /score"); } scoreboard.build();
	 * scoreboard.send(player); }
	 */

	public static int getPagina() {
		return pagina;
	}

	public static void setPagina(int pagina) {
		pagina = scoreboard.pagina;
	}

}