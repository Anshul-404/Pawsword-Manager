package springmvc.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import springmvc.utility.utility;
import org.springframework.stereotype.Service;

import springmvc.dao.UserDao;
import springmvc.dao.AccountsDao;
import springmvc.model.Account;
import springmvc.model.User;
import springmvc.utility.Kms;

@Service
public class AccountService {

	@Autowired
	private AccountsDao account;
	
    public List<Account> getUserWebsites(String emailId) {
    	
    	List<Account> list = account.getAccountsByUserEmail(emailId);
    	
    	for(Account acc : list)
    		acc.setPassword(Kms.getDecryptedPassword(acc.getPassword()));
    	
    	return list;
    }
    
    public int createAccount(Account acc) {
    	this.account.addAccount(acc);
    	return 0;
    }
	
    public void removeAccount(int acc) {
    	this.account.removeAccountByAccId(acc);
    }
}
