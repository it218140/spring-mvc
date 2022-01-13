package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.ds.springmvcdemo1.entity.Subjects;

@Repository
public class SubjectsDAOImpl implements SubjectsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Subjects> getSubjects() {

		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session

		Query<Subjects> query = currentSession.createQuery("select u from Subjects u", Subjects.class);

		List<Subjects> req = query.getResultList();

		return req;
	}

	@Override
	public void InsertSubjects(Subjects subjects) {
		Session currentSession = this.sessionFactory.openSession();

		try {

			currentSession.beginTransaction();
			currentSession.save(subjects);

			currentSession.getTransaction().commit();

		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
			currentSession.getTransaction().rollback();

		} finally {

			currentSession.close();

		}

	}



	@Override
	public List<Subjects> getAllSubjectsByUsername(String username) {
		List<Subjects> subjects= new ArrayList<>();

		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session

		try {

			Query query = currentSession.createQuery("from Subjects where username=:username", Subjects.class); // Create a query

			query.setParameter("username", username);
			subjects = query.getResultList();

		} catch (Exception e) {

			System.out.println("exception: " + e.getMessage());

		} finally {

			currentSession.close(); // Close session
		}

		return subjects;

	}




	@Override
	public void UpdateSubject(int id, String state) {
		Session currentSession = this.sessionFactory.openSession();

		try {

			currentSession.beginTransaction();

			Subjects subjects = currentSession.get(Subjects.class, id);

			subjects.setState(state);
			currentSession.update(subjects);

			currentSession.getTransaction().commit();

			System.out.println("ok");

		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());

			currentSession.getTransaction().rollback();

		} finally {

			currentSession.close(); // Close session

		}

	}


}
