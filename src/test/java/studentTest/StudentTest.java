package studentTest;

import java.util.Set;

import org.junit.Test;

import com.lession4.entity.Student;
import com.lession4.utils.MyJedis;
import com.lession4.utils.MyUUID;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class StudentTest {
	@Test
	public void test()throws Exception{
		Jedis jedis = MyJedis.getJedis();
		Student student = new Student();
		student.setAvgscore(59);
		student.setBirthday("2018-7-19");
		student.setDescription("dd");
		student.setId(MyUUID.getUUID());
		student.setName("π¢¡… ");
		JSONObject fromObject = JSONObject.fromObject(student);
		System.out.println(fromObject.toString());
		jedis.hset("STUDENT_INFO", student.getId(), fromObject.toString());
		jedis.zadd("SCORE_INFO", 59, student.getId());
		jedis.close();
	}
	
	
}
