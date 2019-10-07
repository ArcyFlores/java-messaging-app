package problem1.game;

import java.util.Map;

import problem1.decks.Deck;

/**
 * A GameDeck adds additional functions to the Game contract.
 * Specifically a method for retrieving games.
 */
public interface GameDeck extends Game {

  /**
   * Method to retrieve a deck from a Game.
   */
  Deck getDeck();

  /**
   * Method to return the hands from a deck.
   *
   * @return all the current hands in play during a game
   */
  Map getHands();
}
