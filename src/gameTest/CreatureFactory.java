package gameTest;

import asciiPanel.AsciiPanel;
import java.util.List;

public class CreatureFactory {
    private World world;

    public CreatureFactory(World world)
    {
        this.world = world;
    }

    public Creature newPlayer(List<String> messages) {
        Creature player = new Creature(world, '@', AsciiPanel.brightWhite, 100,20, 0);
        world.addAtEmptyLocation(player, 0);
        new PlayerAi(player, messages);
        return player;
    }

    public Creature newFungus(int depth) {
        Creature fungus = new Creature(world, 'f', AsciiPanel.green, 10, 0, 0);
        world.addAtEmptyLocation(fungus, depth);
        new FungusAi(fungus, this);
        return fungus;
    }

    public Creature newBat(int depth){
        Creature bat = new Creature(world,'b', AsciiPanel.yellow, 15, 5, 0);
        world.addAtEmptyLocation(bat, depth);
        new BatAi(bat);
        return bat;
    }

    public Item newRock(int depth){
        Item rock = new Item(',', AsciiPanel.yellow, "rock");
        world.addAtEmptyLocation(rock, depth);
        return rock;
    }

    public Item newVictoryItem(int depth){
        Item item = new Item('*', AsciiPanel.brightYellow, "teddy bear");
        world.addAtEmptyLocation(item, depth);
        return item;
    }
}
