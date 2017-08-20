import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class PagesPanel extends JPanel
{
	ArrayList<JPanel> pages = null;
	ArrayList<JLabel> pageName = null;
	ArrayList<JLabel> closeButtons = null;
	ArrayList<String> pageString = null;
	private static PagesPanel instance = null;
	
    private PagesPanel()
	{
		//Set own layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER,15,5);
		
		pages = new ArrayList<JPanel>();
		pageName = new ArrayList<JLabel>();
		closeButtons = new ArrayList<JLabel>();
		pageString = new ArrayList<String>();
		
		//Create new page
		JLabel button = new JLabel(new ImageIcon("icons/close.jpg"));
		JLabel label = new JLabel("Unnamed");
		
		
		button.addMouseListener(new MouseAdapter()  
		{  
			public void mouseClicked(MouseEvent e)  
			{
				PagesPanel.getInstance().deletePage((JLabel)e.getSource());
			}  
		}); 
		
		label.setForeground(Color.RED);
		JPanel newPanel = new JPanel();
		newPanel.setBackground(new Color(187,187,187));
		newPanel.setLayout(layout);
		
		newPanel.add(label);
		newPanel.add(button);
		this.add(newPanel);
		
		//Add to vector
		pages.add(newPanel);
		pageName.add(label);
		closeButtons.add(button);
		pageString.add("");
	}
	
	private void deletePage(JLabel label)
	{
		for(int i = 0; i < closeButtons.size(); i++)
		{
			if(label == closeButtons.at(i) )
			{
			
			}
		}
	}
	
	public static PagesPanel getInstance()
	{	
		if(instance == null)
			instance = new PagesPanel();
		return instance;
	}
}
