package gr.hua.dit.ds.springmvcdemo1.dao;

import gr.hua.dit.ds.springmvcdemo1.entity.Subjects;

import java.util.List;

public interface SubjectsDAO {

	public List<Subjects> getSubjects();

	public List<Subjects> getAllSubjectsByUsername(String username);

	public void InsertSubjects(Subjects subjects);



	public void UpdateSubject(int id, String state);

}