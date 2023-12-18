package springmvc.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import springmvc.model.Account;

@Component
public class AccountsDao {

	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//create new account for a user id
	@Transactional
	public void addAccount(Account account)
	{
		this.hibernateTemplate.save(account);
	}
	
	public List<Account> getAccountsByUserId(int userId) {
	    CriteriaBuilder builder = this.hibernateTemplate.getSessionFactory().getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Account> query = builder.createQuery(Account.class);
	    Root<Account> root = query.from(Account.class);
	    query.select(root).where(builder.equal(root.get("userId"), userId));
	    return this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query).getResultList();
	}
	
	@Transactional
	public void removeAccountByAccId(int accId) {
	    Account accountToRemove = this.hibernateTemplate.get(Account.class, accId);
	    if (accountToRemove != null) {
	        this.hibernateTemplate.delete(accountToRemove);
	    }
	}
	
	
	@Transactional
	public List<Account> getAccountsByUserEmail(String userEmail) {
	    CriteriaBuilder builder = this.hibernateTemplate.getSessionFactory().getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Account> query = builder.createQuery(Account.class);
	    Root<Account> accountRoot = query.from(Account.class);

	    query.select(accountRoot).where(builder.equal(accountRoot.get("email"), userEmail));

	    return this.hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query).getResultList();
	}

	
	

}
