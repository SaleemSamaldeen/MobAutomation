package test;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Register Case")
@TmsLink("TC01")
@Tag("sanity")
@Severity(SeverityLevel.NORMAL)
class TestCase01 {

    @Test
    @DisplayName("REGISTER USER FOR ANDROID")
    @Description("Register user and login")
    void registerUser(){
        System.out.println("Launch android device");
    }


}
