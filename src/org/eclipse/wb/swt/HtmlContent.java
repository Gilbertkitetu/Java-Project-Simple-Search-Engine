package org.eclipse.wb.swt;
import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class HtmlContent extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	void start(String FileG)
	{
		try
		{
		String html = FileG;
		//html="<html><head><title>Simple Page</title></head>";
		//html+="<body bgcolor='#777779'><hr/><font size=50>This is Html content</font><hr/>";
		//html+="</body></html>";
		JEditorPane ed1=new JEditorPane("text/html", html);
		add(ed1);
		setVisible(true);
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Some problem has occured"+e.getMessage());
		}
	}
}
