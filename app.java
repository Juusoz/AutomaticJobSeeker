import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class App {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setTitle("Duunitori Automatic Job Application Sender");
					window.frame.setVisible(true);				//Makes the window actually visible
				} catch (Exception e) {							//Error tracker
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public App() throws IOException {
		initialize();											//Launches the window
	}
	
	
	private void initialize() throws IOException {							//--The actual window--//
		frame = new JFrame();
		frame.setBounds(500, 300, 1000, 700);					//Sets the size and spawn position of the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Closes the application when pressing X
		frame.setResizable(false);								//Disables resizing
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JRadioButton rb1 = new JRadioButton("New radio button");
		rb1.setBounds(34, 42, 109, 23);
		panel.add(rb1);
		
		JRadioButton rb2 = new JRadioButton("New radio button");
		rb2.setBounds(34, 63, 109, 23);
		panel.add(rb2);
		
		JButton btnHallitse = new JButton("Hallitse");
		btnHallitse.setBounds(21, 104, 142, 23);
		panel.add(btnHallitse);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rb1);
		group.add(rb2);
		

		FindJobs();
	}

	private void FindJobs() throws IOException {
		
		int T = 0;	
		int TP = 1; //TotalPages
		int Jobs = 0; //Found jobs
		
		Document Source = Jsoup.connect("https://duunitori.fi/tyopaikat/?haku=tieto-+ja+tietoliikennetekniikka&alue=Pirkanmaa&sivu=1&order_by=search_rank").get();		//Gets the page
		int TotalPages = Integer.parseInt(Source.select("div.pagination__pages").text().replace("Sivu 1 / ", "")); //Gets the total amount of pages
		
		while(TP <= TotalPages) {
			System.out.println("Page " + TP); //Current page
			while(T < 19) { //20 results per page
		        String Job = Source.select("h3.job-box__title").eq(T).text();		//Selects the nth job
		        String JobPage = "https://duunitori.fi" + Source.select("a.job-box__hover").eq(T).attr("href"); //Selects the link of the nth job
		         
		         if(CheckSkills(JobPage) > 0) //If you have at least one of the preferred skills, print
		         {
		        	 System.out.println(Job);
		        	 System.out.println("Preferred Skills: " + CheckSkills(JobPage) + "/" + CheckAllKnownSkills(JobPage));
		        	 System.out.println(JobPage);
		        	 System.out.println();
		        	 
		        	 Jobs+=1; //Increases found jobs by 1
		         }
		        
		        T+=1;
			}
			Source = Jsoup.connect("https://duunitori.fi/tyopaikat/?haku=tieto-+ja+tietoliikennetekniikka&alue=Pirkanmaa&sivu=" + TP + "&order_by=search_rank").get();
			TP = TP + 1;
			T = 0; //Resets the T loop
		}
		System.out.println("Jobs found: " + Jobs);
		
		
	}

	private int CheckSkills(String JobPage) throws IOException {
		
		String[] Skills = {		//Your skills
				"Office",
				"Photoshop",
				"HTML",
				"CSS",
				"PHP",
				"SQL",
				"MySQL",
				"Java",
				"JavaScript",
				"jQuery",
				"C#",
				"XML",
				"XSLT",
				"LUA",
				"Android",
		};
		
		int T = 0;
		int RequiredSkills = 0;
	        Document Source = Jsoup.connect(JobPage).get();	//Gets the page
	        
	        String Description = Source.select("div.description-box").first().text();		//Gets the description box
	        //System.out.println(Description);
	        while(T < Skills.length) {
	        	
		        if(Description.contains(Skills[T])) {		//If the description contains a skill you have, increase number
		        	RequiredSkills += 1;
		        }
		        T+=1;
		        }
	        return RequiredSkills;
	}
	
	private int CheckAllKnownSkills(String JobPage) throws IOException {
		
		String[] AllKnownSkills = { //All known skills, including your missing skills
				
			"Office",
			"Photoshop",
			"HTML",
			"CSS",
			"PHP",
			"SQL",
			"MySQL",
			"Java",
			"JavaScript",
			"jQuery",
			"C#",
			"XML",
			"XSLT",
			"LUA",
			"Android",
			
			"C++",
			"Python",
			"Pandas",
			"Scipy",
			"scikit",
			"Keras",
			"Tensorflow",
			"Scala",
			"Spark",
			"Hadoop",
			"Azure",
			"IBM Watson",
			"Robot Framework",
			"Qt",
			"QML",
			"OpenShift",
			"AWS",
			"Linux",
			"QNX",
			"Active Directory",
			"Windows Server",
			"Microservice",
			"REST",
			"GraphQL",
			"JSON",
			"GIT",
			"Docker",
			"Ansible",
			"Jenkins",
			"SonarQube",
			"DevOps",
			"Scrum",
			"Kanban",
			"CI",
			"Angular",
			"Node.js",
			"Vue",
			"Spring",
			"ES6",
			"React",
			"ReactJS",
			"SASS",
			".NET",
			"X++",
			"WordPress",
			"Magento",
			"Less",
			"MariaDB",
			"MongoDB",
			"MSSQL",
			"PostgreSQL",
			"NoSQL",
			"SQL Server",
			"IIS",
			"CI",
			"CD",
			"LESS",
			"VBScript",
			"Powershell",
			"LAMP",
			"JPA",
			"JSF",
			"Vert.x",
			"Schemat",
			"xxlt",
			"xquery",
			"xpath",
			
		};
		
		int T = 0;
		int RequiredSkills = 0;
	        Document Source = Jsoup.connect(JobPage).get();	//Gets the page
	        
	        String Description = Source.select("div.description-box").first().text();		//Gets the description box
	        //System.out.println(Description);
	        while(T < AllKnownSkills.length) {
	        	
		        if(Description.contains(AllKnownSkills[T])) {		//If the description contains a skill you have, print skill
		        	RequiredSkills += 1;
		        }
		        T+=1;
		        }
	        return RequiredSkills;
	}
}


