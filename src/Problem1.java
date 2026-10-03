public class Problem1 {

    public int countFreqBrute(int key, int[] A) {
        int count = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == key) {
                count++;
            }
        }

        return count;
    }


    public int countFreqSmart(int key, int[] A) {
        int first = findFirst(A, key);

        if (first == -1) {
            return 0;
        }

        int last = findLast(A, key);

        return last - first + 1;
    }


    private int findFirst(int[] A, int key) {
        int left = 0;
        int right = A.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (A[mid] == key) {
                result = mid;
                right = mid - 1;
            } else if (A[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }


    private int findLast(int[] A, int key) {
        int left = 0;
        int right = A.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (A[mid] == key) {
                result = mid;
                left = mid + 1;
            } else if (A[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }


    static void main() {
        Problem1 problem = new Problem1();

        int[] A = {
                1, 1, 1,
                2, 2, 2, 2, 2, 2,
                4, 4, 4,
                5, 5, 5, 5
        };

        System.out.println("Brute: " + problem.countFreqBrute(2, A));
        System.out.println("Smart: " + problem.countFreqSmart(5, A));

        System.out.println("Brute (key 3): " + problem.countFreqBrute(3, A));
        System.out.println("Smart (key 3): " + problem.countFreqSmart(3, A));
    }
}