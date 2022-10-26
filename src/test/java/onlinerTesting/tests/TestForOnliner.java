package onlinerTesting.tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestForOnliner extends BaseTest {

    @Parameters({"nameOfProducer", "valueOfResolution",
            "minimumPrice", "maximumPrice", "minimumSize", "maximumSize"})
    @Test
    public void onlinerTest(@Optional("Samsung") String nameOfProducer,
                            @Optional("1920x1080 (Full HD)") String valueOfResolution,
                            @Optional("0") String minimumPrice,
                            @Optional("1500,00") String maximumPrice,
                            @Optional("40\"") String minimumSize,
                            @Optional("50\"") String maximumSize) {
        mainPage.isPageOpened()
                .navigateSection("Каталог");
        cataloguePage.isPageOpened()
                .navigateCatalogueSection("Электроника")
                .navigateCatalogueSubSection("Телевидение")
                .clickTVImage();
        listOfProductsPage.isPageOpened()
                .chooseOptionOfCheckBox("Производитель", nameOfProducer)
                .chooseOptionOfCheckBox("Разрешение", valueOfResolution)
                .setValuesInRangeInput("Минимальная цена в предложениях магазинов", minimumPrice, maximumPrice)
                .setValuesInDropdown("Диагональ", minimumSize, maximumSize)
                .checkingTheModelOfTV(nameOfProducer)
                .checkingTheResolutionOfTV(valueOfResolution)
                .checkingThePriceOfTV(minimumPrice, maximumPrice)
                .checkingSizeOfTV(minimumSize, maximumSize)
                .assertAllData();
    }
}
