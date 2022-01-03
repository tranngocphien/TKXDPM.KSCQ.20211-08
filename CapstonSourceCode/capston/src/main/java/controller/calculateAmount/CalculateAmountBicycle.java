package controller.calculateAmount;

public class CalculateAmountBicycle implements CalculateAmount{
    @Override
    public double calculateTotalFeeBorrowingBike(Long totalTime) {
        long thirtyMinutes = 1000*60*30l;
        long totalMoney = 0;
        if (totalTime <= thirtyMinutes) totalMoney = 10000;
        else totalMoney = (totalTime-thirtyMinutes)/thirtyMinutes * 3000 + 10000;
        return totalMoney;
    }
}
