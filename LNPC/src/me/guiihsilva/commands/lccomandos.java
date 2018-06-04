package me.guiihsilva.commands;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class lccomandos implements CommandExecutor{
	  public static Set<Player> chatTell = new HashSet<Player>();
	  public static Set<Player> chatGlobal = new HashSet<Player>();
	  public static Set<Player> allChats = new HashSet<Player>();
	  
	  
	  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	  {
	    if ((sender instanceof Player))
	    {
	      Player p = (Player)sender;
	      if(!p.hasPermission("chat.custom")){
	    	  p.sendMessage("§cApenas jogadores §6VIP§c podem desativar o tell e o global!");
	    	  return true;
	      }
	      if (args.length == 0)
	      {
	        p.sendMessage("§6Chat ➡ §fOpções do chat:");
	        p.sendMessage(" §e★§f §6/chat tell §7- §fDesativa/ativa o recebimento de mensagens!");
	        p.sendMessage(" §e★§f §6/chat global §7- §fDesativa/ativa o chat global!");
	        p.sendMessage(" §e★§f §6/chat * §7- §fDesativa/ativa globais e privadas!");
	        
	        return true;
	      }
	      if (args[0].equalsIgnoreCase("global")) {
	        if (chatGlobal.contains(p))
	        {
	          chatGlobal.remove(p);
	          p.sendMessage("§6Chat ➡ §fChat global ativado!");
	        }
	        else
	        {
	          chatGlobal.add(p);
	          p.sendMessage("§6Chat ➡ §fChat global desativado!");
	        }
	      }
	      if (args[0].equalsIgnoreCase("tell")) {
	        if (chatTell.contains(p))
	        {
	          chatTell.remove(p);
	          p.sendMessage("§6Chat ➡ §fO recebimento de mensagens privadas foi §aativado");
	        }
	        else
	        {
	          chatTell.add(p);
	          p.sendMessage("§6Chat ➡ §fO recebimento de mensagens privadas foi §cdesativado");
	        }
	      }
	      if (args[0].equalsIgnoreCase("*")) {
	        if (allChats.contains(p))
	        {
	          allChats.remove(p);
	          p.sendMessage("§6Chat ➡ §fTodos os chats foram ativos");
	        }
	        else
	        {
	          allChats.add(p);
	          p.sendMessage("§6Chat ➡ §fTodos os chats foram desativados!");
	          p.sendMessage("§6Chat ➡ §cOBS: §fChat local não pode ser desativado");
	        }
	      }
	    }
	    else
	    {
	      sender.sendMessage("§cConsole não pode executar este comando!");
	    }
	    return false;
	  }
}
