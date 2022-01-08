package com.placement.controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.placement.entities.Admin;
import com.placement.entities.JobApplicant;
import com.placement.entities.Jobs;
import com.placement.entities.Recruiter;
import com.placement.entities.Student;
import com.placement.repository.AdminRepository;
import com.placement.repository.JobApplicantRepository;
import com.placement.repository.JobRepository;
import com.placement.repository.RecruiterRepository;
import com.placement.repository.StudentRepository;

@Controller
public class AdminController 
{
	
	static Path cwd = Path.of("").toAbsolutePath();
	 
	@SuppressWarnings("unused")
	private static Path UPLOADED_FOLDER = cwd;
	
	@Autowired
	AdminRepository repo;
	
	@Autowired
	RecruiterRepository rrepo;
	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	JobRepository jrepo;
	
	@Autowired
	JobApplicantRepository jarepo;
	
	@Autowired
	AdminRepository arepo;
	
	@RequestMapping("/admin")
	public String loadSigninPage(Model model)
	{
		Admin admin=new Admin();
		model.addAttribute("admin", admin);
		return "admin/asignin";
		
	}
	
	@RequestMapping("/adashboard")
	public String showdashboard()
	{
		return "admin/adashboard";
		
	}
	
	@RequestMapping(value="/avalidation", method=RequestMethod.POST)
	public String avalidation(@ModelAttribute("admin") Admin admin,HttpSession session,RedirectAttributes ra )
	{
		String email=admin.getEmail();
		String password=admin.getPassword();
		Admin a=repo.findByEmailAndPassword(email, password);

		if(Objects.isNull(a))
		{
			ra.addFlashAttribute("emessage", "Id or password is incorrect");			
			return "redirect:/admin";
		}
		else
		{
			session.setAttribute("admin",a);			
			return "redirect:/adashboard";
		}
	}
	
	@RequestMapping("/alogout")
	public String logout(RedirectAttributes ra)
	{   ra.addFlashAttribute("smessage", "You have been logged out successfully.");
		return "redirect:/admin";
	}
	
	
	
	@RequestMapping("/smanage")
	public String smanage(Model model)
	{
		Iterable<Student> iterable=srepo.findAll();
		Iterator<Student> iterator=iterable.iterator();
		List<Student> slist=new ArrayList<Student>();
		while(iterator.hasNext())
		{
			slist.add(iterator.next());
		}
		
		model.addAttribute("slist", slist);
		return "admin/smanage";
	}
	
	
	@RequestMapping("/rmanage")
	public String rmanage(Model model)
	{
		Iterable<Recruiter> iterable=rrepo.findAll();
		Iterator<Recruiter> iterator=iterable.iterator();
		List<Recruiter> rlist=new ArrayList<Recruiter>();
		while(iterator.hasNext())
		{
			rlist.add(iterator.next());
		}
		
		model.addAttribute("rlist", rlist);
		return "admin/rmanage";
	}
	

	@RequestMapping("/jmanage")
	public String jmanage(Model model)
	{
		Iterable<Jobs> iterable=jrepo.findAll();
		Iterator<Jobs> iterator=iterable.iterator();
		List<Jobs> jlist=new ArrayList<Jobs>();
		while(iterator.hasNext())
		{
			jlist.add(iterator.next());
		}
		
		model.addAttribute("jlist", jlist);
		return "admin/jmanage";
	}
	@RequestMapping("/placedlist")
	public String placedlist(Model model)
	{
		List<JobApplicant>  alist=jarepo.getPlacedApplicantList();
		model.addAttribute("alist", alist);
		return "admin/placed";
		
	}
	
	
	@RequestMapping("/nonplacedlist")
	public String nonplacedlist(Model model)
	{
		List<JobApplicant>  alist=jarepo.getnonPlacedApplicantList();
		model.addAttribute("alist", alist);
		return "admin/nonplaced";
		
	}
	
	
	@RequestMapping(value="/acpassword",method = RequestMethod.GET )
	public String loadpasswordpage(Model model)
	{
		Admin admin=new Admin();
		model.addAttribute("admin", admin);
		return "admin/achangepassword";
		
	}
	 
	@RequestMapping(value="/asavepassword/{aid}", method = RequestMethod.POST)
	public String asavepassword(@ModelAttribute Admin admin, @PathVariable long aid,RedirectAttributes ra)
	{
		Admin a=arepo.findById(aid).get();
		
		a.setPassword(admin.getPassword());

		arepo.save(a);
		ra.addFlashAttribute("smessage", "Your password has been changed successfully!!");
		return "redirect:/acpassword";
		
		
	}
	

	
	
	
}
