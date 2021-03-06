package cl.josedev.LeParty;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import cl.josedev.LeParty.commands.PartyAdminCommand;
import cl.josedev.LeParty.commands.PartyCommand;
import cl.josedev.LeParty.listeners.ChatListener;
import cl.josedev.LeParty.listeners.GUIListener;
import cl.josedev.LeParty.listeners.PlayerListener;

public class LeParty extends JavaPlugin {
	
	public int bonusPct = 1;
	public boolean teleportAllowed = false;
	public int invitationDuration = 30;
	public static String TAG = ChatColor.WHITE + "[" + ChatColor.BLUE + ChatColor.BOLD + "Grupo" + ChatColor.WHITE + "] " + ChatColor.RESET;
	public static String PERM_USER = "party.user";
	public static String PERM_ADMIN = "party.admin";
	private static LeParty instance;
	private PartyManager manager;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		bonusPct = this.getConfig().getInt("fullPartyBonusPct");
		teleportAllowed = this.getConfig().getBoolean("teleportAllowed");
		invitationDuration = this.getConfig().getInt("invitationDuration");
		
		manager = new PartyManager();
		
		getCommand("party").setExecutor(new PartyCommand(this));
		getCommand("partyadmin").setExecutor(new PartyAdminCommand(this));
		
		getServer().getPluginManager().registerEvents(new ChatListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		getServer().getPluginManager().registerEvents(new GUIListener(this), this);
		instance = this;
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public PartyManager getManager() {
		return this.manager;
	}

	public static LeParty getInstance() {
		return instance;
	}
	
}
