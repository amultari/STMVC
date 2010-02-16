package it.prova.controller;


import it.prova.model.Editore;
import it.test.MyUtils;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.groovy.grails.commons.ApplicationHolder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EditoreController {

	@Autowired
	protected SessionFactory sessionFactory;

	@RequestMapping(value = "/editore.dispatch", method = RequestMethod.GET)
	public String list(@RequestParam(value = "offset", required = false) Integer offset, @RequestParam(value = "max", required = false) Integer max, ModelMap modelMap) {
		if (offset != null || max != null) {
			int sizeNo = max == null ? 10 : max.intValue();
			modelMap.addAttribute("editoreInstaceList", Editore.findAll(offset == null ? 0 : (offset.intValue() - 1) * sizeNo, sizeNo));
			//float nrOfPages = (float) Editore.count() / sizeNo;
			//modelMap.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
			modelMap.addAttribute("editoreInstanceTotal", Editore.count());
		} else {
			modelMap.addAttribute("editoreInstanceList", Editore.list());
			modelMap.addAttribute("editoreInstanceTotal", Editore.count());	
		}
		return "/editore/list";
	}

	@RequestMapping(value = "/editore/create.dispatch", method = RequestMethod.GET)
	public String create(ModelMap modelMap) {
		modelMap.addAttribute("editoreInstance", new Editore());
		return "/editore/create";
	}

	@RequestMapping(value = "/editore/save.dispatch", method = RequestMethod.POST)
	public String save(HttpServletRequest request, ModelMap modelMap) {
		ApplicationContext ctx = ApplicationHolder.getApplication().getMainContext();
		BeanFactory factory = (BeanFactory) ctx;
		Editore editore = (Editore)factory.getBean("editore");
		MyUtils.bindDataFromMap(editore, request);
		if (!editore.validate()) {
			modelMap.addAttribute("editoreInstance", editore);
			return "editore/create";
		}        
		Long id = editore.save();
		return "redirect:/editore/" + id + ".dispatch";
	}

	@RequestMapping(value = "/editore/{id}.dispatch", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("editoreInstance", Editore.get(id));
		return "/editore/show";
	}

	@RequestMapping(value = "/editore/edit/{id}.dispatch", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, ModelMap modelMap) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		modelMap.addAttribute("editoreInstance", Editore.get(id));
		return "/editore/edit";
	}

	@RequestMapping(value = "/editore/update.dispatch", method = RequestMethod.POST)
	public String update(@RequestParam("id") Long id, HttpServletRequest request, ModelMap modelMap) {

		Editore editore = Editore.get(id);
		if (editore == null) throw new IllegalArgumentException("A editore is required");
		MyUtils.bindDataFromMap(editore, request);
		if (!editore.validate()) {
			modelMap.addAttribute("editoreInstance", editore);
			return "/editore/update";
		}
		editore.update();
		return "redirect:/editore/" + editore.getId() + ".dispatch";
	}

	@RequestMapping(value = "/editore/delete/{id}.dispatch", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "offset", required = false) Integer offset, @RequestParam(value = "max", required = false) Integer max) {
		if (id == null) throw new IllegalArgumentException("An Identifier is required");
		Editore.get(id).delete();
		return "redirect:/editore.dispatch?offset=" + ((offset == null) ? "1" : offset.toString()) + "&max=" + ((max == null) ? "10" : max.toString());
	}
}