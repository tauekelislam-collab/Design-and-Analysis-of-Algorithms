import java.util.Arrays;
import java.util.Comparator;

public class Problem4 {

    public double minDistBrute(double[][] p) {
        double minDist = Double.POSITIVE_INFINITY;

        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                double distance = distance(p[i], p[j]);

                if (distance < minDist) {
                    minDist = distance;
                }
            }
        }

        return minDist;
    }


    public double minDistSmart(double[][] p) {

        double[][] points = new double[p.length][2];

        for (int i = 0; i < p.length; i++) {
            points[i][0] = p[i][0];
            points[i][1] = p[i][1];
        }

        Arrays.sort(points, Comparator.comparingDouble(point -> point[0]));

        return closest(points, 0, points.length - 1);
    }


    private double closest(double[][] points, int left, int right) {

        // If there are only 2 or 3 points,
        // brute force is simple enough
        if (right - left <= 2) {
            return bruteRange(points, left, right);
        }

        int mid = left + (right - left) / 2;
        double midX = points[mid][0];

        double leftDist = closest(points, left, mid);
        double rightDist = closest(points, mid + 1, right);

        double minDist = Math.min(leftDist, rightDist);

        double[][] strip = new double[right - left + 1][2];
        int stripSize = 0;

        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i][0] - midX) < minDist) {
                strip[stripSize][0] = points[i][0];
                strip[stripSize][1] = points[i][1];
                stripSize++;
            }
        }

        Arrays.sort(
                strip,
                0,
                stripSize,
                Comparator.comparingDouble(point -> point[1])
        );

        for (int i = 0; i < stripSize; i++) {

            for (int j = i + 1;
                 j < stripSize &&
                         strip[j][1] - strip[i][1] < minDist;
                 j++) {

                double dist = distance(strip[i], strip[j]);

                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }

        return minDist;
    }


    private double bruteRange(double[][] points, int left, int right) {

        double minDist = Double.POSITIVE_INFINITY;

        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {

                double dist = distance(points[i], points[j]);

                if (dist < minDist) {
                    minDist = dist;
                }
            }
        }

        return minDist;
    }


    private double distance(double[] a, double[] b) {

        double dx = a[0] - b[0];
        double dy = a[1] - b[1];

        return Math.sqrt(dx * dx + dy * dy);
    }


    static void main() {

        Problem4 solver = new Problem4();

        double[][] points = {
                {0, 0},
                {3, 4},
                {-5, -3}
        };

        System.out.println("Brute: " + solver.minDistBrute(points));
        System.out.println("Smart: " + solver.minDistSmart(points));
    }
}