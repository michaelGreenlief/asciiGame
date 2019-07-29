package gameTest.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class HelpScreen implements Screen{

    public void displayOutput(AsciiPanel terminal){
        terminal.clear();
        terminal.writeCenter("Goal:", 1);
        terminal.write("Find the lost Teddy Bear, and return to the surface to win.", 1, 3);
        terminal.write("");

        int y = 6;
        terminal.write("[g] or [,] to pick up", 2, y++);
        terminal.write("[d] to drop", 2, y++);
        terminal.write("[e] to eat", 2, y++);
        terminal.write("[w] to wear or wield", 2, y++);
        terminal.write("[?] for help", 2, y++);
        terminal.write("[x] to examine your items", 2, y++);

        terminal.writeCenter(" -- Press any key to continue --", 22);
    }

    public Screen respondToUserInput(KeyEvent key){
        return null;
    }
}
