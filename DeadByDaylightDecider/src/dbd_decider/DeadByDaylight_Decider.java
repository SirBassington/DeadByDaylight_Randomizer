package dbd_decider;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

@SuppressWarnings("serial")
public class DeadByDaylight_Decider extends JFrame implements ActionListener {

	/**
	 * Dead By Daylight Randomizer
	 * @author SirBassington
	 * 
	 * 
	 * Decides for the user which side they should play in
	 * DBD by pulling 99 draws for killer or survivor.
	 * 
	 * Disclaimer: Artwork, names, content and other DBD related themes are owned by their respective owners (context to support any third parties if applicable).
	 * I claim none of these as mine, only the coding and file structure for creating a randomizing utility for this wonderful game I share passion for.
	 * 
	 * 
	 * Hello fellow source code sifter! Below is all the code that makes this project work, based in Java 1.8 with no extra libraries, meaning base Java jdk!
	 * Everything will be documented for the sake of helping observers understand what's happening when and where and for good programming procedures.
	 */
	static JLabel background, charPortrait, charPortraitBackground, charPortraitOverlay, charShadow, itemPower, addOn1, addOn2, offering,
		perk1, perk2, perk3, perk4, infoBoxArea, rightPaneSummary, summary, infoLabel, settingsPanel = new JLabel();
	
	static String baseChoice, charType, itemChoice, finalChar, finalAddOn1, finalAddOn2, finalOffering, finalPerk1, finalPerk2, finalPerk3,
		finalPerk4, finalToolbox, finalMedkit, finalFlashlight, finalMap, finalKey = null;
	
	static int addOnAmount, perkAmount = 0;
	static boolean isKiller, isSurvivor, isUndecided = false;
	
	static JButton survButton, randButton, killButton, infoButton, infoCloseButton, settingsButton, settingsCloseButton;
	static JCheckBox charCheck, itemPowerAddonCheck, perkCheck, offeringCheck = new JCheckBox();
	static BufferedImage image;
	
	static Font sulB = new Font("Segoe UI Light", Font.BOLD, 24);
	static Font sulBS = new Font("Segoe UI Light", Font.BOLD, 20);
	static Font emeR = new Font("Segoe UI Light", Font.BOLD, 14);
	static Font verD = new Font("Verdana", Font.BOLD, 20);
	static Font verDRegular = new Font("Verdana", Font.PLAIN, 20);
	static Font verDLarge = new Font("Verdana", Font.PLAIN, 40);
	static Color whiteF = Color.decode("#ffffff");
	static Color emergencyR = Color.decode("#ff1a1a");
	static Color darkTranparency = new Color(12, 12, 12, 200);
	
	static int width = 1600, height = 900;
	
