package client;

import utils.Initialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int head , tail , start , end = 0 ;
        String players;
        System.out.println("Welcome to Snake and Ladder Game");
        System.out.println("Enter the number of snakes");
        Scanner s = new Scanner(System.in);
        int numberOfSnake = s.nextInt();
        HashMap<Integer,Integer> hmapSnake = new HashMap<>();
        HashMap<Integer,Integer> hmapLadder = new HashMap<>();
        ArrayList<String> setPlayers = new ArrayList<>();
        for(int i=0;i<numberOfSnake;i++){
            System.out.println("Enter the head and tail of each snake");
            head = s.nextInt();
            tail = s.nextInt();
            hmapSnake.put(head,tail);
        }
        System.out.println("Enter the number of ladders");
        int numberOfLadder = s.nextInt();
        for(int i=0;i<numberOfLadder;i++){
            System.out.println("Enter the head and tail of each snake");
            start = s.nextInt();
            end = s.nextInt();
            hmapSnake.put(start,end);
        }
        System.out.println("Enter the number of players");
        int numberOfPlayers = s.nextInt();
        for(int i=0;i<numberOfPlayers;i++){
            System.out.println("Enter the name of each players");
            players= s.next();
            setPlayers.add(players);
        }
        Initialize initialize = new Initialize(hmapSnake,hmapLadder,setPlayers);

    }
}
