package me.guiihsilva.eventos;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.guiihsilva.Main;
import me.guiihsilva.utils.ParticleEffect;
import net.citizensnpcs.api.event.NPCRightClickEvent;

public class clicknps implements Listener {
	
	private JavaPlugin _plugin;
	
	public clicknps(JavaPlugin plugin) {
		this._plugin = plugin;
		
		_plugin.getServer().getPluginManager().registerEvents(this, _plugin);
	}
	
	@EventHandler
	public void npcRightClickEvent(NPCRightClickEvent event){
		if(event.getNPC().getId() == 1){
			Player p = event.getClicker();
			double X = 500.5;
			double Y = 54;	
			double Z = 487.5;	
			float Pitch = 0;	
			float Yaw = 179;	
			org.bukkit.World Mundo = Bukkit.getWorld("world");	
			Location minapvp = new Location(Mundo, X, Y, Z);
			minapvp.setPitch(Pitch);
			minapvp.setYaw(Yaw);
			event.getClicker().teleport(minapvp);
			event.getClicker().sendMessage(" ");
			event.getClicker().sendMessage(" §aVocê saiu de dentro da minapvp!");
			event.getClicker().sendMessage(" ");
			event.getClicker().playSound(event.getClicker().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 5);

			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				
				@Override
				public void run() {
					ParticleEffect.SMOKE_NORMAL.display(10, 15, 0, 0, 0, p.getLocation().add(0, 1.5, 0), 10);
					ParticleEffect.SMOKE_NORMAL.display(10, 15, 0, 0, 0, p.getLocation().add(0.5, 1.5, 0.5), 10);
					ParticleEffect.SMOKE_NORMAL.display(10, 15, 0, 0, 0, p.getLocation().add(-0.5, 1.5, 0.5), 10);
					ParticleEffect.SMOKE_NORMAL.display(10, 15, 0, 0, 0, p.getLocation().add(0.5, 1.5, -0.5), 10);
					ParticleEffect.SMOKE_NORMAL.display(10, 15, 0, 0, 0, p.getLocation().add(-0.5, 1.5, -0.5), 10);
					
				}
			}, 10);
			
		}

		if(event.getNPC().getId() == 5){
			event.getClicker().playSound(event.getClicker().getLocation(), Sound.VILLAGER_YES, 10, 1);
			event.getClicker().sendMessage("§a[§eDono da loja§a]: §6Compre muito! Muito mesmo! Hahaha");
		}
		//event.getClicker().sendMessage("§cteste click");
	}
	

	
	@EventHandler
	public void verificarMoveMinaPVP(PlayerMoveEvent event) {
		
		if (event.getPlayer() == null) return; // Protecao de NPE
		
		Player player = event.getPlayer();
		
		if (!player.getWorld().getName().equalsIgnoreCase("world")) return;
		if (player.getGameMode() == GameMode.CREATIVE) return;


		Location minLocMinaPVP = new Location(_plugin.getServer().getWorld("world"), 479.0F, 0.0F, 437.0F);
		Location maxLocMinaPVP = new Location(_plugin.getServer().getWorld("world"), 521.0F, 0.0F, 479.0F);
		Location minLocspawn = new Location(_plugin.getServer().getWorld("world"), 131.0, 0.0, -131.0);
		Location maxLocspawn = new Location(_plugin.getServer().getWorld("world"), -124.0, 0.0, 117.0);
		Location minLocloja= new Location(_plugin.getServer().getWorld("world"), 970.0F, 0.0F, 916.0F);
		Location maxLocloja = new Location(_plugin.getServer().getWorld("world"), 1149.0F, 0.0F, 1063.0F);
		if (inArea(player, minLocspawn, maxLocspawn)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
		}
		if (inArea(player, minLocloja, maxLocloja)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
		}
		if (player.getAllowFlight()) { 
			
			if (inArea(player, minLocMinaPVP, maxLocMinaPVP)) {
				if (player.getName().equalsIgnoreCase("GuiiHSilva")){
					return;
				}
				player.setFlying(false);
				player.setAllowFlight(false);
				player.sendMessage(" ");
				player.sendMessage(" §cVocê esta em um local onde o fly não é permitido! Fly desativado!");
				player.sendMessage(" ");
				player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1, 5);
			}
		}
		
		// salva e testa
	}
	
	private boolean inArea(Player player, Location minLoc, Location maxLoc) {
		
		// Verifica se os mundos sao iguais
		if ((player.getWorld() != minLoc.getWorld()) || (player.getWorld() != maxLoc.getWorld())) return false;
		
		final Location playerLocation = player.getLocation();
		
		if (playerLocation.getBlockX() >= minLoc.getBlockX() && playerLocation.getBlockX() <= maxLoc.getBlockX()) { 
			if (playerLocation.getBlockZ() >= minLoc.getBlockZ() && playerLocation.getBlockZ() <= maxLoc.getBlockZ()) {
				return true;
			}
		}
		
		return false;
	}

}
