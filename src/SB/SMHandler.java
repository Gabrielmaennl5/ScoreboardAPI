package SB;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class SMHandler
{
	private static List<SMPlayer> players = new ArrayList<SMPlayer>();

	public static SMPlayer getPlayer(Player player)
	{
		for(SMPlayer smp : players)
		{
			if(smp.getPlayer().equals(player))
				return smp;
		}
		return null;
	}

	public static boolean addPlayer(SMPlayer smp)
	{
		return players.add(smp);
	}

	public static boolean removePlayer(Player player)
	{
		for(SMPlayer smp : players)
		{
			if(smp.getPlayer().equals(player))
				return players.remove(smp);
		}
		return false;
	}

}