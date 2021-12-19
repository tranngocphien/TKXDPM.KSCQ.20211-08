package entity.payment;

import java.util.Date;

public class CreditCard {
    private Long id;
    private String cardCode;
    private String owner;
    private Date dateExpire;

    public CreditCard() {

    }

    public CreditCard(Long id, String cardCode, String owner, Date dateExpire) {
        this.id = id;
        this.cardCode = cardCode;
        this.owner = owner;
        this.dateExpire = dateExpire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }
}
