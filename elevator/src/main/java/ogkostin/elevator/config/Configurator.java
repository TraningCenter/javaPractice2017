package ogkostin.elevator.config;

import java.util.List;

/**
 *  Creates a list of items with parameters, which you
 * input in command line.
 *
 *  @author Oleg Kostin
 */
public interface Configurator {
   <T> List<T> configure();
}
