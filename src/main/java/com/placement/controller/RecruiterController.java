package com.placement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.placement.entities.JobApplicant;
import com.placement.entities.Jobs;
import com.placement.entities.Recruiter;
import com.placement.repository.JobApplicantRepository;
import com.placement.repository.JobRepository;
import com.placement.repository.RecruiterRepository;
import com.placement.repository.StudentRepository;

@Controller
public class RecruiterController 
{
	static Path cwd = Path.of("").toAbsolutePath();
	private static Path UPLOADED_FOLDER = cwd;
	
	@Autowired
	RecruiterRepository repo;
	
	@Autowired
	JobRepository jrepo;
	
	@Autowired
	RecruiterRepository rrepo;
	PasswordEncoder passwordencoder;
	
	@Autowired
	JobApplicantRepository jarepo;
	
	@Autowired
	StudentRepository srepo;
	
	@RequestMapping("/recruiter/signup")
  	public String signup(Model model)
  	{
		Recruiter r=new Recruiter();
		model.addAttribute("recruiter", r);
  		return "recruiter/rsignup";
  	}
	
	@RequestMapping("/recruiter/save")
	public String save(@ModelAttribute("recruiter") Recruiter r)
	{
		String password=r.getPassword();
		this.passwordencoder= new BCryptPasswordEncoder();
		String hashPassword=passwordencoder.encode(password);
		r.setPassword(hashPassword);
		
		repo.save(r);
		return "recruiter/success";
	}
	
	@RequestMapping("/recruiter/signin")
	public String signin(Model model)
	{
		Recruiter r=new Recruiter();
		model.addAttribute("recruiter", r);
		return "recruiter/rsignin";
	}
	
	
	
	
	@RequestMapping(value="/rvalidation", method=RequestMethod.POST)
	public String rvalidation(@ModelAttribute("recruiter") Recruiter recruiter,HttpSession session,RedirectAttributes ra )
	{
		String email=recruiter.getEmail();
		String password=recruiter.getPassword();
		Recruiter r=repo.findByEmail(email);
		System.out.println(r);
		this.passwordencoder = new BCryptPasswordEncoder();  
		
		boolean isPasswordMatches = passwordencoder.matches(password,r.getPassword());
		System.out.println(isPasswordMatches);
		
		if(!isPasswordMatches)
		{
			ra.addFlashAttribute("emessage", "Id or password is incorrect");			
			return "redirect:/recruiter/signin";
		}
		else
		{
			session.setAttribute("rname",r.getRname());
			session.setAttribute("rid",r.getRid());
			session.setAttribute("logo",r.getLogo());
			session.setAttribute("status",true);
			return "recruiter/rdashboard";
		}
	}
	


	@RequestMapping("/rlogout")
	public String logout(RedirectAttributes ra,HttpServletRequest request)
	{    HttpSession session=request.getSession();  
    	 session.invalidate();  
		ra.addFlashAttribute("smessage", "You have been logged out successfully.");
		return "redirect:/recruiter/signin";
	}
	
	
	@RequestMapping("/addnewjob")
	public String addnewjobpageload(Model model,HttpSession session)
	{   
		Jobs j=new Jobs();
		model.addAttribute("job", j);
		return "recruiter/addjob";
	}
	
	@RequestMapping(value="/savejob",method =RequestMethod.POST)
	public String addnewjobpageload(@ModelAttribute("job") Jobs job,RedirectAttributes ra,HttpSession session)
	{  
		long rid=(long)session.getAttribute("rid");
		Recruiter r=rrepo.findById(rid).get();
		job.setRid(rid);
		job.setRlogo(r.getLogo());
		job.setStatus("true");
		jrepo.save(job);
		ra.addFlashAttribute("jsmessage", "New Job Created Successfully..!!!");
		return "redirect:/addnewjob";
		
		
	}
	
	
	@RequestMapping("/getjobs/{rid}")
	public String getJobByRecruiter(@PathVariable long rid,Model model)
	{   
		List<Jobs> jlist=jrepo.getJobsById(rid);
		model.addAttribute("jobs", jlist);
		Recruiter r=repo.findById(rid).get();
		model.addAttribute("recruiter", r);
		return "recruiter/joblist";
	}
	@RequestMapping(value="/ruploadlogo",method = RequestMethod.GET )
	public String loadpicuploadpage()
	{
		
		return "recruiter/ruploadpic";
		
	}
	
