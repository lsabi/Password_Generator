import java.util.*;

public class Main 
{
	public static void main(String[] args)
	{
		System.out.println("Welcome in the password generator program\n");
		System.out.println("Digit 'w' for a word password, 'n' for a number password, 'wn' for a union otherwise to quit");
		
		Scanner scan = new Scanner(System.in);
		String temp = scan.nextLine();		
		
		System.out.println("Insert the length of the password");
		
		int length = scan.nextInt();
		
		scan.close();
		try
		{
			if(temp.charAt(0) == 'w' && temp.charAt(1) == 'n')
			{
				System.out.println(WNGen(length));
				return ;
			}
		}
		catch(Exception ex)
		{
			
		}
		
		if(temp.charAt(0) == 'w')
		{
			System.out.println(WGen(length));
			return ;
		}
		if(temp.charAt(0) == 'n')
		{
			System.out.println(NGen(length));
			return ;
		}		
	}
	
	public static String WGen(int i)
	{
		String s = "";
		Random r = new Random();
		for (int a = 0;a < i; a++)
		{
			if(r.nextInt(2) == 0)
				s+=(char)(r.nextInt(90-65) + 65);
			else
				s+=(char)(r.nextInt(122-97) + 97);
		}
		return s;
	}

	public static String NGen(int i)
	{
		String s = "";
		Random r = new Random();
		for (int a = 0; a < i; a++)
		{
			s+=(r.nextInt(10));
		}
		return s;
	}
	
	public static String WNGen(int i)
	{
		String s = "";
		Random r = new Random();
		for (int a = 0; a < i; a++)
		{
			if (r.nextInt(4) == 0)
				if(r.nextInt(4) == 0)
					s+=(char)(r.nextInt(90-65) + 65);
				else
					s+=(char)(r.nextInt(122-97) + 97);
			else
				s+=(r.nextInt(10));		
		}
		return s;
	}
}