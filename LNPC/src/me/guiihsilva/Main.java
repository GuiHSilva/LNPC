package me.guiihsilva;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
//import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.guiihsilva.admin.BuildStats;
import me.guiihsilva.admin.admin;
import me.guiihsilva.automsg.automsg;
import me.guiihsilva.cash.menu;
import me.guiihsilva.commands.ajuda;
import me.guiihsilva.commands.ajuda_responder;
import me.guiihsilva.commands.anuncio;
import me.guiihsilva.commands.arena;
import me.guiihsilva.commands.bolao;
import me.guiihsilva.commands.broadcast;
import me.guiihsilva.commands.cabecas;
import me.guiihsilva.commands.cash;
import me.guiihsilva.commands.cc;
import me.guiihsilva.commands.chain;
import me.guiihsilva.commands.comoconseguirchaves;
import me.guiihsilva.commands.daritemdrop;
import me.guiihsilva.commands.enquete;
import me.guiihsilva.commands.fake;
import me.guiihsilva.commands.firework;
import me.guiihsilva.commands.fly;
import me.guiihsilva.commands.formulario;
import me.guiihsilva.commands.home;
import me.guiihsilva.commands.lag;
import me.guiihsilva.commands.lccomandos;
import me.guiihsilva.commands.loja;
import me.guiihsilva.commands.lojas;
import me.guiihsilva.commands.minapvp;
import me.guiihsilva.commands.onlines;
import me.guiihsilva.commands.ping;
import me.guiihsilva.commands.pvpentreclan;
import me.guiihsilva.commands.recompensa;
import me.guiihsilva.commands.reiniciar;
import me.guiihsilva.commands.reportar;
import me.guiihsilva.commands.score;
import me.guiihsilva.commands.securitymode;
import me.guiihsilva.commands.sethome;
import me.guiihsilva.commands.setloja;
import me.guiihsilva.commands.site;
import me.guiihsilva.commands.skype;
import me.guiihsilva.commands.sortear;
import me.guiihsilva.commands.teste;
import me.guiihsilva.commands.tpa;
import me.guiihsilva.commands.tpaceitar;
import me.guiihsilva.commands.tpanegar;
import me.guiihsilva.commands.tpaoff;
import me.guiihsilva.commands.tpaon;
import me.guiihsilva.commands.trocarmotd;
import me.guiihsilva.commands.vender;
import me.guiihsilva.eventos.PlayerJoinListener;
import me.guiihsilva.eventos.admineffectteleport;
import me.guiihsilva.eventos.clanjoin;
import me.guiihsilva.eventos.clansuport;
import me.guiihsilva.eventos.clicknps;
import me.guiihsilva.eventos.comandocl;
import me.guiihsilva.eventos.definirmotd;
import me.guiihsilva.eventos.dontrain;
import me.guiihsilva.eventos.enderpearl;
import me.guiihsilva.eventos.gchat;
import me.guiihsilva.eventos.iteminteract;
import me.guiihsilva.eventos.lclisteners;
import me.guiihsilva.eventos.mercadonegro;
import me.guiihsilva.eventos.minamenu;
import me.guiihsilva.eventos.minerar;
import me.guiihsilva.eventos.moneypay;
import me.guiihsilva.eventos.mycmd;
import me.guiihsilva.eventos.opcmd;
import me.guiihsilva.eventos.playerdie;
import me.guiihsilva.eventos.reloadcmd;
import me.guiihsilva.eventos.reparar;
import me.guiihsilva.eventos.spongejump;
import me.guiihsilva.eventos.staffchatcolor;
import me.guiihsilva.eventos.tranformar;
import me.guiihsilva.eventos.trocarxp;
import me.guiihsilva.eventos.wecalc;
import me.guiihsilva.gui.guiconfig;
//import me.guiihsilva.scoreboard.scoreboard;
import me.guiihsilva.utils.Lag;
import me.guiihsilva.utils.See;
import me.guiihsilva.utils.inutil;
import net.milkbowl.vault.economy.Economy;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;

public class Main extends JavaPlugin {

	public static Main instance;
	public static SimpleClans core2;

