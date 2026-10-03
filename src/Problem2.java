public class Problem2 {

    public double getMedianBrute(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;

        int[] merged = new int[n + m];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < n && j < m) {
            if (A[i] <= B[j]) {
                merged[k] = A[i];
                i++;
            } else {
                merged[k] = B[j];
                j++;
            }
            k++;
        }

        while (i < n) {
            merged[k] = A[i];
            i++;
            k++;
        }

        while (j < m) {
            merged[k] = B[j];
            j++;
            k++;
        }

        int total = n + m;

        if (total % 2 == 1) {
            return merged[total / 2];
        } else {
            return ((double) merged[total / 2 - 1] + merged[total / 2]) / 2.0;
        }
    }


    public double getMedianSmart(int[] A, int[] B) {

        // Binary search should be done on the smaller array
        if (A.length > B.length) {
            return getMedianSmart(B, A);
        }

        int n = A.length;
        int m = B.length;

        int left = 0;
        int right = n;

        while (left <= right) {

            int partitionA = left + (right - left) / 2;
            int partitionB = (n + m + 1) / 2 - partitionA;

            int maxLeftA;
            int minRightA;
            int maxLeftB;
            int minRightB;

            if (partitionA == 0) {
                maxLeftA = Integer.MIN_VALUE;
            } else {
                maxLeftA = A[partitionA - 1];
            }

            if (partitionA == n) {
                minRightA = Integer.MAX_VALUE;
            } else {
                minRightA = A[partitionA];
            }

            if (partitionB == 0) {
                maxLeftB = Integer.MIN_VALUE;
            } else {
                maxLeftB = B[partitionB - 1];
            }

            if (partitionB == m) {
                minRightB = Integer.MAX_VALUE;
            } else {
                minRightB = B[partitionB];
            }

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {

                if ((n + m) % 2 == 0) {

                    int leftMax = Math.max(maxLeftA, maxLeftB);
                    int rightMin = Math.min(minRightA, minRightB);

                    return ((double) leftMax + rightMin) / 2.0;

                } else {

                    return Math.max(maxLeftA, maxLeftB);
                }

            } else if (maxLeftA > minRightB) {

                right = partitionA - 1;

            } else {

                left = partitionA + 1;
            }
        }

        return 0.0;
    }


    public static void main() {

        Problem2 problem = new Problem2();

        int[] A1 = {2, 4};
        int[] B1 = {3};

        System.out.println("Example 1");
        System.out.println("Brute: " + problem.getMedianBrute(A1, B1));
        System.out.println("Smart: " + problem.getMedianSmart(A1, B1));


        int[] A2 = {2, 4};
        int[] B2 = {3, 5};

        System.out.println("Example 2");
        System.out.println("Brute: " + problem.getMedianBrute(A2, B2));
        System.out.println("Smart: " + problem.getMedianSmart(A2, B2));
    }
}