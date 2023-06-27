package net.atos.wolf.services;

import lombok.Getter;
import lombok.Setter;
import net.atos.wolf.services.character.Character;

@Getter
@Setter
public class GameSession {

    private String id;
    private Character character;
    private int battleRounds;

    public void section(int section) {
        character.section(section);
    }


}
