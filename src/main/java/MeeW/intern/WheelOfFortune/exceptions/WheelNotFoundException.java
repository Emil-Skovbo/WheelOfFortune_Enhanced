package MeeW.intern.WheelOfFortune.exceptions;

public class WheelNotFoundException extends NotFoundException {
    public WheelNotFoundException() {
        super("The Wheel is in another castle");
    }
}
