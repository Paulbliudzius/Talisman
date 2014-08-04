package items.followers;

import items.Item;

public abstract class Follower extends Item {

    @Override
    public String getType() {
	return "follower";
    }
}
