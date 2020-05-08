package logic;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Players implements Iterable<Player> {
	
	private Map<Long, Player> playerIdToPlayer;
	
	public Players() {
		super();
		this.playerIdToPlayer = new ConcurrentHashMap<Long, Player>();
	}

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		playerIdToPlayer.put(player.getId(), player);
	}

	public Player getPlayer(long playerId) {
		// TODO Auto-generated method stub
		return playerIdToPlayer.get(playerId);
	}
	
	public void removePLayer(Player player) {
		// TODO Auto-generated method stub
		playerIdToPlayer.remove(player.getId());
		
	}
	
	public Set<Entry<Long, Player>> getEntrySet() {
		// TODO Auto-generated method stub
		return this.playerIdToPlayer.entrySet();
	}

	@Override
	public Iterator<Player> iterator() {
		// TODO Auto-generated method stub
		return (Iterator<Player>) playerIdToPlayer.values().iterator();
	}


}
