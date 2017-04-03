package main.java.org.daedalus.tests.sokoban;

import main.java.org.daedalus.architecture.GameObject;
import main.java.org.daedalus.architecture.Scene;
import main.java.org.daedalus.architecture.components.SpriteRenderer;
import main.java.org.daedalus.graphics.types.Sprite;
import main.java.org.daedalus.graphics.types.Texture2D;
import main.java.org.daedalus.math.Rect;
import org.joml.Vector2f;

/**
 * Created by 1622565 on 23/03/2017.
 */
public class Level1 extends Scene {

    private Texture2D playerTexture;
    private Texture2D wallTexture;

    private Sprite playerSprite;
    private Sprite wallSprite;

    private GameObject player;
    private GameObject[][] grid;

    public Level1() {
        super("Level 1");
    }

    @Override
    public void Initialise() {
        playerTexture = Texture2D.Create("resources/test/sokoban/player.png");
        wallTexture = Texture2D.Create("resources/test/sokoban/wall.png");
        playerSprite = Sprite.Create(playerTexture, new Rect(0, 0, playerTexture.getWidth(), playerTexture.getHeight()));
        wallSprite = Sprite.Create(wallTexture, new Rect(0, 0, wallTexture.getWidth(), wallTexture.getHeight()));
        player = new GameObject("Player");
        AddGameObject(player);

        SpriteRenderer renderer = player.AddComponent(new SpriteRenderer());
        renderer.SetSprite(playerSprite);

        for (int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                GameObject cell = new GameObject("cell_" + x + "_" + y);
                cell.getTransform().position.set(x * 10, y * 10, 0);
                cell.getTransform().scale.set(0.1f, 0.1f);
                AddGameObject(cell);

                SpriteRenderer r = cell.AddComponent(new SpriteRenderer());
                r.SetSprite(wallSprite);
            }
        }

    }

    @Override
    public void Update() {

    }
}
