package com.application.jobapp.job.impl;

import com.application.jobapp.job.Job;
import com.application.jobapp.job.JobRepository;
import com.application.jobapp.job.JobService;
import org.jspecify.annotations.NonNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceimpl implements JobService {
//    private List<Job> jobs =  new ArrayList<Job>();
//    private static Long id = Long.valueOf(1);
    JobRepository jobRepository;
    public JobServiceimpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(@NonNull Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public boolean deleteJobById(Long id){
        try{
            jobRepository.deleteById(id);
            return true;
        } catch(EmptyResultDataAccessException e){
            return false;
        }
    }

    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job =  jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
