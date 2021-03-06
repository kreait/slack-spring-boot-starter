package io.hndrs.slack.api.group.chat

import io.hndrs.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import io.hndrs.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import io.hndrs.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import io.hndrs.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Provide custom unfurl behavior for user-posted URLs
 * https://api.slack.com/methods/chat.unfurl
 */
abstract class ChatUnfurlMethod :
    io.hndrs.slack.api.group.ApiCallMethod<io.hndrs.slack.api.group.chat.ChatUnfurlMethod, SuccessfulChatUnfurlResponse, ErrorChatUnfurlResponse, ChatUnfurlRequest>() {

}
