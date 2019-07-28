package gameTest.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

public class StartScreen implements Screen
{
    @Override
    public void displayOutput(AsciiPanel terminal){
        terminal.write("PROJECT T.A.D.", 1, 1);
        terminal.writeCenter("- - Press [enter] to Start - -", 22);
    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
