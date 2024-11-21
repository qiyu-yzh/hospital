package org.qiyu.hospital.service;

import org.qiyu.hospital.model.dto.TypeDTO;
import org.qiyu.hospital.model.entity.TypeDO;
import org.qiyu.hospital.model.vo.ConsoleTypeAddVO;
import org.qiyu.hospital.model.vo.ConsoleTypeEditVO;

import java.util.List;

public interface TypeService {
    List<TypeDTO> getTypeList();

    void deleteType(String uuid);

    void addType(ConsoleTypeAddVO consoleTypeAddVO);

    void updateType(ConsoleTypeEditVO consoleTypeEditVO);

    TypeDTO getType(String type);
}
