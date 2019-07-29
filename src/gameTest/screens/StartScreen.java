package gameTest.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class StartScreen implements Screen
{
    @Override
    public void displayOutput(AsciiPanel terminal){
        terminal.write("PROJECT T.A.D.", 34, 1);

        terminal.write("Descend the caves, find the lost Teddy Bear, and return to", 11, 5);
        terminal.write("the surface to win. Use what you find to avoid dying.", 12, 6);

        int y = 11;
        terminal.write("[g] or [,] to pick up", 27, y++);
        terminal.write("[d] to drop", 27, y++);
        terminal.write("[e] to eat", 27, y++);
        terminal.write("[w] to wear or wield", 27, y++);
        terminal.write("[?] for help", 27, y++);
        terminal.write("[x] to examine your items", 27, y++);

        terminal.writeCenter("- - Press [enter] to Start - -", 22);
    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
