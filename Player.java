package mru.game.model;

public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */

private String name;
private int balance;
private int numberOfWins;

public Player(String name, int balance, int numberOfWins) {
    this.name = name;
    this.balance = balance;
    this.numberOfWins = numberOfWins;

	// "this" refers to the current Player object, so this.name is the field and name is the value passed into the constructor.
}
}

