package com.baidu.xuper.api;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Data;

import com.baidu.xuper.pb.XendorserGrpc;

@Data
public class XendorserClient {
    private final ManagedChannel channel;
    private final XendorserGrpc.XendorserBlockingStub blockingClient;

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
}