	public static SimpleClans sc = null;
	public static SimpleClans core;

	private static JavaPlugin javaPlugin;

	private Connection connection;
    private String host, database, username, password;
    private int port;
	
	
    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            } 
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://" + this.host+ ":" + this.port + "/" + this.database, this.username, this.password);
        }
    }
    
	@Override
	public void onEnable() {
		instance = this;
		//PlaceholderAPIFB.init();
		// * Essa parada aq, é um looping infinito! Sempre que iniciar
		// Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
		// {
		//
		// @Override
		// public void run() {
		//
		//
		//
		// }
		// }, 0, 5*20);
		// *
		//Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.instance, new Runnable() {
//
	//		@Override
		//	public void run() {
			//	scoreboard.updatepage();
	//		}
		//}, 0, 20 * 15);
		

        String conection = "jdbc:mysql://" + getConfig().getString("mysql.host").trim() + ":" + getConfig().getInt("mysql.port") + "/" + getConfig().getString("mysql.database").trim();
        String user = getConfig().getString("mysql.username").trim();
        String pass = getConfig().getString("mysql.password").trim();
        String table = getConfig().getString("mysql.tabela").trim();
        try
        {
          Connection driver = (Connection) DriverManager.getConnection(conection, user, pass);
          if (driver == null)
          {
            getLogger().info("Conexao ao servidor MySQL falhou!");
          }
          else
          {
            getLogger().info("Conectado ao servidor MySQL!");
            Statement statement = (Statement) driver.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS `" + table + "players` (`nome` VARCHAR(16) PRIMARY KEY, `cash` INT);");
            statement.execute("CREATE TABLE IF NOT EXISTS `" + table + "keys` (`key` VARCHAR(16) PRIMARY KEY, `valor` INT);");
            statement.close();
          }
          driver.close();
        }
        catch (Exception e)
        {
          getLogger().info("Conexao ao servidor MySQL falhou!");
          e.printStackTrace();
        }
		
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {

			core2 = (SimpleClans) Bukkit.getServer().getPluginManager().getPlugin("SimpleClans");
			new clicknps(this);
			new PlayerJoinListener(this);
			getWorldGuard();
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
			Bukkit.getConsoleSender().sendMessage(" §a* O plugin " + getName() + " §aesta sendo iniciado!!");

			getServer().getPluginManager().registerEvents(new lclisteners(), this);
			getServer().getPluginManager().registerEvents(new guiconfig(), this);
			getServer().getPluginManager().registerEvents(new spongejump(), this);
			getServer().getPluginManager().registerEvents(new dontrain(), this);
			getServer().getPluginManager().registerEvents(new definirmotd(), this);
			getServer().getPluginManager().registerEvents(new admin(), this);
			getServer().getPluginManager().registerEvents(new reparar(), this);
			getServer().getPluginManager().registerEvents(new BuildStats(), this);
			getServer().getPluginManager().registerEvents(new loja(), this);
			getServer().getPluginManager().registerEvents(new iteminteract(), this);
			getServer().getPluginManager().registerEvents(new arena(), this);
			getServer().getPluginManager().registerEvents(new minerar(), this);
			getServer().getPluginManager().registerEvents(new minerar(), this);
			getServer().getPluginManager().registerEvents(new chain(), this);
			getServer().getPluginManager().registerEvents(new clansuport(), this);
			getServer().getPluginManager().registerEvents(new minamenu(), this);
			getServer().getPluginManager().registerEvents(new inutil(), this);
			getServer().getPluginManager().registerEvents(new gchat(), this);
			getServer().getPluginManager().registerEvents(new minapvp(), this);
			getServer().getPluginManager().registerEvents(new clanjoin(), this);
			getServer().getPluginManager().registerEvents(new playerdie(), this);
			getServer().getPluginManager().registerEvents(new enderpearl(), this);
			getServer().getPluginManager().registerEvents(new tranformar(), this);
			getServer().getPluginManager().registerEvents(new moneypay(), this);
			getServer().getPluginManager().registerEvents(new opcmd(), this);
			getServer().getPluginManager().registerEvents(new vender(), this);
			getServer().getPluginManager().registerEvents(new fly(), this);
			getServer().getPluginManager().registerEvents(new menu(), this);
			getServer().getPluginManager().registerEvents(new mercadonegro(), this);
			getServer().getPluginManager().registerEvents(new reloadcmd(), this);
			getServer().getPluginManager().registerEvents(new home(), this);
			getServer().getPluginManager().registerEvents(new trocarxp(), this);
			getServer().getPluginManager().registerEvents(new setloja(), this);
			getServer().getPluginManager().registerEvents(new lojas(), this);
			getServer().getPluginManager().registerEvents(new wecalc(), this);
			getServer().getPluginManager().registerEvents(new comandocl(), this);
			getServer().getPluginManager().registerEvents(new staffchatcolor(), this);
			getServer().getPluginManager().registerEvents(new mycmd(), this);
			getServer().getPluginManager().registerEvents(new sethome(), this);
			getServer().getPluginManager().registerEvents(new admineffectteleport(), this);

			//registerGlow();

			getCommand("chat").setExecutor(new lccomandos());
			getCommand("limpar").setExecutor(new cc());
			getCommand("enquete").setExecutor(new enquete());
			getCommand("sortear").setExecutor(new sortear());
			getCommand("admin").setExecutor(new admin());
			getCommand("recompensa").setExecutor(new recompensa());
			getCommand("fly").setExecutor(new fly());
			getCommand("pvpentreclan").setExecutor(new pvpentreclan());
			getCommand("setloja").setExecutor(new setloja());
			getCommand("lojas").setExecutor(new lojas());
			getCommand("daritemdrop").setExecutor(new daritemdrop());
			getCommand("lag").setExecutor(new lag());
			getCommand("build").setExecutor(new BuildStats());
			getCommand("onlines").setExecutor(new onlines());
			getCommand("sethome").setExecutor(new sethome());
			getCommand("fake").setExecutor(new fake());
			getCommand("anuncio").setExecutor(new anuncio());
			getCommand("vender").setExecutor(new vender());
			getCommand("bolao").setExecutor(new bolao());
			getCommand("site").setExecutor(new site());
			getCommand("tpa").setExecutor(new tpa());
			getCommand("reiniciar").setExecutor(new reiniciar());
			getCommand("cash").setExecutor(new cash());
			getCommand("tpaceitar").setExecutor(new tpaceitar());
			getCommand("tpaon").setExecutor(new tpaon());
			getCommand("tpaoff").setExecutor(new tpaoff());
			getCommand("tpanegar").setExecutor(new tpanegar());
			getCommand("skype").setExecutor(new skype());
			getCommand("ping").setExecutor(new ping());
			getCommand("chain").setExecutor(new chain());
			getCommand("trocarmotd").setExecutor(new trocarmotd());
			getCommand("teste").setExecutor(new teste());
			getCommand("ajuda").setExecutor(new ajuda());
			getCommand("ajdr").setExecutor(new ajuda_responder());
			getCommand("reportar").setExecutor(new reportar());
			getCommand("manu").setExecutor(new securitymode());
			getCommand("bc").setExecutor(new broadcast());
			getCommand("fogos").setExecutor(new firework());
			getCommand("home").setExecutor(new home());
			getCommand("score").setExecutor(new score());
			getCommand("homes").setExecutor(new home());
			getCommand("cabecas").setExecutor(new cabecas());
			getCommand("sethome").setExecutor(new sethome());
			getCommand("formulario").setExecutor(new formulario());
			getCommand("comoconseguirchaves").setExecutor(new comoconseguirchaves());
			if (!new File(getDataFolder(), "config.yml").exists()) {
				saveDefaultConfig();
				Bukkit.getConsoleSender().sendMessage("  §7- config.yml criada com sucesso!");
			} else {
				Bukkit.getConsoleSender().sendMessage("  §7- config.yml carregada com sucesso!");
			}
			File recompensasFile = new File(getDataFolder() + File.separator + "recompensas");
			if (!recompensasFile.exists()) {
				recompensasFile.mkdirs();
				Bukkit.getConsoleSender().sendMessage("  §7- Pasta recompensas criada!");
			}
			if (!new File(getDataFolder() + File.separator + "recompensas" + File.separator + "recompensas_data.yml")
					.exists()) {
				Bukkit.getConsoleSender().sendMessage("  §7- Arquivo recompensas_data.yml criado!");
				saveResource("recompensas" + File.separator + "recompensas_data.yml", false);
			}
			if (!new File(getDataFolder() + File.separator + "arena_chain.yml").exists()) {
				Bukkit.getConsoleSender().sendMessage("  §7- Arquivo arena_chain.yml criado!");
				saveResource("arena_chain.yml", false);
			}
			if (!new File(getDataFolder() + File.separator + "cabecas_data.yml").exists()) {
				Bukkit.getConsoleSender().sendMessage("  §7- Arquivo cabecas_data.yml criado!");
				saveResource("cabecas_data.yml", false);
			}
			if (!new File(getDataFolder() + File.separator + "denuncias.yml").exists()) {
				Bukkit.getConsoleSender().sendMessage("  §7- Arquivo denuncias.yml criado!");
				saveResource("denuncias.yml", false);
			}
			if (!new File(getDataFolder() + File.separator + "homes.yml").exists()) {
				Bukkit.getConsoleSender().sendMessage("  §7- Arquivo homes.yml criado!");
				saveResource("homes.yml", false);
			}
			setupEconomy();
			// scoreboard.changescore();
			if (Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")) {
				Bukkit.getConsoleSender().sendMessage("  §7- §fPermissionsEx foi encontrado! Hooked!");
			} else {
				Bukkit.getConsoleSender().sendMessage("  §7- §4PermissionsEx não foi encontrado! UnHooked!");

			}
			
			if (!hookSimpleClans()){
				Bukkit.getConsoleSender().sendMessage("  §4- §cSimpleClans não encontrado!!!");
			}

		} else {
			throw new RuntimeException("Não foi possivel encontrar o PlaceholderAPI!! O plugin não vai funcionar!");
		}
		
		a();
		See.see();
		// if (!hookSimpleClans()) {
		// Bukkit.getConsoleSender().sendMessage(" §7- §4SimpleClans não foi
		// encontrado! UnHooked!");
		// } else {
		// Bukkit.getConsoleSender().sendMessage(" §7- §fSimpleClans foi
		// encontrado! Hooked!");
		// }

	//	Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	//
	//		@Override
	//		public void run() {
	//			for (Player p : Bukkit.getOnlinePlayers()) {
	//				if (p.getWorld() != Bukkit.getWorld("world") || p.getWorld() != Bukkit.getWorld("plots")) {
	//					scoreboard.setScore(p);
	//				}
	//			}
	//		}
	//	}, 0, 20 * 10);

	}

	private boolean hookSimpleClans() {
		try {
			for (Plugin plugin : getServer().getPluginManager().getPlugins()) {
				if (plugin instanceof SimpleClans) {
					core = (SimpleClans) plugin;
					return true;
				}
			}
		} catch (NoClassDefFoundError e) {
			return false;
		}

		return false;
	}

	
	

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(" §c* O plugin " + getName() + " §cfoi finalizado com sucesso!");
	}

	private void a() {
		int i = 242;
		new automsg(this).runTaskTimer(this, 0L, 20 * i);
	}

	public static WorldGuardPlugin getWorldGuard() {
		Plugin worldguard = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
		if ((worldguard != null) && ((worldguard instanceof WorldGuardPlugin)) && (worldguard.isEnabled())) {
			return (WorldGuardPlugin) worldguard;
		}
		return null;
	}

	public static Economy econ = null;

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	public static JavaPlugin getPlugin() {
		return javaPlugin;
	}

//	public void registerGlow() {
//		try {
//			Field f = Enchantment.class.getDeclaredField("acceptingNew");
//			f.setAccessible(true);
//			f.set(null, true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			Glow glow = new Glow(70);
//			Enchantment.registerEnchantment(glow);
//		} catch (IllegalArgumentException e) {
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
