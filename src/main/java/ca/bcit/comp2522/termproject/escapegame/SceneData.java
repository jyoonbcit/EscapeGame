package ca.bcit.comp2522.termproject.escapegame;

import java.io.Serializable;

public class SceneData implements Serializable {
    // Serial version UID
    private static final long serialVersionUID = 1L;
    // Records win condition
    private boolean hasWon;
    // Records whether player has the screwdriver item
    private boolean hasScrewdriver;
    // Records whether player has the closet key
    private boolean hasClosetKey;

    public SceneData() {
        hasWon = false;
        hasScrewdriver = false;
        hasClosetKey = false;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public void setHasScrewdriver(boolean hasScrewdriver) {
        this.hasScrewdriver = hasScrewdriver;
    }

    public void setHasClosetKey(boolean hasClosetKey) {
        this.hasClosetKey = hasClosetKey;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public boolean isHasScrewdriver() {
        return hasScrewdriver;
    }

    public boolean isHasClosetKey() {
        return hasClosetKey;
    }
}
