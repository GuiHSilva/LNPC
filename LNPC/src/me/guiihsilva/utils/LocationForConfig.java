package me.guiihsilva.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationForConfig {

	public static String getLocationForConfig(Location loc) {
		String world = loc.getWorld().getName();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		float yaw = (float) loc.getYaw();
		float pitch = (float) loc.getPitch();
		return world + ";" + String.valueOf(x) + ";" + String.valueOf(y) + ";" + String.valueOf(z) + ";"
				+ String.valueOf(yaw) + ";" + String.valueOf(pitch);
	}
	
	
	
	public static Location getLocation(YamlConfiguration config, String patch) {
		if (config.getString(patch).split(";").length == 4) {
			String world = config.getString(patch).split(";")[0];
			double x = Double.parseDouble(config.getString(patch).split(";")[1]);
			double y = Double.parseDouble(config.getString(patch).split(";")[2]);
			double z = Double.parseDouble(config.getString(patch).split(";")[3]);
			return new Location(Bukkit.getWorld(world), x, y, z);
		} else if (config.getString(patch).split(";").length == 6) {
			String world = config.getString(patch).split(";")[0];
			double x = Double.parseDouble(config.getString(patch).split(";")[1]);
			double y = Double.parseDouble(config.getString(patch).split(";")[2]);
			double z = Double.parseDouble(config.getString(patch).split(";")[3]);
			float yaw = Float.parseFloat(config.getString(patch).split(";")[4]);
			float pitch = Float.parseFloat(config.getString(patch).split(";")[5]);
			return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
		} else {
			return null;
		}
	}
	
}
