package MeeW.intern.WheelOfFortune.exceptions;

public class WheelNotFoundException extends NotFoundException{
    public WheelNotFoundException(){
        super("The field is in another castle");
    }
}
