package com.restapi.restwithjersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
	
	Connection connection;

	List<Student> students = new ArrayList<Student>();

	public StudentDao() {
		String url = "jdbc:mysql://localhost:3306/restjersey";
		String username = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Student> getStudents() {
		String query = "select * from student";
		List<Student> students = new ArrayList<Student>();

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("SID"));
				student.setName(rs.getString("SNAME"));
				student.setMarks(rs.getInt("MARKS"));
				student.setGrade(rs.getString("GRADE"));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	public Student getStudent(int id) {
		String query = "select * from student where SID=" + id;
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			if (rs.next()) {
				Student student = new Student();
				student.setId(rs.getInt("SID"));
				student.setName(rs.getString("SNAME"));
				student.setMarks(rs.getInt("MARKS"));
				student.setGrade(rs.getString("GRADE"));
				return student;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Student();
	}

	public Student create(Student student) {
		String query = "insert into student values (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, student.getId());
			statement.setString(2, student.getName());
			statement.setInt(3, student.getMarks());
			statement.setString(4, student.getGrade());
			statement.executeUpdate();
			students.add(student);
			System.out.println("Student Created : " + student);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public Student update(Student student) {
		String query = "update student set MARKS=?, GRADE=? where SID=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, student.getMarks());
			statement.setString(2, student.getGrade());
			statement.setInt(3, student.getId());
			statement.executeUpdate();
			students.add(student);
			System.out.println("Student Created : " + student);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
		
	}

	public void delete(int id) {
		String query = "delete from student where SID=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
