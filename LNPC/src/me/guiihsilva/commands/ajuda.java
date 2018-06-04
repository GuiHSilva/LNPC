package me.guiihsilva.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import me.guiihsilva.utils.JSONMessage;

public class ajuda implements CommandExecutor {

	public static ArrayList<String> solicitou_ajuda = new ArrayList<>();
	public static ArrayList<String> delay = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ajuda")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("�cO console n�o pode fazer uso de um comando de ajuda");
				return true;
			}
			if (args.length == 0) {
				Player p = (Player) sender;
				sender.sendMessage("�a�m---------------------�r �2�lAJUDA �a�m---------------------");
				sender.sendMessage(
						" �2- �fVoc� pode solicitar ajuda � staff pelo chat, ou, usando o comando �a/ajuda <sua d�vida>�f. Se houver staffs online, eles v�o responder voc�!");
				sender.sendMessage("�a�m---------------------�r �2�lAJUDA �a�m---------------------");
				p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10, 2);
				return true;
			} else if (args.length > 0) {
				if (solicitou_ajuda.contains(sender.getName()) || delay.contains(sender.getName())){
					sender.sendMessage("�2�lAJUDA > �7Voc� fez uma pergunta recentemente, aguarde para usar este comando novamente!");
					return true;
				}
				String msg_de_ajuda = "";
				for (int i = 0; i < args.length; i++) {
					msg_de_ajuda = msg_de_ajuda + args[i] + " ";
				}

				solicitou_ajuda.add(sender.getName());
				delay.add(sender.getName());
				sender.sendMessage("�a�m---------------------�r �2�lAJUDA �a�m---------------------");
				sender.sendMessage(" �2- �fVoc� solicitou ajuda � staff, a pergunta enviada foi a seguinte: �8'�7" + msg_de_ajuda + "�8'");
				sender.sendMessage(" �2- �fA d�vida foi enviada � todos staffs online, se n�o haver nenhum online, sua d�vida �cn�o �fser� respondida... Em caso de urg�ncia tente outro meio de contato!");
				sender.sendMessage("�a�m---------------------�r �2�lAJUDA �a�m---------------------");
				for (Player ps : Bukkit.getOnlinePlayers()) {
					if (ps.hasPermission("ajuda.receber")) {
						ps.sendMessage("�a�m---------------------�r �2�lAJUDA �a�m---------------------");
						ps.sendMessage(" �2- �fO jogador �6" + sender.getName() + " �festa solicitando ajuda!");
						ps.sendMessage("  �7A d�vida dele(a) � a seguinte:");
						ps.sendMessage(" �8- �7'�f�o " + msg_de_ajuda + "�7'");
						JSONMessage msgjson = JSONMessage.create(" �2- �aClique aqui para responde-lo!").tooltip("�fClique para responder�a "+ sender.getName() + "�f!").suggestCommand("/ajdr " + sender.getName() + " ");
						//MessageSender.sendMessage(ps, new ChatPart(" �2- �aClique aqui para responde-lo!").setHoverEvent(JSONChatHoverEventType.SHOW_TEXT, "�fClique aqui para responder �a" + sender.getName() + "!").setClickEvent(JSONChatClickEventType.SUGGEST_COMMAND,"/ajdr " + sender.getName() + " "));
						msgjson.send(ps);
						ps.sendMessage("�a�m---------------------�r �2�lAJUDA �a�m---------------------");
						ps.playSound(ps.getLocation(), Sound.AMBIENCE_THUNDER, 10, 2);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (!delay.contains(sender.getName())){
									solicitou_ajuda.remove(sender.getName());
									delay.remove(sender.getName());
									return;
								}
								solicitou_ajuda.remove(sender.getName());
								delay.remove(sender.getName());
								sender.sendMessage("�2�lAJUDA > �7Voc� agora est� liberado para usar o �f/ajuda�7, a sua d�vida foi fechada (n�o ter� mais respostas), se a mesma n�o foi respondida, continue tentando...");
							}
						}, 20*60*2);
					}
				}
			}
		}
		return false;
	}
}
