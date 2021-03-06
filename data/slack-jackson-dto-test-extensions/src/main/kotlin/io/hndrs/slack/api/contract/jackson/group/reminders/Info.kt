package io.hndrs.slack.api.contract.jackson.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.common.Reminder

fun SuccessfulRemindersInfoResponse.Companion.sample(): SuccessfulRemindersInfoResponse {
    return SuccessfulRemindersInfoResponse(true, Reminder.sample())
}

fun ErrorRemindersInfoResponse.Companion.sample(): ErrorRemindersInfoResponse {
    return ErrorRemindersInfoResponse(true, "")
}

fun RemindersInfoRequest.Companion.sample(): RemindersInfoRequest = RemindersInfoRequest("")
