package items.armour;

import items.Object;

public abstract class Armours extends Object {
    @Override
    public int goldBonus() {
	return 0;
    }

    @Override
    public String getType() {
	return "armour";
    }
}
