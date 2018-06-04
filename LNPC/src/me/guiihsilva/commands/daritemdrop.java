package me.guiihsilva.commands;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class daritemdrop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("daritemdrop")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cComando para jogadores in-game");
			} else {
				Player p = (Player) sender;
				if (!p.hasPermission("legend.dropitens")) {
					p.sendMessage("§cSem permissão!");
				} else {
					if (args.length == 0 || args.length <= 1) {
						p.sendMessage("§cUso correto: /daritemdrop <quantidade> <id> [encantamento] [encantamento]");
						p.sendMessage("§cSe houver encantamento: Máximo 2 encantamentos por item!");
						return true;
					}
					if (args.length >= 2) {
						if (args.length > 4) {
							p.sendMessage(
									"§cUso correto: /daritemdrop <quantidade> <id> [encantamento] [encantamento]");
							p.sendMessage("§cSe houver encantamento: Máximo 2 encantamentos por item!");
							return true;
						}
						String e1 = "";
						String e2 = "";
						if (args.length == 3) {
							e1 = args[2];
						}
						if (args.length == 4) {
							e1 = args[2];
							e2 = args[3];
						}
						Random r = new Random();
						for (int i = Integer.parseInt(args[0]); i > 0; i--) {

							p.chat("/i " + args[1] + " 1 " + e1 + " " + e2 + " lore:&" + r.nextInt(9) + "DROP"
									+ r.nextInt(99) + r.nextInt(99));
						}
					}
				}
			}
		}
		return false;
	}

}
