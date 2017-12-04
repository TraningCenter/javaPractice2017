package Avilova.Lift;

/**
 *
 */
public class Passenger {

    /** этаж отправления*/
    private int floorOfDeparture;

    /** этаж назначения*/
    private int floorOfDestination;

    /** вероятность захода*/
    private  int probabilityOfEnter;

    /** true-в лифте, false-не в лифте */
    private boolean isInLift;

    /** true-приехать за пассажиром в другой раз, false-забрать пассажира сразу */
    private boolean isLater;

    /** граница между для вероятности, поедет пассажир или нет с первого раза*/
    static final int BorderOfProbability = 20;

    /** максимальное число вероятности того, что пассажи поедет с первого раза*/
    static final int MaxBorder = 251;

    /**
     * конструктор объекта пассажир
     * @param possibleInitialFloor-начальный возможный этаж лифта
     * @param possibleFinaleFloor-конечный возможный этаж лифта
     */
    Passenger(int possibleInitialFloor, int possibleFinaleFloor){
        this.floorOfDeparture = possibleInitialFloor + (int)(Math.random() * ((possibleFinaleFloor - possibleInitialFloor) + 1));
        do {
            this.floorOfDestination = possibleInitialFloor + (int) (Math.random() * ((possibleFinaleFloor - possibleInitialFloor) + 1));
        } while (this.floorOfDeparture == this.floorOfDestination);

        this.probabilityOfEnter = (int)(Math.random() * MaxBorder);

        this.isLater = false;
    }

    public int getFloorOfDeparture() {
        return floorOfDeparture;
    }

    public int getFloorOfDestination() {
        return floorOfDestination;
    }

    public void setIsLater(boolean later){
        this.isLater = later;
    }

    public boolean getIsLater() {
        return isLater;
    }

    public boolean getIsInLift() {
        return isInLift;
    }

    public void setIsInLift(boolean isInLift) {
        this.isInLift  = isInLift;
    }

    /**
     * меняет probabilityOfEnter-вероятность захода пассажира, если раньше, это значение было
     * меньше BorderOfProbability и возвращает ответ, подходит ли лифт пассажиру или нет
     * @return true-лифт подходит пассажиру, false-не подходит
     */
    public boolean isSuit() {
        if (this.probabilityOfEnter < BorderOfProbability) {
            this.probabilityOfEnter = (int)(Math.random() * MaxBorder);
            return false;
        }
        return true;
    }
}
