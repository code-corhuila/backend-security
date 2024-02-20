package com.corhuila.servicesecurity.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corhuila.servicesecurity.Entity.BaseModel;
import com.corhuila.servicesecurity.IRepository.IBaseModelRepository;
import com.corhuila.servicesecurity.IService.IBaseModelService;
import com.corhuila.servicesecurity.Utils.GlobalConstants;

@Service
public class BaseModelService<T extends BaseModel> implements IBaseModelService<T> {

    @Autowired
    private IBaseModelRepository<T, Long> repositoryBaseModel;

    @Override
    public List<T> all() {
        return repositoryBaseModel.findAll();
    }    

    @Override
    public List<T> findByStateTrue() {
        return repositoryBaseModel.findAll();
    }  

	@Override
    public T findById(Long id) throws Exception {
        Optional<T> op = repositoryBaseModel.findById(id);

        if (op.isEmpty()) {
            throw new Exception("Registro no encontrado");
        }

        return op.get();
    }

    @Override
    public T save(T entity) {
        entity.setCreatedAt(LocalDateTime.now());
        return repositoryBaseModel.save(entity);
    }

    @Override
    public void update(Long id, T entity) throws Exception {
        Optional<T> op = repositoryBaseModel.findById(id);

        if (op.isEmpty()) {
            throw new Exception("Registro no encontrado");
        }

        T entityUpdate = op.get();
        BeanUtils.copyProperties(entity, entityUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

        entityUpdate.setUpdatedAt(LocalDateTime.now());
        repositoryBaseModel.save(entityUpdate);
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<T> op = repositoryBaseModel.findById(id);

        if (op.isEmpty()) {
            throw new Exception("Registro no encontrado");
        }

        T entityUpdate = op.get();
        entityUpdate.setDeletedAt(LocalDateTime.now());

        repositoryBaseModel.save(entityUpdate);
    }
}