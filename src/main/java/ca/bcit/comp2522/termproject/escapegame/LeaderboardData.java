package ca.bcit.comp2522.termproject.escapegame;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * LeaderboardData.
 *
 * @author Jihoon Yoon, Wilber Lin
 * @version 2023-04-09
 */
public class LeaderboardData implements Serializable {
    // Serial version UID
    private static final long serialVersionUID = 1L;
    private LinkedList<Long> leaderboard;
    public LeaderboardData() {}

    public LinkedList<Long> getLeaderboard() {
        return leaderboard;
    }
    public void addTime (long elapsedTime) {
        leaderboard.add(elapsedTime);
    }

    @Override
    public String toString() {
        return "LeaderboardData{" +
                "leaderboard=" + leaderboard +
                '}';
    }
}
