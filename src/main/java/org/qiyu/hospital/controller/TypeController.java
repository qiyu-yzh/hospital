package org.qiyu.hospital.controller;

import com.xlf.utility.BaseResponse;
import com.xlf.utility.ResultUtil;
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
public class TypeController {
    private final TypeService typeService;

    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<TypeDTO>>> getTypeList() {
        List<TypeDTO> typeList = typeService.getTypeList();
        return ResultUtil.success("获取类型列表成功", typeList);
    }


    @DeleteMapping("/{uuid}")
    public ResponseEntity<BaseResponse<Void>> deleteType(@PathVariable String uuid) {
        typeService.deleteType(uuid);
        return ResultUtil.success("删除类型成功");
    }

   @PostMapping("/console/add")
    public ResponseEntity<BaseResponse<Void>> addType(@RequestBody @Validated ConsoleTypeAddVO consoleTypeAddVO) {
        typeService.addType(consoleTypeAddVO);
        return ResultUtil.success("添加类型成功");
    }

    @PutMapping("/console/update")
    public ResponseEntity<BaseResponse<Void>> updateType(@RequestBody @Validated ConsoleTypeEditVO consoleTypeEditVO) {
        typeService.updateType(consoleTypeEditVO);
        return ResultUtil.success("更新类型成功");
    }

}
