package application;


public class Game 
{
	private static int[] spielfeld = new int[16];
	private static int points;
	
		
	public static void newGame()
	{
		do
		{
			spielfeld = new int[16];
			for(int i = 0; i < 16; i++)
			{
				spielfeld[i] = 0;
			}
			
			//zweite Zeile von unten
			for(int j = 8; j <= 11; j++)
			{
				Double d = new Double(1 + Math.random()*(8));
				int zelle = d.intValue();	
				if(zelle == 2)
				{
					spielfeld[j] = 2;
				}
				else if (zelle == 4)
				{
					spielfeld[j] = 4;
				}			
			}
			
			//untere Zeile
			for(int k = 12; k <= 15; k++)
			{
				Double d = new Double(1 + Math.random()*(4));
				int zelle = d.intValue();	
				if(zelle == 2)
				{
					spielfeld[k] = 2;
				}
				else if (zelle == 4)
				{
					spielfeld[k] = 4;
				}
			}			
			points = 0;
		}
		while(checkIfEmpty());		
	}
	
	
	public static int[] checkMovement(int zelle1, int zelle2, int zelle3, int zelle4)
	{
			//alle nach unten bewegen
				boolean eins = false;
				boolean zwei = false;
				boolean drei = false;
				boolean vier = false;
				
				if(!(spielfeld[zelle1] == 0))
				{
					eins = true;
				}
				
				if(!(spielfeld[zelle2] == 0))
				{
					zwei = true;
				}
				
				if(!(spielfeld[zelle3] == 0))
				{
					drei = true;
				}
				
				if(!(spielfeld[zelle4] == 0))
				{
					vier = true;
				}
				
				int[] array = new int[4];
				array[0] = spielfeld[zelle1];
				array[1] = spielfeld[zelle2];
				array[2] = spielfeld[zelle3];
				array[3] = spielfeld[zelle4];
				
				return move(array, eins, zwei, drei, vier);				
	}
	
	public static void nachOben()
	{
		int[] neu;
		
		for(int i = 12; i <= 15; i++)
		{
			neu = checkMovement(i, i-4, i-8, i-12);	
				
			checkCombination(neu, i, i-4, i-8, i-12);			
		}						
		newNumber(12,13,14,15);		
	}
	
	public static void nachUnten()
	{
		int[] neu;
		
		for(int i = 0; i <= 3; i++)
		{
			neu = checkMovement(i, i+4, i+8, i+12);	
				
			checkCombination(neu, i, i+4, i+8, i+12);			
		}		
		newNumber(0,1,2,3);
	}
	
	public static void nachLinks()
	{
		int[] neu;
		
		for(int i = 3; i <= 15; i = i + 4)
		{			
			neu = checkMovement(i, i-1, i-2, i-3);	
				
			checkCombination(neu, i, i-1, i-2, i-3);			
		}		
		newNumber(3,7,11,15);
	}
	
	public static void nachRechts()
	{
		int[] neu;
		
		for(int i = 0; i <= 12; i = i +4)
		{
			neu = checkMovement(i, i+1, i+2, i+3);	
				
			checkCombination(neu, i, i+1, i+2, i+3);			
		}		
		newNumber(0,4,8,12);
	}
	
	public static int[] move(int[] array, boolean eins, boolean zwei, boolean drei, boolean vier)
	{
		if(eins && !zwei && !drei && !vier)
		{
			//move
			array[3] = array[0];
			//clear old
			array[0] = 0;
			return array;
		}
		
		if(eins && zwei && !drei && !vier)
		{
			//move
			array[3] = array[1];
			array[2] = array[0];		
			//clear old
			array[0] = 0;	
			array[1] = 0;
			return array;
		}
		
		if(eins && zwei && drei && !vier)
		{
			//move
			array[3] = array[2];
			array[2] = array[1];
			array[1] = array[0];			
			//clear old
			array[0] = 0;
			return array;
		}
		
		if(!eins && zwei && !drei && !vier)
		{
			//move
			array[3] = array[1];						
			//clear old
			array[1] = 0;
			return array;
		}
		
		if(!eins && zwei && drei && !vier)
		{
			//move
			array[3] = array[2];
			array[2] = array[1];
			//clear old
			array[1] = 0;
			return array;
		}
		
		if(!eins && !zwei && drei && !vier)
		{
			//move
			array[3] = array[2];			
			//clear  old
			array[2] = 0;
			return array;
		}
		
		if(eins && !zwei && drei && !vier)
		{
			//move
			array[3] = array[2];
			array[2] = array[0];
			//clear  old
			array[0] = 0;
			return array;
		}
		
		if(eins && !zwei && drei && vier)
		{
			//move			
			array[1] = array[0];
			//clear  old
			array[0] = 0;
			return array;
		}
		
		if(eins && zwei && !drei && vier)
		{
			//move			
			array[2] = array[1];
			array[1] = array[0];
			//clear  old
			array[0] = 0;
			return array;
		}
		
		if(!eins && zwei && !drei && vier)
		{
			//move			
			array[2] = array[1];
			//clear  old
			array[1] = 0;
			return array;
		}
		
		if(!eins && zwei && drei && !vier)
		{
			//move
			array[3] = array[2];
			array[2] = array[1];
			//clear  old
			array[1] = 0;
			return array;
		}
		
		if(!eins && !zwei && drei && !vier)
		{
			//move
			array[3] = array[2];			
			//clear  old
			array[2] = 0;
			return array;
		}
		
		if(eins && !zwei && !drei && vier)
		{
			//move			
			array[2] = array[0];
			//clear  old
			array[0] = 0;
			return array;
		}
		
		return array;
	}
	
