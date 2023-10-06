package net.atos.wolf.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class GameObject {

    /**
     * Unique ID of the item.
     */
    @Setter
    @Getter
    private String id;

    @Setter
    @Getter
    private String image;

    @Setter
    @Getter
    private String tooltip;

    @Setter
    @Getter
    private String text;


}
