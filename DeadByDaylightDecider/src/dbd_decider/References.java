package dbd_decider;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class References extends DeadByDaylight_Decider {

	protected static final String APP_NAME = "Dead By Daylight Decider";
	protected static final String VERSION = "Version 0.9.4";
	protected static final String DIRECTORY = "/assets";
	protected static final String AUTHOR = "SirBassington";
	
	/**
	 * @param input String of what is hard-coded to be printed into the console
	 * 
	 * Diagnostic method for telling what time a certain line of code was reached, prints to console.
	 * Glorified System.out.println() basically
	 */
	protected static void statusAgent(String input)
	{
		System.out.println(input);
	}
	
	/**
	 * @param path location of file being called.
	 * @return returns the results whether the image call was successful or not.
	 * 
	 * Image handler for creating images and displaying the result.
	 * If the image is found successfully then the program sets the result as that and returns it to whatever called this method.
	 * If the image is not found, regardless of missing image, file typo, programming typo or the such result is set to a 'missing'
	 * 		texture and then the issue is sent to errorManagement(String, String, String).
	 */
	protected static ImageIcon createVisuals(String path)
	{
		ImageIcon result = null;
		
		try {
			result = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(DIRECTORY + path));
			//System.out.println("Image displayed: " + result);
		}
		catch (Exception failedLoad) //Catch error in finding file that matches code path, catch and log it and pass to errorManagement()
		{
			statusAgent("\nFailed to load: " + result + "\n");
			
			/*
			 * Obtain the class, method and code line of where the error started to be called from
			 */
			String fullClassName = Thread.currentThread().getStackTrace()[2].getClassName();
		    String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		    int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		    String fullInfo = className + "." + methodName + "():" + lineNumber;
			
		    /*
		     * Format Strings to be passed in errorManagement(String, String, String) error logger
		     */
			String exceptionTitle = "Missing or unexpected asset localization variable.";
			String exceptionCapability = "This is not a severe issue, the program will not auto-close.\n";
			String exceptionDetail = "Detailed explanation:\n\nAn issue occured at " + fullInfo + "\nwhile locating or loading the path of:\n" + DIRECTORY +
					path + "\n----------------------------------------------------------------\n\nPlease inform SirBassington as the soonest convenience!";
			String exceptionType = "Runtime Error MR-0x1 - Failed Resource Load";
			
			result = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/assets/missing.png")); //Set missing resource as the missing image when/if this line is reach
			errorManagement(exceptionTitle, exceptionCapability, exceptionDetail, exceptionType);
		}
		
		return result;
	}
	
	/**
	 * @param errorTitle Basic description of issue occurred
	 * @param errorLevel How severe the issue is. Normal errors won't crash the application, if they do this will not work - shouldn't happen anyway
	 * @param errorDetails Human readable issue of what the program attempted to do/find
	 * 
	 * Creates a popup window when an error occurs and displays the passed information as an error log
	 */
	protected static void errorManagement(String errorTitle, String errorLevel, String errorDetails, String errorType)
	{
		JOptionPane optionPane = new JOptionPane();
		
		optionPane.setSize(500, 300);
		//Add Copy button for error reporting
		String errorMessageHandler = errorTitle + "\n" + errorLevel + "----------------------------------------------------------------\n" + errorDetails;
		
	    JOptionPane.showMessageDialog(null, errorMessageHandler, errorType, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * This remaining are the older version history of this program which I have not really backed up. As of posting to gitHub on March 9th, 2020, this will no longer be continued on here.
	 */
	/*
	 * Version 0.9.4
	 * #############
	 * 
	 * + Added APP_NAME
	 * + Documented more code throughout the References, RoundButton and Decisions files.
	 * + Reworked some References methods to work better or be more efficient for diagnostic purposes.
	 * + Added settings panel,
	 *   *Accessed by clicking the settingsButton on the main panel.
	 *   *Brings up an about-like page with basic stuff of the application.
	 *   *main panel is inaccessible and darkened until the settings panel is closed via the closeSettingsButton button.
	 * + Changed infoButton and closeInfoButton to form factor of settingButton and closeSettingButton
	 * + Official UI from DBD added and replaced infoButton and settingButton look, now also are RoundButtons.
	 */
	
	/*
	 * Version 0.9.3
	 * #############
	 * 
	 * + Rewrote some GUI elements in how they are arranged or created.
	 * + Added APP_NAME for application use, keeps resources like updating application version titles (numbers) easy and in one location.
	 * + Added Info panel
	 *   *Accessed by clicking the App Info button on the main panel.
	 *   *Brings up an about-like page with basic stuff of the application.
	 *   *main panel is inaccessible and darkened until the info panel is closed via the 'Close Information Page' button.
	 * 
	 * - Removed legacy and newly made commented out code.
	 */
	
	/* 
	 * Version 0.9.2
	 * #############
	 * 
	 * + Added new GUI elements, this time some artwork made via Photoshop and not made by anyone else but me, simple but nice/calming style.
	 * + Cleaned up code in main file, removed unused code that no longer works or would work in current version.
	 * + Fixed erroneous GUI bugs, improves application appearance quality.
	 * 
	 * Notes:
	 * Working on adding an info button for basic 'info' stuff for the application
	 */
	
	/* 
	 * Version 0.9.1
	 * #############
	 * 
	 * + Added charPortrait background and overlay for cosmetic reasons.
	 * + Minor graphical rewrite for more optimization.
	 * 
	 * Notes:
	 * Some GUI errors present
	 */
	
	/*
	 * Version 0.9
	 * ###########
	 * 
	 * + Added AUTHOR and APP_NAME finals to this file.
	 * + Added new content for several characters.
	 * + Fixed bugs and missing content inside files.
	 */
	
	/* Version 0.8
	 * ###########
	 * 
	 * + Stability features, new characters added, some new/reworked addons.
	 * + Graphical framework rewrite finished and optimized thoroughly for the time being.
	 * 
	 * Notes:
	 * Demogorgon and Ghost Face do NOT have addons in the app yet.
	 */
	
	/*
	 * Version 0.7.1
	 * #############
	 * 
	 * Notes:
	 * Graphical rewrite in progress with optimal results.
	 * Some visual objects are in need of tweaking which is underway.
	 */
	
	/*
	 * Version 0.7
	 * ###########
	 * 
	 * Notes:
	 * Everything graphically is broken, added new layout management and everything borked.
	 * Migrating randomizer code to a new file and leaving the main file the file for 'main' graphical work.
	 */
	
	/*
	 * Version 0.6
	 * ###########
	 * 
	 * Update Idea: 'Return from the grave work'
	 * Update Nickname: 'Pretty good job so far' joke aside, really is more like 'Why is half of this broken now'
	 * 
	 * +Added VERSION final to this file.
	 * +Updated setTitle in Project.java to show VERSION after title.
	 * +Legacy code is either updated/reworked/redesigned or completely removed - some can stay as is.
	 * +Finished adding offerings, items, and addons to code and files for graphical use.
	 * +Added Perk, Offering, Item and Addons randomizers.
	 * +Perks and addons converted to Lists rather than String arrays. Fixed bug that allowed duplicate choices in run-through.
	 * 
	 * -Removed some useless/old/legacy commented-out code.
	 * -Removed most setSize() methods wherever they were before. Had too many for some reason, now only 2 instances exists - making the original and final window size 1600x900
	 * -Removed some artwork to be more in line with 'desired' theme of images.
	 * 
	 * Known Bugs:
	 * When re-deciding choices, not everything is cleared or chosen again.
	 * Re-choosing results in only characters being chosen - usually, and sometimes addons but not items.
	 * 
	 * Known Issues:
	 * -GUI layout is wonky and sometimes not always aligned
	 * 
	 * Fixed Bugs:
	 * -Fixed Survivor, Random and Killer buttons not working after reaching resolve() method
	 * -Removed some button calls towards end of program that made the endless loop bug present.
	 */
	
	/*
	 * Version 0.5
	 * ###########
	 * 
	 * Update Nickname: 'Stability Release'
	 * 
	 * +Round Buttons for survButton, randButton and killButton; No more block button issues!
	 * +Added character portraits to be displayed when choosing a side.
	 * 
	 * Known Bugs:
	 * Re-choosing through any of the 3 buttons causes erroneous looping that grows exponentially. 2nd click = 4 choices made, 3rd = 25, etc etc.
	 */
	
	/*
	 * Version 0.4
	 * ###########
	 * 
	 * +New GUI elements such as:
	 * JLabels: charPortrait, itemPower (item for survivor or power for killer)
	 * JButtons: survButton, randButton and killButton. they take you down a path for choosing everything related to one side or the other. More to come later
	 * +Added blank.pngs for each new JLabel for when the program starts. The pretty data that shows nothing for now!
	 * 
	 * -Removed JButton "actionButton". Useless button compared to 0.4+ versions
	 */
	
	/*
	 * Version 0.2
	 * ###########
	 * 
	 * +Improved killer/survivor decision basis.
	 * +Behind-The-Scenes diagnostic work and debugging, programming = introducing bugs after :D
	 */
	
	/*
	 * Version 0.1
	 * ###########
	 * 
	 * Started program idea.
	 * Only chooses survivors and killers, no character in particular. Is a random number generator that chooses which side you play based on majority of 99 rolls.
	 * 
	 * +Added this file for change log updates/history.
	 */
}