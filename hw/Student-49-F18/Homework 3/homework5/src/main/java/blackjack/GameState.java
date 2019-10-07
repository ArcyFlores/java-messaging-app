package blackjack;

/**
 * The interface Game state.
 */
public interface GameState {
  /**
   * Start game.
   */
  public void startGame();

  /**
   * End player turn.
   */
  public void endPlayerTurn();

  /**
   * End round.
   */
  public void endRound();

  /**
   * Reset game.
   */
  public void resetGame();
}
