package project;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.service.ProjectService;
import projects.entity.Project;
import projects.exception.DbException;







public class ProjectsApp {
	private Scanner scanner = new Scanner(System.in);
	private Project curProject;
	private ProjectService projectService = new ProjectService();
	
 // @formatter:off
	private List<String> operations = List.of (
			"1) Add a project",
			"2) List projects",
			"3) Select a project",
			"4) Update project details",
			"5) Delete a project"
	);
	//@formatter:on
	
	
public static void main(String[] args) {
	new ProjectsApp().processUserSelection();
	}
	
private void processUserSelection() {
		// TODO Auto-generated method stub
		
	}




private void processUserSelection1() {
	boolean done = false;
	
	while(!done) {
		try {
	int selection = getUserSelection();
	
	
	switch(selection) {
	case -1:
	done = exitMenu();
	break;
	
	case 1:
	createProject();
	 break;
	 
	case 2:
	 listProjects();
	 break;
	 
	case 3:
		selectProject();
		break;
	
	case 4:
		updateProjectDetails();
		break;
	
	case 5:
		deleteProject();
		break;
		
	
	default:
		System.out.println("\n" + selection + "is not valid selection. Try again.");
	
	}
			
		}
		catch(Exception e) {
			System.out.println("\nError: + " + e + " Try again. ");
			
		}
	}
}
	
private void deleteProject() {
	// TODO Auto-generated method stub
	
}

private void listProjects() {
	// TODO Auto-generated method stub
	
}

private void selectProject() {
	// TODO Auto-generated method stub
	
}

private void updateProjectDetails() {
	// TODO Auto-generated method stub
	
}

private boolean exitMenu() {
	// TODO Auto-generated method stub
	return false;
}

private void createProject() {
	String projectName = getStringInput("Enter the project name");
	BigDecimal estimatedhours = getDecimalInput("Enter the estimated hours");
	BigDecimal actualhours = getDecimalInput("Enter the actual hours");
	Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
	String notes = getStringInput("Enter the project notes");
	
	
	Project project = new Project();
	
	project.setProjectName(projectName);
	project.setEstimatedHours(estimatedhours);
	project.setActualHours(actualhours);
	project.setDifficulty(difficulty);
	project.setNotes(notes);
	

	Project dbProject = projectService.addProject(project);
	System.out.println("You have successfully created project: " + dbProject);
	
	
}
private Integer getIntInput(String string) {
	// TODO Auto-generated method stub
	return null;
}

private String getStringInput(String string) {
	// TODO Auto-generated method stub
	return null;
}

private BigDecimal getDecimalInput(String prompt) {
	String input = getStringInput(prompt);
	if (Objects.isNull(input)) {
		return null; 
	}
	try {
		return new BigDecimal(input).setScale(2);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valid decimal number.");
	}
}



private int getUserSelection() {
	printOperations();
	
	Integer input = getIntInput("\n Enter a menu slection");
	
	return Objects.isNull(input) ? -1 : input;
	
}


private void printOperations() {
	System.out.println("\nThese are the available selections. Press the Enter key to quit:");
	
	
	operations.forEach(line -> System.out.println("  " + line));
}
private Integer getIntInput1(String prompt) {
	String input = getStringInput(prompt);
	
	if(Objects.isNull(input)) {
		return null;
	}
	try {
		return Integer.valueOf(input);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valud number.");
	}
}
private String getStringInput1(String prompt) {
	System.out.print(prompt +  " : ");
	String input = scanner.nextLine();
	
	return input.isBlank() ? null : input.trim();
}

private void listProjects1() {
	List<Project> projects = projectService.fetchAllProjects();
	
	System.out.println("\nProjects: ");
	
	projects.forEach(project -> System.out.println("  " + project.getProjectId() + ": " + project.getProjectName()));
	
}
private void selectProject1() {
 listProjects();
 Integer projectId = getIntInput("Enter a project ID yo select a project");
 
 /* Unselect the current project. */
 curProject = null;
 
 /* This will throw an exception if an invalid project ID is entered. */
 
 curProject = projectService.fetchAllProjectsById(projectId);

}

private void deleteProject1() {
	listProjects();
	
	Integer projectId = getIntInput("Enter the ID of the project to delete");
	
	projectService.deleteProject(projectId);
	System.out.println("Project " + projectId + " was deleted successfully.");
	
	if(Objects.nonNull(curProject) && curProject.getProjectId().equals(projectId)) {
		curProject = null;
	}
}





private void updateProjectDetails1() {
	if(Objects.isNull(curProject)) {
		System.out.println("\nPlease Select a project.");
	}


String projectName = getStringInput("Enter the project name [" + curProject.getProjectName() + "]");

Project project = new Project();
project.setProjectName(Objects.isNull(projectName) ? curProject.getProjectName() : projectName);


projectService.modifyProjectDetails(project);
curProject = projectService.fetchProjectById(curProject.getProjectId());
}

}


		
		
	


