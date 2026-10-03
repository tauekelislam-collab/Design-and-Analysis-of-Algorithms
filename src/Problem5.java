import java.math.BigInteger;

public class Problem5 {

    public String multBrute(String A, String B) {

        A = removeLeadingZeros(A);
        B = removeLeadingZeros(B);

        if (A.equals("0") || B.equals("0")) {
            return "0";
        }

        int n = A.length();
        int m = B.length();

        int[] result = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {

            int digitA = A.charAt(i) - '0';

            for (int j = m - 1; j >= 0; j--) {

                int digitB = B.charAt(j) - '0';

                int position = i + j + 1;

                int multiplication = digitA * digitB + result[position];

                result[position] = multiplication % 10;

                result[position - 1] += multiplication / 10;
            }
        }

        StringBuilder answer = new StringBuilder();

        int i = 0;

        while (i < result.length && result[i] == 0) {
            i++;
        }

        while (i < result.length) {
            answer.append(result[i]);
            i++;
        }

        return answer.length() == 0 ? "0" : answer.toString();
    }


    public String multSmart(String A, String B) {

        A = removeLeadingZeros(A);
        B = removeLeadingZeros(B);

        if (A.equals("0") || B.equals("0")) {
            return "0";
        }

        return karatsuba(A, B);
    }


    private String karatsuba(String A, String B) {

        A = removeLeadingZeros(A);
        B = removeLeadingZeros(B);

        if (A.equals("0") || B.equals("0")) {
            return "0";
        }

        if (A.length() <= 4 && B.length() <= 4) {
            long a = Long.parseLong(A);
            long b = Long.parseLong(B);

            return Long.toString(a * b);
        }

        int n = Math.max(A.length(), B.length());

        if (n % 2 == 1) {
            n++;
        }

        A = padLeft(A, n);
        B = padLeft(B, n);

        int half = n / 2;

        String aHigh = A.substring(0, half);
        String aLow = A.substring(half);

        String bHigh = B.substring(0, half);
        String bLow = B.substring(half);

        String z2 = karatsuba(aHigh, bHigh);

        String z0 = karatsuba(aLow, bLow);

        String sumA = addStrings(aHigh, aLow);
        String sumB = addStrings(bHigh, bLow);

        String z1 = karatsuba(sumA, sumB);

        z1 = subtractStrings(z1, z2);
        z1 = subtractStrings(z1, z0);

        String part1 = shiftLeft(z2, 2 * half);
        String part2 = shiftLeft(z1, half);

        return removeLeadingZeros(
                addStrings(
                        addStrings(part1, part2),
                        z0
                )
        );
    }


    private String addStrings(String A, String B) {

        StringBuilder result = new StringBuilder();

        int i = A.length() - 1;
        int j = B.length() - 1;

        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {

            int digitA = 0;
            int digitB = 0;

            if (i >= 0) {
                digitA = A.charAt(i) - '0';
            }

            if (j >= 0) {
                digitB = B.charAt(j) - '0';
            }

            int sum = digitA + digitB + carry;

            result.append(sum % 10);

            carry = sum / 10;

            i--;
            j--;
        }

        return result.reverse().toString();
    }


    private String subtractStrings(String A, String B) {

        StringBuilder result = new StringBuilder();

        int i = A.length() - 1;
        int j = B.length() - 1;

        int borrow = 0;

        while (i >= 0) {

            int digitA = A.charAt(i) - '0' - borrow;

            int digitB = 0;

            if (j >= 0) {
                digitB = B.charAt(j) - '0';
            }

            if (digitA < digitB) {
                digitA += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.append(digitA - digitB);

            i--;
            j--;
        }

        return removeLeadingZeros(result.reverse().toString());
    }


    private String padLeft(String value, int length) {

        StringBuilder result = new StringBuilder();

        for (int i = value.length(); i < length; i++) {
            result.append('0');
        }

        result.append(value);

        return result.toString();
    }


    private String shiftLeft(String value, int zeros) {

        value = removeLeadingZeros(value);

        if (value.equals("0")) {
            return "0";
        }

        StringBuilder result = new StringBuilder(value);

        for (int i = 0; i < zeros; i++) {
            result.append('0');
        }

        return result.toString();
    }


    private String removeLeadingZeros(String value) {

        int i = 0;

        while (i < value.length() - 1 && value.charAt(i) == '0') {
            i++;
        }

        return value.substring(i);
    }


    static void main() {

        Problem5 solver = new Problem5();

        String A = "12345678987654321";
        String B = "98765432123456789";

        System.out.println("Brute:");
        System.out.println(solver.multBrute(A, B));

        System.out.println("Smart:");
        System.out.println(solver.multSmart(A, B));

        System.out.println("BigInteger check:");
        BigInteger a = new BigInteger(A);
        BigInteger b = new BigInteger(B);

        System.out.println(a.multiply(b));
    }
}