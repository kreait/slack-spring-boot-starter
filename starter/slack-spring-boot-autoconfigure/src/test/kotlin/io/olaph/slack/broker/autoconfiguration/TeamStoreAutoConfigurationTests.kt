package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.store.InMemoryTeamStore
import io.olaph.slack.broker.store.Team
import io.olaph.slack.broker.store.TeamStore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class TeamStoreAutoConfigurationTests {

    @DisplayName("Custom TeamStore Registration")
    @Test
    fun teamCustomStoreRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .withUserConfiguration(TestConfiguration::class.java)
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(TeamStore::class.java) }
                    Assertions.assertTrue(it.getBean(TeamStore::class.java) is TestTeamStore)
                }
    }

    @DisplayName("InMemoryTeamStore Registration")
    @Test
    fun teamStoreRegistration() {
        TestApplicationContext.base()
                .withConfiguration(AutoConfigurations.of(SlackBrokerAutoConfiguration::class.java, WebMvcAutoConfiguration::class.java))
                .run {
                    Assertions.assertDoesNotThrow { it.getBean(TeamStore::class.java) }
                    Assertions.assertTrue(it.getBean(TeamStore::class.java) is InMemoryTeamStore)
                }
    }


    @Configuration
    open class TestConfiguration {

        @Bean
        open fun testTeamStore(): TeamStore {
            return TestTeamStore()
        }
    }

    class TestTeamStore : TeamStore {

        override fun findById(id: String): Team = throw NotImplementedError()

        override fun put(team: Team) {}

        override fun removeById(id: String) {}
    }
}