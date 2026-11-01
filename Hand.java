// Noor part 2 (~30 min?)
// here we're giving the hand a deck and counting the cards, calculating whether the player got a blackjack or not!

package mru.game.model;
import java.util.ArrayList;
import mru.game.controller.Card;

// A Hand holds the cards and works out their score
public class Hand {

	// The list of cards currently in this hand:
	private ArrayList<Card> cards;

	// So that means every new hand has an empty list of cards! (use a constructor to create a new object)
	public Hand() {
		cards = new ArrayList<>();
	}

	// Add one card to the hand when it is dealt:
	public void addCard(Card card) {
		cards.add(card);
	}

	// Return all the cards in the hand so they can be displayed:
	public ArrayList<Card> getCards() {
		return cards;
	}

	// Part 1: starting values
	public int getScore() {
		int total = 0;
		boolean hasAce = false;
    // hasAce is set to false until we've seen an ace

		// Part 2: Go through every card
		for (Card c : cards) {
			int rank = c.getRank();

			// if we saw an ace, set hasAce to true
			if (rank == 1) {
				hasAce = true;
			}

			// Jack, Queen and King (11, 12, 13) are worth 10
			if (rank > 10) {
				total += 10;
			} else {
				total += rank;
			}
		}

		// Part 3: if there is an ace and counting it as 11 stays at 21 or under, add 10
		if (hasAce && total + 10 <= 21) {
			total += 10;
		}
		return total;
	}

	// A blackjack (natural) is two cards that add up to 21
	public boolean isNatural() {
		return cards.size() == 2 && getScore() == 21;
	}

	// Bust means the score is over 21, so the hand loses.
	public boolean isBust() {
		return getScore() > 21;
	}
}
