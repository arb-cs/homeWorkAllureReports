package com.github;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.by;
import com.codeborne.selenide.Condition;

public class GitHubSearchSteps {

    private static final String REPOSITORY = "/selenide/selenide";

    @Step("Open the main page.")
    void openMainPage() {
        open("https://github.com/");
    }

    @Step("Search for required repository.")
    void searchForRequiredRepository() {
        $("[data-target*='inputButtonText']").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
    }

    @Step("Open the repository.")
    void openTheRepository() {
        $(by("href", REPOSITORY)).click();
    }

    @Step("Check that the \"Issues\" tab is visible")
    void checkThatTheIssuesTabIsVisible() {
        $("[data-content='Issues']").shouldBe(Condition.visible);
    }

}
