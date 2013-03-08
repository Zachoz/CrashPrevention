package com.zachoz.CrashPrevention;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public class CrashPrevention extends JavaPlugin {

    public final Logger logger = Logger.getLogger("Minecraft");

    public void onDisable() {

	PluginDescriptionFile pdfFile = getDescription();
	this.logger.info(pdfFile.getName() + " is now disabled");
    }

    public void onEnable() {

	PluginDescriptionFile pdfFile = getDescription();
	this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " by Zachoz is now enabled");
	this.saveDefaultConfig();
	checkFreeRam();
	
    }
    
	public int Countdown;
	int count = 31;
	public int tries = 0;

public void shutdowntimer() {
	Countdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		public void run() {
			count--;
			if(count == 30 || count == 20 || count == 15 ||count < 11) {
				Bukkit.broadcastMessage(ChatColor.DARK_RED + "Server will automatically restart in " + ChatColor.AQUA + count + ChatColor.DARK_RED + " seconds!");
				if(count == 0) {
					Bukkit.getServer().getScheduler().cancelTask(Countdown);
					
					for (Player p : Bukkit.getOnlinePlayers()) {
					    p.kickPlayer(ChatColor.RED + "Server automatically restarting due to low available resources! Relog!");
					}
				getServer().shutdown();	
		    }
		}
	    }
	}, 0L, 20L);
}
public int ramCheck;
public void checkFreeRam() {
	ramCheck = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
		public void run() {
		    Runtime runtime = Runtime.getRuntime();
			  
			  if ((runtime.freeMemory() / 1024 / 1024) < getConfig().getInt("minimum_ram")) {
			      tries++;
			      if (getConfig().getBoolean("debug")) {
			      logger.info("Warning, server is below running below defined amount of RAM!");
			    }
			      
			      if (getConfig().getInt("tries") == tries) {
				      Bukkit.broadcastMessage(ChatColor.RED + "Server is running low on available resources! Automatically restarting to prevent lag!");
				      shutdowntimer();
				      Bukkit.getServer().getScheduler().cancelTask(ramCheck);
				  }
			} else {
			    tries = 0;
			}
			  
			  
	    }
	}, 0L, 200L);
}
}
