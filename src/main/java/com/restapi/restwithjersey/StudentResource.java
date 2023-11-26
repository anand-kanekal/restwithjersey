package com.restapi.restwithjersey;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("students")
public class StudentResource {

	List<Student> students = new ArrayList<Student>();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Student> getStudents() {
		StudentDao studentDao = new StudentDao();
		return studentDao.getStudents();
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Student getStudent(@PathParam("id") int id) {
		StudentDao studentDao = new StudentDao();
		return studentDao.getStudent(id);
	}

	@Path("create")
	@POST
	public Student createStudent(Student student) {
		StudentDao studentDao = new StudentDao();
		return studentDao.create(student);
	}

	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(Student student) {
		if (student.getId() != 0) {
			StudentDao studentDao = new StudentDao();
			return studentDao.update(student);
		} else {
			return new Student();
		}
	}

	@DELETE
	@Path("delete/{id}")
	public Student deleteStudent(@PathParam("id") int id) {
		Student student = getStudent(id);

		if (student.getId() != 0) {
			StudentDao studentDao = new StudentDao();
			studentDao.delete(id);
			return student;
		} else {
			return new Student();
		}

	}

}
