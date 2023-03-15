package projects.service;


import projects.dao.ProjectDao;
import projects.entity.Project;



public class ProjectService {
	
	
	
	public static class projectDao {

		public static Project insertProject(Project project) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public Project addProject(Project project) {
		return projectDao.insertProject(project);
		
		
	}

public List<Project> fetchAllProjects() {
	return projectDao.fetchAllProjects();
	
}
	/**
	 * This method calls the project DAO to get all project details, including materials, steps, and categories.
	 * 
	 * @param projectId The Project ID.
	 * @return A project object if successful.
	 * @throws NoSuchElementExpectation Thrown if the project with the given ID does not exist.
	 * 
	 */
	
}
