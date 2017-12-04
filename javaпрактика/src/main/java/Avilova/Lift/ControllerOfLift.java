package Avilova.Lift;

public class ControllerOfLift {

    /**лифт*/
    private Lift lift;

    /**движение вверх*/
    private ActionDown actiondown;

    /**движение вниз*/
    private  ActionUp actionup;

    /**
     * конструктор
     * @param lift-лифт
     */
    ControllerOfLift (Lift lift){
        this.lift = lift;
        actiondown = new ActionDown(lift);
        actionup = new ActionUp(lift);
    }

    /**
     * осуществление движения лифта и вызов методов для движения вверх и вниз
     * изменение направления лифта
     * если лифт изменил направление, то у всех пассажиров поле isLater меняется на false
     */
    public void run() {

        if (isChange()) {
            lift.setRout(!lift.getRout());
            for (Passenger passenger : lift.passengerList)
                if (passenger.getIsLater())
                    passenger.setIsLater(false);
        }
            //едет вверх
        if ( lift.getRout())
            actionup.run(actionup.find());

        //едет вниз
        else
            actiondown.run(actiondown.find());

    }

    /**
     * нужно ли изменить направление лифта или нет
     * меняем, если пассажиров в лифте нет или если нет пассажиров, которым он подходит
     * @return true-нужно изменить, false-не менять
     */
    public boolean isChange(){
        if (lift.colOfPassangeInLift() != 0)
            return false;
        else
          if (lift.getRout()){
              for (Passenger passenger : lift.passengerList)
                  if ((passenger.getFloorOfDeparture() - passenger.getFloorOfDestination() < 0)
                      && (!passenger.getIsLater()))
                      return false;
          } else {
              for (Passenger passenger : lift.passengerList)
                  if ((passenger.getFloorOfDeparture() - passenger.getFloorOfDestination() > 0)
                          && (!passenger.getIsLater()))
                      return false;
          }

        return true;
    }

}
