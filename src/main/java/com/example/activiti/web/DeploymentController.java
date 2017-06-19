package com.example.activiti.web;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class DeploymentController {

    @Autowired
    private ProcessEngine processEngine;

    @RequestMapping(path = "/versions")
    public List<String> listDeployments() {
        return processEngine
                .getRepositoryService()
                .createProcessDefinitionQuery()
                .list()
                .stream()
                .map(ProcessDefinition::getId)
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/versions/{id}")
    public ProcessDefinition getDeployment(@PathVariable("id") String id) {
        System.out.println(id);
        return processEngine
                .getRepositoryService()
                .createProcessDefinitionQuery().processDefinitionId(id).singleResult();
    }
}
