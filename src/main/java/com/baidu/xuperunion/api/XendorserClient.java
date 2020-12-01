package com.baidu.xuperunion.api;

import com.baidu.xuperunion.pb.XendorserGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class XendorserClient {
    private final ManagedChannel channel;
    private final XendorserGrpc.xendorserBlockingStub blockingClient;

    public XendorserClient(String target) {
        this(ManagedChannelBuilder.forTarget(target)
                .usePlaintext()
                .build());
    }

    private XendorserClient(ManagedChannel channel) {
        this.channel = channel;
        blockingClient = XendorserGrpc.newBlockingStub(channel);
    }

    public void close() {
        channel.shutdown();
    }

    XendorserGrpc.xendorserBlockingStub getBlockingClient() {
        return blockingClient;
    }
}
