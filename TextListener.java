import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.*;
import javax.swing.event.*;

public class TextListener implements DocumentListener
{
	boolean block = false;
	TextEditor mainWindow = null;
	
	public TextListener(TextEditor mainWindow)
	{
		this.mainWindow = mainWindow;
	}
	
	public void setBlock(boolean value)
	{
		block = value;
	}
	
    public void insertUpdate(DocumentEvent e)
    {
		if(block) return;
		mainWindow.getPagesPanel().textChanged();
    }
    public void removeUpdate(DocumentEvent e)
    {
		if(block) return;
		mainWindow.getPagesPanel().textChanged();
    }
    public void changedUpdate(DocumentEvent e)
    {
		//if(block) return;
		//mainWindow.getPagesPanel().textChanged();
    }
}
