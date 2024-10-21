package com.backend.companytype.infrastructure;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.companytype.application.CompanyTypeService;
import com.backend.companytype.domain.CompanyType;
import com.backend.companytype.domain.CompanyTypeDto;
import com.backend.utils.enums.Status;

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {
    @Autowired
    private CompanyTypeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Set<CompanyType> findAll() {
        Set<CompanyType> types = new LinkedHashSet<>((List<CompanyType>) repository.findAll());
        return types;
    }

    @Override
    @Transactional
    public CompanyType save(CompanyTypeDto companyType) {
        CompanyType companyTypeDb = new CompanyType();
        BeanUtils.copyProperties(companyType, companyTypeDb, companyType.getClass());
        companyTypeDb.setStatus(Status.ENABLED);

        return repository.save(companyTypeDb);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Optional<CompanyType> delete(Long id) {
        Optional<CompanyType> companyInstance = this.findById(id);
        if (companyInstance.isPresent()) {
            companyInstance.orElseThrow().setStatus(Status.DISABLED);
            return Optional.of(repository.save(companyInstance.orElseThrow()));
        }
        return Optional.empty();
    }

	@Override
	@Transactional
	public Optional<CompanyType> update(Long id, CompanyTypeDto companyType) {
		Optional<CompanyType> companyInstance = repository.findById(id);
		if (companyInstance.isPresent()) {
			CompanyType companyTypeDb = companyInstance.orElseThrow();
            BeanUtils.copyProperties(companyType, companyTypeDb, companyType.getClass());

            return Optional.of(repository.save(companyTypeDb));
		}
		return Optional.empty();
	}
}
