package club.odty.odty;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OdtyApplicationTests {

	@Test
	public void contextLoads() {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern());
		System.out.println(str1.intern() == str1);

		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern());
		System.out.println(str2.intern() == str2);
	}

}
