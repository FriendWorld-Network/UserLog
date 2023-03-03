package fwnet.userlog.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZonedDateTime;

public class main extends JavaPlugin implements Listener {
	
	static main instance;
	
    final String username = getConfig().getString("mysql.username");
    final String password = getConfig().getString("mysql.password");
    final String database = getConfig().getString("mysql.database");
    final String hostname = getConfig().getString("mysql.hostname");
    final String table = getConfig().getString("mysql.table");
    final String options = getConfig().getString("mysql.options");
    final String server = getConfig().getString("plugin.server");
    final String joinmsg = getConfig().getString("plugin.joinmsg");
    final String quitmsg  = getConfig().getString("plugin.quitmsg");
    final String url = "jdbc:mysql://" + hostname + ":3306/" + database + options;
	
    private static Connection con;
    private static Statement stmt;
    private static Connection con2;
    private static Statement stmt2;
    
	@Override
	public void onEnable() {
		instance = this;
		this.getLogger().info("UserLog started!");
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		String nickname = event.getPlayer().getName();
		ZonedDateTime date = ZonedDateTime.now();
		String ip = event.getPlayer().getAddress().getAddress().toString();
		String query = "INSERT INTO `" + table + "` (`id`, `nickname`, `ip`, `date`, `type`, `server`) VALUES (NULL, '" + nickname + "', '" + ip.replace("/", "") + "', '" + date + "', '" + joinmsg + "','" + server + "')";
		try {
            con = DriverManager.getConnection(url, username, password);

            stmt = con.createStatement();
            
            stmt.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		String nickname = event.getPlayer().getName();
		ZonedDateTime date = ZonedDateTime.now();
		String ip = event.getPlayer().getAddress().getAddress().toString();
		String query = "INSERT INTO `" + table + "` (`id`, `nickname`, `ip`, `date`, `type`, `server`) VALUES (NULL, '" + nickname + "', '" + ip.replace("/", "") + "', '" + date + "', '" + quitmsg +  "', '" + server + "')";
		try {
            con2 = DriverManager.getConnection(url, username, password);

            stmt2 = con2.createStatement();
            
            stmt2.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt2.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("UserLog disabled! Goodbye!");
		instance = null;
	}
}
