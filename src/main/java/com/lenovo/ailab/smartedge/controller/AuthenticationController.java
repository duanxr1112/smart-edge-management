package com.lenovo.ailab.smartedge.controller;

import com.lenovo.ailab.smartedge.dao.po.Department;
import com.lenovo.ailab.smartedge.service.DepartmentService;
import com.lenovo.ailab.smartedge.utils.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author duanxr2
 * @Email duanxr2@lenovo.com
 * @Date：2020/2/20
 * @About: get jwttoken
 */


@CrossOrigin(origins = "*",allowCredentials="true",allowedHeaders = "*")
@RestController
@RequestMapping("/getToken")
public class AuthenticationController {

    @Autowired
    private DepartmentService departmentService;


    /**
     * @about select all department for nopage
     * @return
     */
    @RequestMapping(value = "/{lenovoToken}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8" })
    @ResponseBody
    public ResponseModel getJwttoken(@PathVariable(value = "lenovoToken", required = false) String lenovoToken) {

        return departmentService.getJwttoken(lenovoToken);
    }

}