	public static void addComponentsToPane(Container pane)
	{
		/*
		 * The following JPanel stuff are preparations for all the graphical components in the program.
		 * They help determine sizes for each JPanel regarding their parent container.
		 */
        //Horizontal JPanels - aka Trio
		JPanel dbdPanel = new JPanel()
		{
			@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(width, height);
        	}
		};
        JPanel leftStrip = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(width / 5, height);
        	}
        };
        JPanel middleStrip = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension((width / 5) * 3, height);
        	}
        };
        JPanel rightStrip = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(width / 5, height);
        	}
        };
        //Vertical middleStrip JPanels - aka centeredPanes
        JPanel buttonLayer = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(middleStrip.getWidth(), middleStrip.getHeight() / 5);
        	}
        };
        JPanel descriptionLayer = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(middleStrip.getWidth(), (middleStrip.getHeight() / 5) * 3);
        	}
        };
        JPanel legacySummaryLayer = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(middleStrip.getWidth(), middleStrip.getHeight() / 5);
        	}
        };
        JPanel topDescriptionLayer = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(descriptionLayer.getWidth(), (descriptionLayer.getHeight() / 5) * 3);
        	}
        };
        JPanel bottomDescriptionLayer = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(descriptionLayer.getWidth(), (descriptionLayer.getHeight() / 5) * 2);
        	}
        };
        JPanel leftMost = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(topDescriptionLayer.getWidth() / 4, topDescriptionLayer.getHeight());
        	}
        };
        JPanel leftLeast = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(topDescriptionLayer.getWidth() / 4, topDescriptionLayer.getHeight());
        	}
        };
        JPanel rightLeast = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(topDescriptionLayer.getWidth() / 4, topDescriptionLayer.getHeight());
        	}
        };
        JPanel rightLeastTop = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(rightLeast.getWidth(), rightLeast.getHeight() / 2);
        	}
        };
        JPanel rightLeastBottom = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(rightLeast.getWidth(), rightLeast.getHeight() / 2);
        	}
        };
        JPanel rightMost = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(topDescriptionLayer.getWidth() / 4, topDescriptionLayer.getHeight());
        	}
        };
        JPanel perkLayer1 = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(bottomDescriptionLayer.getWidth() / 4, bottomDescriptionLayer.getHeight());
        	}
        };
        JPanel perkLayer2 = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(bottomDescriptionLayer.getWidth() / 4, bottomDescriptionLayer.getHeight());
        	}
        };
        JPanel perkLayer3 = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(bottomDescriptionLayer.getWidth() / 4, bottomDescriptionLayer.getHeight());
        	}
        };
        JPanel perkLayer4 = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension(bottomDescriptionLayer.getWidth() / 4, bottomDescriptionLayer.getHeight());
        	}
        };
        JPanel infoPanel = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension((width / 3) * 2, (height / 6) * 1);
        	}
        };
        JPanel settingsPanel = new JPanel()
        {
        	@Override
        	public Dimension getPreferredSize()
        	{
        		return new Dimension((width / 3) * 2, (height / 6) * 1);
        	}
        };

        //Let the following 2 Panels have their background color which have transparent properties and helps make their content visible against the dbdPanel
        settingsPanel.setOpaque(true);
        infoPanel.setOpaque(true);
        
        dbdPanel.setOpaque(false);
        
        /**
         * This is used to make JPanels overlap eachother in layers with the topmost being added first the then each one below it being the next layer down.
         * This may not be the best approach to having multiple 'windows' in a program but I find it best to go ahead declare them like this for learning purposes.
         */
        JPanel mergePanel = new JPanel();
        mergePanel.setOpaque(false);
        mergePanel.setLayout(new OverlayLayout(mergePanel));
        mergePanel.add(settingsPanel, BorderLayout.CENTER);
        mergePanel.add(infoPanel, BorderLayout.CENTER); //Add transparent first
        mergePanel.add(dbdPanel, BorderLayout.CENTER);
        pane.setLayout(new BorderLayout());
        pane.add(mergePanel);
        
        
        //settingsPanel stuff
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints settP = new GridBagConstraints();
        settingsPanel.setBackground(darkTranparency);
        
        settP.gridx = 0;
        settP.gridy = 0;
        settP.weighty = 1;
        JLabel settingsTitle = new JLabel("Settings - Work In Progress section");
        settingsTitle.setFont(verDLarge);
        settingsTitle.setForeground(whiteF);
        settingsPanel.add(settingsTitle, settP);
        
        settP.gridy++;
        JLabel settingsPurpleBar = new JLabel();
        settingsPurpleBar.setIcon(new ImageIcon(((References.createVisuals("/nonDBDArtwork/purpleBar.png")).getImage()).getScaledInstance(400, 3, java.awt.Image.SCALE_SMOOTH)));
        settingsPanel.add(settingsPurpleBar, settP);
        
        settP.gridy++;
        JPanel optionSets = new JPanel();
        JPanel charCheckPanel = new JPanel();
        JPanel itemPowerAddonsPanel = new JPanel();
        optionSets.setOpaque(false);
        optionSets.setLayout(new GridBagLayout());
        GridBagConstraints opSet = new GridBagConstraints();
        
        opSet.gridy = 0;
        {
        	JLabel settingsOptions = new JLabel("Select the parts you wish to randomize:");
        	settingsOptions.setOpaque(false);
        	settingsOptions.setFont(verD);
        	settingsOptions.setForeground(whiteF);
        	optionSets.add(settingsOptions, opSet);
        }
        
        opSet.gridy++;
        opSet.anchor = GridBagConstraints.WEST;
        {
            //charCheckPanel.setLayout(new GridBagLayout());
            //GridBagConstraints charCheckPanelLayout = new GridBagConstraints();
        	
            //charCheckPanelLayout.gridx = 0;
        	charCheck = new JCheckBox("Characters", true);
            charCheck.setOpaque(true);
            charCheck.setBackground(darkTranparency);
            charCheck.setFont(sulB);
            charCheck.setFont(verD);
            charCheck.setForeground(whiteF);
            charCheck.setFocusPainted(false);
            charCheckPanel.setOpaque(false);
            optionSets.add(charCheck, opSet);
        }
		
        opSet.gridy++;
        {
        	itemPowerAddonCheck = new JCheckBox("Items and addons", true);
        	itemPowerAddonCheck.setOpaque(true);
        	itemPowerAddonCheck.setBackground(darkTranparency);
            itemPowerAddonCheck.setFont(sulB);
            itemPowerAddonCheck.setFont(verD);
            itemPowerAddonCheck.setForeground(whiteF);
            itemPowerAddonCheck.setFocusPainted(true);
            itemPowerAddonsPanel.setOpaque(false);
            optionSets.add(itemPowerAddonCheck, opSet);
        }

        settingsPanel.add(optionSets, settP);
        
        settP.gridy++;
        {
        	settingsCloseButton = new JButton(new ImageIcon(((References.createVisuals("/nonDBDArtwork/blueBox.png").getImage()).getScaledInstance(350, 60, java.awt.Image.SCALE_SMOOTH))));
        	settingsCloseButton.setText("Close Settings");
        	settingsCloseButton.setPreferredSize(new Dimension(350, 60));
        	settingsCloseButton.setHorizontalTextPosition(JLabel.CENTER);
        	settingsCloseButton.setVerticalTextPosition(JLabel.CENTER);
        	settingsCloseButton.setFont(verD);
        	settingsCloseButton.setForeground(whiteF);
        	settingsCloseButton.setFocusPainted(false);
        	settingsPanel.add(settingsCloseButton, settP);
        }
        
        
        //infoPanel stuff
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints infoP = new GridBagConstraints();
        infoPanel.setBackground(darkTranparency);
        
        infoP.gridx = 0;
        infoP.gridy = 0;
        infoP.weighty = 1;
        JLabel informationTitle = new JLabel(References.APP_NAME + " | " + References.VERSION);
        informationTitle.setFont(verDLarge);
        informationTitle.setForeground(whiteF);
        infoPanel.add(informationTitle, infoP);
        
        infoP.gridy++;
        JLabel infoPurpleBar = new JLabel();
        infoPurpleBar.setIcon(new ImageIcon(((References.createVisuals("/nonDBDArtwork/purpleBar.png")).getImage()).getScaledInstance(950, 3, java.awt.Image.SCALE_SMOOTH)));
        infoPanel.add(infoPurpleBar, infoP);
        
        infoP.gridy++;
        String generalText = "<html>Dear user, thank you for trying out this Dead By Daylight Randomizer/Decider program!<br>"
        		+ "<br>This was, and still is a passion project to help me test out my programming skills that I<br>have had to learned at home out of absolute boredom. "
        		+ "The program is made with base<br>Java programming, ie no extra libraries, and is made to hopefully run on systems<br>regardless if they use"
        		+ " letter drive systems for storage or not."
        		+ "<br><br>All works of Dead By Daylight properties in this application belong to their respective<br>owners (Behavior Interactive) and I do not "
        		+ "obviously claim to own them. I only claim<br>the minimal"
        		+ " artwork I made for this program and the code which I plan to push onto<br>Github eventually for open source reasons and maybe inspire others wanting to"
        		+ "<br>program things themself with or without educational support!"
        		+ "<br><br>The aim of this program is to go a step further than other DBD randomizers by also<br>choosing which characters, items, addons, perks, and"
        		+ " offerings you should bring to a trial.<br> The program is recursive with a mostly psuedorandom algorithm so you can reroll as<br>many times as you wish."
        		+ " I absolutely hope you find this useful or charming at<br>the least to use it whenever you want to spice up your matches!"
        		+ "<br><br>Sincerely, SirBassington.";
        
        infoLabel = new JLabel(generalText);
        infoLabel.setFont(verDRegular);
        infoLabel.setForeground(whiteF);
        infoPanel.add(infoLabel, infoP);
        
        infoP.gridy++;
        infoCloseButton = new JButton(new ImageIcon(((References.createVisuals("/nonDBDArtwork/blueBox.png").getImage()).getScaledInstance(350, 60, java.awt.Image.SCALE_SMOOTH))));
        infoCloseButton.setText("Close Information Panel");
        infoCloseButton.setPreferredSize(new Dimension(350, 60));
        infoCloseButton.setHorizontalTextPosition(JLabel.CENTER);
        infoCloseButton.setVerticalTextPosition(JLabel.CENTER);
        infoCloseButton.setFont(verD);
        infoCloseButton.setForeground(whiteF);
        infoCloseButton.setFocusPainted(false);
        infoPanel.add(infoCloseButton, infoP);
        
        
        //dbdPanel stuff
        //Setting up Horizontal JPanel views
        dbdPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        leftStrip.setBackground(Color.BLUE);
        middleStrip.setBackground(Color.MAGENTA);
        rightStrip.setBackground(Color.PINK);
        
        c.gridx = 0;
        c.weighty = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.5;
        
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        dbdPanel.add(leftStrip, c);
        
        c.gridx++;
        dbdPanel.add(middleStrip, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx++;
        dbdPanel.add(rightStrip, c);
        
        rightStrip.setLayout(new GridBagLayout());
        GridBagConstraints r = new GridBagConstraints();
        
        rightPaneSummary = new JLabel();
        rightPaneSummary.setFont(verD);
        rightPaneSummary.setForeground(whiteF);
        rightStrip.add(rightPaneSummary, r);
        
        //Setting up Vertical JPanels inside of middleStrip
        middleStrip.setLayout(new GridBagLayout());
        GridBagConstraints h = new GridBagConstraints();
        
        buttonLayer.setBackground(Color.GREEN);
        descriptionLayer.setBackground(Color.YELLOW);
        legacySummaryLayer.setBackground(Color.ORANGE);
        topDescriptionLayer.setBackground(Color.CYAN);
        bottomDescriptionLayer.setBackground(Color.LIGHT_GRAY);
        perkLayer1.setBackground(Color.decode("#004d00"));
        perkLayer2.setBackground(Color.decode("#008000"));
        perkLayer3.setBackground(Color.decode("#00cc00"));
        perkLayer4.setBackground(Color.decode("#00ff00"));
        leftMost.setBackground(Color.decode("#4d004d"));
        leftLeast.setBackground(Color.decode("#800080"));
        rightLeast.setBackground(Color.decode("#b300b3"));
        rightMost.setBackground(Color.decode("#ff00ff"));
        rightLeastTop.setBackground(Color.decode("#ff80ff"));
        rightLeastBottom.setBackground(Color.decode("#ffb3ff"));
        
        h.gridy = 0;
        h.weightx = 1;
        h.fill = GridBagConstraints.HORIZONTAL;
        h.weighty = 0.5;
        
        h.anchor = GridBagConstraints.NORTH;
        middleStrip.add(buttonLayer, h);
        
        h.gridy++;
        middleStrip.add(descriptionLayer, h);
        
        h.gridy++;
        h.anchor = GridBagConstraints.SOUTH;
        middleStrip.add(legacySummaryLayer, h);
        
        //Debugging views for organization JPanels
        leftStrip.setOpaque(false);
        middleStrip.setOpaque(false);
        rightStrip.setOpaque(false);
        buttonLayer.setOpaque(false);
        descriptionLayer.setOpaque(false);
        legacySummaryLayer.setOpaque(false);
        topDescriptionLayer.setOpaque(false);
        bottomDescriptionLayer.setOpaque(false);
        perkLayer1.setOpaque(false);
        perkLayer2.setOpaque(false);
        perkLayer3.setOpaque(false);
        perkLayer4.setOpaque(false);
        leftMost.setOpaque(false);
        leftLeast.setOpaque(false);
        rightLeast.setOpaque(false);
        rightMost.setOpaque(false);
        rightLeastTop.setOpaque(false);
        rightLeastBottom.setOpaque(false);
        
        settingsButton = new RoundButton(new ImageIcon(((References.createVisuals("/settings.png").getImage()).getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH))));
        survButton = new RoundButton(new ImageIcon(((References.createVisuals("/surv.png").getImage()).getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH))));
        randButton = new RoundButton(new ImageIcon(((References.createVisuals("/rand.png").getImage()).getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH))));
        killButton = new RoundButton(new ImageIcon(((References.createVisuals("/kill.png").getImage()).getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH))));
        infoButton = new RoundButton(new ImageIcon(((References.createVisuals("/info.png").getImage()).getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH))));
        //infoButton.setText("<html>App<br>Info");
        //infoButton.setHorizontalTextPosition(JLabel.CENTER);
        //infoButton.setVerticalTextPosition(JLabel.CENTER);
        //infoButton.setFont(verD);
        //infoButton.setForeground(whiteF);
        
        //Begin adding user friendly UI features
        //Buttons that direct all program components
        buttonLayer.setLayout(new GridBagLayout());
        GridBagConstraints b = new GridBagConstraints();
        
        b.gridx = 0;
        buttonLayer.add(settingsButton, b);
        b.gridx++;
        b.insets = new Insets(0, 20, 0, 0);
        buttonLayer.add(survButton, b);
        b.gridx++;
        buttonLayer.add(randButton, b);
        b.gridx++;
        buttonLayer.add(killButton, b);
        b.gridx++;
        buttonLayer.add(infoButton, b);
        
        //leftStrip character 'shadow'
        leftStrip.setLayout(new GridBagLayout());
        GridBagConstraints l = new GridBagConstraints();
        
        charShadow = new JLabel();
        l.insets = new Insets(0,0,0,19);
        leftStrip.add(charShadow, l);
        
        //descriptionLayer features
        descriptionLayer.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        
        d.gridy = 0;
        d.weightx = 1;
        d.fill = GridBagConstraints.HORIZONTAL;
        d.weighty = 0.5;
        
        d.anchor = GridBagConstraints.NORTH;
        descriptionLayer.add(topDescriptionLayer, d);
        
        d.gridy++;
        descriptionLayer.add(bottomDescriptionLayer, d);
        
        topDescriptionLayer.setLayout(new GridBagLayout());
        GridBagConstraints t = new GridBagConstraints();
        
        t.gridx = 0;
        t.gridy = 0;
        topDescriptionLayer.add(leftMost, t);
        
        t.gridx++;
        topDescriptionLayer.add(leftLeast, t);
        
        t.gridx++;
        topDescriptionLayer.add(rightLeast, t);
        
        t.gridx++;
        topDescriptionLayer.add(rightMost, t);
        
        rightLeast.setLayout(new GridBagLayout());
        GridBagConstraints rl = new GridBagConstraints();
        
        rl.gridy = 0;
        rightLeast.add(rightLeastTop, rl);
        
        rl.gridy++;
        rightLeast.add(rightLeastBottom, rl);
        
        
        //Character Portrait mess
        charPortraitBackground = new JLabel();
        charPortrait = new JLabel();
        charPortraitOverlay = new JLabel();
        
        //Add the top-most (transparent) item first then place the next steps in order
        leftMost.setLayout(new OverlayLayout(leftMost));
        leftMost.add(charPortraitOverlay, BorderLayout.CENTER);
        leftMost.add(charPortrait, BorderLayout.CENTER);
        leftMost.add(charPortraitBackground, BorderLayout.CENTER);
        
        leftLeast.setLayout(new GridBagLayout());
        GridBagConstraints ll = new GridBagConstraints();
        
        itemPower = new JLabel();
        ll.anchor = GridBagConstraints.CENTER;
        leftLeast.add(itemPower, ll);
        
        rightLeastTop.setLayout(new GridBagLayout());
        GridBagConstraints rlt = new GridBagConstraints();
        
        addOn1 = new JLabel();
        rlt.anchor = GridBagConstraints.CENTER;
        addOn1 = new JLabel();
        rightLeastTop.add(addOn1, rlt);
        
        rightLeastBottom.setLayout(new GridBagLayout());
        GridBagConstraints rlb = new GridBagConstraints();
        
        addOn2 = new JLabel();
        rlb.anchor = GridBagConstraints.CENTER;
        addOn2 = new JLabel();
        rightLeastBottom.add(addOn2, rlb);
        
        rightMost.setLayout(new GridBagLayout());
        GridBagConstraints rm = new GridBagConstraints();
        
        offering = new JLabel();
        rm.anchor = GridBagConstraints.CENTER;
        offering = new JLabel();
        rightMost.add(offering, rm);
        
        bottomDescriptionLayer.setLayout(new GridBagLayout());
        GridBagConstraints bo = new GridBagConstraints();
        
        bo.gridx = 0;
        bottomDescriptionLayer.add(perkLayer1, bo);

        bo.gridx++;
        bottomDescriptionLayer.add(perkLayer2, bo);

        bo.gridx++;
        bottomDescriptionLayer.add(perkLayer3, bo);

        bo.gridx++;
        bottomDescriptionLayer.add(perkLayer4, bo);
        
        //Add perks to perkLayers
        perk1 = new JLabel();
        perkLayer1.add(perk1);
        
        perk2 = new JLabel();
        perkLayer2.add(perk2);
        
        perk3 = new JLabel();
        perkLayer3.add(perk3);
        
        perk4 = new JLabel();
        perkLayer4.add(perk4);
        
        summary = new JLabel();
        summary.setFont(sulB);
        summary.setForeground(whiteF);
        legacySummaryLayer.add(summary);
        
        //Start the application with settingsPanel and infoPanel already disabled in sight
        settingsPanel.setVisible(false);
        infoPanel.setVisible(false);
        
        survButton.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent act)
			{
				isSurvivor = true;
		    	isKiller = false;
		    	isUndecided = false;
		    	Decisions.detector();
		    }
		});
		randButton.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent act)
			{
				isSurvivor = false;
		    	isKiller = false;
		    	isUndecided = true;
		    	Decisions.detector();
		    }
		});
		killButton.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent act)
			{
				isSurvivor = false;
		    	isKiller = true;
		    	isUndecided = false;
		    	Decisions.detector();
		    }
		});
		infoButton.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent act)
			{
				survButton.setEnabled(false);
				randButton.setEnabled(false);
				killButton.setEnabled(false);
				infoButton.setEnabled(false);
				settingsButton.setEnabled(false);
				infoPanel.setVisible(true);
		    }
		});
		infoCloseButton.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent act)
			{
				survButton.setEnabled(true);
				randButton.setEnabled(true);
				killButton.setEnabled(true);
				infoButton.setEnabled(true);
				settingsButton.setEnabled(true);
				infoPanel.setVisible(false);
		    }
		});
		settingsButton.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent act)
			{
				survButton.setEnabled(false);
				randButton.setEnabled(false);
				killButton.setEnabled(false);
				infoButton.setEnabled(false);
				settingsButton.setEnabled(false);
				settingsPanel.setVisible(true);
		    }
		});
		settingsCloseButton.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent act)
			{
				survButton.setEnabled(true);
				randButton.setEnabled(true);
				killButton.setEnabled(true);
				infoButton.setEnabled(true);
				settingsButton.setEnabled(true);
				settingsPanel.setVisible(false);
		    }
		});
    }
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI()
    {
    	Panel background = new Panel();
        JFrame frame = new JFrame(References.APP_NAME + " " + References.VERSION);
        frame.setContentPane(background);
        
        //frame.setContentPane(background);
        frame.setResizable(false);
        frame.setSize(1600, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        addComponentsToPane(frame.getContentPane());
    }
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
            @Override
            public void run()
            {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                createAndShowGUI();
            }
        });
	}
	
	/**
	 * I am unsure why I have this in here along with the other actionPerformed states in the method addComponentsToPane().
	 * I know this method is required because of the implements ActionListener, but I have yet to rework the other actionPerformed statements
	 * 	 - unless they don't need to be changed and this is really fine.
	 */
	@Override
	public void actionPerformed(ActionEvent act)
	{
		survButton.addActionListener(this);
		randButton.addActionListener(this);
		killButton.addActionListener(this);
		infoButton.addActionListener(this);
		infoCloseButton.addActionListener(this);
	}
	
	public static class Panel extends JPanel
	{
        public Panel()
        {
            try {
                image = ImageIO.read(getClass().getResource("/assets/campfire.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Dimension getPreferredSize()
        {
			return image == null ? super.getPreferredSize() : new Dimension(image.getWidth(), image.getHeight());
        }

        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
}