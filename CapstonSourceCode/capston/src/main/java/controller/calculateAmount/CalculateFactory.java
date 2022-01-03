package controller.calculateAmount;

public class CalculateFactory {
    public Double calculateFeeBorrowingBike(Integer classifyId, Long totalTime) {
        switch (classifyId) {
            case 1:
                return new CalculateAmountBicycle().calculateTotalFeeBorrowingBike(totalTime);
            case 2:
                return new CalculateAmountCoupleBike().calculateTotalFeeBorrowingBike(totalTime);
            case 3:
                return new CalculateAmountElectricBike().calculateTotalFeeBorrowingBike(totalTime);
        }
        return 0d;
    }
}
