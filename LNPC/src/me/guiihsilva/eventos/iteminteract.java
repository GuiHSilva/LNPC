package me.guiihsilva.eventos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import me.guiihsilva.Main;

public class iteminteract implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	private void playerInteract(PlayerInteractEvent e){
		if (e.getPlayer().getItemInHand().getType() == Material.POTION){
			e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
			Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
				
				@Override
				public void run() {
					e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
				}
			}, 20*2);
		}
	}

}
