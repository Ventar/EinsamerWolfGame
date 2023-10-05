package net.atos.wolf.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item extends GameObject {


    /**
     * If an item can be activated in the UI or not
     */
    @Setter
    @Getter
    private Boolean usable;

    /**
     * If {@link #usable} is set to true and the player activates the item, this attribute will be changed by the value in {@link #modificationValue}
     */
    @Setter
    @Getter
    private String modifiedAttribute;

    /**
     * If {@link #usable} is set to true and the player activates the item, this value modifies the attribute with the name specified in [@link
     * {@link #modifiedAttribute}
     */
    @Setter
    @Getter
    private Integer modificationValue;


    @Setter
    @Getter
    private Boolean isWeapon;


}
