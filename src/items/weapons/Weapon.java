package items.weapons;

import items.Object;

public abstract class Weapon extends Object {
    /**
     * Item's strength bonus
     * 
     * @return
     */

    @Override
    public String getType() {
	return "weapon";
    }

    public abstract int strengthBonus();

    /**
     * Items craft bonus
     * 
     * @return
     */
    public abstract int craftBonus();

    @Override
    public int goldBonus() {
	return 0;
    }
}
