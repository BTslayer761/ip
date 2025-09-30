public class BambotException extends Exception {
    public BambotException(String message) {

        super(Ui.DIVIDER + "\n" + message + "\n" + Ui.DIVIDER);
    }
}
