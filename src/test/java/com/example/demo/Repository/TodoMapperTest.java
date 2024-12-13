package com.example.demo.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Common.Todo;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.internal.DescribableComparator;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.dbunit.dataset.AbstractDataSet;
import org.dbunit.dataset.DataSetUtils;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.dbunit.assertion.DbUnitAssert;
@ExtendWith(MockitoExtension.class)
class TodoMapperTest {
	static IDatabaseTester databaseTester;
	static IDatabaseConnection connection;
		@Mock
	TodoMapper todoMap;
	Todo todo = new Todo();
	  private XmlDataSet readXmlDataSet(String path) throws Exception  {
	        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
	            return new XmlDataSet(inputStream);
	        }
	    }
@BeforeAll
static void beforeAll() throws Exception {
	databaseTester=new JdbcDatabaseTester("org.postgresql.Driver","jdbc:postgresql://127.0.0.1/app","postgres","postgres");
	connection =databaseTester.getConnection();
}
@BeforeEach
void BeforeEach() throws Exception{
	
	IDataSet initialDataSet =readXmlDataSet("/xml/TestCase.xml");
databaseTester.setDataSet(initialDataSet);//xmlのセット
databaseTester.onSetup();
}

	@Test
	@DisplayName("Todo一覧の確認")
	void test() {
		todo.setId(0);
		List<Todo> todoList = new ArrayList<Todo>();
		todoList.add(todo);
		when(todoMap.findAll()).thenReturn(todoList);
		assertEquals(0, todoMap.findAll().get(0).getId());
		verify(todoMap, times(1)).findAll();//値の検査
	}
	
	@Test
	@DisplayName("Todo個別の確認")
	void testSelect() {
		todo.setId(1);
		doReturn(todo).when(todoMap).findOne(1);
		Integer result=todoMap.findOne(1).getId();
		assertEquals(1,result);
	}
	
	@Test
	@DisplayName("insert登録の確認")
	void testInsert() throws Exception {
		IDataSet ExpectedData =readXmlDataSet("/xml/TestExpected.xml");
		IDataSet actual=connection.createDataSet();
		System.out.println(databaseTester);
//		DataSetUtils.assertEquals(ExpectedData, actual);
		DbUnitAssert dbUnitAssert = new DbUnitAssert();
		dbUnitAssert.assertEquals(ExpectedData, actual);
	}

    @AfterAll
    static void afterAll() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
