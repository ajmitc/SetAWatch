package setawatch.view;

import setawatch.Model;
import setawatch.game.adventurer.Adventurer;
import setawatch.game.adventurer.AdventurerAbility;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class AdventurerPanel extends JPanel {
    private Model model;
    private View view;

    private List<AdventurerDisplay> adventurerDisplays = new ArrayList<>();

    public AdventurerPanel(Model model, View view){
        super(new BorderLayout());
        this.model = model;
        this.view = view;

    }

    public void init(){
        for (int i = 0; i < model.getGame().getHeroes().size(); ++i){
            Adventurer adventurer = model.getGame().getHeroes().get(i);
            AdventurerDisplay adventurerDisplay =
                    new AdventurerDisplay(
                            adventurer,
                            new Rectangle(
                                    0,
                                    i * adventurer.getImage().getHeight(null),
                                    adventurer.getImage().getWidth(null),
                                    adventurer.getImage().getHeight(null)));
            adventurerDisplays.add(adventurerDisplay);
        }
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics2D g = (Graphics2D) graphics;

        for (int i = 0; i < adventurerDisplays.size(); ++i){
            AdventurerDisplay adventurerDisplay = adventurerDisplays.get(i);
            g.drawImage(adventurerDisplay.getAdventurer().getImage(), adventurerDisplay.getBoardBounds().x, adventurerDisplay.getBoardBounds().y, null);

            Image heroAbilityImage =
                    adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(0).isExhausted()?
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(0).getImageExhausted():
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(0).getImageActive();
            g.drawImage(heroAbilityImage, adventurerDisplay.getAbilityCardBounds1().xpoints[0], adventurerDisplay.getAbilityCardBounds1().ypoints[0], null);
            g.draw(adventurerDisplay.getAbilityCardBounds1());

            heroAbilityImage =
                    adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(1).isExhausted()?
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(1).getImageExhausted():
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(1).getImageActive();
            g.drawImage(heroAbilityImage, adventurerDisplay.getAbilityCardBounds2().xpoints[0], adventurerDisplay.getAbilityCardBounds2().ypoints[0], null);
            g.draw(adventurerDisplay.getAbilityCardBounds2());

            heroAbilityImage =
                    adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(2).isExhausted()?
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(2).getImageExhausted():
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(2).getImageActive();
            g.drawImage(heroAbilityImage, adventurerDisplay.getAbilityCardBounds3().xpoints[0], adventurerDisplay.getAbilityCardBounds3().ypoints[0], null);
            g.draw(adventurerDisplay.getAbilityCardBounds3());
        }
    }

    public void refresh(){
        repaint();
    }

    private static class AdventurerDisplay{
        private Adventurer adventurer;
        private Rectangle boardBounds;
        private Polygon abilityCardBounds1;
        private Polygon abilityCardBounds2;
        private Polygon abilityCardBounds3;

        public AdventurerDisplay(Adventurer adventurer, Rectangle boardBounds){
            this.adventurer = adventurer;
            this.boardBounds = boardBounds;

            int xoffset = 0;
            int yoffset = 0;
            this.abilityCardBounds1 = new Polygon();
            this.abilityCardBounds1.addPoint(boardBounds.x + xoffset, boardBounds.y + yoffset);
            this.abilityCardBounds1.addPoint(
                    boardBounds.x + xoffset + AdventurerAbility.HERO_ABILITY_CARD_WIDTH,
                    boardBounds.y + yoffset);
            this.abilityCardBounds1.addPoint(
                    boardBounds.x + xoffset + AdventurerAbility.HERO_ABILITY_CARD_WIDTH,
                    boardBounds.y + yoffset + adventurer.getAllHeroAbilities().get(0).getImageActive().getHeight(null));
            this.abilityCardBounds1.addPoint(
                    boardBounds.x + xoffset,
                    boardBounds.y + yoffset + adventurer.getAllHeroAbilities().get(0).getImageActive().getHeight(null));
            this.abilityCardBounds1.addPoint(boardBounds.x + xoffset, boardBounds.y + yoffset);

            xoffset = 0;
            yoffset = 0;
            this.abilityCardBounds2 = new Polygon();
            this.abilityCardBounds2.addPoint(boardBounds.x + xoffset, boardBounds.y + yoffset);
            this.abilityCardBounds2.addPoint(
                    boardBounds.x + xoffset + AdventurerAbility.HERO_ABILITY_CARD_WIDTH,
                    boardBounds.y + yoffset);
            this.abilityCardBounds2.addPoint(
                    boardBounds.x + xoffset + AdventurerAbility.HERO_ABILITY_CARD_WIDTH,
                    boardBounds.y + yoffset + adventurer.getAllHeroAbilities().get(0).getImageActive().getHeight(null));
            this.abilityCardBounds2.addPoint(
                    boardBounds.x + xoffset,
                    boardBounds.y + yoffset + adventurer.getAllHeroAbilities().get(0).getImageActive().getHeight(null));
            this.abilityCardBounds2.addPoint(boardBounds.x + xoffset, boardBounds.y + yoffset);


            xoffset = 0;
            yoffset = 0;
            this.abilityCardBounds3 = new Polygon();
            this.abilityCardBounds3.addPoint(boardBounds.x + xoffset, boardBounds.y + yoffset);
            this.abilityCardBounds3.addPoint(
                    boardBounds.x + xoffset + AdventurerAbility.HERO_ABILITY_CARD_WIDTH,
                    boardBounds.y + yoffset);
            this.abilityCardBounds3.addPoint(
                    boardBounds.x + xoffset + AdventurerAbility.HERO_ABILITY_CARD_WIDTH,
                    boardBounds.y + yoffset + adventurer.getAllHeroAbilities().get(0).getImageActive().getHeight(null));
            this.abilityCardBounds3.addPoint(
                    boardBounds.x + xoffset,
                    boardBounds.y + yoffset + adventurer.getAllHeroAbilities().get(0).getImageActive().getHeight(null));
            this.abilityCardBounds3.addPoint(boardBounds.x + xoffset, boardBounds.y + yoffset);
        }

        public Adventurer getAdventurer() {
            return adventurer;
        }

        public Rectangle getBoardBounds() {
            return boardBounds;
        }

        public Polygon getAbilityCardBounds1() {
            return abilityCardBounds1;
        }

        public Polygon getAbilityCardBounds2() {
            return abilityCardBounds2;
        }

        public Polygon getAbilityCardBounds3() {
            return abilityCardBounds3;
        }
    }
}
