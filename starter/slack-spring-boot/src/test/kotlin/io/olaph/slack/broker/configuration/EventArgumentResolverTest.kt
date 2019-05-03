package io.olaph.slack.broker.configuration

import io.olaph.slack.broker.RequestTestUtils
import io.olaph.slack.broker.RequestTestUtils.jsonBody
import io.olaph.slack.broker.RequestTestUtils.mockMethodParameter
import io.olaph.slack.broker.RequestTestUtils.mockNativeWebRequest
import io.olaph.slack.dto.jackson.EventRequest
import io.olaph.slack.dto.jackson.SlackEvent
import io.olaph.slack.dto.jackson.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.Instant

internal class EventArgumentResolverTest {

    @Test
    fun supportsParameter() {
        assertTrue(EventArgumentResolver("")
                .supportsParameter(mockMethodParameter(EventRequest::class.java, Event::class.java)))

        assertFalse(EventArgumentResolver("")
                .supportsParameter(mockMethodParameter(EventRequest::class.java, RequestTestUtils.TestAnnotation::class.java)))

        assertFalse(EventArgumentResolver("")
                .supportsParameter(mockMethodParameter(Any::class.java, Event::class.java)))
    }

    @Test
    fun internalResolveArgument() {

        //setup
        val slackEvent = SlackEvent.sample()
        val signingSecret = "mySecret"
        val timestamp = Instant.now()

        val mockNativeWebRequest = mockNativeWebRequest(timestamp, signingSecret, jsonBody(slackEvent))

        //test
        val resolvedArgument = EventArgumentResolver(signingSecret)
                .resolveArgument(mockMethodParameter(EventRequest::class.java, Event::class.java), null, mockNativeWebRequest, null)

        Assertions.assertEquals(slackEvent, resolvedArgument)
    }
}
