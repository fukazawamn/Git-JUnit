package unit.user.parts;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import user.bean.RegistrantInfo;
import user.parts.RegInfDAO;

public class RegInfDAOTest
{
	static RegInfDAO util = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		InitialContext ic = new InitialContext();
		ic.createSubcontext("java:");
		ic.createSubcontext("java:comp");
		ic.createSubcontext("java:comp/env");
		ic.createSubcontext("java:comp/env/jdbc");
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUser("root");
		ds.setPassword("root");
		ds.setURL("jdbc:mysql://localhost/Task");
		ic.bind("java:comp/env/jdbc/Task", ds); 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		util = new RegInfDAO();
		util.close();
	}

	@Before
	public void setUp() throws Exception
	{
		RegInfDAO util = new RegInfDAO();
		util.insert("001", "��ؑ��Y", "35");
		util.insert("002", "Tommy", "25");
		util.insert("003", "�R�c�Ԏq", "30");
		util.getReglist();
	}

	@After
	public void tearDown() throws Exception
	{
		util = new RegInfDAO();
		ArrayList<RegistrantInfo> list = util.getReglist();
		Iterator<RegistrantInfo> iterator = list.iterator();
		while(iterator.hasNext())
		{
			RegistrantInfo rInfo = iterator.next();
			util.delete(rInfo.getrId());
		}
	}

	@Test
	public void testInsert()
	{
		util = new RegInfDAO();
		util.insert("004", "�����H����", "28");

		// ���҂����o�́i���ʁj��String�z��Ɋi�[���Ă���
		String[] id = new String[]{"001", "002", "003", "004"};
		String[] name = new String[]{"��ؑ��Y", "Tommy", "�R�c�Ԏq", "�����H����"};
		String[] age = new String[]{"35", "25", "30", "28"};
		
		// �e�X�g�v���O�����Ŏ��s�����l�Ɗ��҂����o�͂����������`�F�b�N���鋤�ʊ֐����Ăяo��
		assertEqualsMethod(id, name, age);
	}

	@Test
	public void testUpdate()
	{
		util = new RegInfDAO();
		util.update("002", "Michael", "29");
		
		// ���҂����o�́i���ʁj��String�z��Ɋi�[���Ă���
		String[] id = new String[]{"001", "002", "003"};
		String[] name = new String[]{"��ؑ��Y", "Michael", "�R�c�Ԏq"};
		String[] age = new String[]{"35", "29", "30"};

		// �e�X�g�v���O�����Ŏ��s�����l�Ɗ��҂����o�͂����������`�F�b�N���鋤�ʊ֐����Ăяo��
		assertEqualsMethod(id, name, age);
	}

	@Test
	public void testDelete()
	{
		util = new RegInfDAO();
		util.delete("001");

		// ���҂����o�́i���ʁj��String�z��Ɋi�[���Ă���
		String[] id = new String[]{"002", "003"};
		String[] name = new String[]{"Tommy", "�R�c�Ԏq"};
		String[] age = new String[]{"25", "30"};

		// �e�X�g�v���O�����Ŏ��s�����l�Ɗ��҂����o�͂����������`�F�b�N���鋤�ʊ֐����Ăяo��
		assertEqualsMethod(id, name, age);
	}

	@Test
	public void testGetReglist()
	{
		util = new RegInfDAO();
		
		// ���҂����o�́i���ʁj��String�z��Ɋi�[���Ă���
		String[] id = new String[]{"001", "002", "003"};
		String[] name = new String[]{"��ؑ��Y", "Tommy", "�R�c�Ԏq"};
		String[] age = new String[]{"35", "25", "30"};
		
		// �e�X�g�v���O�����Ŏ��s�����l�Ɗ��҂����o�͂����������`�F�b�N���鋤�ʊ֐����Ăяo��
		assertEqualsMethod(id, name, age);
	}

	@Test
	public void testGetNextId()
	{
		util = new RegInfDAO();
		
		ArrayList<RegistrantInfo> list = util.getReglist();
		Iterator<RegistrantInfo> iterator = list.iterator();
		while(iterator.hasNext())
		{
			RegistrantInfo rInfo = iterator.next();
			util.delete(rInfo.getrId());
		}

		String rtn = util.getNextId();
		assertEquals(rtn, "001");
	}

	public void assertEqualsMethod(String[] id, String[] name, String[] age)
	{
		ArrayList<RegistrantInfo> list = util.getReglist();
		Iterator<RegistrantInfo> iterator = list.iterator();

		int i = 0;
		while(iterator.hasNext())
		{
			// �e�X�g�v���O�����Ŏ��s�����l�Ɗ��҂����o�͂����������`�F�b�N����
			RegistrantInfo rInfo = iterator.next();
			assertEquals(rInfo.getrId(), id[i]);
			assertEquals(rInfo.getrName(), name[i]);
			assertEquals(rInfo.getrAge(), age[i]);
			i++;
		}
	}
	
}