	public static void checkCombination(int[] array, int zelle1, int zelle2, int zelle3, int zelle4)
	{
		boolean combined;
		
		do
		{
			combined = false;
			
			if(array[2] != 0 && array[3] !=0 && array[2] == array[3])
			{
				array[3] = array[3] * 2;
				array[2] = 0;
				//Punkte erhöhen
				setPoints(array[3]);
				
				combined = true;
			}	
			
			if(array[1] != 0 && array[2] !=0 && array[1] == array[2])
			{
				array[2] = array[2] * 2;
				array[1] = 0;
				//Punkte erhöhen
				setPoints(array[2]);
				
				combined = true;
			}
			
			
			if(array[0] != 0 && array[1] !=0 && array[0] == array[1])
			{
				array[1] = array[1] * 2;
				array[0] = 0;
				//Punkte erhöhen
				setPoints(array[1]);
			}		
			
			spielfeld[zelle1] = array[0];
			spielfeld[zelle2] = array[1];
			spielfeld[zelle3] = array[2];
			spielfeld[zelle4] = array[3];
			
			array = checkMovement(zelle1, zelle2, zelle3, zelle4);
				
		}
		while(combined);
	}
	
	public static boolean combinationLeft(int[] array, int zelle1, int zelle2, int zelle3, int zelle4)
	{
		boolean combined = false;
			
			
			if(array[0] != 0 && array[1] !=0 && array[0] == array[1])
			{
				combined = true;				
			}
			
			if(array[1] != 0 && array[2] !=0 && array[1] == array[2])
			{
				combined = true;
			}
			
			if(array[2] != 0 && array[3] !=0 && array[2] == array[3])
			{
				combined = true;
			}		
		return combined;
	}
	
