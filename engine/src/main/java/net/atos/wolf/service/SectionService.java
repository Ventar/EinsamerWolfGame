package net.atos.wolf.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.Section;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Service to provide {@link Section}s to the {@link GameService}.
 *
 * @author Noel Masur, Julius Reismann, Michael Rodenbuecher
 * @since 2023-05-19
 */
@Slf4j
public class SectionService {
    
    private static final class ImageData {
        /**
         * The id which is used in the parent JSON file to replace the image variable.
         */
        public int id;
        /**
         * The image tag to use during replacement.
         */
        public String tag;
        
        /**
         * Returns the id which is used in the parent JSON file to replace the image variable.
         *
         * @return the id
         */
        public int getId() {
            return id;
        }
    }
    
    /**
     * Contains all services of a single book.
     */
    private Map<Integer, Section> sections;
    
    /**
     * Creates a new section service.
     *
     * @param jsonFile the file that contains the sections of the book we want to manage with this service.
     */
    public SectionService(ObjectMapper mapper) {
        
        String jsonFile = "/ew1.json";
        
        try {
            
            sections = mapper.readValue(
                                 // read the JSON file into a list of type Section
                                 SectionService.class.getResourceAsStream(jsonFile),
                                 // load the file from classpath
                                 new TypeReference<List<Section>>() {})                               // tell JACKSON that there is a list in the fiel ([]) and not a single Java  object
                             .stream()
                             .collect(Collectors.toMap(Section::getSectionNumber,
                                                       Function.identity()));  // convert the list to a map with the section number as key
            
        } catch (Exception e) {
            throw new RuntimeException("Could not load " + jsonFile + " file: ", e);
        }
        
    }
    
    /**
     * Scans the passed section text for variables of type image (e.g. {IMG:1}) and replaces them with the corresponding
     * image tag from file system image
     *
     * @param section the section
     *
     * @return the modified section.
     */
    private Section replaceImageVariable(Section section) {
        
        // filter all image tags with a regexp.
        
        Pattern pattern = Pattern.compile("\\{IMG:[0-9]{1,4}\\}");
        Matcher matcher = pattern.matcher(section.text());
        List<String> groups = new ArrayList<>();
        while (matcher.find()) {
            groups.add(matcher.group());
        }
        
        // the groups list will contain all Strings in the {IMG:1} format so that we can split the variable, extract the ID and replace the whole one in the text with the img
        // tag from the image data map
        
        if (!groups.isEmpty()) {
            groups.forEach(g -> {
                try {
                    int imageId = Integer.valueOf(g.substring(g.indexOf(":") + 1, g.indexOf("}")));
                    InputStream imageStream = SectionService.class.getResourceAsStream("/img/" + imageId + ".png");
                    String
                        base64Image =
                        "<img width='100%' src='data:image/png;base64," + Base64.getEncoder()
                                                                                .encodeToString(imageStream.readAllBytes()) + "'/>";
                    ;
                    section.text(section.text().replace(g, base64Image));
                } catch (Exception e) {
                    LOG.debug("Could not resolve image ::= [{}]", g);
                }
            });
        }
        
        return section;
    }
    
    /**
     * Get the section with the passed section number.
     *
     * @param idToLoad the {@link Section#sectionNumber()}  if the section to load
     *
     * @return Section
     */
    public Section getSection(int idToLoad) {
        Section s = sections.get(idToLoad);
        
        if (s == null) {
            throw new IllegalStateException("Section ::= [" + idToLoad + "] is not available in the SectionService");
        }
        
        
        return replaceImageVariable(new Section(s));
    }
    
}
