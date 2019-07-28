package gameTest.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class WinScreen implements Screen {

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("Congrats, you won the game.", 1, 1);
        terminal.writeCenter("- - Press [enter] to restart - -", 22);
    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen(): this;
    }
}
