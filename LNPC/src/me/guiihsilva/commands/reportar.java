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
import me.guiihsilva.utils.JSONMessage;
import me.guiihsilva.utils.Title;

public class reportar implements CommandExecutor {

	public static ArrayList<Player> delay = new ArrayList<>();
	private static File file = new File(Main.instance.getDataFolder() + "denuncias.yml");
	private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("reportar")) {
			if (!(sender instanceof Player)){
				sender.sendMessage("§cComando apenas para jogadores in-game");
				return true;
			}else{
				Player p = (Player) sender;
				if (delay.contains(p)){
					p.sendMessage("§cAguarde para usar este comando novamente...");
					return true;
				}else{
					if (args.length == 0){
						p.sendMessage("§cUso correto: /reportar <Nick do acusado>");
						return true;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if (args.length == 1){
						target = Bukkit.getPlayer(args[0]);
						JSONMessage msg = JSONMessage.create("§eClique no motivo do report de §f" + target.getName())
								.newline().then("§fAimbot").tooltip("Jogador fica com a mira travada em seus inimigos\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " Aimbot")
								.newline().then("§7Anti Knockback").tooltip("Jogador não recebe repulsão ao ser atacado\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " AntiKnockback")
								.newline().then("§fAuto Armor").tooltip("Jogador equipa uma nova armadura automaticamente\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " AutoArmor")
								.newline().then("§7Chest Finder").tooltip("Jogador consegue ver todos os baús através da parede\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " ChestFinder")
								.newline().then("§fCliente Alternativo").tooltip("Uso de cliente alternativo com mods/hacks proibidos\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " ClienteAlternativo")
								.newline().then("§7Critical").tooltip("Jogador acerta criticals em todos\nos seus ataques mesmo sem pular\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " Critical")
								.newline().then("§fFast Break").tooltip("Jogador quebra blocos com uma velocidade fora do normal\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " FastBreak")
								.newline().then("§7Fast Place").tooltip("Jogador coloca blocos com uma velocidade fora do normal\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " FastPlace")
								.newline().then("§fFly").tooltip("Jogador consegue voar\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " Fly")
								.newline().then("§7Force Field").tooltip("Jogador bate muito rápido e mais longe que o normal\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " ForceField")
								.newline().then("§fNo Fall").tooltip("Jogador não toma dano ao cair\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " Nofall")
								.newline().then("§7No Slow").tooltip("Jogador não fica lento ao comer\nusar o arco\ndefender ou agachar\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " NoSlow")
								.newline().then("§fRegen").tooltip("Jogador recupera vida e come muito rapidamente\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " Regen")
								.newline().then("§7Speed").tooltip("Jogador tem o sprint ativo sempre\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " Speed")
								.newline().then("§fXRay").tooltip("Jogador consegue ver todos minérios pela parede\n"+
								" \n§eClique para selecionar").runCommand("/reportar " + target.getName() + " XRay");
						msg.send(p);
					}else if (args.length == 2){
						String motivo = args[1];
						if (motivo.equalsIgnoreCase("Aimbot") || motivo.equalsIgnoreCase("AntiKnockback") || motivo.equalsIgnoreCase("AutoArmor") || motivo.equalsIgnoreCase("ChestFinder") || motivo.equalsIgnoreCase("ClienteAlternativo") 
								|| motivo.equalsIgnoreCase("Critical") || motivo.equalsIgnoreCase("FastBreak") || motivo.equalsIgnoreCase("FastPlace") || motivo.equalsIgnoreCase("Fly") || motivo.equalsIgnoreCase("ForceField") || motivo.equalsIgnoreCase("Nofall") || motivo.equalsIgnoreCase("")
								|| motivo.equalsIgnoreCase("NoSlow") || motivo.equalsIgnoreCase("Regen") || motivo.equalsIgnoreCase("Speed") || motivo.equalsIgnoreCase("XRay")){
							anunciarReport(p, target, motivo);
						}else{
							p.sendMessage("§cMotivo de denúncia não reconhecida, use apenas as disponíveis...");
							return true;
						}
					}else{
						p.sendMessage("§cUso incorreto do comando! Use /reportar <nick do acusado>");
						return true;
					}
				}
			}
		}
		return false;
	}
	
	

	private static void anunciarReport(Player sender, Player target, String motivo){
		sender.sendMessage(" ");
		sender.sendMessage(" §aVocê denunciou §f" + target.getName() + " §apor§f " + motivo);
		sender.sendMessage(" §aA administração foi alertada sobre o ocorrido e logo os movimento desse jogador vão ser analisados...");
		sender.sendMessage(" §a* Uso incorreto deste comando resultará em punição...");
		for (Player p : Bukkit.getOnlinePlayers()){
			if (p.hasPermission("report.receber")){
				p.playSound(p.getLocation(), Sound.EXPLODE, 5, 1);
				Title t = new Title(" ", "§cUma denúncia foi feita: §f/denuncias", 0, 2, 1);
				t.send(p);
				p.sendMessage("§4§m--------------------§r §f§lREPORT §4§m--------------------");
				p.sendMessage(" §cQuem denunciou:§f " + sender.getName());
				JSONMessage msg1 = JSONMessage.create(" §cAcusado:§f " + target.getName() + " §7(Clique para teleportar-se)")
						.tooltip("§c§lAtive o /admin\nantes de teleportar-se!!")
						.suggestCommand("/tp " + target.getName());
				msg1.send(p);
				p.sendMessage(" §cMotivo:§f " + motivo);
				p.sendMessage("§4§m--------------------§r §f§lREPORT §4§m--------------------");
			}
		}
	}
	
	public boolean checkPlayers(CommandSender sender, Player target)
	  {
	    if (target == null)
	    {
	      sender.sendMessage("§cJogador não encontrado! ");
	      return true;
	    }
	    if (sender.getName().equalsIgnoreCase(target.getName()))
	    {
	      sender.sendMessage("§cVocê não pode denunciar você mesmo!");
	      return true;
	    }
	    return false;
	  }
	


	public static void saveConfig(File file){
		config.options().copyDefaults(true);
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("§4* ERRO AO SALVAR ALGUMA CONFIGURAÇÃO DO ARQUIVO: homes.yml");
		}
	}

}
