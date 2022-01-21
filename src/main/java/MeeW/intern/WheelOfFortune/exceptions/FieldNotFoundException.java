package MeeW.intern.WheelOfFortune.exceptions;

public class FieldNotFoundException extends NotFoundException {
    public FieldNotFoundException() {
        super("This is not the Field you're looking for");
    }
}
