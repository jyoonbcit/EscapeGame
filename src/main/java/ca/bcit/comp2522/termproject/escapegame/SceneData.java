package ca.bcit.comp2522.termproject.escapegame;

import java.io.Serializable;

/**
 * SceneData.
 *
 * @author Jihoon Yoon, Wilber Lin
 * @version 2023-04-09
 */
public class SceneData implements Serializable {
    // Serial version UID
    private static final long serialVersionUID = 1L;
    // Records win condition
    private boolean hasWon;
    // Records whether player has the screwdriver item
    private boolean hasScrewdriver;
    // Records whether player has the closet key
    private boolean hasClosetKey;
    private long startTime;

    /**
     * Default SceneData constructor.
     */
    public SceneData() {
        hasWon = false;
        hasScrewdriver = false;
        hasClosetKey = false;
        startTime = System.currentTimeMillis();
    }

    /**
     * Sets if player has won.
     * @param hasWon true or false
     */
    public void setHasWon(final boolean hasWon) {
        this.hasWon = hasWon;
    }

    /**
     * Sets if player has screwdriver.
     * @param hasScrewdriver true or false
     */
    public void setHasScrewdriver(final boolean hasScrewdriver) {
        this.hasScrewdriver = hasScrewdriver;
    }

    /**
     * Sets if player has closet key.
     * @param hasClosetKey true or false
     */
    public void setHasClosetKey(final boolean hasClosetKey) {
        this.hasClosetKey = hasClosetKey;
    }

    /**
     * Gets if player has won.
     *
     * @return true if player has won
     */
    public boolean isHasWon() {
        return hasWon;
    }

    /**
     * Gets if player has screwdriver.
     *
     * @return true if player has screwdriver
     */
    public boolean isHasScrewdriver() {
        return hasScrewdriver;
    }

    /**
     * Gets if player has closet key.
     *
     * @return true if player has closet key
     */
    public boolean isHasClosetKey() {
        return hasClosetKey;
    }

    public long getStartTime() {
        return startTime;
    }
}
