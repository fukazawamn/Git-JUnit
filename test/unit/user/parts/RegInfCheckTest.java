package unit.user.parts;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import user.parts.RegInfCheck;

public class RegInfCheckTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		// staticメソッドにのみ付与可能。テストを実行する前に一度だけ実行したい処理を書く。
		// 環境設定、マスタデータの読み込み等。
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		// staticメソッドにのみ付与可能。テスト実行後の後始末の処理などを書く。
		// テスト実行前データの復元、コネクションクローズなど。
	}

	@Before
	public void setUp() throws Exception
	{
		// 各テストメソッドが実行される前に毎回行いたい処理を書く。
	}

	@After
	public void tearDown() throws Exception
	{
		// 各テストメソッドが実行された後に毎回行いたい処理を書く。
	}
	
	@Test
	public void testCheckName1()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkName("0123456789");
		assertEquals("", util.getErrMsg());
	}
	
	@Test
	public void testCheckName2()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkName("あいうえおかきくけこ");
		assertEquals("", util.getErrMsg());
	}
	
	@Test
	public void testCheckNameErr1()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkName("01234567890");
		assertEquals("名前は10桁以内で入力してください。<br />", util.getErrMsg());
	}
	
	@Test
	public void testCheckNameErr2()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkName("あいうえおかきくけこさ");
		assertEquals("名前は10桁以内で入力してください。<br />", util.getErrMsg());
	}

	@Test
	public void testCheckAge1()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("16");
		assertEquals("", util.getErrMsg());
	}
	
	@Test
	public void testCheckAge2()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("60");
		assertEquals("", util.getErrMsg());
	}
	
	@Test
	public void testCheckAgeErr1()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("15");
		assertEquals("年齢は(16-60)の範囲で入力してください。<br />", util.getErrMsg());
	}
	
	@Test
	public void testCheckAgeErr2()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("61");
		assertEquals("年齢は(16-60)の範囲で入力してください。<br />", util.getErrMsg());
	}
	
	@Test
	public void testCheckAgeErr3()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("１６");
		assertEquals("年齢は数値(半角)で入力してください。<br />", util.getErrMsg());
	}

	@Test
	public void testCheckAgeErr4()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("あいうえお");
		assertEquals("年齢は数値(半角)で入力してください。<br />年齢は(16-60)の範囲で入力してください。<br />", util.getErrMsg());
	}

	@Test
	public void testCheckAgeErr5()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("16あいうえお");
		assertEquals("年齢は数値(半角)で入力してください。<br />年齢は(16-60)の範囲で入力してください。<br />", util.getErrMsg());
	}

	@Test
	public void testCheckId()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkId("999");
		assertEquals("", util.getErrMsg());
	}

	@Test
	public void testCheckIdErr()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkId("1000");
		assertEquals("登録可能なID（999）を超えています。管理者に問い合わせてください。<br />", util.getErrMsg());
	}

}
