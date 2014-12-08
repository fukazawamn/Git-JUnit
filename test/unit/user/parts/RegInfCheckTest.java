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
		// static���\�b�h�ɂ̂ݕt�^�\�B�e�X�g�����s����O�Ɉ�x�������s�����������������B
		// ���ݒ�A�}�X�^�f�[�^�̓ǂݍ��ݓ��B
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		// static���\�b�h�ɂ̂ݕt�^�\�B�e�X�g���s��̌�n���̏����Ȃǂ������B
		// �e�X�g���s�O�f�[�^�̕����A�R�l�N�V�����N���[�Y�ȂǁB
	}

	@Before
	public void setUp() throws Exception
	{
		// �e�e�X�g���\�b�h�����s�����O�ɖ���s�����������������B
	}

	@After
	public void tearDown() throws Exception
	{
		// �e�e�X�g���\�b�h�����s���ꂽ��ɖ���s�����������������B
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
		util.checkName("��������������������");
		assertEquals("", util.getErrMsg());
	}
	
	@Test
	public void testCheckNameErr1()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkName("01234567890");
		assertEquals("���O��10���ȓ��œ��͂��Ă��������B<br />", util.getErrMsg());
	}
	
	@Test
	public void testCheckNameErr2()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkName("����������������������");
		assertEquals("���O��10���ȓ��œ��͂��Ă��������B<br />", util.getErrMsg());
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
		assertEquals("�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />", util.getErrMsg());
	}
	
	@Test
	public void testCheckAgeErr2()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("61");
		assertEquals("�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />", util.getErrMsg());
	}
	
	@Test
	public void testCheckAgeErr3()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("�P�U");
		assertEquals("�N��͐��l(���p)�œ��͂��Ă��������B<br />", util.getErrMsg());
	}

	@Test
	public void testCheckAgeErr4()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("����������");
		assertEquals("�N��͐��l(���p)�œ��͂��Ă��������B<br />�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />", util.getErrMsg());
	}

	@Test
	public void testCheckAgeErr5()
	{
		RegInfCheck util = new RegInfCheck();
		util.checkAge("16����������");
		assertEquals("�N��͐��l(���p)�œ��͂��Ă��������B<br />�N���(16-60)�͈̔͂œ��͂��Ă��������B<br />", util.getErrMsg());
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
		assertEquals("�o�^�\��ID�i999�j�𒴂��Ă��܂��B�Ǘ��҂ɖ₢���킹�Ă��������B<br />", util.getErrMsg());
	}

}
