package exception;

public class HrExecption extends RuntimeException {
    private static final long serialVersionUID = 1103101685663577352L;

    public HrExecption() {
        super();
    }

    public HrExecption(String message) {
        super(message);
    }
}
