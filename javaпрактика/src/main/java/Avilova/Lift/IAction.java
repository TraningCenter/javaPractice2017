package Avilova.Lift;

public interface IAction {
    /**
     * движение лифта
     * @param number-этаж назначения
     * @return true-если все успешно
     */
    public boolean run(int number);

    /**
     * найти этаж назначения, на котором кто-либо выходит или заходит
     * и расстояние до него самое наименьшее
     * @return-этаж назначения
     */
    public int find();

    /**
     * посмотеть, есть ли еще пассажиры, к которым лифт не приезжал
     * @return true-есть пассажиры, false-нет пассажиров
     */
    public boolean anyoneElse();
}
