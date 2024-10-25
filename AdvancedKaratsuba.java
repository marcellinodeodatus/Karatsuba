import java.math.BigInteger;
import java.util.Scanner;

public class AdvancedKaratsuba {

    /**
     * Main method to run the Karatsuba multiplication program.
     */
    public static void main(String[] args) {
        System.out.println("=== Karatsuba Multiplication Program ===");
        
        // Take large number input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first large integer: ");
        BigInteger num1 = getValidatedBigInteger(scanner);
        
        System.out.print("Enter the second large integer: ");
        BigInteger num2 = getValidatedBigInteger(scanner);
        
        // Perform Karatsuba multiplication
        System.out.println("\nCalculating the product using Karatsuba multiplication...");
        BigInteger result = karatsuba(num1, num2);

        // Display the result
        System.out.println("\nResult:");
        System.out.println(num1 + " * " + num2 + " = " + result);
    }

    /**
     * Recursively performs Karatsuba multiplication on two large integers.
     * 
     * @param x First BigInteger operand
     * @param y Second BigInteger operand
     * @return The product of x and y using the Karatsuba algorithm
     */
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        int N = Math.max(x.bitLength(), y.bitLength());

        // Base case for recursion
        if (N <= 1000) { // Adjust the threshold as needed
            return x.multiply(y);
        }

        // Split size (half the number of bits, rounded up)
        N = (N / 2) + (N % 2);

        // Split x and y
        BigInteger[] xParts = split(x, N);
        BigInteger[] yParts = split(y, N);
        BigInteger x1 = xParts[0];
        BigInteger x0 = xParts[1];
        BigInteger y1 = yParts[0];
        BigInteger y0 = yParts[1];

        // Karatsuba recursive steps
        BigInteger z2 = karatsuba(x1, y1);
        BigInteger z0 = karatsuba(x0, y0);
        BigInteger z1 = karatsuba(x1.add(x0), y1.add(y0)).subtract(z2).subtract(z0);

        // Combine results
        return z2.shiftLeft(2 * N).add(z1.shiftLeft(N)).add(z0);
    }

    /**
     * Splits a BigInteger into two parts: high and low bits based on N-bit size.
     * 
     * @param num The BigInteger to split
     * @param N The number of bits to use for splitting
     * @return An array with two BigIntegers: [high, low]
     */
    private static BigInteger[] split(BigInteger num, int N) {
        BigInteger high = num.shiftRight(N); // High bits
        BigInteger low = num.subtract(high.shiftLeft(N)); // Low bits
        return new BigInteger[] { high, low };
    }

    /**
     * Validates user input and converts it into a BigInteger.
     * 
     * @param scanner Scanner instance for input
     * @return Validated BigInteger from user input
     */
    private static BigInteger getValidatedBigInteger(Scanner scanner) {
        BigInteger number = null;
        boolean valid = false;
        while (!valid) {
            try {
                number = new BigInteger(scanner.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
        return number;
    }
}
