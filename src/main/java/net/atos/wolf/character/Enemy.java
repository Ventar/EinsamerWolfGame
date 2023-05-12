package net.atos.wolf.character;

public class Enemy {


    private String name;
    private Attribute combatStrength;
    private Attribute endurance;

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
     *
     * @return
     */
    public Attribute getCombatStrength() {
        return combatStrength;
    }

    /**
     * set enemys combatstrength
     * @param combatStrength
     */
    public void setCombatStrength(Attribute combatStrength) {
        this.combatStrength = combatStrength;
    }

    /**
     * get enemys Endurance
     *
     * @return
     */
    public Attribute getEndurance() {
        return endurance;
    }

    /**
     * set enemys endurance
     * @param endurance
     */
    public void setEndurance(Attribute endurance) {
        this.endurance = endurance;
    }
}
