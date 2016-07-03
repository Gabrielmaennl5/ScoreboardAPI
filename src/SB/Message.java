package SB;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Message implements OfflinePlayer
{
    private String name;
    private int line;

    public Message(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
    	this.name = name;
    }
   
    public void setLine(int line)
    {
    	this.line = line;
    }
    
    public int getLine()
    {
    	return this.line;
    }

    @Override
    public Map<String, Object> serialize()
    {
        return null;
    }

    @Override
    public UUID getUniqueId()
    {
        return UUID.randomUUID();
    }

    @Override
    public boolean isOp()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setOp(boolean value)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Location getBedSpawnLocation()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getFirstPlayed()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public long getLastPlayed()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Player getPlayer()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasPlayedBefore()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isBanned()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isOnline()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isWhitelisted()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setBanned(boolean banned)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void setWhitelisted(boolean value)
    {
        // TODO Auto-generated method stub

    }
}
