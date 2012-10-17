package command;


public class CommandFactory {

	public Command getCommandClass(String command) {

		Command c = null;

		try {
			Class t = Class.forName(command);
			c = (Command) t.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;
	}

}
