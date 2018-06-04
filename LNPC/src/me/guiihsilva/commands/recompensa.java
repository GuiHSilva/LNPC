package me.guiihsilva.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import me.guiihsilva.utils.FormarNumber;
import net.milkbowl.vault.economy.EconomyResponse;

public class recompensa implements CommandExecutor {

	public static ArrayList<String> delay = new ArrayList<>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("recompensa")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("O console não pode adicionar uma recompensa por jogadores!");
				return true;
			}
			Player p = (Player) sender;
			if (args.length == 0 || args.length > 2 || args.length == 1) {
				p.sendMessage(" ");
				p.sendMessage(" §cUso correto: /recompensa [Jogador Alvo] [Quantia]");
				p.sendMessage(" ");
				return true;
			}
			if (args[0] == null) {
				p.sendMessage(" ");
				p.sendMessage(" §cUso correto: /recompensa [Jogador Alvo] [Quantia]");
				p.sendMessage(" ");
				return true;
			}
			if (args.length == 2) {
				Player target = Bukkit.getPlayer(args[0]);
				if (checkPlayers(sender, target)) {
					return true;
				} else {
					if(delay.contains(sender.getName())){
						sender.sendMessage("§cVocê deve aguardar 5 horas para poder dar recompensas!");
						return true;
					}
					if (!(isInteger(args[1]))) {
						p.sendMessage("§cA quantia deve ser um valor inteiro! (Só números)");
						p.sendMessage("§cExemplo: /" + cmd.getName() + " " + p.getName() + " 95000");
						return true;
					}
					int quantia = Integer.parseInt(args[1]);
					if (quantia < 50000 || quantia > 150000) {
						sender.sendMessage("§cA recompensa deve ser entre §f50,000§c e§f 150,000§c!");
						return true;
					} else {
						EconomyResponse r = Main.econ.withdrawPlayer(p.getName(), quantia);
						if (r.transactionSuccess()) {
							delay.add(sender.getName());
							p.sendMessage("§aVocê pagou §f" + FormarNumber.formatValue(quantia) + "§a pela cabeça de §a" + args[0]
									+ "§a! Assim que alguém mata-lo, o dinheiro será entregue");
							FileConfiguration filec = YamlConfiguration
									.loadConfiguration(new File(Main.instance.getDataFolder() + File.separator
											+ "recompensas" + File.separator + "recompensas_data.yml"));
							if (filec.getString("recompensas." + target.getUniqueId().toString()) == null) {

								filec.addDefault("recompensas." + target.getUniqueId().toString() + ".nickname",
										target.getName());
								filec.addDefault("recompensas." + target.getUniqueId().toString() + ".quantidade",
										Integer.parseInt(args[1]));
								filec.options().copyDefaults(true);
								try {
									filec.save(Main.instance.getDataFolder() + File.separator + "recompensas"
											+ File.separator + "recompensas_data.yml");
								} catch (IOException e) {
									System.out.println(
											" ERRO! Não foi possivel salvar os valores da recompensa no recompensas_data.yml");
									e.printStackTrace();
								}
							} else {
								int qnt = filec
										.getInt("recompensas." + target.getUniqueId().toString() + ".quantidade");
								qnt = qnt + Integer.parseInt(args[1]);
								filec.set("recompensas." + target.getUniqueId().toString() + ".nickname",
										target.getName());
								filec.set("recompensas." + target.getUniqueId().toString() + ".quantidade", qnt);
								filec.options().copyDefaults(true);
								try {
									filec.save(Main.instance.getDataFolder() + File.separator + "recompensas"
											+ File.separator + "recompensas_data.yml");
								} catch (IOException e) {
									System.out.println(
											" ERRO! Não foi possivel salvar os valores da recompensa no recompensas_data.yml");
									e.printStackTrace();
								}
							}

							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {

								@Override
								public void run() {
									for (Player pla : Bukkit.getOnlinePlayers()) {
										double valor = Double.parseDouble(filec.getString("recompensas." + target.getUniqueId() + ".quantidade"));
										pla.sendMessage(" ");
										pla.sendMessage(" §4Recompensa:");
										pla.sendMessage(" §cQuem matar §f"
												+ filec.getString("recompensas." + target.getUniqueId() + ".nickname")
												+ " §creceberá §2$§f" +  FormarNumber.formatValue(valor) + "§c!");
										pla.sendMessage(" ");
										pla.playSound(pla.getLocation(), Sound.AMBIENCE_THUNDER, 10, 1);
									}
								}
							}, 20 * 5);
							if(sender.hasPermission("recompensa.nodelay")){
								delay.remove(sender.getName());
								return true;
							}
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
								
								@Override
								public void run() {
									delay.remove(sender.getName());
								}
							}, 20*60*60*5);
							
							return true;
						} else {
							p.sendMessage("§cVocê não possui todo este dinheiro para pagar por esta recompensa!");
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean checkPlayers(CommandSender sender, Player target) {
		if (target == null) {
			sender.sendMessage("§cJogador não encontrado! ");
			return true;
		}
		if (sender.getName().equalsIgnoreCase(target.getName())) {
			sender.sendMessage("§cNão pode ser você mesmo!");
			return true;
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

/*
 * Player p = (Player) sender;
 * 
 * @SuppressWarnings("deprecation") EconomyResponse r =
 * Main.econ.withdrawPlayer(p.getName(), 10); if (r.transactionSuccess()){
 * sender.sendMessage("§aVocê pagou 10$ por este comando!"); return true; }else{
 * sender.sendMessage("§cDinheiro insuficiente!"); return true; }
 */