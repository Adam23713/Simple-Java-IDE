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
			new WriteMail(menuBar.mainWindow.textArea.getText());
			return;
		}
		
		if( e.getActionCommand() == "Exit" )
			System.exit(0);
			
		if( e.getActionCommand() == "Set Color" )
		{
			new ColorSlide(menuBar.mainWindow);
			return;
		}
	}

}
