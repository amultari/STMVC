@artifact.package@import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class @artifact.name@ {
	
	private Long id;
	private Long version;
	
	@Autowired
	private Validator validator;
	
	@Transient
	private List<ObjectError> domainErrors;
	
	public @artifact.name@() {
		
	}
	
	public @artifact.name@(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
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
		return "@artifact.name@:" + id;
	}
	
	public boolean validate() {
		BindingResult errors = new BeanPropertyBindingResult(this, "@artifact.name@");
		validator.validate(this, errors);
		domainErrors = errors.getAllErrors();
		return (domainErrors.isEmpty());
	}
	
	public static @artifact.name@ get(Long id) {
		return (@artifact.name@) HibernateUtil.sessionFactory().getCurrentSession().get(@artifact.name@.class, id);
	}
	
	public static Set<@artifact.name@> list() {
		// qui bisogna fare una query...
		return new HashSet<@artifact.name@>();
	}
	
	public static Set<@artifact.name@> findAll(int offset, int max) {
		// qui bisogna fare una query...
		return new HashSet<@artifact.name@>();
	}
	
	public static int count() {
		// qui bisogna fare una query...
		return 0;
	}
		
	public Long save() {
		return (Long) HibernateUtil.sessionFactory().getCurrentSession().save(this);
	}
	
	public @artifact.name@ update() {
		return (@artifact.name@) HibernateUtil.sessionFactory().getCurrentSession().merge(this);
	}
	
	public void delete() {
		HibernateUtil.sessionFactory().getCurrentSession().delete(this);
	}
}
