package utils;

import helper.Ladder;
import helper.Player;
import helper.Snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Initialize {

    Die die;
    Board board;
    boolean gameOver;
    List<Snake> snakes;
    List<Ladder> ladders;
    List<Player> players;

    public Initialize(HashMap<Integer,Integer> snakes, HashMap<Integer,Integer> ladders, List<String> players) {
        die = new Die();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.players = new ArrayList<>();
        constructSnakes(snakes);
        constructLadder(ladders);
        constructPlayers(players);
        board = new Board(100, this.snakes, this.ladders);
        startGame();
    }

    private void constructPlayers(List<String> players) {
        Player playerNew = new Player();
        for(String player : players){
                playerNew.name = player;
            this.players.add(playerNew);
        }
    }

    private void constructLadder(HashMap<Integer, Integer> ladders) {
        Ladder ladderNew = new Ladder();
        for(Map.Entry<Integer,Integer> ladder : ladders.entrySet()){
            ladderNew.start = ladder.getKey();
            ladderNew.end = ladder.getValue();
            this.ladders.add(ladderNew);
        }
    }

    private void constructSnakes(HashMap<Integer, Integer> snakes) {
        Snake snakeNew = new Snake();
        for(Map.Entry<Integer,Integer> snake : snakes.entrySet()){
            snakeNew.head = snake.getKey();
            snakeNew.tail = snake.getValue();
            this.snakes.add(snakeNew);
        }
    }

    private void startGame() {
        while (!isGameOver()) {
            for (Player player : players) {
                if (isGameOver()) break;
                playTurn(player);
            }
        }
    }

    private void playTurn(Player player) {
        int currentPos = player.getCurrentPosition();
        int rolledValue = rollDice();
        int newPos = currentPos + rolledValue;
        if (newPos > board.getSize()) {
            return;
        } else if (newPos < board.getSize()) {
            movePlayer(player, rolledValue, currentPos, newPos);
        } else {
            movePlayer(player, rolledValue, currentPos, newPos);
            playerHasWon(player);
        }
    }

    private int rollDice() {
        int rolledValue = die.rollDie();
        return rolledValue;
    }

    private void movePlayer(Player player, int rolledValue, int currentPos, int newPos) {
        newPos = checkForLadderAndSnake(newPos);
        printMovement(player.getName(), rolledValue, currentPos, newPos);
        player.setCurrentPosition(newPos);
    }

    private int checkForLadderAndSnake(int position) {
        int currentPos = position;
        while (board.cellHasSnakeOrLadder(currentPos)) {
            if (board.cellHasSnake(currentPos)) {
                currentPos = board.getEndOfSnake(currentPos);
            }
            if (board.cellHasLadder(currentPos)) {
                currentPos = board.getEndOfLadder(currentPos);
            }
        }
        return currentPos;
    }

    private void printMovement(String playerName, int rolledValue, int currentPos, int newPos) {
        System.out.println(playerName + " rolled a " + rolledValue + " and moved from " + currentPos + " to " + newPos);
    }

    private void playerHasWon(Player player) {
        printWinningMessage(player.getName());
        gameOver = true;
    }

    private void printWinningMessage(String playerName) {
        System.out.println(playerName + " wins the game");
    }

    private boolean isGameOver() {
        return gameOver;
    }
}
