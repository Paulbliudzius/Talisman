package items;

public abstract class Object extends Item {

    /**
     * gives gold. Will usually be destroyed after givinng the player gold
     * 
     * @return
     */
    public abstract int goldBonus();

}
