/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package dp_project;

import java.util.List;

/**
 *
 * @author assal
 */

public class CalculatorMemento {
    private final String currentOperand;
    private final List<Expression> expressionList;
    private final List<String> operationsList;
    private final String expressionText;

    public CalculatorMemento(String currentOperand,
                             List<Expression> expressionList,
                             List<String> operationsList,
                             String expressionText) {
        this.currentOperand = currentOperand;
        this.expressionList = expressionList;
        this.operationsList = operationsList;
        this.expressionText = expressionText;
    }

    public String getCurrentOperand() {
        return currentOperand;
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public List<String> getOperationsList() {
        return operationsList;
    }

    public String getExpressionText() {
        return expressionText;
    }
}

