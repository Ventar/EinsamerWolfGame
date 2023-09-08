package net.atos.wolf.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BattleLogEntry {

    private String text;

    public BattleLogEntry() {
   }

    public BattleLogEntry(String text) {
        this.text = text;
    }
}
