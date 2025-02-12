package org.example.service.jpa;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.EquipTypeDto;
import org.example.repository.EquipTypeRepository;
import org.example.service.EquipTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.utils.Mappers.TO_EQUIP_TYPE_DTO_FUNCTION;

@Slf4j
@Service
@Transactional(readOnly = true)
public class EquipTypeServiceImpl implements EquipTypeService {

//    private final  = LoggerFactory.getLogger(EquipTypeServiceImpl.class);

    private final EquipTypeRepository repository;

    public EquipTypeServiceImpl(EquipTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EquipTypeDto> findAll(Pageable pageable) {
        log.info("findAll");
        return this.repository.findAll(pageable).stream()
                .map(TO_EQUIP_TYPE_DTO_FUNCTION)
                .toList();
    }

    @Override
    public EquipTypeDto findByNameIgnoreCase(String name) {
        log.info("findByNameIgnoreCase: {}", name);
        return this.repository.findByNameIgnoreCase(name);
    }
}
