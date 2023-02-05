public class FizzBuzz {
    public String fizzBuzz(int i) {
        String result = "";
        if (i % 3 == 0) {
            result += "Fizz";
        }
        if (i % 5 == 0) {
            result += "Buzz";
        }
        return result.isBlank() ? Integer.toString(i) : result;
    }
}
