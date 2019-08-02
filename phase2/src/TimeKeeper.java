import javafx.scene.control.DatePicker;

import java.util.Observable;

public class TimeKeeper extends Observable {
    DatePicker today;

    public void updateTime(DatePicker datePicker){
        today = datePicker;
        setChanged(); // marks that the object has changed
        notifyObservers();

    }



 }
