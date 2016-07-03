package SB;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener
{
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event)
	{
		SMHandler.addPlayer(new SMPlayer(event.getPlayer()));
	}
	
	
}