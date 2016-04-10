package seproj;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SetQuestion extends JFrame {

	private JPanel contentPane;
	private JTextField textField1;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {
					SetQuestion frame = new SetQuestion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SetQuestion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		MigLayout layout = new MigLayout("wrap 3");
		contentPane = new JPanel(layout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JLabel header = new JLabel("Enter the Question to be added");
		
		header.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPane.add(header,"cell 1 0 2 1");
		
		textField1 = new JTextField();
		
		contentPane.add(textField1, "cell 0 1 4 1"); //
		textField1.setColumns(30);
		
		JButton btnSubmit = new JButton("Submit");
		
		contentPane.add(btnSubmit, "cell 1 2");
		
		Connection connection = null;
		try{
			
		connection = DriverManager.getConnection("jdbc:sqlite:questionsdb.db");
	    final Statement statement = connection.createStatement();
	    statement.setQueryTimeout(30);
        btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ques =textField1.getText();

				 getQuestion(statement,ques);
			
				textField1.setText(" ");
				showDb(statement);
			}
		});	    
	
		}
		catch(SQLException e){
			
		}
		finally{
			
		}
		
		
	}
	

	public void getQuestion(Statement statement, String ques){
		String data = null;
		try {
			 statement.executeUpdate("insert into mcq(question) values('"+ques+"')");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showDb(Statement statement){
	 ResultSet rs;
	try {
		rs = statement.executeQuery("select * from mcq");
		  while(rs.next())
		     {
		       // read the result set
		       System.out.println("question = " + rs.getString("question"));
		       System.out.println("id = " + rs.getInt("id"));
		     }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   
   }

}	

