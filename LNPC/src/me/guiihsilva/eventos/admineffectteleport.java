package me.guiihsilva.eventos;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffectType;

import com.earth2me.essentials.Essentials;

import io.puharesource.mc.titlemanager.api.ActionbarTitleObject;
import me.guiihsilva.Main;

public class admineffectteleport implements Listener{
	void sendActionbarMessage(Player player, String message) {
		  new ActionbarTitleObject(message).send(player);
		}
	Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
	
	@EventHandler
	public void trocarMundo(PlayerChangedWorldEvent e){
		if(this.ess.getUser(e.getPlayer()).isVanished()){
			if (e.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					@Override
					public void run() {
						sendActionbarMessage(e.getPlayer(), "§7Efeito de invisibilidade removida!");
						e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
					}
				}, 23);
				return;
			}
		}
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE){
			if (!e.getPlayer().hasPermission("fly.nao.remover")){
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					@Override
					public void run() {
						e.getPlayer().setFlying(false);
						e.getPlayer().setAllowFlight(false);
						e.getPlayer().sendMessage("§4*§c Fly detectado durante teleporte, desativando o fly!");
					}
				}, 28);
			}
		}
	}
}