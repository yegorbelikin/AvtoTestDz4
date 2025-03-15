package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.nio.channels.Selector;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DeliveryCardTest {

    public String generDate(int days, String pattern){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void successfulForm() {
        String planDate = generDate(7, "dd.MM.yyyy");

        Selenide.open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Горно-Алтайск");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planDate);
        $("[data-test-id='name'] input").setValue("Водкин-Петров Сергей");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification'] .notification__content").shouldHave(Condition.text("забронирована на " + planDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);

    }
}
