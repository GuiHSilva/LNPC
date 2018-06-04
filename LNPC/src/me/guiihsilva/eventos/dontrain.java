package me.guiihsilva.eventos;

import java.util.Collection;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class dontrain implements Listener {
	@SuppressWarnings("unused")
	private Collection<String> worlds;

	@EventHandler
	public void Chuva(WeatherChangeEvent event) {
		//event.setCancelled(true);
		if (event.toWeatherState()){
			event.setCancelled(true);
		}
	}
}
