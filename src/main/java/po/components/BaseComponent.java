package po.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import po.helpers.ElementHelper;
import po.helpers.WaitHelper;

public abstract class BaseComponent {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected WaitHelper waitHelper;
    protected ElementHelper elementHelper;

    public BaseComponent() {
        this.waitHelper = new WaitHelper(logger);
        this.elementHelper = new ElementHelper(logger);
    }
}
