package net.atos.wolf.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.atos.wolf.character.*;
import net.atos.wolf.character.Character;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * handles character options
 */
public class CharacterService {
    /**
     * save player specific character
     * @param character
     */
    public void saveCharacter(Character character){

    }

    /**
     * lists current character
     */
    public void listCharacter(){


    }

    /**
     * load saved character
     * @param loadedCharacter
     */
    public void loadCharacter(String loadedCharacter){

    }



    public static void main(String[] args) throws IOException {

        Character juli = new Character();
        Enemy enemy = new Enemy();

        juli.addItemToBackpack(Item.CINDER);
        juli.addItemToBackpack(Item.FIREBOTTLE);
        juli.addItemToBackpack(Item.GOLDENKEY);
        juli.addSpecialItem(SpecialItem.CHAIN_MAIL);
        juli.addSkill(KaiSkill.HEALING);
        juli.addSkill(KaiSkill.HUNTING);
        juli.addSkill(KaiSkill.MENTAL_DEFENCE);


        juli.setEndurance(new Attribute("", 10));
        enemy.setEndurance(10);

        ObjectMapper mapper = new ObjectMapper();
        String fileName = "C:\\Development\\EinsamerWolfGame\\juli.char";
        Path filePath = Path.of(fileName);

        //Files.write(filePath, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(juli).getBytes(StandardCharsets.UTF_8));

        String content = Files.readString(filePath);

        System.out.println(mapper.readValue(content, Character.class));
    }
}
