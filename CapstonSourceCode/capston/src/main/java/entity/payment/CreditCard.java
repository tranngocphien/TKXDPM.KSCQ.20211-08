package entity.payment;

import common.exception.InvalidCardException;
import entity.db.CAPSTONDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class CreditCard {
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
    public CreditCard getCreditCardByCardCode(String cardCode) {
        try {
            Statement stm = CAPSTONDB.getConnection().createStatement();
            String sql = "SELECT * FROM card where card_code = "+cardCode;
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                String owner = res.getString("owner");
                String date = res.getDate("date_expired").toString();
                int cvv = 123455;
                CreditCard creditCard = new CreditCard(cardCode, owner, cvv, date);
                return creditCard;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new  InvalidCardException();
        }
        return null;
    }
}
