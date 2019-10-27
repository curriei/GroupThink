import java.util.*;

public class Group {
    //should have the option to reach out to new people, groups are members of a discussion space.
    //could have group leaders who have higher influence on the avgOpinion
    //could have specific subjects which are its focus. This subject would impact its opinion differently.

    private double INFLUENCE;
    private double avgOpinion;
    private Set<Person> members;
    private Map<Person, Integer> leaders;

    public Group(double influence){
        members = new HashSet<>();
        this.INFLUENCE = influence;
        this.leaders = new HashMap<>();
    }
    public Group (double influence,Person[] people){
        members = new HashSet<>();
        this.leaders = new HashMap<>();
        for (int i = 0; i<people.length; i++) {
            this.addMember(people[i]);
            people[i].joinGroup(this);
        }
        this.INFLUENCE = influence;
    }

    public void updateAvgOpinion(Double oldOpinion,Double newOpinion){
        avgOpinion = avgOpinion + (newOpinion - oldOpinion)/this.size();
    }

    //  Still need to figure out how the leaders influence others differently.  Currently we just keep track of them.
    public void makeLeader(Person p, Integer influence){
        members.add(p);
        leaders.put(p,influence);
    }

    public Boolean addMember(Person p){
        avgOpinion = (avgOpinion*members.size()+p.getOpinion())/(members.size()+1);
        p.joinGroup(this);
        return members.add(p);
    }

    public Double getAvgOpinion(){
        return this.avgOpinion;
    }

    public Integer size(){
        return members.size();
    }

    public void updateOpinions(){
        Iterator<Person> membersIterable = members.iterator();
        Double avg = avgOpinion;
        while(membersIterable.hasNext()){
            Person m = membersIterable.next();
            m.updateOpinion(m.getOpinion()*(1-INFLUENCE)+avg*INFLUENCE);
        }
    }

    public Double[] getAllOpinions(){
        Double[] ans = new Double[members.size()];
        Iterator<Person> membersIterable = members.iterator();
        for(int i = 0; i<members.size();i++) ans[i] = membersIterable.next().getOpinion();
        return ans;
    }

    public Double getStdDev(){
        Double sum = 0.;
        Iterator<Person> membersIterable = members.iterator();
        while(membersIterable.hasNext()){
            Person member = membersIterable.next();
            Double val = (member.getOpinion()-avgOpinion)*(member.getOpinion()-avgOpinion);
            val = val*val/members.size();
            sum+=val;
        }
        Double sigma = Math.sqrt(sum);
        return sigma;
    }
}