	@PostMapping("/rsavepic/{rid}") 
    public String picUpload(@RequestParam("logo") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@PathVariable long rid) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("emessage", "Please select a picture to upload");
            return "redirect:/ruploadlogo";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            
            Path path = Paths.get(UPLOADED_FOLDER+"/src/main/resources/static/img/" + file.getOriginalFilename());
            Files.write(path, bytes);
            String profile_pic=("../img/" + file.getOriginalFilename());
            redirectAttributes.addFlashAttribute("smessage",
                    "Picture successfully uploaded '" + file.getOriginalFilename() + "'");
            
            Recruiter r=repo.findById(rid).get();
            r.setLogo(profile_pic);
            repo.save(r);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/ruploadlogo";
    }
	
	@RequestMapping(value="/rcpassword/{rid}",method = RequestMethod.GET )
	public String loadpasswordpage(Model model)
	{
		Recruiter recruiter=new Recruiter();
		model.addAttribute("recruiter", recruiter);
		return "recruiter/rchangepassword";
		
	}
	 
	@RequestMapping(value="/rsavepassword/{rid}", method = RequestMethod.POST)
	public String rsavepassword(@ModelAttribute Recruiter recruiter, @PathVariable long rid,RedirectAttributes ra)
	{
		Recruiter r=rrepo.findById(rid).get();
		this.passwordencoder= new BCryptPasswordEncoder();
		r.setPassword(passwordencoder.encode(recruiter.getPassword()));

		rrepo.save(r);
		ra.addFlashAttribute("smessage", "Your password has been changed successfully!!");
		return "redirect:/rcpassword/{rid}";
		
		
	}
	

	
	@RequestMapping("/applicantlist/{rid}")	
	public String applicantList(@PathVariable long rid,Model model)
	{
		List<Jobs> jlist=jrepo.getJobsById(rid);
		model.addAttribute("jlist", jlist);
		return "/recruiter/applicantlist";
		
	}
	
	@RequestMapping("/fetchapplicant/{jid}")
	public String fetchapplicant(@PathVariable long jid,Model model)
	{
		List<JobApplicant> jalist=jarepo.getJobApplicantByJid(jid);
		model.addAttribute("jalist", jalist);
		JobApplicant jobapplicant=new JobApplicant();
		model.addAttribute("jobapplicant", jobapplicant);
		return "recruiter/fetchapplicant";
		
	}
	@RequestMapping("/updatejapp/{jaid}")
	public String updatejapp(@ModelAttribute("jobapplicant") JobApplicant jobapplicant,@PathVariable long jaid,RedirectAttributes ra)
	{
		JobApplicant ja=new JobApplicant();
		ja=jarepo.findById(jaid).get();
		ja.setJaid(jaid);
		ja.setStatus(jobapplicant.getStatus());
		ja.setRaction(jobapplicant.getRaction());
		System.out.println(ja);
		jarepo.save(ja);
		ra.addFlashAttribute("smessage", "Job status updategd successfully!!");
		return "redirect:/fetchapplicant/{jaid}";
	}
	

	@RequestMapping("/rdetails/{rid}")
	public String rdetail(@PathVariable long rid,Model model)
	{
		Recruiter r=rrepo.findById(rid).get();
		model.addAttribute("recruiter", r);
		return "/recruiter/rdetails";
	}
	@RequestMapping("/rupdate/{rid}")
	public String loadrupdatepage(@PathVariable long rid, Model model)
	{
		Recruiter recruiter=rrepo.findById(rid).get();
		model.addAttribute("recruiter", recruiter);
		return "/recruiter/rupdatedetails";
	}
	
	@RequestMapping(value="/rupdatesave/{rid}",method = RequestMethod.POST)
	public String rupdatesave(@ModelAttribute("recruiter") Recruiter recruiter, @PathVariable long rid,RedirectAttributes ra )
	{
		Recruiter r=rrepo.findById(rid).get();
		r.setAddress(recruiter.getAddress());
		r.setCompany(recruiter.getCompany());
		r.setEmail(recruiter.getEmail());
		r.setPno(recruiter.getPno());
		r.setRname(recruiter.getRname());
		r.setWebsite(recruiter.getWebsite());
		
		rrepo.save(r);
		ra.addFlashAttribute("smessage","Profile details updated Successfully !!");
		//System.out.println(recruiter);
		return "redirect:/rupdate/{rid}";
		
	}
	@RequestMapping("/rdelete/{rid}")
	public String stuDelete(@PathVariable long rid, RedirectAttributes ra)
	{
		repo.deleteById(rid);
		ra.addFlashAttribute("smessage", "Record Deleted Successfully");
		return "redirect:/rmanage";
	}
	
}
