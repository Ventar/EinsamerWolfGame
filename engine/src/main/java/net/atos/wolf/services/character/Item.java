package net.atos.wolf.services.character;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    
    /**
     * Unique ID of the item.
     */
    @Setter
    @Getter
    private String id;
    
    /**
     * If an item can be activated in the UI or not
     */
    @Setter
    @Getter
    private boolean usable;
    
    /**
     * If {@link #usable} is set to true and the player activates the item, this attribute will be changed by the value
     * in {@link #modificationValue}
     */
    @Setter
    @Getter
    private String modifiedAttribute;
    
    /**
     * If {@link #usable} is set to true and the player activates the item, this value modifies the attribute with the
     * name specified in [@link {@link #modifiedAttribute}
     */
    @Setter
    @Getter
    private int modificationValue;
    
}
