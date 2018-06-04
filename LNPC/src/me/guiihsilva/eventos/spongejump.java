package me.guiihsilva.eventos;

import org.bukkit.event.Listener;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class spongejump implements Listener {
	private ArrayList<Player> jumpers = new ArrayList<Player>();

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SPONGE) {
			if (e.getPlayer().getLocation().getWorld() == Bukkit.getWorld("world")) {
				e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
				e.getPlayer().setVelocity(
						new Vector(e.getPlayer().getVelocity().getX(), 2.0D, e.getPlayer().getVelocity().getZ()));
				jumpers.add(e.getPlayer());
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.FIREWORK_LAUNCH, 15, 2);
			}
		}
	}
	@EventHandler
	public void onPlayerMove2(PlayerMoveEvent e) {
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.MELON_BLOCK) {
			if (e.getPlayer().getLocation().getWorld() == Bukkit.getWorld("world")) {
				e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
				e.getPlayer().setVelocity(
						new Vector(e.getPlayer().getVelocity().getX(), 1.0D, e.getPlayer().getVelocity().getZ()));
				jumpers.add(e.getPlayer());
				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.FIREWORK_LAUNCH, 15, 2);
			}
		}
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getCause() == DamageCause.FALL && jumpers.contains(p)) {
				e.setDamage(0.0);
				jumpers.remove(p);
			}
		}
	}
}