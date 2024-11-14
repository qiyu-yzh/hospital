package org.qiyu.hospital.service.logic;

import com.xlf.utility.ErrorCode;
import com.xlf.utility.exception.BusinessException;
import com.xlf.utility.util.UuidUtil;
import org.qiyu.hospital.mapper.TypeMapper;
import org.qiyu.hospital.model.dto.TypeDTO;
import org.qiyu.hospital.model.entity.TypeDO;
import org.qiyu.hospital.model.vo.ConsoleTypeAddVO;
import org.qiyu.hospital.model.vo.ConsoleTypeEditVO;
import org.qiyu.hospital.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeLogic implements TypeService {

    private final TypeMapper typeMapper;

    public TypeLogic(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public List<TypeDTO> getTypeList() {
        List<TypeDO> typeList = typeMapper.getTypeList();
        List<TypeDTO> typeDTOList = new ArrayList<>();
        for (int i = 0; i < typeList.size(); i++) {
            TypeDTO typeDTO = new TypeDTO();
            BeanUtils.copyProperties(typeList.get(i), typeDTO);
            typeDTOList.add(typeDTO);

        }
        return typeDTOList;
    }

    @Override
    public void deleteType(String uuid) {
        TypeDO typeDO = typeMapper.getTypeUuid(uuid);
        if (typeDO == null) {
            throw new BusinessException("类型不存在", ErrorCode.OPERATION_DENIED);
        }
        typeMapper.deleteType(uuid);

    }

    @Override
    public void addType(ConsoleTypeAddVO consoleTypeAddVO) {
        int TypeDO = typeMapper.checkTypeExist(consoleTypeAddVO.getOutpatientType());
        if (TypeDO != 0) {
            throw new BusinessException("类型已存在", ErrorCode.OPERATION_DENIED);
        }
        TypeDO newType = new TypeDO();
        BeanUtils.copyProperties(consoleTypeAddVO, newType);
        newType.setTypeUuid(UuidUtil.generateStringUuid());
        typeMapper.addType(newType);
    }

    @Override
    public void updateType(ConsoleTypeEditVO consoleTypeEditVO) {
        TypeDO typeDO = typeMapper.getTypeUuid(consoleTypeEditVO.getTypeUuid());
        if (typeDO == null) {
            throw new BusinessException("类型不存在", ErrorCode.OPERATION_DENIED);
        }
        TypeDO newType = new TypeDO();
        BeanUtils.copyProperties(consoleTypeEditVO, newType);
        typeMapper.updateType(newType);
    }

}
