package com.placement.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.placement.entities.JobApplicant;
import com.placement.entities.Jobs;
import com.placement.repository.JobApplicantRepository;
import com.placement.repository.JobRepository;
import com.placement.repository.RecruiterRepository;

@Controller
public class JobsController 
{
	
	@Autowired
	JobRepository jrepo;
	
	@Autowired
	JobApplicantRepository jarepo;
	
	@Autowired
	RecruiterRepository rrepo;
	
	@RequestMapping("/jobs")
	public String displayjobspage(Model model,HttpSession session)
	{
		List<Jobs> jlist=jrepo.findAll();
		model.addAttribute("jlist", jlist);
		JobApplicant ja=new JobApplicant();
		model.addAttribute("jobapplicant", ja);
		return "student/jobs";
	}
	
	@RequestMapping(value="/jobapply", method = RequestMethod.POST)
	public String jobapply(@ModelAttribute JobApplicant jobapplicant, RedirectAttributes ra) 
	{
		long sid=jobapplicant.getSid();
		long jid=jobapplicant.getJid();
		List<JobApplicant> ja= jarepo.getJobApplicantBySidandRid(sid,jid);
		
		if(ja.size()==0)
		{
		ra.addFlashAttribute("smessage","You have successfully applied for the job.");
		jarepo.save(jobapplicant);
		return "redirect:/jobs";
		}
		else
		{
			ra.addFlashAttribute("emessage","You have already applied for the job.");
			return "redirect:/jobs";
		}
	}
	
	
	@RequestMapping("/jdelete/{jid}")
	public String jobDelete(@PathVariable long jid,RedirectAttributes ra)
	{
		Jobs j = jrepo.findById(jid).get();
		long rid = j.getRid();
		jrepo.deleteById(jid);
		ra.addFlashAttribute("smessage", "Job deleted Successfully..!!");
		return "redirect:/getjobs/{rid}";
	}
	
	
	@RequestMapping("/jupdate/{jid}")
	public String jobUpdatePageLoad(@PathVariable long jid,Model model)
	{
		Jobs j=jrepo.findById(jid).get();
		model.addAttribute("job", j);
		return "recruiter/updatejob";
	}
	
	@RequestMapping("/jupdatesave/{rid}")
	public String jobUpdatesave(@ModelAttribute("job")Jobs job,Model model,@PathVariable long rid)
	{
		job.setRid(rid);
		jrepo.save(job);
		model.addAttribute("jsmessage", "Job details updated Successfully..!!");
		return "recruiter/updatejob";
	}
	
	@RequestMapping("/jobdelete/{jid}")
	public String jDelete(@PathVariable long jid, RedirectAttributes ra)
	{
		jrepo.deleteById(jid);
		ra.addFlashAttribute("smessage", "Record Deleted Successfully");
		return "redirect:/jmanage";
	}	

}
