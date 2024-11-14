package org.qiyu.hospital.controller;

import com.xlf.utility.BaseResponse;
import com.xlf.utility.ErrorCode;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.vo.ConsoleUserAddVO;
import org.qiyu.hospital.model.vo.ConsoleUserEditVO;
import org.qiyu.hospital.service.TokenService;
import org.qiyu.hospital.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    /**
     * 获取用户列表
     * @return 用户列表
     */
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<UserDTO>>> getUserList() {
        List<UserDTO> userList = userService.getUserList();
        return ResultUtil.success("获取用户列表成功", userList);
    }

    /**
     * @param uuid 用户uuid
     *             删除用户
     */
    @DeleteMapping("/{uuid}")
    public ResponseEntity<BaseResponse<Void>> deleteUser(@PathVariable String uuid) {
        userService.deleteUser(uuid);
        return ResultUtil.success("删除用户成功");
    }

    /**
     * @param consoleUserAddVO
     * 添加用户
     */
    @PostMapping("/console/add")
    public ResponseEntity<BaseResponse<Void>> addUser(@RequestBody @Validated ConsoleUserAddVO consoleUserAddVO) {
        if (consoleUserAddVO.getEmail().isEmpty()) {
            consoleUserAddVO.setEmail(null);
        }
        userService.addUser(consoleUserAddVO);
        return ResultUtil.success("添加用户成功");

    }

    @PutMapping("/console/update")
    public ResponseEntity<BaseResponse<Void>> updateUser(@RequestBody @Validated ConsoleUserEditVO consoleUserEditVO) {
        userService.updateUser(consoleUserEditVO);
        return ResultUtil.success("更新用户成功");
    }

    @GetMapping("/current")
    public ResponseEntity<BaseResponse<UserDTO>> userCurrent(
            @NonNull HttpServletRequest request
    ) {
        String userToken = HeaderUtil.getAuthorizeUserUuidString(request);
        if (tokenService.verifyToken(userToken)) {
            UserDTO userDTO = userService.getUserByTokenUuid(userToken);
            return ResultUtil.success("获取用户信息成功", userDTO);
        } else {
            throw new BusinessException("用户未登录", ErrorCode.FORBIDDEN);
        }
    }

}


