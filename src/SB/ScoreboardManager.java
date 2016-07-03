package SB;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static ScoreboardManager plugin;
	public final PlayerListener pl = new PlayerListener();

	@Override
	public void onEnable()
	{
		plugin = this;

		getServer().getPluginManager().registerEvents(pl, this);

		for(Player player : getServer().getOnlinePlayers())
		{
			if(player == null)
				continue;
			SMPlayer smp = new SMPlayer(player);
			SMHandler.addPlayer(smp);
		}
		
	}

}

