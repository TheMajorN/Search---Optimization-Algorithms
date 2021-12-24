ublic class MonteCarlo {

    int green = 0;  // Symbolize points in circle
    int red = 0;    // Symbolize points out of circle

    public static void main(String[] args) {
        MonteCarlo mc = new MonteCarlo();

        mc.generate();
        mc.areaCalc();
    }

    public double random() {    // Box area from (-1, 1)
        int min = -1;
        int max = 1;

        return (Math.random() * (max - min)) - 1;
    }

    public void generate() {    // Iterate 10,000 times
        for (int i = 0 ; i < 10000 ; i++) {
            double x = random();    // generate random x
            double y = random();    // and y values

            if (1 >= ((x * x) + (y * y))) { // If they're greater than 1,
                green++;                    // increase green, else
            } else {                        // increase red
                red++;
            }
            if (i % 100 == 0) { // Print out points in circle every 100 passes
                System.out.println("Number of points within the circle: " + green);
            }
        }
    }

    public void areaCalc() {
        double boxArea = 4; // 1 - -1 & 1 - -1 make the area 4
        double total = red + green; // Total number of points marked
        double area = (green / total) * boxArea; // Calculate area

        System.out.println("Final area: " + area);  // Print final area
    }

}
