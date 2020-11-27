package com.baidu.xuperunion.pb;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: xendorser.proto")
public final class XendorserGrpc {

  private XendorserGrpc() {}

  public static final String SERVICE_NAME = "pb.xendorser";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<XendorserOuterClass.EndorserRequest,
      XendorserOuterClass.EndorserResponse> getEndorserCallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EndorserCall",
      requestType = XendorserOuterClass.EndorserRequest.class,
      responseType = XendorserOuterClass.EndorserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<XendorserOuterClass.EndorserRequest,
      XendorserOuterClass.EndorserResponse> getEndorserCallMethod() {
    io.grpc.MethodDescriptor<XendorserOuterClass.EndorserRequest, XendorserOuterClass.EndorserResponse> getEndorserCallMethod;
    if ((getEndorserCallMethod = XendorserGrpc.getEndorserCallMethod) == null) {
      synchronized (XendorserGrpc.class) {
        if ((getEndorserCallMethod = XendorserGrpc.getEndorserCallMethod) == null) {
          XendorserGrpc.getEndorserCallMethod = getEndorserCallMethod =
              io.grpc.MethodDescriptor.<XendorserOuterClass.EndorserRequest, XendorserOuterClass.EndorserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EndorserCall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  XendorserOuterClass.EndorserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  XendorserOuterClass.EndorserResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getEndorserCallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static xendorserStub newStub(io.grpc.Channel channel) {
    return new xendorserStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static xendorserBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new xendorserBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static xendorserFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new xendorserFutureStub(channel);
  }

  /**
   */
  public static abstract class xendorserImplBase implements io.grpc.BindableService {

    /**
     */
    public void endorserCall(XendorserOuterClass.EndorserRequest request,
                             io.grpc.stub.StreamObserver<XendorserOuterClass.EndorserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getEndorserCallMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEndorserCallMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                XendorserOuterClass.EndorserRequest,
                XendorserOuterClass.EndorserResponse>(
                  this, METHODID_ENDORSER_CALL)))
          .build();
    }
  }

  /**
   */
  public static final class xendorserStub extends io.grpc.stub.AbstractStub<xendorserStub> {
    private xendorserStub(io.grpc.Channel channel) {
      super(channel);
    }

    private xendorserStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected xendorserStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new xendorserStub(channel, callOptions);
    }

    /**
     */
    public void endorserCall(XendorserOuterClass.EndorserRequest request,
                             io.grpc.stub.StreamObserver<XendorserOuterClass.EndorserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEndorserCallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class xendorserBlockingStub extends io.grpc.stub.AbstractStub<xendorserBlockingStub> {
    private xendorserBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private xendorserBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected xendorserBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new xendorserBlockingStub(channel, callOptions);
    }

    /**
     */
    public XendorserOuterClass.EndorserResponse endorserCall(XendorserOuterClass.EndorserRequest request) {
      return blockingUnaryCall(
          getChannel(), getEndorserCallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class xendorserFutureStub extends io.grpc.stub.AbstractStub<xendorserFutureStub> {
    private xendorserFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private xendorserFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected xendorserFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new xendorserFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<XendorserOuterClass.EndorserResponse> endorserCall(
        XendorserOuterClass.EndorserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getEndorserCallMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ENDORSER_CALL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final xendorserImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(xendorserImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ENDORSER_CALL:
          serviceImpl.endorserCall((XendorserOuterClass.EndorserRequest) request,
              (io.grpc.stub.StreamObserver<XendorserOuterClass.EndorserResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }


  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (XendorserGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getEndorserCallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
