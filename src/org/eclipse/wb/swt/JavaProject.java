package org.eclipse.wb.swt;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;

public class JavaProject implements ActionListener {

	protected static Shell shell;
	private static Text searchText;

	/**
	 * Launch the application.
	 * @param args
	 */
	/*class ActionEvents implements ActionListener {
		JFrame frame = new JFrame();
		JButton button=new JButton("Click Me");
		
		ActionEvents(){
			prepareGUI();//Calling preparedGUI method.
			buttonProperties();
		}
		public void prepareGUI(){
	        frame.setTitle("My Window");
	        frame.getContentPane().setLayout(null);
	        frame.setVisible(true);
	        frame.setBounds(200,200,400,400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }
	    public void buttonProperties(){
	        button.setBounds(130,200,100,40);
	        frame.add(button);
	        button.addActionListener(this);
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 frame.getContentPane().setBackground(Color.pink);
		}
	}*/
	
	
	public static void main(String[] args) {
		//new ActionEvents();
		try {
			JavaProject window = new JavaProject();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//new ActionEvents();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	static void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		searchText = new Text(shell, SWT.BORDER);
		searchText.setBounds(25, 101, 197, 21);
		
		Button search = new Button(shell, SWT.NONE);
		search.setBounds(272, 99, 75, 25);
		search.setText("Search");
		
		
		
		Button btnRecentSearch = new Button(shell, SWT.NONE);
		btnRecentSearch.setBounds(25, 172, 85, 25);
		btnRecentSearch.setText("Recent search");
		
		Button btnFiles = new Button(shell, SWT.NONE);
		btnFiles.setBounds(167, 172, 75, 25);
		btnFiles.setText("Files");
		
		Button btnAbout = new Button(shell, SWT.NONE);
		btnAbout.setBounds(285, 172, 75, 25);
		btnAbout.setText("About");
		
		CLabel logo = new CLabel(shell, SWT.NONE);
		logo.setLeftMargin(120);
		logo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		logo.setFont(SWTResourceManager.getFont("X-Files", 9, SWT.BOLD));
		logo.setBounds(71, 20, 276, 48);
		logo.setText("Star");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
