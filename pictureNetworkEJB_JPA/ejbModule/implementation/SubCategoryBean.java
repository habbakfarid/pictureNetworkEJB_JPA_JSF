package implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import entities.Category;
import entities.Comment;
import entities.SubCategory;
import interfaces.SubCategoryBeanLocal;
import interfaces.SubCategoryBeanRemote;

@Stateless
public class SubCategoryBean implements SubCategoryBeanLocal,SubCategoryBeanRemote {

	
	@PersistenceUnit(name="pictureNetwork")
	private EntityManagerFactory emf;
	private EntityManager em;
	 private void begin()
	    {
	    	if(emf != null)
			{
				em = emf.createEntityManager();
			}
	    }
	 
	@Override
	public boolean addSubCategory(SubCategory subcategory) {
		// TODO Auto-generated method stub
		begin();
		em.persist(subcategory);
		
		return false;
	}

	@Override
	public boolean updateSubCategory(SubCategory subcategory) {
		// TODO Auto-generated method stub
		begin();
		em.merge(subcategory);
		
		return false;
	}

	@Override
	public boolean removeSubCategory(SubCategory subcategory) {
		// TODO Auto-generated method stub
		begin();
		em.remove( em.contains(subcategory) ? subcategory : em.merge(subcategory));
		return false;
	}

	@Override
	public boolean removeIdSubCategory(int id) {
		begin();
		
		SubCategory m = findSubCategoryA(id);
		
			em.remove(em.contains(m)? m : em.merge(m));
		
	
	return false;
	}



	@Override
	public List<SubCategory> findAllSubCategory() {
		begin();
		return em.createQuery("select u from SubCategory u").getResultList();
	}





	@Override
	public SubCategory findSubCategoryA(int id) {
		begin();
		
		return em.find(SubCategory.class, id);
	}

	@Override
	public boolean subcategoryExists(String name) {
		// TODO Auto-generated method stub
		begin();
		SubCategory c = null;
		if(em != null)
		{
			String query = String.format("select c from SubCategory c where c.name = '%s'", name);
			try
			{
			c = (SubCategory) em.createQuery(query).getSingleResult();
			}
			catch(Exception ex)
			{
				return false;
			}
			if( c != null)
			{
				return true;
			}
			return false;
		}
		return true;
	}



	

}
