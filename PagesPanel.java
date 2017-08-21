import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.EtchedBorder;

public class PagesPanel extends JPanel
{
	private int unnamedCounter;
	private int activePage;
	private int counter;
	ArrayList<Boolean> savedPage = null;
	ArrayList<JPanel> pages = null;
	ArrayList<JLabel> pageName = null;
	ArrayList<JLabel> closeButtons = null;
	ArrayList<String> pageString = null;
	private static PagesPanel instance = null;
	private static TextEditor mainWindow;
	
    private PagesPanel()
	{
		//Set own layout
		this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		counter = 0;
		unnamedCounter = 0;
		savedPage = new ArrayList<Boolean>();
		pages = new ArrayList<JPanel>();
		pageName = new ArrayList<JLabel>();
		closeButtons = new ArrayList<JLabel>();
		pageString = new ArrayList<String>();
		
		addNewPage("Unnamed", "", false);
	}
		
	public void addNewPage(String title, String text, boolean loaded)
	{
		
		//If one Unnamed page opened and load a exist file------------------------------------
		if(loaded == true && counter == 1 && unnamedCounter != 0)
		{
			unnamedCounter = 0;
			mainWindow.setTitle("Java Text Editor  ---   " + pageName.get(0).getText());
			pageName.get(0).setText(title);
			pageName.get(0).setForeground(Color.BLACK);
			pageString.set(0,text);
			savedPage.set(0,true);
			mainWindow.setText(text);
			mainWindow.setSeek(0);
			return;
		}
		//------------------------------------------------------------------------------------
		
		counter++;
		if(counter == 1)
		{
			mainWindow.setEnabletTextArea(true);
			mainWindow.changeBackgroundColor(Color.WHITE);
		}
		
		
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER,15,5);
		JLabel button = new JLabel(new ImageIcon("icons/close.jpg"));
		
		if(title.equals("Unnamed")) title = title + " " + (++unnamedCounter);
		JLabel label = new JLabel(title);
		mainWindow.setTitle("Java Text Editor  ---   " + label.getText());
		
		//Close Button event-------------------------------------------------------
		button.addMouseListener(new MouseAdapter()  
		{  
			public void mouseClicked(MouseEvent e)  
			{
				PagesPanel.getInstance(null).deletePage((JLabel)e.getSource());
			}  
		});
		//-------------------------------------------------------------------------
		if(loaded == true)
			label.setForeground(Color.BLACK);
		else
			label.setForeground(Color.RED);
			
		JPanel newPanel = new JPanel();
		//Page panel event--------------------------------------------------------
		newPanel.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)  
			{
				PagesPanel.getInstance(null).loadPage((JPanel)e.getSource());
			}
		});
		//------------------------------------------------------------------------- 
		
		setPageBorder(newPanel,true);
		newPanel.setLayout(layout);
		
		newPanel.add(label);
		newPanel.add(button);
		this.add(newPanel);
		
		//Set non active pages border
		if(counter >= 2)
		{
			setPageBorder(pages.get(activePage),false);
			pageString.set(activePage,mainWindow.getText());
		}
		mainWindow.setText(text);
		if(loaded == true)
			mainWindow.setSeek(0);
		
		//Add to vector
		activePage = counter - 1;
		savedPage.add(false);
		pages.add(newPanel);
		pageName.add(label);
		closeButtons.add(button);
		pageString.add(text);
		
		
		revalidate();
        repaint();
        mainWindow.revalidate();
		mainWindow.repaint();
	}
	
	public void savePage(String fileName)
	{
		savedPage.set(activePage,true);
		pageName.get(activePage).setText(fileName);
		pageName.get(activePage).setForeground(Color.BLACK);
		pageString.set(activePage,mainWindow.getText());
		mainWindow.setTitle("Java Text Editor  ---   " + pageName.get(activePage).getText());
		revalidate();
        repaint();
	}
	
	public int getPagesNumber()
	{
		return counter;
	}
	
	public String getPageName()
	{
		return pageName.get(activePage).getText();
	}
	
	public boolean isSaved()
	{
		return savedPage.get(activePage);
	}
	
	private void setPageBorder(JPanel page, boolean active)
	{
		if(active)
		{
			page.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),BorderFactory.createLoweredBevelBorder()));
			page.setBackground(new Color(187,187,187));
		}
		else
		{
			page.setBorder(BorderFactory.createRaisedBevelBorder());
			page.setBackground(new Color(238,238,238));
		}
	}
	
	private void loadPage(JPanel panel)
	{
		for(int i = 0; i < pages.size(); i++)
		{
			if(panel == pages.get(i) )
			{
				if( activePage == i ) return;
				
				//Save data
				setPageBorder(pages.get(activePage),false);
				pageString.set(activePage,mainWindow.getText());
				
				//Load data
				activePage = i;
				setPageBorder(pages.get(activePage),true);
				mainWindow.setText(pageString.get(activePage));
				mainWindow.setSeek(0);
				mainWindow.setTitle("Java Text Editor  ---   " + pageName.get(activePage).getText());
			}
		}
	}
	
	private void deletePage(JLabel label)
	{
		for(int i = 0; i < closeButtons.size(); i++)
		{
			if(label == closeButtons.get(i) )
			{
				counter--;
				closeButtons.remove(i);
				pageName.remove(i);
				pageString.remove(i);
				PagesPanel.getInstance(null).remove(pages.get(i));
				pages.remove(i);
				
				if(counter == 0)
				{
					unnamedCounter = 0;
					mainWindow.setText("");
					mainWindow.setTitle("Java Text Editor");
					mainWindow.setEnabletTextArea(false);
					mainWindow.changeBackgroundColor(Color.GRAY);
				}
				else
				{
					activePage = counter - 1;
					mainWindow.setText(pageString.get(activePage));
					setPageBorder(pages.get(activePage),true);
					mainWindow.setTitle("Java Text Editor  ---   " + pageName.get(activePage).getText());
					mainWindow.setSeek(0);
					
				}
				revalidate();
				repaint();
			}
		}
	}
	
	public static PagesPanel getInstance(TextEditor editor)
	{	
		if(instance == null && editor != null)
		{
			mainWindow = editor;
			instance = new PagesPanel();
		}
		return instance;
	}
}
