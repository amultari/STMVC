import java.util.List;

import it.prova.util.HibernateUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Entity
public class Unmapped {
	
	private Long id;
	private Long version;
	
	@Autowired
	private Validator validator;
	
	@Transient
	private List<ObjectError> domainErrors;
	
	public Unmapped() {
		
	}
	
	public Unmapped(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	@Column(name = "version")
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	public String toString() {
		return "Unmapped:" + id;
	}
	
	public boolean validate() {
		BindingResult errors = new BeanPropertyBindingResult(this, "Unmapped");
		validator.validate(this, errors);
		return (domainErrors.isEmpty());
	}
	
	public static Unmapped get(Long id) {
		return (Unmapped) HibernateUtil.sessionFactory().getCurrentSession().get(Unmapped.class, id);
	}
		
	public Unmapped save() {
		return (Unmapped) HibernateUtil.sessionFactory().getCurrentSession().save(this);
	}
	
	public void delete() {
		HibernateUtil.sessionFactory().getCurrentSession().delete(this);
	}
}
