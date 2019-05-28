package io.olaph.slack.dto.jackson.group.usergroups.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.olaph.slack.dto.jackson.JacksonDataClass
import io.olaph.slack.dto.jackson.common.types.Usergroup
import io.olaph.slack.dto.jackson.group.users.User

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "ok", visible = true)
@JsonSubTypes(
        JsonSubTypes.Type(value = SuccessfulUsergroupsUsersListResponse::class, name = "true"),
        JsonSubTypes.Type(value = ErrorUsergroupsUsersListResponse::class, name = "false")
)

@JacksonDataClass
sealed class UsergroupsUsersListResponse constructor(@JsonProperty("ok") open val ok: Boolean)

@JacksonDataClass
data class SuccessfulUsergroupsUsersListResponse constructor(override val ok: Boolean,
                                                             @JsonProperty("users") val users: List<User>)
    : UsergroupsUsersListResponse(ok) {
    companion object
}

@JacksonDataClass
data class ErrorUsergroupsUsersListResponse constructor(override val ok: Boolean,
                                                        @JsonProperty("error") val error: String)
    : UsergroupsUsersListResponse(ok) {
    companion object
}

@JacksonDataClass
data class SlackUsergroupsUsersListRequest(@JsonProperty("usergroup") val usergroupId: String,
                                           @JsonProperty("include_disabled") val includeDisabled: Boolean?) {

    companion object

    fun toRequestMap(): MutableMap<String, String> {

        val request = mutableMapOf<String, String>()
        usergroupId.let { request.put("usergroup", it) }
        includeDisabled?.let { request.put("include_disabled", it.toString()) }

        return request
    }
}