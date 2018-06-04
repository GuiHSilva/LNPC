package me.guiihsilva.utils;

public class msg {

	public static String type(String msg){
		if (msg.equalsIgnoreCase("perm")){
			return "§cSem permissão!";
		}
		if (msg.equalsIgnoreCase("<0")){
			return "§cO valor não pode ficar menor que 0 e nem ser negativo!";
		}
		if (msg.equalsIgnoreCase("!int")){
			return "§cO valor precisa ser um número inteiro!";
		}
		if (msg.equalsIgnoreCase("no_player")){
			return "§cJogador não encontrado no banco de dados!";
		}
		return "ERRO DE MENSSAGEM!";
	}
}
