package controller.calculateAmount;

public class CalculateFactory {
    public CalculateAmount getCalculateFeeBorrowingBike(Integer classifyId) {
        switch (classifyId) {
            case 1:
                return new CalculateAmountBicycle();
            case 2:
                return new CalculateAmountCoupleBike();
            case 3:
                return new CalculateAmountElectricBike();
        }
        return null;
    }
}
