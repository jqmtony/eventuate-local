package io.eventuate.local.cdc.debezium;


import io.eventuate.local.java.jdbckafkastore.EventuateLocalConfiguration;
import io.eventuate.local.testutil.CustomDBCreator;
import io.eventuate.local.testutil.CustomDBTestConfiguration;
import io.eventuate.local.testutil.SqlScriptEditor;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MySqlBinLogBasedEventTableChangesToAggregateTopicRelayCustomEventuateDBTest.EventTableChangesToAggregateTopicRelayTestConfiguration.class)
@DirtiesContext
@IntegrationTest
public class MySqlBinLogBasedEventTableChangesToAggregateTopicRelayCustomEventuateDBTest extends AbstractTopicRelayTest {

  @org.springframework.context.annotation.Configuration
  @Import({CustomDBTestConfiguration.class, EventuateLocalConfiguration.class, EventTableChangesToAggregateTopicRelayConfiguration.class})
  @EnableAutoConfiguration
  public static class EventTableChangesToAggregateTopicRelayTestConfiguration {
  }

  @Autowired
  private CustomDBCreator customDBCreator;

  @Autowired
  private SqlScriptEditor eventuateLocalCustomDBSqlEditor;

  @Before
  public void createCustomDB() {
    customDBCreator.create(eventuateLocalCustomDBSqlEditor);
  }
}
