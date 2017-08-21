import javax.swing.JFileChooser;
import java.io.*;

public class FileOperations
{
	TextEditor mainWindow;
	
	public FileOperations(TextEditor win)
	{
		mainWindow = win;
	}
	
	public File openFileDialog()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(mainWindow);
		if(result == JFileChooser.APPROVE_OPTION)
		{
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	public File saveFileDialog()
	{
		File targetFile = null;
		
		JFileChooser c = new JFileChooser();
		int rVal = c.showSaveDialog(mainWindow);
        if(rVal == JFileChooser.APPROVE_OPTION) 
        {
			targetFile = c.getSelectedFile();
		}
		return targetFile;
		
	}
	
	public void saveFile(File file, String text)
	{
		if(file == null) return;
		
		try
		{
			FileWriter writer = new FileWriter(file);
			writer.write(text);
			writer.close();
		}
		catch(IOException e)
		{
			mainWindow.showErrorDialog("The file can't be write","IOException Error!");
		}
		catch(Exception e)
		{
			mainWindow.showErrorDialog("The file can't be write","Unexpected Error!");
		}
		
	}
	
	public String readFile(File targetFile)
	{
		if(targetFile == null) return null;

		String datas = null;
		try
		{
		
			FileInputStream file = new FileInputStream(targetFile);
			byte[] buffer = new byte[(int)targetFile.length()];
		
			file.read(buffer);
			file.close();
			datas = new String(buffer);
		}
		catch(IOException e)
		{
			mainWindow.showErrorDialog("The file can't be opened","IOException Error!");
		}
		catch(Exception e)
		{
			mainWindow.showErrorDialog("The file can't be opened","Unexpected Error!");
		}
		finally
		{
			return datas;
		}
	}
}
