package edu.rodiman.RaspberrySensorIOTA;

import org.iota.jota.IotaAPI;
import org.iota.jota.dto.response.GetNodeInfoResponse;

public class TestingCodes {

	public static void main(String[] args) {

		
		// XXX 1 - Connecting the API to a Node
		IotaAPI api = new IotaAPI.Builder()
		        .protocol("https").host("gewirr.com").port(14267).build();
//		        .protocol("https").host("node-iota.org").port(14267).build();
		
		GetNodeInfoResponse response = api.getNodeInfo();
		System.out.println("Version " + response.getAppVersion());
		
		
		// XXX 2 - Getting balance for an account
//		System.out.println(api.getBalance(100, "HWUYSMQM99PBOKXGZOLQNEWVUFKAONUZHYFEWLMCMMBKVDAGGFMIIYIIFGUWNKIDNMWAGALEWSOXLE9JWVBLVYOBIY")); 	// last address from the tangle monitor
		
		// XXX 3 - Getting objects from transactions (without seed)
//		String[] addresses = new String[2];
//		addresses[0] = "XYMI9FHKHTLBAKUJCAXSGZPPFGBZPYPVBOLZQINIHBSW9XFKBUKZJZWCCDWJFPOCZKVQKGDKYDKDOXPIYVYIMCYEXY";
//		addresses[1] = "IBMAQJHLBLDAKFVZYN9SESSIKKDHXCFBVOSEDDYJIPNDHWDBTIMOIMMKYDEKNAMIOUHBN9VTAC9TAZD9AWUFWGFUFB";
//		List<Transaction> transactionsForAddress = api.findTransactionObjectsByAddresses(addresses);
//		
//		for (Transaction transaction : transactionsForAddress)
//			System.out.println(transaction.getHash());

		
		// XXX 4 - Sending transfers
//		System.out.println("============================");
//		System.out.println("Trying to send a transfer...");
//		System.out.println("============================\n");
		// Seed for sending messages and transactions, no funds.
		// MAJGEUNWX SBECJ9XOU GEGWPEATH KAJBGNVKF MHTLCGOMK QBSYDA9NX X9UREDJ9B VHKGGALVB BMEHSC9QY
		
//		String seedForMessages = "Here the seed to send the transaction from a wallet"; // PUT HERE YOUR WALLET'S SEED money in this address
//		List<Transfer> transfers = new ArrayList<Transfer>();  // (1)
//		Transfer t = new Transfer("Here the address to send",  // (2) THE ADDRESS TO SEND TO, MADE FROM TRINITY.
//		        0, // (3)  VALUE 0, only text.
//		        TrytesConverter.asciiToTrytes("the message FROM JAVA is here"), // (4)
//		        "999999999999999999999999999"); // (5)
//		
//		// XXX POW is done by itself, it seems
//		
//		transfers.add(t);  // (6)
//		SendTransferResponse resp = api.sendTransfer(seedForMessages, // (7) THE SEED of the wallet that I am using, one without funds.
//		        2, // (8)
//		        9, // (9)
//		        15, // (10)
//		        transfers, // (11)
//		        null,  // (12)
//		        null,  // (13)
//		        false, false, null);
//
//		for (Boolean success : resp.getSuccessfully())
//			System.out.println("Success? " + success);
		
		
		// XXX 5 - Getting a new address to receive messages or money
//		GetNewAddressResponse resp = api.getNextAvailableAddress(seedForMessages, 2, false);
//		System.out.println("New address is: " + resp.first());
//		System.out.println("Character long: " + resp.first().length());
		
//		GetNewAddressResponse resp2 = api.generateNewAddresses(seedForMessages, 2, true, 3); //Checksum needs to be true, or the address will be too short
//		List<String> list = resp2.getAddresses();
//		for (String address : list) {
//			System.out.println("New address is: " + address);
//			System.out.println("Character long: " + address.length()); //To work properly is 90, not 81. The checksum is 9 characters
//		}
		
		// XXX 6 - Receiving transfers, with a Seed, so I can see the messages
//		System.out.println("Checking transactions for a seed...");
//		GetTransferResponse transfers = api.getTransfers(seedForMessages, 2, 0, 10, true); // 0 is start index and 1 end. I guess this is getting the last one or the first
//		
//		Bundle[] allTransfers = transfers.getTransfers();
//		System.out.println("Bundles are: " + allTransfers.length);
//		for (Bundle bundle : allTransfers) {
//			
//			List<Transaction> transactionsForAddress = bundle.getTransactions();
//			System.out.println("Transactions for bundle are: " + transactionsForAddress.size());
//			for (Transaction transaction : transactionsForAddress) {
//				System.out.println("=== Transfer found ===");
//				System.out.println("Address: " + transaction.getAddress());
//				System.out.println("Hash: " + transaction.getHash());
//				System.out.println("Bundle: " + transaction.getBundle());
//				System.out.println("Value of the transaction: " + transaction.getValue());
//				System.out.println("Timestamp: " + transaction.getTimestamp());
//				System.out.println("Nonce: " + transaction.getNonce());
//				System.out.println("Tag: " + transaction.getTag());
//				System.out.println("Signature: " + transaction.getSignatureFragments());
//				System.out.println(transaction.getBranchTransaction());
//				System.out.println(transaction.getObsoleteTag());
//				System.out.println(transaction.getTrunkTransaction());
//				System.out.println("=== Transfer ends ===");
//				
//				
//				System.out.println("==== The message: ====");
//				String trytes = transaction.getSignatureFragments().split("99999")[0]; // Removing the 9s at the end, to convert it back
//				System.out.println(TrytesConverter.trytesToAscii(trytes));
//			}
//		}
	
	}

}
