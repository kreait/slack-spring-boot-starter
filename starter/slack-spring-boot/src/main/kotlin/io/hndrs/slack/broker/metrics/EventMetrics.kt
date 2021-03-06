package io.hndrs.slack.broker.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder

/**
 * Collects metrics for incoming events
 */
interface EventMetricsCollector {

    /**
     * Increments receiver execution metric
     */
    fun receiverExecuted()

    /**
     * Increments receiver execution metric
     */
    fun receiverExecutionError()

    /**
     * Increments events received metric
     */
    fun eventsReceived()
}

/**
 * Increments metrics for events
 */
class EventMetrics : MeterBinder, EventMetricsCollector {

    private lateinit var eventReceiverExecutionErrors: Counter
    private lateinit var eventReceiverExecutions: Counter
    private lateinit var eventsReceivedCounter: Counter

    override fun bindTo(registry: MeterRegistry) {

        eventsReceivedCounter = Counter.builder("slack.events.received")
            .description("Total number of events received")
            .register(registry)

        eventReceiverExecutions = Counter.builder("slack.events.receiver.executions")
            .description("Total number of event receivers executions")
            .register(registry)

        eventReceiverExecutionErrors = Counter.builder("slack.events.receiver.errors")
            .description("Number of errors during event receiver execution")
            .register(registry)
    }

    override fun receiverExecuted() = this.eventReceiverExecutions.increment()

    override fun receiverExecutionError() = this.eventReceiverExecutionErrors.increment()

    override fun eventsReceived() = this.eventsReceivedCounter.increment()
}
