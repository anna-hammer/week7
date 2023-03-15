package projects.dao;

import java.lang.reflect.GenericDeclaration;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import projects.exception.DbException;
import provided.util.DaoBase;
import java.sql.ResultSet;


public class ProjectDao {

	
	
	
private static final String CATEGORY_TABLE = "category";
private static final String MATERIAL_TABLE = "material";
private static final String PROJECT_TABLE = "project";
private static final String PROJECT_CATEGORY_TABLE = "project_category";
private static final String STEP_TABLE = "step";


public Project insertProject(Project project) {
	// @formatter:off
	String sql = ""
	   + "INSERT INTO " + PROJECT_TABLE + " "
	   + "(project_name, estimated_hours, actual_hours, difficulty, note) "
	   + "VALUES "
	   + "(?, ?, ?, ?, ?)";
	// @formatter:on
	
	try(Connection conn= DbConnection.getConnection()) {
		startTransaction(conn);
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, project.getProjectName(), String.class);
			setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
			setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
			setParameter(stmt, 4, project.getDifficulty(), Integer.class);
			setParameter(stmt, 5, project.getNotes(), String.class);
			
			stmt.executeUpdate();
			
			Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
			commitTransaction(conn);
			
			project.setProjectId(projectId);
			return project;
			
			
		}
		catch(Exception e) {
			rollbackTransaction(conn);
			throw new DbException(e);
			
		}
	}
	catch(SQLException e) {
		throw new DbException(e);
		
	}
}
 public List<Project> fetchAllProjects() {
	String sql = "SELECT * FROM " + PROJECT_TABLE + " ORDER BY project_name";
	
	try(Connection conn = DbConnection.getConnection())	{
		startTransaction(conn);
		
	try(PreparedStatement stmt = conn.prepareStatement(sql)) {
		try(ResultSet rs = stmt.executeQuery()) {
			List<Project> projects = new LinkedList<>();
		
		while(rs.next()) {
			projects.add(extract(rs, Project.class));
			
		}
		return projects;
		
		}
	} catch (SQLException e) {
		rollbackTransaction(conn);
		throw new DbException();
	}
 		} catch (SQLException e) {
 		throw new DbException(e);
	}
	
}
 
 public Optional<Project> fetchProjectById(Integer projectId) {
	 String sql = "SELECT * FROM " + PROJECT_TABLE + " WHERE project_id = ?";
	 
	 try(Connection conn = DbConnection.getConnection()) {
		 startTransaction(conn);
		 
		try {
			Project project = null;
			
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, projectId, Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					project = extract(rs, Project.class);
				}
			}
		}
		if(Objects.nonNull(project)) {
			project.getMaterials().addAll(fetchMaterialsForProject(conn, projectId));
			project.getSteps().addAll(fetchStepsForProject(conn, projectId));
			project.getCategories().addAll(fetchCategoriesForProject1(conn, projectId));
		}
		commitTransaction(conn);
		
		return Optional.ofNullable(project);	
		}
		catch(Exception e) {
			rollbackTransaction(conn);
			throw new DbException(e);
			
	 }
 }
	  catch(SQLException e) {
		  throw new DbException(e);
	  }
 }
 
 private List<Category> fetchCategoriesForProject1(Connection conn, Integer projectId) throws SQLException {
	 // @formatter:off
	 String sql = ""
			 + "SELECT c.* FROM " + CATEGORY_TABLE + " c " + "JOIN " + PROJECT_CATEGORY_TABLE + " pc USING (category_id) " 
			 + "WHERE project_id = ?";
	 // @formatter:on
	 
	 try(PreparedStatement stmt = conn.prepareStatement(sql)) {
		 setParameter(stmt, 1, projectId, Integer.class);
		 
	try(ResultSet rs = stmt.executeQuery()) {
		List<Category> categories = new LinkedList<>();
		
		while(rs.next()) {
			categories.add(extract(rs, Category.class));
		}
		return categories;
		
	}
	 }
 }



