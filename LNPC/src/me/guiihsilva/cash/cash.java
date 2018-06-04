package me.guiihsilva.cash;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import me.guiihsilva.Main;
public class cash {

	private static Connection conn = null;
	@SuppressWarnings("unused")
	private static String host = Main.instance.getConfig().getString("mysql.host").trim();
	@SuppressWarnings("unused")
	private static int port = Main.instance.getConfig().getInt("mysql.port");
	@SuppressWarnings("unused")
	private static String user = Main.instance.getConfig().getString("mysql.username").trim();
	@SuppressWarnings("unused")
	private static String pass = Main.instance.getConfig().getString("mysql.password").trim();
	private static String table = Main.instance.getConfig().getString("mysql.tabela").trim();
	private static String prefix = "[LEGENDMANIA] ";

	public static void modifyCash(Player p, int valor, String tipo) {
		getDb(p, valor, tipo);
	}

	private static Connection getConnection() throws SQLException {
		if (conn == null) {
			openConnection();
		} else if (!isConnectionValid()) {
			closeConnection();
			openConnection();
		}
		return conn;
	}

	private static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				System.out.println(prefix + "Erro ao fechar conexão: " + e);
			}
		}
	}

	private static boolean isConnectionValid() {
		boolean available = false;
		if (conn != null) {
			Statement stmt = null;
			ResultSet rs = null;
			try {
			//	System.out.println(prefix + "Checando conexão - Dando PING no servidor MySQL");
				stmt = (Statement) conn.createStatement();
				rs = stmt.executeQuery(/* ping */"SELECT 1");
				available = true;
			} catch (SQLException e) {
				System.out.println(prefix + "A conexão com o MySQL está indisponível");
				available = false;
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					System.out.println(prefix + "Erro ao fechar o statement/result set: " + e);
				}
				rs = null;
				stmt = null;
			}
		}
		return available;
	}

	public static Connection openConnection() {
		String conection = "jdbc:mysql://" + Main.instance.getConfig().getString("mysql.host").trim() + ":"
				+ Main.instance.getConfig().getInt("mysql.port") + "/"
				+ Main.instance.getConfig().getString("mysql.database").trim();
		String user = Main.instance.getConfig().getString("mysql.username").trim();
		String pass = Main.instance.getConfig().getString("mysql.password").trim();
		@SuppressWarnings("unused")
		Connection driver = null;
		try {
			System.out.println("[LEGENDMANIA] Conexao com o MySQL estabelecida!");
			conn = (Connection) DriverManager.getConnection(conection, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static String addKey(String key, int valor){
		PreparedStatement select = null;
		try {
			System.out.println("[LEGENDMANIA] Imprimindo uma nova key no banco de dados: " + key + " (Valor: " + valor + ")");
			select = (PreparedStatement) getConnection().prepareStatement("INSERT INTO `" + table + "keys` VALUES ('" + key + "'," + valor + ");");
			select.executeUpdate();
			System.out.println("[LEGENDMANIA] Key impressa com sucesso!");
			return "KeyAdicionada";
		} catch (SQLException e) {
			e.printStackTrace();
			return "KeyNaoAdicionada";
		}
	}
	

	public static String getKeys(){
		@SuppressWarnings("unused")
		PreparedStatement select = null;
		ResultSet rs = null;
		try {
			 @SuppressWarnings("null")
			ResultSetMetaData metadata = (ResultSetMetaData) rs.getMetaData();
			    int columnCount = metadata.getColumnCount();
			    for (int i=1; i<=columnCount; i++) 
			    {
			        String columnName = metadata.getColumnName(i);
			        System.out.println(columnName);
			    }
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static String ativarCash(Player p, String key){
		PreparedStatement select = null;
		try {
			select = (PreparedStatement) getConnection()
					.prepareStatement("SELECT `valor` FROM `" + table + "keys` WHERE `key`='" + key + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			result = select.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String valor = null;
		if (result != null) {
			try {
				while (result.next()) {
					valor = result.getInt("valor") + "";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {

				PreparedStatement select2 = null;
				@Override
				public void run() {
					try {
						select2 = (PreparedStatement) getConnection().prepareStatement("DELETE FROM `cash_keys` WHERE `cash_keys`.`key` = \'" + key + "\'");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						select2.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}	
				}
			}, 1);
			return "" + valor;
		}
		return "null";
	}
	
	public static String getCash(Player p) {
		PreparedStatement select = null;
		try {
			select = (PreparedStatement) getConnection()
					.prepareStatement("SELECT `cash` FROM `" + table + "players` WHERE `nome`='" + p.getName() + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet result = null;
		try {
			result = select.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result != null) {
			try {
				while (result.next()) {
					return result.getInt("cash") + "";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			PreparedStatement create = null;
			try {
				create = (PreparedStatement) getConnection().prepareStatement(
						"INSERT INTO `" + table + "players` VALUES ('" + p.getName() + "',0);");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				create.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		PreparedStatement create = null;
		try {
			create = (PreparedStatement) getConnection().prepareStatement(
					"INSERT INTO `" + table + "players` VALUES ('" + p.getName() + "',0);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			create.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ERRO INTERNO";
	}

	public static void getDb(Player p, int valor, String tipo) {
		if (tipo == "set") {
			try {
				PreparedStatement select = (PreparedStatement) getConnection().prepareStatement(
						"SELECT `cash` FROM `" + table + "players` WHERE `nome`='" + p.getName() + "';");
				ResultSet result = select.executeQuery();
				if (result.next()) {
					PreparedStatement update = (PreparedStatement) getConnection().prepareStatement("UPDATE `" + table
							+ "players` SET `cash`=" + valor + " WHERE `nome`='" + p.getName() + "';");
					update.executeUpdate();
				} else {
					PreparedStatement create = (PreparedStatement) getConnection().prepareStatement(
							"INSERT INTO `" + table + "players` VALUES ('" + p.getName() + "'," + valor + ");");
					create.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		}
		if (tipo == "add") {
			int quantidade = 0;
			try {
				PreparedStatement select = (PreparedStatement) getConnection().prepareStatement(
						"SELECT `cash` FROM `" + table + "players` WHERE `nome`='" + p.getName() + "';");
				ResultSet result = select.executeQuery();
				if (result.next()) {
					quantidade = result.getInt("cash") + valor;
					PreparedStatement update = (PreparedStatement) getConnection().prepareStatement("UPDATE `" + table
							+ "players` SET `cash`=" + quantidade + " WHERE `nome`='" + p.getName() + "';");
					update.executeUpdate();
				} else {
					PreparedStatement create = (PreparedStatement) getConnection().prepareStatement(
							"INSERT INTO `" + table + "players` VALUES ('" + p.getName() + "'," + valor + ");");
					create.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		}

		if (tipo == "remove") {
			int quantidade = 0;
			try {
				PreparedStatement select = (PreparedStatement) getConnection().prepareStatement(
						"SELECT `cash` FROM `" + table + "players` WHERE `nome`='" + p.getName() + "';");
				ResultSet result = select.executeQuery();
				if (result.next()) {
					quantidade = result.getInt("cash") - valor;
					PreparedStatement update = (PreparedStatement) getConnection().prepareStatement("UPDATE `" + table
							+ "players` SET `cash`=" + quantidade + " WHERE `nome`='" + p.getName() + "';");
					update.executeUpdate();
				} else {
					PreparedStatement create = (PreparedStatement) getConnection().prepareStatement(
							"INSERT INTO `" + table + "players` VALUES ('" + p.getName() + "'," + valor + ");");
					create.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		}
	}

}
