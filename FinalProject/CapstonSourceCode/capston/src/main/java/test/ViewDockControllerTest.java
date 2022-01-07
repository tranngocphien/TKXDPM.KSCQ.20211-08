package test;

import controller.ViewDockController;
import entity.dock.Dock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewDockControllerTest {
    private ViewDockController viewDockController;

    @BeforeEach
    void setUp() throws Exception {
        viewDockController = new ViewDockController();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDetailDock(long id, Dock dock) {
    }
}