package project;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import ProjectService.java.projectService;
import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;



public class ProjectsApp {

 // @formatter:off
	private List<String> operations = List.of (
			"1) Add a project"
	);
	//@formatter:on
	
	
public static void main(String[] args) {
	new ProjectsApp().processUserSelection();
	}
	
private void processUserSelection() {
		// TODO Auto-generated method stub
		
	}

private Scanner scanner = new Scanner(System.in);



private void processUserSelection1() {
	boolean done = false;
	
	while(!done) {
		try {
	int selection = processUserSelection();
	
	
	switch(selection) {
	case -1;
	done = exitMenu();
	break;
	
	case 1;
	createProject();
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
	
	ProjectService projectService;
	Project dbProject = projectService.addProject(project);
	System.out.println("You have successfully created project: " + dbProject);
	
	
}
private BigDecimal getDecimalInput() {
	
	try {
		return new BigDecimal(input).setScale(2);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valid decimal number.");
	}
}



private int getUserSelection() {
	printOperations();
	
	Integer input = getIntInput("Enter a menu slection");
	
	return Objects.isNull(input) ? -1 : input;
	
	}
private void printOperations() {
	System.out.println("\nThese are the available selections. Press the Enter key to quit:");
	
	
	operations.forEach(line -> System.out.println("  " + line));
}
private String getIntInput(String prompt) {
	String input = getIntInput(prompt);
	
	if(Objects.isNull(input)) {
		return null;
	}
	try {
		return String.valueOf(input);
	}
	catch(NumberFormatException e) {
		throw new DbException(input + " is not a valud number.");
	}
}
private String getStringInput(String prompt) {
	System.out.print(prompt +  " : ");
	String input = scanner.nextLine();
	
	return input.isBlank() ? null : input.trim();
}

}


		
		
	


