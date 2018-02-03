package tcloud.msg;

import java.util.Scanner;

import tcloud.msg.model.User;
import tcloud.msg.model.Wallet;
import tcloud.msg.user.UserService;
import tcloud.msg.user.UserServiceImpl;
import tcloud.msg.wallet.impl.WalletServiceImpl;

public class WalletApp 
{
	enum Command {
		GetBalance;
	}
	
    public static void main( String[] args )
    {
        if(args.length != 2) {
        	System.out.println("Users' inital balance needs to be initialized. Format = java WalletApp userA,userB 10,20");
        	return;
        }
        
        WalletApp walletApp = new WalletApp();
        
        UserService  userService = new UserServiceImpl();
        
        WalletService walletService = new WalletServiceImpl(userService);
        
        // init users from command line
        walletApp.initUsers(args[0], args[1], userService);
        
        Scanner sc=new Scanner(System.in);
        User sessionOwner = null; // identify the session owner
        while(true) {
	        System.out.println("Identify yourself:");
	        String command = sc.nextLine();
	        sessionOwner = userService.get(command);
	        if(sessionOwner == null) {
	        	System.out.println("User not found, please identity");
	        	continue;
	        }
	        break;
        }
        
        while(true) {
        	try {
        		System.out.println("Enter your command");  
        		String command = sc.nextLine();
        		if(command.startsWith("GetBalance")) {
        			System.out.println("Balance = " + walletService.getBalance(sessionOwner.getUserId()));
        		}
        		if(command.startsWith("RequestPayment")) {
        			String[] commandValues = command.split(",");
        			String user = commandValues[1];
        			double amount = Double.parseDouble(commandValues[2]);
        			System.out.println("Success = " + walletService.requestPayment(sessionOwner.getUserId(), user, amount));
        		}
        		if(command.startsWith("AmountOwed")) {
        			System.out.println("Amount Owed = " +walletService.getAmountToBePayedOut(sessionOwner.getUserId()));
        		}
        		if(command.startsWith("AmountToBeReceived")) {
        			System.out.println("Amount To Be Received = " +walletService.getAmountToBeReceived(sessionOwner.getUserId()));
        		}
        	}catch(Exception e) {
        		System.out.println("Error = " + e.getMessage());
        		e.printStackTrace();
        	}
        	
        }
        
    }
    
    private  void initUsers(String users, String balances, UserService userService) {
    	String[] userList = users.split(",");
    	String[] balanceList = balances.split(",");
    	if(userList.length != balanceList.length) {
    		throw new IllegalArgumentException("users, balances does not match");
    	}
    	for(int i=0; i<userList.length; i++) {
    		String userId = userList[i];
    		String balance = balanceList[i];
    		Wallet wallet = new Wallet(userId, Double.parseDouble(balance)); // using userId for wallet as well.
    		User user = new User(userId, wallet);
    		userService.addUser(user);
    	}
    }
}
