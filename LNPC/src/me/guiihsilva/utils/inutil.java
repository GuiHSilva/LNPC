package me.guiihsilva.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class inutil implements Listener {

	@EventHandler
	public void rojao(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		PermissionUser user = PermissionsEx.getUser(p.getName());
		if ((user.inGroup("dono")) || (user.inGroup("subdono"))) {

			if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getItemInHand().getType() == Material.EMERALD) {
				fireworkutil.spawnRandomFirework(p.getLocation());
			}
		}

	}
}
