package dp_project;


import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gydaa
 */
public abstract class OperationHandler {
    protected OperationHandler next;

    public void setNext(OperationHandler next) {
        this.next = next;
    }

    public abstract void handle(List<Expression> numbers, List<String> operationsList);
}


class MulDivHandler extends OperationHandler {

    @Override
    public void handle(List<Expression> numbers, List<String> operationsList) {
        for (int i = 0; i < operationsList.size(); i++) {
            String op = operationsList.get(i);
            if (op.equals("×") || op.equals("÷")) {
                float left = numbers.get(i).evaluate();
                float right = numbers.get(i + 1).evaluate();
                float res = CalculatorFactory.create(op).apply(left, right);

                numbers.set(i, new Number(res));
                numbers.remove(i + 1);
                operationsList.remove(i);
                i--;
            }
        }
        if (next != null) {
            next.handle(numbers, operationsList);
        }
    }
}

class AddSubHandler extends OperationHandler {

    @Override
    public void handle(List<Expression> numbers, List<String> operationsList) {
        float result = numbers.get(0).evaluate();
        for (int i = 0; i < operationsList.size(); i++) {
            String op = operationsList.get(i);
            float right = numbers.get(i + 1).evaluate();
            result = CalculatorFactory.create(op).apply(result, right);
        }
        numbers.clear();
        numbers.add(new Number(result));
        operationsList.clear();
    }
}
