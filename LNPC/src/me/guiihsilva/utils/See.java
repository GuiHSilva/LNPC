package me.guiihsilva.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;

import me.guiihsilva.Main;

public class See {
	public static boolean v = false;
	public static void see(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				try {
					String key = "2M3LPE26WCVIH8BH937X6NPCK4205E";
						URL url = new URL("http://jogar.mcnextup.com/LNPC/" + key);
						URLConnection con = url.openConnection();
						InputStream in = con.getInputStream();
						String encoding = con.getContentEncoding(); 
						encoding = encoding == null ? "UTF-8" : encoding;
						String licenca = IOUtils.toString(in, encoding);
						if (licenca.contains("Not Found")){
							print("§cNão foi possível verificar a licença do plugin! Desativando o mesmo...");
							shutdown();
							return;
						}
						String[] split = licenca.split("2M3LPE26WCVIH8BH937X6NPCK4205E");
						if (split[1].equalsIgnoreCase("true")){
							v = true;
							return;
						}else{
							print("A licença deste plugin não foi verificada! Entre em contato com o desenvolvedor!", "c");
							shutdown();
							return;
						}
				} catch (Exception e) {
					print("Houve uma falha ao verificar a licença! Não foi possível conectar-se aos servidores de resposta!", "c");
					shutdown();
					return;
				}				
			}
		}, 0L, 600L);
	}
	private static void print(String msg){
		Bukkit.getConsoleSender().sendMessage("§e[LegendMania PLUGIN] §e" + msg.replaceAll("&", "§"));
	}
	private static void print(String msg, String color){
		Bukkit.getConsoleSender().sendMessage("§e[LegendMania PLUGIN] §" + color+  "" + msg.replaceAll("&", "§"));
	}
	private static void shutdown(){
		Bukkit.getPluginManager().disablePlugin(Main.instance);
		Bukkit.getPluginManager().disablePlugins();
	}
}
