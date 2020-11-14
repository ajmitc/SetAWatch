package setawatch.view;

import setawatch.Model;
import setawatch.game.CampAction;
import setawatch.game.Dice;
import setawatch.game.adventurer.Adventurer;
import setawatch.game.adventurer.AdventurerAbility;
import setawatch.game.creature.Creature;
import setawatch.game.location.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePanel extends JPanel {
    private static final Stroke NORMAL_STROKE = new BasicStroke(1.f);

    private static final int BOARD_WIDTH = 720;
    private static final int MAX_LINE_DISPLAYED = 5;
    private static final Font COORD_FONT = new Font("Serif", Font.PLAIN, 12);
    private static final Font LINE_FONT = new Font("Serif", Font.BOLD, 20);
    private static final Font HORDE_TITLE_FONT = new Font("Serif", Font.BOLD, 16);
    private static final Font HORDE_SIZE_FONT = new Font("Serif", Font.BOLD, 40);
    private static final Color AT_CAMP_BORDER_COLOR = Color.RED;
    private static final Stroke AT_CAMP_BORDER_STROKE = new BasicStroke(2.f);

    private static final int CAMP_TOKEN_WIDTH = 50;

    private static final Point[] FIREWOOD_COORDS = {
            new Point(340, 315),   // 0
            new Point(385, 320),
            new Point(420, 450),
            new Point(445, 386),
            new Point(455, 425),
            new Point(445, 460),
            new Point(420, 507),
            new Point(385, 525),  // 7
            new Point(340, 535),
            new Point(300, 530),
            new Point(260, 500),
            new Point(235, 460),
            new Point(230, 415),  // 12
            new Point(235, 380),
            new Point(260, 340),
            new Point(300, 318)   // 15
    };

    private static final Map<CampAction, Rectangle> CAMP_ACTION_COORDS = new HashMap<>();
    static {
        CAMP_ACTION_COORDS.put(CampAction.CHECK_MAP,      new Rectangle(65, 307, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.EQUIP,          new Rectangle(65, 409, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.HEAL,           new Rectangle(65, 516, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.SCOUT_AHEAD1,   new Rectangle(464, 305, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.SCOUT_AHEAD2,   new Rectangle(530, 305, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.SCOUT_AHEAD3,   new Rectangle(597, 305, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.CHOP_FIREWOOD1, new Rectangle(465, 515, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.CHOP_FIREWOOD2, new Rectangle(530, 515, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.CHOP_FIREWOOD3, new Rectangle(595, 515, 60, 60));
        CAMP_ACTION_COORDS.put(CampAction.VANQUISH,       new Rectangle(175, 120, 40, 40));
        CAMP_ACTION_COORDS.put(CampAction.BOLSTER,        new Rectangle(242, 160, 40, 40));
        CAMP_ACTION_COORDS.put(CampAction.SEAL,           new Rectangle(173, 200, 40, 40));
    }

    private static final Point locationBackCoord    = new Point(66, 75);
    private static final Point currentLocationCoord = new Point(372, 75);
    private static final Point hordeCoord           = new Point(66, 620);
    private static final int HORDE_CARD_WIDTH       = 80;

    private static final Point adventurerBoardOffset = new Point(BOARD_WIDTH, 0);
    private static final Point lineOffset = new Point(0, 743);



    private Model model;
    private View view;

    private List<AdventurerDisplay> adventurerDisplays = new ArrayList<>();

    private Image boardImage;
    private Image firewoodImage;
    private Image firewoodImageBig;
    private Image locationBack;
    private Image creatureBack;
    private Image hordeBack;
    private Image camp1;
    private Image camp2;

    private Dice diceDragging = null;
    private int diceDraggingX, diceDraggingY;

    private int mx, my;

    public GamePanel(Model model, View view){
        super(new BorderLayout());
        this.model = model;
        this.view = view;

        boardImage       = ImageUtil.get("Main Board.png", BOARD_WIDTH);
        firewoodImage    = ImageUtil.get("Fire Token.png", 40, "Fire Token Small");
        firewoodImageBig = ImageUtil.get("Fire Token.png", Creature.CREATURE_CARD_WIDTH, "Fire Token Big");
        locationBack     = ImageUtil.get("Loc Back.jpg", Location.CARD_WIDTH);
        creatureBack     = ImageUtil.get("Enemy Back.png", Creature.CREATURE_CARD_WIDTH);
        camp1            = ImageUtil.get("Camp1.png", CAMP_TOKEN_WIDTH);
        camp2            = ImageUtil.get("Camp2.png", CAMP_TOKEN_WIDTH);
        hordeBack        = ImageUtil.get("Enemy Back.png", HORDE_CARD_WIDTH, "hordeBack");

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mx = e.getX();
                my = e.getY();
                refresh();
            }
        });
    }

    public void initGame(){
        for (int i = 0; i < model.getGame().getAdventurers().size(); ++i){
            Adventurer adventurer = model.getGame().getAdventurers().get(i);
            AdventurerDisplay adventurerDisplay =
                    new AdventurerDisplay(
                            adventurer,
                            new Rectangle(
                                    adventurerBoardOffset.x + ((i % 2) * Adventurer.BOARD_WIDTH),
                                    adventurerBoardOffset.y + ((i < 2? 0: 1) * adventurer.getImage().getHeight(null)),
                                    adventurer.getImage().getWidth(null),
                                    adventurer.getImage().getHeight(null)));
            adventurerDisplays.add(adventurerDisplay);
        }
    }

    public void refresh(){
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        drawCamp(g);
        drawAdventurers(g);
        drawLine(g);
    }

    private void drawCamp(Graphics2D g) {
        // Camp Board
        g.drawImage(boardImage, 0, 0, null);

        Point firewoodCoord = FIREWOOD_COORDS[model.getGame().getFirewood()];
        g.drawImage(firewoodImage, firewoodCoord.x, firewoodCoord.y, null);

        g.drawImage(locationBack, locationBackCoord.x, locationBackCoord.y, null);
        g.drawImage(model.getGame().getCurrentLocation().getImage(), currentLocationCoord.x, currentLocationCoord.y, null);

        g.setColor(Color.RED);
        g.setStroke(NORMAL_STROKE);
        for (CampAction campAction: CAMP_ACTION_COORDS.keySet()){
            g.drawRect(CAMP_ACTION_COORDS.get(campAction).x, CAMP_ACTION_COORDS.get(campAction).y, CAMP_ACTION_COORDS.get(campAction).width, CAMP_ACTION_COORDS.get(campAction).height);
        }

        // Draw the Horde
        g.drawImage(hordeBack, hordeCoord.x, hordeCoord.y, null);
        g.setColor(Color.WHITE);
        g.setFont(HORDE_TITLE_FONT);
        g.drawString("Horde", hordeCoord.x + 10, hordeCoord.y + 25);
        g.setFont(HORDE_SIZE_FONT);
        g.drawString("" + model.getGame().getHorde().size(), hordeCoord.x + (HORDE_CARD_WIDTH / 2) - 15, hordeCoord.y + (hordeBack.getHeight(null) / 2) + 15);

        g.setFont(COORD_FONT);
        g.setColor(Color.WHITE);
        g.drawString(mx + ", " + my, 50, 50);
    }

    private void drawAdventurers(Graphics2D g) {
        // Adventurer Boards
        for (int i = 0; i < adventurerDisplays.size(); ++i) {
            AdventurerDisplay adventurerDisplay = adventurerDisplays.get(i);
            g.drawImage(adventurerDisplay.getAdventurer().getImage(), adventurerDisplay.getBoardBounds().x, adventurerDisplay.getBoardBounds().y, null);
            if (adventurerDisplay.getAdventurer().isAtCamp()){
                g.setColor(AT_CAMP_BORDER_COLOR);
                g.setStroke(AT_CAMP_BORDER_STROKE);
                g.drawRect(adventurerDisplay.getBoardBounds().x, adventurerDisplay.getBoardBounds().y, adventurerDisplay.getAdventurer().getImage().getWidth(null), adventurerDisplay.getAdventurer().getImage().getHeight(null));
                g.setStroke(NORMAL_STROKE);
            }

            Image heroAbilityImage =
                    adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(0).isExhausted() ?
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(0).getImageExhausted() :
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(0).getImageActive();
            g.drawImage(heroAbilityImage, adventurerDisplay.getAbilityCardBounds1().xpoints[0], adventurerDisplay.getAbilityCardBounds1().ypoints[0], null);
            //g.draw(adventurerDisplay.getAbilityCardBounds1());

            heroAbilityImage =
                    adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(1).isExhausted() ?
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(1).getImageExhausted() :
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(1).getImageActive();
            g.drawImage(heroAbilityImage, adventurerDisplay.getAbilityCardBounds2().xpoints[0], adventurerDisplay.getAbilityCardBounds2().ypoints[0], null);
            //g.draw(adventurerDisplay.getAbilityCardBounds2());

            heroAbilityImage =
                    adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(2).isExhausted() ?
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(2).getImageExhausted() :
                            adventurerDisplay.getAdventurer().getAssignedHeroAbilities().get(2).getImageActive();
            g.drawImage(heroAbilityImage, adventurerDisplay.getAbilityCardBounds3().xpoints[0], adventurerDisplay.getAbilityCardBounds3().ypoints[0], null);
            //g.draw(adventurerDisplay.getAbilityCardBounds3());

            // Dice
            for (int d = 0; d < 3; ++d) {
                Dice dice = adventurerDisplay.getAdventurer().getDice().get(d);
                if (dice.isAssigned()){
                    g.drawImage(dice.getImage(), dice.getPx(), dice.getPy(), null);
                }
                else if (diceDragging == dice){
                    g.drawImage(dice.getImage(), diceDraggingX - (dice.getImage().getWidth(null) / 2), diceDraggingY - (dice.getImage().getHeight(null) / 2), null);
                }
                else
                    g.drawImage(dice.getImage(), adventurerDisplay.getDiceBounds().get(d).x, adventurerDisplay.getDiceBounds().get(d).y, null);
            }

            // Camp token
            if (adventurerDisplay.getAdventurer().getNumTimesAtCamp() == 1)
                g.drawImage(camp1, adventurerDisplay.getBoardBounds().x, adventurerDisplay.getBoardBounds().y, null);
            else if (adventurerDisplay.getAdventurer().getNumTimesAtCamp() == 2)
                g.drawImage(camp2, adventurerDisplay.getBoardBounds().x, adventurerDisplay.getBoardBounds().y, null);

            g.draw(adventurerDisplay.getCampActionBounds());
        }
    }

    private void drawLine(Graphics2D g){
        // Line
        g.setColor(Color.BLACK);
        g.fillRect(lineOffset.x, lineOffset.y, Toolkit.getDefaultToolkit().getScreenSize().width, 300);

        // Draw firewood icon
        g.drawImage(firewoodImageBig, lineOffset.x, lineOffset.y + creatureBack.getHeight(null) - firewoodImageBig.getHeight(null), null);
        int xoffset = firewoodImageBig.getWidth(null);

        // Draw Line
        int numLineToDraw = Math.min(model.getGame().getLine().size(), MAX_LINE_DISPLAYED);
        for (int i = 0; i < numLineToDraw; ++i){
            Image image = model.getGame().getLine().get(i).getImage();
            if (!model.getGame().getLine().get(i).isRevealed())
                image = creatureBack;
            g.drawImage(image, xoffset, lineOffset.y, null);
            xoffset += image.getWidth(null);
        }
        int numLineNotDrawn = model.getGame().getLine().size() - numLineToDraw;
        if (numLineNotDrawn > 0) {
            g.drawImage(creatureBack, xoffset, lineOffset.y, null);
            g.setColor(Color.WHITE);
            g.setFont(LINE_FONT);
            g.drawString("" + numLineNotDrawn, xoffset + (creatureBack.getWidth(null) / 2), lineOffset.y + (creatureBack.getHeight(null) / 2));
        }
    }

    public Adventurer getSelectedAdventurer(int mx, int my){
        for (AdventurerDisplay adventurerDisplay: adventurerDisplays){
            if (adventurerDisplay.getBoardBounds().contains(mx, my)){
                return adventurerDisplay.getAdventurer();
            }
        }
        return null;
    }

    public Dice getSelectedDice(Adventurer adventurer, int mx, int my){
        for (AdventurerDisplay adventurerDisplay: adventurerDisplays){
            if (adventurerDisplay.getAdventurer() == adventurer){
                for (int d = 0; d < adventurerDisplay.getDiceBounds().size(); ++d){
                    Rectangle rectangle = adventurerDisplay.getDiceBounds().get(d);
                    if (rectangle.contains(mx, my)) {
                        return adventurer.getDice().get(d);
                    }
                }
            }
        }
        return null;
    }

    public CampAction getSelectedCampAction(int mx, int my){
        for (CampAction campAction: CAMP_ACTION_COORDS.keySet()){
            if (CAMP_ACTION_COORDS.get(campAction).contains(mx, my))
                return campAction;
        }
        return null;
    }

    public AdventurerAbility getSelectedAdventurerAbility(Adventurer adventurer, int mx, int my){
        for (AdventurerDisplay adventurerDisplay: adventurerDisplays){
            if (adventurerDisplay.getAdventurer() == adventurer){
                Polygon bounds = adventurerDisplay.getAbilityCardBounds1();
                if (bounds.contains(mx, my)) {
                    return adventurer.getAssignedHeroAbilities().get(0);
                }

                bounds = adventurerDisplay.getAbilityCardBounds2();
                if (bounds.contains(mx, my)) {
                    return adventurer.getAssignedHeroAbilities().get(1);
                }

                bounds = adventurerDisplay.getAbilityCardBounds3();
                if (bounds.contains(mx, my)) {
                    return adventurer.getAssignedHeroAbilities().get(2);
                }
            }
        }
        return null;
    }


    public void setDiceDragging(Dice diceDragging, int mx, int my) {
        this.diceDragging = diceDragging;
        this.diceDraggingX = mx;
        this.diceDraggingY = my;
    }

    public Dice getDiceDragging(){return diceDragging;}

    public int getDiceDraggingX() {
        return diceDraggingX;
    }

    public int getDiceDraggingY() {
        return diceDraggingY;
    }

    public List<AdventurerDisplay> getAdventurerDisplays() {
        return adventurerDisplays;
    }

    public static class AdventurerDisplay{
        private Adventurer adventurer;
        private Rectangle boardBounds;
        private Polygon abilityCardBounds1;
        private Polygon abilityCardBounds2;
        private Polygon abilityCardBounds3;
        private List<Rectangle> diceBounds = new ArrayList<>(3);
        private Rectangle campActionBounds;

        public AdventurerDisplay(Adventurer adventurer, Rectangle boardBounds){
            this.adventurer = adventurer;
            this.boardBounds = boardBounds;

            int xoffset = 185;
            int yoffset = 100;
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

            xoffset = 314;
            yoffset = 100;
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


            xoffset = 438;
            yoffset = 100;
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

            // Dice Bounds
            xoffset = 390;
            yoffset = 50;
            this.diceBounds.add(new Rectangle(boardBounds.x + xoffset, boardBounds.y + yoffset, Dice.DICE_SIZE, Dice.DICE_SIZE));
            adventurer.getDice().get(0).setCoord(boardBounds.x + xoffset, boardBounds.y + yoffset);
            xoffset = 452;
            this.diceBounds.add(new Rectangle(boardBounds.x + xoffset, boardBounds.y + yoffset, Dice.DICE_SIZE, Dice.DICE_SIZE));
            adventurer.getDice().get(1).setCoord(boardBounds.x + xoffset, boardBounds.y + yoffset);
            xoffset = 514;
            this.diceBounds.add(new Rectangle(boardBounds.x + xoffset, boardBounds.y + yoffset, Dice.DICE_SIZE, Dice.DICE_SIZE));
            adventurer.getDice().get(2).setCoord(boardBounds.x + xoffset, boardBounds.y + yoffset);

            campActionBounds = new Rectangle(boardBounds.x + 55, boardBounds.y + 282, 50, 50);
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

        public List<Rectangle> getDiceBounds() {
            return diceBounds;
        }

        public Rectangle getCampActionBounds() {
            return campActionBounds;
        }
    }
}
