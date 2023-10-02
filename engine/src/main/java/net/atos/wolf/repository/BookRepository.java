package net.atos.wolf.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.atos.wolf.data.Action;
import net.atos.wolf.data.Book;
import net.atos.wolf.data.Item;
import net.atos.wolf.data.Section;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class BookRepository {

    private ObjectMapper jsonMapper;

    private Book book;


    public BookRepository(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;

        String jsonFile = "/ew1.json";

        try {
            this.book = jsonMapper.readValue(getClass().getResourceAsStream(jsonFile), Book.class);

            for (Item item : book.items()) {
                item.image(getItemImage(item.image()));
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not load " + jsonFile + " file: ", e);
        }

    }

    private String getItemImage(String fileName) {
        try {
            InputStream imageStream = BookRepository.class.getResourceAsStream("/img/items/" + fileName);
            return "data:image/svg+xml;base64," + Base64.getEncoder().encodeToString(imageStream.readAllBytes());
        } catch (Exception e) {
            LOG.debug("Could not resolve image ::= [{}]", fileName);
            throw new RuntimeException(e);
        }
    }

    /**
     * Scans the passed section text for variables of type image (e.g. {IMG:1}) and replaces them with the corresponding image tag from file system image
     *
     * @param section the section
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
                    InputStream imageStream = BookRepository.class.getResourceAsStream("/img/section/" + imageId + ".png");
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
     * Every action in the JSON file has only the unique ID of an item assigned to reduce duplicated entries. During a call to {@link #getSection(int)} the
     * section get an item assigned from the item list of the book based on the item ID.
     *
     * @param section the section to add potential item information
     */
    private void addItemToAction(Section section) {
        for (Action action : section.actions()) {
            if (action.itemId() != null) {
                for (Item item : book.items()) {
                    if (item.id().equals(action.itemId())) {
                        action.item(item);
                    }
                }
            }
        }
    }

    private void addWeaponToAction(Section section) {
        for (Action action : section.actions()) {
            if (action.weaponId() != null) {
                for (Item item : book.items()) {
                    if (item.id().equals(action.weaponId())) {
                        action.weapon(item);
                    }
                }
            }
        }
    }

    /**
     * Loads an unmodified section from the book.
     *
     * @param idToLoad the id of the section
     * @return the section
     * @throws IllegalStateException if the section with the given id does not exist
     */
    private Section getSectionFromBook(int idToLoad) {
        for (Section section : book.sections()) {
            if (section.sectionNumber() == idToLoad) {
                return section;
            }
        }
        throw new IllegalStateException("Section ::= [" + idToLoad + "] is not available in the SectionService");
    }

    /**
     * Get the section with the passed section number.
     *
     * @param idToLoad the {@link Section#sectionNumber()}  if the section to load
     * @return Section
     */
    public Section getSection(int idToLoad) {
        Section s = new Section(getSectionFromBook(idToLoad));
        replaceImageVariable(s);
        addItemToAction(s);
        addWeaponToAction(s);
        return s;
    }


}
