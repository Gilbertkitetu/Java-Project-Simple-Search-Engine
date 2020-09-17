package org.eclipse.wb.swt;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ibm.icu.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.wb.swt.SWTResourceManager;

import java.sql.*;
//import java.util.Calendar;

import java.io.File;
import java.io.FileWriter;

public class project {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/SEARCHDB";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "1234";
	   
	   
	   String FileG;
	   

	JFrame frame = new JFrame("Star Search");
	JFrame results = new JFrame("Showing Results");
	JFrame aboutJ = new JFrame("About");
	JFrame recentJ = new JFrame("Recent Search");
	
	TextField searchText = new TextField("");
	TextField resultsText = new TextField("");
	Label about = new Label("");
	Label recent = new Label("");
	
	JButton btnSearch = new JButton("Search");
	
	JButton btnRecentSearch = new JButton("Recent Search");
	
	JButton btnResults = new JButton("Results");
	
	JButton btnAbout = new JButton("About");
	
	
	
	
	//String searchTextValue = searchText.getText();
	String searchterm = searchText.getText();
	String searchurl;
	//String searchTermDb;
	//String File = googlesearch();
	

	public void sqlConnector() {
		Connection conn = null;
		   PreparedStatement pstmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      //INSERT
		      Calendar calendar = Calendar.getInstance();
		      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
		      
		      //Mysql insert statement
		      String query = " INSERT INTO SEARCHT (searchTerm, searchURL, htmlfile) VALUES (?, ? , ?)";
		      
		      //stmt = conn.createStatement();
		      //stmt.executeUpdate(query);
		      
		      /*Creating a prepared Statement*/
		      pstmt = conn.prepareStatement(query);
		      pstmt.setString(1, searchterm);
		      pstmt.setString(2, searchurl);
		      pstmt.setString(3, FileG);
		      pstmt.executeUpdate();
		      
		      //pstmt.close();
		      //conn.close();
		      
		      System.out.println("Records inserted...");
		      

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      
		      String sql;
		      sql = "SELECT * FROM SEARCHT";
		      ResultSet rs = pstmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         //int age = rs.getInt("age");
		         String searchTermDb = rs.getString("searchTerm");
		         String searchUrlDb = rs.getString("searchURL");
		         String html = rs.getString("htmlfile");

		         //Display values
		         System.out.println("ID: " + id);
		         System.out.println(", SearchTerm: " + searchTermDb);
		         System.out.println(", SearchURL: " + searchUrlDb);
		         //System.out.println(", htmlFile: " + html);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      pstmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(pstmt!=null)
		            pstmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("It is working on my machine...");
		   
	
		//return sqlConnector();
	}
	
	public String googlesearch() throws IOException {
		final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
		//String searchTerm = searchTextValue;
		int num = 50;
		
		searchurl =  GOOGLE_SEARCH_URL + "?q="+ searchterm +"&num="+num;
		System.out.println(searchurl);
		//without proper User-Agent, we will get 403 error
				Document doc = Jsoup.connect(searchurl).userAgent("Mozilla/5.0").get();
				
				//below will print HTML data, save it to a file and open in browser to compare
				//System.out.println(doc.html());
				
				//If google search results HTML change the <h3 class="r" to <h3 class="r1"
				//we need to change below accordingly
				Elements results = doc.select("h3.r > a");
				
				for (Element result : results) {
					String linkHref = result.attr("href");
					String linkText = result.text();
					System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
				}
				System.out.println(searchterm);
				//System.out.println(doc.toString());//Outputs HTML files
				//System.out.println(doc.body()); //Outputs body files
				return doc.toString();
				
				
	}
	
	
	public void OpenWithBrowser () {
		 try {
	            
			 Runtime rtime = Runtime.getRuntime();
			 
			 

	            String url = "file:///C:/Users/Gilbert%20Kitetu/Desktop/Java/guisql1/JavaProject/Files/filename.html ";
	            
	           String browser = "C:\\Program Files\\Mozilla Firefox\\firefox.exe ";
	            
	            
	            

	            Process pc = rtime.exec(browser + url);    
	            pc.waitFor();  
	            //Desktop.getDesktop().browse(URI);
	              
	            URI uri = new URI(url);
	            uri.normalize();
	            Desktop.getDesktop().browse(uri);
	            
	             
	         } catch (Exception e) {
	              System.out.println("\n\n" + e.getMessage());
	         }
	      
		 /*
		 try {
			   
			   URI uri= new URI(searchurl);
			   
			   java.awt.Desktop.getDesktop().browse(uri);
			    System.out.println("Web page opened in browser");
			 
			  } catch (Exception e) {
			   
			   e.printStackTrace();
			  }
			  */
		
		

	}
	
	
	
