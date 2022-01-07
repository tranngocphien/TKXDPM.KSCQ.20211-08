package entity.payment;

import common.exception.InvalidCardException;
import entity.db.CAPSTONDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class CreditCard implements PaymentCard{
    private String cardCode;
    private String owner;
    private int cvvCode;
    private String dateExpired;

    public CreditCard(String cardCode, String owner, int cvvCode, String dateExpired) {
        super();
        this.cardCode = cardCode;
        this.owner = owner;
        this.cvvCode = cvvCode;
        this.dateExpired = dateExpired;
    }

    public CreditCard() {

    }

    public String getCardCode() {
        return this.cardCode;
    }
    public CreditCard getCreditCardByCardCode(String cardCode) {
        try {
            Statement stm = CAPSTONDB.getConnection().createStatement();
            String sql = String.format("SELECT * FROM card where card_code = '%s'",cardCode);
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                String owner = res.getString("owner");
                String date = res.getString("date_expired");
                Integer cvv = res.getInt("cvv");
                CreditCard creditCard = new CreditCard(cardCode, owner, cvv, date);
                return creditCard;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new  InvalidCardException();
        }
        return null;
    }

    public void createCard(CreditCard creditCard) {
        String sql = "INSERT INTO `lichitec_capston`.`card` (`card_code`, `owner`, `date_expired`, `cvv`) VALUES ('"+creditCard.cardCode+"', '"+creditCard.owner+"', '"+creditCard.dateExpired+"', "+ creditCard.cvvCode +");";
        try {
            Statement stm = CAPSTONDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCard(String cardCode){
        String sql = "DELETE FROM card where card_code = '"+cardCode+"'";
        try {
            Statement stm = CAPSTONDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
