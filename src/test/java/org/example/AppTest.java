package org.example;

import static org.junit.Assert.assertTrue;

import com.bean.Page;
import com.bean.SC;
import com.bean.Student;
import com.controller.StudentServlet;
import com.dao.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }


    @Test
    public void test02() throws SQLException {
        int i = CourseDao.querycstock(3001);
        System.out.println(i);
    }

    @Test
    public void test03() throws SQLException {
        List list = SCDao.queryCount(1);
        double jige = 0,youxiu = 0,count = 0;
        for (Object o : list) {
            count++;
            if (((SC)o).getScore() >=60 && ((SC)o).getScore() <80){jige++;}
            else if (((SC)o).getScore() >= 80){
                jige++;
                youxiu++;
            }
        }
        System.out.println(String.format("%.2f", jige/count*100)+"%");
        System.out.println(String.format("%.2f", youxiu/count*100)+"%");
    }

    @Test
    public void test04() throws SQLException {
        List list = SCDao.queryScoreBySId(1);
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println(list);
    }

}
