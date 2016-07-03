package SB;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SMPlayer
{
	private Player player;
	
	private Scoreboard scoreboard;
	
	private boolean hasBoard;
	
    private String title;
    private List<Team> teams;
    private List<Message> messages = new ArrayList<Message>();;
    private Map<String, Integer> scores;
    
    public SMPlayer(Player p)
    {
            this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
            this.teams = Lists.newArrayList();
            this.scores = Maps.newLinkedHashMap();
            this.player = p;
            hasBoard = false;
    } 
    
	public Scoreboard getScoreboard()
	{
		return scoreboard;
	}
	
	public boolean hasBoard()
	{
		return hasBoard;
	}
	
	public void setHasBoard(boolean trueorfalse)
	{
		hasBoard = trueorfalse;
		if(trueorfalse = false)
		{
			player.setScoreboard(null);
		}
	}

	public Player getPlayer()
	{
		return player;
	}      
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	                  
		public void addSpace() {	
            add(ChatColor.GREEN.toString());
    }

    public void add(String text) {
            add(text, null);    
    }

    public void add(String text, Integer score) {
    	
            Preconditions.checkArgument(text.length() < 48, "scoreboard text cannot contain over 48 letters!");
            text = fixDuplicates(text);
            scores.put(text, score);
            
    }

    private String fixDuplicates(String text) {
    	
    	    while (scores.containsKey(text)) {
                    text += "¤r";
            }
            if (text.length() > 48) {
                    text = text.substring(0, 47);
            }
            return text;
    }

    private Map.Entry<Team, String> createTeam(String text) {
    	
            String result = "";
            if (text.length() <= 16)
            {
            	return new AbstractMap.SimpleEntry<>(null, text);
            }
            Team team = scoreboard.registerNewTeam("text-" + scoreboard.getTeams().size());
            Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
            team.setPrefix(iterator.next());
            result = iterator.next();
            if (text.length() > 32)
                    team.setSuffix(iterator.next());
            teams.add(team);
            return new AbstractMap.SimpleEntry<>(team, result);
    }

    public void build() {
    	
            Objective obj = scoreboard.registerNewObjective((title.length() > 16 ? title.substring(0, 15) : title), "dummy");
            obj.setDisplayName(title);
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            
            int index = scores.size();
            
            for (Map.Entry<String, Integer> text : scores.entrySet()) {
                    Map.Entry<Team, String> team = createTeam(text.getKey());
                    Integer score = text.getValue() != null ? text.getValue() : index;
                    final Map.Entry<Team, String> teamf = team;
                    Message message = new Message(teamf.getValue());
                    if (team.getKey() != null)
                          team.getKey().addPlayer(message);
                    obj.getScore(message).setScore(score);
                    index -= 1;
                    message.setLine(score);
                    this.messages.add(message);
            }
            
    }
    
    public int getStringLine(String string)
    {
    	int line = 0;
    	for(Message msg : this.messages)
    	{
    		if(msg.getName().contains(string))
    		{
    			line = msg.getLine();
    		}
    	}
    	return line;
    }
    
    public void updateLine(int line, String newMsg)
    {
    	Objective obj = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
    	for(Message msg : this.messages)
    	{
    		if(msg.getLine() == line) {
    			scoreboard.resetScores(msg.getName());
    			msg.setName(newMsg);
    			add(newMsg, line);
    			int index = scores.size();
    			for (Map.Entry<String, Integer> text : scores.entrySet()) {
                    obj.getScore(msg).setScore(line);
                    index -= 1;
                    msg.setLine(line);
                }
    		}
    	}
    }
    
    public void removeLine(String line)
    {
    	Objective obj = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
    	if(!obj.getScore(line).equals(0))
    	{
    		Scoreboard sb = player.getScoreboard();
    		sb.resetScores(line);
    	}
    }
    
    public void resetScores() {
        if(player.getScoreboard()!=null) {
            for (String s : player.getScoreboard().getEntries()) {
                player.getScoreboard().resetScores(s);
            }
        }
        for(String s : scoreboard.getEntries()){
            scoreboard.resetScores(s);
        }
    }

    public void clearScores()
    {
        scores.clear();
        teams.clear();
    }

    public void sendScoreboard()
    {
    	player.setScoreboard(scoreboard);
    	this.hasBoard = true;
    }
	
}