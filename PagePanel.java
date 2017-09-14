import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.EtchedBorder;

class CloseLabel extends JLabel
{
	PagePanel Container;
	
	public CloseLabel(PagePanel con)
	{
		setIcon(new ImageIcon("icons/close.jpg"));
		Container = con;
	}
	
	public PagePanel getContainer()
	{
		return Container;
	}
	
}

public class PagePanel extends JPanel
{
	private static int unNamedCounter = 0;

	private boolean unNamed;
	private boolean saved;
	private String filePath;
	private String pageText;
	
	private JLabel pageName;
	private CloseLabel closeButton;
	
	public PagePanel(String text, String fileName, String filePath)
	{
		this.unNamed = false;
		this.saved = true;
		this.pageText = text;
		this.filePath = filePath;
		
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER,15,5);
		pageName = new JLabel(fileName);
		pageName.setForeground(Color.BLACK);
		createCloseLabel();
		this.setActive(true);
		
		this.setLayout(layout);
		this.add(pageName);
		this.add(closeButton);
	}
	
	public PagePanel()
	{
		unNamedCounter++;
		unNamed = true;
		saved = false;
		filePath = "";
		pageText = "";
		
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER,15,5);
		pageName = new JLabel("Unnamed " + unNamedCounter);
		filePath = "Unnamed " + unNamedCounter;
		pageName.setForeground(Color.RED);
		createCloseLabel();

		this.setLayout(layout);
		this.add(pageName);
		this.add(closeButton);
	}
	
	private void createCloseLabel()
	{
		
		//Create CloseLabel
		closeButton = new CloseLabel(this);
		closeButton.addMouseListener(new MouseAdapter()  
		{  
			public void mouseClicked(MouseEvent e)  
			{
				PagesPanel.getInstance(null).deletePage( ((PagePanel)((CloseLabel)e.getSource()).getContainer()) );
			}  
		});
		//---------------------------------------------------
	}
	
	public boolean getSaved()
	{
		return saved;
	}
	
	public void setSaved(boolean value)
	{
		saved = value;
	}
	
	public void setUnnamedFalse()
	{
		unNamed = false;
	}
	
	public boolean getUnnamed()
	{
		return unNamed;
	}
	
	public void setPageText(String text)
	{
		pageText = text;
	}
	
	public void setTitle(String title)
	{
		pageName.setText(title);
	}
	
	public String getTitle()
	{
		return pageName.getText();
	}
	
	public String getText()
	{
		return pageText;
	}
	
	public void setFilePath(String path)
	{
		filePath = path;
	}
	
	public String getFilePath()
	{
		return filePath;
	}
	
	public void resetUnnamedPageCounter()
	{
		unNamedCounter = 0;
	}
	
	public void setColor(Color color)
	{
		pageName.setForeground(color);
	}
	
	public void setActive(boolean value)
	{
		if(value)
		{
			setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),BorderFactory.createLoweredBevelBorder()));
			setBackground(new Color(187,187,187));
		}
		else
		{
			setBorder(BorderFactory.createRaisedBevelBorder());
			setBackground(new Color(238,238,238));
		}
		revalidate();
        repaint();
	}
}
