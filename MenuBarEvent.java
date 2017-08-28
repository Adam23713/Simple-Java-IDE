import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

class MenuBarEvent implements ActionListener, ItemListener
{

	Menu menuBar;

	public MenuBarEvent(Menu menuBar)
	{
		this.menuBar = menuBar;
	}

	public void itemStateChanged(ItemEvent e)
	{
	}

	public void actionPerformed(ActionEvent e)
	{
		if( e.getActionCommand() == "Send To E-Mail" )
		{
			new WriteMail(menuBar.mainWindow.getText());
			return;
		}
		
		if( e.getActionCommand() == "Exit" )
			System.exit(0);
			
		if( e.getActionCommand() == "Set Color" )
		{
			new ColorSlide(menuBar.mainWindow);
			return;
		}
		
		if( e.getActionCommand() == "Setting" )
		{
			new CompileSetting();
			return;
		}
		
		if( e.getActionCommand() == "Open File" )
		{
			menuBar.mainWindow.openFile();
			return;
		}
		
		if( e.getActionCommand() == "New File" )
		{
			menuBar.mainWindow.createNewFile();
			return;
		}
		
		if( e.getActionCommand() == "Save File" )
		{
			menuBar.mainWindow.saveFile(null);
			return;
		}
		if( e.getActionCommand() == "Save All" )
		{
			menuBar.mainWindow.saveAllFile();
			return;
		}
		if( e.getActionCommand() == "Save As..." )
		{
			menuBar.mainWindow.saveAs();
			return;
		}
	}

}
