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
    public CompanyType save(CompanyType companyType) {
        return repository.save(companyType);
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
            repository.delete(companyInstance.get());
            return Optional.of(companyInstance).orElseThrow();
        }
        return Optional.empty();
    }

	@Override
	@Transactional
	public Optional<CompanyType> update(Long id, CompanyType companyType) {
		Optional<CompanyType> companyInstance = repository.findById(id);
		if (companyInstance.isPresent()) {
			CompanyType newCompanyType = companyInstance.get();
			BeanUtils.copyProperties(companyType, newCompanyType);
			return Optional.of(repository.save(newCompanyType));
		}
		return Optional.empty();
	}
}
