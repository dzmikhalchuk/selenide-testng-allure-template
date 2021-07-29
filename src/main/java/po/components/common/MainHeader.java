package po.components.common;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import po.components.BaseComponent;

@Data
public class MainHeader extends BaseComponent {

    private final SelenideElement searchInput = Selenide.$("input#searchInput");
    private final SelenideElement searchButton = Selenide.$("input#searchButton");

}
