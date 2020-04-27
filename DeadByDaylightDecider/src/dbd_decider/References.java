package dbd_decider;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class References extends DeadByDaylight_Decider {

	protected static final String APP_NAME = "Dead By Daylight Decider";
	protected static final String VERSION = "Version 0.9.6";
	protected static final String DIRECTORY = "/assets";
	protected static final String AUTHOR = "SirBassington";
	
	protected static boolean isDebugMode = false;
	
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
	 * @param errorMessageHandler 
	 * @param errorType String 'exceptionType' from makeVisuals or createVisuals
	 * 
	 * Method for coping error messages to the system clipboard for reporting errors that arise.
	 */
	private static void copy(String errorType, String errorMessageHandler)
	{
		StringSelection toCopy = new StringSelection(errorType + "\n" + errorMessageHandler);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(toCopy, null);
	}
	
	protected static ImageIcon createVisualsSmooth(String path)
	{
		ImageIcon result = null;
		
		try {
			result = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(DIRECTORY + path));
		}
		catch (Exception toMake)
		{
			System.out.println("Failure in createVisualsSmooth()");
		}
		
		return result;
	}
	
	/**
	 * Image handler for creating images and displaying it for anything that IS a JLabel.
	 * 
	 * Same function as method below here.
	 * 
	 * @param jLabelIn incoming JLabel object
	 * @param path String of file location
	 * @param widthIn int pixels in width
	 * @param heightIn int pixels in height
	 */
	protected static void makeVisuals(JLabel jLabelIn, String path, int widthIn, int heightIn)
	{
		ImageIcon returnedResult;
		returnedResult = createVisualsSmooth(path);
		
		try {
			jLabelIn.setIcon(new ImageIcon((returnedResult.getImage()).getScaledInstance(widthIn, heightIn, java.awt.Image.SCALE_SMOOTH)));
			//System.out.println("Image displayed: " + result);
		}
		catch (Exception failedLoad) //Catch error in finding file that matches code path, then catch, log it, and pass to errorManagement()
		{
			statusAgent("\nFailed to load: " + returnedResult + "\n");
			
			/*
			 * Obtain the class, method and code line of where the error started to be called from
			 */
			String className = Thread.currentThread().getStackTrace()[2].getClassName();
		    String shortClassName = className.substring(className.lastIndexOf(".") + 1);
		    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		    int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		    String fullInfo = shortClassName + "." + methodName + "():" + lineNumber;
			
		    /*
		     * Format Strings to be passed in errorManagement(String, String, String) error logger
		     */
		    String exceptionBarrier = "----------------------------------------------------------------\n";
			String exceptionTitle = "Missing or unexpected asset localization variable.";
			String exceptionLevel = "This is not a severe issue, the program will not self terminate.\n";
			String exceptionDetail = "Detailed explanation:\n\nAn issue occured at " + fullInfo + "\nwhile locating or loading the path of:\n" + DIRECTORY +
					path + "\n" + exceptionBarrier;
			String exceptionType = "Runtime Error MR-0x2 - Failed Resource Load";
			
			returnedResult = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/assets/missing.png")); //Set missing resource as the missing image when/if this line is reach
			errorManagement(exceptionTitle, exceptionLevel, exceptionBarrier, exceptionDetail, exceptionType);
		}
	}
	
	/**
	 * Image handler for creating images and displaying it for anything that is NOT a JLabel.
	 * 
	 * Image handler for creating images and displaying the result.
	 * If the image is found successfully then the program sets the result as that and returns it to whatever called this method.
	 * If the image is not found, regardless of missing image, file type, programming typo or the such result is set to a 'missing'
	 * 		texture and then the issue is sent to errorManagement(String, String, String, String).
	 * 
	 * @param path location of file being called.
	 * @return returns the results whether the image call was successful or not.
	 * @apiNote Do not use for JLabels, they have the above method for them.
	 */
	protected static ImageIcon createVisuals(String path)
	{
		ImageIcon result = null;
		
		try {
			result = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(DIRECTORY + path));
		}
		catch (Exception failedLoad)
		{
			statusAgent("\nFailed to load: " + result + "\n");
			
			String className = Thread.currentThread().getStackTrace()[2].getClassName();
		    String shortClassName = className.substring(className.lastIndexOf(".") + 1);
		    String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		    int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
		    String fullInfo = shortClassName + "." + methodName + "():" + lineNumber;
		    
		    String exceptionBarrier = "-----------------------------------------------------------------------------------\n";
			String exceptionTitle = "Missing or unexpected asset localization variable.";
			String exceptionLevel = "This is not a severe issue, the program will not self terminate.\n";
			String exceptionDetail = "Detailed explanation:\n\nAn issue occured at " + fullInfo + "\nwhile locating or loading the path of:\n" + DIRECTORY +
					path + "\n" + exceptionBarrier;
			String exceptionType = "Runtime Error MR-0x1 - Failed Resource Load";
			
			result = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/assets/missing.png")); //Set missing resource as the missing image when/if this line is reach
			errorManagement(exceptionTitle, exceptionLevel, exceptionBarrier, exceptionDetail, exceptionType);
		}
		
		return result;
	}
	
	/**
	 * Creates a pop-up window when an error occurs and displays the passed information as an error message
	 * 
	 * @param errorTitle Basic description of issue encountered
	 * @param errorLevel How severe the issue is. Normal errors won't crash the application, if they do this will not work - shouldn't happen anyway
	 * @param errorDetails Human readable issue of what the program attempted to do/find
	 */
	protected static void errorManagement(String errorTitle, String errorLevel, String errorBarrier, String errorDetails, String errorType)
	{
		JOptionPane optionPane = new JOptionPane();
		optionPane.setSize(500, 300);
		
		String errorMessageHandlerBase = errorTitle + "\n" + errorLevel + errorBarrier + errorDetails;
		String errorMessageHandler = errorMessageHandlerBase + "\nPlease inform SirBassington as the soonest convenience!";
		
		Object[] buttonOptions = {"Copy to clipboard", "Close"};
		
		int result = JOptionPane.showOptionDialog(null, errorMessageHandler, errorType, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttonOptions, null);
		
		if (result == JOptionPane.YES_OPTION)
		{
			copy(errorType, errorMessageHandlerBase);
			JOptionPane.showMessageDialog(null, "Copied to clipboard!");
		}
	}
	
	/*
	 * Version 0.9.6
	 * #############
	 * 
	 * + No longer implements actionListener, achieves same goal with interact-able features.
	 * + Added isDebugMode boolean in this file for use elsewhere. Removes making diagnostic IDE code from trying to work when program is compiled (if it matters).
	 */
	
	/*
	 * Version 0.9.5
	 * #############
	 * 
	 * + Refined randomizeBoth() loop to have an accurate majority roll basis, typo made it roll 98 times rather than 99 times and make
	 * 		a biased killer result in the event of a 48-48 tie.
	 *   *Also updated the number for the random number generator from (10) to (20) just because
	 * + Added new methods in References for graphical handling in a formal method. JButtons could not be pushed to this structure so
	 * 		they use to now-old visual creators.
	 * + Rewrote JLabel visual calls to use above method additions.
	 * + Reworked addOn code for decideSAddons() and decideKAddons(): minimized killer specific blocks, list creation is now listed once
	 * 		and amount related values pushed out said killer blocks, eliminated total of 24 duplicate amount graphical code blocks.
	 *   *Program feels more snappy upon this code rewrite and 'visually hangs' dramatically less, almost no more .
	 * + Minor error pop-up window tweaks.
	 *   *New copy to clipboard feature for error pop-up window for easier error reporting due to application not given internet access.
	 *   *Window size is dependent on the error being called so all messages fit nicely.
	 * + Rephrased several statusAgent() calls to make debugging slightly more convenient.
	 * 
	 * Notes: Killer decisions feel slower than Survivor ones, debugging to find reason.
	 */
	
	/*
	 * Version 0.9.4
	 * #############
	 * 
	 * + Added APP_NAME
	 * + Documented more code throughout the References, RoundButton and Decisions files.
	 * + Reworked some References methods to work better or be more efficient for diagnostic purposes.
	 * + Added Settings panel,
	 *   *Accessed by clicking the settingsButton on the main panel.
	 *   *Brings up an about-like page with basic stuff of the application.
	 *   *main panel is inaccessible and darkened until the settings panel is closed via the closeSettingsButton button.
	 * + Changed infoButton and closeInfoButton to form factor of settingButton and closeSettingButton
	 * + Official UI from DBD added for and replaced infoButton and settingButton looks, now also are RoundButtons.
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