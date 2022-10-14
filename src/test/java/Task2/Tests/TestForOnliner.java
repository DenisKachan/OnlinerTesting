package Task2.Tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestForOnliner extends BaseTest {

    @Parameters({"mainPageSection", "catalogueSection", "catalogueSubSection", "producerCategory",
            "resolutionCategory", "priceCategory", "sizeCategory", "nameOfProducer", "valueOfResolution",
            "minimumPrice", "maximumPrice", "minimumSize", "maximumSize"})
    @Test
    public void onlinerTest(@Optional("Каталог") String mainPageSection,
                            @Optional("Электроника") String catalogueSection,
                            @Optional("Телевидение") String catalogueSubSection,
                            @Optional("Производитель") String producerCategory,
                            @Optional("Разрешение") String resolutionCategory,
                            @Optional("Минимальная цена в предложениях магазинов") String priceCategory,
                            @Optional("Диагональ") String sizeCategory,
                            @Optional("Samsung") String nameOfProducer,
                            @Optional("1920x1080 (Full HD)") String valueOfResolution,
                            @Optional("0") String minimumPrice,
                            @Optional("1500,00") String maximumPrice,
                            @Optional("40\"") String minimumSize,
                            @Optional("50\"") String maximumSize) {
        mainPage.isPageOpened()
                .navigateSection(mainPageSection);
        cataloguePage.isPageOpened()
                .navigateCatalogueSection(catalogueSection)
                .navigateCatalogueSubSection(catalogueSubSection)
                .clickTVImage();
        listOfProductsPage.isPageOpened()
                .chooseOptionOfCheckBox(producerCategory, nameOfProducer)
                .chooseOptionOfCheckBox(resolutionCategory, valueOfResolution)
                .setValuesInRangeInput(priceCategory, minimumPrice, maximumPrice)
                .setValuesInDropdown(sizeCategory, minimumSize, maximumSize)
                .totalCheck(nameOfProducer, maximumPrice, valueOfResolution, minimumSize, maximumSize);
    }

}
