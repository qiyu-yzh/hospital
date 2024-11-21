package org.qiyu.hospital.config.aspect;

import com.xlf.utility.annotations.HasRole;
import com.xlf.utility.exception.library.ServerInternalErrorException;
import com.xlf.utility.exception.library.UserAuthenticationException;
import com.xlf.utility.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.qiyu.hospital.model.dto.UserDTO;
import org.qiyu.hospital.model.entity.RoleDO;
import org.qiyu.hospital.service.RoleService;
import org.qiyu.hospital.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 权限切面
 * <p>
 * 该类用于定义权限切面;
 * 该类使用 {@link Aspect} 注解标记;
 * 该类使用 {@link Component} 注解标记;
 * 该类使用 {@link RequiredArgsConstructor} 注解标记;
 * <p>
 * 该类用于检查权限；
 * 用于检查用户是否有权限访问；
 * 即检查用户是否登录。
 *
 * @author qiyu
 * @since v1.0.0
 * @version v1.0.0
 */
@Aspect
@Component
@RequiredArgsConstructor
public class PermissionAspect {
    private final UserService userService;
    private final RoleService roleService;

    @Before("@annotation(com.xlf.utility.annotations.HasAuthorize)")
    public void checkAuthorize() {
        // 截获 HttpServletRequest 对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();

            // 获取请求头中的令牌
            String userToken = HeaderUtil.getAuthorizeUserUuidString(request);
            userService.getUserByTokenUuid(userToken);
        } else {
            throw new ServerInternalErrorException("无法获取请求对象");
        }
    }

    /**
     * 检查角色
     * <p>
     * 该方法用于检查角色；用于检查用户是否有权限访问；
     * 即检查用户是否登录。
     * 该方法使用 {@link com.xlf.utility.annotations.HasRole} 注解标记；
     */
    @Before("@annotation(com.xlf.utility.annotations.HasRole)")
    public void checkRole(JoinPoint joinPoint) {
        // 截获 HttpServletRequest 对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();

            // 获取请求头中的令牌
            String userToken = HeaderUtil.getAuthorizeUserUuidString(request);
            if (userToken == null) {
                throw new UserAuthenticationException(UserAuthenticationException.ErrorType.USER_NOT_LOGIN, request);
            }
            UserDTO getUser = userService.getUserByTokenUuid(userToken);
            if (getUser == null) {
                throw new UserAuthenticationException(UserAuthenticationException.ErrorType.USER_NOT_LOGIN, request);
            }
            if (!roleService.checkRoleHasAdmin(getUser.getRole())) {
                RoleDO getRole = roleService.getRoleByUuid(getUser.getRole());
                // 提取 HasRole 注解中的角色
                HasRole hasRole = joinPoint.getTarget().getClass().getAnnotation(HasRole.class);
                if (hasRole != null) {
                    AtomicBoolean isValidate = new AtomicBoolean(false);
                    Arrays.stream(hasRole.value()).forEach(role -> {
                        if (role.equals(getRole.getRoleName())) {
                            isValidate.set(true);
                        }
                    });
                    if (!isValidate.get()) {
                        throw new UserAuthenticationException(UserAuthenticationException.ErrorType.PERMISSION_DENIED, request);
                    }
                }
            }
        } else {
            throw new ServerInternalErrorException("无法获取请求对象");
        }
    }
}
