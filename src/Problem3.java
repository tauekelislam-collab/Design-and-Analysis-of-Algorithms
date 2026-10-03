public class Problem3 {

    public int maxSumBrute(int[] A) {
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            int currentSum = 0;

            for (int j = i; j < A.length; j++) {
                currentSum += A[j];

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }

        return maxSum;
    }


    public int maxSumSmart(int[] A) {
        return maxSubarray(A, 0, A.length - 1);
    }


    private int maxSubarray(int[] A, int left, int right) {

        // Base case: only one element
        if (left == right) {
            return A[left];
        }

        int mid = left + (right - left) / 2;

        // Best subarray completely in the left half
        int leftSum = maxSubarray(A, left, mid);

        // Best subarray completely in the right half
        int rightSum = maxSubarray(A, mid + 1, right);

        // Best subarray crossing the middle
        int crossSum = maxCrossingSum(A, left, mid, right);

        return Math.max(
                Math.max(leftSum, rightSum),
                crossSum
        );
    }


    private int maxCrossingSum(int[] A, int left, int mid, int right) {

        int sum = 0;
        int bestLeft = Integer.MIN_VALUE;

        // Move from middle to the left
        for (int i = mid; i >= left; i--) {
            sum += A[i];

            if (sum > bestLeft) {
                bestLeft = sum;
            }
        }

        sum = 0;
        int bestRight = Integer.MIN_VALUE;

        // Move from middle + 1 to the right
        for (int i = mid + 1; i <= right; i++) {
            sum += A[i];

            if (sum > bestRight) {
                bestRight = sum;
            }
        }

        return bestLeft + bestRight;
    }


    static void main() {

        Problem3 problem = new Problem3();

        int[] A = {
                -17, 5, 3, -10,
                6, 1, 4, -3,
                8, 1, -13, 4
        };

        System.out.println("Brute: " + problem.maxSumBrute(A));
        System.out.println("Smart: " + problem.maxSumSmart(A));
    }
}