	public static void printArray(int[] array)
	{
		System.out.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);
		System.out.println(array[4] + " " + array[5] + " " + array[6] + " " + array[7]);
		System.out.println(array[8] + " " + array[9] + " " + array[10] + " " + array[11]);
		System.out.println(array[12] + " " + array[13] + " " + array[14] + " " + array[15]);
	}
	
	public static boolean checkWinning()
	{
		for(int i = 0; i < 16; i++)
		{
			if(spielfeld[i] == 2048)
			{
				return true;
			}			
		}
		return false;
	}
	
	public static boolean checkLosing()
	{
		int counter = 0;
		
		for(int i = 0; i < 16; i++)
		{
			if(!(spielfeld[i] == 0))
			{
				counter++;
			}			
		}
		
		if(counter == 16)
		{
			
			int combos = 0;
			
			//nach unten
			int[] neu = new int[4];
			neu[0] = spielfeld[0];
			neu[1] = spielfeld[1];
			neu[2] = spielfeld[2];
			neu[3] = spielfeld[3];			
			
			for(int i = 0; i <= 3; i++)
			{	
				if(combinationLeft(neu, i, i-4, i-8, i-12))
				{
					combos++;
				}
			}	
			
			//nach oben
			
			neu = new int[4];
			neu[0] = spielfeld[12];
			neu[1] = spielfeld[13];
			neu[2] = spielfeld[14];
			neu[3] = spielfeld[15];			
			
			for(int i = 12; i <= 15; i++)
			{	
				if(combinationLeft(neu, i, i-4, i-8, i-12))
				{
					combos++;
				}
			}
			
			//nach rechts
			
			neu = new int[4];
			neu[0] = spielfeld[0];
			neu[1] = spielfeld[4];
			neu[2] = spielfeld[8];
			neu[3] = spielfeld[12];			
			
			for(int i = 0; i <= 12; i = i +4)
			{	
				if(combinationLeft(neu, i, i-4, i-8, i-12))
				{
					combos++;
				}
			}	
			
			//nach links
			
			neu = new int[4];
			neu[0] = spielfeld[3];
			neu[1] = spielfeld[7];
			neu[2] = spielfeld[11];
			neu[3] = spielfeld[15];			
			
			for(int i = 3; i <= 15; i = i + 4)
			{	
				if(combinationLeft(neu, i, i-4, i-8, i-12))
				{
					combos++;
				}
			}	
			
			if(combos == 0)
			{
				return true;
			}
			else
			{
				return false;
			}			
		}
		else
		{
			return false;
		}
	}
	
	
	public static void newNumber(int zelle1, int zelle2, int zelle3, int zelle4)
	{
		boolean eins = false;
		boolean zwei = false;
		boolean drei = false;
		boolean vier = false;
		
		//erkenne leere Zeilenanfänge
		if(spielfeld[zelle1] == 0)
		{
			eins = true;
		}
		if(spielfeld[zelle2] == 0)
		{
			zwei = true;
		}
		if(spielfeld[zelle3] == 0)
		{
			drei = true;
		}
		if(spielfeld[zelle4] == 0)
		{
			vier = true;
		}
		
		//wähle 2 oder 4 aus
		Double k = new Double(1 + Math.random()*(2));		
		int zahl = k.intValue();		
		if(zahl == 1)
		{
			zahl = 2;
		}
		else if(zahl == 2)
		{
			zahl = 4;
		}
		
		//wähle zufällig eine Zeile davon aus
		
		if(eins && zwei && drei && vier)
		{
			Double d = new Double(1 + Math.random()*(4));
			int zeile = d.intValue();	
			switch(zeile)
			{
				case 1: spielfeld[zelle1] = zahl;
						break;
				case 2: spielfeld[zelle2] = zahl;
						break;
				case 3: spielfeld[zelle3] = zahl;
						break;
				case 4: spielfeld[zelle4] = zahl;
						break;
			}
		}
		
		if(eins && !zwei && !drei && !vier)
		{
			spielfeld[zelle1] = zahl;
		}
		
		if(eins && zwei && !drei && !vier)
		{
			Double d = new Double(1 + Math.random()*(2));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle1] = zahl;
						break;
				case 2: spielfeld[zelle2] = zahl;
						break;
			}
		}
		
		if(eins && zwei && drei && !vier)
		{
			Double d = new Double(1 + Math.random()*(3));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle1] = zahl;
						break;
				case 2: spielfeld[zelle2] = zahl;
						break;
				case 3: spielfeld[zelle3] = zahl;
						break;
			}
		}
		
		if(!eins && zwei && !drei && !vier)
		{
			spielfeld[zelle2] = zahl;
		}
		
		if(!eins && zwei && drei && !vier)
		{
			Double d = new Double(1 + Math.random()*(2));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle2] = zahl;
						break;
				case 2: spielfeld[zelle3] = zahl;
						break;
			}
		}
		
		if(!eins && !zwei && drei && !vier)
		{
			spielfeld[zelle3] = zahl;
		}
		
		if(eins && !zwei && drei && !vier)
		{
			Double d = new Double(1 + Math.random()*(2));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle1] = zahl;
						break;
				case 2: spielfeld[zelle3] = zahl;
						break;
			}
		}
		
		if(eins && !zwei && drei && vier)
		{
			Double d = new Double(1 + Math.random()*(3));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle1] = zahl;
						break;
				case 2: spielfeld[zelle3] = zahl;
						break;
				case 3: spielfeld[zelle4] = zahl;
						break;
			}
		}
		
		if(eins && zwei && !drei && vier)
		{
			Double d = new Double(1 + Math.random()*(3));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle1] = zahl;
						break;
				case 2: spielfeld[zelle2] = zahl;
						break;
				case 3: spielfeld[zelle4] = zahl;
						break;
			}
		}
		
		if(!eins && zwei && !drei && vier)
		{
			Double d = new Double(1 + Math.random()*(2));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle2] = zahl;
						break;
				case 2: spielfeld[zelle4] = zahl;
						break;				
			}
		}
		
		if(!eins && zwei && drei && !vier)
		{
			Double d = new Double(1 + Math.random()*(2));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle2] = zahl;
						break;
				case 2: spielfeld[zelle3] = zahl;
						break;
			}
		}		
		
		if(eins && !zwei && !drei && vier)
		{
			Double d = new Double(1 + Math.random()*(2));
			int zeile = d.intValue();
			
			switch(zeile)
			{
				case 1: spielfeld[zelle1] = zahl;
						break;
				case 2: spielfeld[zelle4] = zahl;
						break;			
			}
		}		
	}
	
	public static void setPoints(int neu)
	{
		points = points + neu;
	}
	
	public static int getPoints()
	{
		return points;
	}
	
	public static int[] getSpielfeld()
	{
		return spielfeld;
	}
	
	public static boolean checkIfEmpty()
	{
		int counter = 0;
		
		for(int i = 0; i < 16; i++)
		{
			if(spielfeld[i] == 0)
			{
				counter++;
			}
		}
		
		if(counter == 16)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void cheat()
	{
		spielfeld[15] = 2048;
		points = 999999;
	}
}