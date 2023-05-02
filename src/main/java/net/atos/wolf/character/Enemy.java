package net.atos.wolf.character;

public class Enemy {


    private String name;
    private int combatStrength;
    private int endurance;

    /**
     * Returns the name of the enemy.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the enemy.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the enemies combatstrenght
     * @return
     */
    public int getCombatStrength() {
        return combatStrength;
    }

    /**
     * set enemys combatstrength
     * @param combatStrength
     */
    public void setCombatStrength(int combatStrength) {
        this.combatStrength = combatStrength;
    }

    /**
     * get enemys Endurance
     * @return
     */
    public int getEndurance() {
        return endurance;
    }

    /**
     * set enemys endurance
     * @param endurance
     */
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }
}
