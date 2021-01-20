package com.baidu.xuper.pb;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Xchain is the main interfaces
 * </pre>
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.25.0)",
        comments = "Source: xchain.proto")
public final class XchainGrpc {

    private XchainGrpc() {
    }

    public static final String SERVICE_NAME = "pb.Xchain";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoInput,
            com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> getSelectUTXOBySizeMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "SelectUTXOBySize",
            requestType = com.baidu.xuper.pb.XchainOuterClass.UtxoInput.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.UtxoOutput.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoInput,
            com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> getSelectUTXOBySizeMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoInput, com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> getSelectUTXOBySizeMethod;
        if ((getSelectUTXOBySizeMethod = XchainGrpc.getSelectUTXOBySizeMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getSelectUTXOBySizeMethod = XchainGrpc.getSelectUTXOBySizeMethod) == null) {
                    XchainGrpc.getSelectUTXOBySizeMethod = getSelectUTXOBySizeMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.UtxoInput, com.baidu.xuper.pb.XchainOuterClass.UtxoOutput>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SelectUTXOBySize"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoInput.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoOutput.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getSelectUTXOBySizeMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.TxStatus,
            com.baidu.xuper.pb.XchainOuterClass.CommonReply> getPostTxMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "PostTx",
            requestType = com.baidu.xuper.pb.XchainOuterClass.TxStatus.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.CommonReply.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.TxStatus,
            com.baidu.xuper.pb.XchainOuterClass.CommonReply> getPostTxMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.TxStatus, com.baidu.xuper.pb.XchainOuterClass.CommonReply> getPostTxMethod;
        if ((getPostTxMethod = XchainGrpc.getPostTxMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getPostTxMethod = XchainGrpc.getPostTxMethod) == null) {
                    XchainGrpc.getPostTxMethod = getPostTxMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.TxStatus, com.baidu.xuper.pb.XchainOuterClass.CommonReply>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PostTx"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.TxStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.CommonReply.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getPostTxMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AclStatus,
            com.baidu.xuper.pb.XchainOuterClass.AclStatus> getQueryACLMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "QueryACL",
            requestType = com.baidu.xuper.pb.XchainOuterClass.AclStatus.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.AclStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AclStatus,
            com.baidu.xuper.pb.XchainOuterClass.AclStatus> getQueryACLMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AclStatus, com.baidu.xuper.pb.XchainOuterClass.AclStatus> getQueryACLMethod;
        if ((getQueryACLMethod = XchainGrpc.getQueryACLMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getQueryACLMethod = XchainGrpc.getQueryACLMethod) == null) {
                    XchainGrpc.getQueryACLMethod = getQueryACLMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.AclStatus, com.baidu.xuper.pb.XchainOuterClass.AclStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryACL"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AclStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AclStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getQueryACLMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail,
            com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail> getQueryUtxoRecordMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "QueryUtxoRecord",
            requestType = com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail,
            com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail> getQueryUtxoRecordMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail, com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail> getQueryUtxoRecordMethod;
        if ((getQueryUtxoRecordMethod = XchainGrpc.getQueryUtxoRecordMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getQueryUtxoRecordMethod = XchainGrpc.getQueryUtxoRecordMethod) == null) {
                    XchainGrpc.getQueryUtxoRecordMethod = getQueryUtxoRecordMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail, com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryUtxoRecord"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getQueryUtxoRecordMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest,
            com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse> getQueryContractStatDataMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "QueryContractStatData",
            requestType = com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest,
            com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse> getQueryContractStatDataMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest, com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse> getQueryContractStatDataMethod;
        if ((getQueryContractStatDataMethod = XchainGrpc.getQueryContractStatDataMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getQueryContractStatDataMethod = XchainGrpc.getQueryContractStatDataMethod) == null) {
                    XchainGrpc.getQueryContractStatDataMethod = getQueryContractStatDataMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest, com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryContractStatData"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getQueryContractStatDataMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest,
            com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse> getGetAccountContractsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetAccountContracts",
            requestType = com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest,
            com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse> getGetAccountContractsMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest, com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse> getGetAccountContractsMethod;
        if ((getGetAccountContractsMethod = XchainGrpc.getGetAccountContractsMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetAccountContractsMethod = XchainGrpc.getGetAccountContractsMethod) == null) {
                    XchainGrpc.getGetAccountContractsMethod = getGetAccountContractsMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest, com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountContracts"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetAccountContractsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.TxStatus,
            com.baidu.xuper.pb.XchainOuterClass.TxStatus> getQueryTxMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "QueryTx",
            requestType = com.baidu.xuper.pb.XchainOuterClass.TxStatus.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.TxStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.TxStatus,
            com.baidu.xuper.pb.XchainOuterClass.TxStatus> getQueryTxMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.TxStatus, com.baidu.xuper.pb.XchainOuterClass.TxStatus> getQueryTxMethod;
        if ((getQueryTxMethod = XchainGrpc.getQueryTxMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getQueryTxMethod = XchainGrpc.getQueryTxMethod) == null) {
                    XchainGrpc.getQueryTxMethod = getQueryTxMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.TxStatus, com.baidu.xuper.pb.XchainOuterClass.TxStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "QueryTx"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.TxStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.TxStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getQueryTxMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressStatus,
            com.baidu.xuper.pb.XchainOuterClass.AddressStatus> getGetBalanceMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBalance",
            requestType = com.baidu.xuper.pb.XchainOuterClass.AddressStatus.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.AddressStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressStatus,
            com.baidu.xuper.pb.XchainOuterClass.AddressStatus> getGetBalanceMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressStatus, com.baidu.xuper.pb.XchainOuterClass.AddressStatus> getGetBalanceMethod;
        if ((getGetBalanceMethod = XchainGrpc.getGetBalanceMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetBalanceMethod = XchainGrpc.getGetBalanceMethod) == null) {
                    XchainGrpc.getGetBalanceMethod = getGetBalanceMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.AddressStatus, com.baidu.xuper.pb.XchainOuterClass.AddressStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalance"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AddressStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AddressStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBalanceMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus,
            com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus> getGetBalanceDetailMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBalanceDetail",
            requestType = com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus,
            com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus> getGetBalanceDetailMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus, com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus> getGetBalanceDetailMethod;
        if ((getGetBalanceDetailMethod = XchainGrpc.getGetBalanceDetailMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetBalanceDetailMethod = XchainGrpc.getGetBalanceDetailMethod) == null) {
                    XchainGrpc.getGetBalanceDetailMethod = getGetBalanceDetailMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus, com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalanceDetail"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBalanceDetailMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressStatus,
            com.baidu.xuper.pb.XchainOuterClass.AddressStatus> getGetFrozenBalanceMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetFrozenBalance",
            requestType = com.baidu.xuper.pb.XchainOuterClass.AddressStatus.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.AddressStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressStatus,
            com.baidu.xuper.pb.XchainOuterClass.AddressStatus> getGetFrozenBalanceMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressStatus, com.baidu.xuper.pb.XchainOuterClass.AddressStatus> getGetFrozenBalanceMethod;
        if ((getGetFrozenBalanceMethod = XchainGrpc.getGetFrozenBalanceMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetFrozenBalanceMethod = XchainGrpc.getGetFrozenBalanceMethod) == null) {
                    XchainGrpc.getGetFrozenBalanceMethod = getGetFrozenBalanceMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.AddressStatus, com.baidu.xuper.pb.XchainOuterClass.AddressStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFrozenBalance"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AddressStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AddressStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetFrozenBalanceMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BlockID,
            com.baidu.xuper.pb.XchainOuterClass.Block> getGetBlockMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBlock",
            requestType = com.baidu.xuper.pb.XchainOuterClass.BlockID.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.Block.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BlockID,
            com.baidu.xuper.pb.XchainOuterClass.Block> getGetBlockMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BlockID, com.baidu.xuper.pb.XchainOuterClass.Block> getGetBlockMethod;
        if ((getGetBlockMethod = XchainGrpc.getGetBlockMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetBlockMethod = XchainGrpc.getGetBlockMethod) == null) {
                    XchainGrpc.getGetBlockMethod = getGetBlockMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.BlockID, com.baidu.xuper.pb.XchainOuterClass.Block>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlock"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.BlockID.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.Block.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBlockMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BlockHeight,
            com.baidu.xuper.pb.XchainOuterClass.Block> getGetBlockByHeightMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBlockByHeight",
            requestType = com.baidu.xuper.pb.XchainOuterClass.BlockHeight.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.Block.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BlockHeight,
            com.baidu.xuper.pb.XchainOuterClass.Block> getGetBlockByHeightMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BlockHeight, com.baidu.xuper.pb.XchainOuterClass.Block> getGetBlockByHeightMethod;
        if ((getGetBlockByHeightMethod = XchainGrpc.getGetBlockByHeightMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetBlockByHeightMethod = XchainGrpc.getGetBlockByHeightMethod) == null) {
                    XchainGrpc.getGetBlockByHeightMethod = getGetBlockByHeightMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.BlockHeight, com.baidu.xuper.pb.XchainOuterClass.Block>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockByHeight"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.BlockHeight.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.Block.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBlockByHeightMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BCStatus,
            com.baidu.xuper.pb.XchainOuterClass.BCStatus> getGetBlockChainStatusMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBlockChainStatus",
            requestType = com.baidu.xuper.pb.XchainOuterClass.BCStatus.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.BCStatus.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BCStatus,
            com.baidu.xuper.pb.XchainOuterClass.BCStatus> getGetBlockChainStatusMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.BCStatus, com.baidu.xuper.pb.XchainOuterClass.BCStatus> getGetBlockChainStatusMethod;
        if ((getGetBlockChainStatusMethod = XchainGrpc.getGetBlockChainStatusMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetBlockChainStatusMethod = XchainGrpc.getGetBlockChainStatusMethod) == null) {
                    XchainGrpc.getGetBlockChainStatusMethod = getGetBlockChainStatusMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.BCStatus, com.baidu.xuper.pb.XchainOuterClass.BCStatus>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockChainStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.BCStatus.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.BCStatus.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBlockChainStatusMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn,
            com.baidu.xuper.pb.XchainOuterClass.BlockChains> getGetBlockChainsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetBlockChains",
            requestType = com.baidu.xuper.pb.XchainOuterClass.CommonIn.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.BlockChains.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn,
            com.baidu.xuper.pb.XchainOuterClass.BlockChains> getGetBlockChainsMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn, com.baidu.xuper.pb.XchainOuterClass.BlockChains> getGetBlockChainsMethod;
        if ((getGetBlockChainsMethod = XchainGrpc.getGetBlockChainsMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetBlockChainsMethod = XchainGrpc.getGetBlockChainsMethod) == null) {
                    XchainGrpc.getGetBlockChainsMethod = getGetBlockChainsMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.CommonIn, com.baidu.xuper.pb.XchainOuterClass.BlockChains>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBlockChains"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.CommonIn.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.BlockChains.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetBlockChainsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn,
            com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply> getGetSystemStatusMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetSystemStatus",
            requestType = com.baidu.xuper.pb.XchainOuterClass.CommonIn.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn,
            com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply> getGetSystemStatusMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn, com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply> getGetSystemStatusMethod;
        if ((getGetSystemStatusMethod = XchainGrpc.getGetSystemStatusMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetSystemStatusMethod = XchainGrpc.getGetSystemStatusMethod) == null) {
                    XchainGrpc.getGetSystemStatusMethod = getGetSystemStatusMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.CommonIn, com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSystemStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.CommonIn.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetSystemStatusMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn,
            com.baidu.xuper.pb.XchainOuterClass.RawUrl> getGetNetURLMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetNetURL",
            requestType = com.baidu.xuper.pb.XchainOuterClass.CommonIn.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.RawUrl.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn,
            com.baidu.xuper.pb.XchainOuterClass.RawUrl> getGetNetURLMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.CommonIn, com.baidu.xuper.pb.XchainOuterClass.RawUrl> getGetNetURLMethod;
        if ((getGetNetURLMethod = XchainGrpc.getGetNetURLMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetNetURLMethod = XchainGrpc.getGetNetURLMethod) == null) {
                    XchainGrpc.getGetNetURLMethod = getGetNetURLMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.CommonIn, com.baidu.xuper.pb.XchainOuterClass.RawUrl>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetNetURL"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.CommonIn.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.RawUrl.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetNetURLMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoInput,
            com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> getSelectUTXOMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "SelectUTXO",
            requestType = com.baidu.xuper.pb.XchainOuterClass.UtxoInput.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.UtxoOutput.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoInput,
            com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> getSelectUTXOMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.UtxoInput, com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> getSelectUTXOMethod;
        if ((getSelectUTXOMethod = XchainGrpc.getSelectUTXOMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getSelectUTXOMethod = XchainGrpc.getSelectUTXOMethod) == null) {
                    XchainGrpc.getSelectUTXOMethod = getSelectUTXOMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.UtxoInput, com.baidu.xuper.pb.XchainOuterClass.UtxoOutput>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SelectUTXO"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoInput.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoOutput.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getSelectUTXOMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest,
            com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> getPreExecWithSelectUTXOMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "PreExecWithSelectUTXO",
            requestType = com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest,
            com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> getPreExecWithSelectUTXOMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest, com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> getPreExecWithSelectUTXOMethod;
        if ((getPreExecWithSelectUTXOMethod = XchainGrpc.getPreExecWithSelectUTXOMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getPreExecWithSelectUTXOMethod = XchainGrpc.getPreExecWithSelectUTXOMethod) == null) {
                    XchainGrpc.getPreExecWithSelectUTXOMethod = getPreExecWithSelectUTXOMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest, com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PreExecWithSelectUTXO"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getPreExecWithSelectUTXOMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest,
            com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse> getDeployNativeCodeMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DeployNativeCode",
            requestType = com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest,
            com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse> getDeployNativeCodeMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest, com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse> getDeployNativeCodeMethod;
        if ((getDeployNativeCodeMethod = XchainGrpc.getDeployNativeCodeMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getDeployNativeCodeMethod = XchainGrpc.getDeployNativeCodeMethod) == null) {
                    XchainGrpc.getDeployNativeCodeMethod = getDeployNativeCodeMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest, com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeployNativeCode"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDeployNativeCodeMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest,
            com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse> getNativeCodeStatusMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "NativeCodeStatus",
            requestType = com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest,
            com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse> getNativeCodeStatusMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest, com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse> getNativeCodeStatusMethod;
        if ((getNativeCodeStatusMethod = XchainGrpc.getNativeCodeStatusMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getNativeCodeStatusMethod = XchainGrpc.getNativeCodeStatusMethod) == null) {
                    XchainGrpc.getNativeCodeStatusMethod = getNativeCodeStatusMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest, com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "NativeCodeStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getNativeCodeStatusMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse> getDposCandidatesMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposCandidates",
            requestType = com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse> getDposCandidatesMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest, com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse> getDposCandidatesMethod;
        if ((getDposCandidatesMethod = XchainGrpc.getDposCandidatesMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getDposCandidatesMethod = XchainGrpc.getDposCandidatesMethod) == null) {
                    XchainGrpc.getDposCandidatesMethod = getDposCandidatesMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest, com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposCandidates"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposCandidatesMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse> getDposNominateRecordsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposNominateRecords",
            requestType = com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse> getDposNominateRecordsMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest, com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse> getDposNominateRecordsMethod;
        if ((getDposNominateRecordsMethod = XchainGrpc.getDposNominateRecordsMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getDposNominateRecordsMethod = XchainGrpc.getDposNominateRecordsMethod) == null) {
                    XchainGrpc.getDposNominateRecordsMethod = getDposNominateRecordsMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest, com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposNominateRecords"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposNominateRecordsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse> getDposNomineeRecordsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposNomineeRecords",
            requestType = com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse> getDposNomineeRecordsMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest, com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse> getDposNomineeRecordsMethod;
        if ((getDposNomineeRecordsMethod = XchainGrpc.getDposNomineeRecordsMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getDposNomineeRecordsMethod = XchainGrpc.getDposNomineeRecordsMethod) == null) {
                    XchainGrpc.getDposNomineeRecordsMethod = getDposNomineeRecordsMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest, com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposNomineeRecords"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposNomineeRecordsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse> getDposVoteRecordsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposVoteRecords",
            requestType = com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse> getDposVoteRecordsMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest, com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse> getDposVoteRecordsMethod;
        if ((getDposVoteRecordsMethod = XchainGrpc.getDposVoteRecordsMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getDposVoteRecordsMethod = XchainGrpc.getDposVoteRecordsMethod) == null) {
                    XchainGrpc.getDposVoteRecordsMethod = getDposVoteRecordsMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest, com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposVoteRecords"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposVoteRecordsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse> getDposVotedRecordsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposVotedRecords",
            requestType = com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse> getDposVotedRecordsMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest, com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse> getDposVotedRecordsMethod;
        if ((getDposVotedRecordsMethod = XchainGrpc.getDposVotedRecordsMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getDposVotedRecordsMethod = XchainGrpc.getDposVotedRecordsMethod) == null) {
                    XchainGrpc.getDposVotedRecordsMethod = getDposVotedRecordsMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest, com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposVotedRecords"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposVotedRecordsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse> getDposCheckResultsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposCheckResults",
            requestType = com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse> getDposCheckResultsMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest, com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse> getDposCheckResultsMethod;
        if ((getDposCheckResultsMethod = XchainGrpc.getDposCheckResultsMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getDposCheckResultsMethod = XchainGrpc.getDposCheckResultsMethod) == null) {
                    XchainGrpc.getDposCheckResultsMethod = getDposCheckResultsMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest, com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposCheckResults"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposCheckResultsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse> getDposStatusMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "DposStatus",
            requestType = com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest,
            com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse> getDposStatusMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest, com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse> getDposStatusMethod;
        if ((getDposStatusMethod = XchainGrpc.getDposStatusMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getDposStatusMethod = XchainGrpc.getDposStatusMethod) == null) {
                    XchainGrpc.getDposStatusMethod = getDposStatusMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest, com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DposStatus"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getDposStatusMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest,
            com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse> getGetAccountByAKMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetAccountByAK",
            requestType = com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest,
            com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse> getGetAccountByAKMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest, com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse> getGetAccountByAKMethod;
        if ((getGetAccountByAKMethod = XchainGrpc.getGetAccountByAKMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetAccountByAKMethod = XchainGrpc.getGetAccountByAKMethod) == null) {
                    XchainGrpc.getGetAccountByAKMethod = getGetAccountByAKMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest, com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountByAK"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetAccountByAKMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest,
            com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse> getGetAddressContractsMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetAddressContracts",
            requestType = com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest,
            com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse> getGetAddressContractsMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest, com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse> getGetAddressContractsMethod;
        if ((getGetAddressContractsMethod = XchainGrpc.getGetAddressContractsMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getGetAddressContractsMethod = XchainGrpc.getGetAddressContractsMethod) == null) {
                    XchainGrpc.getGetAddressContractsMethod = getGetAddressContractsMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest, com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAddressContracts"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getGetAddressContractsMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest,
            com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse> getPreExecMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "PreExec",
            requestType = com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest.class,
            responseType = com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest,
            com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse> getPreExecMethod() {
        io.grpc.MethodDescriptor<com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest, com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse> getPreExecMethod;
        if ((getPreExecMethod = XchainGrpc.getPreExecMethod) == null) {
            synchronized (XchainGrpc.class) {
                if ((getPreExecMethod = XchainGrpc.getPreExecMethod) == null) {
                    XchainGrpc.getPreExecMethod = getPreExecMethod =
                            io.grpc.MethodDescriptor.<com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest, com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PreExec"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                                            com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse.getDefaultInstance()))
                                    .build();
                }
            }
        }
        return getPreExecMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static XchainStub newStub(io.grpc.Channel channel) {
        return new XchainStub(channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static XchainBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        return new XchainBlockingStub(channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static XchainFutureStub newFutureStub(
            io.grpc.Channel channel) {
        return new XchainFutureStub(channel);
    }

    /**
     * <pre>
     * Xchain is the main interfaces
     * </pre>
     */
    public static abstract class XchainImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * SelectUTXOBySize merge many utxos into a few of utxos
         * </pre>
         */
        public void selectUTXOBySize(com.baidu.xuper.pb.XchainOuterClass.UtxoInput request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> responseObserver) {
            asyncUnimplementedUnaryCall(getSelectUTXOBySizeMethod(), responseObserver);
        }

        /**
         * <pre>
         * PostTx post Transaction to a node
         * </pre>
         */
        public void postTx(com.baidu.xuper.pb.XchainOuterClass.TxStatus request,
                           io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.CommonReply> responseObserver) {
            asyncUnimplementedUnaryCall(getPostTxMethod(), responseObserver);
        }

        /**
         *
         */
        public void queryACL(com.baidu.xuper.pb.XchainOuterClass.AclStatus request,
                             io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AclStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryACLMethod(), responseObserver);
        }

        /**
         *
         */
        public void queryUtxoRecord(com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail request,
                                    io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryUtxoRecordMethod(), responseObserver);
        }

        /**
         *
         */
        public void queryContractStatData(com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest request,
                                          io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryContractStatDataMethod(), responseObserver);
        }

        /**
         *
         */
        public void getAccountContracts(com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest request,
                                        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAccountContractsMethod(), responseObserver);
        }

        /**
         * <pre>
         * QueryTx query Transaction by TxStatus,
         * Bcname and Txid are required for this
         * </pre>
         */
        public void queryTx(com.baidu.xuper.pb.XchainOuterClass.TxStatus request,
                            io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.TxStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getQueryTxMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetBalance get balance of an address,
         * Address is required for this
         * </pre>
         */
        public void getBalance(com.baidu.xuper.pb.XchainOuterClass.AddressStatus request,
                               io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetFrozenBalance get two kinds of balance
         * 1. Still be frozen of an address
         * 2. Available now of an address
         * Address is required for this
         * </pre>
         */
        public void getBalanceDetail(com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBalanceDetailMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetFrozenBalance get balance that still be frozen of an address,
         * Address is required for this
         * </pre>
         */
        public void getFrozenBalance(com.baidu.xuper.pb.XchainOuterClass.AddressStatus request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetFrozenBalanceMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetBlock get block by blockid and return if the block in trunk or in branch
         * </pre>
         */
        public void getBlock(com.baidu.xuper.pb.XchainOuterClass.BlockID request,
                             io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.Block> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetBlockByHeight get block by height and return if the block in trunk or in
         * branch
         * </pre>
         */
        public void getBlockByHeight(com.baidu.xuper.pb.XchainOuterClass.BlockHeight request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.Block> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockByHeightMethod(), responseObserver);
        }

        /**
         *
         */
        public void getBlockChainStatus(com.baidu.xuper.pb.XchainOuterClass.BCStatus request,
                                        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.BCStatus> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockChainStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         * Get blockchains query blockchains
         * </pre>
         */
        public void getBlockChains(com.baidu.xuper.pb.XchainOuterClass.CommonIn request,
                                   io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.BlockChains> responseObserver) {
            asyncUnimplementedUnaryCall(getGetBlockChainsMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetSystemStatus query system status
         * </pre>
         */
        public void getSystemStatus(com.baidu.xuper.pb.XchainOuterClass.CommonIn request,
                                    io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply> responseObserver) {
            asyncUnimplementedUnaryCall(getGetSystemStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetNetURL return net url
         * </pre>
         */
        public void getNetURL(com.baidu.xuper.pb.XchainOuterClass.CommonIn request,
                              io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.RawUrl> responseObserver) {
            asyncUnimplementedUnaryCall(getGetNetURLMethod(), responseObserver);
        }

        /**
         * <pre>
         * 新的Select utxos接口, 不需要签名，可以支持选择账户的utxo
         * </pre>
         */
        public void selectUTXO(com.baidu.xuper.pb.XchainOuterClass.UtxoInput request,
                               io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> responseObserver) {
            asyncUnimplementedUnaryCall(getSelectUTXOMethod(), responseObserver);
        }

        /**
         * <pre>
         * PreExecWithSelectUTXO preExec &amp; selectUtxo
         * </pre>
         */
        public void preExecWithSelectUTXO(com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest request,
                                          io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getPreExecWithSelectUTXOMethod(), responseObserver);
        }

        /**
         * <pre>
         * Native code deploy interface
         * </pre>
         */
        public void deployNativeCode(com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDeployNativeCodeMethod(), responseObserver);
        }

        /**
         * <pre>
         * Native code status
         * </pre>
         */
        public void nativeCodeStatus(com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getNativeCodeStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposCandidates get all candidates of the tdpos consensus
         * </pre>
         */
        public void dposCandidates(com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest request,
                                   io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposCandidatesMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposNominateRecords get all records nominated by an user
         * </pre>
         */
        public void dposNominateRecords(com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest request,
                                        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposNominateRecordsMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposNomineeRecords get nominated record of a candidate
         * </pre>
         */
        public void dposNomineeRecords(com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest request,
                                       io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposNomineeRecordsMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposVoteRecords get all vote records voted by an user
         * </pre>
         */
        public void dposVoteRecords(com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest request,
                                    io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposVoteRecordsMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposVotedRecords get all vote records of a candidate
         * </pre>
         */
        public void dposVotedRecords(com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposVotedRecordsMethod(), responseObserver);
        }

        /**
         * <pre>
         *  DposCheckResults get check results of a specific term
         * </pre>
         */
        public void dposCheckResults(com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposCheckResultsMethod(), responseObserver);
        }

        /**
         * <pre>
         * DposStatus get dpos status
         * </pre>
         */
        public void dposStatus(com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest request,
                               io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getDposStatusMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetAccountByAK get account sets contain a specific address
         * </pre>
         */
        public void getAccountByAK(com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest request,
                                   io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAccountByAKMethod(), responseObserver);
        }

        /**
         * <pre>
         * GetAddressContracts get contracts of accounts contain a specific address
         * </pre>
         */
        public void getAddressContracts(com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest request,
                                        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getGetAddressContractsMethod(), responseObserver);
        }

        /**
         * <pre>
         * 预执行合约
         * </pre>
         */
        public void preExec(com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest request,
                            io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse> responseObserver) {
            asyncUnimplementedUnaryCall(getPreExecMethod(), responseObserver);
        }

        @java.lang.Override
        public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            getSelectUTXOBySizeMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoInput,
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoOutput>(
                                            this, METHODID_SELECT_UTXOBY_SIZE)))
                    .addMethod(
                            getPostTxMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.TxStatus,
                                            com.baidu.xuper.pb.XchainOuterClass.CommonReply>(
                                            this, METHODID_POST_TX)))
                    .addMethod(
                            getQueryACLMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.AclStatus,
                                            com.baidu.xuper.pb.XchainOuterClass.AclStatus>(
                                            this, METHODID_QUERY_ACL)))
                    .addMethod(
                            getQueryUtxoRecordMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail,
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail>(
                                            this, METHODID_QUERY_UTXO_RECORD)))
                    .addMethod(
                            getQueryContractStatDataMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse>(
                                            this, METHODID_QUERY_CONTRACT_STAT_DATA)))
                    .addMethod(
                            getGetAccountContractsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse>(
                                            this, METHODID_GET_ACCOUNT_CONTRACTS)))
                    .addMethod(
                            getQueryTxMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.TxStatus,
                                            com.baidu.xuper.pb.XchainOuterClass.TxStatus>(
                                            this, METHODID_QUERY_TX)))
                    .addMethod(
                            getGetBalanceMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.AddressStatus,
                                            com.baidu.xuper.pb.XchainOuterClass.AddressStatus>(
                                            this, METHODID_GET_BALANCE)))
                    .addMethod(
                            getGetBalanceDetailMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus,
                                            com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus>(
                                            this, METHODID_GET_BALANCE_DETAIL)))
                    .addMethod(
                            getGetFrozenBalanceMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.AddressStatus,
                                            com.baidu.xuper.pb.XchainOuterClass.AddressStatus>(
                                            this, METHODID_GET_FROZEN_BALANCE)))
                    .addMethod(
                            getGetBlockMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.BlockID,
                                            com.baidu.xuper.pb.XchainOuterClass.Block>(
                                            this, METHODID_GET_BLOCK)))
                    .addMethod(
                            getGetBlockByHeightMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.BlockHeight,
                                            com.baidu.xuper.pb.XchainOuterClass.Block>(
                                            this, METHODID_GET_BLOCK_BY_HEIGHT)))
                    .addMethod(
                            getGetBlockChainStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.BCStatus,
                                            com.baidu.xuper.pb.XchainOuterClass.BCStatus>(
                                            this, METHODID_GET_BLOCK_CHAIN_STATUS)))
                    .addMethod(
                            getGetBlockChainsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.CommonIn,
                                            com.baidu.xuper.pb.XchainOuterClass.BlockChains>(
                                            this, METHODID_GET_BLOCK_CHAINS)))
                    .addMethod(
                            getGetSystemStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.CommonIn,
                                            com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply>(
                                            this, METHODID_GET_SYSTEM_STATUS)))
                    .addMethod(
                            getGetNetURLMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.CommonIn,
                                            com.baidu.xuper.pb.XchainOuterClass.RawUrl>(
                                            this, METHODID_GET_NET_URL)))
                    .addMethod(
                            getSelectUTXOMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoInput,
                                            com.baidu.xuper.pb.XchainOuterClass.UtxoOutput>(
                                            this, METHODID_SELECT_UTXO)))
                    .addMethod(
                            getPreExecWithSelectUTXOMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest,
                                            com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse>(
                                            this, METHODID_PRE_EXEC_WITH_SELECT_UTXO)))
                    .addMethod(
                            getDeployNativeCodeMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse>(
                                            this, METHODID_DEPLOY_NATIVE_CODE)))
                    .addMethod(
                            getNativeCodeStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse>(
                                            this, METHODID_NATIVE_CODE_STATUS)))
                    .addMethod(
                            getDposCandidatesMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse>(
                                            this, METHODID_DPOS_CANDIDATES)))
                    .addMethod(
                            getDposNominateRecordsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse>(
                                            this, METHODID_DPOS_NOMINATE_RECORDS)))
                    .addMethod(
                            getDposNomineeRecordsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse>(
                                            this, METHODID_DPOS_NOMINEE_RECORDS)))
                    .addMethod(
                            getDposVoteRecordsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse>(
                                            this, METHODID_DPOS_VOTE_RECORDS)))
                    .addMethod(
                            getDposVotedRecordsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse>(
                                            this, METHODID_DPOS_VOTED_RECORDS)))
                    .addMethod(
                            getDposCheckResultsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse>(
                                            this, METHODID_DPOS_CHECK_RESULTS)))
                    .addMethod(
                            getDposStatusMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse>(
                                            this, METHODID_DPOS_STATUS)))
                    .addMethod(
                            getGetAccountByAKMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse>(
                                            this, METHODID_GET_ACCOUNT_BY_AK)))
                    .addMethod(
                            getGetAddressContractsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse>(
                                            this, METHODID_GET_ADDRESS_CONTRACTS)))
                    .addMethod(
                            getPreExecMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest,
                                            com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse>(
                                            this, METHODID_PRE_EXEC)))
                    .build();
        }
    }

    /**
     * <pre>
     * Xchain is the main interfaces
     * </pre>
     */
    public static final class XchainStub extends io.grpc.stub.AbstractStub<XchainStub> {
        private XchainStub(io.grpc.Channel channel) {
            super(channel);
        }

        private XchainStub(io.grpc.Channel channel,
                           io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected XchainStub build(io.grpc.Channel channel,
                                   io.grpc.CallOptions callOptions) {
            return new XchainStub(channel, callOptions);
        }

        /**
         * <pre>
         * SelectUTXOBySize merge many utxos into a few of utxos
         * </pre>
         */
        public void selectUTXOBySize(com.baidu.xuper.pb.XchainOuterClass.UtxoInput request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getSelectUTXOBySizeMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * PostTx post Transaction to a node
         * </pre>
         */
        public void postTx(com.baidu.xuper.pb.XchainOuterClass.TxStatus request,
                           io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.CommonReply> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getPostTxMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void queryACL(com.baidu.xuper.pb.XchainOuterClass.AclStatus request,
                             io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AclStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getQueryACLMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void queryUtxoRecord(com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail request,
                                    io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getQueryUtxoRecordMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void queryContractStatData(com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest request,
                                          io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getQueryContractStatDataMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getAccountContracts(com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest request,
                                        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetAccountContractsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * QueryTx query Transaction by TxStatus,
         * Bcname and Txid are required for this
         * </pre>
         */
        public void queryTx(com.baidu.xuper.pb.XchainOuterClass.TxStatus request,
                            io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.TxStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getQueryTxMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetBalance get balance of an address,
         * Address is required for this
         * </pre>
         */
        public void getBalance(com.baidu.xuper.pb.XchainOuterClass.AddressStatus request,
                               io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetFrozenBalance get two kinds of balance
         * 1. Still be frozen of an address
         * 2. Available now of an address
         * Address is required for this
         * </pre>
         */
        public void getBalanceDetail(com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBalanceDetailMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetFrozenBalance get balance that still be frozen of an address,
         * Address is required for this
         * </pre>
         */
        public void getFrozenBalance(com.baidu.xuper.pb.XchainOuterClass.AddressStatus request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetFrozenBalanceMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetBlock get block by blockid and return if the block in trunk or in branch
         * </pre>
         */
        public void getBlock(com.baidu.xuper.pb.XchainOuterClass.BlockID request,
                             io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.Block> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBlockMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetBlockByHeight get block by height and return if the block in trunk or in
         * branch
         * </pre>
         */
        public void getBlockByHeight(com.baidu.xuper.pb.XchainOuterClass.BlockHeight request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.Block> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         *
         */
        public void getBlockChainStatus(com.baidu.xuper.pb.XchainOuterClass.BCStatus request,
                                        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.BCStatus> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBlockChainStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Get blockchains query blockchains
         * </pre>
         */
        public void getBlockChains(com.baidu.xuper.pb.XchainOuterClass.CommonIn request,
                                   io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.BlockChains> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetBlockChainsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetSystemStatus query system status
         * </pre>
         */
        public void getSystemStatus(com.baidu.xuper.pb.XchainOuterClass.CommonIn request,
                                    io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetSystemStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetNetURL return net url
         * </pre>
         */
        public void getNetURL(com.baidu.xuper.pb.XchainOuterClass.CommonIn request,
                              io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.RawUrl> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetNetURLMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 新的Select utxos接口, 不需要签名，可以支持选择账户的utxo
         * </pre>
         */
        public void selectUTXO(com.baidu.xuper.pb.XchainOuterClass.UtxoInput request,
                               io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getSelectUTXOMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * PreExecWithSelectUTXO preExec &amp; selectUtxo
         * </pre>
         */
        public void preExecWithSelectUTXO(com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest request,
                                          io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getPreExecWithSelectUTXOMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Native code deploy interface
         * </pre>
         */
        public void deployNativeCode(com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDeployNativeCodeMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * Native code status
         * </pre>
         */
        public void nativeCodeStatus(com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getNativeCodeStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposCandidates get all candidates of the tdpos consensus
         * </pre>
         */
        public void dposCandidates(com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest request,
                                   io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposCandidatesMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposNominateRecords get all records nominated by an user
         * </pre>
         */
        public void dposNominateRecords(com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest request,
                                        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposNominateRecordsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposNomineeRecords get nominated record of a candidate
         * </pre>
         */
        public void dposNomineeRecords(com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest request,
                                       io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposNomineeRecordsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposVoteRecords get all vote records voted by an user
         * </pre>
         */
        public void dposVoteRecords(com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest request,
                                    io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposVoteRecordsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposVotedRecords get all vote records of a candidate
         * </pre>
         */
        public void dposVotedRecords(com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposVotedRecordsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         *  DposCheckResults get check results of a specific term
         * </pre>
         */
        public void dposCheckResults(com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest request,
                                     io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposCheckResultsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * DposStatus get dpos status
         * </pre>
         */
        public void dposStatus(com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest request,
                               io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getDposStatusMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetAccountByAK get account sets contain a specific address
         * </pre>
         */
        public void getAccountByAK(com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest request,
                                   io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetAccountByAKMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * GetAddressContracts get contracts of accounts contain a specific address
         * </pre>
         */
        public void getAddressContracts(com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest request,
                                        io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getGetAddressContractsMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         * <pre>
         * 预执行合约
         * </pre>
         */
        public void preExec(com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest request,
                            io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse> responseObserver) {
            asyncUnaryCall(
                    getChannel().newCall(getPreExecMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * <pre>
     * Xchain is the main interfaces
     * </pre>
     */
    public static final class XchainBlockingStub extends io.grpc.stub.AbstractStub<XchainBlockingStub> {
        private XchainBlockingStub(io.grpc.Channel channel) {
            super(channel);
        }

        private XchainBlockingStub(io.grpc.Channel channel,
                                   io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected XchainBlockingStub build(io.grpc.Channel channel,
                                           io.grpc.CallOptions callOptions) {
            return new XchainBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * SelectUTXOBySize merge many utxos into a few of utxos
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.UtxoOutput selectUTXOBySize(com.baidu.xuper.pb.XchainOuterClass.UtxoInput request) {
            return blockingUnaryCall(
                    getChannel(), getSelectUTXOBySizeMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * PostTx post Transaction to a node
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.CommonReply postTx(com.baidu.xuper.pb.XchainOuterClass.TxStatus request) {
            return blockingUnaryCall(
                    getChannel(), getPostTxMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.baidu.xuper.pb.XchainOuterClass.AclStatus queryACL(com.baidu.xuper.pb.XchainOuterClass.AclStatus request) {
            return blockingUnaryCall(
                    getChannel(), getQueryACLMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail queryUtxoRecord(com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail request) {
            return blockingUnaryCall(
                    getChannel(), getQueryUtxoRecordMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse queryContractStatData(com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest request) {
            return blockingUnaryCall(
                    getChannel(), getQueryContractStatDataMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse getAccountContracts(com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getGetAccountContractsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * QueryTx query Transaction by TxStatus,
         * Bcname and Txid are required for this
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.TxStatus queryTx(com.baidu.xuper.pb.XchainOuterClass.TxStatus request) {
            return blockingUnaryCall(
                    getChannel(), getQueryTxMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetBalance get balance of an address,
         * Address is required for this
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.AddressStatus getBalance(com.baidu.xuper.pb.XchainOuterClass.AddressStatus request) {
            return blockingUnaryCall(
                    getChannel(), getGetBalanceMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetFrozenBalance get two kinds of balance
         * 1. Still be frozen of an address
         * 2. Available now of an address
         * Address is required for this
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus getBalanceDetail(com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus request) {
            return blockingUnaryCall(
                    getChannel(), getGetBalanceDetailMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetFrozenBalance get balance that still be frozen of an address,
         * Address is required for this
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.AddressStatus getFrozenBalance(com.baidu.xuper.pb.XchainOuterClass.AddressStatus request) {
            return blockingUnaryCall(
                    getChannel(), getGetFrozenBalanceMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetBlock get block by blockid and return if the block in trunk or in branch
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.Block getBlock(com.baidu.xuper.pb.XchainOuterClass.BlockID request) {
            return blockingUnaryCall(
                    getChannel(), getGetBlockMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetBlockByHeight get block by height and return if the block in trunk or in
         * branch
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.Block getBlockByHeight(com.baidu.xuper.pb.XchainOuterClass.BlockHeight request) {
            return blockingUnaryCall(
                    getChannel(), getGetBlockByHeightMethod(), getCallOptions(), request);
        }

        /**
         *
         */
        public com.baidu.xuper.pb.XchainOuterClass.BCStatus getBlockChainStatus(com.baidu.xuper.pb.XchainOuterClass.BCStatus request) {
            return blockingUnaryCall(
                    getChannel(), getGetBlockChainStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Get blockchains query blockchains
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.BlockChains getBlockChains(com.baidu.xuper.pb.XchainOuterClass.CommonIn request) {
            return blockingUnaryCall(
                    getChannel(), getGetBlockChainsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetSystemStatus query system status
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply getSystemStatus(com.baidu.xuper.pb.XchainOuterClass.CommonIn request) {
            return blockingUnaryCall(
                    getChannel(), getGetSystemStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetNetURL return net url
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.RawUrl getNetURL(com.baidu.xuper.pb.XchainOuterClass.CommonIn request) {
            return blockingUnaryCall(
                    getChannel(), getGetNetURLMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 新的Select utxos接口, 不需要签名，可以支持选择账户的utxo
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.UtxoOutput selectUTXO(com.baidu.xuper.pb.XchainOuterClass.UtxoInput request) {
            return blockingUnaryCall(
                    getChannel(), getSelectUTXOMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * PreExecWithSelectUTXO preExec &amp; selectUtxo
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse preExecWithSelectUTXO(com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest request) {
            return blockingUnaryCall(
                    getChannel(), getPreExecWithSelectUTXOMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Native code deploy interface
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse deployNativeCode(com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDeployNativeCodeMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * Native code status
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse nativeCodeStatus(com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest request) {
            return blockingUnaryCall(
                    getChannel(), getNativeCodeStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposCandidates get all candidates of the tdpos consensus
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse dposCandidates(com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposCandidatesMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposNominateRecords get all records nominated by an user
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse dposNominateRecords(com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposNominateRecordsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposNomineeRecords get nominated record of a candidate
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse dposNomineeRecords(com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposNomineeRecordsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposVoteRecords get all vote records voted by an user
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse dposVoteRecords(com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposVoteRecordsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposVotedRecords get all vote records of a candidate
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse dposVotedRecords(com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposVotedRecordsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         *  DposCheckResults get check results of a specific term
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse dposCheckResults(com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposCheckResultsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * DposStatus get dpos status
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse dposStatus(com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest request) {
            return blockingUnaryCall(
                    getChannel(), getDposStatusMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetAccountByAK get account sets contain a specific address
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse getAccountByAK(com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest request) {
            return blockingUnaryCall(
                    getChannel(), getGetAccountByAKMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * GetAddressContracts get contracts of accounts contain a specific address
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse getAddressContracts(com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest request) {
            return blockingUnaryCall(
                    getChannel(), getGetAddressContractsMethod(), getCallOptions(), request);
        }

        /**
         * <pre>
         * 预执行合约
         * </pre>
         */
        public com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse preExec(com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest request) {
            return blockingUnaryCall(
                    getChannel(), getPreExecMethod(), getCallOptions(), request);
        }
    }

    /**
     * <pre>
     * Xchain is the main interfaces
     * </pre>
     */
    public static final class XchainFutureStub extends io.grpc.stub.AbstractStub<XchainFutureStub> {
        private XchainFutureStub(io.grpc.Channel channel) {
            super(channel);
        }

        private XchainFutureStub(io.grpc.Channel channel,
                                 io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected XchainFutureStub build(io.grpc.Channel channel,
                                         io.grpc.CallOptions callOptions) {
            return new XchainFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * SelectUTXOBySize merge many utxos into a few of utxos
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> selectUTXOBySize(
                com.baidu.xuper.pb.XchainOuterClass.UtxoInput request) {
            return futureUnaryCall(
                    getChannel().newCall(getSelectUTXOBySizeMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * PostTx post Transaction to a node
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.CommonReply> postTx(
                com.baidu.xuper.pb.XchainOuterClass.TxStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getPostTxMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.AclStatus> queryACL(
                com.baidu.xuper.pb.XchainOuterClass.AclStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getQueryACLMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail> queryUtxoRecord(
                com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail request) {
            return futureUnaryCall(
                    getChannel().newCall(getQueryUtxoRecordMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse> queryContractStatData(
                com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getQueryContractStatDataMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse> getAccountContracts(
                com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetAccountContractsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * QueryTx query Transaction by TxStatus,
         * Bcname and Txid are required for this
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.TxStatus> queryTx(
                com.baidu.xuper.pb.XchainOuterClass.TxStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getQueryTxMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetBalance get balance of an address,
         * Address is required for this
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.AddressStatus> getBalance(
                com.baidu.xuper.pb.XchainOuterClass.AddressStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetFrozenBalance get two kinds of balance
         * 1. Still be frozen of an address
         * 2. Available now of an address
         * Address is required for this
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus> getBalanceDetail(
                com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBalanceDetailMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetFrozenBalance get balance that still be frozen of an address,
         * Address is required for this
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.AddressStatus> getFrozenBalance(
                com.baidu.xuper.pb.XchainOuterClass.AddressStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetFrozenBalanceMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetBlock get block by blockid and return if the block in trunk or in branch
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.Block> getBlock(
                com.baidu.xuper.pb.XchainOuterClass.BlockID request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBlockMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetBlockByHeight get block by height and return if the block in trunk or in
         * branch
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.Block> getBlockByHeight(
                com.baidu.xuper.pb.XchainOuterClass.BlockHeight request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBlockByHeightMethod(), getCallOptions()), request);
        }

        /**
         *
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.BCStatus> getBlockChainStatus(
                com.baidu.xuper.pb.XchainOuterClass.BCStatus request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBlockChainStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Get blockchains query blockchains
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.BlockChains> getBlockChains(
                com.baidu.xuper.pb.XchainOuterClass.CommonIn request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetBlockChainsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetSystemStatus query system status
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply> getSystemStatus(
                com.baidu.xuper.pb.XchainOuterClass.CommonIn request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetSystemStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetNetURL return net url
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.RawUrl> getNetURL(
                com.baidu.xuper.pb.XchainOuterClass.CommonIn request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetNetURLMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 新的Select utxos接口, 不需要签名，可以支持选择账户的utxo
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.UtxoOutput> selectUTXO(
                com.baidu.xuper.pb.XchainOuterClass.UtxoInput request) {
            return futureUnaryCall(
                    getChannel().newCall(getSelectUTXOMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * PreExecWithSelectUTXO preExec &amp; selectUtxo
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse> preExecWithSelectUTXO(
                com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getPreExecWithSelectUTXOMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Native code deploy interface
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse> deployNativeCode(
                com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDeployNativeCodeMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * Native code status
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse> nativeCodeStatus(
                com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getNativeCodeStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposCandidates get all candidates of the tdpos consensus
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse> dposCandidates(
                com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposCandidatesMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposNominateRecords get all records nominated by an user
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse> dposNominateRecords(
                com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposNominateRecordsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposNomineeRecords get nominated record of a candidate
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse> dposNomineeRecords(
                com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposNomineeRecordsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposVoteRecords get all vote records voted by an user
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse> dposVoteRecords(
                com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposVoteRecordsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposVotedRecords get all vote records of a candidate
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse> dposVotedRecords(
                com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposVotedRecordsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         *  DposCheckResults get check results of a specific term
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse> dposCheckResults(
                com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposCheckResultsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * DposStatus get dpos status
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse> dposStatus(
                com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getDposStatusMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetAccountByAK get account sets contain a specific address
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse> getAccountByAK(
                com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetAccountByAKMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * GetAddressContracts get contracts of accounts contain a specific address
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse> getAddressContracts(
                com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getGetAddressContractsMethod(), getCallOptions()), request);
        }

        /**
         * <pre>
         * 预执行合约
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse> preExec(
                com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest request) {
            return futureUnaryCall(
                    getChannel().newCall(getPreExecMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_SELECT_UTXOBY_SIZE = 0;
    private static final int METHODID_POST_TX = 1;
    private static final int METHODID_QUERY_ACL = 2;
    private static final int METHODID_QUERY_UTXO_RECORD = 3;
    private static final int METHODID_QUERY_CONTRACT_STAT_DATA = 4;
    private static final int METHODID_GET_ACCOUNT_CONTRACTS = 5;
    private static final int METHODID_QUERY_TX = 6;
    private static final int METHODID_GET_BALANCE = 7;
    private static final int METHODID_GET_BALANCE_DETAIL = 8;
    private static final int METHODID_GET_FROZEN_BALANCE = 9;
    private static final int METHODID_GET_BLOCK = 10;
    private static final int METHODID_GET_BLOCK_BY_HEIGHT = 11;
    private static final int METHODID_GET_BLOCK_CHAIN_STATUS = 12;
    private static final int METHODID_GET_BLOCK_CHAINS = 13;
    private static final int METHODID_GET_SYSTEM_STATUS = 14;
    private static final int METHODID_GET_NET_URL = 15;
    private static final int METHODID_SELECT_UTXO = 16;
    private static final int METHODID_PRE_EXEC_WITH_SELECT_UTXO = 17;
    private static final int METHODID_DEPLOY_NATIVE_CODE = 18;
    private static final int METHODID_NATIVE_CODE_STATUS = 19;
    private static final int METHODID_DPOS_CANDIDATES = 20;
    private static final int METHODID_DPOS_NOMINATE_RECORDS = 21;
    private static final int METHODID_DPOS_NOMINEE_RECORDS = 22;
    private static final int METHODID_DPOS_VOTE_RECORDS = 23;
    private static final int METHODID_DPOS_VOTED_RECORDS = 24;
    private static final int METHODID_DPOS_CHECK_RESULTS = 25;
    private static final int METHODID_DPOS_STATUS = 26;
    private static final int METHODID_GET_ACCOUNT_BY_AK = 27;
    private static final int METHODID_GET_ADDRESS_CONTRACTS = 28;
    private static final int METHODID_PRE_EXEC = 29;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final XchainImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(XchainImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SELECT_UTXOBY_SIZE:
                    serviceImpl.selectUTXOBySize((com.baidu.xuper.pb.XchainOuterClass.UtxoInput) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoOutput>) responseObserver);
                    break;
                case METHODID_POST_TX:
                    serviceImpl.postTx((com.baidu.xuper.pb.XchainOuterClass.TxStatus) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.CommonReply>) responseObserver);
                    break;
                case METHODID_QUERY_ACL:
                    serviceImpl.queryACL((com.baidu.xuper.pb.XchainOuterClass.AclStatus) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AclStatus>) responseObserver);
                    break;
                case METHODID_QUERY_UTXO_RECORD:
                    serviceImpl.queryUtxoRecord((com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoRecordDetail>) responseObserver);
                    break;
                case METHODID_QUERY_CONTRACT_STAT_DATA:
                    serviceImpl.queryContractStatData((com.baidu.xuper.pb.XchainOuterClass.ContractStatDataRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.ContractStatDataResponse>) responseObserver);
                    break;
                case METHODID_GET_ACCOUNT_CONTRACTS:
                    serviceImpl.getAccountContracts((com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.GetAccountContractsResponse>) responseObserver);
                    break;
                case METHODID_QUERY_TX:
                    serviceImpl.queryTx((com.baidu.xuper.pb.XchainOuterClass.TxStatus) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.TxStatus>) responseObserver);
                    break;
                case METHODID_GET_BALANCE:
                    serviceImpl.getBalance((com.baidu.xuper.pb.XchainOuterClass.AddressStatus) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressStatus>) responseObserver);
                    break;
                case METHODID_GET_BALANCE_DETAIL:
                    serviceImpl.getBalanceDetail((com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressBalanceStatus>) responseObserver);
                    break;
                case METHODID_GET_FROZEN_BALANCE:
                    serviceImpl.getFrozenBalance((com.baidu.xuper.pb.XchainOuterClass.AddressStatus) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressStatus>) responseObserver);
                    break;
                case METHODID_GET_BLOCK:
                    serviceImpl.getBlock((com.baidu.xuper.pb.XchainOuterClass.BlockID) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.Block>) responseObserver);
                    break;
                case METHODID_GET_BLOCK_BY_HEIGHT:
                    serviceImpl.getBlockByHeight((com.baidu.xuper.pb.XchainOuterClass.BlockHeight) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.Block>) responseObserver);
                    break;
                case METHODID_GET_BLOCK_CHAIN_STATUS:
                    serviceImpl.getBlockChainStatus((com.baidu.xuper.pb.XchainOuterClass.BCStatus) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.BCStatus>) responseObserver);
                    break;
                case METHODID_GET_BLOCK_CHAINS:
                    serviceImpl.getBlockChains((com.baidu.xuper.pb.XchainOuterClass.CommonIn) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.BlockChains>) responseObserver);
                    break;
                case METHODID_GET_SYSTEM_STATUS:
                    serviceImpl.getSystemStatus((com.baidu.xuper.pb.XchainOuterClass.CommonIn) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.SystemsStatusReply>) responseObserver);
                    break;
                case METHODID_GET_NET_URL:
                    serviceImpl.getNetURL((com.baidu.xuper.pb.XchainOuterClass.CommonIn) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.RawUrl>) responseObserver);
                    break;
                case METHODID_SELECT_UTXO:
                    serviceImpl.selectUTXO((com.baidu.xuper.pb.XchainOuterClass.UtxoInput) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.UtxoOutput>) responseObserver);
                    break;
                case METHODID_PRE_EXEC_WITH_SELECT_UTXO:
                    serviceImpl.preExecWithSelectUTXO((com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXORequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.PreExecWithSelectUTXOResponse>) responseObserver);
                    break;
                case METHODID_DEPLOY_NATIVE_CODE:
                    serviceImpl.deployNativeCode((com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DeployNativeCodeResponse>) responseObserver);
                    break;
                case METHODID_NATIVE_CODE_STATUS:
                    serviceImpl.nativeCodeStatus((com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.NativeCodeStatusResponse>) responseObserver);
                    break;
                case METHODID_DPOS_CANDIDATES:
                    serviceImpl.dposCandidates((com.baidu.xuper.pb.XchainOuterClass.DposCandidatesRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposCandidatesResponse>) responseObserver);
                    break;
                case METHODID_DPOS_NOMINATE_RECORDS:
                    serviceImpl.dposNominateRecords((com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposNominateRecordsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_NOMINEE_RECORDS:
                    serviceImpl.dposNomineeRecords((com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposNomineeRecordsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_VOTE_RECORDS:
                    serviceImpl.dposVoteRecords((com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposVoteRecordsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_VOTED_RECORDS:
                    serviceImpl.dposVotedRecords((com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposVotedRecordsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_CHECK_RESULTS:
                    serviceImpl.dposCheckResults((com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposCheckResultsResponse>) responseObserver);
                    break;
                case METHODID_DPOS_STATUS:
                    serviceImpl.dposStatus((com.baidu.xuper.pb.XchainOuterClass.DposStatusRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.DposStatusResponse>) responseObserver);
                    break;
                case METHODID_GET_ACCOUNT_BY_AK:
                    serviceImpl.getAccountByAK((com.baidu.xuper.pb.XchainOuterClass.AK2AccountRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AK2AccountResponse>) responseObserver);
                    break;
                case METHODID_GET_ADDRESS_CONTRACTS:
                    serviceImpl.getAddressContracts((com.baidu.xuper.pb.XchainOuterClass.AddressContractsRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.AddressContractsResponse>) responseObserver);
                    break;
                case METHODID_PRE_EXEC:
                    serviceImpl.preExec((com.baidu.xuper.pb.XchainOuterClass.InvokeRPCRequest) request,
                            (io.grpc.stub.StreamObserver<com.baidu.xuper.pb.XchainOuterClass.InvokeRPCResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
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
            synchronized (XchainGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .addMethod(getSelectUTXOBySizeMethod())
                            .addMethod(getPostTxMethod())
                            .addMethod(getQueryACLMethod())
                            .addMethod(getQueryUtxoRecordMethod())
                            .addMethod(getQueryContractStatDataMethod())
                            .addMethod(getGetAccountContractsMethod())
                            .addMethod(getQueryTxMethod())
                            .addMethod(getGetBalanceMethod())
                            .addMethod(getGetBalanceDetailMethod())
                            .addMethod(getGetFrozenBalanceMethod())
                            .addMethod(getGetBlockMethod())
                            .addMethod(getGetBlockByHeightMethod())
                            .addMethod(getGetBlockChainStatusMethod())
                            .addMethod(getGetBlockChainsMethod())
                            .addMethod(getGetSystemStatusMethod())
                            .addMethod(getGetNetURLMethod())
                            .addMethod(getSelectUTXOMethod())
                            .addMethod(getPreExecWithSelectUTXOMethod())
                            .addMethod(getDeployNativeCodeMethod())
                            .addMethod(getNativeCodeStatusMethod())
                            .addMethod(getDposCandidatesMethod())
                            .addMethod(getDposNominateRecordsMethod())
                            .addMethod(getDposNomineeRecordsMethod())
                            .addMethod(getDposVoteRecordsMethod())
                            .addMethod(getDposVotedRecordsMethod())
                            .addMethod(getDposCheckResultsMethod())
                            .addMethod(getDposStatusMethod())
                            .addMethod(getGetAccountByAKMethod())
                            .addMethod(getGetAddressContractsMethod())
                            .addMethod(getPreExecMethod())
                            .build();
                }
            }
        }
        return result;
    }
}
