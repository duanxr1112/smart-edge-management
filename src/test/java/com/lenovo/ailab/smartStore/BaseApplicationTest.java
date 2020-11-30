package com.lenovo.ailab.smartStore;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lenovo.ailab.smartedge.Application;

/**
 * Title: BaseApplicationTest.java
 * @Autohr "chenpeng"
 * @data 2019年4月17日
 * @Email chenpeng10@lenovo.com
 * @description: 
**/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
//@Transactional
//@Rollback
public abstract class BaseApplicationTest {
	
	@Autowired
    private WebApplicationContext context;

	
    protected MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    
    
    

}
