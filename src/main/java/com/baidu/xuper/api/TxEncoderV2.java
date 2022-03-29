package com.baidu.xuper.api;

import com.baidu.xuper.crypto.xchain.hash.Hash;
import com.baidu.xuper.pb.XchainOuterClass;
import com.google.protobuf.ByteString;

import org.apache.fluo.api.data.Bytes;
import org.apache.fluo.api.data.Bytes.BytesBuilder;

import java.util.Arrays;
import java.util.Map;

/**
 * 序列化
 *
 */
public class TxEncoderV2 {
    private BytesBuilder buffer;

    TxEncoderV2() {
        buffer = Bytes.builder();;
    }

    private void encode(int i) {
        encode((long)i);
//        byte[] result = Common.intToBytes(i, 8);
//        buffer.append(result);
    }

    private void encode(ByteString bs) {
        encode((long)bs.size());
        if (bs.size() == 0) {
            return;
        }
        buffer.append(bs.toByteArray());
    }

    private void encode(String s) {
        encode(s.length());
        if (s.length() == 0) {
            return;
        }
        buffer.append(s.getBytes());
    }

    private void encode(long i) {
        byte[] result = Common.longToBytes(i);
        buffer.append(result);
    }

    private void encode(boolean v) {
        if (v) {
            encode(1);

        } else {
            encode(0);
        }
    }

    private void encode(Map<String, ByteString> maps) {
        int len = maps.size();
        encode(len);
        if (len == 0) {
            return;
        }

        // 遍历到string数组并排序
        String[] strArray = new String[len];
        int i = 0;
        for (String s : maps.keySet()) {
            strArray[i] = s;
            i++;
        }
        Arrays.sort(strArray);

        for (String str : strArray) {
            encode(str);
            encode(maps.get(str));
        }
    }

    byte[] txDigestHashV2(XchainOuterClass.Transaction tx, boolean needSign) {
        encode(tx.getTxInputsList().size());
        for (XchainOuterClass.TxInput input : tx.getTxInputsList()) {
            encode(input.getRefTxid());
            encode(input.getRefOffset());
            encode(input.getFromAddr());
            encode(input.getAmount());
            encode(input.getFrozenHeight());
        }

        encode(tx.getTxOutputsList().size());
        for (XchainOuterClass.TxOutput output : tx.getTxOutputsList()) {
            encode(output.getAmount());
            encode(output.getToAddr());
            encode(output.getFrozenHeight());
        }

        encode(tx.getDesc());
        encode(tx.getCoinbase());
        encode(tx.getNonce());
        encode(tx.getTimestamp());
        encode(tx.getVersion());
        encode(tx.getAutogen());

        encode(tx.getTxInputsExtList().size());
        for (XchainOuterClass.TxInputExt input : tx.getTxInputsExtList()) {
            encode(input.getBucket());
            encode(input.getKey());
            encode(input.getRefTxid());
            encode(input.getRefOffset());
        }

        encode(tx.getTxOutputsExtList().size());
        for (XchainOuterClass.TxOutputExt output : tx.getTxOutputsExtList()) {
            encode(output.getBucket());
            encode(output.getKey());
            encode(output.getValue());
        }

        encode(tx.getContractRequestsList().size());

        for (XchainOuterClass.InvokeRequest rep : tx.getContractRequestsList()) {
            encode(rep.getModuleName());
            encode(rep.getContractName());
            encode(rep.getMethodName());
            encode(rep.getArgsMap());

            encode(rep.getResourceLimitsList().size());
            for (XchainOuterClass.ResourceLimit limit : rep.getResourceLimitsList()) {
                encode(limit.getType().getNumber());
                encode(limit.getLimit());
            }
            encode(rep.getAmount());
        }

        encode(tx.getInitiator());
        encode(tx.getAuthRequireList().size());
        for (String addr : tx.getAuthRequireList()) {
            encode(addr);
        }

        if (needSign) {
            encode(tx.getInitiatorSignsCount());

            for (int i = 0; i < tx.getInitiatorSignsCount(); i++) {
                encode(tx.getInitiatorSigns(i).getPublicKey());
                encode(tx.getInitiatorSigns(i).getSign());
            }

            encode(tx.getAuthRequireSignsCount());
            for (int i = 0; i < tx.getAuthRequireSignsCount(); i++) {
                encode(tx.getAuthRequireSigns(i).getPublicKey());
                encode(tx.getAuthRequireSigns(i).getSign());
            }

            encode(tx.getXuperSign().getPublicKeysList().size());
            for (int i = 0; i < tx.getXuperSign().getPublicKeysCount(); i++) {
                encode(tx.getXuperSign().getPublicKeys(i));
            }
            encode(tx.getXuperSign().getSignature());
        }

        encode(tx.getHDInfo().getHdPublicKey());
        encode(tx.getHDInfo().getOriginalHash());

        byte[] result = new byte[0];
        try {
            result = buffer.toBytes().toArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Hash.doubleSha256(result);
    }
}
