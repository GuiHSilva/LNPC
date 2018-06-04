package me.guiihsilva.eventos;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.com.devpaulo.legendchat.api.events.PrivateMessageEvent;
import me.guiihsilva.commands.lccomandos;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class lclisteners
  implements Listener
{
  @EventHandler
  void onGlobalMessage(ChatMessageEvent e)
  {
    if (e.getChannel().getName().equalsIgnoreCase("global"))
    {
      if (lccomandos.chatGlobal.contains(e.getSender()))
      {
        lccomandos.chatGlobal.remove(e.getSender());
        e.getSender().sendMessage("§6Chat ➡ §fChat global ativado!");
      }
      e.getRecipients().removeAll(lccomandos.chatGlobal);
    }
  }
  
  @EventHandler
  void onChatMessage(ChatMessageEvent e)
  {
    if (lccomandos.allChats.contains(e.getSender()))
    {
      lccomandos.allChats.remove(e.getSender());
      e.getSender().sendMessage("§6Chat ➡ §fTodos os chats foram ativos");
    }
    e.getRecipients().removeAll(lccomandos.allChats);
  }
  
  @EventHandler
  void onPrivateMessage(PrivateMessageEvent e)
  {
    if (lccomandos.chatTell.contains(e.getSender()))
    {
      lccomandos.chatTell.remove(e.getSender());
      e.getSender().sendMessage("§6Chat ➡ §fO recebimento de mensagens privadas esta ativado");
    }
    if (lccomandos.chatTell.contains(e.getReceiver()))
    {
      if(e.getSender().hasPermission("chat.checkestaoff")){
    	  e.getSender().sendMessage("§cEsse jogador esta com o tell desativado, devido ao seu poder, o tell foi enviado!");
    	  e.getReceiver().sendMessage("§aSeu tell esta desativado, porem esse jogador é staff e tem pode para lhe enviar mensagens!");
    	  return;
      }
      e.getSender().sendMessage("§6Chat ➡ §cJogador não encontrado.");
      e.setCancelled(true);
    }
  }
}
