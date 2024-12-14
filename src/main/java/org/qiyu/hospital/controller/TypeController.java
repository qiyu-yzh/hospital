package org.qiyu.hospital.controller;

import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
import com.xlf.utility.annotations.HasAuthorize;
import com.xlf.utility.annotations.HasRole;
import lombok.RequiredArgsConstructor;
import org.qiyu.hospital.model.dto.TypeDTO;
import org.qiyu.hospital.model.entity.TypeDO;
import org.qiyu.hospital.model.vo.ConsoleTypeAddVO;
import org.qiyu.hospital.model.vo.ConsoleTypeEditVO;
import org.qiyu.hospital.service.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/type")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TypeController {
    private final TypeService typeService;


    /**
     * 获取类型列表
     *
     */


    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<TypeDTO>>> getTypeList() {
        List<TypeDTO> typeList = typeService.getTypeList();
        return ResultUtil.success("获取类型列表成功", typeList);
    }


    /**
     * @param uuid 类型uuid
     *             删除类型
     * @return
     */
    @HasAuthorize
    @HasRole({"ADMIN"})
    @DeleteMapping("/{uuid}")
    public ResponseEntity<BaseResponse<Void>> deleteType(@PathVariable String uuid) {
        typeService.deleteType(uuid);
        return ResultUtil.success("删除类型成功");
    }

    /**
     * @param consoleTypeAddVO
     * 添加类型
     * @return
     */
    @HasAuthorize
    @HasRole({"ADMIN"})
    @PostMapping("/console/add")
    public ResponseEntity<BaseResponse<Void>> addType(@RequestBody @Validated ConsoleTypeAddVO consoleTypeAddVO) {
        typeService.addType(consoleTypeAddVO);
        return ResultUtil.success("添加类型成功");
    }

    /**
     * @param consoleTypeEditVO
     * 更新类型
     * @return
     */
    @HasRole({"ADMIN"})
    @PutMapping("/console/update")
    public ResponseEntity<BaseResponse<Void>> updateType(@RequestBody @Validated ConsoleTypeEditVO consoleTypeEditVO) {
        typeService.updateType(consoleTypeEditVO);
        return ResultUtil.success("更新类型成功");
    }

    /**
     * @param type
     * 获取类型
     * @return
     */
    @HasRole({"ADMIN"})
    @GetMapping("/{type}")
    public ResponseEntity<BaseResponse<TypeDTO>> getType(@PathVariable String type) {
        TypeDTO typeDTO = typeService.getType(type);
        return ResultUtil.success("获取类型成功", typeDTO);
    }
}
