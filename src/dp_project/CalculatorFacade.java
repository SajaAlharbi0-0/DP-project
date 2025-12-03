/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dp_project;

/**
 *
 * @author jood2
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class CalculatorFacade {

    // [Memento] history stack – Caretaker
    private final Deque<CalculatorMemento> history = new ArrayDeque<>();

    // [Memento]

    public void saveState(String currentOperand,
                          List<Expression> expressionList,
                          List<String> operationsList,
                          String expressionText) {

        CalculatorMemento m = new CalculatorMemento(
                currentOperand,
                new ArrayList<>(expressionList),    //copy
                new ArrayList<>(operationsList),
                expressionText
        );
        history.push(m);
    }

    public Object[] undo() {
        if (history.isEmpty()) return null;

        CalculatorMemento m = history.pop();

        return new Object[] {
                m.getCurrentOperand(),
                m.getExpressionList(),
                m.getOperationsList(),
                m.getExpressionText()
        };
    }


    // The Facade interacts with the Composite and Factory subsystems.
    public float calculate(List<Expression> expressionList, List<String> operationsList) {
        if (expressionList == null || expressionList.isEmpty()) return 0f;

        // Create a composite operation (Composite Pattern)
        CompositeOperation comp = new CompositeOperation();

        // Add numbers and operations
        for (Expression exp : expressionList) {
            comp.addNumber(exp);
        }

        for (String op : operationsList) {
            comp.addOperation(op);
        }

        // Evaluate and return the result
        return comp.evaluate();
    }
}

