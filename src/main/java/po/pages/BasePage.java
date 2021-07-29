package po.pages;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import po.components.common.MainHeader;
import po.helpers.ElementHelper;
import po.helpers.NavigationHelper;
import po.helpers.WaitHelper;

@Data
public abstract class BasePage {
    private final MainHeader mainHeader;

    protected final ElementHelper elementHelper;
    protected final NavigationHelper navigationHelper;
    protected final WaitHelper waitHelper;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage() {
        this.mainHeader = new MainHeader();
        this.elementHelper = new ElementHelper(logger);
        this.navigationHelper = new NavigationHelper(logger);
        this.waitHelper = new WaitHelper(logger);
    }

}
