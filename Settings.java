import java.awt.*;
import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;


class SettingsException extends Exception
{
	String exceptionNamed = "Unknow";
	String xmlFileName = "Unknow";
	
	public SettingsException(String message)
	{
		super(message);
	}
	
	public SettingsException(String xmlFileName, String message)
	{
		super(message);
		this.xmlFileName = xmlFileName;
	}
	
	public SettingsException(String xmlFileName, String exceptionNamed, String message)
	{
		super(message);
		this.xmlFileName = xmlFileName;
		this.exceptionNamed = exceptionNamed;
	}
	
	public String getXmlFileName()
	{
		return xmlFileName;
	}
	
	public String getExceptionNamed()
	{
		return exceptionNamed;
	}
}

public class Settings
{
	String xmlFileName = "settings.xml";
	String[] files = new String[0];
	
	public Settings(String[] filesName) throws SettingsException
	{
		try
		{
			readXMLFile();
		}
		catch(SettingsException e)
		{
			//Nothing to do
		}
		finally
		{
			if(filesName.length > 0 || files.length == 0)
			{
				this.files = filesName;
			}
		}
	}
	
	public String[] getFilesName()
	{
		return files;
	}
	
	private void readXMLFile() throws SettingsException
	{
		ArrayList<String> filesArray = new ArrayList<String>();
		try
		{
			//Init Document and Document Builder
			File fXmlFile = new File(xmlFileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("File");
			if(nList.getLength() == 0) return;
			
			for(int temp = 0; temp < nList.getLength(); temp++)
			{
				filesArray.add(nList.item(temp).getTextContent());
			}
		}
		catch(FileNotFoundException e)
		{
			throw new SettingsException(xmlFileName,"FileNotFoundException",e.getMessage());
		}
		catch(Exception e)
		{
			throw new SettingsException(xmlFileName,"Exception",e.getMessage());
		}
		files = filesArray.toArray(new String[filesArray.size()]);
	}
	
	public void setOpenedFile(String[] array)
	{
		files = array;
	}
	
	public void writeXMLFile() throws SettingsException
	{
		try
		{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Settings");
			doc.appendChild(rootElement);

			//Opened File elements
			Element OpenedFile = doc.createElement("OpenedFile");
			rootElement.appendChild(OpenedFile);
			
			for(int i = 0; i < files.length; i++)
			{
				//File elements
				Element fileName = doc.createElement("File");
				fileName.appendChild(doc.createTextNode(files[i]));
				OpenedFile.appendChild(fileName);
			}
			
			//write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlFileName));
			transformer.transform(source, result);
		} 
		catch(ParserConfigurationException e)
		{
			throw new SettingsException(xmlFileName,"ParserConfigurationException",e.getMessage());
		}
		catch(TransformerException e)
		{
			throw new SettingsException(xmlFileName,"TransformerException",e.getMessage());
		}
		catch(Exception e)
		{
			throw new SettingsException(xmlFileName,"Exception",e.getMessage());
		}
	}
}


