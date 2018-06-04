package me.guiihsilva.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class staffchatcolor implements Listener{
	
	@EventHandler
	public void onChat(ChatMessageEvent e){
		Player p = e.getSender();
		String msg = e.getMessage();
		if (p.hasPermission("chat.staff.color")){
			PermissionUser user = PermissionsEx.getUser(p);
			if ((user.inGroup("ajudante"))){
				e.setMessage("§3" + msg);
				return;
			}
			if ((user.inGroup("mod"))){
				e.setMessage("§2" + msg);
				return;
			}
			if ((user.inGroup("admin"))){
				e.setMessage("§4" + msg);
				return;
			}
			if ((user.inGroup("diretor"))){
				e.setMessage("§9" + msg);
				return;
			}
			if ((user.inGroup("master"))){
				e.setMessage("§6" + msg);
				return;
			}else{

				e.setMessage("§f" + msg);
			}
		}
	}

}
