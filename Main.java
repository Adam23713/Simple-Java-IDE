class Main
{
	public static void main(String[] arguments)
	{
		Settings setting = null;
		try
		{
			setting = new Settings(arguments);
		}
		catch(SettingsException e)
		{
			
		}
		finally
		{
			TextEditor editor = new TextEditor(setting);
		}
	}
}
