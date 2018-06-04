package me.guiihsilva.commands;


import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import me.guiihsilva.cash.menu;
import me.guiihsilva.utils.Title;
import me.guiihsilva.utils.msg;


public class cash implements CommandExecutor{

	
	private ArrayList<Player> delay = new ArrayList<>();
	private ArrayList<Player> delaykey = new ArrayList<>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cash")){
			if (args.length == 0){
				if (isConsole(sender)){
					sender.sendMessage("§eComandos de cash para console: ");
					sender.sendMessage(" §7/cash add <player> <quantidade>");
					sender.sendMessage(" §7/cash remove <player> <quantidade>");
					sender.sendMessage(" §7/cash set <player> <quantidade>");
					return true;
				}
				Player p = (Player) sender;
				if (delay.contains(p)){
					p.sendMessage("§cAguarde 5s para usar este comando novamente!");
					return true;
				}
				menu.principal(p);
				delay.add(p);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					@Override
					public void run() {
						delay.remove(p);
					}
				}, 20*5);
				return true;
			}
			
			
			
			
			if (args[0].equalsIgnoreCase("set")){
				if (!sender.hasPermission("cash.admin")){
					sender.sendMessage(msg.type("perm"));
					return true;
				}
				if (!(args.length == 3)){
					sender.sendMessage("§cUso correto: /cash set <Jogador> <Quantidade>");
					return true;
				}else{
					Player p = (Player) Bukkit.getPlayer(args[1]);
					if (me.guiihsilva.cash.cash.getCash(p).equalsIgnoreCase("Jogador não encontrado no banco de dados!")){
						sender.sendMessage(msg.type("no_player"));
						return true;
					}
					if (!(isInteger(args[2]))){
						sender.sendMessage(msg.type("!int"));
						return true;
					}
					if (Integer.parseInt(args[2]) < 0){
						sender.sendMessage(msg.type("<0"));
						return true;
					}
					sender.sendMessage("§aDefinido " + args[2] + " de cash para " + args[1]);
					me.guiihsilva.cash.cash.modifyCash(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]), "set");
					return true;
				}
			}
			


			
			
			
			if (args[0].equalsIgnoreCase("add")){
				if (!sender.hasPermission("cash.admin")){
					sender.sendMessage(msg.type("perm"));
					return true;
				}
				if (!(args.length == 3)){
					sender.sendMessage("§cUso correto: /cash add <Jogador> <Quantidade>");
					return true;
				}else{
					Player p = (Player) Bukkit.getPlayer(args[1]);
					if (me.guiihsilva.cash.cash.getCash(p).equalsIgnoreCase("Jogador não encontrado no banco de dados!")){
						sender.sendMessage(msg.type("no_player"));
						return true;
					}
					if (!(isInteger(args[2]))){
						sender.sendMessage(msg.type("!int"));
						return true;
					}
					if (Integer.parseInt(args[2]) < 0){
						sender.sendMessage(msg.type("<0"));
						return true;
					}
					int result;
					result = Integer.parseInt(args[2]) + Integer.parseInt(me.guiihsilva.cash.cash.getCash(p));
 					if (result < 0){
 						sender.sendMessage(msg.type("<0"));
 						return true;
 					}
					sender.sendMessage("§aAdicionando " + args[2] + " de cash para " + args[1]);
					me.guiihsilva.cash.cash.modifyCash(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]), "add");
					return true;
				}
			}
			
			if (args[0].equalsIgnoreCase("gerarkey")){
				if (!sender.hasPermission("cash.admin")){
					sender.sendMessage(msg.type("perm"));
					return true;
				}
				if (!(args.length == 2)){
					sender.sendMessage("§cUso correto: /cash gerarkey <Valor da key>");
					return true;
				}else{
					if (!(isInteger(args[1]))){
						sender.sendMessage(msg.type("!int"));
						return true;
					}
					Player p = (Player) sender;
					String key = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 5);
			        p.sendMessage("§aKey de ativação gerada com êxito!");
			        p.sendMessage("§aKey: §f" + key);
			        p.sendMessage("§aValor: §f" + args[1]);
			        me.guiihsilva.cash.cash.addKey(key, Integer.parseInt(args[1]));
			        return true;
				}
			}
			

			if (args[0].equalsIgnoreCase("keys")){
				if (!sender.hasPermission("cash.admin")){
					sender.sendMessage(msg.type("perm"));
					return true;
				}
				if (!(args.length == 1)){
					sender.sendMessage("§cUso correto: /cash keys");
					return true;
				}else{
					me.guiihsilva.cash.cash.getKeys();
			        return true;
				}
			}
			
			if (args[0].equalsIgnoreCase("key") || args[0].equalsIgnoreCase("usarkey") || args[0].equalsIgnoreCase("ativarcash")){
				if (!(args.length == 2)){
					sender.sendMessage("§cUso correto: /cash " + args[0] + " <KEY>");
					return true;
				}else{
					if (isConsole(sender)){
						sender.sendMessage("§cÉ impossível o console ativar uma key de cash!");
						return true;
					}
					if (delaykey.contains((Player) sender)){
						sender.sendMessage("§cAguarde 30s para usar esse comando novamente!");
						return true;
					}
					delaykey.add((Player) sender);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
						@Override
						public void run() {
							delaykey.remove((Player) sender);
						}
					}, 20*30);
					String key = args[1];
					if (key.length() != 5){
						sender.sendMessage("§cAparentemente, esta não é uma key válida :(");
						return true;
					}
					Player p = (Player) sender;
					if (me.guiihsilva.cash.cash.ativarCash(p, key).equalsIgnoreCase("null")){
						p.sendMessage("§cKey inválida...");
						return true;
					}else{
						p.sendMessage("§aKey encontrada. Aguarde enquanto a key é validada...");
						int valor = Integer.parseInt(me.guiihsilva.cash.cash.ativarCash(p, key));
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
							@Override
							public void run() {
								me.guiihsilva.cash.cash.getDb(p, valor, "add");
								p.sendMessage("§2Key validada com sucesso!");
								p.sendMessage("§aEssa key vale: §f" + valor + " de cash");
								Title t = new Title("§c" + p.getName(), "§eAcabou de ativar " + valor + " de cash", 1, 5, 2);
								for (Player pl : Bukkit.getOnlinePlayers()){
									t.send(pl);
								}
								System.out.println("[LEGENDMANIA] O jogador " + p.getName() + " ativou " + valor + " de cash usando a key: " + key);
							}
						}, 20*3);
					}
			        return true;
				}
			}
			
			if (args[0].equalsIgnoreCase("remove")){
				if (!sender.hasPermission("cash.admin")){
					sender.sendMessage(msg.type("perm"));
					return true;
				}
				if (!(args.length == 3)){
					sender.sendMessage("§cUso correto: /cash remove <Jogador> <Quantidade>");
					return true;
				}else{
					Player p = (Player) Bukkit.getPlayer(args[1]);
					if (me.guiihsilva.cash.cash.getCash(p).equalsIgnoreCase("Jogador não encontrado no banco de dados!")){
						sender.sendMessage(msg.type("no_player"));
						return true;
					}
					if (!(isInteger(args[2]))){
						sender.sendMessage(msg.type("!int"));
						return true;
					}
					if (Integer.parseInt(args[2]) < 0){
						sender.sendMessage(msg.type("<0"));
						return true;
					}
					int result;
					result = Integer.parseInt(me.guiihsilva.cash.cash.getCash(p)) - Integer.parseInt(args[2]) ;
 					if (result < 0){
 						sender.sendMessage(msg.type("<0"));
 						return true;
 					}	
					sender.sendMessage("§aRemovendo " + args[2] + " de cash de " + args[1]);
					me.guiihsilva.cash.cash.modifyCash(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]), "remove");
					return true;
				}
			}
			
			
			if (args[0].equalsIgnoreCase("ajuda") || args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")){
				sender.sendMessage("§e§lCASH: §fLista de comandos:");
				sender.sendMessage(" §f/" + cmd.getName() + " ajuda/?/help §7- Mostra este menu de ajuda!");
				sender.sendMessage(" §f/" + cmd.getName() + " §7- Abre o menu de compras!");
				sender.sendMessage(" §f/" + cmd.getName() + " ver §7- Mostra a quantidade de cash");
				sender.sendMessage(" §f/" + cmd.getName() + " key/usarkey §7- Ativa uma key para receber cash");
				if (sender.hasPermission("cash.admin")){
					sender.sendMessage(" §7/" + cmd.getName() + " set §c- Define uma quantidade de cash");
					sender.sendMessage(" §7/" + cmd.getName() + " add §c- Adiciona uma quantidade de cash");
					sender.sendMessage(" §7/" + cmd.getName() + " remove §c- Remove uma quantidade de cash");
					sender.sendMessage(" §7/" + cmd.getName() + " keys §c- Vê todas as keys (Manutenção)");
					sender.sendMessage(" §7/" + cmd.getName() + " gerarkey §c- Cria uma nova key de ativação");
				}
				return true;
			}
			

			if (args[0].equalsIgnoreCase("ver")){
				Player player = (Player) sender;
				if (player == null || !(player.isOnline())){
					return true;
				}
				player.sendMessage("§eQuantidade de cash: §f" + me.guiihsilva.cash.cash.getCash(player));
				return true;
			
			}else{
				sender.sendMessage("§cSub comando desconhecido! Use §e/" + cmd.getName() + " ajuda§c!");
				return true;
			}
		}
		return false;
	}
	
	public static String generateRandomChars(String candidateChars, int length) {
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
	                .length())));
	    }

	    return sb.toString();
	}
	
	private static boolean isConsole(CommandSender sender){
		if (!(sender instanceof Player)){
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
