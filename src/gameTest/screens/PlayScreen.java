package gameTest.screens;

import java.awt.*;
import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import gameTest.*;

import java.util.ArrayList;
import java.util.List;

public class PlayScreen implements Screen {
    private World world;
    private Creature player;
    private int screenWidth;
    private int screenHeight;
    private List<String> messages;
    private FieldOfView fov;

    public PlayScreen() {
        screenWidth = 80;
        screenHeight = 21;
        messages = new ArrayList<String>();
        createWorld();

        CreatureFactory creatureFactory = new CreatureFactory(world);
        createCreatures(creatureFactory);
    }

    private void createCreatures(CreatureFactory creatureFactory) {
        player = creatureFactory.newPlayer(messages);

        for(int z = 0; z < world.depth(); z++) {
            for(int i = 0; i < 8; i++) {
                creatureFactory.newFungus(z);
            }
            for(int i = 0; i < 20; i++){
                creatureFactory.newBat(z);
            }
        }

    }

    private void createWorld() {
        world = new WorldBuilder(90, 31, 5)
                .makeCaves()
                .build();
    }


    public int getScrollX() { return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth)); }

    public int getScrollY() { return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight)); }

    public void displayOutput(AsciiPanel terminal){
        int left = getScrollX();
        int top = getScrollY();

        displayTiles(terminal, left, top);

        String stats = String.format("%3d/%3d hp", player.hp(), player.maxHp());

        displayMessages(terminal, messages);

        terminal.write(stats,1,23);

        terminal.writeCenter("- - Press [escape] to lose or [enter] to win - -", 22);
    }

    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = screenHeight - messages.size();
        for(int i = 0; i < messages.size(); i++){
            terminal.writeCenter(messages.get(i), top + i);
        }
        messages.clear();
    }

    private void displayTiles(AsciiPanel terminal, int left, int top) {
        fov.update(player.x, player.y, player.z, player.visionRadius());

        for(int x = 0; x < screenWidth; x++){
            for(int y = 0; y < screenHeight; y++){
                int wx = x + left;
                int wy = y + top;

                if(player.canSee(wx, wy, player.z)){
                    terminal.write(world.glyph(wx, wy, player.y), x, y, world.color(wx, wy, player.z));
                }
                else{
                    terminal.write(fov.tile(wx, wy, player.z).glyph(), x, y, Color.darkGray);
                }
            }
        }
    }

    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                return new LoseScreen();

            case KeyEvent.VK_ENTER:
                return new WinScreen();

            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_H:
                player.moveBy(-1,0, 0);
                break;

            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                player.moveBy(1,0, 0);
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                player.moveBy(0, -1, 0);
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                player.moveBy(0,1, 0);
                break;

            case KeyEvent.VK_Y:
                player.moveBy(-1, -1, 0);
                break;

            case KeyEvent.VK_U:
                player.moveBy(1, -1, 0);
                break;

            case KeyEvent.VK_B:
                player.moveBy(-1, 1, 0);
                break;

            case KeyEvent.VK_N:
                player.moveBy(1,1, 0);
                break;
        }

        switch(key.getKeyChar()) {
            case '<':
                player.moveBy( 0, 0, -1);
                break;
            case '>':
                player.moveBy(0, 0, 1);
                break;
        }

        world.update();

        return this;
    }

}
