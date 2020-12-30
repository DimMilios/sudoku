package command;

public class ExitCommand extends Command{

	private final String commandString = "Exit";

	@Override
	public void execute() {
		System.exit(0);
	}

	@Override
	public String getCommandString() {
		return commandString;
	}
}
