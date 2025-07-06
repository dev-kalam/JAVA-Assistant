
package testassistant;

public class Calculator {

    public String handleMathCommand(String command) {
        command = command.toLowerCase();

        if (command.startsWith("add")) {
            return add(command);
        } else if (command.contains("subtract") || command.contains("minus")) {
            return subtract(command);
        } else if (command.contains("multiply") || command.contains("times")) {
            return multiply(command);
        } else if (command.contains("divide") || command.contains("by")) {
            return divide(command);
        }

        return "Please say a math command like 'add 5 3'";
    }

    // ... keep your existing add(), subtract(), etc. methods ...
    // Handles commands like: "add 5 3", "add 1.5 2.5"
    public String add(String numbersInput) {
        String[] numbers = numbersInput.replace("add", "").trim().split(" ");
        double result = 0;
        String equation = "";

        for (String numStr : numbers) {
            try {
                double num = Double.parseDouble(numStr);
                result += num;
                equation += (equation.isEmpty() ? numStr : " + " + numStr);
            } catch (Exception e) {
                /* Skip non-numbers */ }
        }

        return equation + " = " + result;
    }

    // Handles: "subtract 5 from 10", "10 minus 5"
    public String subtract(String numbersInput) {
        String cleaned = numbersInput.replaceAll("[^0-9. ]", "");
        String[] nums = cleaned.trim().split(" ");

        try {
            double num1 = Double.parseDouble(nums[0]);
            double num2 = Double.parseDouble(nums[1]);
            return num1 + " - " + num2 + " = " + (num1 - num2);
        } catch (Exception e) {
            return "Please say 'subtract 5 from 10'";
        }
    }

    // Handles: "multiply 5 by 3", "5 times 3"
    public String multiply(String numbersInput) {
        String[] numbers = numbersInput.replace("multiply", "")
                .replace("by", "")
                .replace("times", "")
                .trim().split(" ");
        double result = 1;
        String equation = "";

        for (String numStr : numbers) {
            try {
                double num = Double.parseDouble(numStr);
                result *= num;
                equation += (equation.isEmpty() ? numStr : " × " + numStr);
            } catch (Exception e) {
                /* Skip non-numbers */ }
        }

        return equation + " = " + result;
    }

    // Handles: "divide 10 by 2", "10 divided by 2"
    public String divide(String numbersInput) {
        String cleaned = numbersInput.replaceAll("[^0-9. ]", "");
        String[] nums = cleaned.trim().split(" ");

        try {
            double num1 = Double.parseDouble(nums[0]);
            double num2 = Double.parseDouble(nums[1]);
            if (num2 == 0) {
                return "Error: Can't divide by zero!";
            }
            return num1 + " ÷ " + num2 + " = " + (num1 / num2);
        } catch (Exception e) {
            return "Please say 'divide 10 by 2'";
        }
    }
}
