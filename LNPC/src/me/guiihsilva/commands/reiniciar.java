package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;

public class reiniciar implements CommandExecutor {

	public static int task;
	public static int tempo;
	public static String motivo = "";
	public static boolean reiniciando_ocorrendo;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("reiniciar")) {
			if (!sender.hasPermission("legendmania.reiniciar")) {
				sender.sendMessage("§cVocê não possui permissão para isto!");
				return true;
			} else {
				if (args.length == 0) {
					sender.sendMessage("§cUso correto: /reiniciar [tempo (minutos)/cancelar] <motivo>");
					return true;
				} else if (args.length > 0) {
					if (args[0].equalsIgnoreCase("cancelar")){
						if (reiniciando_ocorrendo == true){
								Bukkit.getScheduler().cancelTask(task);
								Bukkit.broadcastMessage(" ");
								Bukkit.broadcastMessage(
										" §6§lSERVIDOR: §eTarefa de reiniciar cancelada!");
								Bukkit.broadcastMessage(" ");
								me.guiihsilva.utils.Title t = new me.guiihsilva.utils.Title("§6Reiniciando", "§ecancelado§e!", 0, 3, 1);
								for (Player p : Bukkit.getOnlinePlayers()) {
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 1);
									t.send(p);
								}
								reiniciando_ocorrendo = false;
							return true;
						}else{
							sender.sendMessage("§cNenhuma tarefa para reiniciar encontrada!");
							return true;
						}
					}
					if (!(isInteger(args[0]))) {
						sender.sendMessage("§cO tempo deve ser um valor inteiro! (Só números)");
						return true;
					}
					if (!reiniciando_ocorrendo == false){
						sender.sendMessage("§cO servidor já esta reiniciando! Tempo: §f" + tempo + " segundos");
						return true;
					}
					tempo = Integer.parseInt(args[0]);
					if (tempo >= 16) {
						sender.sendMessage("§cEste tempo é muito grande!");
						return true;
					}
					if (tempo <= 0) {
						sender.sendMessage("§cO tempo deve ser no mínimo 1 minuto!");
						return true;
					} else {
						reiniciando_ocorrendo = true;
						tempo = tempo * 60;
						task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
							@Override
							public void run() {
								if (tempo == 900 || tempo == 840 || tempo == 780 || tempo == 720 || tempo == 660
										|| tempo == 600 || tempo == 540 || tempo == 480 || tempo == 420 || tempo == 360
										|| tempo == 300 || tempo == 240 || tempo == 180 || tempo == 120 || tempo == 60
										|| tempo == 30 || tempo == 15 || tempo == 10 || tempo == 5 || tempo == 4
										|| tempo == 3 || tempo == 2 || tempo == 1) {
									if (tempo > 59) {
										int tempo_t = tempo / 60;
										Bukkit.broadcastMessage(" ");
										if (args.length > 1){
											String msg = "";
											for (int i = 1; i < args.length; i++) {
												msg = msg + args[i] + " ";
											}
											motivo = " §eMotivo: " + msg;
										}
										Bukkit.broadcastMessage(
												" §6§lSERVIDOR: §eReiniciando em §f" + tempo_t + " minuto(s)§6!" + motivo);
										Bukkit.broadcastMessage(" ");
										me.guiihsilva.utils.Title t = new me.guiihsilva.utils.Title("§6Reiniciando", "§eem §f" + tempo_t + " minuto(s)§e!", 0, 3, 1);
										for (Player p : Bukkit.getOnlinePlayers()) {
											t.send(p);
											p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 1);
										}
									} else {
										Bukkit.broadcastMessage(" ");
										if (args.length > 1){
											String msg = "";
											for (int i = 1; i < args.length; i++) {
												msg = msg + args[i] + " ";
											}
											motivo = " §eMotivo: " + msg;
										}
										Bukkit.broadcastMessage(
												" §6§lSERVIDOR: §eReiniciando em §f" + tempo + " segundo(s)§6!" + motivo);
										Bukkit.broadcastMessage(" ");
										me.guiihsilva.utils.Title t = new me.guiihsilva.utils.Title("§6Reiniciando", "§eem §f" + tempo + " segundo(s)§e!", 0, 3, 1);
										for (Player p : Bukkit.getOnlinePlayers()) {
											p.playSound(p.getLocation(), Sound.ANVIL_LAND, 10, 1);
											t.send(p);
										}
									}
								}
								tempo--;
								if (tempo == 0) {
									Bukkit.getScheduler().cancelTask(task);
									reiniciarr();
									reiniciando_ocorrendo = false;
								}
							}
						}, 0, 20);
					}
				}
			}
		}
		return false;
	}
	
	public static void reiniciarr(){
		for (Player p : Bukkit.getOnlinePlayers()){
			p.kickPlayer("§4§lREINICIANDO!\n  §cAguarde enquanto não voltamos!");
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
			}
		}, 20*2);
	}

	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
