package logic;

public class PlayersRepository{
	
	private Players players;

	public PlayersRepository() {
		super();
		this.players = new Players();
	}

	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		players.addPlayer(player);
		
	}

	public Player getPlayer(long playerId) {
		// TODO Auto-generated method stub
		return players.getPlayer(playerId);
	}

}
