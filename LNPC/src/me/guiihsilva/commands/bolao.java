package me.guiihsilva.commands;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import me.guiihsilva.Main;
import me.guiihsilva.utils.ActionBarUtil;
import me.guiihsilva.utils.FormarNumber;
import net.milkbowl.vault.economy.EconomyResponse;

public class bolao implements CommandExecutor {

	private static double valor_total;
	private static boolean ocorrendo = false;
	private static double valor_minimo;
	private static int task;
	private ArrayList<Player> participantes = new ArrayList<>();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("bolao")) {
			if (args.length == 0) {
				sender.sendMessage("§9Bolão ➡ §fComandos:");
				sender.sendMessage(" §f/bolao - §7Aposta no bolão");
				if (sender.hasPermission("bolao.admin")) {
					sender.sendMessage(" §f/bolao abrir [valor mínimo] - §7Abre um bolão");
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("abrir")) {
					if (!sender.hasPermission("bolao.abrir")){
						sender.sendMessage("§cSem permissão!");
						return true;
					}
					if (!isInteger(args[1])) {
						sender.sendMessage("§cPrecisa ser números!");
						return true;
					}
					if (ocorrendo == true) {
						sender.sendMessage("§cJá existe um bolão aberto");
						return true;
					} else {
						ocorrendo = true;
						valor_total = 0;
						valor_minimo = Integer.parseInt(args[1]);
						participantes.clear();
						task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new BukkitRunnable() {
							int i = 15;
							@Override
							public void run() {
								i--;
								if (i == 0) {
									Bukkit.getScheduler().cancelTask(task);
									Random r = new Random();
									if (participantes.isEmpty()) {
										Bukkit.broadcastMessage("§e§m-------------§6§l Bolão §r§e§m-------------");
										Bukkit.broadcastMessage(" §f* §7Evento §6bolão §7foi finalizado!!");
										Bukkit.broadcastMessage(" §f* §7Não houve participantes!");
										Bukkit.broadcastMessage(" ");
										ocorrendo = false;
										valor_total = 0;
										valor_minimo = 0;
										participantes.clear();
										for (Player ps : Bukkit.getOnlinePlayers()) {
											ps.playSound(ps.getLocation(), Sound.NOTE_BASS_DRUM, 10, 2);
										}
									} else {
										int ganhador = r.nextInt(participantes.size());
										Player vencedor = (Player) participantes.get(ganhador);
										Bukkit.broadcastMessage("§e§m-------------§6§l Bolão §r§e§m-------------");
										Bukkit.broadcastMessage(" §f* §7Evento §6bolão §7finalizado!!");
										Bukkit.broadcastMessage(" §f* §7Sorteando um vencedor de §f" + participantes.size() + " §7participantes!");
										Bukkit.broadcastMessage(" §f* §7Total do prêmio: §2$§f" + FormarNumber.formatValue(valor_total));
										Bukkit.broadcastMessage(" ");
										for (Player ps : Bukkit.getOnlinePlayers()) {
											ps.playSound(ps.getLocation(), Sound.LAVA_POP, 10, 5);
										}
										Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
											@Override
											public void run() {
												Bukkit.broadcastMessage("§e§m-------------§6§l Bolão §r§e§m-------------");
												Bukkit.broadcastMessage(" §f* §7Evento §6bolão §7finalizado!!");
												Bukkit.broadcastMessage(" §f* §7O vencedor foi: §b" + vencedor.getName());
												Bukkit.broadcastMessage(" §f* §7Total do prêmio: §2$§f" + FormarNumber.formatValue(valor_total));
												Bukkit.broadcastMessage(" ");
												ocorrendo = false;
												for (Player ps : Bukkit.getOnlinePlayers()) {
													ps.playSound(ps.getLocation(), Sound.LEVEL_UP, 10, 5);
												}
												EconomyResponse e = Main.econ.depositPlayer(vencedor, valor_total);
												if (e.transactionSuccess()){
													vencedor.sendMessage("§aVocê é o ganhador do evento, depositado na conta: §f" + FormarNumber.formatValue(valor_total));
												}else{
													vencedor.sendMessage("§cHouve um erro ao depositar o premio ao vencedor, print este erro e envie á staff!!! (Premio: "  + FormarNumber.formatValue(valor_total) + " )");
												}

												valor_total = 0;
												valor_minimo = 0;
												participantes.clear();
											}
										}, 20*10);
									}
									return ;
								}
								Bukkit.broadcastMessage("§e§m-------------§6§l Bolão §r§e§m-------------");
								Bukkit.broadcastMessage(" §f* §7Evento §6bolão §7esta aberto!!");
								Bukkit.broadcastMessage(" §f* §7Aposte usando §6/bolao [Quantidade]");
								Bukkit.broadcastMessage(" §f* §7Total acumulado: §2$§f" + FormarNumber.formatValue(valor_total));
								Bukkit.broadcastMessage(" §f* §7Participantes: §f" + participantes.size());
								Bukkit.broadcastMessage(" ");
								for (Player ps : Bukkit.getOnlinePlayers()) {
									ps.playSound(ps.getLocation(), Sound.CHEST_OPEN, 10, 7);
								}
							}
						}, 0, 20 * 19);
					}
				} else {
					sender.sendMessage("§cUso correto: /bolao abrir [Valor minimo]");
					return true;
				}
			} else if (args.length == 1) {
				if (!isInteger(args[0])) {
					sender.sendMessage("§cSério isso? Você só poder apostar números, bobinho(a)!");
					return true;
				}
				if (ocorrendo == false) {
					sender.sendMessage("§cNenhum bolao aberto!");
					return true;
				}
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cApenas para jogadores in-game");
					return true;
				}
				if (Integer.parseInt(args[0]) < valor_minimo) {
					sender.sendMessage("§cO valor minimo da aposta é de: " + FormarNumber.formatValue(valor_minimo));
					return true;
				}
				if (ocorrendo == false){
					sender.sendMessage("§cO bolão não esta aberto!!");
					return true;
				}
				EconomyResponse r = Main.econ.withdrawPlayer((Player) sender, Integer.parseInt(args[0]));
				if (r.transactionSuccess()) {
					sender.sendMessage("§aVocê apostou no bolão §2$" + FormarNumber.formatValue(Double.parseDouble(args[0])) + "§a e esta concorrendo ao premio!");
					valor_total = valor_total + Integer.parseInt(args[0]);
					if (!participantes.contains((Player) sender)) {
						participantes.add((Player) sender);
					}

					for (Player ps : Bukkit.getOnlinePlayers()) {
						ActionBarUtil.sendActionBar("§f" + sender.getName() + " §eapostou no bolão, total: §2$§f" + FormarNumber.formatValue(valor_total), ps);
					}
				} else {
					sender.sendMessage("§cDinheiro insuficiente!");
				}
			}
		}
		return false;
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
