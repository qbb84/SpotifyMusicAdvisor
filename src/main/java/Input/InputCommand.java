package Input;

public class InputCommand {
    private String inputCommand;

    private static InputCommand INSTANCE = null;

    private InputCommand() {
    }

    public static InputCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InputCommand();
        }
        return INSTANCE;
    }

    public String getInputCommand() {
        return inputCommand;
    }

    public void setInputCommand(String inputCommand) {
        this.inputCommand = inputCommand;
    }
}