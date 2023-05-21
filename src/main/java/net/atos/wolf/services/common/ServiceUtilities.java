package net.atos.wolf.services.common;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class with functionality used by multiple services.
 */
public final class ServiceUtilities {

    /**
     * Jackson mapper to deserialize JSON data into Java objects.
     */
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

}
