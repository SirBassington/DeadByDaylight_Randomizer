package dbd_decider;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Decisions extends DeadByDaylight_Decider {
	
	protected static void detector()
	{
		//Reset choices in the event another selection is run without closing the application
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter time = DateTimeFormatter.ofPattern("\nHH:mm:ss");
		References.statusAgent(time.format(now) + " selections:");
		baseChoice = null;
		charType = null;
		itemChoice = null;
		finalChar = null;
		finalAddOn1 = null;
		finalAddOn2 = null;
		finalOffering = null;
		finalPerk1 = null;
		finalPerk2 = null;
		finalPerk3 = null;
		finalPerk4 = null;
		charShadow.setIcon(new ImageIcon(((References.createVisuals("/characters/blank.png")).getImage()).getScaledInstance(300, 900, java.awt.Image.SCALE_SMOOTH)));
		charPortrait.setIcon(new ImageIcon((References.createVisuals("/blank.png")).getImage()));
        itemPower.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        addOn1.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
        addOn2.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
        offering.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
        perk1.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
        perk2.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
        perk3.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
        perk4.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		
		if (isSurvivor == true)
		{
			randomizeSurvivor();
		}
		if (isKiller == true)
		{
			randomizeKiller();
		}
		if (isUndecided == true)
		{
			randomizeBoth();
		}
	}
	
	/**
	 * Method that starts from randButton to decide whether to choose survivor or kill.
	 * Then will push the choice onto the respective path as if one of the other buttons was chosen.
	 */
	protected static void randomizeBoth()
	{
		int count = 1;
		int survivor = 0;
		int killer = 0;
		/*
		 * Loop for the random decision.
		 * Pulls a choice of survivor or killer 99 times and which has
		 * majority of 50 of more, is the chosen choice for that pull.
		 */
		while (count < 99)
		{
			int rand = new Random().nextInt(10);
			if (rand % 2 == 0)
			{
				survivor++;
			}
			else killer++;
			count++;
		}
		
		if (survivor > killer)
		{
			isSurvivor = true;
			randomizeSurvivor();
		}
		else
		{
			isKiller = true;
			randomizeKiller();
		}
	}
	
	protected static String randomizeSurvivor()
	{
		charType = "survivor";
		
		String[] characters = new String[] {"Dwight Fairfield", "Meg Thomas", "Claudette Morel", "Jake Park", "Nea Karlsson", "Laurie Strode",
				"Ace Visconti", "Bill Overbeck", "Feng Min", "David King", "Quentin Smith", "David Tapp", "Kate Denson", "Adam Francis",
				"Jeff Johansen", "Jane Romero", "Ash Williams", "Nancy Wheeler", "Steve Harrington", "Yui Kimura", "Zarina Kassir"};
		
		int choice = new Random().nextInt(characters.length);
		finalChar = (characters[choice]);
		
		References.statusAgent("Survivor chosen: " + finalChar);

		charPortraitScale(finalChar);
		
		decideItems();
        return finalChar;
	}
	
	protected static String randomizeKiller()
	{
		charType = "killer";
		
		String[] characters = new String[] {"The Trapper", "The Wraith", "The Hillbilly", "The Nurse", "The Shape", "The Hag", "The Doctor", "The Huntress",
				"The Cannibal", "The Nightmare", "The Pig", "The Clown", "The Spirit", "The Legion", "The Plague", "The Ghost Face", "The Demogorgon",
				"The Oni", "The Deathslinger"};
		
		String[] characterPower = new String[] {"Bear Trap", "Wailing Bell", "Chainsaw", "Spencer's Last Breath", "Evil Within", "Blackened Catalyst",
				"Carter's Spark", "Hunting Hatchets", "Bubba's Chainsaw", "Dream Demon", "Jigsaw's Baptism", "The Afterpiece Tonic", "Yamaoka's Haunting",
				"Feral Frenzy", "Vile Purge", "Night Shroud", "Of The Abyss", "Yamaoka's Wrath", "The Redeemer"};
		
		int choice = new Random().nextInt(characters.length);
		finalChar = (characters[choice]);
		String power = characterPower[choice];
		
		References.statusAgent("Killer chosen: " + finalChar);
		
		charPortraitScale(finalChar);
		itemPowerScale(charType, power);

		decideKAddons();
		return finalChar;
	}
	
	private static void decideItems()
	{	
		String[] baseItem = {"None", "Toolbox", "Medkit", "Flashlight", "Map", "Key"};
		
		String[] toolboxes = {"Worn-Out Tools", "Toolbox", "Mechanic's Toolbox", "Commodious Toolbox", "Engineer's Toolbox", "Alex's Toolbox"};
		String[] medkits = {"Camping Aid Kit", "First Aid Kit", "Emergency Med-Kit", "Ranger Med-Kit"};
		String[] flashlights = {"Flashlight", "Sport Flashlight", "Utility Flashlight"};
		String[] maps = {"Map", "Rainbow Map"};
		String[] keys = {"Broken Key", "Dull Key", "Skeleton Key"};
		
		int choice = new Random().nextInt(baseItem.length);
		baseChoice = (baseItem[choice]);
		
		if (baseChoice == "Toolbox")
		{
			int toolboxChoice = new Random().nextInt(toolboxes.length);
			finalToolbox = (toolboxes[toolboxChoice]);
			
			itemChoice = finalToolbox;
		}
		if (baseChoice == "Medkit")
		{
			int medkitChoice = new Random().nextInt(medkits.length);
			finalMedkit = (medkits[medkitChoice]);
			
			itemChoice = finalMedkit;
		}
		if (baseChoice == "Flashlight")
		{
			int flashlightChoice = new Random().nextInt(flashlights.length);
			finalFlashlight = (flashlights[flashlightChoice]);
			
			itemChoice = finalFlashlight;
		}
		if (baseChoice == "Map")
		{
			int mapChoice = new Random().nextInt(maps.length);
			finalMap = (maps[mapChoice]);

			itemChoice = finalMap;
		}
		if (baseChoice == "Key")
		{
			int keyChoice = new Random().nextInt(keys.length);
			finalKey = (keys[keyChoice]);
			
			itemChoice = finalKey;
		}
		if (baseChoice == "None")
		{
			itemChoice = null;
		}
		
		if (itemChoice != null)
		{
			itemPowerScale(charType, itemChoice);
		}
		
		if (baseChoice != "None")
		{
			decideSAddons();
		}
		else
		{
			offerings();
		}
	}
	
	private static void decideSAddons()
	{
		int amount = new Random().nextInt(3); //3 choices: 0, 1, or 2 addons
		References.statusAgent("method decideSAddons. Number of addons chosen: " + amount);
		
		if (amount == 0)
		{
			addOnAmount = 0;
			finalAddOn1 = null;
			finalAddOn2 = null;
		}
		if (amount == 1)
		{
			addOnAmount = 1;
			finalAddOn2 = null;
		}
		if (amount == 2)
		{
			addOnAmount = 2;
		}
		
		if (baseChoice == "Flashlight")
		{
			/*
			 * A List is used here rather than a String array. Mostly because a String array does NOT support a .remove() functionality 'easily',
			 * so this is my lazy way of doing less work (and sadly making the program larger)
			 */
			List<String> addons = new ArrayList<String>();
			addons.add("Battery");addons.add("Leather Grip");addons.add("Power Bulb");addons.add("Wide Lens");addons.add("Tir Optic");addons.add("Focus Lens");addons.add("Heavy Duty Battery");
			addons.add("Low Amp Filament");addons.add("Rubber Grip");addons.add("Intense Halogen");addons.add("Long Life Battery");addons.add("High-End Sapphire Lens");addons.add("Odd Bulb");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				
				addonScale(charType, baseChoice, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);
				
				addonScale(charType, baseChoice, finalAddOn1, finalAddOn2);
			}
		}
		if (baseChoice == "Medkit")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Bandages");addons.add("Butterfly Tape");addons.add("Rubber Gloves");addons.add("Gauze Roll");addons.add("Medical Scissors");addons.add("Needle & Thread");addons.add("Self Adherent Wrap");
			addons.add("Sponge");addons.add("Abdominal Dressing");addons.add("Gel Dressings");addons.add("Surgical Suture");addons.add("Styptic Agent");addons.add("Anti-Haemorrhagic Syringe");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				
				addonScale(charType, baseChoice, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);
				
				addonScale(charType, baseChoice, finalAddOn1, finalAddOn2);
			}
		}
		if (baseChoice == "Map")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Map Addendum");addons.add("Glass Bead");addons.add("Red Twine");addons.add("Retardant Jelly");addons.add("Unusual Stamp");
			addons.add("Yellow Wire");addons.add("Black Silk Cord");addons.add("Odd Stamp");addons.add("Crystal Bead");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				
				addonScale(charType, baseChoice, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);
				
				addonScale(charType, baseChoice, finalAddOn1, finalAddOn2);
			}
		}
		if (baseChoice == "Key")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Prayer Rope");addons.add("Eroded Token");addons.add("Prayer Beads");addons.add("Scratched Pearl");
			addons.add("Gold Token");addons.add("Milky Glass");addons.add("Blood Amber");addons.add("Weaved Ring");addons.add("Unique Wedding Ring");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				
				addonScale(charType, baseChoice, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);
				
				addonScale(charType, baseChoice, finalAddOn1, finalAddOn2);
			}
		}
		if (baseChoice == "Toolbox")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Clean Rag");addons.add("Scraps");addons.add("Spring Clamp");addons.add("Cutting Wire");addons.add("Grip Wrench");addons.add("Instructions");
			addons.add("Protective Gloves");addons.add("Socket Swivels");addons.add("Wire Spool");addons.add("Hacksaw");addons.add("Brand New Part");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				
				addonScale(charType, baseChoice, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);
				
				addonScale(charType, baseChoice, finalAddOn1, finalAddOn2);
			}
		}
		offerings();
	}
	
	private static void decideKAddons()
	{
		int amount = new Random().nextInt(3);  //3 choices: 0, 1, or 2 addons
		References.statusAgent("method decideKAddons. Number of addons chosen: " + amount);
		
		if (finalChar == "The Trapper")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Strong Coil Spring");addons.add("Padded Jaws");addons.add("Trapper Gloves");addons.add("Trapper Sack");
			addons.add("4-Coil Spring Kit");addons.add("Logwood Dye");addons.add("Serrated Jaws");addons.add("Trapper Bag");
			addons.add("Trap Setters");addons.add("Rusted Jaws");addons.add("Secondary Coil");addons.add("Setting Tools");
			addons.add("Tar Bottle");addons.add("Wax Brick");addons.add("Fastening Tools");addons.add("Honing Stone");
			addons.add("Stitched Bag");addons.add("Oily Coil");addons.add("Bloody Coil");addons.add("Iridescent Stone");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);
				
				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
			
			//baseChoice = "tra";
		}
		if (finalChar == "The Wraith")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("The Beast - Soot");addons.add("The Ghost - Soot");addons.add("The Hound - Soot");addons.add("The Serpent - Soot");
			addons.add("Blind Warrior - Mud");addons.add("Blink - Mud");addons.add("Bone Clapper");addons.add("Swift Hunt - Mud");
			addons.add("Windstorm - Mud");addons.add("Blind Warrior - White");addons.add("Blink - White");addons.add("Shadow Dance - White");
			addons.add("Swift Hunt - White");addons.add("Windstorm - White");addons.add("All Seeing - Blood");addons.add("Shadow Dance - Blood");
			addons.add("Swift Hunt - Blood");addons.add("Windstorm - Blood");addons.add("All Seeing - Spirit");addons.add("Coxcombed Clapper");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);
				
				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Hillbilly")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Chainsaw File");addons.add("Spark Plug");addons.add("Vegetable Oil");addons.add("Death Engravings");
			addons.add("Depth Gauge Rake");addons.add("Homemade Muffler");addons.add("Long Guide Bar");addons.add("Grisly Chains");
			addons.add("Primer Bulb");addons.add("Shop Lubricant");addons.add("Speed Limiter");addons.add("Spiked Boots");
			addons.add("Carburettor tuning Guide");addons.add("Doom Engravings");addons.add("Light Chassis");addons.add("Rusted Chains");
			addons.add("The Thompson's Mix");addons.add("Begrimed Chains");addons.add("Thompson's Moonshine");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Nurse")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Plaid Flannel");addons.add("Wooden Horse");addons.add("Metal Spoon");addons.add("White Nit Comb");
			addons.add("Bad Man Keepsake");addons.add("Catatonic Boy's Treasure");addons.add("Dark Cincture");addons.add("Dull Bracelet");
			addons.add("Pocket Watch");addons.add("Anxious Gasp");addons.add("Ataxic Respiration");addons.add("Fragile Wheeze");
			addons.add("Heavy Panting");addons.add("Spasmodic Breath");addons.add("Bad Man's Last Breath");addons.add("Campbell's Last Breath");
			addons.add("Jenner's Last Breath");addons.add("Kavanagh's Last Breath");addons.add("Matchbox");addons.add("Torn Bookmark");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Shape")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Blonde Hair");addons.add("Boyfriend's Memo");addons.add("Tacky Earrings");addons.add("Dead Rabbit");
			addons.add("Glass Fragment");addons.add("Hair Brush");addons.add("Jewelry");addons.add("Memorial Flower");
			addons.add("Reflective Fragment");addons.add("Hair Bow");addons.add("J. Myer's Memorial");addons.add("Jewelry Box");
			addons.add("Judith's Journal");addons.add("Mirror Shard");addons.add("Lock Of Hair");addons.add("Scratched Mirror");
			addons.add("Tombstone Piece");addons.add("Vanity Mirror");addons.add("Fragrant Tuft Of Hair");addons.add("Judith's Tombstone");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Hag")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Bog Water");addons.add("Dead Fly Mud");addons.add("Powdered Eggshell");addons.add("Rope Necklet");
			addons.add("Bloodied Water");addons.add("Cypress Necklet");addons.add("Dragonfly Wings");addons.add("Half Eggshell");
			addons.add("Pussy Willow Catkins");addons.add("Bloodied Mud");addons.add("Cracked Turtle Egg");addons.add("Dried Cicada");
			addons.add("Swamp Orchid Necklet");addons.add("Willow Wreath");addons.add("Disfigured Ear");addons.add("Granma's Heart");
			addons.add("Rusty Shackles");addons.add("Scarred Hand");addons.add("Mint Rag");addons.add("Waterlogged Shoe");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Doctor")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Calm - Class I");addons.add("Maple Knight");addons.add("Moldy Electrode");addons.add("Order - Class I");
			addons.add("Calm - Class II");addons.add("Discipline - Class II");addons.add("Order - Class II");addons.add("Polished Electrode");
			addons.add("Restraint - Class II");addons.add("Discipline - Class III");addons.add("High Stimulus Electrode");addons.add("Interview Tape");
			addons.add("Obedience - Class III");addons.add("Restraint - Class III");addons.add("Calm - Carter's Notes");
			addons.add("Discipline - Carter's Notes");addons.add("Order - Carter's Notes");addons.add("Restraint - Carter's Notes");
			addons.add("Iridescent King");addons.add("Iridescent Queen");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Huntress")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Coarse Stone");addons.add("Berus Toxin");addons.add("Bandaged Haft");addons.add("Amanita Toxin");
			addons.add("Yew Seed Brew");addons.add("Shiny Pin");addons.add("Oak Haft");addons.add("Manna Grass Braid");
			addons.add("Leather Loop");addons.add("Fine Stone");addons.add("Deerskin Gloves");addons.add("Venomous Concoction");
			addons.add("Rusty Head");addons.add("Pungent Phial");addons.add("Flower Babushka");addons.add("Yew Seed Concoction");
			addons.add("Infantry Belt");addons.add("Glowing Concoction");addons.add("Begrimed Head");addons.add("Iridescent Head");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Cannibal")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Chainsaw File");addons.add("Spark Plug");addons.add("Vegetable Oil");addons.add("Chili");
			addons.add("Depth Gauge Rake");addons.add("Homemade Muffler");addons.add("Long Guide Bar");addons.add("Primer Bulb");
			addons.add("Shop Lubricant");addons.add("Speed Limiter");addons.add("Knife Scratches");addons.add("Carburettor Tuning Guide");
			addons.add("The Beast's Marks");addons.add("Light Chassis");addons.add("Rusted Chains");addons.add("The Grease");
			addons.add("Begrimed Chains");addons.add("Award-winning Chilli");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Nightmare")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Garden Rake");addons.add("Kid's Drawing");addons.add("Sheep Block");addons.add("Wool Shirt");
			addons.add("Cat Block");addons.add("Green Dress");addons.add("Nancy's Sketch");addons.add("Prototype Claws");
			addons.add("Outdoor Rope");addons.add("Nancy's Masterpiece");addons.add("Swing Chains");addons.add("Unicorn Block");
			addons.add("Jump Rope");addons.add("Blue Dress");addons.add("Paint Thinner");addons.add("Class Photo");
			addons.add("Z Block");addons.add("Pill Bottle");addons.add("Red Paint Brush");addons.add("Black Box");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Pig")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Workshop Grease");addons.add("Shattered Syringe");addons.add("John's Medical File");addons.add("Combat Straps");
			addons.add("Video Tape");addons.add("Utility Blades");addons.add("Razor Wires");addons.add("Face Mask");
			addons.add("Last Will");addons.add("Rusty Attachments");addons.add("Jigsaw's Annotated Plan");addons.add("Interlocking Razor");
			addons.add("Bag Of Gears");addons.add("Slow-Release Toxin");addons.add("Tampered Timer");addons.add("Jigsaw's Sketch");
			addons.add("Crate Of Gears");addons.add("Amanda's Secret");addons.add("Rules Set No. 2");addons.add( "Amanda's Letter");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Clown")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Ether 5 Vol");addons.add("Robin Feather");addons.add("Smelly Inner Soles");addons.add("Fingerless Parade Gloves");
			addons.add("Kerosene Can");addons.add("Solvent Jug");addons.add("Starling Feather");addons.add("Sticky Soda Bottle");
			addons.add("Thick Cork Stopper");addons.add("Bottle Of Chloroform");addons.add("Ether 10 Vol");addons.add("Flask Of Bleach");
			addons.add("Sulphuric Acid Vial");addons.add("VHS Porn");addons.add("Cheap Gin Bottle");addons.add("Cigar Box");
			addons.add("Ether 15 Vol");addons.add("Make-Up Kit");addons.add("Redhead's Pinky Finger");addons.add("Tattoo's Middle Finger");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Spirit")
		{	
			List<String> addons = new ArrayList<String>();
			addons.add("Gifted Bamboo Comb");addons.add("Origami Crane");addons.add("Shiawase Amulet");addons.add("Zori");
			addons.add("Juniper Bonsai");addons.add("Kaiun Talisman");addons.add("Muddy Sports Day Cap");addons.add("Rins Broken Watch");
			addons.add("White Hair Ribbon");addons.add("Bloody Hair Brooch");addons.add("Dirt Uwabaki");addons.add("Katana Tsuba");
			addons.add("Katsumori Talisman");addons.add("Rusty Flute");addons.add("Dried Cherry Blossom");addons.add("Prayer Beads Bracelet");
			addons.add("Wakizashi Saya");addons.add("Yakuyoke Amulet");addons.add("Father's Glasses");addons.add("Mother-Daughter Ring");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Legion")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Friendship Bracelet");addons.add("Mischief List");addons.add("Scratched Ruler");addons.add("Smiley Face Pin");
			addons.add("Defaced Smiley Pin");addons.add("Etched Ruler");addons.add("Julie's Mix Tape");addons.add("Mural Sketch");
			addons.add("Never-Sleep Pills");addons.add("Joey's Mix Tape");addons.add("Nasty Blade");addons.add("Stolen Sketch Book");
			addons.add("Susie's Mix Tape");addons.add("The Legion Pin");addons.add("Cold Dirt");addons.add("Filthy Blade");
			addons.add("Frank's Mix Tape");addons.add("Stab Wounds Study");addons.add("Fuming Mix Tape");addons.add("Iridescent Button");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Plague")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Healing Salve");addons.add("Limestone Seal");addons.add("Olibanum Incense");addons.add("Prayer Tablet Fragment");
			addons.add("Blessed Apple");addons.add("Emetic Potion");addons.add("Haematite Seal");addons.add("Potent Tincture");
			addons.add("Prophylactic Amulet");addons.add("Ashen Apple");addons.add("Exorcism Amulet");addons.add("Incensed Ointment");
			addons.add("Infected Emetic");addons.add("Rubbing Oil");addons.add("Devotes Amulet");addons.add("Severed Toe");
			addons.add("Vile Emetic");addons.add("Worship Tablet");addons.add("Black Incense");addons.add("Iridescent Seal");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Ghost Face")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Headlines Cutouts");addons.add("Philly");addons.add("Walleyes Matchbook");addons.add("Cheap Cologne");
			addons.add("Marked Map");addons.add("Olsens Address Book");addons.add("Olsens Journal");addons.add("Reusuable Cinch Straps");
			addons.add("Telephoto Lens");addons.add("Leather Knife Sheath");addons.add("Olsens Wallet");addons.add("Chewed Pen");
			addons.add("Knife Belt Clip");addons.add("Lasting Perfume");addons.add("Drivers License");addons.add("Drop-Leg Knife Sheath");
			addons.add("Nightvision Monocular");addons.add("Victims Detailed Routine");addons.add("Outdoor Security Camera");addons.add("Ghost Face Caught On Tape");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Demogorgon")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Black Heart");addons.add("Rat Liver");addons.add("Rat Tail");addons.add("Rotten Pumpkin");addons.add("Barb's Glasses");
			addons.add("Mew's Guts");addons.add("Rotten Green Tripe");addons.add("Sticky Lining");addons.add("Viscous Webbing");addons.add("Brass Case Lighter");
			addons.add("Deer Lung");addons.add("Eleven's Soda");addons.add("Thorny Vines");addons.add("Violet Waxcap");addons.add("Lifeguard Whistle");
			addons.add("Upside Down Resin");addons.add("Unknown Egg");addons.add("Vermilion Webcap");addons.add("Leprose Lichen");addons.add("Red Moss");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Oni")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Blackened Toenail");addons.add("Cracked Sakazuki");addons.add("Rotting Rope");addons.add("Yakuyoke Talisman");
			addons.add("Bloody Sash");addons.add("Child's Wooden Sword");addons.add("Chipped Saihai");addons.add("Ink Lion");
			addons.add("Polished Maedate");addons.add("Kanai-Anzen Talisman");addons.add("Scalped Topknot");addons.add("Shattered Wakizashi");
			addons.add("Wooden Oni Mask");addons.add("Yamaoka Sashimono");addons.add("Akito's Crutch");addons.add("Lion Fang");
			addons.add("Splintered Hull");addons.add("Tear Soaked Tenugui");addons.add("Iridescent Family Crest");addons.add("Renjiro's Bloody Glove");
			
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		if (finalChar == "The Deathslinger")
		{
			List<String> addons = new ArrayList<String>();
			addons.add("Spit Polish Rag");addons.add("Snake Oil");addons.add("Rickety Chain");addons.add("Modified Ammo Belt");
			addons.add("Rusted Spike");addons.add("Poison Oak Leaves");addons.add("Marshal's Badge");addons.add("Jaw Smasher");
			addons.add("Chewing Tobacco");addons.add("Warden's Keys");addons.add("Wanted Poster");addons.add("Tin Oil Can");
			addons.add("Honey Locust Thorn");addons.add("Bayshore's Gold Tooth");addons.add("Prison Chain");addons.add("Gold Creek Whiskey");
			addons.add("Bayshore's Cigar");addons.add("Barbed Wire");addons.add("Iridescent Coin");addons.add("Hellshire Iron");
			if (amount == 1)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);

				addonScale(charType, finalChar, finalAddOn1, null);
			}
			if (amount == 2)
			{
				String randPull1 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn1 = randPull1;
				addons.remove(randPull1);
				
				String randPull2 = addons.get(new Random().nextInt(addons.size()));
				finalAddOn2 = randPull2;
				addons.remove(randPull2);

				addonScale(charType, finalChar, finalAddOn1, finalAddOn2);
			}
		}
		itemChoice = null;
		offerings();
	}
	
	/**
	 * Determine if an Offering should be chosen or not, if so an Offering will be chosen
	 * per Killer or Survivor side with a chance to pull from the shared Offering list.
	 */
	private static void offerings()
	{
		//Survivor-only Offerings
		String[] sOfferings = {"Bog Laurel Sachet", "Crispleaf Amaranth Sachet", "Primrose Blossom Sachet", "Sweet William Sachet", "Fresh Bog Laurel", "Fresh Crispleaf Amaranth",
				"Fresh Primrose Blossom", "Fresh Sweet William", "Fragrant Bog Laurel", "Fragrant Crispleaf Amaranth", "Fragrant Primrose Blossom", "Fragrant Sweet William",
				"Sealed Envelope", "Bound Envelope", "Escape! Cake", "Chalk Pouch", "Cream Chalk Pouch", "Ivory Chalk Pouch", "Salt Pouch", "Black Salt Statuette",
				"Vigo's Jar Of Salty Lips", "Tarnished Coin", "Shiny Coin", "Petrified Oak", "Shroud Of Union", "Vigo's Shroud", "Shroud Of Binding"};
		
		//Killer-only Offerings
		String[] kOfferings = {"Raven Wreath", "Shrike Wreath", "Spotted Owl Wreath", "Tanager Wreath", "Devout Raven Wreath", "Devout Shrike Wreath",
				"Devout Spotted Owl Wreath", "Devout Tanager Wreath", "Ardent Raven Wreath", "Ardent Shrike Wreath", "Ardent Spotted Owl Wreath",
				"Ardent Tanager Wreath", "Hollow Shell", "Survivor Pudding", "Scratched Coin", "Cut Coin", "Cypress Memento Mori", "Ivory Memento Mori",
				"Ebony Memento Mori", "Shroud Of Separation", "Black Ward", "Moldy Oak", "Rotten Oak", "Putrid Oak"};
		
		//Shared Offerings
		String[] sharedOfferings = {"Azarov's Key", "Charred Wedding Photograph", "Damaged Photo", "Granma's Cookbook", "Heart Locket", "Jigsaw Piece", "Shattered Glasses",
				"MacMilian's Phalanx Bone", "Strode Realty Key", "The Last Mask", "The Pied Piper", "Yamaoka Family Crest", "Clear Reagent", "Faint Reagent", "Hazy Reagent",
				"Murky Reagent", "Bloody Party Streamers"};
		
		//Choose whether an Offering will be used or not
		int count = 1;
		int shouldDecideOffering = 0;
		int shouldNotDecideOffering = 0;
		
		//The loop
		while (count < 99)
		{
			int rand = new Random().nextInt(10);
			if (rand % 2 == 0)
			{
				shouldDecideOffering++;
			}
			else shouldNotDecideOffering++;
			count++;
		}
		
		if (shouldDecideOffering > shouldNotDecideOffering)
		{
			//Decide if a side-unique or shared Offering should be used.
			int roll = 1;
			int chooseUniqueOfferingPool = 0;
			int chooseSharedOfferingPool = 0;
			
			while (roll <= 99)
			{
				int rand = new Random().nextInt(10);
				if (rand % 2 == 0)
				{
					chooseUniqueOfferingPool++;
				}
				else chooseSharedOfferingPool++;
				roll++;
			}
			//For Survivor Offering options
			if (charType == "survivor")
			{
				//Pull from survivor-unique pool
				if (chooseUniqueOfferingPool > chooseSharedOfferingPool)
				{
					int offeringChoice = new Random().nextInt(sOfferings.length);
					finalOffering = (sOfferings[offeringChoice]);
					offeringScale("survivor/", finalOffering);
				}
				else
				{
					//Pull from shared pool
					int offeringChoice = new Random().nextInt(sharedOfferings.length);
					finalOffering = (sharedOfferings[offeringChoice]);
					offeringScale("both/", finalOffering);
				}
			}
			//For killer Offering options
			else
			{
				//Pull from killer-unique pool
				if (chooseUniqueOfferingPool > chooseSharedOfferingPool)
				{
					int offeringChoice = new Random().nextInt(kOfferings.length);
					finalOffering = (kOfferings[offeringChoice]);
					offeringScale("killer/", finalOffering);
				}
				else
				{
					//Pull from shared pool
					int offeringChoice = new Random().nextInt(sharedOfferings.length);
					finalOffering = (sharedOfferings[offeringChoice]);
					offeringScale("both/", finalOffering);
				}
			}
		}
		else
		{
			//No Offering, nothing to do here
		}

		References.statusAgent("method offerings(). Offering chosen: " + finalOffering);

        if (charType == "survivor")
        {
        	survivorPerks();
        }
        else
        {
        	killerPerks();
        }
	}
	
	/**
	 * Chooses what perks will be chosen between 0 to 4.
	 */
	private static void survivorPerks()
	{
		References.statusAgent("method survivorPerks() reached");
		
		List<String> perks = new ArrayList<String>();
		perks.add("Ace In The Hole");perks.add("Adrenaline");perks.add("Aftercare");perks.add("Alert");perks.add("Autodidact");perks.add("Any Means Necessary");perks.add("Baby Sitter");
		perks.add("Balanced Landing");perks.add("Better Together");perks.add("Boil Over");perks.add("Bond");perks.add("Borrowed Time");perks.add("Botany Knowledge");perks.add("Breakdown");perks.add("Breakout");
		perks.add("Buckle Up");perks.add("Calm Spirit");perks.add("Camaraderie");perks.add("Dance With Me");perks.add("Dark Sense");perks.add("Dead Hard");perks.add("Decisive Strike");perks.add("Déjà Vu");
		perks.add("Deliverance");perks.add("Detective's Hunch");perks.add("Distortion");perks.add("Diversion");perks.add("Empathy");perks.add("Fixated");perks.add("Flip-Flop");perks.add("Head On");
		perks.add("Hope");perks.add("Inner Strength");perks.add("Iron Will");perks.add("Kindred");perks.add("Leader");perks.add("Left Behind");perks.add("Lightweight");perks.add("Lithe");perks.add("Lucky Break");
		perks.add("Mettle Of Man");perks.add("No Mither");perks.add("No One Left Behind");perks.add("Object Of Obsession");perks.add("Open-Handed");perks.add("Pharmacy");perks.add("Plunderer's Instinct");
		perks.add("Poised");perks.add("Premonition");perks.add("Prove Thyself");perks.add("Quick & Quiet");perks.add("Resilience");perks.add("Saboteur");perks.add("Second Wind");perks.add("Self Care");
		perks.add("Slippery Meat");perks.add("Small Game");perks.add("Sole Survivor");perks.add("Solidarity");perks.add("Spine Chill");perks.add("Sprint Burst");perks.add("Stake Out");perks.add("Streetwise");
		perks.add("Technician");perks.add("Tenacity");perks.add("This Is Not Happening");perks.add("Unbreakable");perks.add("Up The Ante");perks.add("Urban Evasion");perks.add("Vigil");perks.add("Wake Up");
		perks.add("We'll Make It");perks.add("We're Gonna Live Forever");perks.add("Windows Of Opportunity");
		
		//Debugging counter. Counts how many survivor perks are recognized in perks list
		References.statusAgent("Length of survivorPerks() list: " + perks.size());
		
		int amount = new Random().nextInt(4);
		References.statusAgent("Amount of perks to generate " + (amount + 1));
		perkAmount = amount;
		
		String[] tempPerks = new String[4];
		
		for (int i = 0; i <= amount; i++)
		{
			String pullSelection = perks.get(new Random().nextInt(perks.size()));
			
			tempPerks[i] = pullSelection;
			perks.remove(pullSelection);
		}
		
		if (tempPerks[0] != null)
		{
			finalPerk1 = tempPerks[0];
		}
		if (tempPerks[1] != null)
		{
			finalPerk2 = tempPerks[1];
		}
		if (tempPerks[2] != null)
		{
			finalPerk3 = tempPerks[2];
		}
		if (tempPerks[3] != null)
		{
			finalPerk4 = tempPerks[3];
		}
		
		perkScale(charType, finalPerk1, finalPerk2, finalPerk3, finalPerk4);
		conclusionText();
	}

	/**
	 * Chooses what perks will be chosen between 0 to 4.
	 */
	private static void killerPerks()
	{
		References.statusAgent("method killerPerks() reached");
		
		List<String> perks = new ArrayList<String>();
		perks.add("A Nurse's Calling");perks.add("Agitation");perks.add("Bamboozle");perks.add("Barbecue And Chilli");perks.add("Beast Of Prey");perks.add("Bitter Murmur");perks.add("Blood Echo");
		perks.add("Blood Hound");perks.add("Blood Warden");perks.add("Brutal Strength");perks.add("Corrupt Intervention");perks.add("Coulrophobia");perks.add("Cruel Confinement");perks.add("Dark Devotion");
		perks.add("Deerstalker");perks.add("Discordance");perks.add("Distressing");perks.add("Dying Light");perks.add("Enduring");perks.add("Fire Up");perks.add("Franklin's Demise");perks.add("Furtive Chase");
		perks.add("Hangman's Trick");perks.add("Hex Devour Hope");perks.add("Hex Haunted Ground");perks.add("Hex Huntress Lullaby");perks.add("Hex No One Escapes Death");perks.add("Hex Ruin");
		perks.add("Hex The Third Seal");perks.add("Hex Thrill Of The Hunt");perks.add("I'm All Ears");perks.add("Infectious Fright");perks.add("Insidious");perks.add("Iron Grasp");perks.add("Iron Maiden");
		perks.add("Knock Out");perks.add("Lightborn");perks.add("Mad Grit");perks.add("Make Your Choice");perks.add("Mind Breaker");perks.add("Monitor & Abuse");perks.add("Monstrous Shrine");perks.add("Nemesis");
		perks.add("Overcharge");perks.add("Overwhelming Presence");perks.add("Play With Your Food");perks.add("Pop Goes The Weasel");perks.add("Predator");perks.add("Rancor");perks.add("Remember Me");
		perks.add("Save The Best For Last");perks.add("Shadowborn");perks.add("Sloppy Butcher");perks.add("Spies From The Shadows");perks.add("Spirit Fury");perks.add("Stridor");perks.add("Surge");
		perks.add("Surveillance");perks.add("Territorial Imperative");perks.add("Thanatophobia");perks.add("Thrilling Tremors");perks.add("Tinkerer");perks.add("Unnerving Presence");perks.add("Unrelenting");
		perks.add("Whispers");
		
		//Debugging counter. Counts how many killer perks are recognized in perks list
		References.statusAgent("Length of killerPerks() list: " + perks.size());
		
		int amount = new Random().nextInt(4);
		References.statusAgent("Amount of perks to generate " + (amount + 1));
		perkAmount = amount;
		
		String[] tempPerks = new String[4];
		
		for (int i = 0; i <= amount; i++)
		{
			String pullSelection = perks.get(new Random().nextInt(perks.size()));
			
			tempPerks[i] = pullSelection;
			perks.remove(pullSelection);
		}
		
		if (tempPerks[0] != null)
		{
			finalPerk1 = tempPerks[0];
		}
		if (tempPerks[1] != null)
		{
			finalPerk2 = tempPerks[1];
		}
		if (tempPerks[2] != null)
		{
			finalPerk3 = tempPerks[2];
		}
		if (tempPerks[3] != null)
		{
			finalPerk4 = tempPerks[3];
		}
		
		perkScale(charType, finalPerk1, finalPerk2, finalPerk3, finalPerk4);
		conclusionText();
	}

	/**
	 * @param name name of killer or survivor character
	 */
	private static void charPortraitScale(String name)
	{
		String shadowName = name + " Shadow";
		charPortraitBackground.setIcon(new ImageIcon(((References.createVisuals("/nonDBDArtwork/charPortraitBackgroundOrange.png")).getImage())));//.getScaledInstance(223, 322, java.awt.Image.SCALE_SMOOTH)));
		charPortrait.setIcon(new ImageIcon(((References.createVisuals("/characters/" + name + ".png")).getImage()).getScaledInstance(223, 319, java.awt.Image.SCALE_SMOOTH)));
		charPortraitOverlay.setIcon(new ImageIcon(((References.createVisuals("/nonDBDArtwork/charPortraitOverlay.png")).getImage())));
		charShadow.setIcon(new ImageIcon(((References.createVisuals("/characters/" + shadowName + ".png")).getImage()).getScaledInstance(300, 700, java.awt.Image.SCALE_SMOOTH)));
	}
	
	/**
	 * @param ct charType
	 * @param result finalItemPower
	 * 
	 * Shows the itemPower for characters.
	 */
	private static void itemPowerScale(String ct, String name)
	{
		if (ct == "survivor")
		{
			if (name == "blank")
			{
				itemPower.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
			}
			if (name != "blank")
			{
				itemPower.setIcon(new ImageIcon(((References.createVisuals("/items/" + name + ".png")).getImage()).getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
			}
		}
		if (ct == "killer")
		{
			itemPower.setIcon(new ImageIcon(((References.createVisuals("/powers/" + name + ".png")).getImage()).getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
		}
	}
	
	private static void addonScale(String ct, String name, String addon1In, String addon2In)
	{
		if (addon1In == null)
		{
			addOn1.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		}
		if (addon2In == null)
		{
			addOn2.setIcon(new ImageIcon(((References.createVisuals("/blank.png")).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		}
		if (addon1In != null)
		{
			addOn1.setIcon(new ImageIcon(((References.createVisuals("/addons/" + ct + "/" + name + "/" + addon1In + ".png")).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		}
		if (addon2In != null)
		{
			addOn2.setIcon(new ImageIcon(((References.createVisuals("/addons/" + ct + "/" + name + "/" + addon2In + ".png")).getImage()).getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
		}
	}
	
	/**
	 * @param ct charType for offering folders, survivor, both or killer options
	 * @param offeringIn finalOffering
	 */
	private static void offeringScale(String ct, String offeringIn)
	{
		offering.setIcon(new ImageIcon(((References.createVisuals("/offerings/" + ct + offeringIn + ".png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
	}
	
	/**
	 * @param ct charType
	 * @param p1 finalPerk1
	 * @param p2 finalPerk2
	 * @param p3 finalPerk3
	 * @param p4 finalPerk4
	 * 
	 * Show the perks (0 to 4), if a finalPerk is null it is directed to be Blank for the image.
	 */
	private static void perkScale(String ct, String p1, String p2, String p3, String p4)
	{
		if (p1 == null)
		{
			p1 = "Blank";
		}
		else
		{
			perk1.setIcon(new ImageIcon(((References.createVisuals("/perks/" + ct + "/" + p1 + ".png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		}
		
		if (p2 == null)
		{
			p2 = "Blank";
		}
		else
		{
			perk2.setIcon(new ImageIcon(((References.createVisuals("/perks/" + ct + "/" + p2 + ".png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		}
		
		if (p3 == null)
		{
			p3 = "Blank";
		}
		else
		{
			perk3.setIcon(new ImageIcon(((References.createVisuals("/perks/" + ct + "/" + p3 + ".png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		}
		
		if (p4 == null)
		{
			p4 = "Blank";
		}
		else
		{
			perk4.setIcon(new ImageIcon(((References.createVisuals("/perks/" + ct + "/" + p4 + ".png")).getImage()).getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		}
	}
	
	/*
	 * This will set the summary text box to contain conclusive text about what was chosen. Anything not chosen (ie no item, or not all perk slots chosen)
	 */
	private static void conclusionText()
	{
		
		String absoluteResult = "<html>Results:<br><br>Character:<br>-" + finalChar;
		//if (itemChoice != "blank" || itemChoice != null)
		if (itemChoice != null)
		{
			absoluteResult = absoluteResult + "<br><br>Item:<br>-" + itemChoice;
		}
		if (finalAddOn1 != null)
		{
			absoluteResult = absoluteResult + "<br><br>Addons:<br>-" + finalAddOn1;
		}
		if (finalAddOn2 != null)
		{
			absoluteResult = absoluteResult + "<br>-" + finalAddOn2;
		}
		if (finalOffering != null)
		{
			absoluteResult = absoluteResult + "<br><br>Offering:<br>-" + finalOffering;
		}
		if (finalPerk1 != null)
		{
			absoluteResult = absoluteResult + "<br><br>Perks:<br>-" + finalPerk1;
		}
		if (finalPerk2 != null)
		{
			absoluteResult = absoluteResult + "<br>-" + finalPerk2;
		}
		if (finalPerk3 != null)
		{
			absoluteResult = absoluteResult + "<br>-" + finalPerk3;
		}
		if (finalPerk4 != null)
		{
			absoluteResult = absoluteResult + "<br>-" + finalPerk4;
		}
		
		rightPaneSummary.setText(absoluteResult);
	}
}
