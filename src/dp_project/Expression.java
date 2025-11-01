package dp_project;

import java.util.ArrayList;
import java.util.List;

public interface Expression {

    float evaluate();

    void addNumber(Expression number);

    void addOperation(String opSymbol);
}

class Number implements Expression {

    private float value;

    Number(float value) {
        this.value = value;
    }

    @Override
    public float evaluate() {
        return value;
    }

    @Override
    public void addNumber(Expression number) {}

    @Override
    public void addOperation(String opSymbol) {}
}

class CompositeOperation implements Expression {

    private List<Expression> numbers = new ArrayList<>();
    private List<String> operationsList = new ArrayList<>(); 

    @Override
    public void addNumber(Expression number) {
        numbers.add(number);
    }

    @Override
    public void addOperation(String opSymbol) {
        operationsList.add(opSymbol);
    }

    @Override
    public float evaluate() {
        if (numbers.isEmpty()) {
            return 0;
        }
        if (numbers.size() == 1) {
            return numbers.get(0).evaluate();
        }

        // copy list to avoid change it
        List<Expression> nums = new ArrayList<>(numbers);
        List<String> ops = new ArrayList<>(operationsList);

        // Start with multiplication and division
        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if (op.equals("×") || op.equals("÷")) {
                float left = nums.get(i).evaluate();
                float right = nums.get(i + 1).evaluate();
                float res = CalculatorFactory.create(op).apply(left, right); // use factory 

                nums.set(i, new Number(res));
                nums.remove(i + 1);
                ops.remove(i);
                i--; 
            }
        }

        // Then perform the addition and subtraction.
        float result = nums.get(0).evaluate();
        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            float right = nums.get(i + 1).evaluate();
            result = CalculatorFactory.create(op).apply(result, right); // use factory 
        }
        
        return result;
    }
}
