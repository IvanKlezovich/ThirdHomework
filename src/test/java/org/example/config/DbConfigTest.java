package org.example.config;


import org.example.dao.SingerDao;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DbConfigTest {
    private static Logger logger =
            LoggerFactory.getLogger(DbConfigTest.class);

    @Test
    public void test() {
        GenericApplicationContext context =
                new AnnotationConfigApplicationContext(
                        DataBaseConfig.class);

        DataSource dataSource =
                context.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);

        context.close();
    }

    @Test
    public void testDataSource() {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        var singerDao = context.getBean("singerDao", SingerDao.class);
        assertNotNull(singerDao);
        //assertEquals("Singer(id=1, firstname=John, version=0, lastname=Mayer, birthDate=1977-10-16, albums=null, instruments=null)", singerDao.update(1L));
    }



    private void testDataSource(DataSource dataSource) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement
                    = connection.prepareStatement("select * from singer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(1)+
                        " "+resultSet.getString(2)+
                        " "+resultSet.getString(3)+
                        " "+resultSet.getString(4)+
                        " "+resultSet.getString(5));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
