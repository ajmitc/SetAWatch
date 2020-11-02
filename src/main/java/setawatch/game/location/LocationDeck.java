package setawatch.game.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationDeck {
    private List<Location> normalLocations = new ArrayList<>();
    private List<Location> respiteLocations = new ArrayList<>();
    private List<Location> finalLocations = new ArrayList<>();
    private List<Location> unusedLocations = new ArrayList<>();

    public LocationDeck(){
        finalLocations.add(new ForbiddenTower());

        normalLocations.add(new CliffWatch());
        normalLocations.add(new LostOutpost());
        normalLocations.add(new ForgottenGraveyard());
        normalLocations.add(new AncientCrypt());
        normalLocations.add(new RemoteTavern());
        normalLocations.add(new DarkWoods());
        normalLocations.add(new UndergroundTomb());
        normalLocations.add(new FetidSwamp());
        normalLocations.add(new BarrenPlains());

        Collections.shuffle(normalLocations);
        Collections.shuffle(respiteLocations);
        Collections.shuffle(finalLocations);
    }

    public void formUnusedLocationDeck(){
        unusedLocations.addAll(normalLocations);
        unusedLocations.addAll(respiteLocations);
        Collections.shuffle(unusedLocations);
    }

    public List<Location> getFinalLocations() {
        return finalLocations;
    }

    public List<Location> getNormalLocations() {
        return normalLocations;
    }

    public List<Location> getRespiteLocations() {
        return respiteLocations;
    }

    public List<Location> getUnusedLocations() {
        return unusedLocations;
    }
}
