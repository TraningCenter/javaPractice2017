package drawerTests;

import ogkostin.elevator.model.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests logging
 *
 * @author Oleg Kostin
 */
public class LoggerTest extends Assert {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void printLog() {
        Logger logger = new Logger();
        for (int i = 0; i < 25; i++) {
            logger.append(((Integer) i).toString());
            logger.print();
        }

    }
}
