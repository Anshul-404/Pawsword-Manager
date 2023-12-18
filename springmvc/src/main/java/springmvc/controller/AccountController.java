package springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import springmvc.dao.AccountsDao;
import springmvc.model.Account;
import springmvc.model.User;
import springmvc.service.AccountService;
import springmvc.utility.Kms;

@Controller
public class AccountController {
	
	@Autowired
	private AccountsDao accountDao;
	@Autowired
	private AccountService accountService;
	
	
	@RequestMapping("/dashboard")
	public String showDashboard()
	{
		return "dashboard";
	}
	
	 public List<Account> loadAccounts(HttpSession session) {
	    // Retrieve the user ID from the session
		 int userId = (int) session.getAttribute("userId");
		 // Use the user ID to fetch the accounts
		 List<Account> accounts = accountDao.getAccountsByUserId(userId);
		 return accounts;
	    }

	 
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public RedirectView processAddAccount(@RequestBody Account account, Model model, HttpSession request)
	{
		Account acc = new Account();
		
		acc.setUrl(account.getUrl());
		acc.setEmail(account.getEmail());
		
		String encr = Kms.getEncryptedPassword(account.getPassword());
		
		acc.setPassword(encr);
		acc.setAccId(0);
		acc.setUserId(0);
		model.addAttribute(acc);
		
		this.accountService.createAccount(acc);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("dashboard");
		return redirectView;
	}
	
	@RequestMapping(value = "/removeAccount/{accountId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeAccount(@PathVariable Integer accountId) {
	    accountService.removeAccount(accountId);
	    return ResponseEntity.ok("Account removed successfully");
	}
}
