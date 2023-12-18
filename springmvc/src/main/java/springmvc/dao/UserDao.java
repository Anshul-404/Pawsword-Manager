package springmvc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springmvc.model.User;


@Component
public class UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public int saveUser(User user)
	{
		int id = (Integer)this.hibernateTemplate.save(user);
		return id;
	}
	
	public User findByEmail(String email) {
		 String query = "SELECT u FROM User u WHERE u.email = :email";
		    TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
		    typedQuery.setParameter("email", email);
		    List<User> results = typedQuery.getResultList();
		    return results.isEmpty() ? null : results.get(0);
	}
	
	 public boolean isEmailUnique(String email) {
	        User existingUser = findByEmail(email);
	        return existingUser == null;
	    }
}
