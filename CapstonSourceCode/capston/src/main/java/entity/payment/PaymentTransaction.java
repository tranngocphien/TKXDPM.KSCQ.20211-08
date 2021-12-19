package entity.payment;

import java.sql.Timestamp;

public class PaymentTransaction {
    private Long id;
    private Long cardId;
    private Long invoiceId;
    private String content;
    private String method;
    private Timestamp totalTime;
    private Timestamp createdAt;

    public PaymentTransaction() {

    }

    public PaymentTransaction(Long id, Long cardId, Long invoiceId, String content, String method, Timestamp totalTime, Timestamp createdAt) {
        this.id = id;
        this.cardId = cardId;
        this.invoiceId = invoiceId;
        this.content = content;
        this.method = method;
        this.totalTime = totalTime;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Timestamp getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Timestamp totalTime) {
        this.totalTime = totalTime;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
