package onliner.tests;

import onliner.pages.CataloguePage;
import onliner.pages.ListOfProductsPage;
import onliner.pages.MainPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestForOnliner extends BaseTest {

    @Parameters({"nameOfProducer", "valueOfResolution",
            "minimumPrice", "maximumPrice", "minimumSize", "maximumSize"})
    @Test
    public void onlinerTest(String nameOfProducer, String valueOfResolution,
                            String minimumPrice, String maximumPrice,
                            String minimumSize, String maximumSize) {
        SoftAssert softAssert = new SoftAssert();
        MainPage mainPage = new MainPage(driver);
        mainPage.isPageOpened()
                .navigateSection("Каталог");
        CataloguePage cataloguePage = new CataloguePage(driver);
        cataloguePage.isPageOpened()
                .navigateCatalogueSection("Электроника")
                .navigateCatalogueSubSection("Телевидение")
                .navigateSubSectionEntity("Телевизоры");
        ListOfProductsPage listOfProductsPage = new ListOfProductsPage(driver);
        listOfProductsPage.isPageOpened()
                .chooseOptionOfCheckBox("Производитель", nameOfProducer)
                .chooseOptionOfCheckBox("Разрешение", valueOfResolution)
                .setValuesInRangeInput("Минимальная цена в предложениях магазинов", minimumPrice, maximumPrice)
                .setValuesInDropdown("Диагональ", minimumSize, maximumSize)
                .checkingTheModelOfTV(nameOfProducer)
                .checkingTheResolutionOfTV(valueOfResolution)
                .checkingThePriceOfTV(minimumPrice, maximumPrice)
                .checkingSizeOfTV(minimumSize, maximumSize);
        softAssert.assertAll();
    }
}
