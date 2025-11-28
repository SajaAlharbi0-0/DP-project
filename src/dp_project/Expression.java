package dp_project;
//saja
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
        // Clone to avoid modifying original lists
        List<Expression> nums = new ArrayList<>(numbers);
        List<String> ops = new ArrayList<>(operationsList);

        // Setup Chain
        OperationHandler mulDiv = new MulDivHandler();
        OperationHandler addSub = new AddSubHandler();
        mulDiv.setNext(addSub);

        mulDiv.handle(nums, ops); // start chain

        return nums.get(0).evaluate();
      }
}
