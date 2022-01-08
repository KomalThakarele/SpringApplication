package com.placement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.placement.entities.JobApplicant;
import com.placement.entities.Student;
import com.placement.repository.JobApplicantRepository;
import com.placement.repository.StudentRepository;



@Controller
public class HomeController 
{
	static Path cwd = Path.of("").toAbsolutePath();
	private static Path UPLOADED_FOLDER = cwd;

	@Autowired
	StudentRepository repo;
	
	@Autowired
	JobApplicantRepository jarepo;
	
	@RequestMapping("/")
	public String home()
	{
		return "index";
	}
	
	
	@RequestMapping("/student/signup")
	public String signup(Model model)
	{
		Student s=new Student();
		model.addAttribute("student", s);
		return "student/signup";
	}
	
	@RequestMapping("/student/signin")
	public String signin(Model model)
	{
		Student s=new Student();
		model.addAttribute("student", s);
		return "student/signin";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("student") Student student )
	{
		repo.save(student);
		return "student/success";
		
	}
	@RequestMapping(value="/validation", method=RequestMethod.POST)
	public String validation(@ModelAttribute("student") Student student,HttpSession session,RedirectAttributes ra,Model model )
	{
		String email=student.getEmail();
		String password=student.getPassword();
		Student s=repo.findByEmailAndPassword(email, password);
		
		if(Objects.isNull(s))
		{
			ra.addFlashAttribute("emessage", "Id or password is incorrect");			
			return "redirect:/student/signin";
		}
		else
		{
			session.setAttribute("sname",s.getSname());
			session.setAttribute("sid",s.getSid());
			session.setAttribute("status",true);
			model.addAttribute("student", s);
			return "student/sdashboard";
		}
	}
	
	
	@RequestMapping("/logout")
	public String logout(RedirectAttributes ra)
	{   ra.addFlashAttribute("smessage", "You have been logged out successfully.");
		return "redirect:/student/signin";
	}
	
	@RequestMapping("/sdetails/{sid}")
	public String studentDetails(@PathVariable (value="sid") long sid, Model model)
	{
		Student s=repo.findById(sid).get();
		model.addAttribute("student", s);
		return "student/sdetails";
	}
	
	@RequestMapping("/update/{sid}")
	public String studentupdate(@PathVariable (value="sid") long sid, Model model)
	{
		Student s=repo.findById(sid).get();
		model.addAttribute("student", s);
		return "student/updatedetails";
	}
	
	@RequestMapping(value="/update/{sid}",method = RequestMethod.POST)
	public String update(@ModelAttribute("student") Student student,@PathVariable long sid)
	{
	
		student.setSid(sid);
		repo.save(student);
		return "student/sdetails";
		
	}
	
	@RequestMapping(value="/cpassword",method = RequestMethod.GET )
	public String loadpasswordpage(@ModelAttribute("student") Student student,Model model)
	{
		
		
		model.addAttribute("student", student);
		return "student/changepassword";
		
	}
	 
	
	@RequestMapping(value="/savepassword/{sid}",method = RequestMethod.POST )
	public String cpassword(@ModelAttribute("student") Student student,@PathVariable (value="sid") long sid,RedirectAttributes ra)
	{
		Student s=repo.findById(sid).get();		
		s.setPassword(student.getPassword());
		repo.save(s);
		ra.addFlashAttribute("changepass","Password changed successfully");
		return "redirect:/cpassword";
		
	}
	
	
	
	
	@RequestMapping(value="/uploadresume",method = RequestMethod.GET )
	public String loaduploadpage()
	{
		
		return "student/uploadresume";
		
	}
	
	
	@PostMapping("/saveresume/{sid}") 
    public String singleFileUpload(@RequestParam("cv_url") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@PathVariable long sid) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("emessage", "Please select a file to upload");
            return "redirect:/uploadresume";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER+"/src/main/resources/static/resume/" + file.getOriginalFilename());
            Files.write(path, bytes);
            String cv_url=file.getOriginalFilename();
            redirectAttributes.addFlashAttribute("smessage",
                    "Resume successfully uploaded '" + file.getOriginalFilename() + "'");
            
            Student s=repo.findById(sid).get();
            s.setCv_url(cv_url);
            repo.save(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadresume";
    }

    
	@RequestMapping(value="/uploadpic",method = RequestMethod.GET )
	public String loadpicuploadpage()
	{
		
		return "student/uploadpic";
		
	}
	
	@PostMapping("/savepic/{sid}") 
    public String picUpload(@RequestParam("profile_pic") MultipartFile file,
                                   RedirectAttributes redirectAttributes,@PathVariable long sid) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("emessage", "Please select a picture to upload");
            return "redirect:uploadresume";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER+"/src/main/resources/static/profile/" + file.getOriginalFilename());
            Files.write(path, bytes);
            String profile_pic=file.getOriginalFilename();
            redirectAttributes.addFlashAttribute("smessage",
                    "Picture successfully uploaded '" + file.getOriginalFilename() + "'");
            
            Student s=repo.findById(sid).get();
            s.setProfile_pic(profile_pic);
            repo.save(s);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadpic";
    }
	
	@RequestMapping("/viewresume/{sid}")
	public String viewresume(@PathVariable long sid,Model model)
	{
		Student s=repo.findById(sid).get();
		model.addAttribute("student", s);
		return "student/viewresume";
	}
	@RequestMapping("/appliedjobs/{sid}")
	public String appliedJobs(@PathVariable long sid,Model model)
	{
		List<JobApplicant> jalist=jarepo.getJobApplicantBySid(sid);
		model.addAttribute("jalist", jalist);
		return "student/appliedjobs";
	}
	
	
	@RequestMapping("/getresume/{sid}")
	@ResponseBody
	public String getresume(@PathVariable (value="sid") long sid, Model model)
	{
		Student s=repo.findById(sid).get();
		
		return s.getCv_url();
	}
	
	@RequestMapping("/candidateslist")
	public String candidateList(Model model)
	{
		Iterable<Student> iterable=repo.findAll();
		Iterator<Student> iterator=iterable.iterator();
		List<Student> slist=new ArrayList<Student>();
		while(iterator.hasNext())
		{
			slist.add(iterator.next());
		}
		
		model.addAttribute("slist", slist);
		return "/recruiter/candidateslist";
	}
	
	@RequestMapping("/sdelete/{sid}")
	public String stuDelete(@PathVariable long sid, RedirectAttributes ra)
	{
		repo.deleteById(sid);
		ra.addFlashAttribute("smessage", "Record Deleted Successfully");
		return "redirect:/smanage";
	}
	
	
	
}
