import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class PagesPanel extends JPanel
{
	private int activePage;
	private static PagesPanel instance = null;
	private static TextEditor mainWindow;
	
	private ArrayList<PagePanel> Pages = new ArrayList<>();
	
    private PagesPanel()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		addNewUnnamedPagePanel();
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
	
	public String[] getFileList()
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] array;
		
		for(int i = 0; i < Pages.size(); i++)
		{
			if(Pages.get(i).getUnnamed() == false)
				list.add(Pages.get(i).getFilePath());
		}
		array = list.toArray(new String[list.size()]);
		return array;
	}
	
	public boolean isItSave()
	{
		return Pages.get(activePage).getSaved();
	}
	
	public void savePage(String name, String path, boolean active)
	{
		Pages.get(activePage).setUnnamedFalse();
		Pages.get(activePage).setSaved(true);
		Pages.get(activePage).setTitle(name);
		Pages.get(activePage).setFilePath(path);
		Pages.get(activePage).setColor(Color.BLACK);
		
		if(active)
		{
			Pages.get(activePage).setPageText(mainWindow.getText());
			mainWindow.setTitle("Java Text Editor  ---   " + path);
		}
		
		revalidate();
        repaint();
	}
	
	public void addNewUnnamedPagePanel()
	{
		if(Pages.size() == 0)
		{
			mainWindow.setEnabletTextArea(true);
			mainWindow.changeBackgroundColor(Color.WHITE);
		}
		
		//Create PagePanel---------------------------------
		PagePanel panel = new PagePanel();
		addListenerToPanel(panel);
		//---------------------------------------------------
		
		panel.setActive(true);
		Pages.add(panel);
		this.add(panel);
	
		//Set Active page and save old page's data
		if(Pages.size() > 1)
		{
			Pages.get(activePage).setActive(false);
			Pages.get(activePage).setPageText(mainWindow.getText());
			mainWindow._textListener.setBlock(true);
			mainWindow.setText(panel.getText());
			mainWindow._textListener.setBlock(false);
			mainWindow.setSeek(0);
		}
		activePage = Pages.size() - 1;
		mainWindow.setTitle("Java Text Editor  ---   " + Pages.get(activePage).getFilePath());
		refreshPanel();
	}
	
	public void addPagePanel(PagePanel panel)
	{
		boolean isItRun = false;
		if(Pages.size() == 1 && mainWindow.getText().length() == 0 && Pages.get(activePage).getSaved() == false)
		{
			panel.resetUnnamedPageCounter();
			this.remove(Pages.get(0));
			Pages.set(0,panel);
			activePage = 0;
			isItRun = true;
		}
		
		if(Pages.size() >= 0 && isItRun == false)
		{
			mainWindow.setEnabletTextArea(true);
			mainWindow.changeBackgroundColor(Color.WHITE);
			
			if(Pages.size() > 0)
			{
				Pages.get(activePage).setActive(false);
				Pages.get(activePage).setPageText(mainWindow.getText());
			}
			Pages.add(panel);
			activePage = Pages.size() - 1;
		}
		
		addListenerToPanel(panel);
		mainWindow._textListener.setBlock(true);
		mainWindow.setText(panel.getText());
		mainWindow._textListener.setBlock(false);
		mainWindow.setTitle("Java Text Editor  ---   " + panel.getFilePath());
		this.add(panel);
		mainWindow.setSeek(0);
		refreshPanel();
	}
	
	private void addListenerToPanel(PagePanel panel)
	{
		panel.addMouseListener(new MouseAdapter()  
		{  
			public void mouseClicked(MouseEvent e)  
			{
				loadPage((PagePanel)e.getSource());
			}  
		});
	}
	
	public void loadPage(PagePanel panel)
	{
		int index = Pages.indexOf(panel);
		if( activePage == index ) return;
	
		mainWindow._textListener.setBlock(true);
		
		//Save data
		Pages.get(activePage).setActive(false);
		Pages.get(activePage).setPageText(mainWindow.getText());		
		
		//Load data
		activePage = index;
		panel.setActive(true);
		mainWindow.setText(panel.getText());
		mainWindow.setSeek(0);
		mainWindow.setTitle("Java Text Editor  ---   " + panel.getFilePath());
		
		mainWindow._textListener.setBlock(false);
	}
	
	public void deletePage(PagePanel panel)
	{
		this.remove(panel);
		Pages.remove(Pages.indexOf(panel));
		
		//No more pages
		if( Pages.size() == 0 )
		{
			panel.resetUnnamedPageCounter();
			activePage = -1;
			mainWindow._textListener.setBlock(true);
			mainWindow.setText("");
			mainWindow._textListener.setBlock(false);
			mainWindow.setTitle("Java Text Editor");
			mainWindow.setEnabletTextArea(false);
			mainWindow.changeBackgroundColor(Color.GRAY);
			refreshPanel();
			return;
		}

		//Choose Last of Page
		activePage = Pages.size() - 1;
		Pages.get(activePage).setActive(true);
		mainWindow._textListener.setBlock(true);
		mainWindow.setText(Pages.get(activePage).getText());
		mainWindow._textListener.setBlock(false);
		mainWindow.setTitle("Java Text Editor  ---   " + Pages.get(activePage).getFilePath());
		mainWindow.setSeek(0);
		refreshPanel();
	}
	
	public String getActivePageFileName()
	{
		return Pages.get(activePage).getFilePath();
	}
	
	public boolean unnamedPage()
	{
		return Pages.get(activePage).getUnnamed();
	}
	
	public void textChanged()
	{	 
		Pages.get(activePage).setSaved(false);
		Pages.get(activePage).setColor(Color.RED);
	}
	
	private void refreshPanel()
	{
		revalidate();
        repaint();
        mainWindow.revalidate();
		mainWindow.repaint();
	}

	public String getText()
	{
		return Pages.get(activePage).getText();
	}

	public void setActivePage(int index)
	{
		if(index < Pages.size())
			activePage = index;
	}

	public int pagesSize()
	{
		return Pages.size();
	}

	public int getActivePageIndex()
	{
		return activePage;
	}
}
