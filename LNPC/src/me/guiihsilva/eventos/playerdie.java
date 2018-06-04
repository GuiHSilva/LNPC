package me.guiihsilva.eventos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.guiihsilva.Main;
import me.guiihsilva.utils.FormarNumber;
import net.milkbowl.vault.economy.EconomyResponse;

public class playerdie implements Listener {

	@EventHandler
	public void aoMorrer(PlayerDeathEvent e) {
		if (!(e.getEntity().getKiller() instanceof Player)) {
			return;
		}
		Player p = e.getEntity();
		FileConfiguration filec = YamlConfiguration.loadConfiguration(new File(Main.instance.getDataFolder()
				+ File.separator + "recompensas" + File.separator + "recompensas_data.yml"));
		int quantidade = filec.getInt("recompensas." + p.getUniqueId() + ".quantidade");
		if (quantidade > 0) {
			EconomyResponse r = Main.econ.depositPlayer(p.getKiller(), quantidade);
			if (r.transactionSuccess()) {
				for (Player pl : Bukkit.getOnlinePlayers()) {
					pl.sendMessage("");
					pl.sendMessage(" §2Recompensa:");
					pl.sendMessage(" §f" + p.getKiller().getName() + "§a matou §f" + p.getName() + "§a e recebeu §2$§f"
							+ FormarNumber.formatValue(quantidade));
					pl.sendMessage("");
					pl.playSound(pl.getLocation(), Sound.AMBIENCE_THUNDER, 10, 1);
				}
				filec.set("recompensas." + p.getUniqueId() + ".quantidade", 0);
				filec.options().copyDefaults(true);
				try {
					filec.save(Main.instance.getDataFolder() + File.separator + "recompensas" + File.separator
							+ "recompensas_data.yml");
					return;
				} catch (IOException er) {
					System.out
							.println(" ERRO! Não foi possivel salvar os valores da recompensa no recompensas_data.yml");
					er.printStackTrace();
				}
			} else {
				filec.set("recompensas." + p.getUniqueId() + ".quantidade", 0);
			}
		}
	}

	@EventHandler
	public void playerDie(PlayerDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity();
			Player k = e.getEntity().getKiller();
			if (Math.random() * 100 < 45) {
				k.playSound(p.getLocation(), Sound.SKELETON_DEATH, 10, 7);
				k.sendMessage("§7A cabeça de §c" + p.getName() + " §7caiu no chão!");
				ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
				skull.setDurability((short) 3);
				SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
				skullMeta.setDisplayName("§7Cabeça de §8" + p.getName());
				skullMeta.setOwner(p.getName()); 
				skull.setItemMeta(skullMeta); 
				int ex = (int) p.getLocation().getX();
				int ey = (int) p.getLocation().getY() + 1;
				int ez = (int) p.getLocation().getZ();
				World ew = (World) p.getLocation().getWorld();
				Location loc = new Location(ew, ex, ey, ez);
				ew.dropItem(loc, skull);
				return;
			} 
		}
	}

}
