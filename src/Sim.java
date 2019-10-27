public class Sim {

    private static Integer iterations = 10000;
    private static Double influence = 0.001;
    private static Double[] gr1 = new Double[100];
    private static Double[] gr2 = new Double[100];

    private static void generateOpinions(Double diff, Double[] array){
        for(int i = 0; i<array.length;i++)
            array[i] = Math.random()-diff;
    }

    public static void main(String[] args){
        generateOpinions(.25,gr1);
        generateOpinions(-.25,gr2);

        Person[] leftP = new Person[gr1.length];
        Person[] rightP = new Person[gr2.length];

        for(int i = 0; i<gr1.length;i++) leftP[i] = new Person(gr1[i]);
        for (int i = 0; i <gr2.length;i++) {
            if (i%4 != 0)
                rightP[i] = leftP[i];
            else
                rightP[i] = new Person(gr2[i]);
        }

        Group left = new Group(influence, leftP);
        Group right = new Group(influence, rightP);

        for (int i = 0; i<iterations; i++){
            if(i == 0 || i > 9990){
                System.out.printf("\n\nIteration: %d\nLeft AvgOpinion: %f   Right AvgOpinion: %f\n", i,left.getAvgOpinion(),right.getAvgOpinion());
                System.out.printf("Left StdDev: %f     Right StdDev: %f",left.getStdDev(),right.getStdDev());
            }
            left.updateOpinions();
            right.updateOpinions();
        }
    }
}