private List<Step> fetchStepsforProject(Connection conn, Integer projectId) throws SQLException {
	String sql = "SELECT * FROM " + STEP_TABLE + " WHERE project_id = ? ";
	try(PreparedStatement stmt = conn.prepareStatement(sql)) {
		setParameter(stmt, 1, projectId, Integer.class);
		
	try(ResultSet rs = stmt.executeQuery()) {
		List<Step> steps = new LinkedList<>();
		
		while(rs.next()) {
			steps.add(extract(rs, Step.class));
		}
		return steps;
		}
	}
	}
	// TODO Auto-generated method stub


private Collection<? extends Category> fetchCategoriesForProject(Connection conn, Integer projectId) {
	// TODO Auto-generated method stub
	return null;
}
private Collection<? extends Step> fetchStepsForProject(Connection conn, Integer projectId) {
	// TODO Auto-generated method stub
	return null;
}
private Collection<? extends Material> fetchMaterialsForProject(Connection conn, Integer projectId) {
	// TODO Auto-generated method stub
	return null;
}
private Project extract(ResultSet rs, GenericDeclaration class1) {
	// TODO Auto-generated method stub
	return null;
}


private void rollbackTransaction(Connection conn) {
	// TODO Auto-generated method stub
	
}


private void commitTransaction(Connection conn) {
	// TODO Auto-generated method stub
	
}


private Integer getLastInsertId(Connection conn, String projectTable) {
	// TODO Auto-generated method stub
	return null;
}


private void startTransaction(Connection conn) {
	// TODO Auto-generated method stub
	
}


private void setParameter(PreparedStatement stmt, int i, String notes, Class<String> class1) {
	// TODO Auto-generated method stub
	
}


private void setParameter(PreparedStatement stmt, int i, Integer difficulty, Class<Integer> class1) {
	// TODO Auto-generated method stub
	
}


private void setParameter(PreparedStatement stmt, int i, BigDecimal actualHours, Class<BigDecimal> class1) {
	// TODO Auto-generated method stub
 
	

}
public Object fetchProjectById1(Integer projectId) {
	// TODO Auto-generated method stub
	return null;
}
private List<Material> fetchMaterialsForProject1(Connection conn, Integer projectId) throws SQLException {
	return null;
	
}
public boolean modifyProjectDetails(Project project) {
	// @formatter:off
	String sql = ""
		+ "UPDATE " + PROJECT_TABLE + " SET "
		+ "project_name = ?, "
		+ "estimated_hours = ?, "
		+ "actual_hours = ?, "
		+ "difficulty = ?, "
		+ "notes = ? "
		+ "WHERE project_id = ?";
	//@formatter:on
	
	try(Connection conn = DbConnection.getConnection()) {
		startTransaction(conn);
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, project.getProjectName(), String.class);
			setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
			setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
			setParameter(stmt, 4, project.getDifficulty(), Integer.class);
			setParameter(stmt, 5, project.getNotes(), String.class);
			setParameter(stmt, 6, project.getProjectId(), Integer.class);
			
			
		boolean modified = stmt.executeUpdate() == 1;
		commitTransaction(conn);
		
		return modified;
			
		}
		catch(Exception e) {
			rollbackTransaction(conn);
			throw new DbException(e);
		}
	}
	
	catch(SQLException e) {
		throw new DbException(e);
		
	}
}
public boolean deleteProject(Integer projectid) {
    String sql = "DELETE FROM " + PROJECT_TABLE + " WHERE project_id = ?";
    
    try(Connection conn = DbConnection.getConnection()) {
    	startTransaction(conn);
    	
    	try(PreparedStatement stmt = conn.prepareStatement(sql)) {
    		setParameter(stmt, 1, projectid, Integer.class);
    		
    		boolean deleted = stmt.executeUpdate() ==1;
    		
    		commitTransaction(conn);
    		return deleted;
    	}
    }
	catch(Exception e) {
		Connection conn;
		rollbackTransaction(conn);
		throw new DbException(e);
		
	}
}
		catch(SQLException e) {
		throw new DbException(e);
		
}
}
