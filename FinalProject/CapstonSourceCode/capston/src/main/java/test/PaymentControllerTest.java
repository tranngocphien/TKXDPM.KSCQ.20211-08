package test;

import controller.PaymentController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentControllerTest {
    private PaymentController paymentController;

    @BeforeEach
    void setUp() throws Exception{
        paymentController = new PaymentController();

    }

    @Test
    void payDeposit() {
    }

    @Test
    void refundDeposit() {
    }
}