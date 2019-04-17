package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInviteRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationsInviteFailureTest() {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("conversations.invite Failure")
    fun conversationInvite() {
        val response = ErrorConversationInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.invite")

        DefaultConversationsInviteMethod("", mockTemplate)
                .with(ConversationsInviteRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("conversations.invite Success")
    fun conversationInviteSuccess() {
        val response = SuccessfulConversationInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.invite")

        DefaultConversationsInviteMethod("", mockTemplate)
                .with(ConversationsInviteRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}
