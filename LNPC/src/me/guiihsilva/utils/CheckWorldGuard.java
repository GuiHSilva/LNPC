package me.guiihsilva.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.GlobalRegionManager;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import me.guiihsilva.Main;

@SuppressWarnings("deprecation")
public class CheckWorldGuard {

	public static boolean checkWorldGuard(Location l, Player p, String fs, boolean sendMessage) {
		if (p.isOp()) {
			return true;
		}
		if (Main.getWorldGuard() != null) {
			GlobalRegionManager grm = Main.getWorldGuard().getGlobalRegionManager();
			if (grm == null) {
				return true;
			}
			StateFlag f = null;
			@SuppressWarnings("rawtypes")
			Flag[] arrayOfFlag;
			int j = (arrayOfFlag = DefaultFlag.flagsList).length;
			for (int i = 0; i < j; i++) {
				Flag<?> df = arrayOfFlag[i];
				if (fs.equalsIgnoreCase(df.getName())) {
					f = (StateFlag) df;
				}
			}
			if (f.equals(DefaultFlag.BUILD)) {
				if (!grm.canBuild(p, l)) {
					if (sendMessage) {
						p.sendMessage("§cVocê não pode fazer isso aqui!");
					}
					return false;
				}
			} else if (!grm.allows(f, l, Main.getWorldGuard().wrapPlayer(p))) {
				if (sendMessage) {
					p.sendMessage("§cVocê não pode fazer isso aqui");
				}
				return false;
			}
		}
		return true;
	}

}
