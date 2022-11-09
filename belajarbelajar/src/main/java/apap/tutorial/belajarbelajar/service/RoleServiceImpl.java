package apap.tutorial.belajarbelajar.service;

import apap.tutorial.belajarbelajar.model.RoleModel;
import apap.tutorial.belajarbelajar.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDb roleDb;

    @Override
    public RoleModel addRole(RoleModel role){
        return roleDb.save(role);
    }

    @Override
    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }

    @Override
    public RoleModel getById(Long id){
        Optional<RoleModel> roleId = roleDb.findById(id);
        if (roleId.isPresent()) {
            return roleId.get();
        }
        else return null;
    }

}