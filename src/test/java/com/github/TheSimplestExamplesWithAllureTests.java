package com.github;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import static io.qameta.allure.Allure.step;

public class TheSimplestExamplesWithAllureTests {

    private static final String REPOSITORY = "/selenide/selenide";

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

    @Test
    @Owner("arb-cs")
    @DisplayName("The Tab \"Issues\" should be visible.")
    @Feature("Github search")
    @Story("Repository tabs")
    @Severity(SeverityLevel.CRITICAL)
    void tabIssuesShouldExistsPureSelenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $("[data-target*='inputButtonText']").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(by("href", REPOSITORY)).click();
        $("[data-content='Issues']").shouldBe(Condition.visible);
    }

    @Test
    @Owner("arb-cs")
    @DisplayName("The Tab \"Issues\" should be visible.")
    @Feature("Github search")
    @Story("Repository tabs")
    @Severity(SeverityLevel.CRITICAL)
    void tabIssuesShouldExistsLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open the main page.", () -> {
            open("https://github.com/");
        });

        step("Search for required repository.", () -> {
            $("[data-target*='inputButtonText']").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });

        step("Open the repository.", () -> {
            $(by("href", REPOSITORY)).click();
        });

        step("Check that the \"Issues\" tab is visible", () -> {
            $("[data-content='Issues']").shouldBe(Condition.visible);
        });

    }

    @Test
    @Owner("arb-cs")
    @DisplayName("The Tab \"Issues\" should be visible.")
    @Feature("Github search")
    @Story("Repository tabs")
    @Severity(SeverityLevel.CRITICAL)
    void tabIssuesShouldExistsStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        GitHubSearchSteps steps = new GitHubSearchSteps();

        steps.openMainPage();
        steps.searchForRequiredRepository();
        steps.openTheRepository();
        steps.checkThatTheIssuesTabIsVisible();

    }

}
