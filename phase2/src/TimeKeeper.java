import javafx.scene.control.DatePicker;
import java.util.Observable;

class TimeKeeper extends Observable {
    DatePicker today;

    void updateTime(DatePicker datePicker) {
        today = datePicker;
        setChanged();
        notifyObservers();
    }
}
