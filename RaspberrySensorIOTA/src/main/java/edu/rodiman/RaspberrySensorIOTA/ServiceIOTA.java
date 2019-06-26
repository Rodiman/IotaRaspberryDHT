package edu.rodiman.RaspberrySensorIOTA;

import java.util.ArrayList;
import java.util.List;

import org.iota.jota.IotaAPI;
import org.iota.jota.dto.response.GetNodeInfoResponse;
import org.iota.jota.dto.response.GetTransferResponse;
import org.iota.jota.dto.response.SendTransferResponse;
import org.iota.jota.model.Bundle;
import org.iota.jota.model.Transaction;
import org.iota.jota.model.Transfer;
import org.iota.jota.utils.TrytesConverter;

public class ServiceIOTA {

    private static ServiceIOTA instance = null;

    private static IotaAPI api;

    // Address of the wallet to send data to
    private static final String IOTA_ADDRESS_TO = "address to send to";
    
    // Seed of the wallet to send/receive data
    private static final String SEED_FROM = "seed to send from";
    
    //An example of a list of nodes, to connect
    private static final String[] NODE_LIST = {"pow.iota.community","hanspetzersnode.org","nodes.thetangle.org","nodes.iota.cafe","perma.iota.partners",
    		"node.iota-tangle.io","node-iota.org","node04.iotatoken.nl","ultranode.iotatoken.nl","www.iotaqubic.us"};
    private static final int[] NODE_PORT_LIST = {443,14267,443,443,443,14265,14267,443,443,443};
    
    // Default tag for messages
    final String TAG = "RASPBERRY9RODIMAN9999999999";

    //------------------------------------------------------
    private ServiceIOTA() {
    	
    	int indexForConnection = 0;
    	
        while (establishConnection(indexForConnection) == null && indexForConnection < NODE_LIST.length)
        {
        	indexForConnection++;
        	System.out.println("Retrying new node...");
        }
        
        if (indexForConnection == NODE_LIST.length)
        	System.out.println("NOT Connected. None of the nodes were available at the time.");
        else
        	System.out.println("Connected.");
    }

    public static ServiceIOTA getInstance() {
        if (instance == null) {
            instance = new ServiceIOTA();
        }
        
        return instance;
    }

    //------------------------------------------------------

    private GetNodeInfoResponse establishConnection(int indexForConnection) {
        
    	//FIXME check connectivity, alter between different nodes
        GetNodeInfoResponse response = null;
    	try {
            api = new IotaAPI.Builder().protocol("https")
            		.host(NODE_LIST[indexForConnection])
            		.port(NODE_PORT_LIST[indexForConnection])
            		.build();
            response = api.getNodeInfo();
    	} catch (Exception e) {
    		System.out.println("Couldn't connect");
		}
        
        return response;
    }

    //------------------------------------------------------

    public SendTransferResponse send(String message) {

        // List of transfers to carry out
        List<Transfer> transferList = new ArrayList<Transfer>();

        Transfer t = new Transfer(IOTA_ADDRESS_TO, 0, TrytesConverter.asciiToTrytes(message), TAG);

        transferList.add(t);
        SendTransferResponse resp = api.sendTransfer(SEED_FROM,
                2,
                9,
                15,
                transferList,
                null,
                null,
                false, false, null);
        
        return resp;
    }

    public List<TransactionMessage> receive () {
        //---- I don't know how to get the address i sent my messages to...
        
        List<TransactionMessage> output = new ArrayList<>();

        GetTransferResponse transferResponse = api.getTransfers(SEED_FROM, 2, 0, 10, true);

        Bundle[] allTransfers = transferResponse.getTransfers();

        for (Bundle bundle : allTransfers) {
            List<Transaction> transactionsForAddress = bundle.getTransactions();
            for (Transaction transaction : transactionsForAddress) {
                TransactionMessage message = new TransactionMessage();
                message.address = transaction.getAddress();
                message.hash = transaction.getHash();
                message.bundle = transaction.getBundle();
                message.value = transaction.getValue();
                message.timestamp = transaction.getTimestamp();
                message.branch = transaction.getBranchTransaction();
                message.obstag = transaction.getObsoleteTag();
                message.trunk = transaction.getTrunkTransaction();
                message.signature = transaction.getSignatureFragments();
                message.tag = transaction.getTag();

                String trytes = transaction.getSignatureFragments().split("99999")[0];
                String data = TrytesConverter.trytesToAscii(trytes);

                message.data = data;

                output.add(message);
            }
        }

        return output;
    }
}
