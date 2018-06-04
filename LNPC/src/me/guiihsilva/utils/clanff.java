package me.guiihsilva.utils;

import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;

public class clanff {

	public static void ativarClanFF(Player p) {
		if (Main.core != null) {
			ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
			if (cp != null) {
				Main.core.getClanManager().getClanPlayer(p).setFriendlyFire(true);
			}
		}
	}

	public static void desativarClanFF(Player p) {
		if (Main.core != null) {
			ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
			if (cp != null) {
				Main.core.getClanManager().getClanPlayer(p).setFriendlyFire(false);
			}
		}
	}

}
