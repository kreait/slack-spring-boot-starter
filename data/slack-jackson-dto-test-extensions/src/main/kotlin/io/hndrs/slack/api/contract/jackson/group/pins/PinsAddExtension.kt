package io.hndrs.slack.api.contract.jackson.group.pins

import io.hndrs.slack.api.contract.jackson.common.InstantSample

fun PinsAddRequest.Companion.sample(): PinsAddRequest {
    return PinsAddRequest("", InstantSample.sample())
}

fun SuccessfulPinsAddResponse.Companion.sample(): SuccessfulPinsAddResponse {
    return SuccessfulPinsAddResponse(true)
}

fun ErrorPinsAddResponse.Companion.sample(): ErrorPinsAddResponse {
    return ErrorPinsAddResponse(true, "")
}
