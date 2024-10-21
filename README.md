# Karatsuba Multiplication Algorithm in Java

This Java project implements the **Karatsuba algorithm**, an efficient method for multiplying large integers. The algorithm reduces the number of basic multiplication operations required, offering improved performance over the traditional grade-school multiplication method, especially for very large numbers.

## Features

- Handles arbitrarily large integers using Java's `BigInteger` class.
- Uses a divide-and-conquer approach to recursively multiply large numbers.
- Optimized for numbers larger than 2000 bits, using built-in multiplication for smaller numbers for efficiency.

## Algorithm Overview

The **Karatsuba algorithm** multiplies two large numbers by breaking them into smaller parts and recursively multiplying those parts. It reduces the number of multiplicative operations from four to three compared to the traditional multiplication method.

For two numbers \( X \) and \( Y \):
- Split each number into two halves: `X1`, `X0` (for `X`) and `Y1`, `Y0` (for `Y`).
- Perform three multiplications:
  - `P1 = X1 * Y1`
  - `P2 = X0 * Y0`
  - `P3 = (X1 + X0) * (Y1 + Y0)`
- Combine the results:
  - \( X \times Y = P1 \times 10^{2m} + (P3 - P1 - P2) \times 10^m + P2 \)

This reduces the complexity to approximately \( O(n^{1.585}) \), compared to the \( O(n^2) \) of traditional methods.

## Example
BigInteger num1 = new BigInteger("1234567890123456789012345678901234567890");
BigInteger num2 = new BigInteger("9876543210987654321098765432109876543210");
Result: 121932631137021795226185032733622923332237463801111263526900

## How it Works
**Splitting**: The large numbers are split into two parts (high and low bits).
**Recursive Multiplication**: Three recursive multiplications are performed.
**Combination**: The result is combined using shifts and additions to get the final product.

## Performance Considerations
- For small numbers, the Karatsuba algorithm falls back to the built-in multiplication for efficiency.
- The algorithm becomes significantly faster than the traditional method as the input size increases, due to the reduced number of multiplicative operations.

