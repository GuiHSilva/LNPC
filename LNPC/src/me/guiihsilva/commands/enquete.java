package me.guiihsilva.commands;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.guiihsilva.Main;
import me.guiihsilva.utils.JSONMessage;

public class enquete implements CommandExecutor {

	private static int task;
	private boolean ocorrendo = false;
	private boolean parando = false;
	private int votos_sim;
	private int votos_nao;
	private String resultado;
	private ArrayList<String> javotou = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("enquete")) {
			if (args.length == 0) {
				if (ocorrendo == false) {
					if (sender.hasPermission("enquete.admin")) {
						sender.sendMessage("§eEnquete: §fVocê pode abrir uma enquete usando §7/enquete abrir [Pergunta]");
						return true;
					} else {
						sender.sendMessage("§cNão existe nenhuma enquete ocorrendo no momento!");
						return true;
					}
				}
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("sim")) {
					if (javotou.contains(sender.getName())){
						sender.sendMessage("§cVocê já deu o seu voto!");
						return true;
					}
					if (ocorrendo == false){
						sender.sendMessage("§cNão existe nenhuma enquete ocorrendo no momento!");
						return true;
					}
					sender.sendMessage("§aObrigado por votar! Aguarde pelo resultado...");
					sender.sendMessage("§aSeu voto: §2§lSim");
					votos_sim = votos_sim + 1;
					javotou.add(sender.getName());
					return true;
				} else if (args[0].equalsIgnoreCase("nao")) {
					if (javotou.contains(sender.getName())){
						sender.sendMessage("§cVocê já deu o seu voto!");
						return true;
					}
					if (ocorrendo == false){
						sender.sendMessage("§cNão existe nenhuma enquete ocorrendo no momento!");
						return true;
					}
					sender.sendMessage("§aObrigado por votar! Aguarde pelo resultado...");
					sender.sendMessage("§aSeu voto: §4§lNão");
					votos_nao = votos_nao + 1;
					javotou.add(sender.getName());
					return true;
				} else if (args[0].equalsIgnoreCase("parar")){
					if (ocorrendo == true){
						parando = true;
						sender.sendMessage("§aA enquete foi parada com sucesso!");
						return true;
					}else{
						sender.sendMessage("§cNenhuma enquete ocorrendo");
						return true;
					}
				}
			} else if (args[0].equalsIgnoreCase("abrir")) {
				if (!sender.hasPermission("enquete.admin")) {
					sender.sendMessage("§cNão existe nenhuma enquete ocorrendo no momento!");
					return true;
				} else if (ocorrendo == true) {
					sender.sendMessage("§cJá existe uma enquete aberta! Você pode fecha-la com §7/enquete parar");
					return true;
				} else if (args.length > 1) {
					if (ocorrendo == true){
						sender.sendMessage("§cJá existe uma enquete aberta! Você pode fecha-la com §7/enquete parar");
						return true;
					}
					ocorrendo = true;
					BukkitScheduler sh = Bukkit.getScheduler();
					votos_sim = 0;
					votos_nao = 0;
					sender.sendMessage("§aVocê abriu uma enquete! Para parar use /enquete parar");
					parando = false;
					task = sh.scheduleSyncRepeatingTask(Main.instance, new Runnable() {
						int numero = 11;
						@Override
						public void run() {
							numero--;
							if (parando == true){
								ocorrendo = false;
								javotou.clear();
								sh.cancelTask(task);
								Bukkit.broadcastMessage(" ");
								Bukkit.broadcastMessage("§f§m---------------§r §6§lENQUETE §f§m---------------");
								Bukkit.broadcastMessage("  §eEsta enquete foi finalizada pela staff!");
								for (Player po2 : Bukkit.getOnlinePlayers()) {
									po2.playSound(po2.getLocation(), Sound.SUCCESSFUL_HIT, 10, 1);
								}
								return;
							}
							if (numero == -1) {
								sh.cancelTask(task);
								Bukkit.broadcastMessage(" ");
								Bukkit.broadcastMessage("§f§m---------------§r §6§lENQUETE §f§m---------------");
								Bukkit.broadcastMessage("  §eA enquete foi finalizada com sucesso!");
								ocorrendo = false;
								javotou.clear();
								if (votos_nao > votos_sim) {
									resultado = "§4§lNão";
									Bukkit.broadcastMessage("  §eO resultado final é: " + resultado);
									Bukkit.broadcastMessage("  §eVotos sim: §f" + votos_sim);
									Bukkit.broadcastMessage("  §eVotos não: §f" + votos_nao);
									Bukkit.broadcastMessage(" ");
									for (Player po2 : Bukkit.getOnlinePlayers()) {
										po2.playSound(po2.getLocation(), Sound.SUCCESSFUL_HIT, 10, 1);
									}
									return;
								}
								if (votos_sim > votos_nao) {
									resultado = "§2§lSim";
									Bukkit.broadcastMessage("  §eO resultado final é: " + resultado);
									Bukkit.broadcastMessage("  §eVotos sim: §f" + votos_sim);
									Bukkit.broadcastMessage("  §eVotos não: §f" + votos_nao);
									Bukkit.broadcastMessage(" ");
									for (Player po2 : Bukkit.getOnlinePlayers()) {
										po2.playSound(po2.getLocation(), Sound.NOTE_PLING, 10, 1);
									}
									return;
								}
								if (votos_sim == votos_nao){
									resultado = "§6§lEMPATE!";
									Bukkit.broadcastMessage("  §eO resultado final é: " + resultado);
									Bukkit.broadcastMessage("  §eVotos sim: §f" + votos_sim);
									Bukkit.broadcastMessage("  §eVotos não: §f" + votos_nao);
									Bukkit.broadcastMessage(" ");
									for (Player po2 : Bukkit.getOnlinePlayers()) {
										po2.playSound(po2.getLocation(), Sound.NOTE_PLING, 10, 1);
									}
									return;
								}else{
									resultado = "§4§lHouve um erro na contagem!";
									Bukkit.broadcastMessage("  §eO resultado final é: " + resultado);
									Bukkit.broadcastMessage("  §eVotos sim: §f" + votos_sim);
									Bukkit.broadcastMessage("  §eVotos não: §f" + votos_nao);
									Bukkit.broadcastMessage(" ");
									for (Player po2 : Bukkit.getOnlinePlayers()) {
										po2.playSound(po2.getLocation(), Sound.NOTE_PLING, 10, 1);
									}
									return;
								}
							}
							String msg = "";
							for (int i = 1; i < args.length; i++) {
								msg = msg + args[i] + " ";
							}
							JSONMessage msgjson = JSONMessage.create("  §2§lSim")
									.tooltip("§aAo clicar aqui você\n§avai votar em sim!").runCommand("/enquete sim")
									.then(" §8(§7Clique no chat para votar§8)")
									.tooltip("§fPara votar você dever\n§fclicar em §2§lSim §fou §4§lNão§f!")
									.then(" §4§lNão").tooltip("§4Ao clicar aqui você\n§4vai votar em não!")
									.runCommand("/enquete nao");
							Bukkit.broadcastMessage(" ");
							Bukkit.broadcastMessage("§f§m---------------§r §6§lENQUETE §f§m---------------");
							Bukkit.broadcastMessage("  §ePergunta: §f" + msg);
							for (Player po : Bukkit.getOnlinePlayers()) {
								po.playSound(po.getLocation(), Sound.VILLAGER_YES, 10, 2);
								msgjson.send(po);
							}
							if (numero == 0){
								Bukkit.broadcastMessage("  §eAvisos restantes: §fNenhum");
							}else{
								Bukkit.broadcastMessage("  §eAvisos restantes: §f" + numero);
							}
							Bukkit.broadcastMessage(" ");

						}
					}, 0, 20 * 11);
				} else {
					sender.sendMessage("§cUso correto: /enquete abrir [Pergunta]");
					return true;
				}
			}
		}
		return false;
	}

}

