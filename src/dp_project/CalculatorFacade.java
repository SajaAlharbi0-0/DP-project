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


public class CalculatorFacade {

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
