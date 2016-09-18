
import java.util.*;
public class Password
{
	public Password()
	{
	}

	public String generatePassword(int l, int t, int letters, int digits)
	{
		if(t==1)
			return WGen(l);
		if(t==2)
			return NGen(l);
		if(t==3 && letters!=digits)
			return HWNGEN(l, letters, digits);
		else
			return WNGen(l);
	}

	public String WGen(int i)
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

	public String NGen(int i)
	{
		String s = "";
		Random r = new Random();
		for (int a = 0; a < i; a++)
		{
			s+=(r.nextInt(10));
		}
		return s;
	}
	
	public String WNGen(int i)
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
	
	public String HWNGEN(int i, int w, int n)
	{
		int countw=0, countn=0, count=0;
		String s="";
		Random r=new Random();
		while(count<i)
		{
			if(r.nextBoolean())
				if(countw<w)
				{
					if(r.nextInt(2) == 0)
						s+=(char)(r.nextInt(90-65) + 65);
					else
						s+=(char)(r.nextInt(122-97) + 97);
					countw++;
					count++;
				}
			else
				if(countn<n)
				{
					s+=(r.nextInt(10));
					countn++;
					count++;
				}	
		}
		return s;
	}
}