import java.math.BigInteger;

public class Karatsuba {

    // Karatsuba multiplication function
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        // Base case for recursion: if the numbers are small enough, multiply directly
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) { // For small numbers, use BigInteger's built-in multiplication
            return x.multiply(y);
        }

        // Calculate the size of the numbers (half of the max bit length)
        N = (N / 2) + (N % 2); // Round up to avoid losing bits
        
        // Split the numbers x and y
        BigInteger[] xParts = split(x, N);
        BigInteger[] yParts = split(y, N);
        
        BigInteger x1 = xParts[0]; // High part of x
        BigInteger x0 = xParts[1]; // Low part of x
        BigInteger y1 = yParts[0]; // High part of y
        BigInteger y0 = yParts[1]; // Low part of y

        // Recursively calculate three products
        BigInteger z2 = karatsuba(x1, y1); // x1 * y1
        BigInteger z0 = karatsuba(x0, y0); // x0 * y0
        BigInteger z1 = karatsuba(x1.add(x0), y1.add(y0)); // (x1 + x0) * (y1 + y0)

        // Compute z1 - z2 - z0
        z1 = z1.subtract(z2).subtract(z0);

        // Return the result: z2 * 10^2N + z1 * 10^N + z0
        return z2.shiftLeft(2 * N).add(z1.shiftLeft(N)).add(z0);
    }

    // Helper method to split a BigInteger into two halves
    private static BigInteger[] split(BigInteger num, int N) {
        // Shift to split the number into high and low parts
        BigInteger high = num.shiftRight(N); // High part
        BigInteger low = num.subtract(high.shiftLeft(N)); // Low part
        return new BigInteger[] { high, low };
    }

    public static void main(String[] args) {
        // Example usage:
        BigInteger num1 = new BigInteger("1234567890123456789012345678901234567890");
        BigInteger num2 = new BigInteger("9876543210987654321098765432109876543210");

        // Perform Karatsuba multiplication
        BigInteger result = karatsuba(num1, num2);

        // Output the result
        System.out.println("Result: " + result);
    }
}
