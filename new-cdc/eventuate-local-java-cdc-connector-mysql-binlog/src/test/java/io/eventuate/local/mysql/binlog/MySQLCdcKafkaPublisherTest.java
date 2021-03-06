package io.eventuate.local.mysql.binlog;

import io.eventuate.local.common.CdcKafkaPublisher;
import io.eventuate.local.common.PublishedEvent;
import io.eventuate.local.test.util.CdcKafkaPublisherTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MySqlBinlogCdcIntegrationTestConfiguration.class)
@IntegrationTest
public class MySQLCdcKafkaPublisherTest extends CdcKafkaPublisherTest {

  @Autowired
  DatabaseBinlogOffsetKafkaStore binlogOffsetKafkaStore;

  @Override
  protected CdcKafkaPublisher<PublishedEvent> createCdcKafkaPublisher() {
    return new MySQLCdcKafkaPublisher<>(binlogOffsetKafkaStore,
            eventuateKafkaConfigurationProperties.getBootstrapServers(),
            publishingStrategy);
  }
}
