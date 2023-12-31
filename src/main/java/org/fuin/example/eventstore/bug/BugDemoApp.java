package org.fuin.example.eventstore.bug;

import com.eventstore.dbclient.*;

import java.util.concurrent.ExecutionException;


public class BugDemoApp {

    public static void main(String... args) throws ExecutionException, InterruptedException {

        EventStoreDBProjectionManagementClient client = EventStoreDBProjectionManagementClient
                .create(EventStoreDBConnectionString
                        .parseOrThrow("esdb://localhost:2113?tls=false"));

        String javascript = "fromAll().foreachStream().when({'one': function(state, ev) { linkTo('test-delete', ev); },'two': function(state, ev) { linkTo('test-delete', ev); }})";

        client.create("my-projection", javascript,
                CreateProjectionOptions.get().emitEnabled(false).trackEmittedStreams(true)).get();

    }

}
