package me.guiihsilva.eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.api.events.SimpleClansJoinEvent;

public class clanjoin implements Listener {

	@EventHandler
	public void onJoinClan(SimpleClansJoinEvent e){
		int maximo = 6;
		if (e.getClan().getAllMembers().size() > maximo){
			e.getClanPlayer().toPlayer().sendMessage("§cEste clan esta lotado! Você não pode entrar nele");
			e.getClan().removeMember(e.getClanPlayer().toPlayer().getName());
			e.getClan().removePlayerFromClan(e.getClanPlayer().toPlayer().getName());
			for (ClanPlayer p : e.getClan().getOnlineMembers()) {
				p.toPlayer().sendMessage("§4 * §cUm jogador tentou entrar no clan, porém o clan já esta com o limite de membros (" + maximo + ") e o jogador não pôde entrar");
			}
		}
	}

}
