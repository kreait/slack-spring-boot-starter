package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsOpenRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsOpenTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.open Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsOpenResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.open", response)
        val verifier = Verifier(response)

        DefaultGroupsOpenMethod("", mockTemplate)
                .with(GroupsOpenRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.open Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsOpenResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.open", response)
        val verifier = Verifier(response)

        DefaultGroupsOpenMethod("", mockTemplate)
                .with(GroupsOpenRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}