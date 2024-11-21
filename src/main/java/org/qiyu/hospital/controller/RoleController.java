package org.qiyu.hospital.controller;

import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.model.dto.RoleDTO;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色控制器
 */
@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoleController {
    private final RoleService roleService;

    /**
     * 获取角色列表
     *
     * @return 角色列表
     */
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<RoleDTO>>> getRoleList() {
        List<RoleDTO> roleList = roleService.getRoleList();
        return ResultUtil.success("获取角色列表成功", roleList);
    }


}
