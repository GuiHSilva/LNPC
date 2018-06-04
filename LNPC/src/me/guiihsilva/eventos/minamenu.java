package me.guiihsilva.eventos;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.mewin.WGRegionEvents.events.RegionEnterEvent;

public class minamenu implements Listener{

	
	@EventHandler
	public void aoEntrarNoPortalDasMinas(RegionEnterEvent e){
		if(e.getRegion().getId().equalsIgnoreCase("minas-openmenu")){
			double x = 0;
			double y = 64;
			double z = 68;
			World w = Bukkit.getWorld("world");
			Location loc = new Location(w, x, y, z);
			e.getPlayer().teleport(loc);
			e.getPlayer().chat("/mina");
		}
	}
}
