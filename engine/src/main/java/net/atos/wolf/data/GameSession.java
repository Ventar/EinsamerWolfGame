package net.atos.wolf.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class GameSession {

    /**
     * The unique ID of the session to differentiate between multiple players
     */
    private String id;

    /**
     * The character that is playing the game.
     */
    private Character character;

    /**
     * The current section that is handled by the session
     */
    private Section section;

    /**
     * List of answer options which are currently available to the character for the given session ID.
     */
    private List<Action> modifiedAnswerOptions = new ArrayList<>();

    /**
     * Battle round counter.
     */
    private int battleRounds = 1;

    /**
     * Information about the battle rounds.
     */
    private List<BattleLogEntry> battleLog = new ArrayList<>();

    /**
     * update time to see how long a sessions exists
     */
    private long lastUsed;

    /**
     * Sets a new section to the session, possible modified answer options were cleared.
     *
     * @param section the section to set
     */
    public void section(Section section) {
        modifiedAnswerOptions.clear();
        this.section = section;
    }

    /**
     * Modifies the section text with the values from the character and battle infos from the section.
     *
     * @return the modified text
     */
    public String getModifiedSectionText() {
        return replaceCharacterVariablesInText(section.text());
    }

    /**
     * Replaces variables in the text with the values from the character in the session
     *
     * @param text the text to modify
     */
    private String replaceCharacterVariablesInText(String text) {
        String result = text.replaceAll("\\$\\{ENDURANCE\\}", String.valueOf(character.endurance().get()));
        result = result.replaceAll("\\$\\{GOLD\\}", String.valueOf(character.gold().get()));
        result = result.replaceAll("\\$\\{FOOD\\}", String.valueOf(character.food().get()));
        result = result.replaceAll("\\$\\{BATTLE_STRENGTH\\}", String.valueOf(character.battleStrength().get()));
        return result;
    }


}
