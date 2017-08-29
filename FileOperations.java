import javax.swing.JFileChooser;
import java.io.*;

class FileOperationsException extends Exception
{
	public FileOperationsException(String message)
	{
		super(message);
	}
}

public class FileOperations
{
	TextEditor mainWindow;
	
	public FileOperations(TextEditor win)
	{
		mainWindow = win;
	}
	
	public File openFile(String fileName)
	{
		try
		{
			File file = new File(fileName);
			if(file != null)
				return file;
			else
				return null;
		}
		catch(NullPointerException e)
		{
			return null;
		}
		catch(Exception e)
		{
			return null;
		}
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
	
	public void saveFile(File file, String text) throws FileOperationsException
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
			throw new FileOperationsException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new FileOperationsException(e.getMessage());
		}
		
	}
	
	public String readFile(File targetFile) throws FileOperationsException
	{
		if(targetFile == null) return null;

		FileInputStream file = null;
		String datas = null;
		try
		{
		
			file = new FileInputStream(targetFile);
			byte[] buffer = new byte[(int)targetFile.length()];
			file.read(buffer);
			file.close();
			datas = new String(buffer);
		}
		catch(FileNotFoundException e)
		{
			throw new FileOperationsException(e.getMessage());
		}
		catch(IOException e)
		{
			throw new FileOperationsException(e.getMessage());
		}
		catch(Exception e)
		{
			throw new FileOperationsException(e.getMessage());
		}
		finally
		{
			return datas;
		}
	}
}
