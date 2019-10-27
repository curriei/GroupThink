import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Person {
    //should have a chance to find a new group or drop out of a group at a given point in time.
    //should be based on the opinion of the user in comparison to the opinion of the group.

    private HashSet<Group> groups;
    private double opinion;

    public Person(double opinion){
        if(opinion>1) opinion = 1;
        else if (opinion <0) opinion = 0;
        this.opinion = opinion;
        groups = new HashSet<>();
    }

    public void joinGroup(Group g) {
        groups.add(g);
    }
    public void leaveGroup(Group g){
        groups.remove(g);
    }

    public void updateOpinion(double newOpinion){
        if(newOpinion>1) newOpinion = 1;
        else if (newOpinion <0) newOpinion = 0;
        Iterator<Group> groupIterator = groups.iterator();
        while(groupIterator.hasNext()) {
            Group g = groupIterator.next();
            g.updateAvgOpinion(this.opinion, newOpinion);
        }
        this.opinion = newOpinion;
    }

    public double getOpinion(){
        return this.opinion;
    }

}
