package unit.employ;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import employ.OutLog;

public class OutLogTest
{
	OutLog util = null;
	File newfile = null;
	static int num;
	String str = null;
	String regex = null;
	Pattern p = null;
	Matcher m = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		num = 0;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception{
	}

	@Before
	public void setUp() throws Exception
	{
		// C:/test/log/log.txtを空ファイルとして作成する。
		newfile = new File("C:/test/log/log.txt");
		newfile.createNewFile();
		num++;
	}

	@After
	public void tearDown() throws Exception 
	{
		// C:/test/log/log.txtをリネームする
		File refile = new File("C:/test/log/log_" + num + ".txt");
		this.newfile.renameTo(refile);	
	}

	@Test
	public void testOutLogDmpString() throws FileNotFoundException
	{
		OutLog.outLogDmp("sample：サンプル");
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(newfile));
			str = br.readLine();
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		regex = "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}:(sample：サンプル)";
		p = Pattern.compile(regex);
		m = p.matcher(str);

		assertTrue(m.matches());
	}

	@Test
	public void testOutLogDmpInteger()
	{
		OutLog.outLogDmp(12345);
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(newfile));
			str = br.readLine();
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		regex = "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}:(12345)";
		p = Pattern.compile(regex);
		m = p.matcher(str);

		assertTrue(m.matches());
	}

}