	public project() {
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(450,300);
		frame.getContentPane().setLayout(null);
		
		/*
		ImageIcon icon = new ImageIcon("star.jpg");
		  JLabel label = new JLabel(icon);
		  frame.add(label);
		  icon.setBounds(20,100,200,30);
		  */
		//JLabel label = new JLabel(icon);
		
		frame.getContentPane().add(searchText);
		searchText.setBounds(20,100,200,30);
		//searchTerm = searchText.getText();
		searchText.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				searchText.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				searchText.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				searchText.setText("");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				searchText.setText("Search here");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				//searchText.setText("exited");
				searchterm = searchText.getText();
			}
			
		});
		searchterm = searchText.getText();
		
		
		frame.getContentPane().add(btnSearch);
		btnSearch.setBounds(250,100,150,30);
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnSearch.setBackground(Color.gray);
				btnSearch.setText("Searching...");
				
				
				//String StrResultsText;
				try {
					 googlesearch();
					 FileG = googlesearch();
					 
					 File MyhtmlFile = new File("C:\\Users\\Gilbert Kitetu\\Desktop\\Java\\guisql1\\JavaProject\\Files\\filename.html");
					 if (MyhtmlFile.createNewFile()) {
						 System.out.println("File created: " + MyhtmlFile.getName());
					 }else {
						 System.out.println("File already exists.");
					 }
					 FileWriter myWriter = new FileWriter("C:\\Users\\Gilbert Kitetu\\Desktop\\Java\\guisql1\\JavaProject\\Files\\filename.html");
					 myWriter.write(FileG);
					 myWriter.close();
					 
					 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					 System.out.println("An error occurred.");
					e1.printStackTrace();
				}
				//String File = googlesearch();
				/*
				results.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				results.pack();
				results.setVisible(true);
				results.setSize(450,300);
				results.getContentPane().setLayout(null);
				
				results.getContentPane().add(resultsText);
				resultsText.setBounds(10,0,400,400);
				results.setTitle("Showing Results...");
				*/
				
				
				//resultsText.(googlesearch() + "\n");
				resultsText.selectAll();
				
				System.out.println(searchterm);
				System.out.println(searchurl);

				
				System.out.println(FileG);
				//openInBrowser("C:\\Users\\Gilbert Kitetu\\Desktop\\Java\\guisql1\\JavaProject\\Files\\filename.html");
				OpenWithBrowser();
				
				
				//new HtmlContent().start(FileG);
				
				
				sqlConnector();
				
				//System.out.println(searchTerm);
				  // System.out.println(searchURL);
				   //System.out.println(FileG);
			}
			
		});
		
		
		
		 frame.getContentPane().add(btnRecentSearch);
		 btnRecentSearch.setBounds(20, 170, 120, 25);
		 
		 btnRecentSearch.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 recentJ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					recentJ.pack();
					recentJ.setVisible(true);
					recentJ.setSize(650,300);
					recentJ.getContentPane().setLayout(null);
					
					recentJ.getContentPane().add(recent);
					recent.setBounds(0,10,600,40);
					recentJ.setTitle("Recent Search");
					recent.setText(searchterm);
					
					
			 }
		 });
		 
		 frame.getContentPane().add(btnResults);
		 btnResults.setBounds(150, 170, 120, 25);
		 
		 btnResults.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					btnResults.setBackground(Color.gray);
					btnResults.setText("Showing Results");
					//btnAbout.setText("Help");
					OpenWithBrowser();
					
					
				}
				
			});
		 
		 frame.getContentPane().add(btnAbout);
		 btnAbout.setBounds(300, 170, 120, 25);
		 btnAbout.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 aboutJ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					aboutJ.pack();
					aboutJ.setVisible(true);
					aboutJ.setSize(650,300);
					aboutJ.getContentPane().setLayout(null);
					
					aboutJ.getContentPane().add(about);
					about.setBounds(0,10,600,40);
					aboutJ.setTitle("About");
					about.setText("Type your search word in the text box, Click on the search button, "
							+ "Wait for the results...");
					
			 }
		 });
		 
		 
		 
		 
		
	}


	
	public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new project();
				//new googlesearch();
			}
		});
	}
/*private class Evt implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
		button.setBackground(Color.RED);
	}
}*/
}
