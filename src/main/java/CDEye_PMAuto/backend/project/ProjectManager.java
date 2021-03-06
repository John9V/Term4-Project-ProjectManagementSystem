package CDEye_PMAuto.backend.project;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Dependent
@Stateless
public class ProjectManager implements Serializable {

	@PersistenceContext(unitName="inventory-jpa") EntityManager em;
	
	public Project[] getAll() {
		TypedQuery<Project> query = em.createQuery("select p from Project p",
				Project.class);
		List<Project> projects = query.getResultList();
		Project[] projectArr = new Project[projects.size()];
        for (int i = 0; i < projectArr.length; i++) {
        	projectArr[i] = projects.get(i);
        }
        return projectArr;
	}

	public void updateProject(Project p) {
		em.merge(p);
	}
	
	public Project find(UUID projectId) {
		return em.find(Project.class, projectId);
	}
	
	public void persist(Project p) {
        em.persist(p);
    }
	
	public void delete(Project p) {
        em.remove(p);
    }
	
	public Project findProject(String projectName) {
		TypedQuery<Project> query = em.createQuery(
				"SELECT p FROM Project p WHERE p.projectName LIKE :projectName", Project.class)
				.setParameter("projectName", "%" + projectName + "%");
		List<Project> projects = query.getResultList();
		return projects.get(0);
	}
}
