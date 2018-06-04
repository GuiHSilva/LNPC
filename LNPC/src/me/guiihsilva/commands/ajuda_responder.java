package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.utils.JSONMessage;

public class ajuda_responder implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ajdr")) {
			if (!sender.hasPermission("ajuda.responder")){
				sender.sendMessage("§cSem permissão!");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage("§cUso correto: /ajdr <Jogador> <Resposta>");
				return true;
			} else if (args.length == 1) {
				sender.sendMessage("§cUso correto: /ajdr " + args[0] + " <Resposta>");
				return true;
			} else if (args.length >= 2) {

				String msg_de_resposta = "";
				for (int i = 1; i < args.length; i++) {
					msg_de_resposta = msg_de_resposta + args[i] + " ";
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (checkPlayers(sender, target)) {
					return true;
				} else {
					if (ajuda.solicitou_ajuda.contains(target.getName())) {
						for (Player so : Bukkit.getOnlinePlayers()) {
							if (so.hasPermission("ajuda.receber")) {
								JSONMessage json = JSONMessage.create("§aO(a) staff §2" + sender.getName() + " §arespondeu a dúvida de §2" + target.getName()).tooltip("§7§o" + msg_de_resposta);
								json.send(so);
							}
						}

						target.sendMessage("§a§m---------------------§r §2§lAJUDA §a§m---------------------");
						target.sendMessage(
								" §2- §fSua dúvida foi respondida por §7" + sender.getName() + " §fe foi fechada.");
						target.sendMessage(" §2- §fResposta: §7" + msg_de_resposta + "");
						target.sendMessage("§a§m---------------------§r §2§lAJUDA §a§m---------------------");
						target.playSound(target.getLocation(), Sound.FIREWORK_LAUNCH, 10, 1);
						ajuda.solicitou_ajuda.remove(target.getName());
					} else {
						sender.sendMessage("§2§lAJUDA > §7Este jogador não possui nenhuma dúvida aberta!");
						return true;
					}
				}
			}

		}
		return false;
	}

	public boolean checkPlayers(CommandSender sender, Player target) {
		if (target == null) {
			sender.sendMessage("§cJogador não encontrado!");
			return true;
		}
		if (sender.getName().equalsIgnoreCase(target.getName())) {
			sender.sendMessage("§cNão pode ser você mesmo!");
			return true;
		}
		return false;
	}

}
